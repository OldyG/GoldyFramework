/**
 * FileName : RepositoryBody.java
 * Created : 2017. 4. 10.
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Goldy Project. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.repository;

import java.io.File;
import java.sql.SQLException;

public interface RepositoryBody {

	String generateSavePath();

	String generateSavePath(String extension);

	String getDownloadName() throws SQLException, RepositoryException, NotRegisteredFileException;

	File getRegisteredFile() throws SQLException, RepositoryException, NotRegisteredFileException;

}
