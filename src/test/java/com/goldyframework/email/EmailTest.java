/**
 * @author 2017. 6. 18. 오전 12:04:50 jeong
 */
package com.goldyframework.email;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.goldyframework.annotaion.MavenIgnoreTest;
import com.goldyframework.email.model.SendModel;
import com.goldyframework.email.sender.GmailSender;
import com.goldyframework.encryption.exception.EncryptionException;

@Category(MavenIgnoreTest.class)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = EmailTest.ContextConfiguration.class)
public class EmailTest {

	@Configuration
	static class ContextConfiguration {

		@Bean
		public Email email() {

			return new Email();
		}

		@Bean
		public GmailSender JavaMailSenderImpl() throws EncryptionException {

			return new GmailSender();
		}
	}

	@Spy
	@Autowired
	private Email email;

	@Test
	public void testSend() throws UnsupportedEncodingException, MessagingException {

		final EmailForm emailContentForm = new EmailForm(EmailFormDesignType.INFOMATION);
		emailContentForm.setTitleName("JUNIT TEST"); //$NON-NLS-1$
		emailContentForm.inputBody("<div>내용입니다.</div>"); //$NON-NLS-1$

		final String result = emailContentForm.parse();
		final SendModel prop = new SendModel(new InternetAddress("hokkk01@naver.com"), "JUNIT TEST"); //$NON-NLS-1$ //$NON-NLS-2$

		prop.setTo(Arrays.asList(
			new InternetAddress("hokkk01@naver.com", "금정금정현"), //$NON-NLS-1$//$NON-NLS-2$
			new InternetAddress("jhkume90@gmail.com", "금정금정현")));  //$NON-NLS-1$//$NON-NLS-2$
		prop.setText(result);

		this.email.send(prop);
		Mockito.verify(this.email, Mockito.times(1)).createMimeMessage(prop);
	}

}
