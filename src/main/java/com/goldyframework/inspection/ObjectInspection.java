/**
 * FileName : {@link ObjectAssert}.java
 * Created : 2017. 4. 10.
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Goldy Project. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.inspection;

import java.util.Collection;

import com.goldyframework.inspection.exception.InspectionException;

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
			throw new InspectionException("Null이 들어올수 없습니다."); //$NON-NLS-1$
		}
	}
	
	/**
	 * 다음 컬렉션이 null 또는 빈 컬렉션인지 확인합니다.
	 *
	 * @author 2017. 6. 14. 오후 10:09:10 jeong
	 * @param targetList
	 *            검사대상 리스트
	 * @throws ValidateException
	 *             null 또는 사이즈가 0일경우 발생합니다.
	 */
	public static void checkNullOrEmptyCollection(final Collection<?> targetList) {
		
		checkNull(targetList);
		
		if (targetList.isEmpty()) {
			throw new InspectionException("리스트 사이즈가 0이 될 수 없습니다."); //$NON-NLS-1$
		}
		
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
			throw new InspectionException(validClass.getSimpleName() + "는 cast가 불가능합니다."); //$NON-NLS-1$
		}
		
		if (validClass.isInstance(obj) == false) {
			throw new InspectionException(validClass.getSimpleName() + " 형식이 아닙니다."); //$NON-NLS-1$
		}
		
		return validClass.cast(obj);
	}
	
	/**
	 * {@link ObjectAssert} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author jeong
	 * @since 2017. 5. 22. 오후 9:44:14
	 */
	private ObjectInspection() {
		throw new IllegalStateException("Utility class"); //$NON-NLS-1$
	}
}
