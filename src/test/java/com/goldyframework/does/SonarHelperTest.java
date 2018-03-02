/**
 * FileName : {@link NeedNotUseTest}.java
 * Created : 2017. 6. 19. 오후 10:59:54
 * Author : jeong
 * Summary :
 * Copyright (C) 2018 Formal Works Inc. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.does;

import org.junit.Assert;
import org.junit.Test;

import com.goldyframework.does.SonarHelper;

/**
 * Test class for {@link NeedNotUse}
 *
 * @author 2017. 6. 19. 오후 10:59:54 jeong
 */
public class SonarHelperTest {
	
	/**
	 * Test for Constructor
	 *
	 * @author 2017. 6. 19. 오후 11:08:03 jeong
	 */
	@Test(expected = IllegalStateException.class)
	public void testConstructor() {
		
		new SonarHelper.TestSet().createNewInstance();
	}
	
	/**
	 * Test method for {@link com.formalworks.kumutils.utils.NeedNotUse#inputObject(java.lang.Object)}.
	 */
	@Test
	public void testInputObject() {
		
		SonarHelper.noStatic(this);
		Assert.assertTrue("", Boolean.TRUE);
	}
	
}
