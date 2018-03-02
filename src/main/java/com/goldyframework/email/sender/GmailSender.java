/**
 * @author 2017. 6. 18. 오전 1:03:38 jeong
 */
package com.goldyframework.email.sender;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.goldyframework.Prop;
import com.goldyframework.email.exception.EmailException;
import com.goldyframework.encryption.Encryption;
import com.goldyframework.encryption.exception.EncryptionException;

public class GmailSender extends AbstractMailSender {
	
	/**
	 * 호스트
	 */
	private static final String GMAIL_HOST = "smtp.gmail.com";
	
	/**
	 * 포트
	 */
	private static final int GMAIL_PORT = 587;
	
	/**
	 * slf4j Logger
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(GmailSender.class);
	
	/**
	 * {@link GmailSender} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author 2017. 6. 18. 오전 1:04:57 jeong
	 * @throws EmailException
	 *             {@link GmailSender}를 초기화중 오류발생
	 */
	public GmailSender() throws EmailException {
		
		super();
		this.initialize();
	}
	
	/**
	 * @author 2017. 6. 18. 오전 1:08:12 jeong
	 * @throws EmailException
	 *             패스워드를 암호화 중
	 */
	private void initialize() throws EmailException {
		
		String decryptdPassword;
		try {
			decryptdPassword = new Encryption().decrypt("Zsnv6SJj5ltiepBLrXHZkw==");
		} catch (EncryptionException e) {
			LOGGER.error("Gmail을 초기화중 패스워드 암호화에 실패하였습니다.", e);
			throw new EmailException(e);
		}
		super.setUsername("jhkume90@gmail.com");
		super.setPassword(decryptdPassword);
		super.setHost(GMAIL_HOST);
		super.setPort(GMAIL_PORT);
		this.setDefaultEncoding(Prop.DATE_FORMAT);
		
		Properties prop = new Properties();
		prop.setProperty("mail.smtp.ssl.trust", GMAIL_HOST);
		prop.setProperty("mail.smtp.starttls.enable", "true");
		prop.setProperty("mail.smtp.auth", "true");
		
		super.setJavaMailProperties(prop);
	}
	
}
