/**
 * FileName : {@link EnumGtils}.java
 * Created : 2018. 2. 24. 오후 10:53:39
 * Author : jeong
 * Summary :
 * Copyright (C) 2018 Formal Works Inc. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.utils.aenum;

import java.lang.reflect.Field;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.goldyframework.does.SonarHelper;
import com.goldyframework.exception.LogicErrorType;
import com.goldyframework.exception.LogicException;
import com.goldyframework.inspection.ObjectInspection;
import com.goldyframework.inspection.StringInspection;
import com.goldyframework.inspection.exception.InspectionException;
import com.google.common.annotations.VisibleForTesting;

public class EnumGtils<T> {
	
	public static <T> T findEnum(Class<T> enumClass, String value) {
		
		ObjectInspection.checkNull(enumClass);
		StringInspection.checkBlank(value);
		
		EnumGtils<T> core = new EnumGtils<>();
		return core.findEnumForTest(enumClass, value);
	}
	
	public static <T extends Enum<?>> T findEnumForAnyField(Class<T> enumClass, String value) {
		
		ObjectInspection.checkNull(enumClass);
		StringInspection.checkBlank(value);
		
		EnumGtils<T> core = new EnumGtils<>();
		return core.findEnumForAnyFieldForTest(enumClass, value);
	}
	
	public static <T> T findEnumForFieldName(Class<T> enumClass, String fieldName, String fieldValue) {
		
		ObjectInspection.checkNull(enumClass);
		StringInspection.checkBlank(fieldName);
		StringInspection.checkBlank(fieldValue);
		EnumGtils<T> core = new EnumGtils<>();
		return core.findEnumForFieldNameForTest(enumClass, fieldName, fieldValue);
	}
	
	public static <T extends Enum<?>> T findSmart(Class<T> enumClass, String value) {
		
		ObjectInspection.checkNull(enumClass);
		
		// Enum 필드 이름 비교
		
		try {
			return findEnum(enumClass, value);
		} catch (InspectionException e) {
			SonarHelper.unuse(e);
			// 내부 필드 비교
			return findEnumForAnyField(enumClass, value);
		}
		
	}
	
	private List<T> extractEnumConstants(Class<T> enumClass) {
		
		return Arrays.asList(enumClass.getEnumConstants()).stream().filter(Objects::nonNull)
			.collect(Collectors.toList());
	}
	
	@VisibleForTesting
	FieldWrapper extractGlobalField(Class<T> enumClass, String fieldName) {
		
		Field[] declaredFields = enumClass.getDeclaredFields();
		
		for (Field field : declaredFields) {
			if ((field.isEnumConstant() == false) && field.getName().equals(fieldName)) {
				return new FieldWrapper(field);
			}
		}
		throw new InspectionException(
			MessageFormat.format("{0}에서 {1}에 해당하는 필드를 찾지 못했습니다.", enumClass.getSimpleName(), fieldName));
	}
	
	private List<FieldWrapper> extractGlobalFields(Class<T> enumClass) {
		
		Field[] declaredFields = enumClass.getDeclaredFields();
		
		List<FieldWrapper> result = new ArrayList<>();
		for (Field field : declaredFields) {
			if (field.isEnumConstant() == false) {
				result.add(new FieldWrapper(field));
			}
		}
		
		return result;
	}
	
	private List<EnumField<T>> findEnumFields(Class<T> enumClass) {
		
		List<Field> fields = new ArrayList<>();
		Field[] declaredFields = enumClass.getDeclaredFields();
		for (Field field : declaredFields) {
			if (field.isEnumConstant()) {
				fields.add(field);
			}
		}
		
		List<EnumField<T>> result = new ArrayList<>();
		
		List<T> enumConstants = this.extractEnumConstants(enumClass);
		for (T field : enumConstants) {
			if (field == null) {
				throw new LogicException(LogicErrorType.INVALID_DESIGN);
			}
			
			String name = field.toString();
			for (Field df : declaredFields) {
				if (name.equals(df.getName())) {
					EnumField<T> ef = new EnumField<>();
					ef.setField(df);
					ef.setConstant(field);
					result.add(ef);
				}
				
			}
		}
		
		return result;
	}
	
	@VisibleForTesting
	T findEnumForAnyFieldForTest(Class<T> enumClass, String value) {
		
		List<FieldWrapper> globalFields = this.extractGlobalFields(enumClass);
		for (FieldWrapper globalField : globalFields) {
			
			try {
				return findEnumForFieldName(enumClass, globalField.getName(), value);
			} catch (InspectionException e) {
				SonarHelper.unuse(e);
				continue;
			}
			
		}
		throw new InspectionException(
			MessageFormat.format("{0}에서 {1}에 해당하는 값을 찾지 못했습니다.", enumClass.getSimpleName(), value));
	}
	
	@VisibleForTesting
	T findEnumForFieldNameForTest(Class<T> enumClass, String fieldName, String fieldValue) {
		
		List<EnumField<T>> enumFields = this.findEnumFields(enumClass);
		FieldWrapper globalField = this.extractGlobalField(enumClass, fieldName);
		
		boolean changedAccessible = globalField.enableAccessible();
		
		if (globalField.getType().equals(String.class)) {
			for (EnumField<T> enumField : enumFields) {
				try {
					String globalFieldValue = (String) globalField.get(enumField.getConstant());
					if (globalFieldValue.equalsIgnoreCase(fieldValue)) {
						return enumField.getConstant();
					}
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					SonarHelper.unuse(e);
				}
			}
		}
		if (changedAccessible) {
			globalField.disableAccessible();
		}
		
		throw new InspectionException(
			MessageFormat.format("{0}에서 {1}필드의 {2}에 해당하는 값을 찾지 못했습니다.", enumClass.getSimpleName(), fieldName,
				fieldValue));
	}
	
	@VisibleForTesting
	T findEnumForTest(Class<T> enumClass, String value) {
		
		List<T> enumConstants = this.extractEnumConstants(enumClass);
		for (T enumConstant : enumConstants) {
			if (enumConstant.toString().equalsIgnoreCase(value)) {
				return enumConstant;
			}
		}
		throw new InspectionException(
			MessageFormat.format("{0}에서 {1}에 해당하는 값을 찾지 못했습니다.", enumClass.getSimpleName(), value));
	}
}
