/**
 * FileName : {@link InsertPrepareTest}.java
 * Created : 2017. 7. 2. 오후 8:21:22
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Goldy Project. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 (주)Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.db.prepare;

import static org.junit.Assert.fail;

import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;

@SuppressWarnings("nls")
public class InsertPrepareTest {
	
	/**
	 * Test method for {@link com.goldyframework.db.prepare.InsertPrepare#getArgs()}.
	 */
	@Test
	public void testGetArgs() {
		
		final InsertPrepare target = new InsertPrepare("TEST");
		
		final Collection<Object> actual1 = target.getArgs();
		Assert.assertEquals("", 0, actual1.size());
		
		target.setValues(3, "1", 1.4F);
		final Collection<Object> actual2 = target.getArgs();
		
		Assert.assertEquals("", 3, actual2.size());
		Assert.assertTrue("", actual2.contains(3));
		Assert.assertTrue("", actual2.contains("1"));
		Assert.assertTrue("", actual2.contains(1.4F));
	}
	
	/**
	 * Test method for {@link com.goldyframework.db.prepare.InsertPrepare#setColumns(java.util.List)}.
	 */
	@Test
	public void testSetColumnsListOfString() {
		
		fail("Not yet implemented");
	}
	
	/**
	 * Test method for {@link com.goldyframework.db.prepare.InsertPrepare#setColumns(java.lang.String[])}.
	 */
	@Test
	public void testSetColumnsStringArray() {
		
		fail("Not yet implemented");
	}
	
	/**
	 * Test method for {@link com.goldyframework.db.prepare.InsertPrepare#setValues(java.util.List)}.
	 */
	@Test
	public void testSetValuesListOfObject() {
		
		fail("Not yet implemented");
	}
	
	/**
	 * Test method for {@link com.goldyframework.db.prepare.InsertPrepare#setValues(java.lang.Object[])}.
	 */
	@Test
	public void testSetValuesObjectArray() {
		
		fail("Not yet implemented");
	}
	
	/**
	 * Test method for {@link com.goldyframework.db.prepare.InsertPrepare#toPrepareSql()}.
	 */
	@Test
	public void testToPrepareSql() {
		
		final InsertPrepare target = new InsertPrepare("TEST");
		target.setColumns("A", "B", "C");
		target.setValues("1", "2", "3");
		
		final String prepareSql = target.toPrepareSql();
		
		System.out.println(prepareSql);
		
	}
	
}
