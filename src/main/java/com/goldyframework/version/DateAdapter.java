/**
 * FileName : DateAdapter.java
 * Created : 2017. 4. 10.
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Goldy Project. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.version;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class DateAdapter extends XmlAdapter<String, Date> {

	private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //$NON-NLS-1$

	/**
	 * DateAdapter 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author jeong
	 * @since 2017. 4. 10. 오후 9:37:34
	 */
	public DateAdapter() {
		super();
	}

	@Override
	public String marshal(final Date date) {
		synchronized (this.dateFormat) {
			return this.dateFormat.format(date);
		}
	}

	@Override
	public Date unmarshal(final String date) throws Exception {
		synchronized (this.dateFormat) {
			return this.dateFormat.parse(date);
		}
	}

}