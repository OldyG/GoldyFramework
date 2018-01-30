/**
 * FileName : {@link RandomSelectGtils}.java
 * Created : 2018. 1. 30. 오후 7:49:14
 * Author : jeong
 * Summary :
 * Copyright (C) 2018 Formal Works Inc. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 (주)포멀웍스에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.random;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;

public class RandomSelectGtils {
	
	public static <T> T selectRandomObject(final Collection<T> collection) {
		
		if (CollectionUtils.isEmpty(collection)) {
			return null;
		}
		
		final Set<T> set = new HashSet<>(collection);
		final List<T> list = new ArrayList<>(set);
		
		final int randomInteger = new SecureRandom().nextInt(set.size());
		
		return list.get(randomInteger);
		
	}
}
