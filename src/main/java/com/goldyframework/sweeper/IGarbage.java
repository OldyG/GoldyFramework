/**
 * FileName : {@link IGarbage}.java
 * Created : 2017. 4. 10.
 * Author : jeong
 * Summary :
 * Copyright (C) 2018 Formal Works Inc. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.sweeper;

import com.goldyframework.sweeper.exception.SweeperException;

/**
 * 쓰레기 모델 인터페이스
 *
 * @author 2017. 6. 18. 오후 2:29:37 jeong
 */
@FunctionalInterface
public interface IGarbage {
	
	/**
	 * 청소 로직을 작성합니다.
	 * 만약 Exception을 throws를 한다면 이 청소를 중단하며 파일을 제거하지 않습니다.
	 *
	 * @author jeong
	 * @throws SweeperException
	 * @since 2016. 7. 9. 오후 12:20:39
	 * @throws Exception
	 *             청소 예외사항
	 */
	void clean() throws SweeperException;
}
