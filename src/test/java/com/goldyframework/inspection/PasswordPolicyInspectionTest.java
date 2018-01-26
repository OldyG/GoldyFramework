/**
 * FileName : {@link PasswordPolicyInspectionTest}.java
 * Created : 2017. 4. 10.
 * Author : jeong
 * Summary :
 * Copyright (C) 2018 Formal Works Inc. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.inspection;

import org.junit.Test;

import com.goldyframework.inspection.PasswordPolicyInspection;
import com.goldyframework.inspection.exception.InspectionException;

public class PasswordPolicyInspectionTest {
	
	private final PasswordPolicyInspection PASSWORD_POLICY_VALIDATOR = new PasswordPolicyInspection();
	
	@Test
	public void testAssert1() {
		
		this.PASSWORD_POLICY_VALIDATOR.check("!Abc5agdsd21"); 
	}
	
	@Test
	public void testAssert2() {
		
		this.PASSWORD_POLICY_VALIDATOR.check("abAbzjfj5!@1!"); 
	}
	
	@Test
	public void testAssertCheckContinuedKeyboardString1() {
		
		this.PASSWORD_POLICY_VALIDATOR.checkContinuedKeyboardString("qwe1", 4); 
	}
	
	@Test
	public void testAssertCheckContinuedString1() {
		
		this.PASSWORD_POLICY_VALIDATOR.checkContinuedString("124", 3); 
	}
	
	@Test
	public void testAssertCheckContinuedString2() {
		
		this.PASSWORD_POLICY_VALIDATOR.checkContinuedString("112", 3); 
	}
	
	@Test
	public void testAssertCheckContinuedString3() {
		
		this.PASSWORD_POLICY_VALIDATOR.checkContinuedString("121", 3); 
	}
	
	@Test
	public void testAssertCheckContinuedString4() {
		
		this.PASSWORD_POLICY_VALIDATOR.checkContinuedString("901", 3); 
	}
	
	@Test
	public void testAssertCheckKeyboardSpecialCharacter1() {
		
		this.PASSWORD_POLICY_VALIDATOR.needKeyboardSpecialCharacter("!abc"); 
	}
	
	@Test(expected = InspectionException.class)
	public void testFail1() {
		
		this.PASSWORD_POLICY_VALIDATOR.check("a"); 
	}
	
	@Test(expected = InspectionException.class)
	public void testFail2() {
		
		this.PASSWORD_POLICY_VALIDATOR.check("!abcdefg"); 
	}
	
	@Test(expected = InspectionException.class)
	public void testFail3() {
		
		this.PASSWORD_POLICY_VALIDATOR.check("Abcde1"); 
	}
	
	@Test(expected = InspectionException.class)
	public void testFail4() {
		
		this.PASSWORD_POLICY_VALIDATOR.check("!@#!$!@$"); 
	}
	
	@Test(expected = InspectionException.class)
	public void testFailCheckContinuedKeyboardString1() {
		
		this.PASSWORD_POLICY_VALIDATOR.checkContinuedKeyboardString("qwerty", 4); 
	}
	
	@Test(expected = InspectionException.class)
	public void testFailCheckContinuedKeyboardString2() {
		
		this.PASSWORD_POLICY_VALIDATOR.checkContinuedKeyboardString("!23$5", 4); 
	}
	
	@Test(expected = InspectionException.class)
	public void testFailCheckContinuedKeyboardString3() {
		
		this.PASSWORD_POLICY_VALIDATOR.checkContinuedKeyboardString("QwErT", 4); 
	}
	
	@Test(expected = InspectionException.class)
	public void testFailCheckContinuedString1() {
		
		this.PASSWORD_POLICY_VALIDATOR.checkContinuedString("123", 3); 
	}
	
	@Test(expected = InspectionException.class)
	public void testFailCheckContinuedString2() {
		
		this.PASSWORD_POLICY_VALIDATOR.checkContinuedString("111", 3); 
	}
	
	@Test(expected = InspectionException.class)
	public void testFailCheckContinuedString3() {
		
		this.PASSWORD_POLICY_VALIDATOR.checkContinuedString("1abc1", 3); 
	}
	
	@Test(expected = InspectionException.class)
	public void testFailCheckContinuedString4() {
		
		this.PASSWORD_POLICY_VALIDATOR.checkContinuedString("321", 3); 
	}
	
	@Test(expected = InspectionException.class)
	public void testFailCheckContinuedString5() {
		
		this.PASSWORD_POLICY_VALIDATOR.checkContinuedString("cba", 3); 
	}
	
	@Test(expected = InspectionException.class)
	public void testFailCheckContinuedString6() {
		
		this.PASSWORD_POLICY_VALIDATOR.checkContinuedString("aaa", 3); 
	}
	
	@Test(expected = InspectionException.class)
	public void testFailCheckKeyboardSpecialCharacter1() {
		
		this.PASSWORD_POLICY_VALIDATOR.needKeyboardSpecialCharacter("abc"); 
	}
	
}
