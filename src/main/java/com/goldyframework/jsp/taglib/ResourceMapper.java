/**
 * FileName : {@link ResourceMapper}.java
 * Created : 2017. 4. 10.
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Goldy Project. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.jsp.taglib;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResourceMapper extends GoldyTag {

	/**
	 * slf4j Logger
	 *
	 * @author jeong
	 * @since 2017. 5. 22. 오후 9:20:02
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(ResourceMapper.class);

	private MapperMode mode = MapperMode.AUTO;

	private String pattern = "*"; //$NON-NLS-1$

	/**
	 * {@link ResourceMapper} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author jeong
	 * @since 2017. 4. 10. 오후 9:11:10
	 */
	public ResourceMapper() {
		super();
	}

	/**
	 * {@inheritDoc}
	 *
	 * @author 2017. 6. 29. 오후 10:26:54 jeong
	 */
	@Override
	protected void doTag() throws IOException {

		switch (this.mode) {
			case AUTO:
				this.writeResourceAutoMap();
				break;
			case RELATIVE:
				break;
			default:
				break;

		}
	}

	/**
	 * dir를 반환합니다.
	 *
	 * @see {@link #mode}
	 * @author jeong
	 * @since 2017. 3. 1. 오후 3:05:28
	 * @return mode
	 */
	public String getMode() {

		return this.mode.name();
	}

	/**
	 * pattern를 반환합니다.
	 *
	 * @see {@link #pattern}
	 * @author jeong
	 * @since 2017. 3. 2. 오후 8:55:16
	 * @return pattern
	 */
	public String getPattern() {

		return this.pattern;
	}

	/**
	 * mode 초기화 합니다.
	 *
	 * @see {@link #mode}
	 * @author jeong
	 * @since 2017. 3. 1. 오후 3:05:28
	 * @param mode
	 *            초기화 값
	 */

	public void setMode(final String mode) {

		this.mode = MapperMode.lookUp(mode);
	}

	/**
	 * pattern 초기화 합니다.
	 *
	 * @see {@link #pattern}
	 * @author jeong
	 * @since 2017. 3. 2. 오후 8:55:16
	 * @param pattern
	 *            초기화 값
	 */

	public void setPattern(final String pattern) {

		this.pattern = pattern.replace(".", "\\.").replace("*", ".*"); //$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$//$NON-NLS-4$
	}

	private void writeResourceAutoMap() throws IOException {

		final ResourceChannel resourceChannel = new ResourceChannel(this.getCurrentPath());
		final List<File> matchedResource = resourceChannel.getMachedResources();

		for (final File file : matchedResource) {
			final String name = FilenameUtils.getName(file.getPath());

			final boolean matched = name.matches("^" + this.pattern + "$"); //$NON-NLS-1$//$NON-NLS-2$

			LOGGER.trace(MessageFormat.format("정의된 패턴 [{0}]을 [{1}]과 비교 : {2}", this.pattern, name, matched)); //$NON-NLS-1$

			if (matched) {
				final String tagString = resourceChannel.getTagString(file);
				this.pageContext.getOut().write(tagString);
			}
		}

	}

}
