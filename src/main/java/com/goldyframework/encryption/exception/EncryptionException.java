/**
 * FileName : {@link EncryptionException}.java
 * Created : 2017. 6. 19. 오후 9:40:41
 * Author : jeong
 * Summary :
 * Copyright (C) 2018 Formal Works Inc. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 (주)포멀웍스에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.encryption.exception;

import com.goldyframework.utils.NullGtils;

/**
 * 암호화 복호화 중 예외 발생
 *
 * @author 2017. 6. 19. 오후 9:40:41 jeong
 */
public class EncryptionException extends Exception {
	
	private static final long serialVersionUID = -6224567991282560573L;
	
	/**
	 * {@link Encryption} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author 2017. 6. 19. 오후 9:40:41 jeong
	 * @param cause
	 */
	public EncryptionException(final Throwable cause) {
		
		super(NullGtils.throwIfNull(cause));
	}
	
}
