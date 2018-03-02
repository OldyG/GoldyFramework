/**
 * FileName : {@link CollectionInspection}.java
 * Created : 2017. 8. 19. 오후 12:34:31
 * Author : jeong
 * Summary :
 * Copyright (C) 2018 Formal Works Inc. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 (주)포멀웍스에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.inspection;

import java.text.MessageFormat;
import java.util.Collection;

import com.goldyframework.inspection.exception.InspectionException;

public class CollectionInspection {
	
	/**
	 * 다음 컬렉션이 null 또는 빈 컬렉션인지 확인합니다.
	 *
	 * @author 2017. 6. 14. 오후 10:09:10 jeong
	 * @param targetList
	 *            검사대상 리스트
	 * @throws ValidateException
	 *             null 또는 사이즈가 0일경우 발생합니다.
	 */
	public static void checkNullOrEmptyCollection(final Collection<?> targetList) {
		
		ObjectInspection.checkNull(targetList);
		
		if (targetList.isEmpty()) {
			throw new InspectionException("리스트 사이즈가 0이 될 수 없습니다.");
		}
		
	}
	
	public static void checkSize(final Collection<?> collection, final int validSize) {
		
		if (isValidSize(collection, validSize) == false) {
			throw new InspectionException(MessageFormat.format("컬렉션 사이즈는 반드시 [{0}]이어야합니다.", validSize));
		}
	}
	
	public static boolean isValidSize(final Collection<?> collection, final int validSize) {
		
		ObjectInspection.checkNull(collection);
		IntegerInspection.checkBelow(validSize, -1);
		return collection.size() == validSize;
	}
	
}
