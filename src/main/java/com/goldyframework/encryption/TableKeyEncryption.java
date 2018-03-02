/**
 * FileName : {@link TableKeyEncryption}.java
 * Created : 2018. 1. 20. 오후 3:35:29
 * Author : jeong
 * Summary :
 * Copyright (C) 2018 Formal Works Inc. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.encryption;

import com.goldyframework.encryption.exception.EncryptionException;
import com.goldyframework.random.RandomStringGtils;
import com.google.gson.Gson;

public class TableKeyEncryption {
	
	class TempModel {
		
		int key;
		
		String randomString;
		
		/**
		 * {@link TempModel} 클래스의 새 인스턴스를 초기화 합니다.
		 *
		 * @author 2018. 1. 20. 오후 3:41:09 jeong
		 */
		public TempModel() {
			
			super();
			
		}
		
	}
	
	private final Encryption encryption = new Encryption();
	
	public int decrypt(String encryptedString) throws EncryptionException {
		
		String decrypt = this.encryption.decrypt(encryptedString);
		TempModel tempModel = new Gson().fromJson(decrypt, TempModel.class);
		
		return tempModel.key;
	}
	
	public String encrypt(int tableKey) throws EncryptionException {
		
		TempModel tempModel = new TempModel();
		tempModel.key = tableKey;
		
		String randomString = RandomStringGtils.createRandomString(3, 3);
		tempModel.randomString = randomString;
		
		String json = new Gson().toJson(tempModel);
		
		return this.encryption.encrypt(json);
		
	}
	
}
