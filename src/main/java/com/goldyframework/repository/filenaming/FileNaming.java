/**
 * FileName : {@link FileNaming}.java
 * Created : 2017. 4. 10.
 * Author : jeong
 * Summary :
 * Copyright (C) 2018 Formal Works Inc. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.repository.filenaming;

/**
 * 파일 이름 생성 인터페이스
 *
 * @author 2017. 6. 18. 오후 1:34:22 jeong
 */
@FunctionalInterface
public interface FileNaming {
	
	/**
	 * 저장경로를 생성한다
	 *
	 * @author 2017. 6. 18. 오후 1:34:29 jeong
	 * @param directory
	 *            디렉토리
	 * @param baseName
	 *            기본 이름
	 * @param extension
	 *            확장자
	 * @return 생성된 저장 경로
	 */
	String generageSavePath(String directory, String baseName, String extension);
	
}
