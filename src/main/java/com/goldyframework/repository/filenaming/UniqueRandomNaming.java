/**
 * FileName : UniqueRandomNaming.java
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

import com.goldyframework.repository.RandomFileName;

class UniqueRandomNaming implements FileNaming {
	/**
	 * UniqueRandomNaming 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author jeong
	 * @since 2017. 4. 10. 오후 9:33:23
	 */
	public UniqueRandomNaming() {
		super();
	}

	@Override
	public String generageSavePath(final String directory, final String baseName,
			final String extension) {
		while (true) {
			final String randomName = new RandomFileName().generate();

			final String savePath = MessageFormat.format("{0}{1}.{2}", directory, randomName, extension); //$NON-NLS-1$
			final File tempFile = new File(savePath);

			if (tempFile.exists() == false) {
				return tempFile.getAbsolutePath();
			}
		}
	}

}
