/**
 * FileName : {@link IntegerBinder}.java
 * Created : 2017. 7. 1. 오후 3:13:51
 * Author : jeong
 * Summary :
 * Copyright (C) 2018 Formal Works Inc. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 (주)포멀웍스에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.db.bind;

import com.goldyframework.utils.NullGtils;

/**
 * @author 2017. 7. 1. 오후 3:13:51 jeong
 */
public class IntegerBinder implements IBinder<Integer> {
	
	/**
	 * {@inheritDoc}
	 *
	 * @author 2017. 7. 1. 오후 3:14:06 jeong
	 */
	@Override
	public Integer bind(final String value) {
		
		return Integer.parseInt(NullGtils.throwIfNull(value));
	}
	
}
