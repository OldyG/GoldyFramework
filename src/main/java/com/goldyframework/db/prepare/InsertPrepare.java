/**
 * FileName : {@link InsertPrepare}.java
 * Created : 2017. 7. 2. 오전 11:10:01
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Goldy Project. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 (주)Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.db.prepare;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.goldyframework.inspection.ObjectInspection;
import com.goldyframework.utils.NullGtils;

public class InsertPrepare extends AbstractPrepare {

	private final List<String> columns = new ArrayList<>();

	private final List<Object> values = new ArrayList<>();

	private final PrepareHelper helper = new PrepareHelper();

	/**
	 * {@link InsertPrepare} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author 2017. 7. 2. 오후 5:33:23 jeong
	 * @param tableName
	 */
	public InsertPrepare(final String tableName) {
		super(NullGtils.throwIfNull(tableName));
	}

	/**
	 * @author 2017. 7. 2. 오후 12:32:00 jeong
	 * @return
	 */
	@Override
	public Collection<Object> getArgs() {

		return new ArrayList<>(this.values);
	}

	public void setColumns(final List<String> columns) {

		ObjectInspection.checkNull(columns);
		this.columns.clear();
		this.columns.addAll(columns);
	}

	public void setColumns(final String... columns) {

		ObjectInspection.checkNull(columns);
		this.setColumns(new ArrayList<>(Arrays.asList(columns)));
	}

	public void setValues(final List<Object> values) {

		ObjectInspection.checkNull(values);
		this.values.clear();
		this.values.addAll(values);
	}

	/**
	 * @author 2017. 7. 2. 오전 11:26:44 jeong
	 * @param loginType
	 * @param id
	 * @param password
	 */
	public void setValues(final Object... values) {

		ObjectInspection.checkNull(values);
		this.setValues(new ArrayList<>(Arrays.asList(values)));
	}

	/**
	 * {@inheritDoc}
	 *
	 * @author 2017. 7. 2. 오전 11:31:20 jeong
	 */
	@Override
	public String toPrepareSql() {

		final List<String> detailColumn = this.helper.toDetiailColumn(super.getTableName(), this.columns);

		return MessageFormat.format("INSERT INTO {0} ({1}) VALUES ({2})",  //$NON-NLS-1$
			super.getTableName(),
			this.helper.join(detailColumn),
			this.helper.joinMark(this.columns.size()));
	}

}
