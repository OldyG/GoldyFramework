package com.goldyframework.validator;

import java.text.MessageFormat;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * {@link String} 타입 제약
 *
 * @author 2017. 6. 14. 오후 9:10:59 jeong
 */
public class StringAssert {

	private static final Logger LOGGER = LoggerFactory.getLogger(StringAssert.class);

	private static final String NUMBER_REGEX = ".*[0-9].*"; //$NON-NLS-1$

	private static final String LOWER_CASE_REGEX = ".*[a-z].*"; //$NON-NLS-1$

	private static final String UPPER_CASE_REGEX = ".*[A-Z].*"; //$NON-NLS-1$

	private static final String HANGLE_REGEX = ".*[가-힣].*"; //$NON-NLS-1$

	private static final String HANGLE_REGEX2 = ".*[ㄱ-ㅎ].*"; //$NON-NLS-1$

	private static final String ENGLISH_REGEX = ".*[A-Za-z].*"; //$NON-NLS-1$

	private static final String MULTI_WHITE_SPACE_REGEX = ".*\\s{2}.*"; //$NON-NLS-1$

	private static final String SPCIAL_CHARACTER_REGEX = ".*[^0-9A-Za-z가-힇\\s].*"; //$NON-NLS-1$

	/**
	 * 문자열에 영어가 포함되었는지 확인합니다.
	 *
	 * @author 2017. 6. 14. 오후 10:12:26 jeong
	 * @param target
	 *            검사대상 문자열
	 * @throws ValidateException
	 *             영어가 포함되어있는 경우 발생합니다.
	 */
	public static void checkContainEnglish(final String target) throws ValidateException {

		ObjectAssert.checkNull(target);

		if (Pattern.matches(ENGLISH_REGEX, target) == true) {
			throw new ValidateException("영문이 포함될 수 없습니다."); //$NON-NLS-1$
		}
	}

	/**
	 * 문자열에 한글이 포함되어있는지 확인합니다.
	 *
	 * @author jeong
	 * @since 2016. 7. 2. 오후 1:56:03
	 * @param target
	 *            검사대상 문자열
	 * @throws ValidateException
	 *             한글이 포함되어있는 경우 발생합니다.
	 */
	public static void checkContainHangle(final String target) throws ValidateException {

		ObjectAssert.checkNull(target);

		if (Pattern.matches(HANGLE_REGEX, target) || Pattern.matches(HANGLE_REGEX2, target)) {
			throw new ValidateException("한글이 포함될 수 없습니다."); //$NON-NLS-1$
		}
	}

	/**
	 * 문자열에 숫자가 포함되어있는지 확인합니다.
	 *
	 * @author jeong
	 * @since 2016. 7. 2. 오전 2:54:33
	 * @param target
	 *            검사대상 문자열
	 * @throws ValidateException
	 *             숫자가 포함되어있는 경우 발생합니다.
	 */
	public static void checkContainNumber(final String target) throws ValidateException {

		ObjectAssert.checkNull(target);

		if (Pattern.matches(NUMBER_REGEX, target) == true) {
			throw new ValidateException("숫자가 포함될 수 없습니다."); //$NON-NLS-1$
		}
	}

	/**
	 * 문자열에 특수문자가 포함되어있는지 확인합니다.
	 * 한글, 영어, 띄어쓰기를 제외한 모든 문자는 특수문자로 처리합니다.
	 *
	 * @author jeong
	 * @since 2016. 7. 2. 오후 1:27:26
	 * @param target
	 *            검사대상 문자열
	 * @throws ValidateException
	 *             특수문자가 포함되어있는 경우 발생합니다.
	 */
	public static void checkContainSpecialCharacter(final String target) throws ValidateException {

		ObjectAssert.checkNull(target);

		if (Pattern.matches(SPCIAL_CHARACTER_REGEX, target)) { // $NON-NLS-1$
			throw new ValidateException("특수문자는 포함될 수 없습니다."); //$NON-NLS-1$
		}
	}

	/**
	 * 문자열에 대문자가 포함되었는지 확인합니다.
	 *
	 * @author jeong
	 * @since 2016. 7. 2. 오후 1:56:59
	 * @param target
	 *            검사대상 문자열
	 * @throws ValidateException
	 *             대문자가 포함되어있는 경우 발생합니다.
	 */
	public static void checkContainUpperString(final String target) throws ValidateException {

		ObjectAssert.checkNull(target);

		if (Pattern.matches(UPPER_CASE_REGEX, target)) {
			throw new ValidateException("대문자가 포함될 수 없습니다."); //$NON-NLS-1$
		}
	}

