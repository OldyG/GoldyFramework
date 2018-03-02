/**
 * FileName : {@link ShortUrlController}.java
 * Created : 2018. 2. 25. 오후 7:58:38
 * Author : jeong
 * Summary :
 * Copyright (C) 2018 Formal Works Inc. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.shorturl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ShortUrlController {
	
	private final ShortUrlService shortUrlService;
	
	/**
	 * {@link ShortUrlController} 클래스의 새 인스턴스를 초기화 합니다.
	 * 
	 * @author 2018. 2. 25. 오후 8:18:19 jeong
	 */
	public ShortUrlController(ShortUrlService shortUrlService) {
		
		super();
		this.shortUrlService = shortUrlService;
	}
	
	@RequestMapping(value = "/shorturl/{url}", method = RequestMethod.GET)
	public ModelAndView get(final HttpServletRequest request, final HttpServletResponse response,
		@PathVariable final String url) {
		
		return this.shortUrlService.action(request, response, url);
	}
}
