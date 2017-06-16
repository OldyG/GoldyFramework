/**
 * @author 2017. 6. 16. 오후 6:30:05 jeong
 */
package com.goldyframework;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @author 2017. 6. 16. 오후 6:30:05 jeong
 */
public final class Prop {

	/**
	 * 기본 인코딩
	 */
	public static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

	/**
	 * 기본 포맷
	 */
	public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss"; //$NON-NLS-1$

	/**
	 * {@link Prop} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author 2017. 6. 19. 오후 8:55:43 jeong
	 */
	private Prop() {
		throw new IllegalStateException("Utility class"); //$NON-NLS-1$
	}
}
