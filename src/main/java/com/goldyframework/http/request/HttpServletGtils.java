/**
 * FileName : {@link HttpServletGtils}.java
 * Created : 2017. 4. 10.
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Goldy Project. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.http.request;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.MessageFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.goldyframework.inspection.ObjectInspection;

/**
 * {@link HttpServletRequest}, {@link HttpServletResponse} 의 공통 작업 목록
 *
 * @author 2017. 6. 18. 오후 1:04:35 jeong
 */
public final class HttpServletGtils {
	
	/**
	 * BaseURL을 추출한다.
	 *
	 * @author 2017. 6. 18. 오후 1:05:49 jeong
	 * @param request
	 *            {@link HttpServletRequest}
	 * @return BaseURL
	 * @throws MalformedURLException
	 *             Thrown to indicate that a malformed URL has occurred. Either no legal protocol could be found in a
	 *             specification string or the string could not be parsed.
	 */
	public static String extractBaseUrl(final HttpServletRequest request) throws MalformedURLException {
		
		ObjectInspection.checkNull(request);
		final URL requestUrl = new URL(request.getRequestURL().toString());
		final String portString;
		if (requestUrl.getPort() == -1) {
			portString = ""; //$NON-NLS-1$
		} else {
			portString = ":" + requestUrl.getPort(); //$NON-NLS-1$
		}
		
		return MessageFormat.format("{0}://{1}{2}{3}/",  //$NON-NLS-1$
			requestUrl.getProtocol(),
			requestUrl.getHost(),
			portString, request.getContextPath());
		
	}
	
	/**
	 * Request 사용자 정보를 반환한다.
	 *
	 * @author 2017. 6. 18. 오후 1:06:11 jeong
	 * @param request
	 *            {@link HttpServletRequest}
	 * @return Request 사용자 정보
	 */
	public static RequestDetail extractRequest(final HttpServletRequest request) {
		
		final RequestDetail info = new RequestDetail();
		info.setRequestUrl(request.getRequestURI() + '?' + request.getQueryString());
		info.setAuthType(request.getAuthType());
		info.setContentType(request.getContentType());
		info.setContextPath(request.getContextPath());
		info.setCookies(request.getCookies());
		info.setLocalAddr(request.getLocalAddr());
		info.setLocalName(request.getLocalName());
		info.setLocalPort(request.getLocalPort());
		info.setMethod(request.getMethod());
		
		return info;
	}
	
	/**
	 * {@link HttpServletGtils} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author jeong
	 * @since 2017. 4. 10. 오후 9:36:27
	 */
	private HttpServletGtils() {
		
		throw new IllegalStateException("Utility class"); //$NON-NLS-1$
	}
}
