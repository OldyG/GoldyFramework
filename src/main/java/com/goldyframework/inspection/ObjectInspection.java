/**
 * FileName : {@link ObjectInspection}.java
 * Created : 2017. 4. 10.
 * Author : jeong
 * Summary :
 * Copyright (C) 2018 Formal Works Inc. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.inspection;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.goldyframework.inspection.exception.InspectionException;
import com.goldyframework.utils.ReflectionGtils;
import com.goldyframework.utils.json.JsonGtils;

/**
 * {@link Object} 타입 제약
 *
 * @author 2017. 6. 14. 오후 9:10:40 jeong
 */
public final class ObjectInspection {
	
	/**
	 * 다음 객체가 null인지 확인합니다.
	 *
	 * @author 2017. 6. 14. 오후 10:08:45 jeong
	 * @param obj
	 *            검사대상 객체
	 * @throws ValidateException
	 *             null일 경우 발생합니다.
	 */
	public static void checkNull(final Object obj) {
		
		if (obj == null) {
			throw new InspectionException("변수에 Null이 할당되었습니다."); 
		}
	}
	
	public static <T> boolean equalsForFields(final T thisObj, final T targetObj) {
		
		if ((thisObj == null) || (targetObj == null)) {
			return false;
		}
		
		if (thisObj.getClass().equals(targetObj.getClass()) == false) {
			return false;
		}
		final String json1 = JsonGtils.toGson(thisObj);
		final String json2 = JsonGtils.toGson(targetObj);
		
		return json1.equals(json2);
	}
	
	/**
	 * @author 2017. 8. 19. 오후 4:32:21 jeong
	 * @param obj
	 * @return
	 */
	public static int hashCodeForFields(final Object obj) {
		
		final String gson = JsonGtils.toGson(obj);
		
		return Objects.hash(gson);
	}
	
	@Deprecated
	public static int hashCodeForFields2(final Object obj) {
		
		final List<Field> declaredFields = ReflectionGtils.getDeclaredFieldWithInherit(obj);
		
		final List<Object> fieldValues = new ArrayList<>();
		for (final Field field : declaredFields) {
			final Object fieldValue = ReflectionGtils.getFieldValue(obj, field);
			fieldValues.add(fieldValue);
		}
		
		return Objects.hash(CollectionInspection.toArray(fieldValues));
	}
	
	/**
	 * 해당 객체를 캐스팅합니다.
	 *
	 * @author 2017. 6. 14. 오후 10:10:10 jeong
	 * @param <T>
	 *            캐스팅 타입
	 * @param obj
	 *            변경대상 객체
	 * @param validClass
	 *            캐스팅 타입
	 * @return 캐스팅된 객체
	 * @throws ValidateException
	 *             원시타입이거나 캐스팅에 실패한 경우 발생합니다.
	 */
	public static <T> T tryCast(final Object obj, final Class<T> validClass) {
		
		ObjectInspection.checkNull(obj);
		ObjectInspection.checkNull(validClass);
		
		if ((validClass == Integer.class) || (validClass == Boolean.class)) {
			throw new InspectionException(validClass.getSimpleName() + "는 cast가 불가능합니다."); 
		}
		
		if (validClass.isInstance(obj) == false) {
			throw new InspectionException(validClass.getSimpleName() + " 형식이 아닙니다."); 
		}
		
		return validClass.cast(obj);
	}
	
	/**
	 * {@link ObjectInspection} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author jeong
	 * @since 2017. 5. 22. 오후 9:44:14
	 */
	private ObjectInspection() {
		
		throw new IllegalStateException("Utility class"); 
	}
}
