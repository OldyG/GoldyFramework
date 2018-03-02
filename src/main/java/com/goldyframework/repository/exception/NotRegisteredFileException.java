/**
 * FileName : {@link NotRegisteredFileException}.java
 * Created : 2017. 4. 10.
 * Author : jeong
 * Summary :
 * Copyright (C) 2018 Formal Works Inc. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.repository.exception;

/**
 * 데이터베이스에 경로가 저장되지 않았을 때 발생하는 예외입니다.
 * 이 클래스는 Handler 단에서 처리가 되어야합니다.
 *
 * @author jeong
 * @date 2016. 5. 19.
 */
public class NotRegisteredFileException extends Exception {
	
	
	private static final long serialVersionUID = -1912617014161373805L;

	/**
	 * {@link NotRegisteredFileException} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author jeong
	 * @since 2017. 5. 22. 오후 9:34:46
	 */
	public NotRegisteredFileException() {
		
		super();
	}
	
}
