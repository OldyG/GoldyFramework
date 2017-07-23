/**
 * FileName : {@link SelectSql}.java
 * Created : 2017. 4. 10.
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Goldy Project. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.db.sql;

import java.sql.SQLException;
import java.util.Collection;

import com.goldyframework.inspection.StringInspection;
import com.goldyframework.inspection.exception.InspectionException;

/**
 * SQL SELECT 문 문법
 *
 * @author 2017. 6. 18. 오전 10:57:09 jeong
 */
public class SelectSql extends AbstractSql {
	
	/**
	 * "SELECT " 문자열 프로퍼티
	 */
	private static final String SELECT = "SELECT "; //$NON-NLS-1$
	
	/**
	 * "FROM " 문자열 프로퍼티
	 */
	private static final String FROM = "FROM "; //$NON-NLS-1$
	
	/**
	 * "WHERE " 문자열 프로퍼티
	 */
	private static final String WHERE = "WHERE "; //$NON-NLS-1$
	
	/**
	 * select 절 (컬럼 이름 목록)
	 */
	private String selectSection = "*"; //$NON-NLS-1$
	
	/**
	 * from 절 (테이블 목록)
	 */
	private String fromSection;
	
	/**
	 * where 절 (조건)
	 */
	private String whereSection;
	
	/**
	 * where 절 사용 여부
	 */
	private boolean usingWhereStatement;
	
	/**
	 * {@link SelectSql} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author 2017. 6. 18. 오전 10:59:39 jeong
	 */
	private SelectSql() {
		
		this.selectSection = "*"; //$NON-NLS-1$
		this.usingWhereStatement = false;
	}
	
	/**
	 * {@link SelectSql} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author jeong
	 * @since 2017. 4. 10. 오후 9:30:42
	 * @param selectColumnList
	 *            select 절 (컬럼 이름 목록)
	 * @param fromTableList
	 *            from 절 (테이블 목록)
	 * @throws SQLException
	 *             문법에 오류가 있는 경우 발생합니다.
	 */
	public SelectSql(final Collection<String> selectColumnList, final Collection<String> fromTableList)
		throws SQLException {
		
		this(collectionToCommaString(selectColumnList), collectionToCommaString(fromTableList));
	}
	
	/**
	 * {@link SelectSql} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author jeong
	 * @since 2017. 4. 10. 오후 9:30:43
	 * @param selectColumnList
	 *            select 절 (컬럼 이름 목록)
	 * @param fromTableList
	 *            from 절 (테이블 목록)
	 * @param where
	 *            where 절 (조건)
	 * @throws SQLException
	 *             문법에 오류가 있는 경우 발생합니다.
	 */
	public SelectSql(final Collection<String> selectColumnList, final Collection<String> fromTableList,
		final String where)
		throws SQLException {
		
		this(collectionToCommaString(selectColumnList), collectionToCommaString(fromTableList), where);
	}
	
	/**
	 * {@link SelectSql} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author jeong
	 * @since 2017. 4. 10. 오후 9:30:46
	 * @param selectColumnList
	 *            select 절 (컬럼 이름 목록)
	 * @param fromTable
	 *            from 절 (테이블 목록)
	 * @throws SQLException
	 *             문법에 오류가 있는 경우 발생합니다.
	 */
	public SelectSql(final Collection<String> selectColumnList, final String fromTable) throws SQLException {
		
		this(collectionToCommaString(selectColumnList), fromTable);
	}
	
	/**
	 * {@link SelectSql} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author jeong
	 * @since 2017. 4. 10. 오후 9:30:47
	 * @param selectColumnList
	 *            select 절 (컬럼 이름 목록)
	 * @param fromTable
	 *            from 절 (테이블 목록)
	 * @param where
	 *            where 절 (조건)
	 * @throws SQLException
	 *             문법에 오류가 있는 경우 발생합니다.
	 */
	public SelectSql(final Collection<String> selectColumnList, final String fromTable, final String where)
		throws SQLException {
		
		this(collectionToCommaString(selectColumnList), fromTable, where);
	}
	
	/**
	 * {@link SelectSql} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author jeong
	 * @since 2017. 4. 10. 오후 9:30:48
	 * @param selectColumns
	 *            select 절 (컬럼 이름 목록)
	 * @param fromTables
	 *            from 절 (테이블 목록)
	 * @throws SQLException
	 *             문법에 오류가 있는 경우 발생합니다.
	 */
	public SelectSql(final String selectColumns, final String fromTables) throws SQLException {
		
		this();
		try {
			StringInspection.checkNullOrEmpty(selectColumns);
			StringInspection.checkNullOrEmpty(fromTables);
		} catch (final InspectionException e) {
			throw new SQLException(e.getMessage(), e);
		}
		
		this.selectSection = selectColumns;
		this.fromSection = fromTables;
	}
	
	/**
	 * {@link SelectSql} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author jeong
	 * @since 2017. 4. 10. 오후 9:30:51
	 * @param selectColumns
	 *            select 절 (컬럼 이름 목록)
	 * @param fromTables
	 *            from 절 (테이블 목록)
	 * @param where
	 *            where 절 (조건)
	 * @throws SQLException
	 *             문법에 오류가 있는 경우 발생합니다.
	 */
	public SelectSql(final String selectColumns, final String fromTables, final String where) throws SQLException {
		
		this(selectColumns, fromTables);
		try {
			StringInspection.checkNullOrEmpty(where);
		} catch (final InspectionException e) {
			throw new SQLException(e.getMessage(), e);
		}
		
		this.setWhereData(where);
	}
	
	/**
	 * where 절 사용 여부를 설정한다.
	 *
	 * @author 2017. 6. 18. 오전 11:01:49 jeong
	 * @param usingWhereStstement
	 *            설정 값
	 */
	public void setUsingWhereStatement(final boolean usingWhereStstement) {
		
		this.usingWhereStatement = usingWhereStstement;
	}
	
	/**
	 * where 절을 초기화한다.
	 *
	 * @author 2017. 6. 18. 오전 11:01:56 jeong
	 * @param whereData
	 *            where 절에 삽입될 데이터
	 */
	private void setWhereData(final String whereData) {
		
		this.whereSection = whereData;
		this.setUsingWhereStatement(true);
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * @author 2017. 6. 18. 오전 11:02:24 jeong
	 */
	@Override
	public String toSql() {
		
		final StringBuilder builder = new StringBuilder();
		builder.append(SelectSql.SELECT);
		builder.append(this.selectSection);
		builder.append(' ');
		builder.append(SelectSql.FROM);
		builder.append(this.fromSection);
		
		if (this.usingWhereStatement) {
			builder.append(' ');
			builder.append(SelectSql.WHERE);
			builder.append(this.whereSection);
		}
		
		if (this.useEndSemicolon) {
			builder.append(';');
		}
		
		return builder.toString();
	}
	
}
