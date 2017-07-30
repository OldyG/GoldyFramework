/**
 * FileName : {@link FixedSweeper}.java
 * Created : 2017. 4. 10.
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Goldy Project. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.sweeper;

import java.util.Collection;
import java.util.LinkedList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.goldyframework.sweeper.exception.SweeperException;

/**
 * 고정 청소
 *
 * @author 2017. 6. 18. 오후 2:29:17 jeong
 */
public class FixedSweeper implements ISweeper {
	
	/**
	 * slf4j Logger
	 *
	 * @author jeong
	 * @since 2017. 5. 22. 오후 9:20:02
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(FixedSweeper.class);
	
	/**
	 * 고정 청소 목록
	 */
	private static final Collection<IGarbage> FIXED_GARBAGE_LIST = new LinkedList<>();
	
	/**
	 * {@link FixedSweeper} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author jeong
	 * @since 2017. 4. 10. 오후 9:34:40
	 */
	public FixedSweeper() {
		
		super();
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * @author 2017. 6. 18. 오후 2:29:32 jeong
	 */
	@Override
	public void run() {
		
		for (final IGarbage garbage : FIXED_GARBAGE_LIST) {
			try {
				garbage.clean();
			} catch (final SweeperException e) {
				LOGGER.error(garbage.getClass().getName() + "을 청소하는 중 알수없는 문제가 발생하였습니다. 해당 클래스를 점검하시길바랍니다.", e); //$NON-NLS-1$
				continue;
			}
		}
	}
}
