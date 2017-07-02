/**
 * @author 2017. 6. 16. 오후 10:14:08 jeong
 */
package com.goldyframework.http.requestinfo;

import javax.servlet.http.Cookie;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.goldyframework.utils.json.JsonGtils;

/**
 * 요청자의 정보 모델
 *
 * @author 2017. 6. 18. 오후 1:09:39 jeong
 */
public class RequestDetail {
	
	/**
	 * slf4j Logger
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(RequestDetail.class);
	
	/**
	 * 인증 타입
	 */
	private String authType;
	
	/**
	 * Content 타입
	 */
	private String contentType;
	
	/**
	 * Context 경로
	 */
	private String contextPath;
	
	/**
	 * 쿠키 목록
	 */
	private String cookies;
	
	/**
	 * 사용자 IP 주소
	 */
	private String localAddr;
	
	/**
	 * 사용자 포트 주소
	 */
	private int localPort;
	
	/**
	 * 사용자 이름
	 */
	private String localName;
	
	/**
	 * 사용자 메소드
	 */
	private String method;
	
	/**
	 * 요청 URL
	 */
	private String requestUrl;
	
	/**
	 * {@link RequestDetail} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author jeong
	 * @since 2017. 4. 10. 오후 9:36:15
	 */
	public RequestDetail() {
		super();
	}
	
	/**
	 * authType를 반환합니다.
	 *
	 * @return authType
	 * @author 2017. 6. 18. 오후 1:12:55 jeong
	 * @see {@link #authType}
	 */
	public final String getAuthType() {
		
		return this.authType;
	}
	
	/**
	 * contentType를 반환합니다.
	 *
	 * @return contentType
	 * @author 2017. 6. 18. 오후 1:12:55 jeong
	 * @see {@link #contentType}
	 */
	public final String getContentType() {
		
		return this.contentType;
	}
	
	/**
	 * contextPath를 반환합니다.
	 *
	 * @return contextPath
	 * @author 2017. 6. 18. 오후 1:12:55 jeong
	 * @see {@link #contextPath}
	 */
	public final String getContextPath() {
		
		return this.contextPath;
	}
	
	/**
	 * cookies를 반환합니다.
	 *
	 * @return cookies
	 * @author 2017. 6. 18. 오후 1:12:55 jeong
	 * @see {@link #cookies}
	 */
	public final String getCookies() {
		
		return this.cookies;
	}
	
	/**
	 * localAddr를 반환합니다.
	 *
	 * @return localAddr
	 * @author 2017. 6. 18. 오후 1:12:55 jeong
	 * @see {@link #localAddr}
	 */
	public final String getLocalAddr() {
		
		return this.localAddr;
	}
	
	/**
	 * localName를 반환합니다.
	 *
	 * @return localName
	 * @author 2017. 6. 18. 오후 1:12:55 jeong
	 * @see {@link #localName}
	 */
	public final String getLocalName() {
		
		return this.localName;
	}
	
	/**
	 * localPort를 반환합니다.
	 *
	 * @return localPort
	 * @author 2017. 6. 18. 오후 1:12:55 jeong
	 * @see {@link #localPort}
	 */
	public final int getLocalPort() {
		
		return this.localPort;
	}
	
	/**
	 * method를 반환합니다.
	 *
	 * @return method
	 * @author 2017. 6. 18. 오후 1:12:55 jeong
	 * @see {@link #method}
	 */
	public final String getMethod() {
		
		return this.method;
	}
	
	/**
	 * requestUrl를 반환합니다.
	 *
	 * @return requestUrl
	 * @author 2017. 6. 18. 오후 1:12:55 jeong
	 * @see {@link #requestUrl}
	 */
	public final String getRequestUrl() {
		
		return this.requestUrl;
	}
	
	/**
	 * authType 초기화 합니다.
	 *
	 * @param authType
	 *            초기화 값
	 * @author 2017. 6. 18. 오후 1:12:55 jeong
	 * @see {@link #authType}
	 */
	
	public final void setAuthType(final String authType) {
		
		this.authType = authType;
	}
	
	/**
	 * contentType 초기화 합니다.
	 *
	 * @param contentType
	 *            초기화 값
	 * @author 2017. 6. 18. 오후 1:12:55 jeong
	 * @see {@link #contentType}
	 */
	
	public final void setContentType(final String contentType) {
		
		this.contentType = contentType;
	}
	
	/**
	 * contextPath 초기화 합니다.
	 *
	 * @param contextPath
	 *            초기화 값
	 * @author 2017. 6. 18. 오후 1:12:55 jeong
	 * @see {@link #contextPath}
	 */
	
	public final void setContextPath(final String contextPath) {
		
		this.contextPath = contextPath;
	}
	
	/**
	 * cookies 초기화 합니다.
	 *
	 * @param cookies
	 *            초기화 값
	 * @author 2017. 6. 18. 오후 1:12:55 jeong
	 * @see {@link #cookies}
	 */
	
	public final void setCookies(final Cookie[] cookies) {
		
		final StringBuilder builder = new StringBuilder();
		for (final Cookie cookie : cookies) {
			builder.append(JsonGtils.toGson(cookie)).append('\n');
		}
		this.cookies = builder.toString();
	}
	
	/**
	 * localAddr 초기화 합니다.
	 *
	 * @param localAddr
	 *            초기화 값
	 * @author 2017. 6. 18. 오후 1:12:55 jeong
	 * @see {@link #localAddr}
	 */
	
	public final void setLocalAddr(final String localAddr) {
		
		this.localAddr = localAddr;
	}
	
	/**
	 * localName 초기화 합니다.
	 *
	 * @param localName
	 *            초기화 값
	 * @author 2017. 6. 18. 오후 1:12:55 jeong
	 * @see {@link #localName}
	 */
	
	public final void setLocalName(final String localName) {
		
		this.localName = localName;
	}
	
	/**
	 * localPort 초기화 합니다.
	 *
	 * @param localPort
	 *            초기화 값
	 * @author 2017. 6. 18. 오후 1:12:55 jeong
	 * @see {@link #localPort}
	 */
	
	public final void setLocalPort(final int localPort) {
		
		this.localPort = localPort;
	}
	
	/**
	 * method 초기화 합니다.
	 *
	 * @param method
	 *            초기화 값
	 * @author 2017. 6. 18. 오후 1:12:55 jeong
	 * @see {@link #method}
	 */
	
	public final void setMethod(final String method) {
		
		this.method = method;
	}
	
	/**
	 * requestUrl 초기화 합니다.
	 *
	 * @param requestUrl
	 *            초기화 값
	 * @author 2017. 6. 18. 오후 1:12:55 jeong
	 * @see {@link #requestUrl}
	 */
	
	public final void setRequestUrl(final String requestUrl) {
		
		this.requestUrl = requestUrl;
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * @author 2017. 6. 18. 오후 1:13:08 jeong
	 */
	@Override
	public String toString() {
		
		try {
			return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(this);
		} catch (final JsonProcessingException e) {
			LOGGER.trace("Jackson을 통한 출력에 실패하여 Gson을 통하여 캐스팅합니다.", e); //$NON-NLS-1$
			return JsonGtils.toGson(this);
		}
		
	}
	
}
