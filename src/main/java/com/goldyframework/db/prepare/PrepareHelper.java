/**
 * FileName : {@link PrepareHelper}.java
 * Created : 2017. 7. 2. 오전 11:34:52
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Goldy Project. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 (주)Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.db.prepare;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.goldyframework.inspection.IntegerInspection;
import com.goldyframework.utils.NullGtils;
import com.goldyframework.utils.StringCollectionGtils;

/**
 * @author 2017. 7. 2. 오전 11:34:52 jeong
 */
public class PrepareHelper {
	
	public String join(final List<String> columns) {
		
		return StringCollectionGtils.join(NullGtils.throwIfNull(columns), ", "); //$NON-NLS-1$
	}
	
	public String joinMark(final int size) {
		
		IntegerInspection.checkBelowZero(size);
		final Collection<String> marks = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			marks.add("?"); //$NON-NLS-1$
		}
		
		return StringCollectionGtils.join(marks, ", "); //$NON-NLS-1$
	}
	
	/**
	 * @author 2017. 7. 2. 오후 12:53:16 jeong
	 * @param table
	 * @param columns
	 * @return
	 */
	public List<String> toDetiailColumn(final String table, final List<String> columns) {
		
		final String prepend = NullGtils.throwIfNull(table) + '.';
		final List<String> stringList = NullGtils.throwIfNull(columns);
		return StringCollectionGtils.eachPrepend(prepend, stringList);
	}
	
}
