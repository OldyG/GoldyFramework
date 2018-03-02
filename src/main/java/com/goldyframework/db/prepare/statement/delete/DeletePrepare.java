/**
 * FileName : {@link DeletePrepare}.java
 * Created : 2017. 7. 2. 오후 5:19:33
 * Author : jeong
 * Summary :
 * Copyright (C) 2018 Formal Works Inc. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.db.prepare.statement.delete;

import java.text.MessageFormat;
import java.util.Collection;

import com.goldyframework.db.prepare.statement.AbstractPrepare;
import com.goldyframework.db.prepare.statement.FieldWrapper;
import com.goldyframework.db.prepare.statement.guide.WhereGuide;
import com.goldyframework.inspection.StringInspection;
import com.goldyframework.utils.NullGtils;
import com.google.common.annotations.VisibleForTesting;

public class DeletePrepare extends AbstractPrepare {
	
	private final WhereGuide where;
	
	/**
	 * {@link DeletePrepare} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author 2017. 7. 2. 오후 5:32:49 jeong
	 * @param tableName
	 * @param whereBuilder
	 */
	@VisibleForTesting
	DeletePrepare(String tableName, WhereGuide whereBuilder) {
		
		super(NullGtils.throwIfNull(tableName));
		this.where = whereBuilder;
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * @author 2017. 7. 2. 오후 5:27:25 jeong
	 */
	@Override
	public Collection<Object> getArgs() {
		
		return this.where.getArgs();
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * @author 2017. 7. 2. 오후 5:27:25 jeong
	 */
	@Override
	public String toPrepareSql() {
		
		String tableName = super.getTableName();
		String where = this.where.toSql();
		StringInspection.checkBlank(tableName);
		StringInspection.checkBlank(where);
		
		return MessageFormat.format("DELETE FROM {0} WHERE {1}", FieldWrapper.wrap(tableName), where);
	}
	
}
