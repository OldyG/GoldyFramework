/**
 * FileName : {@link UnitTest}.java
 * Created : 2017. 7. 2. 오후 9:18:38
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Goldy Project. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 (주)Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.annotaion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 테스트 케이스 시험 중 유닛 테스트인경우 붙입니다.
 *
 * @author 2017. 7. 2. 오후 9:18:38 jeong
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.SOURCE)
public @interface UnitTest {
	
	String[] note() default "";
}
