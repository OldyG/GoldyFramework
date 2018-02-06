/**
 * FileName : {@link InsertPrepare}.java
 * Created : 2017. 7. 2. 오전 11:10:01
 * Author : jeong
 * Summary :
 * Copyright (C) 2018 Formal Works Inc. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 (주)포멀웍스에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.db.prepare.statement.insert;

import java.text.MessageFormat;
import java.util.Collection;
import java.util.List;

import com.goldyframework.db.prepare.statement.AbstractPrepare;
import com.goldyframework.db.prepare.statement.FieldWrapper;
import com.goldyframework.db.prepare.statement.guide.AssignGuide;
import com.goldyframework.inspection.ObjectInspection;
import com.goldyframework.utils.NullGtils;
import com.goldyframework.utils.StringCollectionGtils;
import com.google.common.annotations.VisibleForTesting;

public class InsertPrepare extends AbstractPrepare {
	
	private final AssignGuide assign;
	
	/**
	 * {@link InsertPrepare} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author 2017. 7. 2. 오후 5:33:23 jeong
	 * @param tableName
	 * @param assign
	 */
	@VisibleForTesting
	InsertPrepare(final String tableName, final AssignGuide assign) {
		
		super(NullGtils.throwIfNull(tableName));
		ObjectInspection.checkNull(assign);
		this.assign = assign;
	}
	
	/**
	 * @author 2017. 7. 2. 오후 12:32:00 jeong
	 * @return
	 */
	@Override
	public Collection<Object> getArgs() {
		
		return this.assign.getArgs();
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * @author 2017. 7. 2. 오전 11:31:20 jeong
	 */
	@Override
	public String toPrepareSql() {
		
		String tableName = this.getTableName();
		tableName = FieldWrapper.wrap(tableName);
		Collection<String> columns = this.assign.getColumns();
		columns = FieldWrapper.wrap(columns);
		final List<String> detailColumn = StringCollectionGtils.eachPrepend(tableName + '.',
			columns);
		
		return MessageFormat.format("INSERT INTO {0} ({1}) VALUES ({2})",
			tableName,
			StringCollectionGtils.join(detailColumn, ", "),
			super.joinMark(detailColumn.size()));
	}
	
}
