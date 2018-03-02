/**
 * FileName : {@link ShortUrlLinker}.java
 * Created : 2017. 7. 31. 오전 12:37:19
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Formal Works Inc. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.shorturl;

/**
 * @author 2017. 7. 31. 오전 12:37:19 jeong
 */
public interface ShortUrlLinker {
	
	/**
	 * 링크 URL API를 정의합니다.
	 * 
	 * @author 2017. 7. 31. 오후 12:43:44 jeong
	 * @return {@link ShortUrlApi}
	 */
	String initializeShortUrlApi();
}
