/**
 * FileName : {@link Validator}.java
 * Created : 2017. 4. 10.
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Goldy Project. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.inspection;

import com.goldyframework.inspection.exception.InspectionException;

/**
 * 검사 도구 개발 인터페이스
 *
 * @author 2017. 6. 14. 오후 9:13:40 jeong
 * @param <T>
 *            검사 대상 타입
 */
@FunctionalInterface
public interface Inspection<T> {

	/**
	 * 검사를 수행합니다.<br>
	 *
	 * @author 2017. 6. 14. 오후 9:15:14 jeong
	 * @param target
	 *            검사 대상
	 * @throws ValidateException
	 *             검사 대상이 조건에 만족하지 못할 시 발생하는 예외사항 클래스
	 */
	void check(T target) throws InspectionException;
}
