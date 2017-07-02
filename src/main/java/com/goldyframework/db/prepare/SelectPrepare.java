/**
 * FileName : {@link SelectPrepare}.java
 * Created : 2017. 7. 2. 오후 5:19:40
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Goldy Project. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 (주)Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.db.prepare;

import java.util.Collection;

public class SelectPrepare extends AbstractPrepare {

	/**
	 * {@link SelectPrepare} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author 2017. 7. 2. 오후 5:33:38 jeong
	 * @param tableName
	 */
	public SelectPrepare(final String tableName) {
		super(tableName);
	}

	/**
	 * {@inheritDoc}
	 *
	 * @author 2017. 7. 2. 오후 5:27:33 jeong
	 */
	@Override
	public Collection<Object> getArgs() {

		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @author 2017. 7. 2. 오후 5:27:33 jeong
	 */
	@Override
	public String toPrepareSql() {

		// TODO Auto-generated method stub
		return null;
	}

}
