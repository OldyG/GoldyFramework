/**
 * FileName : {@link EncryptionTest}.java
 * Created : 2017. 4. 10.
 * Author : jeong
 * Summary :
 * Copyright (C) 2018 Formal Works Inc. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.encrytion;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import com.goldyframework.encryption.Encryption;
import com.goldyframework.encryption.exception.EncryptionException;

@SuppressWarnings("nls")
@RunWith(MockitoJUnitRunner.class)
public class EncryptionTest {
	
	@Spy
	private final Encryption targetSpy = new Encryption();
	
	/**
	 * Test method for
	 * {@link com.goldy.common.util.encrytion.Encryption#decrypt(java.lang.String)}.
	 *
	 * @throws EncryptionException
	 */
	@Test
	public void testDecrypt() throws EncryptionException {
		
		final String decrypt = this.targetSpy.decrypt("gLXeSjXtIsbFwIq1Z/Am3g==");
		Assert.assertEquals("복호화 시험", "금정현", decrypt);
	}
	
	@Test
	public void testDiff() throws EncryptionException {
		
		final String encrypt = this.targetSpy.encrypt("abc");
		final String decrypt = this.targetSpy.decrypt(encrypt);
		Assert.assertEquals("암호화 후 복호화 시험", "abc", decrypt);
	}
	
	/**
	 * Test method for
	 * {@link com.goldy.common.util.encrytion.Encryption#encrypt(java.lang.String)}.
	 *
	 * @throws EncryptionException
	 */
	@Test
	public void testEncrypt() throws EncryptionException {
		
		final String encrypt = this.targetSpy.encrypt("금정현");
		
		Assert.assertEquals("암호화 시험", "gLXeSjXtIsbFwIq1Z/Am3g==", encrypt);
	}
	
}
