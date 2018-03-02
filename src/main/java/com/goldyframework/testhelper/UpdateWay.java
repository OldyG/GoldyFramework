/**
 * FileName : {@link UpdateWay}.java
 * Created : 2017. 8. 15. 오전 1:11:04
 * Author : jeong
 * Summary :
 * Copyright (C) 2018 Formal Works Inc. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.testhelper;

import com.goldyframework.db.inf.Dto;

public interface UpdateWay<T, DTO extends Dto> {
	
	String getColumnName();
	
	T getValue(DTO result);
	
	T updateValue();
	
}
