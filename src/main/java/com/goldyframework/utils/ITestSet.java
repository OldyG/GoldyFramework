/**
 * FileName : {@link ITestSet}.java
 * Created : 2017. 6. 19. 오후 6:44:45
 * Author : jeong
 * Summary :
 * Copyright (C) 2018 Formal Works Inc. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.utils;

/**
 * @author 2017. 6. 19. 오후 6:44:45 jeong
 * @param <T>
 */
@FunctionalInterface
public interface ITestSet<T> {
	
	T createNewInstance();
}
