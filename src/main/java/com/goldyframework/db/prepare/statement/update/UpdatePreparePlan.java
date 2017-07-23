/**
 * FileName : {@link UpdatePreparePlan}.java
 * Created : 2017. 7. 8. 오후 1:41:16
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Goldy Project. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 (주)Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.db.prepare.statement.update;

import com.goldyframework.db.prepare.statement.AssignBuilder;
import com.goldyframework.db.prepare.statement.Comparison;
import com.goldyframework.db.prepare.statement.PreparePlan;
import com.goldyframework.db.prepare.statement.WhereBuilder;

/**
 * @author 2017. 7. 8. 오후 1:41:16 jeong
 */
public class UpdatePreparePlan implements PreparePlan<UpdatePrepare> {

	private final String tableName;

	private final AssignBuilder assign;

	private final WhereBuilder where;

	/**
	 * {@link UpdatePreparePlan} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author 2017. 7. 8. 오후 1:41:31 jeong
	 * @param tableName
	 */
	public UpdatePreparePlan(final String tableName) {
		
		this.tableName = tableName;
		this.assign = new AssignBuilder(tableName);
		this.where = new WhereBuilder(tableName);
	}

	/**
	 * @author 2017. 7. 8. 오후 1:51:14 jeong
	 * @param column
	 * @param value
	 * @return
	 */
	public UpdatePreparePlan assign(final String column, final Object value) {

		this.assign.appendIfNotNull(column, value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @author 2017. 7. 8. 오후 1:58:10 jeong
	 */
	@Override
	public UpdatePrepare build() {

		return new UpdatePrepare(this.tableName, this.assign, this.where);
	}

	public UpdatePreparePlan where(final String columnName, final Comparison comparison, final Object value) {

		this.where.append(columnName, comparison, value);
		return this;
	}

}
