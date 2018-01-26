/**
 * FileName : {@link Cache}.java
 * Created : 2018. 1. 13. 오후 6:38:07
 * Author : jeong
 * Summary :
 * Copyright (C) 2018 Formal Works Inc. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 (주)포멀웍스에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.db.cache;

import java.text.MessageFormat;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.goldyframework.inspection.ObjectInspection;
import com.goldyframework.inspection.StringInspection;
import com.goldyframework.inspection.exception.InspectionException;
import com.google.common.annotations.VisibleForTesting;

/**
 * @author 2018. 1. 13. 오후 6:38:07 jeong
 */
public class CacheMap extends ConcurrentHashMap<String, Object> {

	private final Map<String, Class<?>> definedKeyType = new ConcurrentHashMap<>();

	public void defineKey(final String key, final Class<?> type) {

		StringInspection.checkBlank(key);
		ObjectInspection.checkNull(type);

		if (this.definedKeyType.containsKey(key) && (this.definedKeyType.get(key).equals(type) == false)) {
			throw new InspectionException(key + "는 이미 할당되었습니다.");
		}

		this.definedKeyType.put(key, type);
	}

	/**
	 * definedKeyType를 반환합니다.
	 *
	 * @return definedKeyType
	 * @author 2018. 1. 13. 오후 8:02:42 jeong
	 * @see {@link #definedKeyType}
	 */
	@VisibleForTesting
	Map<String, Class<?>> getDefinedKeyType() {

		return this.definedKeyType;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @author 2018. 1. 13. 오후 6:49:12 jeong
	 */
	@Override
	public Object put(final String key, final Object value) {

		if (this.definedKeyType.containsKey(key)) {
			final Class<?> type = this.definedKeyType.get(key);

			if (type.isInstance(value) == false) {
				final String message = MessageFormat.format("{0}는 [{1}] 할당으로 제한되어있습니다.", key, type.getName());
				throw new InspectionException(message);
			}
		}

		return super.put(key, value);
	}

}
