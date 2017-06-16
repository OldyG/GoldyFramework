/**
 * FileName : RepositoryUtil.java
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

public class RepositoryUtil {

	/**
	 * RepositoryUtil 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author jeong
	 * @since 2017. 4. 10. 오후 9:33:46
	 */
	public RepositoryUtil() {
		super();
	}

	public ResponseEntity<byte[]> getImage(
		final RepositoryBody repository) throws IOException, SQLException, RepositoryException, NotRegisteredFileException {

		final RepositoryService service = new RepositoryServiceImpl(repository);

		return service.displayImage();

	}
}
