/**
 * FileName : ReservationGarbageRepositoryBody.java
 * Created : 2017. 4. 10.
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Goldy Project. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.sweeper;

import org.apache.commons.io.FilenameUtils;

import com.goldyframework.property.IRepositoryProperty;
import com.goldyframework.repository.AbstractRepositoryBody;
import com.goldyframework.repository.filenaming.FileNamingType;
import com.goldyframework.util.SpringUtil;

public class ReservationGarbageRepositoryBody extends AbstractRepositoryBody {

	private final IRepositoryProperty repositoryProperty;

	/**
	 * ReservationGarbageRepositoryBody 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author jeonghyun.kum
	 * @since 2016. 6. 14. 오후 2:16:44
	 */
	public ReservationGarbageRepositoryBody() {
		this.repositoryProperty = new SpringUtil().getBean(IRepositoryProperty.class);
		this.initial();
	}

	@Override
	protected String getBaseName() {
		return "Garbage"; //$NON-NLS-1$
	}

	@Override
	public String getDownloadName() {
		return FilenameUtils.getName(this.getRegisteredFileName());
	}

	@Override
	protected String getRegisteredFileName() {
		return null;
	}

	@Override
	protected String initialDefaultExtension() {
		return "json"; //$NON-NLS-1$
	}

	@Override
	protected String initialDirectory() {
		return this.repositoryProperty.reservationGarbage();
	}

	@Override
	protected FileNamingType initialNamingType() {
		return FileNamingType.MILLISECOND;
	}

}
