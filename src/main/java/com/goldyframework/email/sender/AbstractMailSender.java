/**
 * @author 2017. 6. 18. 오전 1:06:28 jeong
 */
package com.goldyframework.email.sender;

import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * {@link JavaMailSenderImpl} 가상 클래스
 *
 * @author 2017. 6. 18. 오전 1:06:28 jeong
 */
public abstract class AbstractMailSender extends JavaMailSenderImpl {

	/**
	 * {@link AbstractMailSender} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author 2017. 6. 18. 오전 1:07:00 jeong
	 */
	public AbstractMailSender() {
		super();

	}

}
