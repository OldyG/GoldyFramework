/**
 * FileName : {@link ShortUrlMapping}.java
 * Created : 2017. 7. 13. 오후 7:12:53
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Formal Works Inc. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.shorturl.db;

import org.springframework.jdbc.core.RowMapper;

import com.goldyframework.db.bind.AbstractMapping;
import com.goldyframework.db.bind.TimeStampBinder;

/**
 * pin 테이블 매핑정보
 *
 * @author 2017. 7. 13. 오후 7:12:53 jeong
 */
public class ShortUrlMapping extends AbstractMapping<ShortUrlDto> {
	
	/**
	 * Serializable UID
	 */
	private static final long serialVersionUID = -2863351288874438904L;
	
	/**
	 * {@link ShortUrlMapping} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author 2017. 7. 13. 오후 7:13:55 jeong
	 */
	public ShortUrlMapping() {
		
		super("sharedpin", ShortUrlProp.TABLE_NAME);
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * @author 2017. 7. 13. 오후 7:14:05 jeong
	 */
	@Override
	protected void initialize() {
		
		super.put(ShortUrlProp.KEY, (rs, column) -> rs.getInt(column));
		super.put(ShortUrlProp.URL, (rs, column) -> rs.getString(column));
		super.put(ShortUrlProp.API, (rs, column) -> rs.getString(column));
		super.put(ShortUrlProp.DATA, (rs, column) -> rs.getString(column));
		super.put(ShortUrlProp.CREATED_TIME, new TimeStampBinder());
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * @author 2017. 7. 13. 오후 7:14:05 jeong
	 */
	@Override
	public RowMapper<ShortUrlDto> rowMapper() {
		
		return (rs, rowNum) -> {
			ShortUrlDto result = new ShortUrlDto();
			result.setApi(super.bind(rs, ShortUrlProp.API));
			result.setCreatedTime(super.bind(rs, ShortUrlProp.CREATED_TIME));
			result.setKey(super.bind(rs, ShortUrlProp.KEY));
			result.setUrl(super.bind(rs, ShortUrlProp.URL));
			result.setData(super.bind(rs, ShortUrlProp.DATA));
			
			return result;
		};
	}
	
}
