/**
 * FileName : {@link DuplicateRecordException}.java
 * Created : 2017. 8. 14. 오후 5:58:40
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Goldy Project. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 (주)Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.db.exception;

/**
 * @author 2017. 8. 14. 오후 5:58:40 jeong
 */
public class DuplicateRecordException extends Exception {
	
	/**
	 * Constructs a new exception with the specified detail message and
	 * cause.
	 * <p>
	 * Note that the detail message associated with
	 * {@code cause} is <i>not</i> automatically incorporated in
	 * this exception's detail message.
	 *
	 * @param message
	 *            the detail message (which is saved for later retrieval
	 *            by the {@link #getMessage()} method).
	 * @param cause
	 *            the cause (which is saved for later retrieval by the
	 *            {@link #getCause()} method). (A <tt>null</tt> value is
	 *            permitted, and indicates that the cause is nonexistent or
	 *            unknown.)
	 * @since 1.4
	 */
	public DuplicateRecordException(final String message, final Throwable cause) {
		
		super(message, cause);
	}
}
