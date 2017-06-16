/**
 * FileName : EmailForm.java
 * Created : 2017. 4. 10.
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Goldy Project. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.email;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmailForm {

	/**
	 * slf4j Logger
	 *
	 * @author jeong
	 * @since 2017. 5. 22. 오후 9:20:02
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(EmailForm.class);
	private static final String DEFAULT_TITLE_NAME = "메일 서비스"; //$NON-NLS-1$

	private static Elements getBodyElements(final Document doc) {
		final Elements body = doc.select(".email_box>.base>.body>.input_area "); //$NON-NLS-1$
		body.empty();
		return body;
	}

	private static Elements getTitleElements(final Document doc) {
		final Elements title = doc.select(".email_box>.base>.header>.input_area"); //$NON-NLS-1$
		title.empty();
		return title;
	}

	private final EmailFormDesignType type;

	private String titleName = EmailForm.DEFAULT_TITLE_NAME;

	private String inputBody;

	/**
	 * EmailContentForm 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author jeonghyun.kum
	 * @param type
	 *            이메일 폼 디자인
	 * @since 2016. 4. 26. 오전 10:06:35
	 */
	public EmailForm(final EmailFormDesignType type) {
		this.type = type;
	}

	/**
	 * 내용에 작성할 html를 설정합니다.
	 *
	 * @author jeonghyun.kum
	 * @since 2016. 4. 26. 오전 11:18:03
	 * @param htmlCode
	 *            내용에 작성할 html를 삽입합니다.
	 */
	public void inputBody(final String htmlCode) {
		this.inputBody = htmlCode;
	}

	/**
	 * 설정한 값들을 적용하여 HTML 태그로 반환합니다.
	 *
	 * @author jeonghyun.kum
	 * @since 2016. 4. 26. 오전 11:19:53
	 * @return 완성된 HTML 테그
	 * @throws IOException
	 *             리소스 파일을 찾을 수 없을 때 발생합니다.
	 */
	public String parse() {
		final File designFile = this.type.getDesignFile();

		try {
			final Document doc = Jsoup.parse(designFile, "UTF-8"); //$NON-NLS-1$
			final Elements title = getTitleElements(doc);
			title.append(this.titleName);
			final Elements body = getBodyElements(doc);
			body.append(this.inputBody);

			return doc.outerHtml();
		} catch (final IOException e) {
			LOGGER.info(e.getMessage(), e);
			return this.inputBody;
		}
	}

	/**
	 * 이메일 타이틀을 정의합니다.
	 * 기본값으로 {@value #DEFAULT_TITLE_NAME}으로 정의되어있습니다.
	 *
	 * @author jeonghyun.kum
	 * @since 2016. 4. 26. 오전 11:21:58
	 * @param titleName
	 *            타이틀 이름
	 */
	public void setTitleName(final String titleName) {
		this.titleName = titleName;
	}

}
