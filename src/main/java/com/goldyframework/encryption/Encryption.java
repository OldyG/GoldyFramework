/**
 * FileName : {@link Encryption}.java
 * Created : 2017. 4. 10.
 * Author : jeong
 * Summary :
 * Copyright (C) 2018 Formal Works Inc. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.encryption;

import java.math.BigInteger;
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

import com.goldyframework.Prop;
import com.goldyframework.does.Because;
import com.goldyframework.does.Does;
import com.goldyframework.encryption.exception.EncryptionException;
import com.goldyframework.inspection.ObjectInspection;
import com.goldyframework.utils.NullGtils;

/**
 * 암호화 도구
 *
 * @author 2017. 6. 14. 오후 8:38:03 jeong
 */
public class Encryption {

	private enum Transformation {
		AES_CBC_PKCS5PADDING("AES/CBC/PKCS5PADDING"),

		DESEDE_ECB_PKCS5PADDING("DESede/ECB/PKCS5Padding"),

		AES_GCM_NOPADDING("AES/GCM/NoPadding");

		private final String value;

		/**
		 * {@link Transformation} 클래스의 새 인스턴스를 초기화 합니다.
		 *
		 * @author 2017. 6. 20. 오후 8:32:14 jeong
		 * @param value
		 */
		Transformation(final String value) {

			this.value = NullGtils.throwIfNull(value);
		}
	}

	private static final Transformation TRANSFORMATION = Transformation.AES_CBC_PKCS5PADDING;

	private static final byte[] HASHCODE = new BigInteger(
		"76410acb8e8fba45348fb639b6f4f0524acc4e83a463d0a11316a18d71064791b8776a193720668a7e6227f2fb229d55", 16)
			.toByteArray();

	/**
	 * slf4j Logger
	 *
	 * @author jeong
	 * @since 2017. 5. 22. 오후 9:20:02
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(Encryption.class);

	/**
	 * {@link Encryption} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author jeong
	 * @since 2017. 5. 22. 오후 9:43:46
	 */
	public Encryption() {

		super();
	}

	private byte[] createIv() {

		Does.notUse(this, Because.WANT_NOT_STATIC_FUNCTION);

		final int arrayLength = 16;
		final byte[] resultIv = new byte[arrayLength];

		for (int loop = arrayLength; loop < (arrayLength * 2); loop++) {
			resultIv[loop - arrayLength] = HASHCODE[loop];
		}

		return resultIv;
	}

	private byte[] createKey() {

		Does.notUse(this, Because.WANT_NOT_STATIC_FUNCTION);

		final int arrayLength = 16;
		final byte[] resultKey = new byte[arrayLength];

		for (int loop = 0; loop < arrayLength; loop++) {
			resultKey[loop] = HASHCODE[loop];
		}
		return resultKey;
	}

	/**
	 * 암호화된 문자열을 복호화합니다.
	 *
	 * @author 2017. 6. 14. 오후 8:38:27 jeong
	 * @param data
	 *            암호화된 문자열
	 * @return 복호화된 문자열
	 * @throws EncryptionException
	 */
	public String decrypt(final String data) throws EncryptionException {

		ObjectInspection.checkNull(data);
		try {
			final IvParameterSpec iv = new IvParameterSpec(this.createIv());
			final SecretKeySpec skeySpec = new SecretKeySpec(this.createKey(), "AES");

			final Cipher cipher = Cipher.getInstance(TRANSFORMATION.value);
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

			final byte[] original = cipher.doFinal(Base64.decode(data.getBytes(Prop.DEFAULT_CHARSET)));

			return new String(original, Prop.DEFAULT_CHARSET);
		} catch (IllegalBlockSizeException | BadPaddingException | InvalidKeyException
			| InvalidAlgorithmParameterException | NoSuchAlgorithmException | NoSuchPaddingException e) {
			LOGGER.error("암호화된 문자열을 복호화하는 중 오류 발생", e);
			throw new EncryptionException(e);
		}

	}

	/**
	 * 문자열을 암호화합니다.
	 *
	 * @author 2017. 6. 14. 오후 8:40:07 jeong
	 * @param data
	 *            암호화 대상 문자열
	 * @return 복호화된 문자열
	 * @throws EncryptionException
	 */
	public String encrypt(final String data) throws EncryptionException {

		ObjectInspection.checkNull(data);

		try {
			final IvParameterSpec iv = new IvParameterSpec(this.createIv());
			final SecretKeySpec skeySpec = new SecretKeySpec(this.createKey(), "AES");

			final Cipher cipher = Cipher.getInstance(TRANSFORMATION.value);
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

			final byte[] encrypted = cipher.doFinal(data.getBytes(Prop.DEFAULT_CHARSET));

			final byte[] encode = Base64.encode(encrypted);

			return new String(encode);
		} catch (IllegalBlockSizeException | BadPaddingException | InvalidKeyException
			| InvalidAlgorithmParameterException | NoSuchAlgorithmException | NoSuchPaddingException e) {
			LOGGER.error("데이터를 암호화하는 중 오류 발생", e);
			throw new EncryptionException(e);
		}

	}
}
