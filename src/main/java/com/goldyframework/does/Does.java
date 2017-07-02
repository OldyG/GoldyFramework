/**
 * FileName : {@link Does}.java
 * Created : 2017. 6. 19. 오후 9:04:49
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Goldy Project. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 (주)Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.does;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.goldyframework.utils.ITestSet;
import com.google.common.annotations.VisibleForTesting;

/**
 * 함수를 static으로 변형하지 못하도록 도와줍니다.
 *
 * @author 2017. 6. 19. 오후 9:04:49 jeong
 */
public final class Does {

	@VisibleForTesting
	static class TestSet implements ITestSet<Does> {

		/**
		 * {@inheritDoc}
		 *
		 * @author 2017. 6. 19. 오후 11:01:42 jeong
		 */
		@Override
		public Does createNewInstance() {

			return new Does();
		}

	}

	/**
	 * slf4j Logger
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(Does.class);

	/**
	 * 이 함수에 this를 설정하세요
	 * ex) {@link NonStaticFunction#inputThis(this)}
	 *
	 * @author 2017. 6. 19. 오후 9:07:54 jeong
	 * @param inputYourObjectAtHere
	 *            this를 집어넣으세요
	 */
	public static void notUse(final Object obj, final Because because) {

		if (obj == null) {
			LOGGER.trace(because.getMessage());
		} else {
			final String message = "unuse by " + obj.toString(); //$NON-NLS-1$
			LOGGER.trace(message);
		}
	}

	/**
	 * {@link Does} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author 2017. 6. 19. 오후 9:04:49 jeong
	 */
	private Does() {
		throw new IllegalStateException("Utility class"); //$NON-NLS-1$
	}

}
