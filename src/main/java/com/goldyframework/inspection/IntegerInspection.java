/**
 * FileName : {@link IntegerInspection}.java
 * Created : 2017. 6. 18. 오전 1:55:29
 * Author : jeong
 * Summary :
 * Copyright (C) 2018 Formal Works Inc. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.inspection;

import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.goldyframework.does.SonarHelper;
import com.goldyframework.inspection.exception.InspectionException;

/**
 * int, {@link Integer} 타입 제약
 *
 * @author 2017. 6. 14. 오후 9:09:09 jeong
 */
public class IntegerInspection {
	
	/**
	 * slf4j Logger
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(IntegerInspection.class);
	
	private static void checkAbove(int target, int maximumValue) {
		
		if (target >= maximumValue) {
			throw new InspectionException(maximumValue + "이상으로 초기화될수 없습니다. : " + target);
		}
		
	}
	
	public static void checkBelow(int target, int minimumValue) {
		
		if (target <= minimumValue) {
			throw new InspectionException(minimumValue + "이하로 초기화될수 없습니다. : " + target);
		}
		
	}
	
	/**
	 * 해당 정수가 0을 포함한 음수 값인지 검사합니다.
	 *
	 * @author 2017. 6. 14. 오후 9:45:26 jeong
	 * @param target
	 *            검사대상 정수
	 * @throws ValidateException
	 *             0 이하일 경우 발생합니다.
	 */
	public static void checkBelowZero(int target) {
		
		checkBelow(target, 0);
	}
	
	public static void checkUnsigned(int target) {
		
		checkBelow(target, -1);
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
	public static boolean isNumberCharacter(Character character) {
		
		ObjectInspection.checkNull(character);
		
		try {
			int parseInt = Integer.parseInt(character.toString());
			SonarHelper.unuse(parseInt);
			return true;
		} catch (NumberFormatException e) {
			String message = MessageFormat.format("Character [{0}]은 Int형으로 변환할 수 없습니다.", character);
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
	public static boolean isNumberString(String string) {
		
		StringInspection.checkBlank(string);
		
		try {
			int parseInt = Integer.parseInt(string);
			SonarHelper.unuse(parseInt);
			return true;
		} catch (NumberFormatException e) {
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
	public static int tryCast(String stringNumber) {
		
		if (isNumberString(stringNumber)) {
			return Integer.parseInt(stringNumber);
		}
		
		throw new InspectionException("Integer 형식이 아닙니다. : " + stringNumber);
	}
	
	/**
	 * {@link IntegerInspection} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author jeong
	 * @since 2017. 5. 22. 오후 9:45:02
	 */
	private IntegerInspection() {
		
		throw new IllegalStateException("Utility class");
	}
}
