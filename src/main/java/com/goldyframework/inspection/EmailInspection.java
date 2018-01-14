/**
 * FileName : {@link EmailInspection}.java
 * Created : 2017. 4. 10.
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Goldy Project. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.inspection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.goldyframework.inspection.exception.InspectionException;

/**
 * Email 주소 검사도구
 *
 * @author 2017. 6. 18. 오전 11:43:08 jeong
 */
public class EmailInspection implements Inspection<String> {
	
	/**
	 * slf4j Logger
	 *
	 * @author jeong
	 * @since 2017. 5. 22. 오후 9:20:02
	 */
	@SuppressWarnings("unused")
	private static final Logger LOGGER = LoggerFactory.getLogger(EmailInspection.class);
	
	private static final String EMAIL_REGEX_STRING = "[\\w\\~\\-\\.]+@[\\w\\~\\-]+(\\.[\\w\\~\\-]+)+"; 
	
	public static final Collection<String> UNSUPPORTED_EMAIL_LIST = new ArrayList<>();
	
	private static void checkEmailRegex(final String target) {
		
		if (Pattern.matches(EmailInspection.EMAIL_REGEX_STRING, target) == false) {
			throw new InspectionException("이메일 형식에 충족하지 않습니다."); 
		}
	}
	
	private static void checkUnsupportedEmail(final String target) {
		
		for (final String emailList : UNSUPPORTED_EMAIL_LIST) {
			if (target.contains(emailList)) {
				throw new InspectionException("죄송합니다. 현재 입력하신 이메일은 지원하지 않습니다."); 
			}
		}
		
	}
	
	/**
	 * {@link EmailInspection} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author jeong
	 * @since 2017. 4. 10. 오후 9:38:30
	 */
	public EmailInspection() {
		
		super();
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * @author 2017. 6. 29. 오후 10:28:50 jeong
	 */
	@Override
	public void check(final String target) {
		
		ObjectInspection.checkNull(target);
		StringInspection.checkPossibleTrim(target);
		StringInspection.checkContinasSemiclone(target);
		StringInspection.checkContainWhiteSpace(target);
		StringInspection.checkContainHangle(target);
		checkEmailRegex(target);
		checkUnsupportedEmail(target);
	}
}
