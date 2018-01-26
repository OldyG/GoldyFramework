/**
 * FileName : {@link EmailException}.java
 * Created : 2017. 6. 28. 오후 10:36:51
 * Author : jeong
 * Summary :
 * Copyright (C) 2018 Formal Works Inc. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 (주)포멀웍스에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.email.exception;

import com.goldyframework.utils.NullGtils;

/**
 * Email 관련 예외사항
 *
 * @author 2017. 6. 28. 오후 10:36:51 jeong
 */
public class EmailException extends Exception {
	
	/**
	 * Serializable UID
	 */
	private static final long serialVersionUID = 2635026459109314583L;
	
	/**
	 * {@link EmailException} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author 2017. 6. 28. 오후 10:36:51 jeong
	 */
	public EmailException() {
		
		super();
		
	}
	
	/**
	 * {@link EmailException} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author 2017. 6. 28. 오후 10:36:51 jeong
	 * @param message
	 *            오류 메세지
	 */
	public EmailException(final String message) {
		
		super(NullGtils.throwIfNull(message));
	}
	
	/**
	 * {@link EmailException} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author 2017. 6. 28. 오후 10:36:51 jeong
	 * @param message
	 *            오류 메세지
	 * @param cause
	 *            the cause
	 */
	public EmailException(final String message, final Throwable cause) {
		
		super(NullGtils.throwIfNull(message), NullGtils.throwIfNull(cause));
	}
	
	/**
	 * {@link EmailException} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author 2017. 6. 28. 오후 10:36:51 jeong
	 * @param cause
	 *            the cause
	 */
	public EmailException(final Throwable cause) {
		
		super(NullGtils.throwIfNull(cause));
	}
	
}
