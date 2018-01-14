/**
 * FileName : {@link IntegerSetCacheTest}.java
 * Created : 2018. 1. 13. 오후 8:19:06
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Goldy Project. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 (주)Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.db.cache;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author 2018. 1. 13. 오후 8:19:06 jeong
 */
public class IntegerSetCacheTest {

	@Test
	public void testAdd() {

		final IntegerSetCache target = new IntegerSetCache("3;1;2;");
		target.add(3);

		Assert.assertEquals(target.size(), 3);
		Assert.assertTrue(target.contains(1));
		Assert.assertTrue(target.contains(2));
		Assert.assertTrue(target.contains(3));
	}

	@Test
	public void testAdd2() {

		final IntegerSetCache target = new IntegerSetCache("3;1;2;");
		target.add(List.of(1, 2, 4));

		Assert.assertEquals(target.size(), 4);
		Assert.assertTrue(target.contains(1));
		Assert.assertTrue(target.contains(2));
		Assert.assertTrue(target.contains(3));
		Assert.assertTrue(target.contains(4));

	}

	/**
	 * Test method for {@link com.goldyframework.db.cache.IntegerSetCache#toCacheString()}.
	 */
	@Test
	public void testToCacheString() {

		final IntegerSetCache target = new IntegerSetCache("3;1;2;");

		final String cacheString = target.toCacheString();
		Assert.assertEquals(cacheString, "1;2;3;");

	}

	/**
	 * Test method for {@link com.goldyframework.db.cache.IntegerSetCache#toList()}.
	 */
	@Test
	public void testToList() {

		final IntegerSetCache target = new IntegerSetCache("3;1;2;");

		Assert.assertTrue(target.contains(1));
		Assert.assertTrue(target.contains(2));
		Assert.assertTrue(target.contains(3));
	}

}
