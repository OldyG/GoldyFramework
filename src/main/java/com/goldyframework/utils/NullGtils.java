/**
 * FileName : {@link NullGtils}.java
 * Created : 2017. 7. 2. 오후 8:27:12
 * Author : jeong
 * Summary :
 * Copyright (C) 2018 Formal Works Inc. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 (주)포멀웍스에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.utils;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.goldyframework.inspection.ObjectInspection;

/**
 * @author 2017. 7. 2. 오후 8:27:12 jeong
 */
public class NullGtils {
	
	public static final <T> Collection<T> emptyIfNull(final Collection<T> object) {
		
		return object != null ? object : Collections.emptyList();
	}
	
	public static final <T> List<T> emptyIfNull(final List<T> object) {
		
		return object != null ? object : Collections.emptyList();
	}
	
	public static final <K, V> Map<K, V> emptyIfNull(final Map<K, V> object) {
		
		return object != null ? object : Collections.emptyMap();
	}
	
	public static final <T> Set<T> emptyIfNull(final Set<T> object) {
		
		return object != null ? object : Collections.emptySet();
	}
	
	public static final String emptyIfNull(final String string) {
		
		return string != null ? string : StringUtils.EMPTY;
	}
	
	/**
	 * @author 2017. 7. 2. 오후 9:04:53 jeong
	 * @param <T>
	 * @param values
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T[] emptyIfNull(final T[] values) {
		
		return values != null ? values : (T[]) new Object[0];
	}
	
	/**
	 * @author 2017. 7. 3. 오후 11:13:03 jeong
	 * @param <T>
	 * @param value
	 * @return
	 */
	public static <T> T throwIfNull(final T value) {
		
		ObjectInspection.checkNull(value);
		return value;
	}
	
}
