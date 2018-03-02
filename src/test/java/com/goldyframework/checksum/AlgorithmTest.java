/**
 * FileName : {@link AlgorithmTest}.java
 * Created : 2017. 6. 18. 오후 4:54:10
 * Author : jeong
 * Summary :
 * Copyright (C) 2018 Formal Works Inc. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.checksum;

import org.junit.Assert;
import org.junit.Test;

import com.goldyframework.checksum.Algorithm;

/**
 * Tests for {@link Algorithm}
 *
 * @author 2017. 6. 18. 오후 4:54:10 jeong
 */
public class AlgorithmTest {
	
	/**
	 * Test method for {@link com.goldyframework.checksum.Algorithm#getName()}.
	 */
	@Test
	public void testGetName() {
		
		String actual = Algorithm.MD2.getName();
		Assert.assertEquals("", "MD2", actual);
	}
	
}
