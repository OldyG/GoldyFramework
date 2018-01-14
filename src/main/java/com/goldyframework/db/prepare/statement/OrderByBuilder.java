/**
 * FileName : {@link OrderByBuilder}.java
 * Created : 2017. 8. 6. 오후 1:58:28
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Goldy Project. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 (주)Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.db.prepare.statement;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.goldyframework.utils.StringCollectionGtils;

/**
 * @author 2017. 8. 6. 오후 1:58:28 jeong
 */
public class OrderByBuilder extends ConcurrentHashMap<String, OrderType> {
	
	/**
	 * @author 2017. 8. 6. 오후 2:08:37 jeong
	 * @return
	 */
	public String toSql() {
		
		final List<String> temp = new ArrayList<>();
		for (final Entry<String, OrderType> entry : this.entrySet()) {
			temp.add(MessageFormat.format("{0} {1}", entry.getKey(), entry.getValue().toString())); 
		}
		
		return "ORDER BY " + StringCollectionGtils.join(temp, ", ");  
	}
	
}
