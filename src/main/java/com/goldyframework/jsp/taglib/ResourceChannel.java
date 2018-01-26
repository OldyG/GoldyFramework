/**
 * FileName : {@link ResourceChannel}.java
 * Created : 2017. 4. 10.
 * Author : jeong
 * Summary :
 * Copyright (C) 2018 Formal Works Inc. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.jsp.taglib;

import java.io.File;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.stream.Collectors;

import javax.servlet.ServletContext;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@PropertySource("classpath:goldyframework.properties")
@Service
public class ResourceChannel {
	
	/**
	 * slf4j Logger
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(ResourceChannel.class);
	
	private final String projectBasePath;
	
	@Value("${resource-channel.resource-base-path}")
	private String resourceBasePath;
	
	@Value("${resource-channel.views-base-path}")
	private String viewsBasePath;
	
	private final ServletContext servletContet;
	
	private final String contextPath;
	
	/**
	 * {@link ResourceChannel} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author jeong
	 * @param resourceBasePath
	 * @param viewsBasePath
	 * @since 2017. 3. 2. 오후 8:58:10
	 * @param viewPath
	 */
	@Autowired
	public ResourceChannel(final ServletContext servletContet) {
		this.servletContet = servletContet;
		this.contextPath = this.servletContet.getContextPath();
		LOGGER.trace("contextPath : " + this.contextPath); 
		this.projectBasePath = this.extractProjectBasePath();
	}
	
	private String extractProjectBasePath() {
		
		final String allPath = this.servletContet.getRealPath(""); 
		
		final String projectPath = new File("").getAbsolutePath(); 
		final String relativePath = allPath.replace(projectPath, ""); 
		final String javaPath = relativePath.replaceAll(Matcher.quoteReplacement("\\"), "/"); 
		return javaPath.substring(1, javaPath.length() - 1);
	}
	
	public List<File> getMachedResources(final String viewPath) {
		
		LOGGER.trace("viewPath : " + viewPath); 
		
		final List<File> result = new LinkedList<>();
		if (viewPath == null) {
			return result;
		}
		final String viewDirectory = FilenameUtils.getFullPath(viewPath);
		LOGGER.trace("viewDirectory : " + viewDirectory); 
		final String resourceDirectory = viewDirectory.replace(this.viewsBasePath, this.resourceBasePath);
		LOGGER.trace("resourceDirectory : " + resourceDirectory); 
		
		final File resourceDirectoryFile = new File(this.projectBasePath + resourceDirectory);
		LOGGER.trace("resourceDirectoryFile : " + resourceDirectoryFile); 
		
		if (resourceDirectoryFile.exists() == false) {
			return result;
		}
		
		final File[] innerFiles = resourceDirectoryFile.listFiles();
		if (LOGGER.isTraceEnabled()) {
			
			for (final File innerFile : innerFiles) {
				LOGGER.trace("innerFiles : " + innerFile); 
			}
			
		}
		
		if (innerFiles == null) {
			return Collections.emptyList();
		}
		
		return new ArrayList<>(Arrays.asList(innerFiles))
			.stream()
			.filter(File::isFile)
			.collect(Collectors.toList());
	}
	
	public String getTagString(final File file) {
		
		final String extension = FilenameUtils.getExtension(file.getPath());
		final String path = file.getPath().replaceAll(Matcher.quoteReplacement("\\"), "/") 
			.replaceAll(this.projectBasePath, this.contextPath);
		
		switch (extension) {
			case "css": 
				return MessageFormat.format("<link rel=\"stylesheet\" type=\"text/css\" href=\"{0}\" />", path); 
			case "js": 
				return MessageFormat.format("<script type=\"text/javascript\" src=\"{0}\"></script>", path); 
				
			default:
				return ""; 
		}
	}
	
	/**
	 * resourceBasePath 초기화 합니다.
	 * 
	 * @param resourceBasePath
	 *            초기화 값
	 * @author 2017. 8. 21. 오전 10:15:44 jeonghyun.kum
	 * @see {@link #resourceBasePath}
	 */
	public void setResourceBasePath(final String resourceBasePath) {
		
		this.resourceBasePath = resourceBasePath;
	}
	
	/**
	 * viewsBasePath 초기화 합니다.
	 * 
	 * @param viewsBasePath
	 *            초기화 값
	 * @author 2017. 8. 21. 오전 10:15:45 jeonghyun.kum
	 * @see {@link #viewsBasePath}
	 */
	public void setViewsBasePath(final String viewsBasePath) {
		
		this.viewsBasePath = viewsBasePath;
	}
	
}
