/**
 * FileName : {@link EnumProperty}.java
 * Created : 2017. 4. 10.
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Goldy Project. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.enumproperty;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Enum 클래스를 관리하기 위한 어노테이션
 *
 * @author 2017. 6. 14. 오후 8:40:58 jeong
 * @deprecated (개발중인 코드)
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Deprecated
public @interface EnumProperty {

	/**
	 * 값
	 *
	 * @author 2017. 6. 14. 오후 8:41:29 jeong
	 * @return 값
	 */
	String value() default "";
}
