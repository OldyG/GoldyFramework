/**
 * FileName : {@link InsertPrepareBuilder}.java
 * Created : 2017. 7. 8. 오후 2:11:21
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Goldy Project. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 (주)Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.db.prepare.statement.insert;

import com.goldyframework.db.prepare.statement.AssignBuilder;
import com.goldyframework.db.prepare.statement.IPrepareBuilder;

/**
 * @author 2017. 7. 8. 오후 2:11:21 jeong
 */
public class InsertPrepareBuilder implements IPrepareBuilder<InsertPrepare> {

	private final String tableName;

	private final AssignBuilder assign;

	/**
	 * {@link InsertPrepareBuilder} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author 2017. 7. 8. 오후 2:11:29 jeong
	 * @param tableName
	 */
	public InsertPrepareBuilder(final String tableName) {
		super();
		this.tableName = tableName;
		this.assign = new AssignBuilder(tableName);
	}

	/**
	 * @author 2017. 7. 8. 오후 1:51:14 jeong
	 * @param column
	 * @param value
	 * @return
	 */
	public InsertPrepareBuilder assign(final String column, final Object value) {

		this.assign.appendIfNotNull(column, value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @author 2017. 7. 8. 오후 2:12:05 jeong
	 */
	@Override
	public InsertPrepare build() {

		return new InsertPrepare(this.tableName, this.assign);
	}

}
