/**
 * FileName : Attachment.java
 * Created : 2017. 4. 10.
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Goldy Project. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.email;

import java.io.File;

public class Attachment {
	private final String fileName;

	private final File file;

	public Attachment(final String fileName, final File file) {
		this.fileName = fileName;
		this.file = file;
	}

	File getFile() {
		return this.file;
	}

	String getFileName() {
		return this.fileName;
	}
}