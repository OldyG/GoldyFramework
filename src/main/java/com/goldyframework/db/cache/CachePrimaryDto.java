/**
 * FileName : {@link CachePrimaryDto}.java
 * Created : 2017. 7. 29. 오후 1:06:30
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Formal Works Inc. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 (주)포멀웍스에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.db.cache;

import com.goldyframework.db.inf.PrimaryDto;

/**
 * cache 테이블의 primary DTO
 * 
 * @author 2017. 7. 29. 오후 1:06:30 jeong
 */
public class CachePrimaryDto implements PrimaryDto {
	
	/**
	 * 참조 테이블 키
	 */
	private final Integer foreignKey;
	
	/**
	 * Cache 이름
	 */
	private final String name;
	
	/**
	 * {@link CachePrimaryDto} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author 2017. 7. 30. 오후 7:59:39 jeong
	 * @param foreignKey
	 *            참조 테이블 키
	 * @param name
	 *            Cache 이름
	 */
	public CachePrimaryDto(final Integer foreignKey, final String name) {
		
		this.foreignKey = foreignKey;
		this.name = name;
	}
	
	/**
	 * userKey를 반환합니다.
	 *
	 * @return foreignKey
	 * @author 2017. 7. 15. 오후 7:09:24 jeong
	 * @see #foreignKey
	 */
	public final Integer getForeignKey() {
		
		return this.foreignKey;
	}
	
	/**
	 * name를 반환합니다.
	 *
	 * @return name
	 * @author 2017. 7. 15. 오후 7:09:24 jeong
	 * @see #name
	 */
	public final String getName() {
		
		return this.name;
	}
	
}