/**
 * FileName : ReservationGarbageBinderModel.java
 * Created : 2017. 4. 10.
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Goldy Project. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.sweeper;

class ReservationGarbageBinderModel {
	private Class<ReservationGarbage> target;

	private Object set;

	/**
	 * ReservationGarbageBinderModel 클래스의 새 인스턴스를 초기화 합니다.
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
	public Class<ReservationGarbage> getTarget() {
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
		this.set = set;
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
	@SuppressWarnings("unchecked")
	public void setTarget(final String target) {

		Class<?> reservation;
		try {
			reservation = Class.forName(target);
			this.target = (Class<ReservationGarbage>) reservation;
		} catch (final ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
