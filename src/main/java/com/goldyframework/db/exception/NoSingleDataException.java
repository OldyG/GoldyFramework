/**
 * FileName : {@link NoSingleDataException}.java
 * Created : 2017. 4. 10.
 * Author : jeong
 * Summary :
 * Copyright (C) 2018 Formal Works Inc. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.db.exception;

/**
 * 1개의 데이터를 추출하는 SELECT SQL문에서 0개 또는 2개 이상의 데이터를 가져왔을 때 발생하는 예외 사항
 *
 * @author 2017. 6. 18. 오후 12:40:38 jeong
 */
public class NoSingleDataException extends RuntimeException {
	
	/**
	 * Serializable UID
	 *
	 * @author jeong
	 * @since 2017. 5. 22. 오후 9:20:10
	 */
	private static final long serialVersionUID = -7155344775578898405L;
	
	/**
	 * {@link NoSingleDataException} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author jeong
	 * @since 2017. 5. 22. 오후 9:35:48
	 */
	public NoSingleDataException() {
		
		super();
	}
	
	/**
	 * {@link NoSingleDataException} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author 2017. 7. 11. 오후 11:17:06 jeong
	 * @param message
	 *            오류 메세지
	 * @param cause
	 *            the cause
	 */
	public NoSingleDataException(final String message, final Throwable cause) {
		
		super(message, cause);
	}
	
}
