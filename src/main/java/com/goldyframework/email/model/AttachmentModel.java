/**
 * FileName : {@link AttachmentModel}.java
 * Created : 2017. 4. 10.
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Goldy Project. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.email.model;

import java.io.File;

import com.goldyframework.utils.NullGtils;

/**
 * 첨부파일 모델
 *
 * @author 2017. 6. 18. 오전 11:36:14 jeong
 */
public class AttachmentModel {
	
	/**
	 * 파일 이름
	 */
	private final String fileName;
	
	/**
	 * 대상 파일
	 */
	private final File file;
	
	/**
	 * {@link AttachmentModel} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author 2017. 6. 18. 오전 11:36:56 jeong
	 * @param fileName
	 *            파일 이름
	 * @param file
	 *            파일 데이터
	 */
	public AttachmentModel(final String fileName, final File file) {
		this.fileName = NullGtils.throwIfNull(fileName);
		this.file = NullGtils.throwIfNull(file);
	}
	
	/**
	 * file를 반환합니다.
	 *
	 * @return file
	 * @author 2017. 6. 18. 오전 11:37:22 jeong
	 * @see {@link #file}
	 */
	public final File getFile() {
		
		return this.file;
	}
	
	/**
	 * fileName를 반환합니다.
	 *
	 * @return fileName
	 * @author 2017. 6. 18. 오전 11:37:22 jeong
	 * @see {@link #fileName}
	 */
	public final String getFileName() {
		
		return this.fileName;
	}
	
}
