/**
 * FileName : {@link StreamGtils}.java
 * Created : 2018. 2. 17. 오후 8:17:40
 * Author : jeong
 * Summary :
 * Copyright (C) 2018 Formal Works Inc. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.utils;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamGtils {
	
	public static <T, R> List<R> mapList(Collection<T> collection,
		Function<T, R> function) {
		
		return mapStream(collection, function).collect(Collectors.toList());
	}
	
	public static <T, R> Set<R> mapSet(Collection<T> collection,
		Function<T, R> function) {
		
		return mapStream(collection, function).collect(Collectors.toSet());
	}
	
	private static <T, R> Stream<R> mapStream(Collection<T> collection, Function<T, R> function) {
		
		return collection.stream().map(function);
	}
	
	/**
	 * {@link StreamGtils} 클래스의 새 인스턴스를 초기화 합니다.
	 * 
	 * @author 2018. 2. 17. 오후 8:18:29 jeong
	 */
	private StreamGtils() {
		
		throw new IllegalStateException("Utility class");
		
	}
}
