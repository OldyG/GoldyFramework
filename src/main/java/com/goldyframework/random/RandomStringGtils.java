/**
 * FileName : {@link RandomStringGtils}.java
 * Created : 2017. 4. 10.
 * Author : jeong
 * Summary :
 * Copyright (C) 2018 Formal Works Inc. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.random;

import java.security.SecureRandom;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Random;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 문자열 생성 가상 클래스
 *
 * @author 2017. 6. 18. 오후 1:31:56 jeong
 */
public final class RandomStringGtils {
	
	/**
	 * slf4j Logger
	 *
	 * @author jeong
	 * @since 2017. 5. 22. 오후 9:20:02
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(RandomStringGtils.class);
	
	/**
	 * 문자열 심볼 목록
	 */
	private static final Collection<Character> SYMBOLS;
	
	static {
		final Collection<Character> tempSymbols = new LinkedList<>();
		final IntConsumer consumer = index -> tempSymbols.add((char) index);
		IntStream.rangeClosed('0', '9').forEach(consumer);
		IntStream.rangeClosed('a', 'z').forEach(consumer);
		IntStream.rangeClosed('A', 'Z').forEach(consumer);
		SYMBOLS = tempSymbols;
	}
	
	/**
	 * 해당 길이에 대한 무작위 문자열을 생성한다.
	 *
	 * @author 2017. 6. 18. 오후 1:32:19 jeong
	 * @param minLength
	 *            최소 길이
	 * @param range
	 *            범위
	 * @return 결과 무작위 문자열
	 */
	public static String createRandomString(final int minLength, final int range) {
		
		final Random random = new SecureRandom();
		int passwordLength;
		if (range <= 0) {
			passwordLength = minLength;
			LOGGER.error("range는 0또는 음수가 될수 없습니다."); 
		} else {
			passwordLength = random.nextInt(range) + minLength;
		}
		
		final StringBuilder builder = new StringBuilder();
		
		IntStream.range(0, passwordLength).forEach(index -> {
			final int symbolSize = random.nextInt(RandomStringGtils.SYMBOLS.size());
			final char c = ((LinkedList<Character>) RandomStringGtils.SYMBOLS).get(symbolSize);
			builder.append(c);
		});
		return builder.toString();
	}
	
	/**
	 * {@link AbstractRandomString} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author jeong
	 * @since 2017. 4. 10. 오후 9:36:09
	 */
	private RandomStringGtils() {
		
		throw new IllegalStateException("Utility class"); 
	}
	
}
