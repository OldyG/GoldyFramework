/**
 * FileName : {@link SpringPasswordEncryptor}.java
 * Created : 2017. 4. 10.
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Goldy Project. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.encryption;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.goldyframework.inspection.ObjectInspection;

/**
 * Spring 패스워드 암호화 도구
 *
 * @author 2017. 6. 18. 오후 12:56:45 jeong
 */
@Service
public class SpringPasswordEncryptor implements PasswordEncoder {
	
	/**
	 * {@link SpringPasswordEncryptor} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author jeong
	 * @since 2017. 4. 10. 오후 9:36:03
	 */
	public SpringPasswordEncryptor() {
		
		super();
	}
	
	/**
	 * 암호화 작업을 수행한다.
	 * {@inheritDoc}
	 *
	 * @author 2017. 6. 18. 오후 12:58:47 jeong
	 */
	@Override
	public String encode(final CharSequence rawPassword) {
		
		ObjectInspection.checkNull(rawPassword);
		return PasswordEncryptor.encode(rawPassword);
	}
	
	/**
	 * 일치한지 여부를 반환한다.
	 * {@inheritDoc}
	 *
	 * @author 2017. 6. 18. 오후 12:58:54 jeong
	 */
	@Override
	public boolean matches(final CharSequence rawPassword, final String encodedPassword) {
		
		ObjectInspection.checkNull(rawPassword);
		ObjectInspection.checkNull(encodedPassword);
		return PasswordEncryptor.matches(rawPassword, encodedPassword);
	}
	
}
