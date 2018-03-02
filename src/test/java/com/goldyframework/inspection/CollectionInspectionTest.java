/**
 * FileName : {@link CollectionInspectionTest}.java
 * Created : 2018. 2. 10. 오후 10:03:14
 * Author : jeong
 * Summary :
 * Copyright (C) 2018 Formal Works Inc. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 (주)포멀웍스에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.inspection;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.testng.Assert;

import com.goldyframework.inspection.exception.InspectionException;

public class CollectionInspectionTest {
	
	@Rule
	public ExpectedException ee = ExpectedException.none();
	
	/**
	 * Test method for
	 * {@link com.goldyframework.inspection.CollectionInspection#checkNullOrEmptyCollection(java.util.Collection)}.
	 */
	@Test
	public void testCheckNullOrEmptyCollection1() {
		
		this.ee.expect(InspectionException.class);
		this.ee.expectMessage("변수에 Null이 할당되었습니다.");
		
		final Collection<String> targetList = null;
		CollectionInspection.checkNullOrEmptyCollection(targetList);
		Assert.fail();
	}
	
	@Test
	public void testCheckNullOrEmptyCollection2() {
		
		this.ee.expect(InspectionException.class);
		this.ee.expectMessage("리스트 사이즈가 0이 될 수 없습니다.");
		
		final Collection<String> targetList = Collections.emptyList();
		CollectionInspection.checkNullOrEmptyCollection(targetList);
		Assert.fail();
	}
	
	@Test
	public void testCheckNullOrEmptyCollection3() {
		
		final Collection<String> targetList = Arrays.asList("ANY");
		
		CollectionInspection.checkNullOrEmptyCollection(targetList);
	}
	
	/**
	 * Test method for {@link com.goldyframework.inspection.CollectionInspection#checkSize(java.util.Collection, int)}.
	 */
	@Test
	public void testCheckSize1() {
		
		CollectionInspection.checkSize(Collections.emptyList(), 0);
	}
	
	/**
	 * Test method for {@link com.goldyframework.inspection.CollectionInspection#checkSize(java.util.Collection, int)}.
	 */
	@Test
	public void testCheckSize2() {
		
		this.ee.expect(InspectionException.class);
		this.ee.expectMessage("컬렉션 사이즈는 반드시 [1]이어야합니다.");
		CollectionInspection.checkSize(Collections.emptyList(), 1);
	}
	
	/**
	 * Test method for
	 * {@link com.goldyframework.inspection.CollectionInspection#isValidSize(java.util.Collection, int)}.
	 */
	@Test
	public void testIsValidSize1() {
		
		final boolean actual = CollectionInspection.isValidSize(Collections.emptyList(), 0);
		Assert.assertTrue(actual);
	}
	
	@Test
	public void testIsValidSize2() {
		
		final boolean actual = CollectionInspection.isValidSize(Collections.emptyList(), 1);
		Assert.assertFalse(actual);
	}
	
}
