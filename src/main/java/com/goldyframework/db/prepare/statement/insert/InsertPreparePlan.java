/**
 * FileName : {@link InsertPreparePlan}.java
 * Created : 2017. 7. 8. 오후 2:11:21
 * Author : jeong
 * Summary :
 * Copyright (C) 2018 Formal Works Inc. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 (주)포멀웍스에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.db.prepare.statement.insert;

import com.goldyframework.db.prepare.statement.PreparePlan;
import com.goldyframework.db.prepare.statement.guide.AssignGuide;

/**
 * @author 2017. 7. 8. 오후 2:11:21 jeong
 */
public class InsertPreparePlan implements PreparePlan<InsertPrepare> {
	
	private final String tableName;
	
	private final AssignGuide assign;
	
	/**
	 * {@link InsertPreparePlan} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author 2017. 7. 8. 오후 2:11:29 jeong
	 * @param tableName
	 */
	public InsertPreparePlan(final String tableName) {
		
		super();
		this.tableName = tableName;
		this.assign = new AssignGuide(tableName);
	}
	
	/**
	 * @author 2017. 7. 8. 오후 1:51:14 jeong
	 * @param column
	 * @param value
	 * @return
	 */
	public InsertPreparePlan assign(final String column, final Object value) {
		
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
