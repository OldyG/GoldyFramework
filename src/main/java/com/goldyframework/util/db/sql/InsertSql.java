/**
 * FileName : InsertSql.java
 * Created : 2017. 4. 10.
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Goldy Project. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.util.db.sql;

import java.security.InvalidParameterException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import com.goldyframework.validator.ObjectAssert;
import com.goldyframework.validator.StringAssert;
import com.goldyframework.validator.ValidateException;

public class InsertSql extends Sql {

	private static final String INSERT_INTO = "INSERT INTO "; //$NON-NLS-1$
	private static final String VALUES = "VALUES "; //$NON-NLS-1$

	private final String table;

	private final Collection<Object> inputList;

	private Collection<String> columnList;

	/**
	 * InsertSql 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author jeong
	 * @since 2017. 4. 10. 오후 9:30:25
	 * @param table
	 * @param inputList
	 * @throws SQLException
	 */
	public InsertSql(final String table, final Collection<Object> inputList) throws SQLException {
		try {
			StringAssert.checkNullOrEmptyString(table);
			ObjectAssert.checkNullOrEmptyCollection(inputList);
		} catch (final ValidateException e) {
			throw new SQLException(e.getMessage());
		}

		this.table = table;
		this.inputList = new ArrayList<>(inputList);
	}

	/**
	 * InsertSql 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author jeong
	 * @since 2017. 4. 10. 오후 9:30:36
	 * @param table
	 * @param columnList
	 * @param inputList
	 * @throws SQLException
	 */
	public InsertSql(final String table, final Collection<String> columnList, final Collection<Object> inputList) throws SQLException {
		this(table, inputList);
		try {
			ObjectAssert.checkNullOrEmptyCollection(columnList);
			ObjectAssert.checkNullOrEmptyCollection(columnList);
		} catch (final ValidateException e) {
			throw new SQLException(e.getMessage());
		}
		this.columnList = new ArrayList<>(columnList);
	}

	private String addValues() {
		final StringBuilder builder = new StringBuilder();
		builder.append(InsertSql.VALUES);
		builder.append('(');

		String prefix = ""; //$NON-NLS-1$
		for (final Object cellData : this.inputList) {

			builder.append(prefix);
			prefix = ", "; //$NON-NLS-1$
			final boolean quotationRule = (cellData instanceof String) || (cellData instanceof Boolean)
					|| ((cellData != null) && cellData.getClass().isEnum());
			if (quotationRule) {
				builder.append('\'');
			}
			builder.append(cellData);
			if (quotationRule) {
				builder.append('\'');
			}
		}
		return builder.toString();
	}

	private StringBuilder createFullColumnTypeQuery() {
		final StringBuilder builder = new StringBuilder();
		builder.append(InsertSql.INSERT_INTO);
		builder.append(this.table);
		builder.append(' ');
		builder.append(this.addValues());

		builder.append(")"); //$NON-NLS-1$
		return builder;
	}

	private StringBuilder createSelectionColumnTypeQuery() {
		final StringBuilder builder = new StringBuilder();
		builder.append(InsertSql.INSERT_INTO);
		builder.append(this.table);
		builder.append(' ');

		builder.append('(');

		String prefix = ""; //$NON-NLS-1$
		for (final String columnName : this.columnList) {
			builder.append(prefix);
			prefix = ", "; //$NON-NLS-1$
			builder.append(columnName);
		}

		builder.append(") "); //$NON-NLS-1$

		builder.append(this.addValues());

		builder.append(")"); //$NON-NLS-1$
		return builder;
	}

	@Override
	public String toSql() {

		StringBuilder returnData;

		if ((this.columnList == null) || this.columnList.isEmpty()) {
			returnData = this.createFullColumnTypeQuery();
		} else {
			if (this.columnList.size() != this.inputList.size()) {
				throw new InvalidParameterException("컬럼 갯수와 입력데이터의 개수가 일치하지 않음"); //$NON-NLS-1$
			}
			returnData = this.createSelectionColumnTypeQuery();
		}
		if (this.useSemicolon) {
			returnData.append(";"); //$NON-NLS-1$
		}

		return returnData.toString();
	}

}
