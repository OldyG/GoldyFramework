/**
 * FileName : {@link ShortUrlDto}.java
 * Created : 2017. 6. 26. 오후 7:44:19
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Formal Works Inc. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.shorturl.db;

import java.util.Date;

import com.goldyframework.annotaion.ModelClass;
import com.goldyframework.db.inf.Dto;
import com.goldyframework.utils.json.JsonGtils;

/**
 * short_url 테이블 Dto
 *
 * @author 2017. 7. 23. 오후 5:12:57 jeong
 */
@ModelClass
public class ShortUrlDto extends ShortUrlPrimaryDto implements Dto {
	
	/**
	 * URL
	 */
	private String url;
	
	/**
	 * 테이블 키
	 */
	private Integer key;
	
	/**
	 * 레코드 추가시간
	 */
	private Date createdTime;
	
	/**
	 * createdTime를 반환합니다.
	 * 
	 * @return createdTime
	 * @author 2017. 7. 31. 오후 12:53:48 jeong
	 * @see #createdTime
	 */
	public final Date getCreatedTime() {
		
		return this.createdTime;
	}
	
	/**
	 * key를 반환합니다.
	 * 
	 * @return key
	 * @author 2017. 7. 31. 오후 12:53:48 jeong
	 * @see #key
	 */
	@Override
	public final Integer getKey() {
		
		return this.key;
	}
	
	/**
	 * url를 반환합니다.
	 * 
	 * @return url
	 * @author 2018. 2. 25. 오후 9:34:16 jeong
	 * @see {@link #url}
	 */
	public String getUrl() {
		
		return this.url;
	}
	
	/**
	 * createdTime 초기화 합니다.
	 * 
	 * @param createdTime
	 *            초기화 값
	 * @author 2018. 2. 25. 오후 7:44:00 jeong
	 * @see {@link #createdTime}
	 */
	
	public void setCreatedTime(Date createdTime) {
		
		this.createdTime = createdTime;
	}
	
	/**
	 * key 초기화 합니다.
	 * 
	 * @param key
	 *            초기화 값
	 * @author 2018. 2. 25. 오후 7:44:00 jeong
	 * @see {@link #key}
	 */
	
	public void setKey(Integer key) {
		
		this.key = key;
	}
	
	/**
	 * url 초기화 합니다.
	 * 
	 * @param url
	 *            초기화 값
	 * @author 2018. 2. 25. 오후 9:34:16 jeong
	 * @see {@link #url}
	 */
	
	public void setUrl(String url) {
		
		this.url = url;
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
