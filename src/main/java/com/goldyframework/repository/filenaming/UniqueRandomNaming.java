/**
 * FileName : {@link UniqueRandomNaming}.java
 * Created : 2017. 4. 10.
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Goldy Project. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.repository.filenaming;

import java.io.File;
import java.text.MessageFormat;

import com.goldyframework.inspection.ObjectInspection;
import com.goldyframework.random.RandomStringGtils;

/**
 * 파일 이름을 무작위로 생성합니다.<br>
 * 중복 파일이 존재할 경우 재생성 합니다.
 */
class UniqueRandomNaming implements FileNaming {

	/**
	 * 파일 이름 최소 크기
	 */
	private static final int MIN_LENGTH = 10;

	/**
	 * 파일 이름 최대 크기
	 */
	private static final int MAX_LENGTH = 15;

	/**
	 * {@link UniqueRandomNaming} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author jeong
	 * @since 2017. 4. 10. 오후 9:33:23
	 */
	public UniqueRandomNaming() {
		super();
	}

	/**
	 * {@inheritDoc}
	 *
	 * @author 2017. 6. 18. 오후 1:36:20 jeong
	 */
	@Override
	public String generageSavePath(final String directory, final String baseName, final String extension) {

		ObjectInspection.checkNull(directory);
		ObjectInspection.checkNull(baseName);
		ObjectInspection.checkNull(extension);
		while (true) {
			final String randomName = RandomStringGtils.createRandomString(MIN_LENGTH, MAX_LENGTH - MIN_LENGTH);

			final String fileName = MessageFormat.format("{0}.{1}", randomName, extension); //$NON-NLS-1$
			final File tempFile = new File(directory, fileName);

			if (tempFile.exists() == false) {
				return tempFile.getAbsolutePath();
			}
		}
	}

}
