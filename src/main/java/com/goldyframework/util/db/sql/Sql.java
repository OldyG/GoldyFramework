/**
 * FileName : Sql.java
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

import com.goldyframework.validator.ObjectAssert;
import com.goldyframework.validator.ValidateException;

public abstract class Sql {

	protected static String collectionToCommaString(final Collection<String> list) throws SQLException {
		try {
			ObjectAssert.checkNullOrEmptyCollection(list);
		} catch (final ValidateException e) {
			throw new SQLException(e.getMessage());
		}
		final StringBuilder builder = new StringBuilder();

		String prefix = ""; //$NON-NLS-1$
		for (final String data : list) {
			builder.append(prefix);
			prefix = ", "; //$NON-NLS-1$
			builder.append(data);
		}

		return builder.toString();
	}

	protected boolean useSemicolon = true;

	public abstract String toSql();

	public void unuseSemicolon() {
		this.useSemicolon = false;
	}

}
