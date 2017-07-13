/**
 * FileName : {@link AssignBuilder}.java
 * Created : 2017. 7. 3. 오후 11:11:22
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Goldy Project. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 (주)Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.db.prepare.statement;

import java.util.Collection;
import java.util.List;

import com.goldyframework.inspection.ObjectInspection;
import com.goldyframework.utils.StringCollectionGtils;

/**
 * @author 2017. 7. 3. 오후 11:11:22 jeong
 */
public class AssignBuilder {

	private final WhereBuilder where;

	/**
	 * {@link AssignBuilder} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author 2017. 7. 7. 오후 6:54:29 jeong
	 * @param tableName
	 */
	public AssignBuilder(final String tableName) {
		this.where = new WhereBuilder(tableName);
	}

	public void appendIfNotNull(final String column, final Object value) {

		ObjectInspection.checkNull(column);
		if (value != null) {
			if (value.getClass().isEnum()) {
				this.where.append(column, Comparison.EQUAL, value.toString());
			} else {
				this.where.append(column, Comparison.EQUAL, value);
			}
		}
	}

	/**
	 * @author 2017. 7. 7. 오후 6:52:34 jeong
	 * @return
	 */
	public String build() {

		final List<String> tableColumnList = this.where.createTableColumnList();

		return StringCollectionGtils.join(tableColumnList, " , "); //$NON-NLS-1$
	}

	/**
	 * @author 2017. 7. 7. 오후 7:15:05 jeong
	 * @return
	 */
	public Collection<Object> getArgs() {

		return this.where.getArgs();
	}

	/**
	 * @author 2017. 7. 8. 오후 2:20:46 jeong
	 * @return
	 */
	public Collection<String> getColumns() {

		return this.where.getColumns();
	}

}
