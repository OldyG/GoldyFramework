/**
 * FileName : {@link AssignGuide}.java
 * Created : 2017. 7. 3. 오후 11:11:22
 * Author : jeong
 * Summary :
 * Copyright (C) 2018 Formal Works Inc. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.db.prepare.statement.guide;

import java.util.Collection;
import java.util.List;

import com.goldyframework.inspection.ObjectInspection;
import com.goldyframework.utils.StringCollectionGtils;

/**
 * @author 2017. 7. 3. 오후 11:11:22 jeong
 */
public class AssignGuide implements Guide {
	
	private final WhereGuide where;
	
	/**
	 * {@link AssignGuide} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author 2017. 7. 7. 오후 6:54:29 jeong
	 * @param tableName
	 */
	public AssignGuide(String tableName) {
		
		ObjectInspection.checkNull(tableName);
		this.where = new WhereGuide(tableName);
	}
	
	public void appendIfNotNull(String column, Object value) {
		
		ObjectInspection.checkNull(column);
		
		String wrapColumn = column;
		if (value != null) {
			
			if (column.endsWith("key") && (value instanceof Integer) && ((int) value == 0)) {
				return;
			}
			
			this.where.append(wrapColumn, Comparison.EQUAL, value);
		}
	}
	
	public Collection<Object> getArgs() {
		
		return this.where.getArgs();
	}
	
	public Collection<String> getColumns() {
		
		return this.where.getColumns();
	}
	
	@Override
	public String toSql() {
		
		List<String> tableColumnList = this.where.createTableColumnList();
		
		return StringCollectionGtils.join(tableColumnList, ", ");
	}
	
}
