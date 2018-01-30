/**
 * FileName : {@link RandomSelectGtilsTest}.java
 * Created : 2018. 1. 30. 오후 7:53:24
 * Author : jeong
 * Summary :
 * Copyright (C) 2018 Formal Works Inc. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 (주)포멀웍스에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.random;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RandomSelectGtilsTest {
	
	/**
	 * slf4j Logger
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(RandomSelectGtilsTest.class);
	
	/**
	 * Test method for {@link com.goldyframework.random.RandomSelectGtils#selectRandomObject(java.util.Collection)}.
	 */
	@Test
	public void testSelectRandomObject() {
		
		final List<String> randomList = Arrays.asList("ROLE_USER", "ROLE_ADMIN", "ROLE_DBA");
		
		final Set<String> actualList = new HashSet<>();
		int count = 0;
		while (actualList.size() < randomList.size()) {
			
			if (count >= 100) {
				Assert.fail();
			}
			count++;
			
			final String actual = RandomSelectGtils.selectRandomObject(randomList);
			actualList.add(actual);
		}
		
		LOGGER.trace(Integer.toString(count));
		Assert.assertTrue(actualList.contains("ROLE_USER"));
		Assert.assertTrue(actualList.contains("ROLE_ADMIN"));
		Assert.assertTrue(actualList.contains("ROLE_DBA"));
		
	}
	
}
