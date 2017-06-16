/**
 * FileName : {@link ChecksumAnalyser}.java
 * Created : 2017. 4. 10.
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Goldy Project. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.checksum;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.goldyframework.inspection.exception.InspectionException;
import com.goldyframework.util.ClassLoaderUtils;
import com.goldyframework.util.ITestSet;
import com.google.common.annotations.VisibleForTesting;

/**
 * File을 인자로 불러와 각종 {@link Checksum}을 반환하는 도구
 *
 * @author 2017. 6. 18. 오전 10:30:04 jeong
 */
public class ChecksumAnalyser {

	@VisibleForTesting
	static class TestSet implements ITestSet<ChecksumAnalyser> {

		private final File testFile = ClassLoaderUtils.getFile("checksum/Test File.xml"); //$NON-NLS-1$

		/**
		 * {@inheritDoc}
		 *
		 * @author 2017. 6. 19. 오후 6:47:16 jeong
		 */
		@Override
		public ChecksumAnalyser createNewInstance() {

			try {
				return new ChecksumAnalyser(this.testFile);
			} catch (final InspectionException e) {
				LOGGER.error("인스턴스 생성 실패", e); //$NON-NLS-1$
				return null;
			}
		}

	}

	/**
	 * slf4j Logger
	 *
	 * @author jeong
	 * @since 2017. 5. 22. 오후 9:20:02
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(ChecksumAnalyser.class);

	/**
	 * Checksum 생성 대상 파일
	 */
	private final File file;

	/**
	 * {@link ChecksumAnalyser} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author 2017. 6. 18. 오후 6:08:36 jeong
	 */
	@VisibleForTesting
	ChecksumAnalyser() {
		super();
		this.file = null;
	}

	/**
	 * {@link ChecksumAnalyser} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author 2017. 6. 18. 오전 10:32:16 jeong
	 * @param file
	 *            Checksum 생성 대상 파일입니다.
	 */
	public ChecksumAnalyser(final File file) {
		super();
		new CheckSumFileValidator().check(file);
		this.file = file;
	}

	/**
	 * 대상 알고리즘에 해당하다는 체크섬 문자열을 반환합니다.
	 *
	 * @author 2017. 6. 18. 오전 10:33:42 jeong
	 * @param algoritm
	 *            생성 알고리즘
	 * @return 체크섬 문자열
	 */

	@VisibleForTesting
	String analysisCheckSum(final Algorithm algoritm) {

		// Get file input stream for reading the file content
		try (final FileInputStream fis = new FileInputStream(this.file)) {
			final MessageDigest digest = MessageDigest.getInstance(algoritm.getName());

			// Create byte array to read data in chunks
			final byte[] byteArray = new byte[1024];
			int bytesCount = 0;

			// Read file data and update in message digest
			while ((bytesCount = fis.read(byteArray)) != -1) {
				digest.update(byteArray, 0, bytesCount);
			}

			// Get the hash's bytes
			final byte[] byteType = digest.digest();
			return this.convertDigestToString(byteType);
		} catch (final NoSuchAlgorithmException | IOException e) {
			LOGGER.error(MessageFormat.format("체크섬 알고리즘{0} 분석 중 오류 발생", algoritm.getName()), e); //$NON-NLS-1$
			return "FAIL : " + e.getMessage(); //$NON-NLS-1$
		}
	}

	/**
	 * 분석을 진행합니다.
	 *
	 * @author 2017. 6. 18. 오전 10:34:47 jeong
	 * @return {@link Checksum}
	 */
	public Checksum analyze() {

		final Checksum result = new Checksum();
		result.setMd2(this.analysisCheckSum(Algorithm.MD2));
		result.setMd5(this.analysisCheckSum(Algorithm.MD5));
		result.setSha1(this.analysisCheckSum(Algorithm.SHA1));
		result.setSha256(this.analysisCheckSum(Algorithm.SHA256));
		result.setSha384(this.analysisCheckSum(Algorithm.SHA384));
		result.setSha512(this.analysisCheckSum(Algorithm.SHA512));
		return result;
	}

	/**
	 * byte[]를 문자열로 변환한다.
	 *
	 * @author 2017. 6. 18. 오전 10:31:29 jeong
	 * @param digest
	 *            변환 대상 byte[]
	 * @return 결과 문자열
	 */
	@VisibleForTesting
	String convertDigestToString(final byte[] digest) {

		final StringBuilder sb = new StringBuilder();
		for (final byte element : digest) {
			sb.append(Integer.toString((element & 0xff) + 0x100, 16).substring(1));
		}
		return sb.toString();
	}
}
