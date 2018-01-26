/**
 * FileName : {@link Because}.java
 * Created : 2017. 6. 22. 오후 6:45:36
 * Author : jeong
 * Summary :
 * Copyright (C) 2018 Formal Works Inc. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 (주)포멀웍스에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.does;

/**
 * {@link Does} 클래스에서 사용하는 Enum으로 사용하지 않는 이유를 목록화 합니다.
 *
 * @author 2017. 6. 22. 오후 6:46:17 jeong
 */
public enum Because {
	/**
	 * 이 함수를 static으로 변경하지 않기 위해서
	 *
	 * <pre>
	 * (example)
	 * public class Example {
	 * 	void int exam(int arg){
	 * 		{@link Does#notUse}(this, {@link Because#WANT_NOT_STATIC_FUNCTION})
	 * 		return arg + 1;
	 * 	}
	 * }
	 * </pre>
	 */
	WANT_NOT_STATIC_FUNCTION("해당 함수를 can be static가 되지 않도록 함"), 
	
	/**
	 * 다음 객체를 처리하지않기 위해서<br>
	 *
	 * <pre>
	 *(example)
	 *boolean success = file.createNewFile();
	 *{@link Does#notUse}(success, {@link Because#DO_NOTHING});
	 * </pre>
	 */
	DO_NOTHING("아무행위도 하지 않음"); 
	
	private final String message;
	
	/**
	 * {@link Because} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author 2017. 6. 22. 오후 7:31:42 jeong
	 * @param message
	 *            행위
	 */
	Because(final String message) {
		
		this.message = message;
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * @author 2017. 6. 22. 오후 7:31:58 jeong
	 * @return
	 */
	public String getMessage() {
		
		return this.message;
	}
}
