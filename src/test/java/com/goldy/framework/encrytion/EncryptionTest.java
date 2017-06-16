/**
 * FileName : EncryptionTest.java
 * Created : 2017. 4. 10.
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Goldy Project. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldy.framework.encrytion;

import org.junit.Assert;
import org.junit.Test;

import com.goldyframework.encryption.Encryption;

public class EncryptionTest {
	
	/**
	 * Test method for
	 * {@link com.goldy.common.util.encrytion.Encryption#decrypt(java.lang.String)}.
	 */
	@Test
	public void testDecrypt() {
		
		final String decrypt = Encryption.decrypt("gLXeSjXtIsbFwIq1Z/Am3g=="); //$NON-NLS-1$
		Assert.assertEquals("금정현", decrypt); //$NON-NLS-1$
	}
	
	@Test
	public void testDiff() {
		
		final byte[] encrypt = Encryption.encrypt("abc"); //$NON-NLS-1$
		final String decrypt = Encryption.decrypt(new String(encrypt));
		Assert.assertEquals("abc", decrypt); //$NON-NLS-1$
	}
	
	/**
	 * Test method for
	 * {@link com.goldy.common.util.encrytion.Encryption#encrypt(java.lang.String)}.
	 */
	@Test
	public void testEncrypt() {
		
		final byte[] encrypt = Encryption.encrypt("금정현"); //$NON-NLS-1$
		
		Assert.assertEquals("gLXeSjXtIsbFwIq1Z/Am3g==", new String(encrypt)); //$NON-NLS-1$
	}
	
}
