/**
 * FileName : {@link AbstractReservationGarbage}.java
 * Created : 2017. 4. 10.
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Goldy Project. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.sweeper.reservation;

import java.text.ParseException;
import java.util.Date;

import com.goldyframework.inspection.ObjectInspection;
import com.goldyframework.sweeper.IGarbage;
import com.goldyframework.utils.ShareFunction;
import com.goldyframework.version.DateAdapter;

/**
 * 예약된 쓰레기 가상 클래스
 *
 * @author 2017. 6. 18. 오후 1:57:53 jeong
 */
public abstract class AbstractReservationGarbage implements IGarbage {

	/**
	 * 예약 시간
	 */
	private Date reservationTime;

	/**
	 * {@link AbstractReservationGarbage} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author 2017. 6. 18. 오후 1:58:05 jeong
	 * @param reservationTime
	 *            예약시간
	 */
	protected AbstractReservationGarbage(final Date reservationTime) {
		ObjectInspection.checkNull(reservationTime);
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
	 * @return 검사 결과
	 */
	public boolean isCleaningTarget() {

		final long calculateRemaingTime = ShareFunction.calculateRemaingTime(this.reservationTime);
		return calculateRemaingTime < 0;
	}

	/**
	 * 시험용 쓰레기 여부를 반환한다.
	 *
	 * @author 2017. 6. 18. 오후 1:58:23 jeong
	 * @return true일경우 제거대상이라고 하더라도 실제로 파일을 제거하지 않는다.
	 */
	public abstract boolean isTestGarbage();

	/**
	 * 예약 시간을 초기화한다.
	 *
	 * @author 2017. 6. 18. 오후 1:58:53 jeong
	 * @param stringDate
	 *            예약시간 문자열
	 * @throws ParseException
	 *             unmarshal 예외사항
	 */
	public void setReservationTime(final String stringDate) throws ParseException {

		ObjectInspection.checkNull(stringDate);
		this.reservationTime = new DateAdapter().unmarshal(stringDate);
	}
}
