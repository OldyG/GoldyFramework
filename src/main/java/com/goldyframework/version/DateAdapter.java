/**
 * FileName : {@link DateAdapter}.java
 * Created : 2017. 4. 10.
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Goldy Project. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.version;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import com.goldyframework.inspection.ObjectInspection;

/**
 * XML 파싱 중 {@link Date} 필드 연결도구
 *
 * @author 2017. 6. 18. 오후 3:02:52 jeong
 */
public class DateAdapter extends XmlAdapter<String, Date> {

	private static final Object LOCK_OBJECT = new Object();

	/**
	 * 날자 포맷
	 */
	private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 

	/**
	 * {@link DateAdapter} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author jeong
	 * @since 2017. 4. 10. 오후 9:37:34
	 */
	public DateAdapter() {

		super();
	}

	/**
	 * {@inheritDoc}
	 *
	 * @author 2017. 6. 18. 오후 2:00:07 jeong
	 */
	@Override
	public String marshal(final Date date) {

		ObjectInspection.checkNull(date);
		synchronized (LOCK_OBJECT) {
			return this.dateFormat.format(date);
		}
	}

	/**
	 * {@inheritDoc}
	 *
	 * @author 2017. 6. 18. 오후 2:00:06 jeong
	 */
	@Override
	public Date unmarshal(final String date) throws ParseException {

		ObjectInspection.checkNull(date);
		synchronized (LOCK_OBJECT) {
			return this.dateFormat.parse(date);
		}
	}

}
