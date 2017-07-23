/**
 * FileName : {@link BooleanInspection}.java
 * Created : 2017. 6. 18. 오전 1:55:29
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Goldy Project. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 (주)Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.inspection;

import java.util.Arrays;
import java.util.Collection;
import java.util.Locale;

import com.goldyframework.inspection.exception.InspectionException;

/**
 * bool, {@link Boolean} 타입 제약
 *
 * @author 2017. 6. 14. 오후 9:07:37 jeong
 */
public final class BooleanInspection {
	
	/**
	 * {@link String} 형태의 {@link Boolean}타입으로 캐스팅합니다.
	 * 대소문자는 구분하지 않으며 true 또는 false일때만 정상적인 캐스팅이 가능합니다.
	 *
	 * @author 2017. 6. 14. 오후 9:23:12 jeong
	 * @param stringBoolean
	 *            검사대상 문자 열
	 * @return 결과 {@link Boolean}
	 * @throws ValidateException
	 *             설명에 작성된 true, 1, t 등에 포함되지 않는 문자열일 경우 예외가 발생합니다.
	 */
	public static boolean tryHardCast(final String stringBoolean) {
		
		StringInspection.checkNullOrEmpty(stringBoolean);
		
		if ("true".equalsIgnoreCase(stringBoolean)) { //$NON-NLS-1$
			return true;
		} else if ("false".equalsIgnoreCase(stringBoolean)) { //$NON-NLS-1$
			return false;
		}
		
		throw new InspectionException("Boolean타입은 CAST는 문자열 true 또는 false만 처리합니다."); //$NON-NLS-1$
	}
	
	/**
	 * {@link String} 형태의 {@link Boolean}타입으로 캐스팅합니다. (대소문자 무관)
	 * true : true, 1, t
	 * false : false, 0, f
	 *
	 * @author 2017. 6. 14. 오후 9:23:12 jeong
	 * @param stringBoolean
	 *            검사대상 문자 열
	 * @return 파싱 결과
	 * @throws ValidateException
	 *             설명에 작성된 true, 1, t 등에 포함되지 않는 문자열일 경우 예외가 발생합니다.
	 */
	public static boolean trySoftCast(final String stringBoolean) {
		
		StringInspection.checkNullOrEmpty(stringBoolean);
		
		final Collection<String> trueList = Arrays.asList("true", "1", "t"); //$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$
		final Collection<String> falseList = Arrays.asList("false", "0", "f"); //$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$
		
		final String lowerCase = stringBoolean.toLowerCase(Locale.getDefault());
		
		if (trueList.contains(lowerCase)) {
			return true;
		} else if (falseList.contains(lowerCase)) {
			return false;
		}
		
		throw new InspectionException("Boolean타입은 CAST는 문자열 true 또는 false만 처리합니다."); //$NON-NLS-1$
	}
	
	/**
	 * {@link BooleanInspection} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author jeong
	 * @since 2017. 5. 22. 오후 9:44:34
	 */
	private BooleanInspection() {
		
		throw new IllegalStateException("Utility class"); //$NON-NLS-1$
	}
}
