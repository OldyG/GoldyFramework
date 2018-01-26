/**
 * FileName : {@link LogicException}.java
 * Created : 2017. 4. 10.
 * Author : jeong
 * Summary :
 * Copyright (C) 2018 Formal Works Inc. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.exception;

public class LogicException extends RuntimeException {
	
	/**
	 * Serializable UID
	 *
	 * @author jeong
	 * @since 2017. 5. 22. 오후 9:20:10
	 */
	private static final long serialVersionUID = 8650355375231410298L;
	
	public LogicException() {
		
		super("이 메세지가 보일경우 미구현 코드이거나 코드로직의 문제입니다."); 
	}
	
	public LogicException(final Throwable cause) {
		
		super("이 메세지가 보일경우 미구현 코드이거나 코드로직의 문제입니다.", cause); 
	}
	
}
