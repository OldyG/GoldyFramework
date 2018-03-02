/**
 * FileName : {@link EnumGtilsTest}.java
 * Created : 2018. 2. 24. 오후 11:49:21
 * Author : jeong
 * Summary :
 * Copyright (C) 2018 Formal Works Inc. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.utils.aenum;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import com.goldyframework.inspection.exception.InspectionException;
import com.goldyframework.utils.aenum.EnumGtils;
import com.goldyframework.utils.aenum.FieldWrapper;

public class EnumGtilsTest extends Mockito {
	
	enum TestEnum {
		T1("test"),
		T2("Test2");
		
		private final String value;
		
		TestEnum(String value) {
			
			this.value = value;
		}
	}
	
	@Test(expected = InspectionException.class)
	public void testExtractGlobalField() {
		
		EnumGtils<TestEnum> target = new EnumGtils<>();
		target.extractGlobalField(TestEnum.class, "UNKNOWN");
		
	}
	
	@Test(expected = InspectionException.class)
	public void testFindEnumForFieldName1() {
		
		EnumGtils<TestEnum> target = spy(new EnumGtils<>());
		
		// 매개 변수
		Class<TestEnum> enumClass = TestEnum.class;
		String fieldName = "value";
		String fieldValue = "test";
		
		// 내부 변수
		FieldWrapper globalFieldMock = mock(FieldWrapper.class);
		
		// 액션
		doReturn(globalFieldMock).when(target).extractGlobalField(enumClass, fieldName);
		doReturn(Object.class).when(globalFieldMock).getType();
		doReturn(false).when(globalFieldMock).enableAccessible();
		
		target.findEnumForFieldNameForTest(enumClass, fieldName, fieldValue);
		
	}
	
	@Test(expected = InspectionException.class)
	public void testFindEnumForFieldName2() {
		
		EnumGtils<TestEnum> target = spy(new EnumGtils<>());
		
		// 매개 변수
		Class<TestEnum> enumClass = TestEnum.class;
		String fieldName = "value";
		String fieldValue = "test";
		
		// 내부 변수
		FieldWrapper globalFieldMock = mock(FieldWrapper.class);
		
		// 액션
		doReturn(globalFieldMock).when(target).extractGlobalField(enumClass, fieldName);
		doReturn(Object.class).when(globalFieldMock).getType();
		doReturn(true).when(globalFieldMock).enableAccessible();
		
		target.findEnumForFieldNameForTest(enumClass, fieldName, fieldValue);
		
		verify(globalFieldMock).disableAccessible();
	}
	
	@Test(expected = InspectionException.class)
	public void testFindEnumForFieldName3() throws IllegalArgumentException, IllegalAccessException {
		
		EnumGtils<TestEnum> target = spy(new EnumGtils<>());
		
		// 매개 변수
		Class<TestEnum> enumClass = TestEnum.class;
		String fieldName = "value";
		String fieldValue = "test";
		
		// 내부 변수
		FieldWrapper globalFieldMock = mock(FieldWrapper.class);
		
		// 액션
		doReturn(globalFieldMock).when(target).extractGlobalField(enumClass, fieldName);
		doReturn(String.class).when(globalFieldMock).getType();
		doReturn("T").when(globalFieldMock).get(any());
		
		target.findEnumForFieldNameForTest(enumClass, fieldName, fieldValue);
		
		// 검사
		verify(globalFieldMock, never()).disableAccessible();
	}
	
	@Test(expected = InspectionException.class)
	public void testFindEnumForFieldName4() throws IllegalArgumentException, IllegalAccessException {
		
		EnumGtils<TestEnum> target = spy(new EnumGtils<>());
		
		// 매개 변수
		Class<TestEnum> enumClass = TestEnum.class;
		String fieldName = "value";
		String fieldValue = "test";
		
		// 내부 변수
		FieldWrapper globalFieldMock = mock(FieldWrapper.class);
		
		// 액션
		doReturn(globalFieldMock).when(target).extractGlobalField(enumClass, fieldName);
		doReturn(String.class).when(globalFieldMock).getType();
		doThrow(IllegalArgumentException.class).when(globalFieldMock).get(any());
		
		target.findEnumForFieldNameForTest(enumClass, fieldName, fieldValue);
	}
	
	@Test
	public void testFindOfField() {
		
		TestEnum actual = EnumGtils.findEnumForFieldName(TestEnum.class, "value", "test");
		Assert.assertEquals(actual, TestEnum.T1);
	}
	
	@Test
	public void testFindSmart1() {
		
		TestEnum actual1 = EnumGtils.findSmart(TestEnum.class, "t2");
		Assert.assertEquals(actual1, TestEnum.T2);
		
		TestEnum actual2 = EnumGtils.findSmart(TestEnum.class, "test2");
		Assert.assertEquals(actual2, TestEnum.T2);
	}
	
	@Test(expected = InspectionException.class)
	public void testFindSmart2() {
		
		EnumGtils<TestEnum> target = spy(new EnumGtils<>());
		// 매개 변수
		Class<TestEnum> enumClass = TestEnum.class;
		String value = "str";
		target.findEnumForAnyFieldForTest(enumClass, value);
	}
	
}
