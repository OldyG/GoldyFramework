/**
 * FileName : Encryption.java
 * Created : 2017. 4. 10.
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Goldy Project. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.encryption;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.codec.Base64;

/**
 * 암호화 도구
 *
 * @author 2017. 6. 14. 오후 8:38:03 jeong
 */
public class Encryption {

	private static final byte[] KEY;

	private static final byte[] IV;

	static {
		final String hashCode = "76410acb8e8fba45348fb639b6f4f0524acc4e83a463d0a11316a18d71064791b8776a193720668a7e6227f2fb229d55"; //$NON-NLS-1$

		final byte[] result = new BigInteger(hashCode, 16).toByteArray();
		final int arrayLength = 16;

		KEY = new byte[arrayLength];
		for (int loop = 0; loop < arrayLength; loop++) {
			Encryption.KEY[loop] = result[loop];
		}

		IV = new byte[arrayLength];
		for (int loop = arrayLength; loop < (arrayLength * 2); loop++) {
			Encryption.IV[loop - arrayLength] = result[loop];
		}

	}

	/**
	 * slf4j Logger
	 *
	 * @author jeong
	 * @since 2017. 5. 22. 오후 9:20:02
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(Encryption.class);

	/**
	 * 암호화된 문자열을 복호화합니다.
	 *
	 * @author 2017. 6. 14. 오후 8:38:27 jeong
	 * @param data
	 *            암호화된 문자열
	 * @return 복호화된 문자열
	 */
	public static String decrypt(final String data) {

		try {
			final IvParameterSpec iv = new IvParameterSpec(Encryption.IV);
			final SecretKeySpec skeySpec = new SecretKeySpec(Encryption.KEY, "AES"); //$NON-NLS-1$

			final Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING"); //$NON-NLS-1$
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

			final byte[] original = cipher.doFinal(Base64.decode(data.getBytes()));

			return new String(original, Charset.forName("UTF-8")); //$NON-NLS-1$
		} catch (IllegalBlockSizeException | BadPaddingException | InvalidKeyException
			| InvalidAlgorithmParameterException
			| NoSuchAlgorithmException | NoSuchPaddingException e) {
			LOGGER.error("암호화된 문자열을 복호화하는 중 오류 발생", e); //$NON-NLS-1$
			return null;
		}

	}

	/**
	 * 문자열을 암호화합니다.
	 *
	 * @author 2017. 6. 14. 오후 8:40:07 jeong
	 * @param data
	 *            암호화 대상 문자열
	 * @return 복호화된 문자열
	 */
	public static byte[] encrypt(final String data) {

		try {
			final IvParameterSpec iv = new IvParameterSpec(Encryption.IV);
			final SecretKeySpec skeySpec = new SecretKeySpec(Encryption.KEY, "AES"); //$NON-NLS-1$

			final Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING"); //$NON-NLS-1$
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

			final byte[] encrypted = cipher.doFinal(data.getBytes(Charset.forName("UTF-8"))); //$NON-NLS-1$

			return Base64.encode(encrypted);
		} catch (IllegalBlockSizeException | BadPaddingException | InvalidKeyException
			| InvalidAlgorithmParameterException
			| NoSuchAlgorithmException | NoSuchPaddingException e) {
			LOGGER.error("데이터를 암호화하는 중 오류 발생", e); //$NON-NLS-1$
			return null;
		}

	}

	/**
	 * Encryption 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author jeong
	 * @since 2017. 5. 22. 오후 9:43:46
	 */
	public Encryption() {
		super();
	}
}
