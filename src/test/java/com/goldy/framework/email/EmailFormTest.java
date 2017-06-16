/**
 * FileName : EmailFormTest.java
 * Created : 2017. 4. 10.
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Goldy Project. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldy.framework.email;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.goldyframework.email.Email;
import com.goldyframework.email.EmailForm;
import com.goldyframework.email.EmailFormDesignType;
import com.goldyframework.email.EmailTransmissionProperty;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmailFormTest {

	@Autowired
	private Email email;

	@Test
	public void testSendEmail() throws UnsupportedEncodingException, MessagingException {

		final EmailForm emailContentForm = new EmailForm(EmailFormDesignType.INFOMATION);
		emailContentForm.setTitleName("JUNIT TEST"); //$NON-NLS-1$
		emailContentForm.inputBody("<div>내용입니다.</div>"); //$NON-NLS-1$

		final String result = emailContentForm.parse();
		final InternetAddress sender = new InternetAddress("hokkk01@naver.com", "금정현"); //$NON-NLS-1$ //$NON-NLS-2$
		final EmailTransmissionProperty prop = new EmailTransmissionProperty(sender, "JUNIT TEST"); //$NON-NLS-1$

		prop.setTo(Arrays.asList(
			new InternetAddress("hokkk01@naver.com", "금정금정현"), //$NON-NLS-1$//$NON-NLS-2$
			new InternetAddress("jhkume90@gmail.com", "금정금정현")));  //$NON-NLS-1$//$NON-NLS-2$
		prop.setText(result);

		this.email.send(prop);
	}

}
