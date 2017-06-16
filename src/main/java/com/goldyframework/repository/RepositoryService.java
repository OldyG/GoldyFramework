/**
 * FileName : RepositoryService.java
 * Created : 2017. 4. 10.
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Goldy Project. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.repository;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface RepositoryService {

	/**
	 * 파일을 제거합니다.
	 *
	 * @author jeonghyun.kum
	 * @since 2016. 5. 20. 오전 10:42:40
	 */
	void delete() throws SQLException, RepositoryException;

	ResponseEntity<byte[]> displayImage() throws SQLException, RepositoryException, NotRegisteredFileException, IOException;

	ResponseEntity<InputStreamResource> download() throws SQLException, RepositoryException, NotRegisteredFileException, IOException;

	/**
	 * 파일을 가져옵니다.
	 *
	 * @author jeonghyun.kum
	 * @since 2016. 5. 20. 오전 10:42:51
	 */
	File getFile() throws SQLException, RepositoryException, NotRegisteredFileException;

	/**
	 * 파일을 읽어 byte[]로 반환합니다.
	 *
	 * @author jeonghyun.kum
	 * @since 2016. 5. 20. 오전 10:44:55
	 */
	byte[] readToByteArray() throws IOException, SQLException, RepositoryException, NotRegisteredFileException;

	/**
	 * 파일을 읽어와 String으로 반환합니다.
	 *
	 * @author jeonghyun.kum
	 * @since 2016. 5. 20. 오전 10:42:59
	 * @return
	 * @throws SQLException
	 * @throws NotFoundVoException
	 * @throws NotRegisteredFileException
	 * @throws IOException
	 */
	String readToString() throws SQLException, RepositoryException, NotRegisteredFileException, IOException;

	/**
	 * MultipartFile 인자를 받아 저장합니다.
	 *
	 * @author jeonghyun.kum
	 * @since 2016. 5. 20. 오전 10:45:12
	 * @param multipartFile
	 * @return
	 * @throws IOException
	 */
	File save(MultipartFile multipartFile) throws IOException;

	/**
	 * 파일 내용을 작성합니다.
	 *
	 * @author jeonghyun.kum
	 * @since 2016. 5. 20. 오전 10:45:28
	 * @param content
	 * @return
	 * @throws SQLException
	 * @throws NotFoundVoException
	 * @throws IOException
	 */
	File write(String content) throws SQLException, RepositoryException, IOException;

}
