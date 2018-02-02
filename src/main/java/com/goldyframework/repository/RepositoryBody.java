/**
 * FileName : {@link RepositoryBody}.java
 * Created : 2017. 4. 10.
 * Author : jeong
 * Summary :
 * Copyright (C) 2018 Formal Works Inc. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.repository;

import java.io.File;
import java.sql.SQLException;

import com.goldyframework.repository.exception.NotRegisteredFileException;

/**
 * 저장소 바디
 *
 * @author 2017. 6. 18. 오후 1:46:02 jeong
 */
public interface RepositoryBody {
	
	/**
	 * 저장 경로를 생성합니다.
	 *
	 * @author 2017. 6. 18. 오후 1:46:15 jeong
	 * @return 저장 경로
	 */
	String generateSavePath();
	
	/**
	 * 주어진 확장자를 사용한 저장 경로를 생성합니다.
	 *
	 * @author 2017. 6. 18. 오후 1:46:25 jeong
	 * @param extension
	 *            확장자
	 * @return 저장 경로
	 */
	String generateSavePath(String extension);
	
	/**
	 * 사용자가 다운로드 할 때의 이름을 반환합니다. 확장자를 제외한 파일의 이름만 작성합니다.
	 *
	 * @author 2017. 6. 18. 오후 1:46:42 jeong
	 * @return 다운로드 이름
	 * @throws NotRegisteredFileException
	 *             등록되지 않은 파일일경우 발생하는 예외 사항
	 * @throws SQLException
	 *             SQL 관련 예외사항
	 * @throws RepositoryException
	 *             이 외 발생하는 예외 사항
	 */
	String getDownloadName() throws NotRegisteredFileException, SQLException, RepositoryException;
	
	/**
	 * 등록된 파일을 반환합니다.
	 *
	 * @author 2017. 6. 18. 오후 1:47:36 jeong
	 * @return 등록된 파일 이름
	 * @throws NotRegisteredFileException
	 *             등록되지 않은 파일일경우 발생하는 예외 사항
	 * @throws SQLException
	 *             SQL 관련 예외사항
	 * @throws RepositoryException
	 *             이 외 발생하는 예외 사항
	 */
	File getRegisteredFile() throws NotRegisteredFileException, SQLException, RepositoryException;
	
}
