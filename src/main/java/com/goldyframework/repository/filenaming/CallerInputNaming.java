/**
 * FileName : {@link CallerInputNaming}.java
 * Created : 2017. 4. 10.
 * Author : jeong
 * Summary :
 * Copyright (C) 2018 Formal Works Inc. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.repository.filenaming;

import java.text.MessageFormat;

import com.goldyframework.inspection.ObjectInspection;

/**
 * 호출자가 이름을 직접 정의합니다.<br>
 * 중복 파일이 존재 할 경우 덮어씌웁니다.
 */
class CallerInputNaming implements FileNaming {
	
	/**
	 * {@link CallerInputNaming} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author jeong
	 * @since 2017. 4. 10. 오후 9:33:12
	 */
	public CallerInputNaming() {
		
		super();
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * @author 2017. 6. 18. 오후 1:36:03 jeong
	 */
	@Override
	public String generageSavePath(final String directory, final String baseName, final String extension) {
		
		ObjectInspection.checkNull(directory);
		ObjectInspection.checkNull(baseName);
		ObjectInspection.checkNull(extension);
		return MessageFormat.format("{0}{1}.{2}", directory, baseName, extension); 
	}
	
}
