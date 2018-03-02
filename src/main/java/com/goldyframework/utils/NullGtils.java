/**
 * FileName : {@link NullGtils}.java
 * Created : 2017. 7. 2. 오후 8:27:12
 * Author : jeong
 * Summary :
 * Copyright (C) 2018 Formal Works Inc. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
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
	
	public static <T> Collection<T> emptyIfNull(Collection<T> object) {
		
		return object != null ? object : Collections.emptyList();
	}
	
	public static <T> List<T> emptyIfNull(List<T> object) {
		
		return object != null ? object : Collections.emptyList();
	}
	
	public static <K, V> Map<K, V> emptyIfNull(Map<K, V> object) {
		
		return object != null ? object : Collections.emptyMap();
	}
	
	public static <T> Set<T> emptyIfNull(Set<T> object) {
		
		return object != null ? object : Collections.emptySet();
	}
	
	public static String emptyIfNull(String string) {
		
		return string != null ? string : StringUtils.EMPTY;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T[] emptyIfNull(T[] values) {
		
		return values != null ? values : (T[]) new Object[0];
	}
	
	public static <T> T throwIfNull(T value) {
		
		ObjectInspection.checkNull(value);
		return value;
	}
	
}
