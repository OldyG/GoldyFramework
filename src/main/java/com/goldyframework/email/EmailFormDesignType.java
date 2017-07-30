/**
 * FileName : {@link EmailFormDesignType}.java
 * Created : 2017. 4. 10.
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Goldy Project. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.email;

import java.io.File;

import com.goldyframework.annotaion.Doc;
import com.goldyframework.utils.NullGtils;

/**
 * 이메일 폼 디자인 방법
 *
 * @author 2017. 6. 18. 오후 12:51:55 jeong
 */
@Doc("이메일 폼 디자인 방법")
public enum EmailFormDesignType {
	/**
	 * 일반적인 이메일 디자인 타입
	 *
	 * @author jeonghyun.kum
	 * @since 2016. 4. 26. 오전 10:14:56
	 */
	INFOMATION("information.html"), //$NON-NLS-1$
	/**
	 * 경고 이메일 디자인 타입 (미구현)
	 *
	 * @author jeonghyun.kum
	 * @since 2016. 4. 26. 오전 10:14:41
	 */
	@Deprecated()
	WARNNING("warnning.html"); //$NON-NLS-1$
	
	/**
	 * 기본 위치
	 */
	private static final String BASE_LOCATION = "src/main/resources/email/"; //$NON-NLS-1$
	
	/**
	 * 파일 이름
	 */
	private final File designFile;
	
	/**
	 * {@link EmailFormDesignType} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author jeonghyun.kum
	 * @param fileName
	 *            파일 이름
	 * @since 2016. 4. 26. 오전 10:14:13
	 */
	EmailFormDesignType(final String fileName) {
		
		this.designFile = new File(EmailFormDesignType.BASE_LOCATION + NullGtils.throwIfNull(fileName));
	}
	
	/**
	 * designFile를 반환합니다.
	 *
	 * @see {@link #designFile}
	 * @author jeonghyun.kum
	 * @since 2016. 4. 26. 오전 10:17:02
	 * @return designFile
	 */
	public File getDesignFile() {
		
		return this.designFile;
	}
	
}
