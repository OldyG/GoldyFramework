/**
 * FileName : {@link AbstractRepositoryBody}.java
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
import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.goldyframework.inspection.ObjectInspection;
import com.goldyframework.repository.exception.NotRegisteredFileException;
import com.goldyframework.repository.filenaming.FileNamingType;
import com.google.common.base.Strings;

/**
 * 가상 저장소 바디
 *
 * @author 2017. 6. 18. 오후 1:43:59 jeong
 */
public abstract class AbstractRepositoryBody implements RepositoryBody {

	/**
	 * slf4j Logger
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractRepositoryBody.class);

	/**
	 * 디렉토리
	 */
	private String directory;

	/**
	 * 파일 이름 정의 방법
	 */
	private FileNamingType namingType;

	/**
	 * 기본 확장자
	 */
	private String defaultExtension;

	/**
	 * {@link AbstractRepositoryBody} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author jeong
	 * @since 2017. 4. 10. 오후 9:33:30
	 */
	public AbstractRepositoryBody() {
		super();
	}

	/**
	 * {@inheritDoc}
	 *
	 * @author 2017. 6. 18. 오후 1:44:20 jeong
	 */
	@Override
	public String generateSavePath() {

		return this.generateSavePath(this.defaultExtension);
	}

	/**
	 * {@inheritDoc}
	 *
	 * @author 2017. 6. 18. 오후 1:44:21 jeong
	 */
	@Override
	public String generateSavePath(final String extension) {

		ObjectInspection.checkNull(extension);
		return this.namingType.getFileNaming().generageSavePath(this.directory, this.getBaseName(), extension);
	}

	/**
	 * 파일의 이름을 작성합니다.
	 * 이 함수가 호출되는 관리타입
	 * - {@link FileNamingType#CALLER_INPUT}
	 *
	 * @author jeong
	 * @return 기본이름
	 * @date 2016. 5. 18.
	 */
	protected abstract String getBaseName();

	/**
	 * {@inheritDoc}
	 *
	 * @author 2017. 6. 18. 오후 1:44:44 jeong
	 */
	@Override
	public File getRegisteredFile() throws NotRegisteredFileException, SQLException, RepositoryException {

		final String fileUrl = this.getRegisteredFileName();
		if (Strings.isNullOrEmpty(fileUrl)) {
			throw new NotRegisteredFileException();
		}
		return new File(this.directory, fileUrl);
	}

	/**
	 * 등록된 파일 이름을 반환한다.
	 *
	 * @author 2017. 6. 18. 오후 1:44:48 jeong
	 * @return 등록된 파일 이름
	 * @throws NotRegisteredFileException
	 *             등록되지 않은 파일일경우 발생하는 예외 사항
	 * @throws SQLException
	 *             SQL 관련 예외사항
	 * @throws RepositoryException
	 *             이 외 발생하는 예외 사항
	 */
	protected abstract String getRegisteredFileName()
		throws NotRegisteredFileException, SQLException, RepositoryException;

	/**
	 * 파일의 기본 확장자를 작성합니다.<br>
	 * 문자열에서 점(.)을 표시하지 않습니다.<br>
	 * (올바른 예 : "txt", "html")<br>
	 *
	 * @author jeong
	 * @date 2016. 5. 19.
	 * @return 기본 확장자를 초기화한다.
	 */
	protected abstract String initialDefaultExtension();

	/**
	 * 파일이 저장되어있는 디렉토리를 지정합니다.<br>
	 * 디렉토리는 절대경로이며 마지막 폴더구분자는 (\\ 또는 /) 를 작성하지 않습니다.<br>
	 *
	 * @author jeong
	 * @return 기본 디렉토리를 초기화한다.
	 * @date 2016. 5. 18.
	 */
	protected abstract String initialDirectory();

	/**
	 * 초기화
	 *
	 * @author 2017. 6. 18. 오후 1:45:24 jeong
	 */
	protected void initialize() {

		this.directory = this.initialDirectory();
		final File defaultDirectory = new File(this.directory);
		if (defaultDirectory.exists() == false) {

			final String absolutePath = defaultDirectory.getAbsolutePath();
			if (defaultDirectory.mkdirs()) {
				final String message = MessageFormat.format("디렉토리 생성 성공{0}", absolutePath); //$NON-NLS-1$
				LOGGER.trace(message);
			} else {
				final String message = MessageFormat.format("디렉토리 생성에 실패하였습니다.{0}", absolutePath); //$NON-NLS-1$
				LOGGER.error(message);
			}

		}
		this.namingType = this.initialNamingType();
		this.defaultExtension = this.initialDefaultExtension();

	}

	/**
	 * @author 2017. 6. 18. 오후 1:45:50 jeong
	 * @return 이름 관리 방법을 초기화한다.
	 */
	protected abstract FileNamingType initialNamingType();
}
