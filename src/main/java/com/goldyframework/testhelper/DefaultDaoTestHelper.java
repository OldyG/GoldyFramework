/**
 * FileName : {@link DefaultDaoTestHelper}.java
 * Created : 2017. 8. 15. 오전 3:09:17
 * Author : jeong
 * Summary :
 * Copyright (C) 2018 Formal Works Inc. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 (주)포멀웍스에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.testhelper;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;

import com.goldyframework.db.exception.DuplicateRecordException;
import com.goldyframework.db.inf.DefaultDao;
import com.goldyframework.db.inf.Dto;
import com.goldyframework.utils.json.JsonGtils;

/**
 * @author 2017. 8. 15. 오전 3:09:17 jeong
 */
public class DefaultDaoTestHelper<DTO extends Dto> {
	
	protected final DefaultDao<DTO> defaultDao;
	
	protected final DaoTestTemplate<DTO> template;
	
	/**
	 * {@link StandardDaoTestHelper} 클래스의 새 인스턴스를 초기화 합니다.
	 * 
	 * @author 2017. 8. 14. 오후 11:54:37 jeong
	 */
	public DefaultDaoTestHelper(final DefaultDao<DTO> standardDao, final DaoTestTemplate<DTO> template) {
		
		super();
		this.defaultDao = standardDao;
		this.template = template;
	}
	
	public DTO createDto(final DTO dto) throws DuplicateRecordException {
		
		return this.defaultDao.insert(dto);
	}
	
	protected Collection<DTO> getAllDto() {
		
		return this.defaultDao.selectAll();
	}
	
	protected DTO getAnyDto() {
		
		return this.getAllDto().stream()
			.findAny()
			.orElseGet(() -> this.insertAndGetDto(0));
	}
	
	private DTO insertAndGetDto(int stackCount) {
		
		stackCount++;
		final DTO createAllSetDto = this.template.createRandomDtoForInsert();
		try {
			return this.defaultDao.insert(createAllSetDto);
		} catch (final DuplicateRecordException e) {
			if (stackCount > 10) {
				throw new StackOverflowError("createRandomDtoForInsert 함수가 랜덤으로 생성되지 않는것같습니다."); 
			}
			return this.insertAndGetDto(stackCount);
		}
	}
	
	public DTO insertRandomDto() {
		
		return this.insertAndGetDto(0);
	}
	
	public void testDelete() {
		
		final DTO anyDto = this.getAnyDto();
		final Integer key = anyDto.getKey();
		this.defaultDao.delete(key);
		
		final Optional<DTO> findFirst = this.getAllDto().stream()
			.filter(dto -> dto.getKey() == key)
			.findFirst();
		
		Assert.assertFalse(findFirst.isPresent());
		
	}
	
	private void testEqualsTheme(final DTO expected, final DTO actual) {
		
		final String expectedJson = JsonGtils.toGson(expected);
		final String actualJson = JsonGtils.toGson(actual);
		Assert.assertEquals(expectedJson, actualJson);
	}
	
	public void testInsert() throws DuplicateRecordException {
		
		final DTO allSetDto = this.template.createRandomDtoForInsert();
		
		final DTO actual = this.defaultDao.insert(allSetDto);
		
		final List<Object> values = this.template.getNotNullableField(actual);
		
		for (final Object value : values) {
			Assert.assertNotNull(value);
		}
	}
	
	public void testInsertDuplication(final String validMessage) {
		
		final DTO allSetDto = this.template.createRandomDtoForInsert();
		
		try {
			this.defaultDao.insert(allSetDto);
			this.defaultDao.insert(allSetDto);
		} catch (final DuplicateRecordException e) {
			Assert.assertEquals(validMessage, e.getMessage());
		}
	}
	
	@SuppressWarnings("null")
	public void testSelect() {
		
		final DTO anyDto = this.getAnyDto();
		final DTO result = this.defaultDao.select(anyDto.getKey());
		this.testEqualsTheme(anyDto, result);
	}
	
	public void testSelectAll() throws DuplicateRecordException {
		
		final Collection<DTO> beforeSelectAll = this.defaultDao.selectAll();
		
		this.defaultDao.insert(this.template.createRandomDtoForInsert());
		
		final Collection<DTO> afterSelectAll = this.defaultDao.selectAll();
		
		Assert.assertEquals(beforeSelectAll.size() + 1, afterSelectAll.size());
	}
	
	public <T> void testUpdate(final UpdateWay<T, DTO> updateUnit) {
		
		final DTO anyDto = this.getAnyDto();
		
		this.defaultDao.update(anyDto.getKey(), updateUnit.getColumnName(), updateUnit.updateValue());
		final DTO result = this.defaultDao.select(anyDto.getKey());
		
		Assert.assertEquals(updateUnit.getValue(result), updateUnit.updateValue());
	}
	
	public void testUpdateAll() {
		
		final DTO anyDto = this.getAnyDto();
		final DTO allSetDto = this.template.createRandomDtoForInsert();
		this.defaultDao.updateAll(anyDto.getKey(), allSetDto);
		final DTO result = this.defaultDao.select(anyDto.getKey());
		final List<Object> values = this.template.getNotNullableField(result);
		for (final Object value : values) {
			Assert.assertNotNull(value);
		}
	}
	
}
