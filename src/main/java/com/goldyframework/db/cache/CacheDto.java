/**
 * FileName : {@link CacheDto}.java
 * Created : 2017. 7. 15. 오후 7:05:47
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Formal Works Inc. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 (주)포멀웍스에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.db.cache;

import java.util.Date;

import com.goldyframework.annotaion.ModelClass;
import com.goldyframework.db.inf.Dto;
import com.goldyframework.inspection.ObjectInspection;
import com.goldyframework.utils.json.JsonGtils;

/**
 * cache 테이블 Dto
 *
 * @author 2017. 7. 15. 오후 7:05:47 jeong
 */
@ModelClass
public class CacheDto extends CachePrimaryDto implements Dto {

	/**
	 * 테이블 키
	 */
	private final Integer key;

	/**
	 * Cache 값
	 */
	private final String value;

	/**
	 * 레코드 추가시간
	 */
	private final Date createdTime;

	/**
	 * {@link CacheDto} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author 2017. 7. 30. 오후 8:01:23 jeong
	 * @param key
	 *            테이블 키
	 * @param foreignKey
	 *            참조 테이블 키
	 * @param name
	 *            Cache 이름
	 * @param value
	 *            Cache 값
	 * @param createdTime
	 *            레코드 추가시간
	 */
	public CacheDto(final Integer key, final Integer foreignKey, final String name, final String value,
		final Date createdTime) {

		super(foreignKey, name);
		this.key = key;
		this.value = value;
		this.createdTime = createdTime;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @author 2017. 8. 19. 오후 4:54:15 jeong
	 */
	@Override
	public boolean equals(final Object obj) {

		return ObjectInspection.equalsForFields(this, obj);
	}

	/**
	 * createdDate를 반환합니다.
	 *
	 * @return createdTime
	 * @author 2017. 7. 16. 오후 11:16:35 jeong
	 * @see #createdTime
	 */
	public Date getCreatedTime() {

		return this.createdTime;
	}

	/**
	 * key를 반환합니다.
	 *
	 * @return key
	 * @author 2017. 7. 15. 오후 7:09:24 jeong
	 * @see #key
	 */
	@Override
	public final Integer getKey() {

		return this.key;
	}

	/**
	 * value를 반환합니다.
	 *
	 * @return value
	 * @author 2017. 7. 15. 오후 7:09:24 jeong
	 * @see #value
	 */
	public final String getValue() {

		return this.value;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @author 2017. 8. 19. 오후 6:25:46 jeong
	 */
	@Override
	public String toString() {

		return JsonGtils.toGsonPretty(this);
	}
}
