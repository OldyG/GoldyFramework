/**
 * FileName : {@link SpringGtils}.java
 * Created : 2017. 4. 10.
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Goldy Project. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.utils;

import org.springframework.context.ApplicationContext;

import com.goldyframework.does.Because;
import com.goldyframework.does.Does;
import com.goldyframework.technology.ApplicationContextProvider;

/**
 * Spring 관련 공통 작업 목록
 *
 * @author 2017. 6. 18. 오후 2:48:49 jeong
 */
public class SpringGtils {

	/**
	 * {@link SpringGtils} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author jeong
	 * @since 2017. 4. 10. 오후 9:36:42
	 */
	public SpringGtils() {
		super();
	}

	/**
	 * 주어진 클래스에 해당하는 Bean을 반환합니다.
	 * 해당 클래스는 반드시 {@link Component}, {@link Bean} 등으로 정의되어있어야합니다.
	 *
	 * @author 2017. 6. 18. 오후 2:49:05 jeong
	 * @param <T>
	 *            클래스 타입
	 * @param beanClass
	 *            빈 클래스
	 * @return Bean
	 */
	public <T> T getBean(final Class<T> beanClass) {

		Does.notUse(this, Because.WANT_NOT_STATIC_FUNCTION);
		final ApplicationContext context = ApplicationContextProvider.getApplicationContext();
		return context.getBean(beanClass);
	}
}
