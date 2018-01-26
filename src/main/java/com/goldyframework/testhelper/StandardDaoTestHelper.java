/**
 * FileName : {@link StandardDaoTestHelper}.java
 * Created : 2017. 8. 14. 오후 11:38:01
 * Author : jeong
 * Summary :
 * Copyright (C) 2018 Formal Works Inc. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 (주)포멀웍스에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.testhelper;

import java.util.Collection;
import java.util.Optional;

import org.junit.Assert;

import com.goldyframework.db.cache.AbstractCacheDao;
import com.goldyframework.db.cache.CacheDto;
import com.goldyframework.db.inf.Dto;
import com.goldyframework.db.inf.PrimaryDto;
import com.goldyframework.db.inf.StandardDao;

public class StandardDaoTestHelper<DTO extends Dto, PDTO extends PrimaryDto> extends DefaultDaoTestHelper<DTO> {
	
	protected final StandardDao<DTO, PDTO> standardDao;
	
	protected final DaoTestTemplate<DTO> template;
	
	/**
	 * {@link StandardDaoTestHelper} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author 2017. 8. 14. 오후 11:54:37 jeong
	 */
	public StandardDaoTestHelper(final StandardDao<DTO, PDTO> standardDao,
		final DaoTestTemplate<DTO> template) {
		
		super(standardDao, template);
		this.standardDao = standardDao;
		this.template = template;
	}
	
	public void testDeleteForPrimary() {
		
		final Dto anyDto = super.getAnyDto();
		this.standardDao.delete((PDTO) anyDto);
		
		final Optional<DTO> findFirst = super.getAllDto().stream()
			.filter(dto -> dto.getKey() == anyDto.getKey())
			.findFirst();
		
		Assert.assertFalse(findFirst.isPresent());
	}
	
	public <DTOP extends Dto, PDTOP extends PrimaryDto> void testSelectFormForeignKey(final AbstractCacheDao cacheDao,
		final ParentTest<DTOP, PDTOP> parentTest) {
		
		final Integer foreignKey = parentTest.getHelper().insertRandomDto().getKey();
		
		final Collection<CacheDto> cacheDtoCollection = cacheDao.selectFromForeignKey(foreignKey);
		for (final CacheDto cacheDto : cacheDtoCollection) {
			Assert.assertEquals(foreignKey, cacheDto.getForeignKey());
		}
	}
	
	public void testUpdateCache(final AbstractCacheDao cacheDao) {
		
		final DTO anyDto = super.getAnyDto();
		
		cacheDao.updateCache(anyDto.getKey(), "ABC");
		final CacheDto cacheDto = cacheDao.select(anyDto.getKey());
		Assert.assertEquals("ABC", cacheDto.getValue());
	}
	
}
