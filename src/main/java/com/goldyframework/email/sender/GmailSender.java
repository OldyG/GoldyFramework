/**
 * @author 2017. 6. 18. 오전 1:03:38 jeong
 */
package com.goldyframework.email.sender;

import java.util.Properties;

import com.goldyframework.Prop;
import com.goldyframework.encryption.Encryption;
import com.goldyframework.encryption.exception.EncryptionException;

/**
 * @author 2017. 6. 18. 오전 1:03:38 jeong
 */
public class GmailSender extends AbstractMailSender {
	
	/**
	 * 호스트
	 */
	private static final String GMAIL_HOST = "smtp.gmail.com"; //$NON-NLS-1$
	
	/**
	 * 포트
	 */
	private static final int GMAIL_PORT = 587;
	
	/**
	 * {@link GmailSender} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author 2017. 6. 18. 오전 1:04:57 jeong
	 */
	public GmailSender() throws EncryptionException {
		super();
		this.initialize();
	}
	
	/**
	 * @author 2017. 6. 18. 오전 1:08:12 jeong
	 */
	private void initialize() throws EncryptionException {
		
		super.setUsername("jhkume90@gmail.com"); //$NON-NLS-1$
		super.setPassword(new Encryption().decrypt("Zsnv6SJj5ltiepBLrXHZkw==")); //$NON-NLS-1$
		super.setHost(GMAIL_HOST);
		super.setPort(GMAIL_PORT);
		this.setDefaultEncoding(Prop.DATE_FORMAT);
		
		final Properties prop = new Properties();
		prop.setProperty("mail.smtp.ssl.trust", GMAIL_HOST); //$NON-NLS-1$
		prop.setProperty("mail.smtp.starttls.enable", "true"); //$NON-NLS-1$//$NON-NLS-2$
		prop.setProperty("mail.smtp.auth", "true"); //$NON-NLS-1$//$NON-NLS-2$
		
		super.setJavaMailProperties(prop);
	}
	
}
