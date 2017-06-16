package com.goldyframework.validator;

import java.util.Date;

/**
 * {@link Date} 타입 제약
 *
 * @author 2017. 6. 14. 오후 9:08:49 jeong
 */
public class DateAssert {

	/**
	 * 날짜가 과거 날짜인지 검사합니다.
	 *
	 * @author 2017. 6. 14. 오후 9:38:00 jeong
	 * @param date
	 *            검사대상 날짜
	 * @throws ValidateException
	 *             날짜가 과거날짜일 경우 발생합니다.
	 */
	public static void checkFuture(final Date date) throws ValidateException {

		ObjectAssert.checkNull(date);

		if ((date.getTime() > new Date().getTime()) == false) {
			throw new ValidateException("과거날짜를 입력 할 수 없습니다."); //$NON-NLS-1$
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
	public static void checkPast(final Date date) throws ValidateException {

		ObjectAssert.checkNull(date);

		if ((date.getTime() < new Date().getTime()) == false) {
			throw new ValidateException("과거 날짜로 입력셔야합니다."); //$NON-NLS-1$
		}
	}

	/**
	 * GoldyAssert.DateAssert 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author jeong
	 * @since 2017. 5. 22. 오후 9:44:43
	 */
	public DateAssert() {
		super();
	}
}