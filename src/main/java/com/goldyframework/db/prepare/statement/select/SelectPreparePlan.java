/**
 * FileName : {@link SelectPreparePlan}.java
 * Created : 2017. 7. 8. 오후 11:37:46
 * Author : jeong
 * Summary :
 * Copyright (C) 2018 Formal Works Inc. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.db.prepare.statement.select;

import java.util.ArrayList;
import java.util.List;

import com.goldyframework.db.prepare.statement.PreparePlan;
import com.goldyframework.db.prepare.statement.guide.Comparison;
import com.goldyframework.db.prepare.statement.guide.LimitGuide;
import com.goldyframework.db.prepare.statement.guide.OrderByGuide;
import com.goldyframework.db.prepare.statement.guide.OrderType;
import com.goldyframework.db.prepare.statement.guide.WhereGuide;

/**
 * @author 2017. 7. 8. 오후 11:37:46 jeong
 */
public class SelectPreparePlan implements PreparePlan<SelectPrepare> {
	
	private final String tableName;
	
	private final WhereGuide where;
	
	private final OrderByGuide orderBy;
	
	private LimitGuide limit;
	
	private final List<String> columns = new ArrayList<>();
	
	/**
	 * {@link SelectPreparePlan} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author 2017. 7. 8. 오후 11:37:50 jeong
	 */
	public SelectPreparePlan(String tableName) {
		
		super();
		this.tableName = tableName;
		this.where = new WhereGuide(tableName);
		this.orderBy = new OrderByGuide();
	}
	
	/**
	 * @author 2017. 7. 8. 오후 11:38:38 jeong
	 * @return
	 */
	@Override
	public SelectPrepare build() {
		
		return new SelectPrepare(this.tableName, this.columns, this.where, this.orderBy, this.limit);
	}
	
	public SelectPreparePlan column(String columnName) {
		
		this.columns.add(columnName);
		return this;
	}
	
	public SelectPreparePlan limit(int count) {
		
		this.limit = new LimitGuide(count);
		return this;
	}
	
	public SelectPreparePlan limit(int startIndex, int count) {
		
		this.limit = new LimitGuide(startIndex, count);
		return this;
	}
	
	public SelectPreparePlan orderby(String columnName, OrderType orderType) {
		
		this.orderBy.put(columnName, orderType);
		return this;
	}
	
	public SelectPreparePlan where(String columnName, Comparison comparison, Object value) {
		
		this.where.append(columnName, comparison, value);
		return this;
	}
}
