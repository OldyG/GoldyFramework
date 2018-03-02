/**
 * FileName : {@link StandardDao}.java
 * Created : 2017. 4. 10.
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Formal Works Inc. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.db.inf;

import java.util.Collection;

import org.eclipse.jdt.annotation.NonNull;

import com.goldyframework.db.exception.DuplicateRecordException;

/**
 * DAO클래스의 기본구성
 *
 * @author 2017. 7. 23. 오후 5:42:45 jeong
 * @param <DTO>
 *            Dto 타입
 */
public interface DefaultDao<DTO extends Dto> {
	
	/**
	 * 주어진 key를 가진 데이터를 제거합니다.
	 *
	 * @author 2017. 7. 3. 오후 10:22:45 jeong
	 * @param key
	 *            제거대상 키
	 */
	void delete(int key);
	
	/**
	 * Dto를 새 데이터로 추가합니다.
	 *
	 * @author 2017. 7. 3. 오후 10:23:17 jeong
	 * @param dto
	 *            추가 vo
	 * @return 추가된 Dto
	 * @throws DuplicateRecordException
	 *             중복 데이터 예외사항
	 */
	@NonNull
	DTO insert(DTO dto) throws DuplicateRecordException;
	
	/**
	 * 주어진 테이블 Unique 키를 가진 데이터를 Dto로 변환하여 가져옵니다.
	 *
	 * @author 2017. 7. 3. 오후 10:24:54 jeong
	 * @param key
	 *            참조 Unique 키
	 * @return Dto
	 */
	@NonNull
	DTO select(int key);
	
	/**
	 * 모든 Dto 목록을 반환합니다.
	 *
	 * @author 2017. 7. 3. 오후 10:24:03 jeong
	 * @return Dto 목록
	 */
	@NonNull
	Collection<DTO> selectAll();
	
	/**
	 * 해당 키를 가진 데이터를 업데이트합니다.
	 *
	 * @author 2017. 7. 23. 오후 5:47:48 jeong
	 * @param key
	 *            레코드 키
	 * @param column
	 *            변경 대상 컬럼
	 * @param value
	 *            업데이트 값
	 */
	void update(int key, String column, Object value);
	
	/**
	 * 해당 키를 가진 데이터를 수정합니다.
	 *
	 * @author 2017. 7. 3. 오후 10:25:37 jeong
	 * @param key
	 *            수정 대상 키
	 * @param dto
	 *            변경하려는 값
	 */
	void updateAll(int key, DTO dto);
	
}
