/**
 * FileName : Sql.java
 * Created : 2017. 4. 10.
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Goldy Project. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.db.sql;

/**
 * SQL 통합 인터페이스
 *
 * @author 2017. 6. 18. 오전 11:09:15 jeong
 */
public interface Sql {

	/**
	 * 설정된 값을 SQL문으로 반환합니다.
	 *
	 * @author 2017. 6. 18. 오전 11:09:23 jeong
	 * @return SQL 문자열
	 */
	String toSql();
	
	/**
	 * @author 2017. 6. 18. 오후 3:09:29 jeong
	 */
	void unuseSemicolon();

}
