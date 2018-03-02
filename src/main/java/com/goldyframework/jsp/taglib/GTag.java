/**
 * FileName : {@link GTag}.java
 * Created : 2017. 4. 10.
 * Author : jeong
 * Summary :
 * Copyright (C) 2018 Formal Works Inc. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.jsp.taglib;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;

import org.apache.jasper.runtime.PageContextImpl;
import org.sitemesh.webapp.contentfilter.HttpServletRequestFilterable;
import org.springframework.security.taglibs.TagLibConfig;
import org.springframework.security.taglibs.authz.AbstractAuthorizeTag;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;

public abstract class GTag implements Tag {
	
	private Tag parent;
	
	protected String access;
	
	protected PageContext pageContext;
	
	/**
	 * {@link GTag} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author jeong
	 * @since 2017. 4. 10. 오후 9:10:56
	 */
	public GTag() {
		
		super();
	}
	
	/**
	 * Default processing of the end tag returning EVAL_PAGE.
	 *
	 * @return EVAL_PAGE
	 * @see Tag#doEndTag()
	 */
	@Override
	public int doEndTag() {
		
		return EVAL_PAGE;
	}
	
	/**
	 * Invokes the base class {@link AbstractAuthorizeTag#authorize()} method to decide if
	 * the body of the tag should be skipped or not.
	 *
	 * @return {@link Tag#SKIP_BODY} or {@link Tag#EVAL_BODY_INCLUDE}
	 */
	@Override
	public int doStartTag() throws JspException {
		
		try {
			
			AccessExpression accessExpression;
			if ((this.access == null) || (this.access.length() == 0)) {
				accessExpression = new AccessExpression("permitAll", this.pageContext);
			} else {
				accessExpression = new AccessExpression(this.access, this.pageContext);
			}
			boolean authorize = accessExpression.authorize();
			
			if (authorize) {
				this.doTag();
			}
			
			return TagLibConfig.evalOrSkip(authorize);
			
		} catch (IOException e) {
			throw new JspException(e);
		}
	}
	
	protected abstract void doTag() throws IOException;
	
	protected String getCurrentPath() {
		
		PageContextImpl attribute = (PageContextImpl) this.pageContext
			.getAttribute("javax.servlet.jsp.jspPageContext");
		ServletRequest request = attribute.getRequest();
		
		if (request instanceof SecurityContextHolderAwareRequestWrapper) {
			return ((SecurityContextHolderAwareRequestWrapper) request).getServletPath();
			
		}
		if (request instanceof HttpServletRequestFilterable) {
			return ((HttpServletRequestFilterable) request).getServletPath();
		}
		return null;
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * @author 2017. 6. 29. 오후 10:26:48 jeong
	 */
	@Override
	public Tag getParent() {
		
		return this.parent;
	}
	
	protected ServletRequest getRequest() {
		
		return this.pageContext.getRequest();
	}
	
	protected ServletResponse getResponse() {
		
		return this.pageContext.getResponse();
	}
	
	protected ServletContext getServletContext() {
		
		return this.pageContext.getServletContext();
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * @author 2017. 6. 29. 오후 10:26:50 jeong
	 */
	@Override
	public void release() {
		
		this.parent = null;
	}
	
	public void setAccess(String access) {
		
		this.access = access;
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * @author 2017. 6. 29. 오후 10:26:52 jeong
	 */
	@Override
	public void setPageContext(PageContext pageContext) {
		
		this.pageContext = pageContext;
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * @author 2017. 6. 29. 오후 10:26:53 jeong
	 */
	@Override
	public void setParent(Tag parent) {
		
		this.parent = parent;
	}
	
}
