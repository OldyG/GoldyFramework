package com.goldyframework.validator;

import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * int, {@link Integer} 타입 제약
 *
 * @author 2017. 6. 14. 오후 9:09:09 jeong
 */
public class IntegerAssert {

	private static final Logger LOGGER = LoggerFactory.getLogger(IntegerAssert.class);

	/**
	 * 해당 정수가 0 또는 0이하인지 검사합니다.
	 *
	 * @author 2017. 6. 14. 오후 9:45:26 jeong
	 * @param integer
	 *            검사대상 정수
	 * @throws ValidateException
	 *             0 이하일 경우 발생합니다.
	 */
	public static void checkBelowZero(final int integer) throws ValidateException {

		if (integer <= 0) {
			throw new ValidateException("0이하로 초기화될수 없습니다."); //$NON-NLS-1$
		}
	}

	/**
	 * 다음 문자가 숫자형 문자인지 검사합니다.
	 *
	 * @author 2017. 6. 14. 오후 9:46:13 jeong
	 * @param character
	 *            검사대상 문자
	 * @return 변환이 가능한경우 true, 아닌경우 false
	 * @throws ValidateException
	 *             매개변수가 null일 경우 발생합니다.
	 */
	public static boolean isNumberCharacter(final Character character) throws ValidateException {

		ObjectAssert.checkNull(character);

		try {
			Integer.parseInt(character.toString());
			return true;
		} catch (final NumberFormatException e) {
			final String message = MessageFormat.format("Character [{0}]은 Int형으로 변환할 수 없습니다.", character); //$NON-NLS-1$
			LOGGER.trace(message, e);
			return false;
		}
	}

	/**
	 * 다음 문자열이 숫자형 문자열인지 검사합니다.
	 *
	 * @author 2017. 6. 14. 오후 9:46:13 jeong
	 * @param string
	 *            검사대상 문자열
	 * @return 변환이 가능한경우 true, 아닌경우 false
	 * @throws ValidateException
	 *             매개변수가 null일 경우 발생합니다.
	 */
	public static boolean isNumberString(final String string) throws ValidateException {

		ObjectAssert.checkNull(string);

		try {
			Integer.parseInt(string);
			return true;
		} catch (final NumberFormatException e) {
			final String message = MessageFormat.format("String [{0}]은 Int형으로 변환할 수 없습니다.", string); //$NON-NLS-1$
			LOGGER.trace(message, e);
			return false;
		}
	}

	/**
	 * 문자열을 정수로 변환합니다.
	 *
	 * @author 2017. 6. 14. 오후 9:49:31 jeong
	 * @param stringNumber
	 *            숫자형 문자열
	 * @return 정수
	 * @throws ValidateException
	 *             문자열이 정수형이 아닌경우 발생합니다.
	 */
	public static int tryCast(final String stringNumber) throws ValidateException {

		if (isNumberString(stringNumber)) {
			return Integer.parseInt(stringNumber);
		}

		throw new ValidateException("Integer 형식이 아닙니다."); //$NON-NLS-1$
	}

	/**
	 * GoldyAssert.IntegerAssert 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author jeong
	 * @since 2017. 5. 22. 오후 9:45:02
	 */
	public IntegerAssert() {
		super();
	}
}