/**
 * FileName : {@link ReflectionGtils}.java
 * Created : 2017. 8. 19. 오후 5:49:56
 * Author : jeong
 * Summary :
 * Copyright (C) 2018 Formal Works Inc. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 (주)포멀웍스에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ReflectionGtils {
	
	public static List<Class<?>> getAllSuperClass(final Class<?> clazz) {
		
		final List<Class<?>> result = new ArrayList<>();
		if ((clazz != null) && (!clazz.equals(Object.class))) {
			result.add(clazz);
			result.addAll(getAllSuperClass(clazz.getSuperclass()));
		}
		
		return result;
	}
	
	public static List<Field> getDeclaredFieldWithInherit(final Object obj) {
		
		final List<Class<?>> allSuperClass = getAllSuperClass(obj.getClass());
		
		final List<Field> fields = new ArrayList<>();
		for (final Class<?> clazz : allSuperClass) {
			final Field[] fieldArray = clazz.getDeclaredFields();
			for (final Field field : fieldArray) {
				fields.add(field);
			}
		}
		
		return fields;
	}
	
	public static Object getFieldValue(final Object obj, final Field field) {
		
		boolean changed = false;
		try {
			if (field.isAccessible() == false) {
				changed = true;
				field.setAccessible(true);
			}
			return field.get(obj);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			return e;
		} finally {
			if (changed) {
				field.setAccessible(false);
			}
		}
	}
}
