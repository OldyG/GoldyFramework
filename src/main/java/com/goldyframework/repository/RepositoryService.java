/**
 * FileName : {@link RepositoryService}.java
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

import com.goldyframework.repository.exception.NotRegisteredFileException;

/**
 * 저장소 관리 서비스
 *
 * @author 2017. 6. 18. 오후 1:48:54 jeong
 */
public interface RepositoryService {
	
	/**
	 * 기존파일을 제거하고 주어진 첨부파일로 변경한다.
	 *
	 * @author 2017. 6. 18. 오후 1:53:50 jeong
	 * @param multipartFile
	 *            변경대상 첨부파일
	 * @return 저장된 파일
	 * @throws SQLException
	 *             SQL 관련 예외사항
	 * @throws RepositoryException
	 *             이 외 발생하는 예외 사항
	 * @throws IOException
	 *             Signals that an I/O exception of some sort has occurred. This class is the general class of
	 *             exceptions produced by failed or interrupted I/O operations.
	 */
	File change(MultipartFile multipartFile) throws SQLException, RepositoryException, IOException;
	
	/**
	 * 파일을 제거합니다.
	 *
	 * @author jeonghyun.kum
	 * @throws SQLException
	 *             SQL 관련 예외사항
	 * @throws RepositoryException
	 *             이 외 발생하는 예외 사항
	 * @since 2016. 5. 20. 오전 10:42:40
	 */
	void delete() throws SQLException, RepositoryException;
	
	/**
	 * 이미지를 byte[]로 반환한다.
	 *
	 * @author 2017. 6. 18. 오후 1:49:20 jeong
	 * @return 이미지
	 * @throws NotRegisteredFileException
	 *             등록되지 않은 파일일경우 발생하는 예외 사항
	 * @throws SQLException
	 *             SQL 관련 예외사항
	 * @throws RepositoryException
	 *             이 외 발생하는 예외 사항
	 * @throws IOException
	 *             Signals that an I/O exception of some sort has occurred. This class is the general class of
	 *             exceptions produced by failed or interrupted I/O operations.
	 */
	ResponseEntity<byte[]> displayImage()
		throws SQLException, RepositoryException, NotRegisteredFileException, IOException;
	
	/**
	 * 파일을 다운로드 합니다.
	 *
	 * @author 2017. 6. 18. 오후 1:51:55 jeong
	 * @return 다운로드 {@link InputStreamResource}
	 * @throws NotRegisteredFileException
	 *             등록되지 않은 파일일경우 발생하는 예외 사항
	 * @throws SQLException
	 *             SQL 관련 예외사항
	 * @throws RepositoryException
	 *             이 외 발생하는 예외 사항
	 * @throws IOException
	 *             Signals that an I/O exception of some sort has occurred. This class is the general class of
	 *             exceptions produced by failed or interrupted I/O operations.
	 */
	ResponseEntity<InputStreamResource> download()
		throws SQLException, RepositoryException, NotRegisteredFileException, IOException;
	
	/**
	 * 파일을 가져옵니다.
	 *
	 * @author 2017. 6. 18. 오후 1:49:27 jeong
	 * @return 파일
	 * @throws NotRegisteredFileException
	 *             등록되지 않은 파일일경우 발생하는 예외 사항
	 * @throws SQLException
	 *             SQL 관련 예외사항
	 * @throws RepositoryException
	 *             이 외 발생하는 예외 사항
	 */
	File getFile() throws SQLException, RepositoryException, NotRegisteredFileException;
	
	/**
	 * 파일을 읽어 byte[]로 반환합니다.
	 *
	 * @author jeonghyun.kum
	 * @return byte[]
	 * @throws IOException
	 *             Signals that an I/O exception of some sort has occurred. This class is the general class of
	 *             exceptions produced by failed or interrupted I/O operations.
	 * @throws NotRegisteredFileException
	 *             등록되지 않은 파일일경우 발생하는 예외 사항
	 * @throws SQLException
	 *             SQL 관련 예외사항
	 * @throws RepositoryException
	 *             이 외 발생하는 예외 사항
	 * @since 2016. 5. 20. 오전 10:44:55
	 */
	byte[] readToByteArray() throws IOException, SQLException, RepositoryException, NotRegisteredFileException;
	
	/**
	 * 파일을 읽어와 String으로 반환합니다.
	 *
	 * @author jeonghyun.kum
	 * @since 2016. 5. 20. 오전 10:42:59
	 * @return 파일 본문
	 * @throws NotRegisteredFileException
	 *             등록되지 않은 파일일경우 발생하는 예외 사항
	 * @throws SQLException
	 *             SQL 관련 예외사항
	 * @throws RepositoryException
	 *             이 외 발생하는 예외 사항
	 * @throws IOException
	 *             Signals that an I/O exception of some sort has occurred. This class is the general class of
	 *             exceptions produced by failed or interrupted I/O operations.
	 */
	String readToString() throws SQLException, RepositoryException, NotRegisteredFileException, IOException;
	
	/**
	 * MultipartFile 인자를 받아 저장합니다.
	 *
	 * @author jeonghyun.kum
	 * @since 2016. 5. 20. 오전 10:45:12
	 * @param multipartFile
	 *            첨부파일
	 * @return 로컬 파일
	 * @throws IOException
	 *             Signals that an I/O exception of some sort has occurred. This class is the general class of
	 *             exceptions produced by failed or interrupted I/O operations.
	 */
	File save(MultipartFile multipartFile) throws IOException;
	
	/**
	 * 파일 내용을 작성합니다.
	 *
	 * @author jeonghyun.kum
	 * @since 2016. 5. 20. 오전 10:45:28
	 * @param content
	 *            작성될 파일 본문
	 * @return 로컬 파일
	 * @throws SQLException
	 *             SQL 관련 예외사항
	 * @throws RepositoryException
	 *             이 외 발생하는 예외 사항
	 * @throws IOException
	 *             Signals that an I/O exception of some sort has occurred. This class is the general class of
	 *             exceptions produced by failed or interrupted I/O operations.
	 */
	File write(String content) throws SQLException, RepositoryException, IOException;
	
}
