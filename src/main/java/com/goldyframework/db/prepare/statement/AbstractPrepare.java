/**
 * FileName : {@link AbstractPrepare}.java
 * Created : 2017. 7. 2. 오후 5:30:23
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Goldy Project. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 (주)Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.db.prepare.statement;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;

import com.goldyframework.inspection.IntegerInspection;
import com.goldyframework.utils.NullGtils;
import com.goldyframework.utils.StringCollectionGtils;

public abstract class AbstractPrepare implements Prepare {

	private final String tableName;

	/**
	 * {@link AbstractPrepare} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author 2017. 7. 2. 오후 5:30:23 jeong
	 */
	public AbstractPrepare(final String tableName) {
		super();
		this.tableName = NullGtils.throwIfNull(tableName);
	}

	/**
	 * tableName를 반환합니다.
	 *
	 * @return tableName
	 * @author 2017. 7. 2. 오후 5:43:55 jeong
	 * @see {@link #tableName}
	 */
	public String getTableName() {

		return this.tableName;
	}

	protected String joinMark(final int size) {

		IntegerInspection.checkBelowZero(size);
		final Collection<String> marks = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			marks.add("?"); //$NON-NLS-1$
		}

		return StringCollectionGtils.join(marks, ", "); //$NON-NLS-1$
	}

	/**
	 * {@inheritDoc}
	 *
	 * @author 2017. 7. 18. 오후 9:08:27 jeong
	 */
	@Override
	public String toString() {

		return MessageFormat.format("SQL [{0}], OBJ[{1}]", this.toPrepareSql(), this.getArgs()); //$NON-NLS-1$
	}
}