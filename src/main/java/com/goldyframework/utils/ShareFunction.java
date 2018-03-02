/**
 * FileName : {@link ShareFunction}.java
 * Created : 2017. 4. 10.
 * Author : jeong
 * Summary :
 * Copyright (C) 2018 Formal Works Inc. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.utils;

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
import com.goldyframework.inspection.ObjectInspection;

/**
 * 공유 함수 Utils
 *
 * @author 2017. 6. 18. 오후 2:41:41 jeong
 */
public class ShareFunction {
	
	/**
	 * slf4j Logger
	 *
	 * @author jeong
	 * @since 2017. 5. 22. 오후 9:20:02
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(ShareFunction.class);
	
	/**
	 * 주어진 날짜가 오늘날짜로 몇일이 남았는지 계산합니다.
	 *
	 * @author 2017. 6. 18. 오후 2:41:50 jeong
	 * @param date
	 *            확인 날짜
	 * @return 남은 날
	 */
	public static int calculateRemaingDay(Date date) {
		
		ObjectInspection.checkNull(date);
		long diff = calculateRemaingTime(date);
		int day = (int) (diff / (1000 * 60 * 60 * 24));
		return day;
	}
	
	/**
	 * 문자열로 작성된 Date가 오늘날짜 기준으로 몇일이 남았는지 계산합니다.
	 *
	 * @author 2017. 6. 18. 오후 2:43:13 jeong
	 * @param stringDate
	 *            문자열 날짜
	 * @param format
	 *            포맷
	 * @return 남은 날
	 * @throws ParseException
	 *             날짜가 포맷에 맞지 않는 경우 예외 발생
	 */
	public static int calculateRemaingDay(String stringDate, String format) throws ParseException {
		
		ObjectInspection.checkNull(stringDate);
		ObjectInspection.checkNull(format);
		
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return ShareFunction.calculateRemaingDay(sdf.parse(stringDate));
	}
	
	/**
	 * 주어진 날짜가 오늘날짜로 몇 밀리초가 남았는지 계산합니다.
	 *
	 * @author 2017. 6. 18. 오후 2:44:12 jeong
	 * @param date
	 *            확인 날짜
	 * @return 밀리초
	 */
	public static long calculateRemaingTime(Date date) {
		
		ObjectInspection.checkNull(date);
		Date currentDate = new Date();
		long diff = date.getTime() - currentDate.getTime();
		return diff;
	}
	
	/**
	 * 주어진 문자열의 개행을 HTML의 개행(<br/>
	 * )로 변경합니다.
	 *
	 * @author 2017. 6. 18. 오후 2:45:02 jeong
	 * @param data
	 *            변경 대상 문자열
	 * @return 변경 된 문자열
	 */
	public static String convertNewLineToBrTag(String data) {
		
		ObjectInspection.checkNull(data);
		return data.replaceAll("(\r\n|\n)", "<br />");
	}
	
	/**
	 * 주어진 클래스 타입에 값을 초기화합니다.
	 * (Enum, Date, 외 모든 객체 지원)
	 *
	 * @author 2017. 6. 18. 오후 2:45:34 jeong
	 * @param paramType
	 *            파라미터 타입
	 * @param value
	 *            값
	 * @return 값이 초기화 된 객체
	 * @throws ParseException
	 *             Date 작업을 수행 중 예외 발생
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Object mappingParamType(Class<?> paramType, String value) throws ParseException {
		
		ObjectInspection.checkNull(paramType);
		ObjectInspection.checkNull(value);
		if (paramType.isEnum()) {
			return Enum.valueOf((Class<Enum>) paramType, value);
		} else if (paramType.equals(Date.class)) {
			SimpleDateFormat dateFormat = new SimpleDateFormat(Prop.DATE_FORMAT);
			return dateFormat.parse(value);
		}
		
		PropertyEditor editor = PropertyEditorManager.findEditor(paramType);
		editor.setAsText(value);
		return editor.getValue();
	}
	
	/**
	 * {@link Annotation}에 할당된 값을 문자열로 출력합니다.
	 *
	 * @author 2017. 6. 18. 오후 2:48:08 jeong
	 * @param anno
	 *            출력대상 {@link Annotation}
	 * @return 결과 문자열
	 */
	public static String toString(Annotation anno) {
		
		ObjectInspection.checkNull(anno);
		StringBuilder builder = new StringBuilder();
		
		String deli = "";
		
		for (Method method : anno.annotationType().getDeclaredMethods()) {
			builder.append(deli);
			try {
				String methodName = method.getName();
				builder.append(methodName).append('=');
				
				Object value = method.invoke(anno);
				if (value.getClass().isArray()) {
					Collection<Object> list = Arrays.asList((Object[]) value);
					builder.append(list);
				} else {
					builder.append(value);
				}
				if ("".equals(deli)) {
					deli = ", ";
				}
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				LOGGER.error("Annotation을 toString()하는 중 오류가 발생하였습니다", e);
			}
		}
		return builder.toString();
	}
	
	/**
	 * {@link ShareFunction} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author jeong
	 * @since 2017. 5. 22. 오후 9:43:33
	 */
	private ShareFunction() {
		
		throw new IllegalStateException("Utility class");
	}
}
