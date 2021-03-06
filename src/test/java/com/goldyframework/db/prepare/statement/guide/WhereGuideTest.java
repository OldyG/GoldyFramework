/**
 * FileName : {@link WhereGuideTest}.java
 * Created : 2017. 7. 2. 오후 6:07:02
 * Author : jeong
 * Summary :
 * Copyright (C) 2018 Formal Works Inc. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 (주)포멀웍스에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.db.prepare.statement.guide;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import com.goldyframework.annotaion.UnitTest;
import com.goldyframework.db.prepare.statement.guide.WhereGuide.ComparisonValue;

@SuppressWarnings("nls")
public class WhereGuideTest extends Mockito {
	
	/**
	 * Test method for {@link com.goldyframework.db.prepare.statement.guide.WhereGuide#append(java.lang.String, int)}.
	 */
	@Test
	public void testAppend() {
		
		final WhereGuide target = new WhereGuide("TEST");
		target.append("ColumnName1", Comparison.EQUAL, "123");
		target.append("ColumnName2", Comparison.EQUAL, 11_455);
		
		final Map<String, ComparisonValue> whereMap = target.getCopiedWhereMap();
		Assert.assertEquals("", "123", whereMap.get("ColumnName1").getValue());
		Assert.assertEquals("", Comparison.EQUAL, whereMap.get("ColumnName1").getComparison());
		Assert.assertEquals("", 11_455, whereMap.get("ColumnName2").getValue());
		Assert.assertEquals("", Comparison.EQUAL, whereMap.get("ColumnName2").getComparison());
	}
	
	/**
	 * Test method for {@link com.goldyframework.db.prepare.statement.guide.WhereGuide#toSql()}.
	 */
	@Test
	public void testBuild() {
		
		final WhereGuide target = new WhereGuide("TEST");
		
		final String actualEmpty = target.toSql();
		Assert.assertTrue("", actualEmpty.isEmpty());
		
		target.append("ColumnName2", Comparison.EQUAL, 11_455);
		target.append("ColumnName1", Comparison.EQUAL, "123");
		
		final String actual2 = target.toSql();
		Assert.assertFalse("", actual2.isEmpty());
		
		final Object next = target.getArgs().iterator().next();
		// 순서대로 정의되는것이 아니기 때문에, 첫번째 엔트리가 무엇이냐에 따라 결과가 다름
		if (next.equals(11_455)) {
			Assert.assertEquals("", "TEST.ColumnName2 = ? AND TEST.ColumnName1 = ?", actual2);
		} else if (next.equals("123")) {
			Assert.assertEquals("", "TEST.ColumnName1 = ? AND TEST.ColumnName2 = ?", actual2);
		} else {
			Assert.fail("");
		}
	}
	
	/**
	 * Test method for {@link com.goldyframework.db.prepare.statement.guide.WhereGuide#eachAppendComparisonValue()}.
	 */
	@Test
	@UnitTest
	public void testEachAppendComparisonValue() {
		
		final WhereGuide target = spy(new WhereGuide("TEST"));
		
		// 내부 변수
		final Map<String, ComparisonValue> copiedWhereMap = spy(new ConcurrentHashMap<>());
		copiedWhereMap.put("column1", target.new ComparisonValue(Comparison.EQUAL, true));
		copiedWhereMap.put("column2", target.new ComparisonValue(Comparison.LESS_EQUAL, 3));
		copiedWhereMap.put("column3", target.new ComparisonValue(Comparison.NOT_EQUAL, "abc"));
		
		// 액션
		doReturn(copiedWhereMap).when(target).getCopiedWhereMap();
		
		// 실행
		final List<String> actual = target.eachAppendComparisonValue();
		
		// 검사
		Assert.assertTrue("", actual.contains("column1 = ?"));
		Assert.assertTrue("", actual.contains("column2 <= ?"));
		Assert.assertTrue("", actual.contains("column3 != ?"));
	}
	
	/**
	 * Test method for {@link com.goldyframework.db.prepare.statement.guide.WhereGuide#getArgs()}.
	 */
	@Test
	public void testGetArgs() {
		
		final WhereGuide target = new WhereGuide("TEST");
		
		target.append("ColumnName2", Comparison.EQUAL, 11_455);
		target.append("ColumnName1", Comparison.EQUAL, "123");
		
		final Collection<Object> args = target.getArgs();
		Assert.assertEquals("", 2, args.size());
		Assert.assertTrue("", args.contains(11_455));
		Assert.assertTrue("", args.contains("123"));
	}
	
}
