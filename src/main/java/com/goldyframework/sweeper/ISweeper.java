/**
 * FileName : {@link ISweeper}.java
 * Created : 2017. 4. 10.
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Goldy Project. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.sweeper;

import com.goldyframework.sweeper.exception.SweeperException;

/**
 * 청소 인터페이스
 *
 * @author 2017. 6. 18. 오후 2:30:00 jeong
 */
@FunctionalInterface
public interface ISweeper {
	
	/**
	 * 청소 유형에 따른 실행 방법 구현
	 *
	 * @author 2017. 6. 18. 오후 2:30:07 jeong
	 * @throws SweeperException
	 *             청소 중 발생한 예외사항
	 */
	void run() throws SweeperException;
}
