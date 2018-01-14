/**
 * @author 2017. 6. 18. 오전 12:04:50 jeong
 */
package com.goldyframework.email;

import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.goldyframework.annotaion.MavenIgnoreTest;
import com.goldyframework.email.exception.EmailException;
import com.goldyframework.email.model.SendModel;
import com.goldyframework.email.sender.GmailSender;

@Category(MavenIgnoreTest.class)
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { Email.class, GmailSender.class })
public class EmailTest extends Mockito {
	
	/**
	 * slf4j Logger
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(EmailTest.class);
	
	@Spy
	@Autowired
	private Email email;
	
	private final CountDownLatch lock = new CountDownLatch(1);
	
	@SpyBean
	private GmailSender mailSender;
	
	@Test
	public void testCreateSendTread() throws InterruptedException {
		
		final MimeMessage msg1 = mock(MimeMessage.class);
		final MimeMessage msg2 = mock(MimeMessage.class);
		final MimeMessage msg3 = mock(MimeMessage.class);
		final MimeMessage msg4 = mock(MimeMessage.class);
		
		doAnswer(invocation -> {
			final Random secureRandom = new SecureRandom();
			final int result = secureRandom.nextInt(200) + 100;
			LOGGER.trace("[이메일 테스트] 전송중" + result); 
			Thread.sleep(result);
			LOGGER.trace("[이메일 테스트] 전송완료" + result); 
			return null;
		}).when(this.mailSender).send(any(MimeMessage.class));
		
		final Thread send1 = this.email.createSendTread(msg1);
		final Thread send2 = this.email.createSendTread(msg2);
		final Thread send3 = this.email.createSendTread(msg3);
		final Thread send4 = this.email.createSendTread(msg4);
		
		send1.start();
		send2.start();
		send3.start();
		send4.start();
		
		send1.join();
		send2.join();
		send3.join();
		send4.join();
		
		verify(this.mailSender, times(1)).send(msg1);
		verify(this.mailSender, times(1)).send(msg2);
		verify(this.mailSender, times(1)).send(msg3);
		verify(this.mailSender, times(1)).send(msg4);
	}
	
	@Test
	public void testSend() throws EmailException, AddressException, UnsupportedEncodingException, InterruptedException {
		
		final EmailForm emailContentForm = new EmailForm(EmailFormDesignType.INFOMATION);
		emailContentForm.setTitleName("JUNIT TEST"); 
		emailContentForm.inputBody("<div>내용입니다.</div>"); 
		
		final String result = emailContentForm.parse();
		final SendModel prop = new SendModel(new InternetAddress("hokkk01@naver.com"), "JUNIT TEST"); 
		
		prop.setTo(Arrays.asList(
			new InternetAddress("hokkk01@naver.com", "금정금정현"), 
			new InternetAddress("jhkume90@gmail.com", "금정금정현")));  
		prop.setText(result);
		
		final MimeMessage msg = this.email.createMimeMessage(prop);
		final Thread thread = this.email.createSendTread(msg);
		thread.start();
		thread.join();
		verify(this.email, times(1)).createMimeMessage(prop);
	}
	
}
