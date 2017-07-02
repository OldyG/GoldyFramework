/**
 * FileName : {@link StringCollectionUtilTest}.java
 * Created : 2017. 7. 2. 오후 12:06:10
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Goldy Project. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 (주)Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

@SuppressWarnings("nls")
public class StringCollectionUtilTest {
	
	private final Collection<String> appendTarget = Arrays.asList("Test1", "Test2", "Test3");
	
	/**
	 * Test method for
	 * {@link com.goldyframework.util.StringCollectionUtil#eachAppend(java.util.Collection, java.lang.String)}.
	 */
	@Test
	public void testEachAppend() {
		
		final List<String> actual = StringCollectionUtil.eachAppend(this.appendTarget, ".abc");
		
		Assert.assertEquals("", 3, actual.size());
		Assert.assertEquals("", "Test1.abc", actual.get(0));
		Assert.assertEquals("", "Test2.abc", actual.get(1));
		Assert.assertEquals("", "Test3.abc", actual.get(2));
	}
	
	/**
	 * Test method for
	 * {@link com.goldyframework.util.StringCollectionUtil#eachPrepend(java.util.Collection, java.lang.String)}.
	 */
	@Test
	public void testEachPrepend() {
		
		final List<String> actual = StringCollectionUtil.eachPrepend(this.appendTarget, "abc.");
		
		Assert.assertEquals("", 3, actual.size());
		Assert.assertEquals("", "abc.Test1", actual.get(0));
		Assert.assertEquals("", "abc.Test2", actual.get(1));
		Assert.assertEquals("", "abc.Test3", actual.get(2));
	}
	
	/**
	 * Test method for
	 * {@link com.goldyframework.util.StringCollectionUtil#join(java.util.Collection, java.lang.String)}.
	 */
	@Test
	public void testJoin() {
		
		final String actual = StringCollectionUtil.join(this.appendTarget, ", ");
		Assert.assertEquals("", "Test1, Test2, Test3", actual);
	}
	
}
