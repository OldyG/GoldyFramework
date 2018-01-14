/**
 * FileName : {@link ClassLoaderGtils}.java
 * Created : 2017. 6. 18. 오후 5:16:50
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Goldy Project. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 (주)Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.utils;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.goldyframework.inspection.ObjectInspection;
import com.google.common.annotations.VisibleForTesting;

/**
 * @author 2017. 6. 18. 오후 5:16:50 jeong
 */
public final class ClassLoaderGtils {
	
	@VisibleForTesting
	class TestSet implements ITestSet<ClassLoaderGtils> {
		
		/**
		 * {@inheritDoc}
		 *
		 * @author 2017. 6. 19. 오후 11:01:42 jeong
		 */
		@Override
		public ClassLoaderGtils createNewInstance() {
			
			return new ClassLoaderGtils(this);
		}
		
	}
	
	/**
	 * slf4j Logger
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(ClassLoaderGtils.class);
	
	/**
	 * @author 2017. 6. 19. 오후 11:12:31 jeong
	 * @param url
	 * @return
	 * @throws URISyntaxException
	 */
	@VisibleForTesting
	static File createNewFile(final URL url) throws URISyntaxException {
		
		return new File(url.toURI());
	}
	
	/**
	 * @author 2017. 6. 18. 오후 5:18:02 jeong
	 * @param path
	 * @param string
	 * @return
	 */
	public static File getFile(final String path) {
		
		ObjectInspection.checkNull(path);
		final URL url = ClassLoader.getSystemResource(path);
		try {
			return createNewFile(url);
		} catch (final URISyntaxException e) {
			LOGGER.error("발생 할 수 없는 오류 발생", e); 
			return null;
		}
	}
	
	/**
	 * {@link ClassLoaderGtils} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author 2017. 6. 30. 오후 8:22:35 jeong
	 * @param testSet
	 */
	@VisibleForTesting
	ClassLoaderGtils(final TestSet testSet) {
		
		super();
	}
	
}
