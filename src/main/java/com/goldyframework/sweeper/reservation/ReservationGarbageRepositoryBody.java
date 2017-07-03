/**
 * FileName : {@link ReservationGarbageRepositoryBody}.java
 * Created : 2017. 4. 10.
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Goldy Project. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.sweeper.reservation;

import java.io.File;

import org.apache.commons.io.FilenameUtils;

import com.goldyframework.repository.AbstractRepositoryBody;
import com.goldyframework.repository.filenaming.FileNamingType;
import com.goldyframework.utils.NullGtils;

/**
 * 예약 쓰레기 저장소 바디
 *
 * @author 2017. 6. 18. 오후 2:27:10 jeong
 */
public class ReservationGarbageRepositoryBody extends AbstractRepositoryBody {
	
	private final File garbageDirectory;
	
	/**
	 * {@link ReservationGarbageRepositoryBody} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author jeonghyun.kum
	 * @param garbageDirectory
	 * @since 2016. 6. 14. 오후 2:16:44
	 */
	public ReservationGarbageRepositoryBody(final File garbageDirectory) {
		this.garbageDirectory = NullGtils.throwIfNull(garbageDirectory);
		this.init();
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * @author 2017. 6. 29. 오후 10:33:42 jeong
	 */
	@Override
	protected String getBaseName() {
		
		return "Garbage"; //$NON-NLS-1$
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * @author 2017. 6. 29. 오후 10:33:34 jeong
	 */
	@Override
	public String getDownloadName() {
		
		return FilenameUtils.getName(this.getRegisteredFileName());
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * @author 2017. 6. 29. 오후 10:33:46 jeong
	 */
	@Override
	protected String getRegisteredFileName() {
		
		return null;
	}
	
	/**
	 * @author 2017. 6. 18. 오후 3:45:29 jeong
	 */
	private void init() {
		
		super.initialize();
	}
	
	/**
	 * @author 2017. 6. 18. 오후 2:28:39 jeong
	 * @return 기본 확장자
	 */
	@Override
	protected String initialDefaultExtension() {
		
		return "json"; //$NON-NLS-1$
	}
	
	/**
	 * @author 2017. 6. 18. 오후 2:28:48 jeong
	 * @return 디렉토리
	 */
	@Override
	protected String initialDirectory() {
		
		return this.garbageDirectory.getAbsolutePath();
	}
	
	/**
	 * @author 2017. 6. 18. 오후 2:28:58 jeong
	 * @return {@link FileNamingType#MILLISECOND}
	 */
	@Override
	protected FileNamingType initialNamingType() {
		
		return FileNamingType.MILLISECOND;
	}
	
}
