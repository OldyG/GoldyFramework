/**
 * FileName : {@link DateAssert}.java
 * Created : 2017. 6. 18. 오전 1:55:29
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Goldy Project Inc. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 (주)Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.inspection;

import java.util.Date;

import com.goldyframework.inspection.exception.InspectionException;

/**
 * {@link Date} 타입 제약
 *
 * @author 2017. 6. 14. 오후 9:08:49 jeong
 */
public final class DateInspection {
	
	/**
	 * 날짜가 과거 날짜인지 검사합니다.
	 *
	 * @author 2017. 6. 14. 오후 9:38:00 jeong
	 * @param date
	 *            검사대상 날짜
	 * @throws ValidateException
	 *             날짜가 과거날짜일 경우 발생합니다.
	 */
	public static void checkFuture(final Date date) {
		
		ObjectInspection.checkNull(date);
		
		if ((date.getTime() > new Date().getTime()) == false) {
			throw new InspectionException("과거날짜를 입력 할 수 없습니다."); //$NON-NLS-1$
		}
	}
	
	/**
	 * 날짜가 미래 날짜인지 검사합니다.
	 *
	 * @author 2017. 6. 14. 오후 9:39:57 jeong
	 * @param date
	 *            검사대상 날짜
	 * @throws ValidateException
	 *             날짜가 미래날짜일 경우 발생합니다.
	 */
	public static void checkPast(final Date date) {
		
		ObjectInspection.checkNull(date);
		
		if ((date.getTime() < new Date().getTime()) == false) {
			throw new InspectionException("과거 날짜로 입력셔야합니다."); //$NON-NLS-1$
		}
	}
	
	/**
	 * {@link DateAssert} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author jeong
	 * @since 2017. 5. 22. 오후 9:44:43
	 */
	private DateInspection() {
		throw new IllegalStateException("Utility class"); //$NON-NLS-1$
	}
}
