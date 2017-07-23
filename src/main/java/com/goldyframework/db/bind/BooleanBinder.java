/**
 * FileName : {@link BooleanBinder}.java
 * Created : 2017. 6. 30. 오후 10:29:59
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Goldy Project. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 (주)Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.db.bind;

import com.goldyframework.inspection.BooleanInspection;
import com.goldyframework.utils.NullGtils;

/**
 * @author 2017. 6. 30. 오후 10:29:59 jeong
 */
public class BooleanBinder implements IBinder<Boolean> {

	/**
	 * {@inheritDoc}
	 *
	 * @author 2017. 7. 1. 오후 2:42:47 jeong
	 */
	@Override
	public Boolean bind(final String value) {
		
		return BooleanInspection.trySoftCast(NullGtils.throwIfNull(value));
	}

}
