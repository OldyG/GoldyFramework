/**
 * FileName : {@link DeletePrepareTest}.java
 * Created : 2017. 7. 2. 오후 9:12:22
 * Author : jeong
 * Summary :
 * Copyright (C) 2018 Formal Works Inc. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.db.prepare.statement.delete;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import com.goldyframework.annotaion.UnitTest;
import com.goldyframework.db.prepare.statement.delete.DeletePrepare;
import com.goldyframework.db.prepare.statement.guide.Comparison;
import com.goldyframework.db.prepare.statement.guide.WhereGuide;

@SuppressWarnings("nls")
public class DeletePrepareTest extends Mockito {
	
	@Test
	public void testToPrepareSql() {
		
		WhereGuide whereBuilder = new WhereGuide("TEST");
		whereBuilder.append("user_key", Comparison.EQUAL, "1");
		whereBuilder.append("enable_login", Comparison.EQUAL, true);
		
		DeletePrepare target = new DeletePrepare("TEST", whereBuilder);
		
		String actual = target.toPrepareSql();
		
		String expected = "DELETE FROM `TEST` WHERE `TEST`.`user_key` = ? AND `TEST`.`enable_login` = ?";
		Assert.assertEquals("", expected, actual);
	}
	
	/**
	 * Test method for {@link com.goldyframework.db.prepare.statement.delete.DeletePrepare#toPrepareSql()}.
	 */
	@Test
	@UnitTest
	public void testUnitToPrepareSql() {
		
		WhereGuide whereBuilder = mock(WhereGuide.class, RETURNS_DEFAULTS);
		DeletePrepare target = new DeletePrepare("TEST", whereBuilder);
		
		// 액션
		doReturn("abc").when(whereBuilder).toSql();
		
		String actual = target.toPrepareSql();
		
		Assert.assertEquals("", "DELETE FROM `TEST` WHERE abc", actual);
	}
	
}
