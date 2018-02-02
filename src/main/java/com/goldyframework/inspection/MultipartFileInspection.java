/**
 * FileName : {@link MultipartFileInspection}.java
 * Created : 2017. 6. 18. 오전 1:55:29
 * Author : jeong
 * Summary :
 * Copyright (C) 2018 Formal Works Inc. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 (주)포멀웍스에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.inspection;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import com.goldyframework.inspection.exception.InspectionException;

/**
 * {@link MultipartFile} 타입 제약
 *
 * @author 2017. 6. 14. 오후 9:09:52 jeong
 */
public final class MultipartFileInspection {
	
	public static void checkAboveSize(final MultipartFile target, final long maxSizebyte) {
		
		if (target.getSize() > maxSizebyte) {
			final String messsage = MessageFormat.format("파일 사이즈는 {0}를 넘길 수 없습니다.", maxSizebyte);
			throw new InspectionException(messsage);
		}
		
	}
	
	/**
	 * 파일의 확장자가 정의한 매개변수에 포함되어있지 않은지 검사합니다.
	 *
	 * @author 2017. 6. 14. 오후 10:01:38 jeong
	 * @param target
	 *            검사대상
	 * @param validExtentions
	 *            포함해야할 확장자
	 * @throws ValidateException
	 *             정의한 확장자가 포함하지 않으면 발생합니다.
	 */
	public static void checkExtentionIs(final MultipartFile target, final List<String> validExtentions) {
		
		ObjectInspection.checkNull(target);
		ObjectInspection.checkNull(validExtentions);
		
		final String originalFilename = target.getOriginalFilename();
		final String extention = extractExtention(originalFilename).toLowerCase(Locale.getDefault());
		
		for (final String String : validExtentions) {
			if (String.equalsIgnoreCase(extention)) {
				return;
			}
		}
		throw new InspectionException(validExtentions + "가 아닙니다.");
	}
	
	public static void checkNotImage(final MultipartFile target) {
		
		checkExtentionIs(target, Arrays.asList("jpg", "jpeg", "png", "gif", "jfif", "tiff", "bmp", "svg"));
	}
	
	/**
	 * 파일이 올바른 파일인지 검사합니다.
	 * 다음 상황에 예외를 발생합니다.
	 * - 파일 사이즈가 0인 경우
	 * - 파일 이름이 없는 경우
	 * - 확장자를 제외한 파일이름이 없는 경우
	 *
	 * @author 2017. 6. 14. 오후 10:03:56 jeong
	 * @param target
	 *            검사대상 파일
	 * @throws ValidateException
	 *             파일 사이즈가 0이거나 이름이 없는 경우 발생합니다.
	 */
	public static void checkNullOrEmpty(final MultipartFile target) {
		
		ObjectInspection.checkNull(target);
		StringInspection.checkBlank(target.getName());
		
		try {
			IntegerInspection.checkBelowZero((int) target.getSize());
		} catch (final InspectionException e) {
			throw new InspectionException("파일 사이즈가 0입니다.", e);
		}
		
		try {
			StringInspection.checkBlank(target.getOriginalFilename());
		} catch (final InspectionException e) {
			throw new InspectionException("파일 이름이 없습니다.", e);
		}
		
		try {
			final String originalFilename = target.getOriginalFilename();
			StringInspection.checkBlank(FilenameUtils.getBaseName(originalFilename));
		} catch (final InspectionException e) {
			throw new InspectionException("확장자를 제외한 파일이름이 비어있습니다.", e);
		}
	}
	
	/**
	 * 파일이름에서 확장자만 반환합니다.
	 *
	 * @author 2017. 6. 14. 오후 10:07:24 jeong
	 * @param fileName
	 *            파일 이름
	 * @return 확장자
	 * @throws ValidateException
	 *             null일 경우 발생합니다.
	 */
	private static String extractExtention(final String fileName) {
		
		ObjectInspection.checkNull(fileName);
		
		final int dotIndex = fileName.lastIndexOf('.');
		return fileName.substring(dotIndex + 1);
	}
	
	/**
	 * {@link MultipartFileInspection} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author jeong
	 * @since 2017. 5. 22. 오후 9:45:06
	 */
	private MultipartFileInspection() {
		
		throw new IllegalStateException("Utility class");
	}
	
}
