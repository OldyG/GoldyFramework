/**
 * FileName : {@link SweeperException}.java
 * Created : 2017. 4. 10.
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Goldy Project. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.sweeper.exception;

/**
 * Sweeper 관련 예외사항
 *
 * @author 2017. 6. 18. 오후 1:57:40 jeong
 */
public class SweeperException extends Exception {

	/**
	 * Serializable UID
	 *
	 * @author jeong
	 * @since 2017. 5. 22. 오후 9:20:10
	 */
	private static final long serialVersionUID = 8445273325098801274L;

	/**
	 * {@link SweeperException} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author jeong
	 * @since 2016. 6. 11. 오후 5:34:45
	 * @param cause
	 *            the cause
	 */
	public SweeperException(final Exception cause) {
		super(cause);
	}

}
