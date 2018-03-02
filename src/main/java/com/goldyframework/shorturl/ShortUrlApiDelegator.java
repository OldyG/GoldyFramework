package com.goldyframework.shorturl;
/**
 * FileName : {@link ShortUrlApiDelegator}.java
 * Created : 2017. 7. 31. 오후 5:05:50
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Formal Works Inc. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

/**
 * @author 2017. 7. 31. 오후 5:05:50 jeong
 */
public interface ShortUrlApiDelegator {
	
	ModelAndView redirect(String url, String data, HttpServletRequest request, HttpServletResponse response);
	
}
