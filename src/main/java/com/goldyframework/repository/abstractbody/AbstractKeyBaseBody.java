/**
 * FileName : {@link AbstractKeyBaseBody}.java
 * Created : 2018. 2. 2. 오후 10:35:18
 * Author : jeong
 * Summary :
 * Copyright (C) 2018 Formal Works Inc. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 (주)포멀웍스에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.repository.abstractbody;

import java.io.File;

import org.apache.commons.io.FilenameUtils;

import com.goldyframework.repository.AbstractRepositoryBody;
import com.goldyframework.repository.exception.NotRegisteredFileException;
import com.goldyframework.repository.filenaming.FileNamingType;

public abstract class AbstractKeyBaseBody extends AbstractRepositoryBody {
	
	/**
	 * {@link AbstractKeyBaseBody} 클래스의 새 인스턴스를 초기화 합니다.
	 * 
	 * @author 2018. 2. 2. 오후 10:36:17 jeong
	 */
	public AbstractKeyBaseBody() {
		
		super();
	}
	
	/**
	 * {@inheritDoc}
	 * 
	 * @author 2018. 2. 2. 오후 10:00:20 jeong
	 */
	@Override
	protected String getBaseName() {
		
		return this.getUniqueKey();
	}
	
	/**
	 * {@inheritDoc}
	 * 
	 * @author 2018. 2. 2. 오후 10:00:20 jeong
	 */
	@Override
	protected String getRegisteredFileName() throws NotRegisteredFileException {
		
		final File baseDirectory = this.getDirectory();
		
		final File[] listFiles = baseDirectory.listFiles();
		if (listFiles != null) {
			for (final File file : listFiles) {
				
				final String expectedName = FilenameUtils.getBaseName(file.getName());
				final String actualName = this.getUniqueKey();
				
				if (expectedName.equals(actualName)) {
					return file.getName();
				}
			}
		}
		throw new NotRegisteredFileException();
	}
	
	protected abstract String getUniqueKey();
	
	/**
	 * {@inheritDoc}
	 * 
	 * @author 2018. 2. 2. 오후 10:39:35 jeong
	 */
	@Override
	protected String getDefaultExtension() {
		
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * {@inheritDoc}
	 * 
	 * @author 2018. 2. 2. 오후 10:00:20 jeong
	 */
	@Override
	protected FileNamingType getNamingType() {
		
		return FileNamingType.CALLER_INPUT;
	}
	
}
