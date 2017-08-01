/**
 * FileName : {@link DateBinder}.java
 * Created : 2017. 7. 1. 오후 2:40:58
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Goldy Project. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 (주)Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.db.bind;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.goldyframework.inspection.ObjectInspection;
import com.google.common.base.Throwables;

/**
 * @author 2017. 7. 1. 오후 2:40:58 jeong
 */
public class TimeStampBinder implements IBinder<Date> {
	
	private static final String FORMAT = "yyyy-MM-dd hh:mm:ss"; //$NON-NLS-1$
	
	/**
	 * {@inheritDoc}
	 *
	 * @author 2017. 7. 1. 오후 2:42:54 jeong
	 */
	@Override
	public Date bind(final String value) {
		
		ObjectInspection.checkNull(value);
		try {
			return new SimpleDateFormat(FORMAT).parse(value);
		} catch (final ParseException e) {
			throw Throwables.propagate(e);
		}
	}
	
}
