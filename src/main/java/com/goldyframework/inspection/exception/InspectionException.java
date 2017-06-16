/**
 * FileName : {@link ValidateException}.java
 * Created : 2017. 4. 10.
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Goldy Project. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.inspection.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 검증 실패 예외사항
 *
 * @author 2017. 6. 14. 오후 9:11:09 jeong
 */
public class InspectionException extends RuntimeException {
	
	/**
	 * slf4j Logger
	 *
	 * @author jeong
	 * @since 2017. 5. 22. 오후 9:20:02
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(InspectionException.class);
	
	/**
	 * Serializable UID
	 *
	 * @author jeong
	 * @since 2017. 5. 22. 오후 9:20:10
	 */
	private static final long serialVersionUID = 2075811842785280153L;
	
	/**
	 * {@link ValidateException} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author jeong
	 * @since 2017. 5. 22. 오후 9:35:54
	 * @param message
	 *            오류 메세지
	 */
	public InspectionException(final String message) {
		super(message);
		LOGGER.debug(message);
	}
	
	/**
	 * {@link ValidateException} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author jeong
	 * @since 2017. 5. 22. 오후 9:35:54
	 * @param message
	 *            오류 메세지
	 * @param cause
	 *            cause
	 */
	public InspectionException(final String message, final Throwable cause) {
		super(message, cause);
		LOGGER.debug(message, cause);
	}
}
