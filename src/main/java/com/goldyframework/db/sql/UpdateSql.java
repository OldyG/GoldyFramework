/**
 * FileName : {@link UpdateSql}.java
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
 * SQL UPDATE 문 문법
 *
 * @author 2017. 6. 18. 오전 11:08:09 jeong
 */
public class UpdateSql extends AbstractSql {

	/**
	 * "UPDATE " 문자열 프로퍼티
	 */
	private static final String UPDEATE = "UPDATE "; //$NON-NLS-1$

	/**
	 * "SET " 문자열 프로퍼티
	 */
	private static final String SET = "SET "; //$NON-NLS-1$

	/**
	 * "WHERE " 문자열 프로퍼티
	 */
	private static final String WHERE = "WHERE "; //$NON-NLS-1$

	/**
	 * update 절 (컬럼 목록)
	 */
	private String updateSection = "*"; //$NON-NLS-1$

	/**
	 * set 절 (설정 값 목록)
	 */
	private final String setSection;

	/**
	 * where 절 (조건)
	 */
	private final String whereSection;

	/**
	 * {@link UpdateSql} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author 2017. 6. 18. 오전 11:11:19 jeong
	 * @param update
	 *            update 절 (컬럼 목록)
	 * @param setList
	 *            set 절 (설정 값 목록)
	 * @param where
	 *            where 절 (조건)
	 * @throws SQLException
	 *             문법에 오류가 있는 경우 발생합니다.
	 */
	public UpdateSql(final String update, final Collection<String> setList, final String where) throws SQLException {
		
		this(update, collectionToCommaString(setList), where);
	}

	/**
	 * {@link UpdateSql} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author 2017. 6. 18. 오전 11:11:24 jeong
	 * @param update
	 *            update 절 (컬럼 목록)
	 * @param set
	 *            set 절 (설정 값 목록)
	 * @param where
	 *            where 절 (조건)
	 * @throws SQLException
	 *             문법에 오류가 있는 경우 발생합니다.
	 */
	public UpdateSql(final String update, final String set, final String where) throws SQLException {

		try {
			StringInspection.checkNullOrEmpty(update);
			StringInspection.checkNullOrEmpty(set);
			StringInspection.checkNullOrEmpty(where);
		} catch (final InspectionException e) {
			throw new SQLException(e.getMessage(), e);
		}

		this.updateSection = update;
		this.setSection = set;
		this.whereSection = where;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @author 2017. 6. 18. 오전 11:11:26 jeong
	 */
	@Override
	public String toSql() {

		final StringBuilder builder = new StringBuilder();
		builder.append(UpdateSql.UPDEATE);
		builder.append(this.updateSection);
		builder.append(' ');
		builder.append(UpdateSql.SET);
		builder.append(this.setSection);
		builder.append(' ');
		builder.append(UpdateSql.WHERE);
		builder.append(this.whereSection);

		if (this.useEndSemicolon) {
			builder.append(';');
		}

		return builder.toString();
	}

}
