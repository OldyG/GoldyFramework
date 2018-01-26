/**
 * FileName : {@link FileNamingType}.java
 * Created : 2017. 4. 10.
 * Author : jeong
 * Summary :
 * Copyright (C) 2018 Formal Works Inc. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.repository.filenaming;

import com.goldyframework.utils.NullGtils;

/**
 * 파일 이름 생성 타입
 *
 * @author 2017. 6. 18. 오후 1:35:50 jeong
 */
public enum FileNamingType {
	
	/**
	 * 파일 이름을 밀리초 단위로 저장합니다.<br>
	 * 중복 파일이 존재할 경우 중복된 시간이 생기지 않을 때 까지 반복합니다.
	 */
	MILLISECOND(new MillisecondNaming()),
	
	/**
	 * 파일 이름을 무작위로 생성합니다.<br>
	 * 중복 파일이 존재할 경우 재생성 합니다.
	 */
	UNIQUE_RANDOM(new UniqueRandomNaming()),
	
	/**
	 * 호출자가 이름을 직접 정의합니다.<br>
	 * 중복 파일이 존재 할 경우 덮어씌웁니다.
	 */
	CALLER_INPUT(new CallerInputNaming());
	
	/**
	 * 파일 이름 생성 방법
	 */
	private final FileNaming fileNaming;
	
	/**
	 * {@link FileNamingType} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author jeong
	 * @param fileNaming
	 *            파일 이름 생성 방법
	 * @date 2016. 5. 18.
	 */
	FileNamingType(final FileNaming fileNaming) {
		
		this.fileNaming = NullGtils.throwIfNull(fileNaming);
	}
	
	/**
	 * 파일 이름 생성 방법을 반환한다.
	 *
	 * @author 2017. 6. 18. 오후 1:35:35 jeong
	 * @return 파일 이름 생성 방법
	 */
	public FileNaming getFileNaming() {
		
		return this.fileNaming;
	}
}
