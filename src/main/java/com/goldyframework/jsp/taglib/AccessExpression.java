/**
 * FileName : {@link AccessExpression}.java
 * Created : 2017. 4. 10.
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Goldy Project. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.jsp.taglib;

import java.io.IOException;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.jsp.PageContext;

import org.springframework.context.ApplicationContext;
import org.springframework.core.GenericTypeResolver;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ParseException;
import org.springframework.security.access.expression.ExpressionUtils;
import org.springframework.security.access.expression.SecurityExpressionHandler;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.context.support.SecurityWebApplicationContextUtils;
import org.springframework.util.StringUtils;

public class AccessExpression {

	protected final Authentication authentication;

	private final String access;

	private final PageContext pageContext;

	/**
	 * {@link AccessExpression} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author jeong
	 * @since 2017. 2. 28. 오후 9:53:28
	 * @param access
	 */
	public AccessExpression(final String access, final PageContext pageContext) {

		this.access = access;
		this.pageContext = pageContext;
		this.authentication = SecurityContextHolder.getContext().getAuthentication();
	}

	public boolean authorize() throws IOException {

		boolean isAuthorized;

		if (StringUtils.hasText(this.access)) {
			isAuthorized = this.authorizeUsingAccessExpression();

		} else {
			isAuthorized = false;

		}

		return isAuthorized;
	}

	private boolean authorizeUsingAccessExpression() throws IOException {

		if (SecurityContextHolder.getContext().getAuthentication() == null) {
			return false;
		}

		final SecurityExpressionHandler<FilterInvocation> handler = this.getExpressionHandler();

		Expression accessExpression;
		try {
			accessExpression = handler.getExpressionParser().parseExpression(this.access);

		} catch (final ParseException e) {
			final IOException ioException = new IOException();
			ioException.initCause(e);
			throw ioException;
		}

		return ExpressionUtils.evaluateAsBoolean(accessExpression,
			this.createExpressionEvaluationContext(handler));
	}

	protected EvaluationContext createExpressionEvaluationContext(
		final SecurityExpressionHandler<FilterInvocation> handler) {

		final FilterInvocation f = new FilterInvocation(this.pageContext.getRequest(), this.pageContext.getResponse(),
			new FilterChain() {

				/**
				 * {@inheritDoc}
				 *
				 * @author 2017. 6. 29. 오후 10:26:43 jeong
				 */
				@Override
				public void doFilter(final ServletRequest request, final ServletResponse response) {

					throw new UnsupportedOperationException();
				}
			});

		return handler.createEvaluationContext(SecurityContextHolder.getContext()
			.getAuthentication(), f);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private SecurityExpressionHandler<FilterInvocation> getExpressionHandler() throws IOException {

		final ApplicationContext appContext = SecurityWebApplicationContextUtils
			.findRequiredWebApplicationContext(this.pageContext.getServletContext());
		final Map<String, SecurityExpressionHandler> handlers = appContext
			.getBeansOfType(SecurityExpressionHandler.class);

		for (final SecurityExpressionHandler h : handlers.values()) {
			if (FilterInvocation.class.equals(GenericTypeResolver.resolveTypeArgument(
				h.getClass(), SecurityExpressionHandler.class))) {
				return h;
			}
		}

		throw new IOException(
			"No visible WebSecurityExpressionHandler instance could be found in the application " //$NON-NLS-1$
				+ "context. There must be at least one in order to support expressions in JSP 'authorize' tags."); //$NON-NLS-1$
	}

}
