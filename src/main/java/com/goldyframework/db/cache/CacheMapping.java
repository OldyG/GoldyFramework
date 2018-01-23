/**
 * FileName : {@link CacheMapping}.java
 * Created : 2017. 7. 15. 오후 7:05:29
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Formal Works Inc. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 (주)포멀웍스에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.db.cache;

import org.springframework.jdbc.core.RowMapper;

import com.goldyframework.db.bind.AbstractMapping;
import com.goldyframework.db.bind.TimeStampBinder;

/**
 * cache 테이블 매핑정보
 *
 * @author 2017. 7. 23. 오후 5:30:02 jeong
 */
public class CacheMapping extends AbstractMapping<CacheDto> {
	
	/**
	 * Serializable UID
	 */
	private static final long serialVersionUID = 2830145397895780219L;
	
	/**
	 * {@link CacheMapping} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author 2017. 7. 15. 오후 7:06:04 jeong
	 * @param tableName
	 *            테이블 이름
	 */
	public CacheMapping(final String schema, final String tableName) {
		
		super(schema, tableName);
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * @author 2017. 7. 15. 오후 7:06:00 jeong
	 */
	@Override
	protected void initialize() {
		
		super.put(CacheProp.KEY, (rs, column) -> rs.getInt(column));
		super.put(CacheProp.FOREIGN_KEY, (rs, column) -> rs.getInt(column));
		super.put(CacheProp.NAME, (rs, column) -> rs.getString(column));
		super.put(CacheProp.VALUE, (rs, column) -> rs.getString(column));
		super.put(CacheProp.CREATED_TIME, new TimeStampBinder());
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * @author 2017. 7. 15. 오후 7:06:00 jeong
	 */
	@Override
	public RowMapper<CacheDto> rowMapper() {
		
		return (rs, rowNum) -> {
			
			return new CacheDto(
				super.bind(rs, CacheProp.KEY),
				super.bind(rs, CacheProp.FOREIGN_KEY),
				super.bind(rs, CacheProp.NAME),
				super.bind(rs, CacheProp.VALUE),
				super.bind(rs, CacheProp.CREATED_TIME));
		};
	}
	
}
