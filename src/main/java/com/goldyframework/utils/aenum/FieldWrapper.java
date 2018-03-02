/**
 * FileName : {@link FieldWrapper}.java
 * Created : 2018. 2. 25. 오전 12:18:38
 * Author : jeong
 * Summary :
 * Copyright (C) 2018 Formal Works Inc. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.utils.aenum;

import java.lang.reflect.Field;

/**
 * Field 클래스는 final class 이므로 래퍼 클래스를 생성함
 */
public class FieldWrapper {
	
	private final Field field;
	
	/**
	 * {@link FieldWrapper} 클래스의 새 인스턴스를 초기화 합니다.
	 * 
	 * @author 2018. 2. 25. 오전 12:19:24 jeong
	 * @param field
	 */
	public FieldWrapper(Field field) {
		
		super();
		this.field = field;
	}
	
	public void disableAccessible() {
		
		this.field.setAccessible(false);
	}
	
	public boolean enableAccessible() {
		
		boolean changedAccessible = false;
		
		if (this.field.isAccessible() == false) {
			changedAccessible = true;
			this.field.setAccessible(true);
		}
		return changedAccessible;
	}
	
	public Object get(Object constant) throws IllegalArgumentException, IllegalAccessException {
		
		return this.field.get(constant);
	}
	
	/**
	 * field를 반환합니다.
	 * 
	 * @return field
	 * @author 2018. 2. 25. 오전 12:19:31 jeong
	 * @see {@link #field}
	 */
	public Field getField() {
		
		return this.field;
	}
	
	public String getName() {
		
		return this.field.getName();
	}
	
	public Object getType() {
		
		return this.field.getType();
	}
	
}
