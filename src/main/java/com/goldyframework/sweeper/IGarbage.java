/**
 * FileName : IGarbage.java
 * Created : 2017. 4. 10.
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Goldy Project. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.sweeper;

public interface IGarbage {

	/**
	 * 청소 로직을 작성합니다.
	 * 만약 Exception을 throws를 한다면 이 청소를 중단하며 파일을 제거하지 않습니다.
	 *
	 * @author jeong
	 * @since 2016. 7. 9. 오후 12:20:39
	 * @throws Exception
	 */
	void clean() throws Exception;
}
