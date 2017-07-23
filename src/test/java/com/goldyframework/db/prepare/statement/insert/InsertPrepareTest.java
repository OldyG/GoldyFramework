/**
 * FileName : {@link InsertPrepareTest}.java
 * Created : 2017. 7. 2. 오후 8:21:22
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Goldy Project. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 (주)Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.db.prepare.statement.insert;

import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;

import com.goldyframework.db.prepare.statement.AssignBuilder;

@SuppressWarnings("nls")
public class InsertPrepareTest {
	
	/**
	 * Test method for {@link com.goldyframework.db.prepare.statement.insert.InsertPrepare#getArgs()}.
	 */
	@Test
	public void testGetArgs() {
		
		final AssignBuilder assign = new AssignBuilder("TEST");
		assign.appendIfNotNull("any1", 3);
		assign.appendIfNotNull("any2", "1");
		assign.appendIfNotNull("any3", 1.4F);
		final InsertPrepare target = new InsertPrepare("TEST", assign);
		
		final Collection<Object> actual2 = target.getArgs();
		
		Assert.assertEquals("", 3, actual2.size());
		Assert.assertTrue("", actual2.contains(3));
		Assert.assertTrue("", actual2.contains("1"));
		Assert.assertTrue("", actual2.contains(1.4F));
	}
	
	/**
	 * Test method for {@link com.goldyframework.db.prepare.statement.insert.InsertPrepare#toPrepareSql()}.
	 */
	@Test
	public void testToPrepareSql() {
		
		final AssignBuilder assign = new AssignBuilder("TEST");
		assign.appendIfNotNull("A", 1);
		assign.appendIfNotNull("B", "2");
		assign.appendIfNotNull("C", 3);
		
		final InsertPrepare target = new InsertPrepare("TEST", assign);
		
		final String prepareSql = target.toPrepareSql();
		
		System.out.println(prepareSql);
		
	}
	
}
