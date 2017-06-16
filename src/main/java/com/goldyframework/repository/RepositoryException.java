/**
 * @author 2017. 6. 15. 오후 9:56:38 jeong
 */
package com.goldyframework.repository;

/**
 * @author 2017. 6. 15. 오후 9:56:38 jeong
 */
public class RepositoryException extends Exception {
	
	enum RepositoryError {
		NOT_FOUND_FILE
	}
	
	/**
	 * {@link RepositoryException} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author 2017. 6. 15. 오후 10:04:33 jeong
	 * @param cause
	 */
	public RepositoryException(final Exception cause) {
		super(cause);
	}
	
	/**
	 * {@link RepositoryException} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author 2017. 6. 15. 오후 10:02:43 jeong
	 * @param message
	 */
	public RepositoryException(final String message) {
		super(message);
	}
	
}
