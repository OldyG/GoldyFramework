/**
 * FileName : {@link UpdatePrepareTest}.java
 * Created : 2017. 7. 7. 오후 7:08:57
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Goldy Project. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 (주)Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.db.prepare.statement.update;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.goldyframework.db.prepare.statement.AssignBuilder;
import com.goldyframework.db.prepare.statement.Comparison;
import com.goldyframework.db.prepare.statement.WhereBuilder;
import com.goldyframework.db.prepare.statement.update.UpdatePrepare;

/**
 * @author 2017. 7. 7. 오후 7:08:57 jeong
 */
@SuppressWarnings("nls")
public class UpdatePrepareTest {
	
	@Test
	public void test() {
		
		final AssignBuilder assign = new AssignBuilder("TEST");
		assign.appendIfNotNull("name", "test");
		assign.appendIfNotNull("name2", "test2");
		
		final WhereBuilder where = new WhereBuilder("TEST");
		where.append("column1", Comparison.GREATER_EQUAL, 1);
		where.append("column2", Comparison.EQUAL, "true");

		final UpdatePrepare update = new UpdatePrepare("TEST", assign, where);
		
		Assert.assertEquals("UPDATE Sql 비교 시험",
			"UPDATE TEST SET TEST.name = ?, TEST.name2 = ? WHERE TEST.column1 >= ?, TEST.column2 = ?",
			update.toPrepareSql());
		
		final List<Object> args = new ArrayList<>(update.getArgs());
		Assert.assertEquals("", 4, args.size());
		Assert.assertEquals("", "test", args.get(0));
		Assert.assertEquals("", "test2", args.get(1));
		Assert.assertEquals("", 1, args.get(2));
		Assert.assertEquals("", "true", args.get(3));
		
	}
	
}
