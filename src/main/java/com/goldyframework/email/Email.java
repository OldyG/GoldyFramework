/**
 * FileName : Email.java
 * Created : 2017. 4. 10.
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Goldy Project. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.email;

import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.Date;
import java.util.concurrent.Callable;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.goldyframework.validator.ValidateException;

@Component
public class Email {

	static class EmailAuthenticator extends Authenticator {

		private final String id;

		private final String pw;

		public EmailAuthenticator(final String id, final String pw) {
			this.id = id;
			this.pw = pw;
		}

		@Override
		protected PasswordAuthentication getPasswordAuthentication() {

			return new PasswordAuthentication(this.id, this.pw);
		}
	}

	/**
	 * slf4j Logger
	 *
	 * @author jeong
	 * @since 2017. 5. 22. 오후 9:20:02
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(Email.class);

	private static Multipart convertMultipart(final String content,
		final Collection<Attachment> file) throws MessagingException, UnsupportedEncodingException {

		final Multipart multiPart = new MimeMultipart();

		for (final Attachment attachment : file) {
			final MimeBodyPart filePart = new MimeBodyPart();
			final DataSource data = new FileDataSource(attachment.getFile());
			filePart.setDataHandler(new DataHandler(data));
			filePart.setFileName(new String(attachment.getFileName().getBytes("UTF-8"), "ISO-8859-1")); //$NON-NLS-1$//$NON-NLS-2$
			multiPart.addBodyPart(filePart);
		}
		final MimeBodyPart contentPart = new MimeBodyPart();
		contentPart.setContent(content, "text/html; charset=utf-8"); //$NON-NLS-1$
		multiPart.addBodyPart(contentPart);

		return multiPart;
	}

	@Autowired
	private JavaMailSender mailSender;

	/**
	 * Email 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author jeong
	 * @since 2017. 4. 10. 오후 9:32:22
	 */
	public Email() {
		super();
	}

	private MimeMessage createMimeMessage(
		final EmailTransmissionProperty property) throws MessagingException, UnsupportedEncodingException {

		final MimeMessage msg = this.mailSender.createMimeMessage();
		msg.setHeader("Content-Type", "text/html; charset=utf-8"); //$NON-NLS-1$//$NON-NLS-2$
		msg.setSentDate(new Date());
		msg.setFrom(property.getFrom());
		msg.setReplyTo(new Address[] {
				property.getFrom()
		});

		msg.setRecipients(Message.RecipientType.TO,
			property.getTo().toArray(new InternetAddress[property.getTo().size()]));
		msg.setSubject(MimeUtility.encodeText(property.getSubject(), "utf-8", "B")); //$NON-NLS-1$//$NON-NLS-2$

		if ((property.getCc() != null) && (property.getCc().isEmpty())) {
			msg.setRecipients(Message.RecipientType.CC,
				property.getCc().toArray(new InternetAddress[property.getCc().size()]));
		}
		msg.setContent(convertMultipart(property.getText(), property.getFile()), "text/html"); //$NON-NLS-1$
		return msg;
	}

	/**
	 * 작성된 propery항목으로 이메일을 전송합니다.
	 *
	 * @author jeonghyun.kum
	 * @since 2016. 4. 26. 오후 12:05:43
	 * @param property
	 *            전송 이메일 폼
	 * @return 전송 결과
	 * @throws MessagingException
	 *             이메일 전송 실패 오류
	 * @throws UnsupportedEncodingException
	 *             이메일 전송 실패 오류
	 */
	public void send(final EmailTransmissionProperty property) throws MessagingException, UnsupportedEncodingException {

		this.sendByGoldyServer(property);
	}

	private void sendByGoldyServer(
		final EmailTransmissionProperty property) throws MessagingException, UnsupportedEncodingException {

		LOGGER.debug("[이메일 서버] 이메일 전송 설정 중"); //$NON-NLS-1$
		try {
			new EmailSendPropertyValidator().check(property);
		} catch (final ValidateException e) {
			LOGGER.error(e.getMessage(), e);
			return;
		}

		final MimeMessage msg = this.createMimeMessage(property);

		LOGGER.debug("[이메일 서버] 이메일 전송 보내는 중"); //$NON-NLS-1$
		this.mailSender.send(msg);
		LOGGER.debug("[이메일 서버] 이메일 전송 완료"); //$NON-NLS-1$
	}
}

class EmailTaskSender implements Callable<String> {

	private final Message msg;

	/**
	 * EmailTaskSender 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author jeonghyun.kum
	 * @since 2016. 5. 12. 오후 6:13:14
	 */
	public EmailTaskSender(final Message msg) {
		this.msg = msg;
	}

	@Override
	public String call() throws MessagingException {

		Transport.send(this.msg);
		return "OK"; //$NON-NLS-1$
	}

}
