/**
 * FileName : EmailTransmissionProperty.java
 * Created : 2017. 4. 10.
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Goldy Project. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.email;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

import javax.mail.internet.InternetAddress;

public class EmailTransmissionProperty {

	// 보내는 사람
	private final InternetAddress from;

	// 받는 사람
	private Collection<InternetAddress> to = new LinkedList<>();

	// 참조인
	private Collection<InternetAddress> cc = new LinkedList<>();

	// 첨부 파일
	private Collection<Attachment> file = new LinkedList<>();

	// 제목
	private final String subject;

	// 내용
	private String text;

	public EmailTransmissionProperty(final InternetAddress sender, final String subject) {
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
	public Collection<Attachment> getFile() {

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
	 * @since 2016. 4. 23. 오후 6:27:53
	 */
	public void setFile(final Attachment file) {

		this.file.add(file);
	}

	/**
	 * file를 초기화합니다.
	 *
	 * @see {@link #file}
	 * @author jeonghyun.kum
	 * @since 2016. 4. 23. 오후 6:27:53
	 */
	public void setFile(final Collection<Attachment> file) {

		this.file = new ArrayList<>(file);
	}

	/**
	 * text를 초기화합니다.
	 *
	 * @see {@link #text}
	 * @author jeonghyun.kum
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
	 * @since 2016. 4. 23. 오후 6:27:53
	 */
	public void setTo(final InternetAddress to) {

		this.to.add(to);
	}
}