	/**
	 * 공백이 포함되어있는지 확인합니다.
	 *
	 * @author 2017. 6. 14. 오후 10:14:55 jeong
	 * @param target
	 *            검사대상 문자열
	 * @throws ValidateException
	 *             공백이 포함되어있는 경우 발생합니다.
	 */
	public static void checkContainWhiteSpace(final String target) throws ValidateException {

		ObjectAssert.checkNull(target);

		if (Pattern.matches(".*\\s.*", target)) { //$NON-NLS-1$
			throw new ValidateException("공백을 포함될 수 없습니다."); //$NON-NLS-1$
		}

	}

	/**
	 * 세미콜론이 포함되었는지 확인합니다.
	 *
	 * @author 2017. 6. 14. 오후 10:22:26 jeong
	 * @param target
	 *            검사대상 문자열
	 * @throws ValidateException
	 *             세미콜론이 포함되어있는 경우 발생합니다.
	 */
	public static void checkContinasSemiclone(final String target) throws ValidateException {

		ObjectAssert.checkNull(target);

		if (target.contains(";")) { //$NON-NLS-1$
			throw new ValidateException("세미클론이 포함될 수 없습니다."); //$NON-NLS-1$
		}
	}

	public static void checkLimitLength(final String target, final int min, final int max) throws ValidateException {

		ObjectAssert.checkNull(target);

		if ((min < 0) || (max <= 0)) {
			LOGGER.error("min 또는 max의 수는 0 이하로 입력 할 수 없습니다."); //$NON-NLS-1$
		}

		if (min > max) {
			LOGGER.error("min 값이 max보다 큰 수로 입력될 수 없습니다."); //$NON-NLS-1$
		}

		final int length = target.length();

		if (length < min) {
			throw new ValidateException(min + "글자 이상 작성해 주세요 현재길이:" + length); //$NON-NLS-1$
		}

		if (length > max) {
			throw new ValidateException(max + "글자 이하로 작성해 주세요 현재길이: " + length); //$NON-NLS-1$
		}
	}

	public static void checkMoreThanOneCharacter(final String target, final String sub) throws ValidateException {

		checkNullOrEmptyString(target);
		checkNullOrEmptyString(sub);

		final int count = StringUtils.countOccurrencesOf(target, sub);
		if (count > 1) {
			throw new ValidateException(sub + "는 2회 이상 사용될수 없습니다."); //$NON-NLS-1$
		}
	}

	/**
	 * 공백이 2회 이상 존재한지 확인합니다.
	 *
	 * @author jeong
	 * @since 2016. 7. 2. 오전 2:45:37
	 * @param target
	 *            검사대상 문자열
	 * @throws ValidateException
	 *             2회연속된 공백이 포함되어있는 경우 발생합니다.
	 */
	public static void checkMultiWhiteSpace(final String target) throws ValidateException {

		if (Pattern.matches(MULTI_WHITE_SPACE_REGEX, target)) { // $NON-NLS-1$
			throw new ValidateException("공백이 2회 연속 들어갈 수 없습니다."); //$NON-NLS-1$
		}
	}

	public static void checkNullOrEmptyString(final String target) throws ValidateException {

		ObjectAssert.checkNull(target);

		if (target.isEmpty() || target.trim().isEmpty()) {
			throw new ValidateException("빈값이 정의되어있습니다."); //$NON-NLS-1$
		}
	}

	public static void checkOrder(final String target, final String front, final String illegalBehind)
		throws ValidateException {

		checkNullOrEmptyString(target);
		checkNullOrEmptyString(front);
		checkNullOrEmptyString(illegalBehind);

		final int frontIndex = target.indexOf(front);

		// front가 존재하지 않으면 검사를 진행하지 않는다.
		if (frontIndex == -1) {
			return;
		}

		final int behindIndex = target.substring(frontIndex + front.length()).indexOf(illegalBehind);
		if (behindIndex != -1) {
			throw new ValidateException(MessageFormat.format("\"{0}\"뒤엔 \"{1}\"가 포함될 수 없습니다.", front, illegalBehind)); //$NON-NLS-1$
		}
	}

	/**
	 * 좌우에 공백이 존재한지 확인합니다.
	 *
	 * @author jeong
	 * @since 2016. 7. 2. 오전 2:44:10
	 * @param target
	 *            검사대상 문자열
	 * @throws ValidateException
	 *             좌우 공백이 존재하는 경우 발생합니다.
	 */
	public static void checkPossibleTrim(final String target) throws ValidateException {

		if (target.equals(target.trim()) == false) {
			throw new ValidateException("좌우에 공백이 포함될 수 없습니다"); //$NON-NLS-1$
		}

	}

	/**
	 * 한글이 포함되어있지 않은지 확인합니다.
	 *
	 * @author jeong
	 * @since 2016. 7. 2. 오후 1:57:50
	 * @param target
	 *            검사대상 문자열
	 * @throws ValidateException
	 *             한글이 포함되어있지 않은 경우 발생합니다.
	 */
	public static void needHangle(final String target) throws ValidateException {

		if (Pattern.matches(HANGLE_REGEX, target) == false) {
			throw new ValidateException("한글이 1글자 이상 포함되어야 합니다."); //$NON-NLS-1$
		}
	}

