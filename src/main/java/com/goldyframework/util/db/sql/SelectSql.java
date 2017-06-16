/**
 * FileName : SelectSql.java
 * Created : 2017. 4. 10.
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Goldy Project. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.util.db.sql;

import java.sql.SQLException;
import java.util.Collection;

import com.goldyframework.validator.StringAssert;
import com.goldyframework.validator.ValidateException;

public class SelectSql extends Sql {

	private static final String SELECT = "SELECT "; //$NON-NLS-1$
	private static final String FROM = "FROM "; //$NON-NLS-1$
	private static final String WHERE = "WHERE "; //$NON-NLS-1$

	private String selectColumns = "*"; //$NON-NLS-1$

	private String fromTables;

	private String where;

	private boolean usingWhereStatement = false;

	private SelectSql() {
		this.selectColumns = "*"; //$NON-NLS-1$
		this.usingWhereStatement = false;
	}

	/**
	 * SelectSql 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author jeong
	 * @since 2017. 4. 10. 오후 9:30:42
	 * @param selectColumnList
	 * @param fromTableList
	 * @throws SQLException
	 */
	public SelectSql(final Collection<String> selectColumnList, final Collection<String> fromTableList)
			throws SQLException {
		this(Sql.collectionToCommaString(selectColumnList), Sql.collectionToCommaString(fromTableList));
	}

	/**
	 * SelectSql 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author jeong
	 * @since 2017. 4. 10. 오후 9:30:43
	 * @param selectColumnList
	 * @param fromTableList
	 * @param where
	 * @throws SQLException
	 */
	public SelectSql(final Collection<String> selectColumnList, final Collection<String> fromTableList, final String where)
			throws SQLException {
		this(Sql.collectionToCommaString(selectColumnList), Sql.collectionToCommaString(fromTableList), where);
	}

	/**
	 * SelectSql 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author jeong
	 * @since 2017. 4. 10. 오후 9:30:46
	 * @param selectColumnList
	 * @param fromTable
	 * @throws SQLException
	 */
	public SelectSql(final Collection<String> selectColumnList, final String fromTable) throws SQLException {
		this(Sql.collectionToCommaString(selectColumnList), fromTable);
	}

	/**
	 * SelectSql 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author jeong
	 * @since 2017. 4. 10. 오후 9:30:47
	 * @param selectColumnList
	 * @param fromTable
	 * @param where
	 * @throws SQLException
	 */
	public SelectSql(final Collection<String> selectColumnList, final String fromTable, final String where)
			throws SQLException {
		this(Sql.collectionToCommaString(selectColumnList), fromTable, where);
	}

	/**
	 * SelectSql 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author jeong
	 * @since 2017. 4. 10. 오후 9:30:48
	 * @param selectColumns
	 * @param fromTables
	 * @throws SQLException
	 */
	public SelectSql(final String selectColumns, final String fromTables) throws SQLException {
		this();
		try {
			StringAssert.checkNullOrEmptyString(selectColumns);
			StringAssert.checkNullOrEmptyString(fromTables);
		} catch (final ValidateException e) {
			throw new SQLException(e.getMessage());
		}

		this.selectColumns = selectColumns;
		this.fromTables = fromTables;
	}

	/**
	 * SelectSql 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author jeong
	 * @since 2017. 4. 10. 오후 9:30:51
	 * @param selectColumns
	 * @param fromTables
	 * @param where
	 * @throws SQLException
	 */
	public SelectSql(final String selectColumns, final String fromTables, final String where) throws SQLException {
		this(selectColumns, fromTables);
		try {
			StringAssert.checkNullOrEmptyString(where);
		} catch (final ValidateException e) {
			throw new SQLException(e.getMessage());
		}

		this.setWhereData(where);
	}

	public void setUsingWhereStatement(final boolean usingWhereStstement) {
		this.usingWhereStatement = usingWhereStstement;
	}

	private void setWhereData(final String whereData) {
		this.where = whereData;
		this.setUsingWhereStatement(true);
	}

	@Override
	public String toSql() {

		final StringBuilder builder = new StringBuilder();
		builder.append(SelectSql.SELECT);
		builder.append(this.selectColumns);
		builder.append(' ');
		builder.append(SelectSql.FROM);
		builder.append(this.fromTables);

		if (this.usingWhereStatement) {
			builder.append(' ');
			builder.append(SelectSql.WHERE);
			builder.append(this.where);
		}

		if (this.useSemicolon) {
			builder.append(';');
		}

		return builder.toString();
	}

}
