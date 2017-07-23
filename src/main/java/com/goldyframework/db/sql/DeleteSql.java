/**
 * FileName : {@link DeleteSql}.java
 * Created : 2017. 4. 10.
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Goldy Project. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.db.sql;

import java.sql.SQLException;

import com.goldyframework.inspection.StringInspection;
import com.goldyframework.inspection.exception.InspectionException;

/**
 * SQL DELETE 문 문법
 *
 * @author 2017. 6. 18. 오전 10:37:15 jeong
 */
public class DeleteSql extends AbstractSql {
	
	/**
	 * "DELETE " 문자열 프로퍼티
	 */
	private static final String DELETE = "DELETE "; //$NON-NLS-1$
	
	/**
	 * "FROM " 문자열 프로퍼티
	 */
	private static final String FROM = "FROM "; //$NON-NLS-1$
	
	/**
	 * "WHERE " 문자열 프로퍼티
	 */
	private static final String WHERE = "WHERE "; //$NON-NLS-1$
	
	/**
	 * from 절 문구
	 */
	private final String fromSection;
	
	/**
	 * where 절 문구
	 */
	private final String whereSection;
	
	/**
	 * {@link DeleteSql} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author jeong
	 * @since 2017. 4. 10. 오후 9:30:20
	 * @param from
	 *            from 절 문구
	 * @param where
	 *            where 절 문구
	 * @throws SQLException
	 *             문법에 오류가 있는 경우 발생합니다.
	 */
	public DeleteSql(final String from, final String where) throws SQLException {
		
		try {
			StringInspection.checkNullOrEmpty(from);
			StringInspection.checkNullOrEmpty(where);
		} catch (final InspectionException e) {
			throw new SQLException(e.getMessage(), e);
		}
		
		this.fromSection = from;
		this.whereSection = where;
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * @author 2017. 6. 29. 오후 10:32:51 jeong
	 */
	@Override
	public String toSql() {
		
		final StringBuilder builder = new StringBuilder();
		builder.append(DeleteSql.DELETE);
		builder.append(DeleteSql.FROM);
		builder.append(this.fromSection);
		builder.append(' ');
		builder.append(DeleteSql.WHERE);
		builder.append(this.whereSection);
		if (this.useEndSemicolon) {
			builder.append(';');
		}
		
		return builder.toString();
	}
	
}
