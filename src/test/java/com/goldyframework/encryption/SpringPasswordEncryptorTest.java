/**
 * FileName : {@link SpringPasswordEncryptorTest}.java
 * Created : 2017. 7. 11. 오후 11:34:15
 * Author : jeong
 * Summary :
 * Copyright (C) 2018 Formal Works Inc. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.encryption;

import org.junit.Assert;
import org.junit.Test;

import com.goldyframework.encryption.SpringPasswordEncryptor;

@SuppressWarnings("nls")
public class SpringPasswordEncryptorTest {
	
	/**
	 * Test method for
	 * {@link com.goldyframework.encryption.SpringPasswordEncryptor#encode(java.lang.CharSequence)}.
	 */
	@Test
	public void testEncode() {
		
		SpringPasswordEncryptor target = new SpringPasswordEncryptor();
		
		String actual1 = target.encode("admin");
		String actual2 = target.encode("admin");
		Assert.assertNotEquals("패스워드 두번 생성 시 서로 미일치 시험", actual1, actual2);
	}
	
	/**
	 * Test method for
	 * {@link com.goldyframework.encryption.SpringPasswordEncryptor#matches(java.lang.CharSequence, java.lang.String)}.
	 */
	@Test
	public void testMatches() {
		
		SpringPasswordEncryptor target = new SpringPasswordEncryptor();
		
		boolean actual1 = target.matches("admin", "yEHxDXtVDw+ue8EPjUORGg$pJWYS6f28odpAXXo5TtTqA");
		Assert.assertTrue("패스워드 암호화 일치 여부", actual1);
	}
	
}
