/**
 * FileName : {@link DeletePreparePlan}.java
 * Created : 2017. 7. 8. 오후 2:00:40
 * Author : jeong
 * Summary :
 * Copyright (C) 2018 Formal Works Inc. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 (주)포멀웍스에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.db.prepare.statement.delete;

import com.goldyframework.db.prepare.statement.Comparison;
import com.goldyframework.db.prepare.statement.PreparePlan;
import com.goldyframework.db.prepare.statement.WhereBuilder;

/**
 * @author 2017. 7. 8. 오후 2:00:40 jeong
 */
public class DeletePreparePlan implements PreparePlan<DeletePrepare> {
	
	private final String tableName;
	
	private final WhereBuilder where;
	
	/**
	 * {@link DeletePreparePlan} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author 2017. 7. 8. 오후 2:01:26 jeong
	 * @param tableName
	 */
	public DeletePreparePlan(final String tableName) {
		
		super();
		this.tableName = tableName;
		this.where = new WhereBuilder(tableName);
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * @author 2017. 7. 8. 오후 2:01:12 jeong
	 */
	@Override
	public DeletePrepare build() {
		
		return new DeletePrepare(this.tableName, this.where);
	}
	
	public DeletePreparePlan where(final String columnName, final Comparison comparison, final Object value) {
		
		this.where.append(columnName, comparison, value);
		return this;
	}
	
}
