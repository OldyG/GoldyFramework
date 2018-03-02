/**
 * FileName : {@link LimitGuide}.java
 * Created : 2018. 1. 27. 오후 9:23:11
 * Author : jeong
 * Summary :
 * Copyright (C) 2018 Formal Works Inc. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.db.prepare.statement.guide;

import java.text.MessageFormat;

import com.goldyframework.exception.LogicErrorType;
import com.goldyframework.exception.LogicException;
import com.goldyframework.inspection.IntegerInspection;

public class LimitGuide implements Guide {
	
	enum LimitType {
		ONLY_COUNT,
		HAS_START_INDEX;
	}
	
	private final LimitType limitType;
	
	private final int startIndex;
	
	private final int count;
	
	public LimitGuide(int count) {
		
		IntegerInspection.checkBelowZero(count);
		this.startIndex = -1;
		this.count = count;
		this.limitType = LimitType.ONLY_COUNT;
	}
	
	public LimitGuide(int startIndex, int count) {
		
		IntegerInspection.checkUnsigned(startIndex);
		IntegerInspection.checkBelowZero(count);
		
		this.startIndex = startIndex;
		this.count = count;
		this.limitType = LimitType.HAS_START_INDEX;
	}
	
	@Override
	public String toSql() {
		
		switch (this.limitType) {
			case HAS_START_INDEX:
				return MessageFormat.format("LIMIT {0}, {1}", this.startIndex, this.count);
			case ONLY_COUNT:
				return MessageFormat.format("LIMIT {0}", this.count);
			default:
				throw new LogicException(LogicErrorType.ENUM_CASE);
		}
	}
	
}
