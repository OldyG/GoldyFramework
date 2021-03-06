/**
 * FileName : {@link RandomEnumGtils}.java
 * Created : 2017. 8. 15. 오후 4:30:51
 * Author : jeong
 * Summary :
 * Copyright (C) 2018 Formal Works Inc. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 (주)포멀웍스에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.random;

import java.util.Arrays;
import java.util.Collection;

import com.goldyframework.inspection.ObjectInspection;

/**
 * @author 2017. 8. 15. 오후 4:30:51 jeong
 */
public class RandomEnumGtils {
	
	public static <T extends Enum<?>> T createRadomEnum(final Class<T> enumClass) {
		
		ObjectInspection.checkNull(enumClass);
		
		if (enumClass.isEnum() == false) {
			throw new IllegalArgumentException(enumClass.getName() + "클래스는 Enum클래스가 아닙니다.");
		}
		
		final T[] enumConstants = enumClass.getEnumConstants();
		
		final Collection<T> collection = Arrays.asList(enumConstants);
		
		return RandomSelectGtils.selectRandomObject(collection);
	}
	
}
