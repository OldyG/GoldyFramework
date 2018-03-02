/**
 * FileName : {@link ShortUrlPrimaryDto}.java
 * Created : 2017. 7. 29. 오후 12:53:25
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Formal Works Inc. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.shorturl.db;

import com.goldyframework.db.inf.PrimaryDto;

/**
 * short_url Primary DTO
 * 
 * @author 2017. 7. 29. 오후 12:53:25 jeong
 */
public class ShortUrlPrimaryDto implements PrimaryDto {
	
	/**
	 * 기능
	 */
	private String api;
	
	/** 데이터 */
	private String data;
	
	/**
	 * api를 반환합니다.
	 * 
	 * @return api
	 * @author 2018. 2. 25. 오후 9:31:47 jeong
	 * @see {@link #api}
	 */
	public String getApi() {
		
		return this.api;
	}
	
	/**
	 * data를 반환합니다.
	 * 
	 * @return data
	 * @author 2018. 2. 25. 오후 9:31:47 jeong
	 * @see {@link #data}
	 */
	public String getData() {
		
		return this.data;
	}
	
	/**
	 * api 초기화 합니다.
	 * 
	 * @param api
	 *            초기화 값
	 * @author 2018. 2. 25. 오후 9:31:47 jeong
	 * @see {@link #api}
	 */
	
	public void setApi(String api) {
		
		this.api = api;
	}
	
	/**
	 * data 초기화 합니다.
	 * 
	 * @param data
	 *            초기화 값
	 * @author 2018. 2. 25. 오후 9:31:47 jeong
	 * @see {@link #data}
	 */
	
	public void setData(String data) {
		
		this.data = data;
	}
	
}