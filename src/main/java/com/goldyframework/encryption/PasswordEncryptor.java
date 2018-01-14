/**
 * FileName : java
 * Created : 2017. 4. 10.
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Goldy Project. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.encryption;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.Random;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.goldyframework.inspection.ObjectInspection;

/**
 * 패스워드 암호화 도구
 *
 * @author 2017. 6. 18. 오후 12:52:59 jeong
 */
public final class PasswordEncryptor {
	
	/**
	 * Encode Salt Byte Length
	 *
	 * @author jeong
	 * @since 2017. 5. 22. 오후 9:49:02
	 */
	private static final int SALT_BYTE_LENGH = 16;
	
	/**
	 * BASE 64 Encoder
	 */
	private static final Base64.Encoder ENCODER = Base64.getEncoder().withoutPadding();
	
	/**
	 * Base 64 Decoder
	 */
	private static final Base64.Decoder DECODER = Base64.getDecoder();
	
	/**
	 * slf4j Logger
	 *
	 * @author jeong
	 * @since 2017. 5. 22. 오후 9:20:02
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(PasswordEncryptor.class);
	
	/**
	 * 패스워드를 암호화 합니다.
	 *
	 * @author 2017. 6. 18. 오후 12:54:20 jeong
	 * @param rawPassword
	 *            기존 패스워드
	 * @return 암호화된 문자열
	 */
	public static String encode(final CharSequence rawPassword) {
		
		ObjectInspection.checkNull(rawPassword);
		try {
			final Random random = new SecureRandom();
			final byte[] saltbytes = new byte[SALT_BYTE_LENGH];
			random.nextBytes(saltbytes);
			final String salt = ENCODER.encodeToString(saltbytes);
			
			final String hash = hashWithSalt(rawPassword, saltbytes);
			
			return salt + '$' + hash;
			
		} catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
			LOGGER.error(e.getMessage(), e);
			return null;
		}
	}
	
	/**
	 * Salt 추가한 Hash 코드를 반환한다.
	 *
	 * @author 2017. 6. 18. 오후 12:54:48 jeong
	 * @param password
	 *            패스워드
	 * @param saltbytes
	 *            Salt 바티으
	 * @return Hash코드
	 * @throws NoSuchAlgorithmException
	 *             This exception is thrown when a particular cryptographic algorithm is requested but is not available
	 *             in the environment.
	 * @throws InvalidKeySpecException
	 *             This is the exception for invalid key specifications.
	 */
	private static String hashWithSalt(final CharSequence password, final byte[] saltbytes)
		throws NoSuchAlgorithmException, InvalidKeySpecException {
		
		final KeySpec spec = new PBEKeySpec(password.toString().toCharArray(), saltbytes, 65_536, 128);
		final SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1"); 
		final byte[] hashbytes = f.generateSecret(spec).getEncoded();
		
		return ENCODER.encodeToString(hashbytes);
	}
	
	/**
	 * 순수 비밀번호와 암호화된 비밀번호가 일치한지 비교합니다.
	 *
	 * @author 2017. 6. 18. 오후 12:56:07 jeong
	 * @param rawPassword
	 *            순수 비밀번호
	 * @param encodedPassword
	 *            암호화된 비밀번호
	 * @return 일치하는 경우 true, 아닌경우 false
	 */
	public static boolean matches(final CharSequence rawPassword, final String encodedPassword) {
		
		ObjectInspection.checkNull(rawPassword);
		ObjectInspection.checkNull(encodedPassword);
		
		final String[] saltAndPass = encodedPassword.split("\\$"); 
		final byte[] saltBytes = DECODER.decode(saltAndPass[0]);
		
		try {
			final String hashOfInput = hashWithSalt(rawPassword, saltBytes);
			return hashOfInput.equals(saltAndPass[1]);
			
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			LOGGER.error(e.getMessage(), e);
			return false;
		}
	}
	
	/**
	 * {@link PasswordEncryptor} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author jeong
	 * @since 2017. 5. 22. 오후 9:42:39
	 */
	private PasswordEncryptor() {
		
		throw new IllegalStateException("Utility class"); 
	}
	
}
