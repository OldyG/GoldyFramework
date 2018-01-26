/**
 * FileName : {@link ParentTest}.java
 * Created : 2017. 8. 15. 오후 4:41:18
 * Author : jeong
 * Summary :
 * Copyright (C) 2018 Formal Works Inc. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 (주)포멀웍스에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.testhelper;

import com.goldyframework.db.inf.Dto;
import com.goldyframework.db.inf.PrimaryDto;

/**
 * @author 2017. 8. 15. 오후 4:41:18 jeong
 */
public interface ParentTest<DTO extends Dto, PDTO extends PrimaryDto> {
	
	StandardDaoTestHelper<DTO, PDTO> getHelper();
	
}