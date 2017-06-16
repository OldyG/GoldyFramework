/**
 * FileName : DeleteSql.java
 * Created : 2017. 4. 10.
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Goldy Project. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.util.db.sql;

import java.sql.SQLException;

import com.goldyframework.validator.StringAssert;
import com.goldyframework.validator.ValidateException;

public class DeleteSql extends Sql {

	private static final String DELETE = "DELETE "; //$NON-NLS-1$
	private static final String FROM = "FROM "; //$NON-NLS-1$
	private static final String WHERE = "WHERE "; //$NON-NLS-1$

	private final String from;

	private final String where;

	/**
	 * DeleteSql 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author jeong
	 * @since 2017. 4. 10. 오후 9:30:20
	 * @param from
	 * @param where
	 * @throws SQLException
	 */
	public DeleteSql(final String from, final String where) throws SQLException {
		try {
			StringAssert.checkNullOrEmptyString(from);
			StringAssert.checkNullOrEmptyString(where);
		} catch (final ValidateException e) {
			throw new SQLException(e.getMessage());
		}

		this.from = from;
		this.where = where;
	}

	@Override
	public String toSql() {

		final StringBuilder builder = new StringBuilder();
		builder.append(DeleteSql.DELETE);
		builder.append(DeleteSql.FROM);
		builder.append(this.from);
		builder.append(' ');
		builder.append(DeleteSql.WHERE);
		builder.append(this.where);
		if (this.useSemicolon) {
			builder.append(';');
		}

		return builder.toString();
	}

}
