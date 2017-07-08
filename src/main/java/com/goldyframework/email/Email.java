/**
 * FileName : {@link Email}.java
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

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.goldyframework.Prop;
import com.goldyframework.annotaion.Ref;
import com.goldyframework.email.exception.EmailException;
import com.goldyframework.email.model.AttachmentModel;
import com.goldyframework.email.model.SendModel;
import com.goldyframework.email.validator.SendModelValidator;
import com.google.common.annotations.VisibleForTesting;

/**
 * 이메일 전송 도구
 *
 * @author 2017. 6. 18. 오후 12:41:21 jeong
 */
@Component
public class Email {

	/**
	 * slf4j Logger
	 *
	 * @author jeong
	 * @since 2017. 5. 22. 오후 9:20:02
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(Email.class);

	/**
	 * 이메일 전송 대리자
	 */
	@Autowired
	private JavaMailSender mailSender;

	/**
	 * {@link Email} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author jeong
	 * @since 2017. 4. 10. 오후 9:32:22
	 */
	public Email() {
		super();
	}

	private void addBodyPart(@Ref final Multipart multiPart, final Collection<AttachmentModel> file)
		throws MessagingException, UnsupportedEncodingException {

		for (final AttachmentModel attachment : file) {
			final MimeBodyPart filePart = this.createAttachmentMimeBodyPart(attachment);
			multiPart.addBodyPart(filePart);
		}
	}

	private String convertEncodedString(final String string) throws UnsupportedEncodingException {

		final byte[] fileNameBytes = string.getBytes(Prop.DEFAULT_CHARSET);
		return new String(fileNameBytes, "ISO-8859-1"); //$NON-NLS-1$
	}

	private MimeBodyPart createAttachmentMimeBodyPart(final AttachmentModel attachment)
		throws MessagingException, UnsupportedEncodingException {

		final MimeBodyPart filePart = new MimeBodyPart();
		final DataSource data = new FileDataSource(attachment.getFile());
		filePart.setDataHandler(new DataHandler(data));
		filePart.setFileName(this.convertEncodedString(attachment.getFileName()));
		return filePart;
	}

	/**
	 * 이메일 본문에 첨부파일을 통합하여 반환합니다.
	 *
	 * @author 2017. 6. 18. 오후 12:41:27 jeong
	 * @param content
	 *            메일 본문
	 * @param file
	 *            파일
	 * @return 이메일 본문 및 첨부파일 포함 객체 {@link Multipart}
	 * @throws MessagingException
	 *             The base class for all exceptions thrown by the Messaging classes
	 * @throws UnsupportedEncodingException
	 *             The Character Encoding is not supported.
	 */
	private Multipart createContentMultipart(final String content, final Collection<AttachmentModel> file)
		throws MessagingException, UnsupportedEncodingException {

		final Multipart multiPart = new MimeMultipart();

		this.addBodyPart(multiPart, file);

		final MimeBodyPart contentPart = new MimeBodyPart();
		contentPart.setContent(content, "text/html; charset=utf-8"); //$NON-NLS-1$
		multiPart.addBodyPart(contentPart);

		return multiPart;
	}

	/**
	 * 이메일 전송 데이터를 생성한다.
	 *
	 * @author 2017. 6. 18. 오후 12:44:03 jeong
	 * @param model
	 *            이메일 전송 모델
	 * @return 이메일 전송 데이터 {@link MimeMessage}
	 * @throws EmailException
	 *             MimeMessage 생성 중 오류 발생
	 */
	@VisibleForTesting
	MimeMessage createMimeMessage(final SendModel model) throws EmailException {

		final MimeMessage msg = this.mailSender.createMimeMessage();
		try {
			msg.setHeader("Content-Type", "text/html; charset=utf-8"); //$NON-NLS-1$//$NON-NLS-2$
			msg.setSentDate(new Date());
			msg.setFrom(model.getFrom());
			msg.setReplyTo(new Address[] {
					model.getFrom()
			});

			msg.setRecipients(RecipientType.TO,
				model.getTo().toArray(new InternetAddress[model.getTo().size()]));
			msg.setSubject(MimeUtility.encodeText(model.getSubject(), Prop.DEFAULT_CHARSET.name(), "B")); //$NON-NLS-1$

			if ((model.getCc() != null) && (model.getCc().isEmpty())) {
				msg.setRecipients(RecipientType.CC,
					model.getCc().toArray(new InternetAddress[model.getCc().size()]));
			}
			msg.setContent(this.createContentMultipart(model.getText(), model.getAttachmentList()), "text/html"); //$NON-NLS-1$
			return msg;
		} catch (final MessagingException | UnsupportedEncodingException e) {
			LOGGER.error("MimeMessage를 생성 중 오류 발생", e); //$NON-NLS-1$
			throw new EmailException(e);
		}
	}

	/**
	 * 작성된 propery항목으로 이메일을 전송합니다.
	 *
	 * @author jeonghyun.kum
	 * @since 2016. 4. 26. 오후 12:05:43
	 * @param msg
	 *            전송 이메일 폼
	 * @return
	 */
	@VisibleForTesting
	Thread createSendTread(final MimeMessage msg) {

		return new Thread(() -> {
			try {
				this.mailSender.send(msg);
			} catch (final MailException e) {
				LOGGER.debug("이메일 전송 실패", e); //$NON-NLS-1$
			}
		});
	}

	public void send(final SendModel property) throws EmailException {

		LOGGER.debug("이메일 전송 데이터 설정 중"); //$NON-NLS-1$
		new SendModelValidator().check(property);
		final MimeMessage msg = this.createMimeMessage(property);
		final Thread sendAwait = this.createSendTread(msg);
		sendAwait.start();
	}

}
