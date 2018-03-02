/**
 * FileName : {@link RandomDateGtils}.java
 * Created : 2018. 2. 24. 오후 7:18:57
 * Author : jeong
 * Summary :
 * Copyright (C) 2018 Formal Works Inc. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.random;

import java.security.SecureRandom;
import java.util.Calendar;
import java.util.Date;

public class RandomDateGtils {
	
	public static Date createRandomDate() {
		
		long date = new SecureRandom().nextLong();
		return new Date(date);
	}
	
	/**
	 * Mysql의 DateTime의 범위는 '1970-01-01 00:00:00' ~ '2037-12-31 23:59:59'이다
	 * 
	 * @author 2018. 2. 24. 오후 7:52:19 jeong
	 * @return
	 */
	public static Date createRandomDateLimitDateTime() {
		
		Date randomDate = createRandomDate();
		int randomYear = new SecureRandom().nextInt(2036 - 1970) + 1970;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(randomDate);
		calendar.set(Calendar.YEAR, randomYear);
		return calendar.getTime();
	}
	
}
