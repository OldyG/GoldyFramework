/**
 * FileName : {@link Ref}.java
 * Created : 2017. 7. 2. 오후 10:49:42
 * Author : jeong
 * Summary :
 * Copyright (C) 2018 Formal Works Inc. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.annotaion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 매개변수 붙는 어노테이션으로, 호출 시 입력된 인자가 변경되는 경우에 붙입니다.
 *
 * <pre>
 * private void test(@{@link Ref} A a, String name) {
 * 	a.setName(name);	// 참조된 a의 값이 변경됨
 * }
 * </pre>
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.SOURCE)
public @interface Ref {
	
	String[] note() default "";
	
}
