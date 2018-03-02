/**
 * FileName : {@link TempFileStorage}.java
 * Created : 2018. 2. 20. 오후 11:05:35
 * Author : jeong
 * Summary :
 * Copyright (C) 2018 Formal Works Inc. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.file;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.goldyframework.does.SonarHelper;
import com.google.common.annotations.VisibleForTesting;

/**
 * Temp 파일을 수집하고 일괄 제거하는 도구입니다.<br>
 * 프로그램 종료 시점 전에 deleteAll을 호출하세요
 */
public class TempFileStorage {
	
	/**
	 * {@link TempFileStorage} 클래스 싱글톤 인스턴스
	 */
	private static TempFileStorage instance;
	
	/**
	 * Lock Object
	 */
	private static final Object LOCK_OBJECT = new Object();
	
	/**
	 * slf4j Logger
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(TempFileStorage.class);
	
	public static TempFileStorage getInstance() {
		
		synchronized (LOCK_OBJECT) {
			if (instance == null) {
				instance = new TempFileStorage();
			}
		}
		
		return instance;
	}
	
	@VisibleForTesting
	final Set<File> tempFiles = new HashSet<>();
	
	/**
	 * {@link TempFileStorage} 클래스의 새 인스턴스를 초기화 합니다.
	 */
	public TempFileStorage() {
		// do nothing
	}
	
	private void cleanTempFileField() {
		
		this.tempFiles.removeIf(t -> t.exists() == false);
		
	}
	
	private int delayOf(Date start) {
		
		long startTime = start.getTime();
		long current = new Date().getTime();
		return (int) (current - startTime);
	}
	
	public void deleteAll() {
		
		for (File file : this.tempFiles) {
			
			String absolutePath = file.getAbsolutePath();
			String message1 = MessageFormat.format("Temp File 를 제거 시도합니다. : {0}", absolutePath); //$NON-NLS-1$
			LOGGER.trace(message1);
			
			if (file.exists()) {
				if (file.delete()) {
					String message3 = MessageFormat.format("Temp 파일 제거에 성공하였습니다 : {0}", absolutePath); //$NON-NLS-1$
					LOGGER.info(message3);
				}
			} else {
				String message2 = MessageFormat.format("이미 존재하지 않은 파일이므로 제거 작업을 수행하지 않습니다. : {0}", absolutePath); //$NON-NLS-1$
				LOGGER.trace(message2);
			}
		}
		
		this.cleanTempFileField();
		
		if (this.tempFiles.isEmpty() == false) {
			this.removeWait(500);
		}
		
	}
	
	public void pushTempFile(File file) {
		
		this.tempFiles.add(file);
	}
	
	private void removeWait(int milliseconds) {
		
		for (File file : this.tempFiles) {
			try {
				Files.delete(Paths.get(new URI(file.getAbsolutePath())));
			} catch (IOException | URISyntaxException e) {
				LOGGER.debug("Files.delete를 하는 중 오류 발생", e);
				SonarHelper.unuse(e);
			}
			
			Date current = new Date();
			boolean stop = false;
			while (file.exists() || stop) {
				file.delete();
				if (this.delayOf(current) > milliseconds) {
					stop = true;
					file.deleteOnExit();
				}
			}
			this.cleanTempFileField();
		}
	}
}