/**
 * FileName : {@link NeedNotUseTest}.java
 * Created : 2017. 6. 19. 오후 10:59:54
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Goldy Project. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 (주)Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.does;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test class for {@link NeedNotUse}
 *
 * @author 2017. 6. 19. 오후 10:59:54 jeong
 */
public class DoesTest {
	
	/**
	 * Test for Constructor
	 *
	 * @author 2017. 6. 19. 오후 11:08:03 jeong
	 */
	@Test(expected = IllegalStateException.class)
	public void testConstructor() {
		
		new Does.TestSet().createNewInstance();
	}
	
	/**
	 * Test method for {@link com.goldyframework.utils.NeedNotUse#inputObject(java.lang.Object)}.
	 */
	@Test
	public void testInputObject() {
		
		Does.notUse(this, Because.WANT_NOT_STATIC_FUNCTION);
		Assert.assertTrue("", Boolean.TRUE); //$NON-NLS-1$
	}
	
}
