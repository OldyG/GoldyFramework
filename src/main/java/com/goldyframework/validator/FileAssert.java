package com.goldyframework.validator;

import java.io.File;

/**
 * {@link File} 타입 제약
 *
 * @author 2017. 6. 14. 오후 9:08:59 jeong
 */
public class FileAssert {

	/**
	 * 파일이 잘못된 파일인지 검사합니다.
	 *
	 * @author 2017. 6. 14. 오후 9:40:56 jeong
	 * @param file
	 *            검사 대상 파일
	 * @throws ValidateException
	 *             파일이 아닌 디렉토리이거나 존재하지 않은 경우 발생합니다.
	 */
	public static void checkIllegalFile(final File file) throws ValidateException {

		ObjectAssert.checkNull(file);
		StringAssert.checkNullOrEmptyString(file.getPath());
		StringAssert.checkNullOrEmptyString(file.getAbsolutePath());

		if (file.isDirectory()) {
			throw new ValidateException("파일 형식의 주소가 아닙니다."); //$NON-NLS-1$
		}

		if (file.exists() == false) {
			throw new ValidateException("존재 하지 않은 파일입니다."); //$NON-NLS-1$
		}
	}

	/**
	 * GoldyAssert.FileAssert 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author jeong
	 * @since 2017. 5. 22. 오후 9:44:57
	 */
	public FileAssert() {
		super();
	}
}