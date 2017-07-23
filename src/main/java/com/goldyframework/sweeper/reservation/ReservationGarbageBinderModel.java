/**
 * FileName : {@link ReservationGarbageBinderModel}.java
 * Created : 2017. 4. 10.
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Goldy Project. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.sweeper.reservation;

import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.goldyframework.inspection.ObjectInspection;
import com.goldyframework.utils.NullGtils;

/**
 * 바인더 모델
 *
 * @author 2017. 6. 18. 오후 2:05:34 jeong
 */
class ReservationGarbageBinderModel {
	
	/**
	 * slf4j Logger
	 */
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationGarbageBinderModel.class);
	
	@SuppressWarnings({ "unchecked", "squid:S2658", "squid:S1309" })
	private static Class<AbstractReservationGarbage> castingClass(final String target) {
		
		ObjectInspection.checkNull(target);
		try {
			final Class<?> reservation = Class.forName(target);
			return (Class<AbstractReservationGarbage>) reservation;
		} catch (final ClassNotFoundException | LinkageError e) {
			LOGGER.error(MessageFormat.format("{0}캐스팅에 실패하였습니다.", e)); //$NON-NLS-1$
			return null;
		}
	}
	
	/**
	 * 바인더 클래스 {@link AbstractReservationGarbage}
	 */
	private Class<AbstractReservationGarbage> target;
	
	/**
	 * 초기화 값
	 */
	private Object set;
	
	/**
	 * {@link ReservationGarbageBinderModel} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author jeong
	 * @since 2017. 4. 10. 오후 9:34:59
	 */
	public ReservationGarbageBinderModel() {
		
		super();
	}
	
	/**
	 * set를 반환합니다.
	 *
	 * @see {@link #set}
	 * @author jeonghyun.kum
	 * @since 2016. 6. 23. 오후 12:17:00
	 * @return set
	 */
	public Object getSet() {
		
		return this.set;
	}
	
	/**
	 * target를 반환합니다.
	 *
	 * @see {@link #target}
	 * @author jeonghyun.kum
	 * @since 2016. 6. 23. 오전 11:38:17
	 * @return target
	 */
	public Class<AbstractReservationGarbage> getTarget() {
		
		return this.target;
	}
	
	/**
	 * set 초기화 합니다.
	 *
	 * @see {@link #set}
	 * @author jeonghyun.kum
	 * @since 2016. 6. 23. 오후 12:17:00
	 * @param set
	 *            초기화 값
	 */
	public void setSet(final Object set) {
		
		this.set = NullGtils.throwIfNull(set);
	}
	
	/**
	 * target 초기화 합니다.
	 *
	 * @see {@link #target}
	 * @author jeonghyun.kum
	 * @since 2016. 6. 23. 오전 11:38:17
	 * @param target
	 *            초기화 값
	 */
	public void setTarget(final String target) {
		
		ObjectInspection.checkNull(target);
		this.target = ReservationGarbageBinderModel.castingClass(target);
	}
}
