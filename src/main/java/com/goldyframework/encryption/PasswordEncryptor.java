/**
 * FileName : PasswordEncryptor.java
 * Created : 2017. 4. 10.
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Goldy Project. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.encryption;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.Random;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PasswordEncryptor {
	
	/**
	 * Encode Salt Byte Length
	 *
	 * @author jeong
	 * @since 2017. 5. 22. 오후 9:49:02
	 */
	private static final int SALT_BYTE_LENGH = 16;
	
	private static final Base64.Encoder ENC = Base64.getEncoder().withoutPadding();
	
	private static final Base64.Decoder DEC = Base64.getDecoder();
	
	/**
	 * slf4j Logger
	 *
	 * @author jeong
	 * @since 2017. 5. 22. 오후 9:20:02
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(PasswordEncryptor.class);
	
	public static String encode(final CharSequence rawPassword) {
		
		try {
			final Random random = new Random();
			final byte[] saltbytes = new byte[SALT_BYTE_LENGH];
			random.nextBytes(saltbytes);
			final String salt = PasswordEncryptor.ENC.encodeToString(saltbytes);
			
			final String hash = PasswordEncryptor.hashWithSalt(rawPassword, saltbytes);
			
			return salt + '$' + hash;
			
		} catch (final InvalidKeySpecException e) {
			LOGGER.error(e.getMessage(), e);
			return null;
		} catch (final NoSuchAlgorithmException e) {
			LOGGER.error(e.getMessage(), e);
			return null;
		}
	}
	
	private static String hashWithSalt(final CharSequence password, final byte[] saltbytes)
		throws NoSuchAlgorithmException,
		InvalidKeySpecException {
		
		final KeySpec spec = new PBEKeySpec(password.toString().toCharArray(), saltbytes, 65536, 128);
		final SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1"); //$NON-NLS-1$
		final byte[] hashbytes = f.generateSecret(spec).getEncoded();
		final String hash = PasswordEncryptor.ENC.encodeToString(hashbytes);
		return hash;
	}
	
	public static boolean matches(final CharSequence rawPassword, final String encodedPassword) {
		
		final String[] saltAndPass = encodedPassword.split("\\$"); //$NON-NLS-1$
		final byte[] saltBytes = PasswordEncryptor.DEC.decode(saltAndPass[0]);
		// TODO stored format check
		
		try {
			final String hashOfInput = PasswordEncryptor.hashWithSalt(rawPassword, saltBytes);
			return hashOfInput.equals(saltAndPass[1]);
			
		} catch (final NoSuchAlgorithmException e) {
			LOGGER.error(e.getMessage(), e);
			return false;
		} catch (final InvalidKeySpecException e) {
			LOGGER.error(e.getMessage(), e);
			return false;
		}
	}
	
	/**
	 * PasswordEncryptor 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author jeong
	 * @since 2017. 5. 22. 오후 9:42:39
	 */
	public PasswordEncryptor() {
		super();
	}
	
}
