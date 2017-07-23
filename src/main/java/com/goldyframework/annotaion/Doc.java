/**
 * FileName : {@link Doc}.java
 * Created : 2017. 4. 10.
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Goldy Project. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.annotaion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 자체 Doc 수집
 *
 * @author jeong
 * @since 2016. 7. 10. 오후 8:23:51
 */
@Target({ ElementType.METHOD, ElementType.TYPE, ElementType.FIELD, ElementType.PARAMETER, ElementType.PACKAGE })
@Retention(RetentionPolicy.RUNTIME)
public @interface Doc {
	
	/**
	 * 이 코드를 생성한 개발자
	 *
	 * @author jeonghyun.kum
	 * @since 2016. 7. 11. 오후 2:32:41
	 */
	String author() default "";
	
	/**
	 * 문서화 순서를 정의합니다.<br>
	 * 작은 숫자가 가장 우선순위가 높습니다.<br>
	 * 만약 같은 숫자가 정의되어있을 경우 같은 숫자끼리 랜덤으로, 정의하지 않을 경우 가장 하위에 작성됩니다.
	 *
	 * @author jeong
	 * @since 2016. 7. 13. 오후 10:04:03
	 */
	int order() default Integer.MAX_VALUE;
	
	/**
	 * 이 코드를 생성한 시간
	 *
	 * @author jeonghyun.kum
	 * @since 2016. 7. 11. 오후 2:32:39
	 */
	String since() default "";
	
	/**
	 * 코드 설명
	 * 여러 줄로 나눌 경우, 배열로 구분합니다.
	 * 함수 매개변수 호출 우선순위
	 * 파라미터의 {@link Doc} > 파라미터의 클래스의 {@link Doc} > 클래스 타입에 대한 미리정의한 값 > 변수이름에 대한 미리정의한 값
	 * 위에 해당하는 설명을 찾을 수 없는경우 빈 문자열반환
	 *
	 * @author jeonghyun.kum
	 * @since 2016. 7. 11. 오후 2:32:37
	 */
	String value();
	
}
