/**
 * FileName : EmailSendPropertyValidator.java
 * Created : 2017. 4. 10.
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Goldy Project. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.email;

import com.goldyframework.validator.ObjectAssert;
import com.goldyframework.validator.ValidateException;
import com.goldyframework.validator.Validator;

public class EmailSendPropertyValidator implements Validator<EmailTransmissionProperty> {
	/**
	 * EmailSendPropertyValidator 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author jeong
	 * @since 2017. 4. 10. 오후 9:32:45
	 */
	public EmailSendPropertyValidator() {
		super();
	}

	@Override
	public void check(final EmailTransmissionProperty obj) throws ValidateException {
		ObjectAssert.checkNull(obj);

	}

}
