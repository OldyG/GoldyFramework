/**
 * FileName : {@link ApplicationContextProvider}.java
 * Created : 2017. 4. 10.
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Goldy Project. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.technology;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Interface to be implemented by any object that wishes to be notified
 * of the {@link ApplicationContext} that it runs in.
 *
 * @author 2017. 6. 18. 오후 2:40:17 jeong
 */
@Component
public class ApplicationContextProvider implements ApplicationContextAware {

	/**
	 * Central interface to provide configuration for an application. This is read-only while the application is
	 * running, but may be reloaded if the implementation supports this.
	 */
	private static ApplicationContext context;

	/**
	 * @author 2017. 6. 18. 오후 2:40:43 jeong
	 * @return {@link ApplicationContext}
	 */
	public static synchronized ApplicationContext getApplicationContext() {

		return context;
	}

	/**
	 * {@link ApplicationContextProvider} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author jeong
	 * @since 2017. 4. 10. 오후 9:26:47
	 */
	public ApplicationContextProvider() {
		super();
	}

	/**
	 * {@inheritDoc}
	 *
	 * @author 2017. 6. 18. 오후 2:41:01 jeong
	 */
	@Override
	public synchronized void setApplicationContext(final ApplicationContext ac) {

		context = ac;
	}
}
