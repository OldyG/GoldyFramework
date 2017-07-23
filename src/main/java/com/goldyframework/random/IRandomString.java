/**
 * FileName : {@link IRandomString}.java
 * Created : 2017. 4. 10.
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Goldy Project. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.random;

/**
 * 무작위 문자열 생성 인터페이스
 *
 * @author 2017. 6. 18. 오후 1:31:09 jeong
 */
@FunctionalInterface
public interface IRandomString {
	
	/**
	 * 무작위 문자열 생성한다.
	 *
	 * @author 2017. 6. 18. 오후 1:31:19 jeong
	 * @return 무작위 문자열
	 */
	Object generate();
}
