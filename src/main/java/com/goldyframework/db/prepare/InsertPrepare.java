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
import java.util.List;

/**
 * @author 2017. 7. 2. 오전 11:10:01 jeong
 */
public class InsertPrepare implements Prepare {

	private List<String> columns;

	private String table;

	private List<Object> values;

	private final PrepareHelper helper = new PrepareHelper();

	/**
	 * @author 2017. 7. 2. 오후 12:32:00 jeong
	 * @return
	 */
	@Override
	public Object[] getArgs() {
		
		return this.values.toArray(new Object[this.values.size()]);
	}

	public void setColumns(final List<String> columns) {

		this.columns = columns;
	}

	public void setColumns(final String... columns) {

		this.setColumns(new ArrayList<>(Arrays.asList(columns)));
	}

	public void setTable(final String table) {

		this.table = table;
	}

	public void setValues(final List<Object> values) {

		this.values = values;
	}

	/**
	 * @author 2017. 7. 2. 오전 11:26:44 jeong
	 * @param loginType
	 * @param id
	 * @param password
	 */
	public void setValues(final Object... values) {

		this.setValues(new ArrayList<>(Arrays.asList(values)));
	}

	/**
	 * {@inheritDoc}
	 *
	 * @author 2017. 7. 2. 오전 11:31:20 jeong
	 */
	@Override
	public String toPrepareSql() {

		final List<String> detailColumn = this.helper.toDetiailColumn(this.table, this.columns);

		return MessageFormat.format("INSERT INTO {0} ({1}) VALUES ({2})",  //$NON-NLS-1$
			this.table,
			this.helper.join(detailColumn),
			this.helper.joinMark(this.columns.size()));
	}

}
