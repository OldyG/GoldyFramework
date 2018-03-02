/**
 * FileName : {@link TableKeyEncryptionTest}.java
 * Created : 2018. 1. 20. 오후 3:47:17
 * Author : jeong
 * Summary :
 * Copyright (C) 2018 Formal Works Inc. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.encryption;

import org.junit.Assert;
import org.junit.Test;

import com.goldyframework.encryption.TableKeyEncryption;
import com.goldyframework.encryption.exception.EncryptionException;

/**
 * @author 2018. 1. 20. 오후 3:47:17 jeong
 */
public class TableKeyEncryptionTest {
	
	/**
	 * Test method for {@link com.goldyframework.encryption.TableKeyEncryption#encrypt(int)}.
	 */
	@Test
	public void testEncrypt() throws EncryptionException {
		
		TableKeyEncryption target = new TableKeyEncryption();
		
		String encrypt1 = target.encrypt(3);
		String encrypt2 = target.encrypt(3);
		
		Assert.assertNotEquals(encrypt1, encrypt2);
		
		int decrypt1 = target.decrypt(encrypt1);
		int decrypt2 = target.decrypt(encrypt2);
		
		Assert.assertEquals(decrypt1, decrypt2);
	}
	
}
