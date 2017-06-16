package com.goldyframework.validator;

import java.util.Arrays;
import java.util.Collection;

/**
 * bool, {@link Boolean} 타입 제약
 *
 * @author 2017. 6. 14. 오후 9:07:37 jeong
 */
public class BooleanAssert {

	/**
	 * 문자열 형태의 불린타입을 파싱합니다. (대소문자 무관)
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
	public static boolean tryCast(final String stringBoolean) throws ValidateException {

		ObjectAssert.checkNull(stringBoolean);

		final Collection<String> trueList = Arrays.asList("true", "1", "t"); //$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$
		final Collection<String> falseList = Arrays.asList("false", "0", "f"); //$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$

		final String lowerCase = stringBoolean.toLowerCase();

		if (trueList.contains(lowerCase)) {
			return true;
		} else if (falseList.contains(lowerCase)) {
			return false;
		}

		throw new ValidateException("Boolean타입은 CAST는 문자열 true 또는 false만 처리합니다."); //$NON-NLS-1$
	}

	/**
	 * GoldyAssert.BooleanAssert 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author jeong
	 * @since 2017. 5. 22. 오후 9:44:34
	 */
	public BooleanAssert() {
		super();
	}
}