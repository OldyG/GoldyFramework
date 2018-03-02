/**
 * FileName : {@link SuspicionException}.java
 * Created : 2017. 4. 10.
 * Author : jeong
 * Summary :
 * Copyright (C) 2018 Formal Works Inc. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.exception;

/**
 * 개발자의 로직 오류나 조작된 요청을 할경우 발생하는 예외입니다.
 *
 * @author jeonghyun.kum
 * @since 2016. 4. 23. 오후 9:01:17
 */
public class SuspicionException extends RuntimeException {
	
	
	private static final long serialVersionUID = -538387501259539764L;

	/**
	 * {@link SuspicionException} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author jeonghyun.kum
	 * @since 2016. 5. 26. 오후 12:26:35
	 */
	public SuspicionException() {}
	
	/**
	 * {@link SuspicionException} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author jeonghyun.kum
	 * @since 2016. 5. 26. 오후 12:24:21
	 * @param message
	 *            오류 메세지
	 */
	public SuspicionException(String message) {
		
		super(message);
	}
	
	/**
	 * {@link SuspicionException} 클래스의 새 인스턴스를 초기화 합니다.
	 * 
	 * @author 2017. 8. 23. 오후 1:52:49 jeonghyun.kum
	 * @param message
	 *            오류 메세지
	 * @param cause
	 *            cause
	 */
	public SuspicionException(String message, Throwable cause) {
		super();
		
	}
	
	/**
	 * {@link SuspicionException} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author jeong
	 * @since 2017. 3. 19. 오후 6:09:44
	 * @param cause
	 *            cause
	 */
	public SuspicionException(Throwable cause) {
		
		super(cause);
	}
	
}
