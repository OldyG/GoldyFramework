/**
 * FileName : {@link FwValidatorLinker}.java
 * Created : 2017. 4. 10.
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Goldy Project. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.authentication;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.goldyframework.inspection.Inspection;

@Documented
@Constraint(validatedBy = FwValidatorLinkerValidator.class)
@Target({ ElementType.FIELD, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface FwValidatorLinker {
	
	Class<?>[] groups() default {};
	
	String message() default "";
	
	Class<? extends Payload>[] payload() default {};
	
	@SuppressWarnings("rawtypes")
	Class<? extends Inspection> value();
	
}
