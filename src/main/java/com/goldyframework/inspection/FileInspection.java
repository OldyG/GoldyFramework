/**
 * FileName : {@link FileInspection}.java
 * Created : 2017. 6. 18. 오전 1:55:29
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Goldy Project. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 (주)Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.inspection;

import java.io.File;

import com.goldyframework.inspection.exception.InspectionException;

/**
 * {@link File} 타입 제약
 *
 * @author 2017. 6. 14. 오후 9:08:59 jeong
 */
public final class FileInspection {
	
	/**
	 * 파일이 잘못된 파일인지 검사합니다.
	 *
	 * @author 2017. 6. 14. 오후 9:40:56 jeong
	 * @param file
	 *            검사 대상 파일
	 * @throws ValidateException
	 *             파일이 아닌 디렉토리이거나 존재하지 않은 경우 발생합니다.
	 */
	public static void checkIllegalFile(final File file) {
		
		ObjectInspection.checkNull(file);
		StringInspection.checkNullOrEmpty(file.getPath());
		StringInspection.checkNullOrEmpty(file.getAbsolutePath());
		
		if (file.isDirectory()) {
			throw new InspectionException("파일 형식의 주소가 아닙니다."); //$NON-NLS-1$
		}
		
		if (file.exists() == false) {
			throw new InspectionException("존재 하지 않은 파일입니다."); //$NON-NLS-1$
		}
	}
	
	/**
	 * {@link FileInspection} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author jeong
	 * @since 2017. 5. 22. 오후 9:44:57
	 */
	private FileInspection() {
		
		throw new IllegalStateException("Utility class"); //$NON-NLS-1$
	}
}
