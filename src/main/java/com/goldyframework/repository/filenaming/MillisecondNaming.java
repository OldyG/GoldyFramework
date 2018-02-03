/**
 * FileName : {@link MillisecondNaming}.java
 * Created : 2017. 4. 10.
 * Author : jeong
 * Summary :
 * Copyright (C) 2018 Formal Works Inc. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.repository.filenaming;

import java.io.File;
import java.text.MessageFormat;
import java.util.Date;

import com.goldyframework.inspection.ObjectInspection;

/**
 * 파일 이름을 밀리초 단위로 저장합니다.<br>
 * 중복 파일이 존재할 경우 중복된 시간이 생기지 않을 때 까지 반복합니다.
 */
class MillisecondNaming implements FileNaming {
	
	/**
	 * {@link MillisecondNaming} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author jeong
	 * @since 2017. 4. 10. 오후 9:33:19
	 */
	public MillisecondNaming() {
		
		super();
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * @author 2017. 6. 18. 오후 1:36:08 jeong
	 */
	@Override
	public File generageSavePath(final File directory, final String baseName, final String extension) {
		
		ObjectInspection.checkNull(directory);
		ObjectInspection.checkNull(baseName);
		ObjectInspection.checkNull(extension);
		while (true) {
			final String millisecond = Long.toString(new Date().getTime());
			
			final String fileName = MessageFormat.format("{0}-{1}.{2}", baseName, millisecond, extension);
			final File tempFile = new File(directory, fileName);
			
			if (tempFile.exists() == false) {
				return tempFile;
			}
		}
	}
	
}
