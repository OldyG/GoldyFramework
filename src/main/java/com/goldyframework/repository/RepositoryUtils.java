/**
 * FileName : {@link RepositoryUtil}.java
 * Created : 2017. 4. 10.
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Goldy Project. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.repository;

import java.io.IOException;
import java.sql.SQLException;

import org.springframework.http.ResponseEntity;

import com.goldyframework.inspection.ObjectInspection;
import com.goldyframework.repository.exception.NotRegisteredFileException;

/**
 * 저장소 관련 공통 작업 목록
 *
 * @author 2017. 6. 18. 오후 1:56:26 jeong
 */
public class RepositoryUtils {
	
	/**
	 * {@link RepositoryUtil} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author jeong
	 * @since 2017. 4. 10. 오후 9:33:46
	 */
	public RepositoryUtils() {
		
		super();
	}
	
	/**
	 * 이미지를 반환한다.
	 *
	 * @author 2017. 6. 18. 오후 1:56:41 jeong
	 * @param repository
	 *            저장소 바디
	 * @return 이미지 byte[]
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
	public ResponseEntity<byte[]> getImage(final RepositoryBody repository)
		throws IOException, SQLException, RepositoryException, NotRegisteredFileException {
		
		ObjectInspection.checkNull(repository);
		final RepositoryService service = new RepositoryServiceImpl(repository);
		
		return service.displayImage();
		
	}
}
