/**
 * FileName : {@link SelectPreparePlan}.java
 * Created : 2017. 7. 8. 오후 11:37:46
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Goldy Project. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 (주)Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.db.prepare.statement.select;

import java.util.ArrayList;
import java.util.List;

import com.goldyframework.db.prepare.statement.Comparison;
import com.goldyframework.db.prepare.statement.OrderByBuilder;
import com.goldyframework.db.prepare.statement.OrderType;
import com.goldyframework.db.prepare.statement.PreparePlan;
import com.goldyframework.db.prepare.statement.WhereBuilder;

/**
 * @author 2017. 7. 8. 오후 11:37:46 jeong
 */
public class SelectPreparePlan implements PreparePlan<SelectPrepare> {
	
	private final String tableName;
	
	private final WhereBuilder where;
	
	private final OrderByBuilder orderBy;
	
	private final List<String> columns = new ArrayList<>();
	
	/**
	 * {@link SelectPreparePlan} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author 2017. 7. 8. 오후 11:37:50 jeong
	 * @param tableName
	 */
	public SelectPreparePlan(final String tableName) {
		
		super();
		this.tableName = tableName;
		this.where = new WhereBuilder(tableName);
		this.orderBy = new OrderByBuilder();
	}
	
	/**
	 * @author 2017. 7. 8. 오후 11:38:38 jeong
	 * @return
	 */
	@Override
	public SelectPrepare build() {
		
		return new SelectPrepare(this.tableName, this.columns, this.where, this.orderBy);
	}
	
	public SelectPreparePlan column(final String columnName) {
		
		this.columns.add(columnName);
		return this;
	}
	
	/**
	 * @author 2017. 8. 6. 오후 1:53:03 jeong
	 * @param createdTime
	 * @return
	 */
	public SelectPreparePlan orderby(final String columnName, final OrderType orderType) {
		
		this.orderBy.put(columnName, orderType);
		return this;
	}
	
	public SelectPreparePlan where(final String columnName, final Comparison comparison, final Object value) {
		
		this.where.append(columnName, comparison, value);
		return this;
	}
	
}
