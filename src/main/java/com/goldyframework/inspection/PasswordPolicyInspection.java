/**
 * FileName : {@link PasswordPolicyInspection}.java
 * Created : 2017. 4. 10.
 * Author : jeong
 * Summary :
 * Copyright (C) 2018 Formal Works Inc. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.inspection;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.goldyframework.inspection.exception.InspectionException;
import com.google.common.collect.Iterables;

/**
 * 비밀번호 검사도구
 *
 * @author 2017. 6. 18. 오전 11:43:57 jeong
 */
public class PasswordPolicyInspection implements Inspection<String> {

	private static final int minLength = 8;

	private static final int maxLength = 30;

	/**
	 * slf4j Logger
	 *
	 * @author jeong
	 * @since 2017. 5. 22. 오후 9:20:02
	 */
	@SuppressWarnings("unused")
	private static final Logger LOGGER = LoggerFactory.getLogger(PasswordPolicyInspection.class);

	private static final Collection<Collection<Character>> KEYBOARD = new LinkedList<>();

	private static final Map<Character, Character> KEYBOARD_CASE_MAP = new HashMap<>();
	static {
		settingKeyboard();
		settingKeyboardCaseMap();
	}

	private static Collection<Character> findKeyboardLine(final Collection<Collection<Character>> keyboard,
		final Character first) {

		Collection<Character> targetKeyboardLine = null;

		for (final Collection<Character> keyboardLine : keyboard) {
			if (keyboardLine.contains(first)) {
				targetKeyboardLine = keyboardLine;
				break;
			}
		}
		return targetKeyboardLine;
	}

	public static int getMaxLength() {

		return maxLength;
	}

	public static int getMinLength() {

		return minLength;
	}

	private static void settingKeyboard() {

		final Collection<Character> keyboardLine4 = Arrays.asList('`', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0',
			'-', '=');
		final Collection<Character> keyboardLine3 = Arrays.asList('q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p', '[',
			']', '\\');
		final Collection<Character> keyboardLine2 = Arrays.asList('a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l', ';',
			'\'');
		final Collection<Character> keyboardLine1 = Arrays.asList('z', 'x', 'c', 'v', 'b', 'n', 'm', ',', '.', '/');

		KEYBOARD.add(keyboardLine4);
		KEYBOARD.add(keyboardLine3);
		KEYBOARD.add(keyboardLine2);
		KEYBOARD.add(keyboardLine1);
	}

	private static void settingKeyboardCaseMap() {

		KEYBOARD_CASE_MAP.put('~', '`');
		KEYBOARD_CASE_MAP.put('!', '1');
		KEYBOARD_CASE_MAP.put('@', '2');
		KEYBOARD_CASE_MAP.put('#', '3');
		KEYBOARD_CASE_MAP.put('$', '4');
		KEYBOARD_CASE_MAP.put('%', '5');
		KEYBOARD_CASE_MAP.put('^', '6');
		KEYBOARD_CASE_MAP.put('&', '7');
		KEYBOARD_CASE_MAP.put('*', '8');
		KEYBOARD_CASE_MAP.put('(', '9');
		KEYBOARD_CASE_MAP.put(')', '0');
		KEYBOARD_CASE_MAP.put('_', '-');
		KEYBOARD_CASE_MAP.put('+', '=');
		KEYBOARD_CASE_MAP.put('{', '[');
		KEYBOARD_CASE_MAP.put('}', ']');
		KEYBOARD_CASE_MAP.put('|', '\\');
		KEYBOARD_CASE_MAP.put(':', ';');
		KEYBOARD_CASE_MAP.put('"', '\'');
		KEYBOARD_CASE_MAP.put('<', ',');
		KEYBOARD_CASE_MAP.put('>', '.');
		KEYBOARD_CASE_MAP.put('?', '/');
	}

	private static String toKeyboardCase(final String target) {

		final String lowerCase = target.toLowerCase();
		final StringBuilder builder = new StringBuilder();
		for (final char c : lowerCase.toCharArray()) {
			if (KEYBOARD_CASE_MAP.containsKey(c)) {
				builder.append(KEYBOARD_CASE_MAP.get(c));
			} else {
				builder.append(c);
			}
		}
		return builder.toString();
	}

	/**
	 * {@link PasswordPolicyInspection} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author jeong
	 * @since 2017. 4. 10. 오후 9:38:57
	 */
	public PasswordPolicyInspection() {
		
		super();
	}

