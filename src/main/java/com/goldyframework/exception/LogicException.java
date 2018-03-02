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
	
	
	
	
	private static final long serialVersionUID = 3029504618525263582L;
	private final LogicErrorType logicErrorType;
	
	public LogicException(LogicErrorType errorType) {
		
		super("이 메세지가 보일경우 미구현 코드이거나 코드로직의 문제입니다.");
		this.logicErrorType = errorType;
	}
	
	public LogicException(LogicErrorType errorType, Throwable cause) {
		
		super("이 메세지가 보일경우 미구현 코드이거나 코드로직의 문제입니다.", cause);
		this.logicErrorType = errorType;
		
	}
	
	/**
	 * logicErrorType를 반환합니다.
	 * 
	 * @return logicErrorType
	 * @author 2018. 2. 11. 오후 8:17:11 jeong
	 * @see {@link #logicErrorType}
	 */
	public LogicErrorType getLogicErrorType() {
		
		return this.logicErrorType;
	}
	
}
