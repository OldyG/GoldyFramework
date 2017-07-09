/**
 * FileName : {@link PreparePlan}.java
 * Created : 2017. 7. 8. 오후 1:57:28
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Goldy Project. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 (주)Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.db.prepare.statement;

/**
 * @author 2017. 7. 8. 오후 1:57:28 jeong
 */
public interface PreparePlan<T extends Prepare> {

	T build();

}
