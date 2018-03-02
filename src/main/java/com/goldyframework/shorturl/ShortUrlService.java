/**
 * FileName : {@link ShortUrlService}.java
 * Created : 2017. 7. 30. 오후 1:39:33
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Formal Works Inc. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.shorturl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.goldyframework.db.exception.DuplicateRecordException;
import com.goldyframework.inspection.ObjectInspection;
import com.goldyframework.inspection.StringInspection;
import com.goldyframework.random.RandomStringGtils;
import com.goldyframework.shorturl.db.ShortUrlDao;
import com.goldyframework.shorturl.db.ShortUrlDto;
import com.goldyframework.shorturl.db.ShortUrlPrimaryDto;
import com.goldyframework.utils.SpringGtils;
import com.google.common.annotations.VisibleForTesting;

@Transactional
public class ShortUrlService {
	
	/**
	 * slf4j Logger
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(ShortUrlService.class);
	
	@Autowired
	private ShortUrlDao shortUrlDao;
	
	public ModelAndView action(final HttpServletRequest request, final HttpServletResponse response, final String url) {
		
		ObjectInspection.checkNull(request);
		ObjectInspection.checkNull(response);
		StringInspection.checkBlank(url);
		
		final ShortUrlDto urlDto = this.shortUrlDao.selectOfUrl(url);
		final String api = urlDto.getApi();
		final ShortUrlApiDelegator apiDelegator = this.getApiDelegator(api);
		
		return apiDelegator.redirect(urlDto.getApi(), urlDto.getData(), request, response);
	}
	
	public void delete(final String shortUrl) {
		
		StringInspection.checkBlank(shortUrl);
		this.shortUrlDao.deleteOfUrl(shortUrl);
		
	}
	
	public String generateShortUrl(String beanName, String data) throws DuplicateRecordException {
		
		ObjectInspection.checkNull(beanName);
		new SpringGtils().getSubBeans(ShortUrlApiDelegator.class);
		
		final ShortUrlDto shortUrlDto = this.insertRandomUrl(beanName, data);
		return shortUrlDto.getUrl();
	}
	
	@VisibleForTesting
	ShortUrlApiDelegator getApiDelegator(final String shortUrlApi) {
		
		ObjectInspection.checkNull(shortUrlApi);
		
		return new SpringGtils().getBean(shortUrlApi);
	}
	
	public String getUrl(String beanName, String data) {
		
		ShortUrlPrimaryDto primary = new ShortUrlPrimaryDto();
		primary.setApi(beanName);
		primary.setData(data);
		return this.shortUrlDao.select(primary).getUrl();
	}
	
	@VisibleForTesting
	ShortUrlDto insertRandomUrl(final String shortUrlApi, String data) throws DuplicateRecordException {
		
		ObjectInspection.checkNull(shortUrlApi);
		
		// 5~15글자 사이의 무작위 문자열 생성
		final String randomString = RandomStringGtils.createRandomString(5, 15);
		final ShortUrlDto dto = new ShortUrlDto();
		dto.setUrl(randomString);
		dto.setApi(shortUrlApi);
		dto.setData(data);
		
		return this.shortUrlDao.insert(dto);
	}
	
}
