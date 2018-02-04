/**
 * FileName : {@link AbstractKeyFolderBaseBody}.java
 * Created : 2018. 2. 3. 오후 10:31:42
 * Author : jeong
 * Summary :
 * Copyright (C) 2018 Formal Works Inc. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 (주)포멀웍스에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.repository.abstractbody;

import java.io.File;

import com.goldyframework.repository.AbstractRepositoryBody;
import com.goldyframework.repository.RepositoryException;
import com.goldyframework.repository.filenaming.FileNamingType;

public abstract class AbstractKeyFolderBaseBody extends AbstractRepositoryBody {
	
	/**
	 * {@inheritDoc}
	 * 
	 * @author 2018. 2. 3. 오후 10:32:10 jeong
	 */
	@Override
	public String getDownloadName() throws RepositoryException {
		
		throw new RepositoryException("Unsupport Method");
	}
	
	/**
	 * {@inheritDoc}
	 * 
	 * @author 2018. 2. 3. 오후 10:32:10 jeong
	 */
	@Override
	public FileNamingType getNamingType() {
		
		return FileNamingType.CALLER_INPUT;
	}
	
	/**
	 * {@inheritDoc}
	 * 
	 * @author 2018. 2. 3. 오후 10:32:10 jeong
	 */
	@Override
	protected String getRegisteredFileName() {
		
		final File rootDirectory = this.getRootDirectory();
		
		final File subDirectory = new File(rootDirectory, this.getBaseName());
		if (subDirectory.exists() == false) {
			subDirectory.mkdirs();
		}
		
		return subDirectory.getName();
	}
	
	/**
	 * {@inheritDoc}
	 * 
	 * @author 2018. 2. 3. 오후 10:32:10 jeong
	 */
	@Override
	protected abstract File getRootDirectory();
	
}
