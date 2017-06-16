/**
 * FileName : ReservationGarbage.java
 * Created : 2017. 4. 10.
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Goldy Project. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.sweeper;

import java.util.Date;

import com.goldyframework.util.ShareFunction;
import com.goldyframework.version.DateAdapter;

public abstract class ReservationGarbage implements IGarbage {

	private Date reservationTime;

	protected ReservationGarbage(final Date reservationTime) {
		this.reservationTime = new Date(reservationTime.getTime());
	}

	/**
	 * reservationTime를 반환합니다.
	 *
	 * @see {@link #reservationTime}
	 * @author jeong
	 * @since 2016. 6. 11. 오후 11:33:45
	 * @return reservationTime
	 */
	public String getReservationTime() {
		return new DateAdapter().marshal(this.reservationTime);
	}

	/**
	 * 해당 파일이 청소 대상인지 검사합니다.
	 *
	 * @author jeong
	 * @since 2016. 6. 12. 오후 1:34:52
	 * @return
	 */
	boolean isCleaningTarget() {
		final long calculateRemaingTime = ShareFunction.calculateRemaingTime(this.reservationTime);
		return calculateRemaingTime < 0 ? true : false;
	}

	protected abstract boolean isTestGarbage();

	public void setReservationTime(final String stringDate) throws Exception {
		this.reservationTime = new DateAdapter().unmarshal(stringDate);
	}
}
