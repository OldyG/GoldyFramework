/**
 * FileName : {@link StandardDao}.java
 * Created : 2017. 4. 10.
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Formal Works Inc. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 (주)포멀웍스에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.db.inf;

/**
 * DAO클래스의 기본구성
 *
 * @author 2017. 7. 23. 오후 5:42:45 jeong
 * @param <DTO>
 *            Dto 타입
 * @param <PDTO>
 *            Primary Dto 타입
 */
public interface StandardDao<DTO extends Dto, PDTO extends PrimaryDto> extends DefaultDao<DTO> {
	
	/**
	 * 주어진 key를 가진 데이터를 제거합니다.
	 *
	 * @author 2017. 7. 3. 오후 10:22:45 jeong
	 * @param primary
	 *            제거 대상 프라이머리
	 */
	void delete(PDTO primary);
	
	/**
	 * 주어진 primary DTO을 통하여 DTO로 전환합니다.
	 *
	 * @author 2017. 7. 29. 오후 12:30:59 jeong
	 * @param primary
	 *            레코드 primary
	 * @return Dto
	 */
	DTO select(final PDTO primary);
	
}
