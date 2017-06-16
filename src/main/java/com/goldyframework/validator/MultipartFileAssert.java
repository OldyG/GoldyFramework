package com.goldyframework.validator;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * {@link MultipartFile} 타입 제약
 *
 * @author 2017. 6. 14. 오후 9:09:52 jeong
 */
public class MultipartFileAssert {

	/**
	 * 빈 파일이 검사합니다.
	 *
	 * @author 2017. 6. 14. 오후 10:00:47 jeong
	 * @param mpFile
	 *            검사대상 파일
	 * @throws ValidateException
	 *             빈 파일일 경우 발생합니다.
	 */
	public static void checkEmptyFile(final MultipartFile mpFile) throws ValidateException {

		ObjectAssert.checkNull(mpFile);

		if (mpFile.isEmpty()) {
			throw new ValidateException("빈 파일입니다."); //$NON-NLS-1$
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
	public static void checkExtentionIs(final MultipartFile target, final String... validExtentions)
		throws ValidateException {

		ObjectAssert.checkNull(target);
		ObjectAssert.checkNull(validExtentions);

		final String originalFilename = target.getOriginalFilename();
		final String extention = extractExtention(originalFilename);

		for (final String String : validExtentions) {
			if (extention.toLowerCase().equals(String.toLowerCase())) {
				return;
			}
		}
		throw new ValidateException(validExtentions + "가 아닙니다."); //$NON-NLS-1$
	}

	/**
	 * 파일이 올바른 파일인지 검사합니다.
	 * 다음 상황에 예외를 발생합니다.
	 * - 파일 사이즈가 0인 경우
	 * - 파일 이름이 없는 경우
	 * - 확장자를 제외한 파일이름이 없는 경우
	 *
	 * @author 2017. 6. 14. 오후 10:03:56 jeong
	 * @param multipartFile
	 *            검사대상 파일
	 * @throws ValidateException
	 *             파일 사이즈가 0이거나 이름이 없는 경우 발생합니다.
	 */
	public static void checkNullOrEmptyMultipartFile(final MultipartFile multipartFile) throws ValidateException {

		ObjectAssert.checkNull(multipartFile);
		StringAssert.checkNullOrEmptyString(multipartFile.getName());

		try {
			IntegerAssert.checkBelowZero(Math.round(multipartFile.getSize()));
		} catch (final ValidateException e) {
			throw new ValidateException("파일 사이즈가 0입니다.", e); //$NON-NLS-1$
		}

		try {
			StringAssert.checkNullOrEmptyString(multipartFile.getOriginalFilename());
		} catch (final ValidateException e) {
			throw new ValidateException("파일 이름이 없습니다.", e); //$NON-NLS-1$
		}

		try {
			final String originalFilename = multipartFile.getOriginalFilename();
			StringAssert.checkNullOrEmptyString(FilenameUtils.getBaseName(originalFilename));
		} catch (final ValidateException e) {
			throw new ValidateException("확장자를 제외한 파일이름이 비어있습니다.", e); //$NON-NLS-1$
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
	public static String extractExtention(final String fileName) throws ValidateException {

		ObjectAssert.checkNull(fileName);

		final int dotIndex = fileName.lastIndexOf("."); //$NON-NLS-1$
		return fileName.substring(dotIndex + 1);
	}

	/**
	 * GoldyAssert.MultiPartFileAssert 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author jeong
	 * @since 2017. 5. 22. 오후 9:45:06
	 */
	public MultipartFileAssert() {
		super();
	}

}