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

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

import javax.mail.internet.InternetAddress;

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
	private Collection<InternetAddress> to = new LinkedList<>();

	/**
	 * 참조인
	 */
	private Collection<InternetAddress> cc = new LinkedList<>();

	/**
	 * 첨부 파일
	 */
	private Collection<AttachmentModel> file = new LinkedList<>();

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
		this.from = sender;
		this.subject = subject;
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
	 * file를 반환합니다.
	 *
	 * @see {@link #file}
	 * @author jeonghyun.kum
	 * @since 2016. 4. 23. 오후 6:27:53
	 * @return file
	 */
	public Collection<AttachmentModel> getFile() {

		return this.file;
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
	 * cc를 초기화합니다.
	 *
	 * @see {@link #cc}
	 * @author jeonghyun.kum
	 * @param cc
	 *            cc
	 * @since 2016. 4. 23. 오후 6:27:53
	 */
	public void setCc(final Collection<InternetAddress> cc) {

		this.cc = new ArrayList<>(cc);
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

		this.cc.add(cc);
	}

	/**
	 * file를 초기화합니다.
	 *
	 * @see {@link #file}
	 * @author jeonghyun.kum
	 * @param file
	 *            file
	 * @since 2016. 4. 23. 오후 6:27:53
	 */
	public void setFile(final AttachmentModel file) {

		this.file.add(file);
	}

	/**
	 * file를 초기화합니다.
	 *
	 * @see {@link #file}
	 * @author jeonghyun.kum
	 * @param fileList
	 *            file
	 * @since 2016. 4. 23. 오후 6:27:53
	 */
	public void setFile(final Collection<AttachmentModel> fileList) {

		this.file = new ArrayList<>(fileList);
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

		this.text = text;
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
	public void setTo(final Collection<InternetAddress> to) {

		this.to = new ArrayList<>(to);
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

		this.to.add(to);
	}
}
