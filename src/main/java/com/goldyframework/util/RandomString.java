/**
 * FileName : RandomString.java
 * Created : 2017. 4. 10.
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Goldy Project. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.util;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Random;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class RandomString implements IRandomString {

	/**
	 * slf4j Logger
	 *
	 * @author jeong
	 * @since 2017. 5. 22. 오후 9:20:02
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(RandomString.class);
	private static final Collection<Character> SYMBOLS;

	static {
		final Collection<Character> tempSymbols = new LinkedList<>();
		final IntConsumer consumer = index -> tempSymbols.add((char) index);
		IntStream.rangeClosed('0', '9').forEach(consumer);
		IntStream.rangeClosed('a', 'z').forEach(consumer);
		IntStream.rangeClosed('A', 'Z').forEach(consumer);
		SYMBOLS = tempSymbols;
	}

	protected static String createRandomString(final int minLength, final int range) {

		final Random random = new Random();
		int passwordLength;
		if (range <= 0) {
			passwordLength = minLength;
			LOGGER.error("range는 0또는 음수가 될수 없습니다."); //$NON-NLS-1$
		} else {
			passwordLength = random.nextInt(range) + minLength;
		}

		final StringBuilder builder = new StringBuilder();

		IntStream.range(0, passwordLength).forEach((index) -> {
			final int symbolSize = random.nextInt(RandomString.SYMBOLS.size());
			final char c = ((LinkedList<Character>) RandomString.SYMBOLS).get(symbolSize);
			builder.append(c);
		});
		return builder.toString();
	}

	/**
	 * RandomString 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author jeong
	 * @since 2017. 4. 10. 오후 9:36:09
	 */
	public RandomString() {
		super();
	}

}
