/**
 * FileName : {@link StringCollectionUtil}.java
 * Created : 2017. 7. 2. 오전 11:47:19
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Goldy Project. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 (주)Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * 문자열 리스트 공통 알고리즘 유틸
 *
 * @author 2017. 7. 2. 오전 11:47:19 jeong
 */
public class StringCollectionUtil {

	/**
	 * 문자열 리스트중 patternList에 일치하는 리스트만 반환합니다.
	 *
	 * @author 2017. 7. 2. 오후 1:07:59 jeong
	 * @param stringList
	 *            문자열 리스트
	 * @param patternList
	 *            패턴 (Regex) 리스트
	 * @return 결과
	 */
	public static List<String> collectMatching(final Collection<String> stringList, final List<String> patternList) {

		final List<String> result = new ArrayList<>(stringList);

		final List<String> matchList = new ArrayList<>();

		patternList.stream().forEach(pattern -> {
			final Collection<String> match = collectMatching(stringList, pattern);
			matchList.addAll(match);
		});

		result.retainAll(matchList);
		return result;
	}

	/**
	 * 문자열 리스트중 patternList에 일치하는 리스트만 반환합니다.
	 *
	 * @author 2017. 7. 2. 오후 1:07:59 jeong
	 * @param stringList
	 *            문자열 리스트
	 * @param patternString
	 *            패턴 (Regex)
	 * @return 결과
	 */
	public static List<String> collectMatching(final Collection<String> stringList, final String patternString) {

		final Pattern pattern = Pattern.compile(patternString);

		return stringList.stream()
			.filter(string -> pattern.matcher(string).matches())
			.collect(Collectors.toList());
	}

	/**
	 * 각 문자열의 뒤에 공통으로 문자를 덧붙입니다.
	 * 예를들어 매개변수가 {"example1","example2"}리스트와 ".txt"가 있다면 {"example1.txt","example2.txt"}와 같이 변형합니다.
	 *
	 * @author 2017. 7. 2. 오후 1:02:44 jeong
	 * @param stringList
	 *            문자열 리스트
	 * @param append
	 *            뒤에 덧붙는 값
	 * @return 결과
	 */
	public static List<String> eachAppend(final Collection<String> stringList, final String append) {

		return stringList.stream()
			.map(str -> str + append)
			.collect(Collectors.toList());
	}

	/**
	 * 각 문자열의 앞에 공통으로 문자를 덧붙입니다.
	 * 예를들어 매개변수가 {"example1","example2"}리스트와 "table."가 있다면 {"table.example1","table.example2"}와 같이 변형합니다.
	 *
	 * @author 2017. 7. 2. 오후 1:02:44 jeong
	 * @param stringList
	 *            문자열 리스트
	 * @param prepend
	 *            앞에 덧붙는 값
	 * @return 결과
	 */
	public static List<String> eachPrepend(final Collection<String> stringList, final String prepend) {

		return stringList.stream()
			.map(str -> prepend + str)
			.collect(Collectors.toList());
	}

	/**
	 * 문자열 리스트를 delimiter로 구분하여 하나의 문자열로 반환합니다.
	 * 예를들어 매개변수가 {"example1","example2"}리스트와 ", "가 있다면 "example1, example2"와 같이 변형합니다.
	 *
	 * @author 2017. 7. 2. 오후 1:06:08 jeong
	 * @param stringList
	 *            문자열 리스트
	 * @param delimiter
	 *            구분자
	 * @return 결과
	 */
	public static String join(final Collection<String> stringList, final String delimiter) {

		return stringList.stream()
			.collect(Collectors.joining(delimiter));
	}

	/**
	 * 문자열 리스트중 patternList에 일치하는 목록은 제거합니다.
	 *
	 * @author 2017. 7. 2. 오후 1:07:59 jeong
	 * @param stringList
	 *            문자열 리스트
	 * @param patternList
	 *            패턴 (Regex)
	 * @return 결과
	 */
	public static List<String> removeMatching(final Collection<String> stringList, final List<String> patternList) {

		List<String> temp = new ArrayList<>(stringList);
		for (final String pattern : patternList) {
			temp = removeMatching(temp, pattern);
		}
		return temp;
	}

	/**
	 * 문자열 리스트중 patternString에 일치하는 목록은 제거합니다.
	 *
	 * @author 2017. 7. 2. 오후 1:07:59 jeong
	 * @param stringList
	 *            문자열 리스트
	 * @param patternString
	 *            패턴 (Regex)
	 * @return 결과
	 */
	public static List<String> removeMatching(final Collection<String> stringList, final String patternString) {

		final Pattern pattern = Pattern.compile(patternString);

		return stringList.stream()
			.filter(string -> pattern.matcher(string).matches() == false)
			.collect(Collectors.toList());
	}

	private StringCollectionUtil() {
		throw new IllegalStateException("Utility class"); //$NON-NLS-1$
	}

}
