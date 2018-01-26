/**
 * FileName : {@link CacheMapTest}.java
 * Created : 2018. 1. 13. 오후 7:59:40
 * Author : jeong
 * Summary :
 * Copyright (C) 2018 Formal Works Inc. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 (주)포멀웍스에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.db.cache;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.goldyframework.inspection.exception.InspectionException;

/**
 * @author 2018. 1. 13. 오후 7:59:40 jeong
 */
public class CacheMapTest {

	@Rule
	public ExpectedException ee = ExpectedException.none();

	/**
	 * Test method for {@link com.goldyframework.db.cache.CacheMap#defineKey(java.lang.String, java.lang.Class)}.
	 */
	@Test
	public void testDefineKey1() {

		final CacheMap cacheMap = new CacheMap();
		cacheMap.defineKey("Test", String.class);
		Assert.assertTrue(cacheMap.getDefinedKeyType().containsKey("Test"));

		cacheMap.defineKey("ABC", String.class);
		Assert.assertTrue(cacheMap.getDefinedKeyType().containsKey("ABC"));

		Assert.assertFalse(cacheMap.getDefinedKeyType().containsKey("NONE KEY"));
	}

	/**
	 * Test method for {@link com.goldyframework.db.cache.CacheMap#defineKey(java.lang.String, java.lang.Class)}.
	 */
	@Test
	public void testDefineKey2() {

		final CacheMap cacheMap = new CacheMap();
		cacheMap.defineKey("Test", String.class);
		cacheMap.defineKey("Test", String.class);

		Assert.assertTrue(cacheMap.getDefinedKeyType().containsKey("Test"));
	}

	/**
	 * Test method for {@link com.goldyframework.db.cache.CacheMap#defineKey(java.lang.String, java.lang.Class)}.
	 */
	@Test
	public void testDefineKey3() {

		this.ee.expect(InspectionException.class);
		this.ee.expectMessage("는 이미 할당되었습니다.");

		final CacheMap cacheMap = new CacheMap();
		cacheMap.defineKey("Test", String.class);
		cacheMap.defineKey("Test", Integer.class);
	}

	/**
	 * Test method for {@link com.goldyframework.db.cache.CacheMap#put(java.lang.String, java.lang.Object)}.
	 */
	@Test
	public void testPutStringObject1() {

		this.ee.expect(InspectionException.class);
		this.ee.expectMessage("할당으로 제한되어있습니다.");

		final CacheMap cacheMap = new CacheMap();
		cacheMap.defineKey("Test", String.class);

		cacheMap.put("Test", 1);
	}

	/**
	 * Test method for {@link com.goldyframework.db.cache.CacheMap#put(java.lang.String, java.lang.Object)}.
	 */
	@Test
	public void testPutStringObject2() {

		final CacheMap cacheMap = new CacheMap();
		cacheMap.defineKey("Test", String.class);

		cacheMap.put("Test", "any");

		Assert.assertEquals(cacheMap.get("Test"), "any");

	}

	/**
	 * Test method for {@link com.goldyframework.db.cache.CacheMap#put(java.lang.String, java.lang.Object)}.
	 */
	@Test
	public void testPutStringObject3() {

		final CacheMap cacheMap = new CacheMap();

		cacheMap.put("Test", "any");

		Assert.assertEquals(cacheMap.get("Test"), "any");

	}

}
