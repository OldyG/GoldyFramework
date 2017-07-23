/**
 * FileName : {@link PrepareBuilderTest}.java
 * Created : 2017. 7. 8. 오후 1:42:00
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Goldy Project. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 (주)Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.db.prepare;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.goldyframework.db.prepare.statement.Comparison;
import com.goldyframework.db.prepare.statement.delete.DeletePrepare;
import com.goldyframework.db.prepare.statement.select.SelectPrepare;
import com.goldyframework.db.prepare.statement.update.UpdatePrepare;

/**
 * @author 2017. 7. 8. 오후 1:42:00 jeong
 */
@SuppressWarnings("nls")
public class PrepareBuilderTest {

	@Test
	public void testDelete() {

		final DeletePrepare delete = PrepareBuilder
			.delete("TEST_DELETE")
			.where("column1", Comparison.GREATER_EQUAL, 1)
			.where("column2", Comparison.EQUAL, "true")
			.build();

		Assert.assertEquals("", "DELETE FROM "
			+ "TEST_DELETE "
			+ "WHERE TEST_DELETE.column1 >= ? AND "
			+ "TEST_DELETE.column2 = ?",
			delete.toPrepareSql());
		final List<Object> args = new ArrayList<>(delete.getArgs());
		Assert.assertEquals("", 1, args.get(0));
		Assert.assertEquals("", "true", args.get(1));
	}

	@Test
	public void testSelect() {

		final SelectPrepare select = PrepareBuilder
			.select("TEST_SELECT")
			.column("A")
			.column("B")
			.column("C")
			.where("F", Comparison.EQUAL, "QQ")
			.where("G", Comparison.GREATER, 3)
			.build();
		Assert.assertEquals("",
			"SELECT TEST_SELECT.A, TEST_SELECT.B, TEST_SELECT.C "
				+ "FROM TEST_SELECT "
				+ "WHERE TEST_SELECT.F = ? AND TEST_SELECT.G > ?",
			select.toPrepareSql());

		System.out.println(select.toPrepareSql());
		System.out.println(select.getArgs());

		final List<Object> args = new ArrayList<>(select.getArgs());
		Assert.assertEquals("", "QQ", args.get(0));
		Assert.assertEquals("", 3, args.get(1));
	}

	/**
	 * Test method for {@link com.goldyframework.db.prepare.PrepareBuilder#PrepareBuilder()}.
	 */
	@Test
	public void testUpdate() {

		final UpdatePrepare update = PrepareBuilder
			.update("TEST_UPDATE")
			.assign("name", "test")
			.assign("name2", "test2")
			.where("column1", Comparison.GREATER_EQUAL, 1)
			.where("column2", Comparison.EQUAL, "true")
			.build();

		Assert.assertEquals("",
			"UPDATE TEST_UPDATE "
				+ "SET TEST_UPDATE.name = ?, TEST_UPDATE.name2 = ? "
				+ "WHERE TEST_UPDATE.column1 >= ? AND TEST_UPDATE.column2 = ?",
			update.toPrepareSql());
		final List<Object> args = new ArrayList<>(update.getArgs());
		Assert.assertEquals("", "test", args.get(0));
		Assert.assertEquals("", "test2", args.get(1));
		Assert.assertEquals("", 1, args.get(2));
		Assert.assertEquals("", "true", args.get(3));
	}

}
