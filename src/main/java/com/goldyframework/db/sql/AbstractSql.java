/**
 * FileName : {@link AbstractSql}.java
 * Created : 2017. 6. 18. 오전 11:03:27
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Goldy Project. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 (주)Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.db.sql;

import java.sql.SQLException;
import java.util.Collection;

import com.goldyframework.inspection.ObjectInspection;
import com.goldyframework.inspection.exception.InspectionException;

/**
 * SQL 공용 기능 구현함수
 *
 * @author 2017. 6. 18. 오전 11:03:27 jeong
 */
abstract class AbstractSql implements Sql {

	/**
	 * 문자열 리스트를 쉼표로 구분한 문자열로 변환합니다.
	 *
	 * @author 2017. 6. 18. 오전 11:05:16 jeong
	 * @param list
	 *            변환 대상 문자열
	 * @return 쉼표로 구분한 문자열
	 * @throws SQLException
	 *             변환 대상 문자열 리스트가 Null 또는 사이즈가 0 일 경우 발생
	 */
	protected static String collectionToCommaString(final Collection<String> list) throws SQLException {

		try {
			ObjectInspection.checkNullOrEmptyCollection(list);
		} catch (final InspectionException e) {
			throw new SQLException(e.getMessage(), e);
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

	/**
	 * 마지막 세미콜론 포함 여부
	 */
	protected boolean useEndSemicolon = true;

	/**
	 * 마지막 세미콜론을 포함하지 않도록 설정합니다.
	 *
	 * @author 2017. 6. 18. 오전 11:07:00 jeong
	 */
	@Override
	public final void unuseSemicolon() {

		this.useEndSemicolon = false;
	}

}
