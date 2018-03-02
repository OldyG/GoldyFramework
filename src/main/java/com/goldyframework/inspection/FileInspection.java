/**
 * FileName : {@link FileInspection}.java
 * Created : 2017. 6. 18. 오전 1:55:29
 * Author : jeong
 * Summary :
 * Copyright (C) 2018 Formal Works Inc. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.inspection;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.apache.commons.io.FilenameUtils;

import com.goldyframework.inspection.exception.InspectionException;

/**
 * {@link File} 타입 제약
 *
 * @author 2017. 6. 14. 오후 9:08:59 jeong
 */
public class FileInspection {
	
	/**
	 * 파일이 잘못된 파일인지 검사합니다.
	 *
	 * @author 2017. 6. 14. 오후 9:40:56 jeong
	 * @param directory
	 *            검사 대상 파일
	 * @throws ValidateException
	 *             파일이 아닌 디렉토리이거나 존재하지 않은 경우 발생합니다.
	 */
	public static void checkExistsDirectory(File directory) {
		
		ObjectInspection.checkNull(directory);
		StringInspection.checkBlank(directory.getPath());
		StringInspection.checkBlank(directory.getAbsolutePath());
		
		if (directory.exists() == false) {
			throw new InspectionException("존재 하지 않은 파일입니다.");
		}
		
		if (directory.isFile() || (directory.isDirectory() == false)) {
			throw new InspectionException("디렉토리 형식이 아닙니다.");
		}
	}
	
	/**
	 * 파일이 잘못된 파일인지 검사합니다.
	 *
	 * @author 2017. 6. 14. 오후 9:40:56 jeong
	 * @param file
	 *            검사 대상 파일
	 * @throws ValidateException
	 *             파일이 아닌 디렉토리이거나 존재하지 않은 경우 발생합니다.
	 */
	public static void checkExistsFile(File file) {
		
		ObjectInspection.checkNull(file);
		StringInspection.checkBlank(file.getPath());
		StringInspection.checkBlank(file.getAbsolutePath());
		
		if (file.exists() == false) {
			throw new InspectionException("존재 하지 않은 파일입니다.");
		}
		
		if (file.isDirectory() || (file.isFile() == false)) {
			throw new InspectionException("파일 형식이 아닙니다.");
		}
	}
	
	public static void checkExtentionIs(File file, List<String> validExtensions) {
		
		ObjectInspection.checkNull(file);
		ObjectInspection.checkNull(validExtensions);
		String extension = FilenameUtils.getExtension(file.getAbsolutePath()).toLowerCase(Locale.getDefault());
		if (StringInspection.isBlank(extension)) {
			throw new InspectionException("확장자가 없습니다.");
		}
		
		for (String validExtension : validExtensions) {
			if (validExtension.equals(extension)) {
				return;
			}
		}
		throw new InspectionException(extension + "는 허용하는 확장자가 아닙니다.");
	}
	
	public static void checkImageFile(File file) {
		
		checkExtentionIs(file, Arrays.asList("jpg", "jpeg", "png", "gif", "jfif", "tiff", "bmp", "svg"));
	}
	
	public static void checkMsExcelFile(File file) {
		
		checkExtentionIs(file, Arrays.asList("xlsx", "xls", "csv", "xlt", "ods"));
	}
	
	public static void checkMsWordFile(File file) {
		
		checkExtentionIs(file, Arrays.asList("docx", "doc", "doctx", "xlt", "odt"));
		
	}
	
	public static void checkNullOrEmptyFile(File file) {
		
		checkExistsFile(file);
		IntegerInspection.checkBelowZero((int) file.length());
	}
	
	/**
	 * {@link FileInspection} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author jeong
	 * @since 2017. 5. 22. 오후 9:44:57
	 */
	private FileInspection() {
		
		throw new IllegalStateException("Utility class");
	}
}
