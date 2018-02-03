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
import com.goldyframework.db.prepare.statement.FieldWrapper;
import com.goldyframework.db.prepare.statement.guide.LimitGuide;
import com.goldyframework.db.prepare.statement.guide.OrderByGuide;
import com.goldyframework.db.prepare.statement.guide.WhereGuide;
import com.goldyframework.exception.LogicException;
import com.goldyframework.utils.NullGtils;
import com.goldyframework.utils.StringCollectionGtils;
import com.google.common.annotations.VisibleForTesting;

public class SelectPrepare extends AbstractPrepare {
	
	enum GuideState {
		NONE,
		EXIST;
	}
	
	private final List<String> columns;
	
	private final WhereGuide where;
	
	private final OrderByGuide orderBy;
	
	private final LimitGuide limit;
	
	private final GuideState whereState;
	
	private final GuideState orderByState;
	
	private final GuideState limitState;
	
	/**
	 * {@link SelectPrepare} 클래스의 새 인스턴스를 초기화 합니다.
	 */
	@VisibleForTesting
	SelectPrepare(final String tableName, final List<String> columns, final WhereGuide where,
		final OrderByGuide orderByBuilder, final LimitGuide limit) {
		
		super(NullGtils.throwIfNull(tableName));
		this.columns = new ArrayList<>(columns);
		this.where = where;
		this.orderBy = orderByBuilder;
		this.limit = limit;
		
		this.whereState = this.toGudeState(this.where.isEmpty() == false);
		this.orderByState = this.toGudeState(MapUtils.isEmpty(this.orderBy) == false);
		this.limitState = this.toGudeState(limit != null);
		
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
		final String tableName = FieldWrapper.wrap(super.getTableName());
		
		final List<String> wrapColumn = FieldWrapper.wrap(this.columns);
		
		final List<String> eachPrepend = StringCollectionGtils.eachPrepend(tableName + '.', wrapColumn);
		
		return StringCollectionGtils.join(eachPrepend, ", ");
	}
	
	private GuideState toGudeState(final boolean hasValue) {
		
		if (hasValue) {
			return GuideState.EXIST;
		} else {
			return GuideState.NONE;
		}
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * @author 2017. 7. 2. 오후 5:27:33 jeong
	 */
	@Override
	public String toPrepareSql() {
		
		final String columnArea = this.getColumnArea();
		final String tableName = FieldWrapper.wrap(super.getTableName());
		
		final StringBuilder sqlBuilder = new StringBuilder();
		
		final String firstStep;
		
		switch (this.whereState) {
			case EXIST:
				firstStep = MessageFormat.format("SELECT {0} FROM {1} WHERE {2}",
					columnArea, tableName, this.where.toSql());
				break;
			case NONE:
				firstStep = MessageFormat.format("SELECT {0} FROM {1}", columnArea, tableName);
				break;
			default:
				throw new LogicException();
				
		}
		sqlBuilder.append(firstStep);
		
		if (this.orderByState == GuideState.EXIST) {
			sqlBuilder.append(' ').append(this.orderBy.toSql());
		}
		
		if (this.limitState == GuideState.EXIST) {
			sqlBuilder.append(' ').append(this.limit.toSql());
		}
		
		return sqlBuilder.toString();
	}
	
}
