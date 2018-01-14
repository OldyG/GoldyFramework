/**
 * FileName : {@link ValidatorGtil}.java
 * Created : 2017. 4. 10.
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Goldy Project. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.annotaion;

import java.lang.reflect.Field;

import javax.validation.ConstraintValidatorContext;

/**
 * ConstraintValidator 공통작업 Util입니다.
 *
 * @author jeonghyun.kum
 * @since 2016. 4. 25. 오후 4:23:08
 */
public class ValidatorGtil {

	/**
	 * 애러임을 추가합니다.
	 *
	 * @author jeonghyun.kum
	 * @since 2016. 4. 25. 오후 4:24:21
	 * @param field
	 *            애러가 발생한 필드
	 * @param context
	 *            해당 ConstraintValidator
	 */
	@SuppressWarnings("deprecation")
	public static void addValidationError(final ConstraintValidatorContext context, final String field) {

		context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate()).addNode(field)
				.addConstraintViolation();
	}

	@SuppressWarnings("deprecation")
	public static void addValidationError(final ConstraintValidatorContext context, final String field,
			final String message) {

		context.buildConstraintViolationWithTemplate(message).addNode(field).addConstraintViolation();

	}

	/**
	 * 필드의 값을 가져옵니다.
	 *
	 * @author jeonghyun.kum
	 * @since 2016. 4. 25. 오후 4:24:52
	 * @param object
	 *            가져오려는 필드
	 * @param fieldName
	 *            필드의 이름
	 * @return 값
	 * @throws NoSuchFieldException
	 *             filedName과 일치한 필드를 찾을수 없을 때 발생합니다.
	 * @throws IllegalAccessException
	 *             접근이 불가능할때 발생합니다.
	 */
	public static <T> T getFieldValue(final Object object, final String fieldName, final Class<T> clazz)
			throws NoSuchFieldException, IllegalAccessException {

		final Field f = object.getClass().getDeclaredField(fieldName);
		f.setAccessible(true);
		return (T) f.get(object);
	}

	/**
	 * {@link ValidatorGtil} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author jeong
	 * @since 2017. 4. 10. 오후 9:28:16
	 */
	public ValidatorGtil() {

		super();
	}
}
