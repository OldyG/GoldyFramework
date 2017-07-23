/**
 * FileName : {@link ResourceChannel}.java
 * Created : 2017. 4. 10.
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Goldy Project. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.jsp.taglib;

import java.io.File;
import java.text.MessageFormat;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;

import javax.servlet.ServletContext;

import org.apache.commons.io.FilenameUtils;

public class ResourceChannel {
	
	private static ServletContext SEVLET_CONTEXT;
	
	private static String RESOURCE_BASE_PATH;
	
	private static String VIEWS_BASE_PATH;
	
	private static String PROJECT_BASE_PATH;
	
	private static String extractProjectBasePath() {
		
		final String allPath = SEVLET_CONTEXT.getRealPath(""); //$NON-NLS-1$
		final String projectPath = new File("").getAbsolutePath(); //$NON-NLS-1$
		final String relativePath = allPath.replace(projectPath, ""); //$NON-NLS-1$
		final String javaPath = relativePath.replaceAll(Matcher.quoteReplacement("\\"), "/"); //$NON-NLS-1$//$NON-NLS-2$
		return javaPath.substring(1, javaPath.length() - 1);
	}
	
	private static void initialize() {
		
		try {
			PROJECT_BASE_PATH = extractProjectBasePath();
			RESOURCE_BASE_PATH = "/resources/custom/views/"; //$NON-NLS-1$
			VIEWS_BASE_PATH = "/WEB-INF/views/"; //$NON-NLS-1$
		} catch (final Exception e) {
			// do nothing
		}
	}
	
	public static void load(final ServletContext servletContext) {
		
		SEVLET_CONTEXT = servletContext;
		initialize();
	}
	
	private final String viewPath;
	
	/**
	 * {@link ResourceChannel} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author jeong
	 * @since 2017. 3. 2. 오후 8:58:10
	 * @param viewPath
	 */
	public ResourceChannel(final String viewPath) {
		
		this.viewPath = viewPath;
	}
	
	public List<File> getMachedResources() {
		
		final List<File> result = new LinkedList<>();
		if (this.viewPath == null) {
			return result;
		}
		
		final String viewDirectory = FilenameUtils.getFullPath(this.viewPath);
		final String resourceDirectory = viewDirectory.replace(VIEWS_BASE_PATH, RESOURCE_BASE_PATH);
		
		final File resourceDirectoryFile = new File(PROJECT_BASE_PATH + resourceDirectory);
		
		if (resourceDirectoryFile.exists() == false) {
			return result;
		}
		
		final File[] listFiles = resourceDirectoryFile.listFiles();
		
		if (listFiles == null) {
			return Collections.emptyList();
		}
		for (final File file : listFiles) {
			if (file.isFile()) {
				result.add(file);
			}
		}
		return result;
	}
	
	public String getTagString(final File file) {
		
		final String extension = FilenameUtils.getExtension(file.getPath());
		final String path = file.getPath().replaceAll(Matcher.quoteReplacement("\\"), "/") //$NON-NLS-1$//$NON-NLS-2$
			.replaceAll("src/main/webapp/", "/");  //$NON-NLS-1$//$NON-NLS-2$
		
		switch (extension) {
			case "css": //$NON-NLS-1$
				return MessageFormat.format("<link rel=\"stylesheet\" type=\"text/css\" href=\"{0}\" />", path); //$NON-NLS-1$
			case "js": //$NON-NLS-1$
				return MessageFormat.format("<script type=\"text/javascript\" src=\"{0}\"></script>", path); //$NON-NLS-1$
				
			default:
				return null;
		}
	}
	
}
