/**
 * FileName : {@link Algorithm}.java
 * Created : 2017. 6. 18. 오전 10:35:27
 * Author : jeong
 * Summary :
 * Copyright (C) 2018 Formal Works Inc. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.checksum;

import com.goldyframework.utils.NullGtils;

/**
 * 알고리즘 종류
 *
 * @author 2017. 6. 18. 오전 10:30:32 jeong
 */
enum Algorithm {
	/**
	 * MD2 Algolithm
	 */
	MD2("MD2"),
	/**
	 * MD5 Algolithm
	 */
	MD5("MD5"),
	/**
	 * SHA-1 Algolithm
	 */
	SHA1("SHA-1"),
	/**
	 * SHA-256 Algolithm
	 */
	SHA256("SHA-256"),
	/**
	 * SHA-384 Algolithm
	 */
	SHA384("SHA-384"),
	/**
	 * SHA-512 Algolithm
	 */
	SHA512("SHA-512");
	
	/**
	 * 알고리즘 이름
	 */
	private String name;
	
	/**
	 * {@link Algorithm} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author 2017. 6. 18. 오전 10:35:50 jeong
	 * @param name
	 *            알고리즘 이름
	 */
	Algorithm(String name) {
		
		this.name = NullGtils.throwIfNull(name);
	}
	
	/**
	 * name를 반환합니다.
	 *
	 * @see {@link #name}
	 * @author jeong
	 * @since 2016. 6. 18. 오전 1:10:21
	 * @return name
	 */
	public String getName() {
		
		return this.name;
	}
}
