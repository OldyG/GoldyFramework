/**
 * @author 2017. 6. 15. 오후 9:56:38 jeong
 */
package com.goldyframework.repository;

import com.goldyframework.utils.NullGtils;

/**
 * Repository 관리 중 발생하는 예외 사항
 *
 * @author 2017. 6. 15. 오후 9:56:38 jeong
 */
public class RepositoryException extends Exception {

	/**
	 * 오류 타입
	 *
	 * @author 2017. 6. 18. 오후 1:48:17 jeong
	 */
	enum RepositoryError {
		/**
		 * 파일을 찾을 수 없어 진행할 수 없음
		 */
		NOT_FOUND_FILE
	}

	/**
	 *
	 */
	private static final long serialVersionUID = 5008339989164173078L;

	/**
	 * {@link RepositoryException} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author 2017. 6. 15. 오후 10:04:33 jeong
	 * @param cause
	 *            the cause
	 */
	public RepositoryException(final Exception cause) {
		super(NullGtils.throwIfNull(cause));
	}

	/**
	 * {@link RepositoryException} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author 2017. 6. 15. 오후 10:02:43 jeong
	 * @param message
	 *            오류 메세지
	 */
	public RepositoryException(final String message) {
		super(NullGtils.throwIfNull(message));
	}

}