	/**
	 * {@inheritDoc}
	 *
	 * @author 2017. 6. 29. 오후 10:28:51 jeong
	 */
	@Override
	public void check(final String target) {

		// 문자열이 null 또는 Empty 인지 검사
		StringInspection.checkBlank(target);

		// 글자 길이제한 8 이상 30이하
		StringInspection.checkLimitLength(target, minLength, maxLength);

		// 한글이 포함되어있는지 검사
		StringInspection.checkContainHangle(target);

		// // 공백이 포함되어있는지 검사
		// checkContainWhiteSpace(target); 불필요한 제한사항으로 판단하여 제거함

		// // 3회 이상 연속된 글자가 존재한지 검사
		// this.checkContinuedString(target, 3); 불필요한 제한사항으로 판단하여 제거함
		//
		// // 키보드상 연속적인키가 4회 이상 입력되었는지 검사
		// this.checkContinuedKeyboardString(target, 4); 불필요한 제한사항으로 판단하여 제거함

		// 세미클론(;)이 포함되어있는지 검사
		StringInspection.checkContinasSemiclone(target);

		// 키보드에 존재하는 특수문자가 1회이상 입력되었는지 검사
		this.needKeyboardSpecialCharacter(target);

		// 대문자가 1회이상 입력되었는지 검사
		StringInspection.needUpperString(target);

		// 소문자가 1회이상 입력되었는지 검사
		StringInspection.needLowerString(target);

		// 숫자가 1회이상 입력되었는지 검사
		StringInspection.needNumber(target);
	}

	protected void checkContinuedKeyboardString(final String target, final int limit) {

		final String keyboardcaseTarget = toKeyboardCase(target);

		for (int i = 0; i <= (keyboardcaseTarget.length() - limit); i++) {
			final List<Character> linkedList = new LinkedList<>();
			for (int j = i; j < (i + limit); j++) {
				linkedList.add(keyboardcaseTarget.charAt(j));
			}

			int riseUpCount = 1;
			int riseDownCount = 1;

			for (int j = 0; j < (linkedList.size() - 1); j++) {
				Iterables.get(linkedList, j);
				final Character first = linkedList.get(j);
				final Character next = linkedList.get(j + 1);

				final List<Character> keyboardLineOfFirst = (List<Character>) findKeyboardLine(KEYBOARD, first);
				if (keyboardLineOfFirst == null) {
					break;
				}

				final int firstIndex = keyboardLineOfFirst.indexOf(first);
				if (firstIndex > (keyboardLineOfFirst.size() - limit)) {
					break;
				}

				final Collection<Character> keyboardLineOfNext = findKeyboardLine(KEYBOARD, next);
				if (keyboardLineOfNext == null) {
					break;
				}

				final int nextIndex = keyboardLineOfFirst.indexOf(next);

				if ((firstIndex + 1) == nextIndex) {
					riseUpCount++;
				}
				if ((firstIndex - 1) == nextIndex) {
					riseDownCount++;
				}

			}
			if ((riseUpCount >= limit) || (riseDownCount >= limit)) {
				throw new InspectionException("키보드상 연속적인 키가 " + limit + "회 이상 입력될 수 없습니다."); 
			}
		}
	}

	protected void checkContinuedString(final String target, final int limit) {

		for (int i = 0; i <= (target.length() - limit); i++) {
			final List<Character> linkedList = new LinkedList<>();
			for (int j = i; j < (i + limit); j++) {
				linkedList.add(target.charAt(j));
			}

			int sameCount = 1;
			int riseUpCount = 1;
			int riseDownCount = 1;

			for (int j = 0; j < (linkedList.size() - 1); j++) {
				final Character first = linkedList.get(j);
				final Character next = linkedList.get(j + 1);

				if (first.equals(next)) {
					sameCount++;
					if ((riseUpCount != 1) || (riseDownCount != 1)) {
						break;
					}
				}

				if (new Character((char) (first + 1)).equals(next)) {
					riseUpCount++;
					if ((sameCount != 1) || (riseDownCount != 1)) {
						break;
					}
				}

				if (new Character((char) (first - 1)).equals(next)) {
					riseDownCount++;
					if ((sameCount != 1) || (riseUpCount != 1)) {
						break;
					}
				}
			}

			if (sameCount >= limit) {
				throw new InspectionException("동일한 문자가 " + limit + "회 이상 입력될 수 없습니다."); 
			}
			if ((riseUpCount >= limit) || (riseDownCount >= limit)) {
				throw new InspectionException("연속적인 문자가 " + limit + "회 이상 입력될 수 없습니다."); 
			}
		}

	}

	protected void needKeyboardSpecialCharacter(final String target) {

		final Collection<Character> specialList = Arrays.asList(
			'!', '@', '#', '$',
			'%', '^', '&', '*',
			'(', ')', '-', '_',
			'=', '+', '[', '{',
			']', '}', '\\', '|',
			':', ';', '\'', '"',
			',', '<', '.', '>',
			'/', '?');

		for (final char c : target.toCharArray()) {
			if (specialList.contains(c)) {
				return;
			}
		}
		throw new InspectionException("키보드에 존재하는 특수문자를 1개 이상 입력해야합니다."); 
	}
}
