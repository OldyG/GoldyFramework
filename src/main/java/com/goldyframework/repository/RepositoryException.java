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
	
	
	private static final long serialVersionUID = 105797788851906749L;

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
	 * {@link RepositoryException} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author 2017. 6. 15. 오후 10:04:33 jeong
	 * @param cause
	 *            the cause
	 */
	public RepositoryException(Exception cause) {
		
		super(NullGtils.throwIfNull(cause));
	}
	
	/**
	 * {@link RepositoryException} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author 2017. 6. 15. 오후 10:02:43 jeong
	 * @param message
	 *            오류 메세지
	 */
	public RepositoryException(String message) {
		
		super(NullGtils.throwIfNull(message));
	}
	
}
