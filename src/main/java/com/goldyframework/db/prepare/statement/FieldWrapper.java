/**
 * FileName : {@link FieldWrapper}.java
 * Created : 2018. 2. 3. 오전 2:20:36
 * Author : jeong
 * Summary :
 * Copyright (C) 2018 Formal Works Inc. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.db.prepare.statement;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class FieldWrapper {
	
	public static List<String> wrap(Collection<String> fields) {
		
		return fields.stream()
			.map(field -> wrap(field))
			.collect(Collectors.toList());
	}
	
	public static String wrap(String field) {
		
		return '`' + field + '`';
	}
	
}
