/**
 * FileName : {@link Checksum}.java
 * Created : 2017. 4. 10.
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Goldy Project. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.checksum;

import com.goldyframework.utils.NullGtils;

/**
 * File Checksum
 *
 * @author 2017. 6. 18. 오전 10:27:16 jeong
 */
public class Checksum {
	
	/**
	 * MD2 Algolithm
	 */
	private String md2;
	
	/**
	 * MD5 Algolithm
	 */
	private String md5;
	
	/**
	 * SHA-1 Algolithm
	 */
	private String sha1;
	
	/**
	 * SHA-256 Algolithm
	 */
	private String sha256;
	
	/**
	 * SHA-384 Algolithm
	 */
	private String sha384;
	
	/**
	 * SHA-512 Algolithm
	 */
	private String sha512;
	
	/**
	 * {@link Checksum} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author jeong
	 * @since 2017. 4. 10. 오후 9:35:33
	 */
	public Checksum() {
		super();
	}
	
	/**
	 * md2를 반환합니다.
	 *
	 * @see {@link #md2}
	 * @author jeong
	 * @since 2016. 6. 18. 오전 1:54:47
	 * @return md2
	 */
	public String getMd2() {
		
		return this.md2;
	}
	
	/**
	 * md5를 반환합니다.
	 *
	 * @see {@link #md5}
	 * @author jeong
	 * @since 2016. 6. 18. 오전 1:54:47
	 * @return md5
	 */
	public String getMd5() {
		
		return this.md5;
	}
	
	/**
	 * sha1를 반환합니다.
	 *
	 * @see {@link #sha1}
	 * @author jeong
	 * @since 2016. 6. 18. 오전 1:54:47
	 * @return sha1
	 */
	public String getSha1() {
		
		return this.sha1;
	}
	
	/**
	 * sha256를 반환합니다.
	 *
	 * @see {@link #sha256}
	 * @author jeong
	 * @since 2016. 6. 18. 오전 1:54:47
	 * @return sha256
	 */
	public String getSha256() {
		
		return this.sha256;
	}
	
	/**
	 * sha384를 반환합니다.
	 *
	 * @see {@link #sha384}
	 * @author jeong
	 * @since 2016. 6. 18. 오전 1:54:47
	 * @return sha384
	 */
	public String getSha384() {
		
		return this.sha384;
	}
	
	/**
	 * sha512를 반환합니다.
	 *
	 * @see {@link #sha512}
	 * @author jeong
	 * @since 2016. 6. 18. 오전 1:54:47
	 * @return sha512
	 */
	public String getSha512() {
		
		return this.sha512;
	}
	
	/**
	 * md2 초기화 합니다.
	 *
	 * @see {@link #md2}
	 * @author jeong
	 * @since 2016. 6. 18. 오전 1:54:47
	 * @param md2
	 *            초기화 값
	 */
	
	public void setMd2(final String md2) {
		
		this.md2 = NullGtils.throwIfNull(md2);
	}
	
	/**
	 * md5 초기화 합니다.
	 *
	 * @see {@link #md5}
	 * @author jeong
	 * @since 2016. 6. 18. 오전 1:54:47
	 * @param md5
	 *            초기화 값
	 */
	
	public void setMd5(final String md5) {
		
		this.md5 = NullGtils.throwIfNull(md5);
	}
	
	/**
	 * sha1 초기화 합니다.
	 *
	 * @see {@link #sha1}
	 * @author jeong
	 * @since 2016. 6. 18. 오전 1:54:47
	 * @param sha1
	 *            초기화 값
	 */
	
	public void setSha1(final String sha1) {
		
		this.sha1 = NullGtils.throwIfNull(sha1);
	}
	
	/**
	 * sha256 초기화 합니다.
	 *
	 * @see {@link #sha256}
	 * @author jeong
	 * @since 2016. 6. 18. 오전 1:54:47
	 * @param sha256
	 *            초기화 값
	 */
	
	public void setSha256(final String sha256) {
		
		this.sha256 = NullGtils.throwIfNull(sha256);
	}
	
	/**
	 * sha384 초기화 합니다.
	 *
	 * @see {@link #sha384}
	 * @author jeong
	 * @since 2016. 6. 18. 오전 1:54:47
	 * @param sha384
	 *            초기화 값
	 */
	
	public void setSha384(final String sha384) {
		
		this.sha384 = NullGtils.throwIfNull(sha384);
	}
	
	/**
	 * sha512 초기화 합니다.
	 *
	 * @see {@link #sha512}
	 * @author jeong
	 * @since 2016. 6. 18. 오전 1:54:47
	 * @param sha512
	 *            초기화 값
	 */
	
	public void setSha512(final String sha512) {
		
		this.sha512 = NullGtils.throwIfNull(sha512);
	}
	
}
