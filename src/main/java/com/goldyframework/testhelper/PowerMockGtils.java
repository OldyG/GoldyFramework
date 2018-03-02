/**
 * FileName : {@link PowerMockGtils}.java
 * Created : 2018. 2. 11. 오후 9:48:41
 * Author : jeong
 * Summary :
 * Copyright (C) 2018 Formal Works Inc. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.testhelper;

import org.apache.commons.lang3.ArrayUtils;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.reflect.Whitebox;

import com.goldyframework.inspection.ObjectInspection;
import com.goldyframework.inspection.StringInspection;

public class PowerMockGtils extends PowerMockito {
	
	/**
	 * example)
	 * 
	 * <pre>
	 * &#64;RunWith(PowerMockRunner.class)
	 * public class ExampleTest extends PowerMockito {
	 * 
	 * 		&#64;Test
	 * 		&#64;PrepareForTest(AnyEnum.class)
	 * 		public void testExample() {
	 * 			AnyEnum any = {@link PowerMockGtils#createNewField}(AnyEnum.class, "ANY_VALUE");
	 * 			// any = "ANY_VALUE"
	 * 		}
	 * }
	 * </pre>
	 * 
	 * @param <T>
	 *            EnumClass
	 */
	public static <T extends Enum<T>> T createNewField(Class<T> enumType, String newName)
		throws Exception {
		
		ObjectInspection.checkNull(enumType);
		StringInspection.checkBlank(newName);
		
		if (enumType.isEnum() == false) {
			throw new IllegalArgumentException("Enum 클래스만 호출 할 수 있습니다.");
		}
		
		T newType = mock(enumType);
		
		T[] originTypes = enumType.getEnumConstants();
		
		Whitebox.setInternalState(newType, "name", newName);
		Whitebox.setInternalState(newType, "ordinal", originTypes.length);
		
		T[] newTypes = ArrayUtils.add(originTypes, newType);
		
		mockStatic(enumType);
		when(enumType, "values").thenReturn(newTypes);
		doReturn(newName).when(newType).toString();
		
		return newType;
	}
}
