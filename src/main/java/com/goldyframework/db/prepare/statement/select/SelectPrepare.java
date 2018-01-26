/**
 * FileName : {@link SelectPrepare}.java
 * Created : 2017. 7. 2. 오후 5:19:40
 * Author : jeong
 * Summary :
 * Copyright (C) 2018 Formal Works Inc. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 (주)포멀웍스에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.db.prepare.statement.select;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.collections4.MapUtils;

import com.goldyframework.db.prepare.statement.AbstractPrepare;
import com.goldyframework.db.prepare.statement.OrderByBuilder;
import com.goldyframework.db.prepare.statement.WhereBuilder;
import com.goldyframework.utils.NullGtils;
import com.goldyframework.utils.StringCollectionGtils;
import com.google.common.annotations.VisibleForTesting;

public class SelectPrepare extends AbstractPrepare {
	
	private final List<String> columns;
	
	private final WhereBuilder where;
	
	private final OrderByBuilder orderBy;
	
	/**
	 * {@link SelectPrepare} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author 2017. 7. 2. 오후 5:33:38 jeong
	 * @param tableName
	 * @param columns
	 * @param where
	 */
	@VisibleForTesting
	SelectPrepare(final String tableName, final List<String> columns, final WhereBuilder where,
		final OrderByBuilder orderByBuilder) {
		
		super(NullGtils.throwIfNull(tableName));
		this.columns = new ArrayList<>(columns);
		this.where = where;
		this.orderBy = orderByBuilder;
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * @author 2017. 7. 2. 오후 5:27:33 jeong
	 */
	@Override
	public Collection<Object> getArgs() {
		
		return this.where.getArgs();
	}
	
	/**
	 * @author 2017. 7. 9. 오전 12:02:09 jeong
	 * @return
	 */
	private String getColumnArea() {
		
		if (this.columns.isEmpty()) {
			return "*"; 
		}
		final List<String> eachPrepend = StringCollectionGtils.eachPrepend(super.getTableName() + '.', this.columns);
		
		return StringCollectionGtils.join(eachPrepend, ", "); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * @author 2017. 7. 2. 오후 5:27:33 jeong
	 */
	@Override
	public String toPrepareSql() {
		
		final String columnArea = this.getColumnArea();
		final String tableName = super.getTableName();
		
		final String firstStep;
		
		if (this.where.isEmpty()) {
			firstStep = MessageFormat.format("SELECT {0} FROM {1}", columnArea, tableName); 
		} else {
			firstStep = MessageFormat.format("SELECT {0} FROM {1} WHERE {2}", 
				columnArea, tableName, this.where.build());
		}
		
		if (MapUtils.isEmpty(this.orderBy)) {
			return firstStep;
		}
		
		return MessageFormat.format("{0} {1}", firstStep, this.orderBy.toSql()); 
		
	}
	
}
