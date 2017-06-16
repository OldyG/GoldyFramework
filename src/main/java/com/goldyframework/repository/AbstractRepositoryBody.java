/**
 * FileName : AbstractRepositoryBody.java
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

import com.goldyframework.repository.filenaming.FileNamingType;
import com.google.common.base.Strings;

public abstract class AbstractRepositoryBody implements RepositoryBody {

	private String directory;

	private FileNamingType namingType;

	private String defaultExtension;

	/**
	 * AbstractRepositoryBody 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author jeong
	 * @since 2017. 4. 10. 오후 9:33:30
	 */
	public AbstractRepositoryBody() {
		super();
	}

	@Override
	public String generateSavePath() {

		return this.generateSavePath(this.defaultExtension);
	}

	@Override
	public String generateSavePath(final String extension) {

		return this.namingType.getManagement().generageSavePath(this.directory, this.getBaseName(), extension);
	}

	/**
	 * 파일의 이름을 작성합니다.
	 * 이 함수가 호출되는 관리타입
	 * - {@link FileNamingType#CALLER_INPUT}
	 *
	 * @author jeong
	 * @date 2016. 5. 18.
	 */
	protected abstract String getBaseName();

	@Override
	public File getRegisteredFile() throws NotRegisteredFileException, SQLException, RepositoryException {

		final String fileUrl = this.getRegisteredFileName();
		if (Strings.isNullOrEmpty(fileUrl)) {
			throw new NotRegisteredFileException();
		}
		return new File(this.directory + fileUrl);
	}

	protected abstract String getRegisteredFileName() throws NotRegisteredFileException, SQLException, RepositoryException;

	protected void initial() {

		this.directory = this.initialDirectory();
		final File defaultDirectory = new File(this.directory);
		if (defaultDirectory.exists() == false) {
			defaultDirectory.mkdirs();
		}

		this.namingType = this.initialNamingType();
		this.defaultExtension = this.initialDefaultExtension();
	}

	/**
	 * 파일의 기본 확장자를 작성합니다.<br>
	 * 문자열에서 점(.)을 표시하지 않습니다.<br>
	 * (올바른 예 : "txt", "html")<br>
	 *
	 * @author jeong
	 * @date 2016. 5. 19.
	 * @return
	 */
	protected abstract String initialDefaultExtension();

	/**
	 * 파일이 저장되어있는 디렉토리를 지정합니다.<br>
	 * 디렉토리는 절대경로이며 마지막 폴더구분자는 (\\ 또는 /) 를 작성하지 않습니다.<br>
	 *
	 * @author jeong
	 * @return
	 * @date 2016. 5. 18.
	 */
	protected abstract String initialDirectory();

	protected abstract FileNamingType initialNamingType();
}
