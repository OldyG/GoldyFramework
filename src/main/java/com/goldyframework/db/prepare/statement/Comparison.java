/**
 * FileName : {@link Comparison}.java
 * Created : 2017. 7. 2. 오후 9:52:13
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Goldy Project. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 (주)Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.db.prepare.statement;

import com.goldyframework.utils.NullGtils;

@SuppressWarnings("nls")
public enum Comparison {
	EQUAL("="),
	NOT_EQUAL("!="),
	GREATER(">"),
	LESS("<"),
	GREATER_EQUAL(">="),
	LESS_EQUAL("<="),
	IS_NULL("IS NULL");

	private final String comparison;

	Comparison(final String comparison) {
		this.comparison = NullGtils.throwIfNull(comparison);
	}

	/**
	 * value를 반환합니다.
	 *
	 * @return comparison
	 * @author 2017. 7. 2. 오후 9:49:54 jeong
	 * @see {@link #comparison}
	 */
	public String getComparison() {

		return this.comparison;
	}

}