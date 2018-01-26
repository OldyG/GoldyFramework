/**
 * FileName : {@link DaoException}.java
 * Created : 2017. 7. 1. 오전 12:00:58
 * Author : jeong
 * Summary :
 * Copyright (C) 2018 Formal Works Inc. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 (주)포멀웍스에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.db.exception;

import com.goldyframework.utils.NullGtils;

/**
 * @author 2017. 7. 1. 오전 12:00:58 jeong
 */
public class DaoException extends Exception {
	
	/**
	 * Serializable UID
	 */
	private static final long serialVersionUID = 2135496068594915680L;
	
	/**
	 * Constructs a new exception with {@code null} as its detail message.
	 * The cause is not initialized, and may subsequently be initialized by a
	 * call to {@link #initCause}.
	 */
	public DaoException() {
		
		super();
	}
	
	/**
	 * Constructs a new exception with the specified detail message. The
	 * cause is not initialized, and may subsequently be initialized by
	 * a call to {@link #initCause}.
	 *
	 * @param message
	 *            the detail message. The detail message is saved for
	 *            later retrieval by the {@link #getMessage()} method.
	 */
	public DaoException(final String message) {
		
		super(NullGtils.throwIfNull(message));
	}
	
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
	public DaoException(final String message, final Throwable cause) {
		
		super(NullGtils.throwIfNull(message), NullGtils.throwIfNull(cause));
	}
	
	/**
	 * Constructs a new exception with the specified cause and a detail
	 * message of <tt>(cause==null ? null : cause.toString())</tt> (which
	 * typically contains the class and detail message of <tt>cause</tt>).
	 * This constructor is useful for exceptions that are little more than
	 * wrappers for other throwables (for example, {@link
	 * java.security.PrivilegedActionException}).
	 *
	 * @param cause
	 *            the cause (which is saved for later retrieval by the
	 *            {@link #getCause()} method). (A <tt>null</tt> value is
	 *            permitted, and indicates that the cause is nonexistent or
	 *            unknown.)
	 * @since 1.4
	 */
	public DaoException(final Throwable cause) {
		
		super(NullGtils.throwIfNull(cause));
	}
}
