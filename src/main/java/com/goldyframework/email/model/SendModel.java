/**
 * FileName : {@link SendModel}.java
 * Created : 2017. 4. 10.
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Goldy Project. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.email.model;

import java.util.Collection;
import java.util.LinkedList;

import javax.mail.internet.InternetAddress;

import com.goldyframework.inspection.ObjectInspection;
import com.goldyframework.utils.NullGtils;

/**
 * 전송 모델
 *
 * @author 2017. 6. 18. 오전 11:37:28 jeong
 */
public class SendModel {

	/**
	 * 보내는 사람
	 */
	private final InternetAddress from;

	/**
	 * 받는 사람
	 */
	private final Collection<InternetAddress> to = new LinkedList<>();

	/**
	 * 참조인
	 */
	private final Collection<InternetAddress> cc = new LinkedList<>();

	/**
	 * 첨부 파일
	 */
	private final Collection<AttachmentModel> attachmentList = new LinkedList<>();

	/**
	 * 제목
	 */
	private final String subject;

	/**
	 * 이메일 내용
	 */
	private String text;

	/**
	 * {@link SendModel} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author 2017. 6. 18. 오전 11:38:43 jeong
	 * @param sender
	 *            보내는 사람
	 * @param subject
	 *            제목
	 */
	public SendModel(final InternetAddress sender, final String subject) {

		this.from = NullGtils.throwIfNull(sender);
		this.subject = NullGtils.throwIfNull(subject);
	}

	/**
	 * file를 반환합니다.
	 *
	 * @see {@link #attachmentList}
	 * @author jeonghyun.kum
	 * @since 2016. 4. 23. 오후 6:27:53
	 * @return attachmentList
	 */
	public Collection<AttachmentModel> getAttachmentList() {

		return this.attachmentList;
	}

	/**
	 * cc를 반환합니다.
	 *
	 * @see {@link #cc}
	 * @author jeonghyun.kum
	 * @since 2016. 4. 23. 오후 6:27:53
	 * @return cc
	 */
	public Collection<InternetAddress> getCc() {

		return this.cc;
	}

	/**
	 * from를 반환합니다.
	 *
	 * @see {@link #from}
	 * @author jeonghyun.kum
	 * @since 2016. 4. 23. 오후 6:27:53
	 * @return from
	 */
	public InternetAddress getFrom() {

		return this.from;
	}

	/**
	 * subject를 반환합니다.
	 *
	 * @see {@link #subject}
	 * @author jeonghyun.kum
	 * @since 2016. 4. 23. 오후 6:27:53
	 * @return subject
	 */
	public String getSubject() {

		return this.subject;
	}

	/**
	 * text를 반환합니다.
	 *
	 * @see {@link #text}
	 * @author jeonghyun.kum
	 * @since 2016. 4. 23. 오후 6:27:53
	 * @return text
	 */
	public String getText() {

		return this.text;
	}

	/**
	 * to를 반환합니다.
	 *
	 * @see {@link #to}
	 * @author jeonghyun.kum
	 * @since 2016. 4. 23. 오후 6:27:53
	 * @return to
	 */
	public Collection<InternetAddress> getTo() {

		return this.to;
	}

	/**
	 * file를 초기화합니다.
	 *
	 * @see {@link #attachmentList}
	 * @author jeonghyun.kum
	 * @param fileList
	 *            attachmentList
	 * @since 2016. 4. 23. 오후 6:27:53
	 */
	public void setAttachmentList(final Collection<AttachmentModel> fileList) {

		ObjectInspection.checkNull(fileList);
		this.attachmentList.clear();
		this.attachmentList.addAll(fileList);
	}

	/**
	 * cc를 초기화합니다.
	 *
	 * @see {@link #cc}
	 * @author jeonghyun.kum
	 * @param ccList
	 *            cc
	 * @since 2016. 4. 23. 오후 6:27:53
	 */
	public void setCc(final Collection<InternetAddress> ccList) {

		ObjectInspection.checkNull(ccList);
		this.cc.clear();
		this.cc.addAll(ccList);
	}

	/**
	 * cc를 초기화합니다.
	 *
	 * @see {@link #cc}
	 * @author jeonghyun.kum
	 * @param cc
	 *            cc
	 * @since 2016. 4. 23. 오후 6:27:53
	 */
	public void setCc(final InternetAddress cc) {

		ObjectInspection.checkNull(cc);
		this.cc.clear();
		this.cc.add(cc);
	}

	/**
	 * file를 초기화합니다.
	 *
	 * @see {@link #attachmentList}
	 * @author jeonghyun.kum
	 * @param file
	 * @param attachmentList
	 *            attachmentList
	 * @since 2016. 4. 23. 오후 6:27:53
	 */
	public void setFile(final AttachmentModel file) {

		ObjectInspection.checkNull(file);
		this.attachmentList.clear();
		this.attachmentList.add(file);
	}

	/**
	 * text를 초기화합니다.
	 *
	 * @see {@link #text}
	 * @author jeonghyun.kum
	 * @param text
	 *            text
	 * @since 2016. 4. 23. 오후 6:27:53
	 */
	public void setText(final String text) {

		this.text = NullGtils.throwIfNull(text);
	}

	/**
	 * to를 초기화합니다.
	 *
	 * @see {@link #to}
	 * @author jeonghyun.kum
	 * @param toList
	 *            to
	 * @since 2016. 4. 23. 오후 6:27:53
	 */
	public void setTo(final Collection<InternetAddress> toList) {

		ObjectInspection.checkNull(toList);
		this.to.clear();
		this.to.addAll(toList);
	}

	/**
	 * to를 초기화합니다.
	 *
	 * @see {@link #to}
	 * @author jeonghyun.kum
	 * @param to
	 *            to
	 * @since 2016. 4. 23. 오후 6:27:53
	 */
	public void setTo(final InternetAddress to) {

		ObjectInspection.checkNull(to);
		this.to.clear();
		this.to.add(to);
	}
}
