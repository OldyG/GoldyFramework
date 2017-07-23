/**
 * FileName : {@link SendModelValidator}.java
 * Created : 2017. 4. 10.
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Goldy Project. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.email.validator;

import com.goldyframework.email.model.SendModel;
import com.goldyframework.inspection.Inspection;
import com.goldyframework.inspection.ObjectInspection;

/**
 * {@link SendModel} 객체 검사도구
 *
 * @author 2017. 6. 18. 오전 11:40:46 jeong
 */
public class SendModelValidator implements Inspection<SendModel> {
	
	/**
	 * {@link SendModelValidator} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author jeong
	 * @since 2017. 4. 10. 오후 9:32:45
	 */
	public SendModelValidator() {
		
		super();
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * @author 2017. 6. 18. 오전 11:40:58 jeong
	 */
	@Override
	public void check(final SendModel obj) {
		
		ObjectInspection.checkNull(obj);
		
	}
	
}