	/**
	 * 소문자가 포함되어있지 않은지 확인합니다.
	 *
	 * @author jeong
	 * @since 2016. 7. 2. 오후 1:58:03
	 * @param target
	 *            검사대상 문자열
	 * @throws ValidateException
	 *             소문자가 되어있지 않은 경우 발생합니다.
	 */
	public static void needLowerString(final String target) throws ValidateException {

		if (Pattern.matches(LOWER_CASE_REGEX, target) == false) {
			throw new ValidateException("소문자가 1글자 이상 포함되어야 합니다."); //$NON-NLS-1$
		}
	}

	/**
	 * 숫자가 포함되어있지 않은지 확인합니다.
	 *
	 * @author jeong
	 * @since 2016. 7. 2. 오후 1:58:14
	 * @param target
	 *            검사대상 문자열
	 * @throws ValidateException
	 *             숫자가 포함되어있지 않은 경우 발생합니다.
	 */
	public static void needNumber(final String target) throws ValidateException {

		if (Pattern.matches(NUMBER_REGEX, target) == false) {
			throw new ValidateException("숫자가 1글자 이상 포함되어야 합니다."); //$NON-NLS-1$
		}
	}

	public static void needNumberAtBehindCharacter(final String target, final String sub) throws ValidateException {

		final String[] split = target.split(Pattern.quote(sub));

		for (final String splitString : split) {
			final char[] charArray = splitString.toCharArray();

			if (charArray.length == 0) {
				continue;
			}

			final char firstChar = charArray[0];

			if (IntegerAssert.isNumberCharacter(firstChar) == false) {
				throw new ValidateException(sub + "뒤에는 숫자가 포함되어야 합니다."); //$NON-NLS-1$
			}
		}
	}

	public static void needNumberAtFrontCharacter(final String target, final String sub) throws ValidateException {

		final String[] split = target.split(Pattern.quote(sub));

		for (final String splitString : split) {
			final char[] charArray = splitString.toCharArray();
			final char lastChar = charArray[charArray.length - 1];
			if (IntegerAssert.isNumberCharacter(lastChar) == false) {
				throw new ValidateException(sub + "앞에는 숫자가 포함되어야 합니다."); //$NON-NLS-1$
			}
		}
	}

	public static void needOrder(final String target, final String front, final String needBehind)
		throws ValidateException {

		checkNullOrEmptyString(target);
		checkNullOrEmptyString(front);
		checkNullOrEmptyString(needBehind);

		final int frontIndex = target.indexOf(front);

		// front가 존재하지 않으면 검사를 진행하지 않는다.
		if (frontIndex == -1) {
			return;
		}

		final int behindIndex = target.substring(frontIndex + front.length()).indexOf(needBehind);
		if (behindIndex == -1) {
			throw new ValidateException(MessageFormat.format("\"{0}\"뒤엔 반드시 \"{1}\"가 존재해야합니다.", front, needBehind)); //$NON-NLS-1$
		}
	}

	/**
	 * 특수문자가 포함되어있지 않은지 확인합니다.
	 * 한글, 영어, 띄어쓰기를 제외한 모든 문자는 특수문자로 처리합니다.
	 *
	 * @author jeong
	 * @since 2016. 7. 2. 오후 1:58:35
	 * @param target
	 *            검사대상 문자열
	 * @throws ValidateException
	 *             특수문자가 포함되어있지 않을 경우 예외가 발생합니다.
	 */
	public static void needSpecialCharacter(final String target) throws ValidateException {

		if (Pattern.matches(SPCIAL_CHARACTER_REGEX, target) == false) {
			throw new ValidateException("특수문자가 1글자 이상 포함되어야 합니다."); //$NON-NLS-1$
		}
	}

	/**
	 * 대문자가 포함되어있지 않은지 확인합니다.
	 *
	 * @author jeong
	 * @since 2016. 7. 2. 오후 1:59:16
	 * @param target
	 *            검사대상 문자열
	 * @throws ValidateException
	 *             대문자가 포함되어있지 않을 경우 예외가 발생합니다.
	 */
	public static void needUpperString(final String target) throws ValidateException {

		if (Pattern.matches(UPPER_CASE_REGEX, target) == false) {
			throw new ValidateException("대문자가 1글자 이상 포함되어야 합니다."); //$NON-NLS-1$
		}
	}

	/**
	 * GoldyAssert.StringAssert 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author jeong
	 * @since 2017. 5. 22. 오후 9:45:12
	 */
	public StringAssert() {
		super();
	}
}