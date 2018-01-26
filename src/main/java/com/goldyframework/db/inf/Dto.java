/**
 * FileName : {@link Dto}.java
 * Created : 2017. 6. 30. 오후 8:52:03
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Formal Works Inc. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 (주)포멀웍스에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.db.inf;

import java.util.Date;

/**
 * Value Object 인터페이스
 *
 * @author 2017. 6. 30. 오후 8:52:03 jeong
 */
public interface Dto extends PrimaryDto {
	
	/**
	 * 두 객체가 동일한지 검사합니다.
	 * 필드가 동일할경우 동일한 객체로 판별합니다.
	 * 
	 * @author 2017. 8. 19. 오후 5:41:47 jeong
	 * @param obj
	 *            비교대상 객체
	 */
	@Override
	boolean equals(Object obj);
	
	Date getCreatedTime();
	
	Integer getKey();
	
}
