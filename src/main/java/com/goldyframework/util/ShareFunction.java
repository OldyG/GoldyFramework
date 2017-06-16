/**
 * FileName : ShareFunction.java
 * Created : 2017. 4. 10.
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Goldy Project. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.util;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorManager;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.goldyframework.Prop;


public class ShareFunction {

	/**
	 * slf4j Logger
	 *
	 * @author jeong
	 * @since 2017. 5. 22. 오후 9:20:02
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(ShareFunction.class);

	public static int calculateRemaingDay(final Date endDate) {

		final long diff = calculateRemaingTime(endDate);
		final int day = (int) (diff / (1000 * 60 * 60 * 24));
		return day;
	}

	public static int calculateRemaingDay(final String endDate, final String format) throws ParseException {

		final SimpleDateFormat sdf = new SimpleDateFormat(format);
		return ShareFunction.calculateRemaingDay(sdf.parse(endDate));
	}

	public static long calculateRemaingTime(final Date endDate) {

		final Date currentDate = new Date();
		final long diff = endDate.getTime() - currentDate.getTime();
		return diff;
	}

	public static String convertNewLineToBrTag(final String data) {

		return data.replaceAll("(\r\n|\n)", "<br />"); //$NON-NLS-1$//$NON-NLS-2$
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Object mappingParamType(final Class<?> paramType, final String value) throws ParseException {

		if (paramType.isEnum()) {
			return Enum.valueOf((Class<Enum>) paramType, value);
		} else if (paramType.equals(Date.class)) {
			final SimpleDateFormat dateFormat = new SimpleDateFormat(Prop.DATE_FORMAT);
			return dateFormat.parse(value);
		} else {
			final PropertyEditor editor = PropertyEditorManager.findEditor(paramType);
			editor.setAsText(value);
			return editor.getValue();
		}
	}

	public static String toString(final Annotation anno) {

		final StringBuilder builder = new StringBuilder();

		String deli = ""; //$NON-NLS-1$

		for (final Method method : anno.annotationType().getDeclaredMethods()) {
			builder.append(deli);
			try {
				final String methodName = method.getName();
				builder.append(methodName).append('=');

				final Object value = method.invoke(anno);
				if (value.getClass().isArray()) {
					final Collection<Object> list = Arrays.asList((Object[]) value);
					builder.append(list);
				} else {
					builder.append(value);
				}
				if (deli.equals("")) { //$NON-NLS-1$
					deli = ", "; //$NON-NLS-1$
				}
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				LOGGER.error("Annotation을 toString()하는 중 오류가 발생하였습니다", e); //$NON-NLS-1$
			}
		}
		return builder.toString();
	}

	/**
	 * ShareFunction 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author jeong
	 * @since 2017. 5. 22. 오후 9:43:33
	 */
	public ShareFunction() {
		super();
	}
}
