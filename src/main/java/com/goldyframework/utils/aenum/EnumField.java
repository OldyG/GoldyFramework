/**
 * FileName : {@link EnumField}.java
 * Created : 2018. 2. 25. 오전 12:20:48
 * Author : jeong
 * Summary :
 * Copyright (C) 2018 Formal Works Inc. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.utils.aenum;

import java.lang.reflect.Field;

class EnumField<T> {
	
	private Field field;
	
	private T constant;
	
	/**
	 * {@link EnumGtils.EnumField} 클래스의 새 인스턴스를 초기화 합니다.
	 * 
	 * @author 2018. 2. 24. 오후 11:20:51 jeong
	 */
	public EnumField() {
		
		super();
	}
	
	/**
	 * constant를 반환합니다.
	 * 
	 * @return constant
	 * @author 2018. 2. 24. 오후 11:20:21 jeong
	 * @see {@link #constant}
	 */
	public T getConstant() {
		
		return this.constant;
	}
	
	/**
	 * field를 반환합니다.
	 * 
	 * @return field
	 * @author 2018. 2. 24. 오후 11:20:21 jeong
	 * @see {@link #field}
	 */
	public Field getField() {
		
		return this.field;
	}
	
	/**
	 * constant 초기화 합니다.
	 * 
	 * @param constant
	 *            초기화 값
	 * @author 2018. 2. 24. 오후 11:20:21 jeong
	 * @see {@link #constant}
	 */
	
	public void setConstant(T constant) {
		
		this.constant = constant;
	}
	
	/**
	 * field 초기화 합니다.
	 * 
	 * @param field
	 *            초기화 값
	 * @author 2018. 2. 24. 오후 11:20:21 jeong
	 * @see {@link #field}
	 */
	
	public void setField(Field field) {
		
		this.field = field;
	}
}