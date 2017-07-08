/**
 * @author 2017. 6. 18. 오전 1:06:28 jeong
 */
package com.goldyframework.email.sender;

import java.text.MessageFormat;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * {@link JavaMailSenderImpl} 가상 클래스
 *
 * @author 2017. 6. 18. 오전 1:06:28 jeong
 */
public abstract class AbstractMailSender extends JavaMailSenderImpl {

	/**
	 * slf4j Logger
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractMailSender.class);

	/**
	 * {@link AbstractMailSender} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author 2017. 6. 18. 오전 1:07:00 jeong
	 */
	public AbstractMailSender() {
		super();

	}

	/**
	 * {@inheritDoc}
	 *
	 * @author 2017. 7. 8. 오후 3:38:28 jeong
	 */
	@Override
	public synchronized void send(final MimeMessage mimeMessage) {

		final String threadName = Thread.currentThread().getName();
		final String readyMessage = MessageFormat.format("[{0}]이메일 전송 중", threadName); //$NON-NLS-1$
		LOGGER.trace(readyMessage);
		super.send(mimeMessage);
		final String finishMessage = MessageFormat.format("[{0}]이메일 전송 완료", threadName); //$NON-NLS-1$
		LOGGER.trace(finishMessage);
	}
}
