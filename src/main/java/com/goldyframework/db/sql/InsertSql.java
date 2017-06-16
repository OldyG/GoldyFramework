/**
 * FileName : {@link InsertSql}.java
 * Created : 2017. 4. 10.
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Goldy Project. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.db.sql;

import java.security.InvalidParameterException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import com.goldyframework.inspection.ObjectInspection;
import com.goldyframework.inspection.StringInspection;
import com.goldyframework.inspection.exception.InspectionException;

/**
 * SQL INSERT 문 문법
 *
 * @author 2017. 6. 18. 오전 10:39:58 jeong
 */
public class InsertSql extends AbstractSql {

	/**
	 * "INSERT INTO " 문자열 프로퍼티
	 */
	private static final String INSERT_INTO = "INSERT INTO "; //$NON-NLS-1$

	/**
	 * "VALUES " 문자열 프로퍼티
	 */
	private static final String VALUES = "VALUES "; //$NON-NLS-1$

	/**
	 * 해당 객체 타입의 값이 쌍따옴표를 씌어야 하는지 구분한다.
	 *
	 * @author 2017. 6. 18. 오전 10:46:49 jeong
	 * @param cellData
	 *            검사 대상 객체
	 * @return 쌍따옴표 씌움 여부
	 */
	private static boolean isQuotationRule(final Object cellData) {

		return (cellData instanceof String)
			|| (cellData instanceof Boolean)
			|| ((cellData != null) && cellData.getClass().isEnum());
	}

	/**
	 * table 절
	 */
	private final String tableSection;

	/**
	 * values 절 목록 (삽입 데이터 목록)
	 */
	private final Collection<Object> valueSectionList;

	/**
	 * insert into 절 목록 (테이블 컬럼 목록)
	 */
	private Collection<String> columnSectionList;

	/**
	 * {@link InsertSql} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author jeong
	 * @since 2017. 4. 10. 오후 9:30:25
	 * @param table
	 *            table 절
	 * @param inputList
	 *            values 절 목록 (삽입 데이터 목록)
	 * @throws SQLException
	 *             문법에 오류가 있는 경우 발생합니다.
	 */
	public InsertSql(final String table, final Collection<Object> inputList) throws SQLException {
		try {
			StringInspection.checkNullOrEmpty(table);
			ObjectInspection.checkNullOrEmptyCollection(inputList);
		} catch (final InspectionException e) {
			throw new SQLException(e.getMessage(), e);
		}

		this.tableSection = table;
		this.valueSectionList = new ArrayList<>(inputList);
	}

	/**
	 * {@link InsertSql} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author jeong
	 * @since 2017. 4. 10. 오후 9:30:36
	 * @param table
	 *            테이블 절
	 * @param columnList
	 *            insert into 절 목록 (테이블 컬럼 목록)
	 * @param inputList
	 *            values 절 목록 (삽입 데이터 목록)
	 * @throws SQLException
	 *             문법에 오류가 있는 경우 발생합니다.
	 */
	public InsertSql(final String table, final Collection<String> columnList, final Collection<Object> inputList)
		throws SQLException {
		this(table, inputList);
		try {
			ObjectInspection.checkNullOrEmptyCollection(columnList);
			ObjectInspection.checkNullOrEmptyCollection(inputList);
		} catch (final InspectionException e) {
			throw new SQLException(e.getMessage(), e);
		}
		this.columnSectionList = new ArrayList<>(columnList);
	}

	/**
	 * insert into 절 목록 (테이블 컬럼 목록)값이 설정되어있지 않다면, value값 만 작성 된 SQL문을 생성한다.
	 *
	 * @author 2017. 6. 18. 오전 10:54:50 jeong
	 * @return value값 만 작성 된 SQL문
	 */
	private StringBuilder createFullColumnTypeQuery() {

		final StringBuilder builder = new StringBuilder();
		builder.append(InsertSql.INSERT_INTO);
		builder.append(this.tableSection);
		builder.append(' ');
		builder.append(this.createValuesClause());

		builder.append(')');
		return builder;
	}

	/**
	 * insert into 절 목록 (테이블 컬럼 목록)값이 설정되어있다면, INSERT INTO 문을 포함한 SQL문을 생성한다.
	 *
	 * @author 2017. 6. 18. 오전 10:55:33 jeong
	 * @return INSERT INTO 문을 포함한 SQL문
	 */
	private StringBuilder createSelectionColumnTypeQuery() {

		final StringBuilder builder = new StringBuilder();
		builder.append(InsertSql.INSERT_INTO);
		builder.append(this.tableSection);
		builder.append(' ');

		builder.append('(');

		String prefix = ""; //$NON-NLS-1$
		for (final String columnName : this.columnSectionList) {
			builder.append(prefix);
			prefix = ", "; //$NON-NLS-1$
			builder.append(columnName);
		}

		builder.append(") "); //$NON-NLS-1$

		builder.append(this.createValuesClause());

		builder.append(')');
		return builder;
	}

	/**
	 * VALUES 절에 생성될 문자열을 완성한다.
	 *
	 * @author 2017. 6. 18. 오전 10:45:56 jeong
	 * @return VALUES 절에 생성될 문자열
	 */
	private String createValuesClause() {

		final StringBuilder builder = new StringBuilder();
		builder.append(InsertSql.VALUES);
		builder.append('(');

		String prefix = ""; //$NON-NLS-1$
		for (final Object cellData : this.valueSectionList) {

			builder.append(prefix);
			prefix = ", "; //$NON-NLS-1$
			final boolean quotationRule = InsertSql.isQuotationRule(cellData);

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

	/**
	 * {@inheritDoc}
	 *
	 * @author 2017. 6. 18. 오전 10:50:01 jeong
	 */
	@Override
	public String toSql() {

		StringBuilder returnData;

		if ((this.columnSectionList == null) || this.columnSectionList.isEmpty()) {
			returnData = this.createFullColumnTypeQuery();
		} else {
			if (this.columnSectionList.size() != this.valueSectionList.size()) {
				throw new InvalidParameterException("컬럼 갯수와 입력데이터의 개수가 일치하지 않음"); //$NON-NLS-1$
			}
			returnData = this.createSelectionColumnTypeQuery();
		}

		if (this.useEndSemicolon) {
			returnData.append(';');
		}

		return returnData.toString();
	}

}
