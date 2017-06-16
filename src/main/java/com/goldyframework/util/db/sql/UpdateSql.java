/**
 * FileName : UpdateSql.java
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

public class UpdateSql extends Sql {

	private static final String UPDEATE = "UPDATE "; //$NON-NLS-1$
	private static final String SET = "SET "; //$NON-NLS-1$
	private static final String WHERE = "WHERE "; //$NON-NLS-1$

	private String update = "*"; //$NON-NLS-1$

	private final String set;

	private final String where;

	public UpdateSql(final String update, final Collection<String> setList, final String where) throws SQLException {
		this(update, Sql.collectionToCommaString(setList), where);
	}

	public UpdateSql(final String update, final String set, final String where) throws SQLException {

		try {
			StringAssert.checkNullOrEmptyString(update);
			StringAssert.checkNullOrEmptyString(set);
			StringAssert.checkNullOrEmptyString(where);
		} catch (final ValidateException e) {
			throw new SQLException(e.getMessage());
		}

		this.update = update;
		this.set = set;
		this.where = where;
	}

	@Override
	public String toSql() {

		final StringBuilder builder = new StringBuilder();
		builder.append(UpdateSql.UPDEATE);
		builder.append(this.update);
		builder.append(' ');
		builder.append(UpdateSql.SET);
		builder.append(this.set);
		builder.append(' ');
		builder.append(UpdateSql.WHERE);
		builder.append(this.where);

		if (this.useSemicolon) {
			builder.append(';');
		}

		return builder.toString();
	}

}
