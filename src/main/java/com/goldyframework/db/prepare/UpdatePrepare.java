/**
 * FileName : {@link UpdatePrepare}.java
 * Created : 2017. 7. 2. 오후 9:41:50
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Goldy Project. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 (주)Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.db.prepare;

import java.text.MessageFormat;
import java.util.Collection;

import com.goldyframework.utils.NullGtils;

/**
 * @author 2017. 7. 2. 오후 9:41:50 jeong
 */
public class UpdatePrepare extends AbstractPrepare {

	private final AssignBuilder assign;

	private final WhereBuilder where;

	/**
	 * {@link UpdatePrepare} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author 2017. 7. 2. 오후 9:42:13 jeong
	 * @param tableName
	 *            테이블 이름
	 * @param assign
	 */
	public UpdatePrepare(final String tableName, final AssignBuilder assign, final WhereBuilder where) {
		super(NullGtils.throwIfNull(tableName));
		this.assign = NullGtils.throwIfNull(assign);
		this.where = NullGtils.throwIfNull(where);
	}

	/**
	 * {@inheritDoc}
	 *
	 * @author 2017. 7. 2. 오후 9:42:08 jeong
	 */
	@Override
	public Collection<Object> getArgs() {

		return this.where.getArgs();
	}

	/**
	 * {@inheritDoc}
	 *
	 * @author 2017. 7. 2. 오후 9:42:08 jeong
	 */
	@Override
	public String toPrepareSql() {

		return MessageFormat.format("", super.getTableName()); //$NON-NLS-1$
	}

}
