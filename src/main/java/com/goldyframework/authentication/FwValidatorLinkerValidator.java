/**
 * FileName : {@link FwValidatorLinkerValidator}.java
 * Created : 2017. 4. 10.
 * Author : jeong
 * Summary :
 * Copyright (C) 2018 Formal Works Inc. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.authentication;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.goldyframework.inspection.exception.InspectionException;

public class FwValidatorLinkerValidator implements ConstraintValidator<FwValidatorLinker, Object> {
	
	/**
	 * slf4j Logger
	 *
	 * @author jeong
	 * @since 2017. 5. 22. 오후 9:20:02
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(FwValidatorLinkerValidator.class);
	
	private FwValidatorLinker liniker;
	
	/**
	 * {@link FwValidatorLinkerValidator} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author jeong
	 * @since 2017. 4. 10. 오후 9:27:32
	 */
	public FwValidatorLinkerValidator() {
		
		super();
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * @author 2017. 6. 29. 오후 10:27:26 jeong
	 */
	@Override
	public void initialize(final FwValidatorLinker anno) {
		
		this.liniker = anno;
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * @author 2017. 6. 29. 오후 10:27:28 jeong
	 */
	@SuppressWarnings({ "unchecked", "null" })
	@Override
	public boolean isValid(final Object value, final ConstraintValidatorContext context) {
		
		context.disableDefaultConstraintViolation();
		
		try {
			this.liniker.value().newInstance().check(value);
			return true;
		} catch (InstantiationException | IllegalAccessException e) {
			LOGGER.error(e.getMessage(), e);
			return false;
		} catch (final InspectionException e) {
			context.buildConstraintViolationWithTemplate(e.getMessage()).addConstraintViolation();
			return false;
		}
	}
	
}
