/**
 * FileName : {@link ResultSetBinder}.java
 * Created : 2017. 7. 1. 오후 5:00:07
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Goldy Project. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 (주)Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.db.bind;

import java.sql.ResultSet;
import java.sql.SQLException;

@FunctionalInterface
public interface ResultSetBinder<T> {
	
	T bind(ResultSet rs, String column) throws SQLException;
}
