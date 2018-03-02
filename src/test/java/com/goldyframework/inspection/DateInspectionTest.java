/**
 * FileName : {@link DateInspectionTest}.java
 * Created : 2018. 2. 10. 오후 10:59:06
 * Author : jeong
 * Summary :
 * Copyright (C) 2018 Formal Works Inc. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 (주)포멀웍스에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.inspection;

import java.util.Calendar;
import java.util.Date;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.goldyframework.inspection.exception.InspectionException;

public class DateInspectionTest {
	
	@Rule
	public ExpectedException ee = ExpectedException.none();
	
	/**
	 * Test method for {@link com.goldyframework.inspection.DateInspection#checkFuture(java.util.Date)}.
	 */
	@Test
	public void testCheckFuture1() {
		
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DATE, 1); // 하루 증가
		
		DateInspection.checkFuture(calendar.getTime());
	}
	
	@Test
	public void testCheckFuture2() {
		
		this.ee.expect(InspectionException.class);
		this.ee.expectMessage("과거날짜를 입력 할 수 없습니다.");
		
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DATE, -1); // 하루 감소
		
		DateInspection.checkFuture(calendar.getTime());
	}
	
	@Test
	public void testCheckFuture3() {
		
		this.ee.expect(InspectionException.class);
		this.ee.expectMessage("과거날짜를 입력 할 수 없습니다.");
		DateInspection.checkFuture(new Date());
	}
	
	/**
	 * Test method for {@link com.goldyframework.inspection.DateInspection#checkPast(java.util.Date)}.
	 */
	@Test
	public void testCheckPast1() {
		
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DATE, -1); // 하루 증가
		
		DateInspection.checkPast(calendar.getTime());
	}
	
	@Test
	public void testCheckPast2() {
		
		this.ee.expect(InspectionException.class);
		this.ee.expectMessage("과거 날짜로 입력셔야합니다.");
		
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DATE, 1); // 하루 감소
		
		DateInspection.checkPast(calendar.getTime());
	}
	
	@Test
	public void testCheckPast3() {
		
		this.ee.expect(InspectionException.class);
		this.ee.expectMessage("과거 날짜로 입력셔야합니다.");
		DateInspection.checkPast(new Date());
	}
	
}
