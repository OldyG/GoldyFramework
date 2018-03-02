/**
 * FileName : {@link BooleanInspectionTest}.java
 * Created : 2018. 2. 10. 오후 9:55:05
 * Author : jeong
 * Summary :
 * Copyright (C) 2018 Formal Works Inc. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 (주)포멀웍스에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.inspection;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.goldyframework.inspection.exception.InspectionException;

public class BooleanInspectionTest {
	
	@Rule
	public ExpectedException ee = ExpectedException.none();
	
	/**
	 * Test method for {@link com.goldyframework.inspection.BooleanInspection#tryHardCast(java.lang.String)}.
	 */
	@Test
	public void testTryHardCast1() {
		
		final boolean actual = BooleanInspection.tryHardCast("true");
		Assert.assertTrue(actual);
	}
	
	@Test
	public void testTryHardCast2() {
		
		final boolean actual = BooleanInspection.tryHardCast("FALSE");
		Assert.assertFalse(actual);
	}
	
	@Test
	public void testTryHardCast3() {
		
		this.ee.expect(InspectionException.class);
		this.ee.expectMessage("Boolean타입 CAST는 문자열 \"true\", \"false\"을 대소문자 구분없이 처리합니다.");
		
		BooleanInspection.tryHardCast("1");
	}
	
	/**
	 * Test method for {@link com.goldyframework.inspection.BooleanInspection#trySoftCast(java.lang.String)}.
	 */
	@Test
	public void testTrySoftCast1() {
		
		final boolean actual = BooleanInspection.trySoftCast("true");
		Assert.assertTrue(actual);
	}
	
	@Test
	public void testTrySoftCast2() {
		
		final boolean actual = BooleanInspection.trySoftCast("False");
		Assert.assertFalse(actual);
	}
	
	@Test
	public void testTrySoftCast3() {
		
		final boolean actual = BooleanInspection.trySoftCast("1");
		Assert.assertTrue(actual);
	}
	
	@Test
	public void testTrySoftCast4() {
		
		final boolean actual = BooleanInspection.trySoftCast("0");
		Assert.assertFalse(actual);
	}
	
	@Test
	public void testTrySoftCast5() {
		
		final boolean actual = BooleanInspection.trySoftCast("t");
		Assert.assertTrue(actual);
	}
	
	@Test
	public void testTrySoftCast6() {
		
		final boolean actual = BooleanInspection.trySoftCast("F");
		Assert.assertFalse(actual);
	}
	
	@Test
	public void testTrySoftCast7() {
		
		this.ee.expect(InspectionException.class);
		this.ee.expectMessage("Boolean타입 CAST는 문자열 \"true\", \"false\", \"1\", \"0\", \"t\", \"f\"만 처리합니다.");
		BooleanInspection.trySoftCast("A");
		
	}
	
}
