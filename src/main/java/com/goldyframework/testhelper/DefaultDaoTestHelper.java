/**
 * FileName : {@link DefaultDaoTestHelper}.java
 * Created : 2017. 8. 15. 오전 3:09:17
 * Author : jeong
 * Summary :
 * Copyright (C) 2018 Formal Works Inc. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
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
import com.google.common.annotations.VisibleForTesting;

/**
 * @author 2017. 8. 15. 오전 3:09:17 jeong
 */
public class DefaultDaoTestHelper<DTO extends Dto> {
	
	protected DefaultDao<DTO> defaultDao;
	
	protected DaoTestTemplate<DTO> template;
	
	/**
	 * {@link StandardDaoTestHelper} 클래스의 새 인스턴스를 초기화 합니다.
	 * 
	 * @author 2017. 8. 14. 오후 11:54:37 jeong
	 */
	public DefaultDaoTestHelper(DefaultDao<DTO> standardDao, DaoTestTemplate<DTO> template) {
		
		super();
		this.defaultDao = standardDao;
		this.template = template;
	}
	
	public void abstractTest() throws DuplicateRecordException {
		
		this.testSelect();
		this.testDelete();
		this.testSelectAll();
	}
	
	public boolean contains(Collection<DTO> actualList, DTO expected) {
		
		for (DTO actual : actualList) {
			if (this.equalsDto(expected, actual)) {
				return true;
			}
		}
		return false;
	}
	
	public DTO createRandomDto() {
		
		return this.template.createRandomDtoForInsert();
	}
	
	public boolean equalsDto(DTO expected, DTO actual) {
		
		String expectedJson = JsonGtils.toGson(expected);
		String actualJson = JsonGtils.toGson(actual);
		return expectedJson.equals(actualJson);
	}
	
	@VisibleForTesting
	Collection<DTO> getAllDto() {
		
		return this.defaultDao.selectAll();
	}
	
	@VisibleForTesting
	DTO getAnyDto() {
		
		return this.getAllDto().stream()
			.findAny()
			.orElseGet(() -> this.insertAndGetDto(0));
	}
	
	private DTO insertAndGetDto(int stackCount) {
		
		int increamentStackCount = stackCount + 1;
		DTO createAllSetDto = this.template.createRandomDtoForInsert();
		
		try {
			return this.defaultDao.insert(createAllSetDto);
		} catch (DuplicateRecordException e) {
			if (increamentStackCount > 10) {
				throw new StackOverflowError("createRandomDtoForInsert 함수가 랜덤으로 생성되지 않는것같습니다.");
			}
			return this.insertAndGetDto(increamentStackCount);
		}
	}
	
	public DTO insertDto(DTO dto) throws DuplicateRecordException {
		
		return this.defaultDao.insert(dto);
	}
	
	public DTO insertRandomDto() {
		
		return this.insertAndGetDto(0);
	}
	
	public void testDelete() {
		
		DTO anyDto = this.getAnyDto();
		Integer key = anyDto.getKey();
		this.defaultDao.delete(key);
		
		Optional<DTO> findFirst = this.getAllDto().stream()
			.filter(dto -> dto.getKey() == key)
			.findFirst();
		
		Assert.assertFalse(findFirst.isPresent());
		
	}
	
	private void testEquals(DTO expected, DTO actual) {
		
		boolean equalsDto = this.equalsDto(expected, actual);
		Assert.assertTrue("Equals 테스트", equalsDto);
	}
	
	public void testInsert() throws DuplicateRecordException {
		
		DTO allSetDto = this.template.createRandomDtoForInsert();
		
		DTO actual = this.defaultDao.insert(allSetDto);
		
		List<Object> values = this.template.getNotNullableField(actual);
		
		for (Object value : values) {
			Assert.assertNotNull(value);
		}
	}
	
	public void testInsertDuplication(String validMessage) {
		
		DTO allSetDto = this.template.createRandomDtoForInsert();
		
		try {
			this.defaultDao.insert(allSetDto);
			this.defaultDao.insert(allSetDto);
			Assert.fail("같은 값을 넣었지만 중복으로 처리되지 않음");
		} catch (DuplicateRecordException e) {
			Assert.assertEquals(validMessage, e.getMessage());
		}
	}
	
	@SuppressWarnings("null")
	public void testSelect() {
		
		DTO anyDto = this.getAnyDto();
		DTO result = this.defaultDao.select(anyDto.getKey());
		this.testEquals(anyDto, result);
	}
	
	public void testSelectAll() throws DuplicateRecordException {
		
		Collection<DTO> beforeSelectAll = this.defaultDao.selectAll();
		
		this.defaultDao.insert(this.template.createRandomDtoForInsert());
		
		Collection<DTO> afterSelectAll = this.defaultDao.selectAll();
		
		Assert.assertEquals(beforeSelectAll.size() + 1, afterSelectAll.size());
	}
	
	public <T> void testUpdate(UpdateWay<T, DTO> updateUnit) {
		
		DTO anyDto = this.getAnyDto();
		
		this.defaultDao.update(anyDto.getKey(), updateUnit.getColumnName(), updateUnit.updateValue());
		DTO result = this.defaultDao.select(anyDto.getKey());
		
		Assert.assertEquals(updateUnit.getValue(result), updateUnit.updateValue());
	}
	
	public void testUpdateAll() {
		
		DTO anyDto = this.getAnyDto();
		DTO allSetDto = this.template.createRandomDtoForInsert();
		this.defaultDao.updateAll(anyDto.getKey(), allSetDto);
		DTO result = this.defaultDao.select(anyDto.getKey());
		List<Object> values = this.template.getNotNullableField(result);
		for (Object value : values) {
			Assert.assertNotNull(value);
		}
	}
	
}
