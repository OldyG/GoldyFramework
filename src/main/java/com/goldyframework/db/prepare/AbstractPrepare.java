/**
 * FileName : {@link AbstractPrepare}.java
 * Created : 2017. 7. 2. 오후 5:30:23
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Goldy Project. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 (주)Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.db.prepare;

import com.goldyframework.utils.NullGtils;

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
	
}