/**
 * FileName : {@link AbstractCacheDao}.java
 * Created : 2017. 7. 15. 오후 7:03:51
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Formal Works Inc. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 (주)포멀웍스에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.db.cache;

import java.util.Collection;

import com.goldyframework.db.JdbcPrepareTemplate;
import com.goldyframework.db.exception.DuplicateRecordException;
import com.goldyframework.db.inf.StandardDao;
import com.goldyframework.db.prepare.PrepareBuilder;
import com.goldyframework.db.prepare.statement.Comparison;
import com.goldyframework.db.prepare.statement.delete.DeletePrepare;
import com.goldyframework.db.prepare.statement.insert.InsertPrepare;
import com.goldyframework.db.prepare.statement.select.SelectPrepare;
import com.goldyframework.db.prepare.statement.update.UpdatePrepare;
import com.goldyframework.does.Because;
import com.goldyframework.does.Does;
import com.goldyframework.inspection.IntegerInspection;
import com.goldyframework.inspection.ObjectInspection;
import com.goldyframework.inspection.StringInspection;

/**
 * 모든 cache 테이블 가상 DAO
 *
 * @author 2017. 7. 23. 오후 5:33:25 jeong
 */
public abstract class AbstractCacheDao implements StandardDao<CacheDto, CachePrimaryDto> {
	
	/**
	 * SQL을 전송하는 템플릿
	 */
	private final JdbcPrepareTemplate template;
	
	/**
	 * 사용자 캐시 매핑정보
	 */
	private final CacheMapping mapping;
	
	/**
	 * 테이블 이름
	 */
	private final String tableName;
	
	/**
	 * {@link AbstractCacheDao} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author 2017. 7. 15. 오후 7:13:35 jeong
	 * @param template
	 *            SQL을 전송하는 템플릿
	 * @param tableName
	 *            테이블 이름
	 */
	public AbstractCacheDao(final JdbcPrepareTemplate template, final String schema, final String tableName) {

		ObjectInspection.checkNull(template);
		StringInspection.checkBlank(tableName);
		this.template = template;
		this.tableName = tableName;
		this.mapping = new CacheMapping(schema, tableName);
	}
	
	/**
	 * 주어진 key를 가진 데이터를 제거합니다.
	 *
	 * @author 2017. 7. 3. 오후 10:22:45 jeong
	 * @param primary
	 *            제거 대상 프라이머리
	 */
	@Override
	public void delete(final CachePrimaryDto primary) {
		
		ObjectInspection.checkNull(primary);
		final DeletePrepare delete = PrepareBuilder
			.delete(this.tableName)
			.where(CacheProp.FOREIGN_KEY, Comparison.EQUAL, primary.getForeignKey())
			.where(CacheProp.NAME, Comparison.EQUAL, primary.getName())
			.build();
		
		this.template.delete(delete);
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * @author 2017. 7. 23. 오후 8:31:59 jeong
	 */
	@Override
	public void delete(final int targetCacheKey) {
		
		IntegerInspection.checkBelowZero(targetCacheKey);
		final DeletePrepare delete = PrepareBuilder
			.delete(this.tableName)
			.where(CacheProp.KEY, Comparison.EQUAL, targetCacheKey)
			.build();
		
		this.template.delete(delete);
		
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * @author 2017. 7. 23. 오후 8:31:59 jeong
	 */
	@Override
	public CacheDto insert(final CacheDto dto) throws DuplicateRecordException {
		
		ObjectInspection.checkNull(dto);
		try {
			final InsertPrepare insert = PrepareBuilder
				.insert(this.tableName)
				.assign(CacheProp.FOREIGN_KEY, dto.getForeignKey())
				.assign(CacheProp.NAME, dto.getName())
				.assign(CacheProp.VALUE, dto.getValue())
				.build();
			
			this.template.insert(insert);
			
			return this.select(dto);
		} catch (final DuplicateRecordException e) {
			throw new DuplicateRecordException("동일한 캐시가 존재합니다.", e);
		}
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * @author 2017. 7. 23. 오후 8:31:59 jeong
	 */
	@Override
	public CacheDto select(final CachePrimaryDto primaryDto) {
		
		ObjectInspection.checkNull(primaryDto);
		final SelectPrepare select = PrepareBuilder
			.select(this.tableName)
			.where(CacheProp.FOREIGN_KEY, Comparison.EQUAL, primaryDto.getForeignKey())
			.where(CacheProp.NAME, Comparison.EQUAL, primaryDto.getName())
			.build();
		
		return this.template.select(select, this.mapping.rowMapper());
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * @author 2017. 7. 23. 오후 8:31:59 jeong
	 */
	@Override
	public CacheDto select(final int targetCacheKey) {
		
		IntegerInspection.checkBelowZero(targetCacheKey);
		final SelectPrepare select = PrepareBuilder
			.select(this.tableName)
			.where(CacheProp.KEY, Comparison.EQUAL, targetCacheKey)
			.build();
		
		return this.template.select(select, this.mapping.rowMapper());
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * @author 2017. 7. 23. 오후 8:31:59 jeong
	 */
	@Override
	public Collection<CacheDto> selectAll() {
		
		final SelectPrepare select = PrepareBuilder
			.select(this.tableName)
			.build();
		return this.template.selectAll(select, this.mapping.rowMapper());
	}
	
	/**
	 * 주어진 user키를 가진 모든 {@link CacheDto} 목록을 반환합니다.
	 *
	 * @author 2017. 7. 15. 오후 7:36:19 jeong
	 * @param foreignKey
	 *            user 테이블 키
	 * @return {@link CacheDto} 목록
	 */
	public Collection<CacheDto> selectFromForeignKey(final int foreignKey) {
		
		IntegerInspection.checkBelowZero(foreignKey);
		final SelectPrepare select = PrepareBuilder
			.select(this.tableName)
			.where(CacheProp.FOREIGN_KEY, Comparison.EQUAL, foreignKey)
			.build();
		return this.template.selectPart(select, this.mapping.rowMapper());
		
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * @author 2017. 7. 23. 오후 8:31:59 jeong
	 */
	@Override
	public void update(final int targetCacheKey, final String column, final Object value) {
		
		IntegerInspection.checkBelowZero(targetCacheKey);
		StringInspection.checkBlank(column);
		ObjectInspection.checkNull(value);
		Does.notUse(this.select(targetCacheKey), Because.DO_NOTHING);
		final UpdatePrepare update = PrepareBuilder
			.update(this.tableName)
			.assign(column, value)
			.where(CacheProp.KEY, Comparison.EQUAL, targetCacheKey)
			.build();
		
		this.template.update(update);
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * @author 2017. 7. 23. 오후 8:31:59 jeong
	 */
	@Override
	public void updateAll(final int targetCacheKey, final CacheDto dto) {
		
		IntegerInspection.checkBelowZero(targetCacheKey);
		ObjectInspection.checkNull(dto);
		
		Does.notUse(this.select(targetCacheKey), Because.DO_NOTHING);
		final UpdatePrepare update = PrepareBuilder
			.update(this.tableName)
			.assign(CacheProp.FOREIGN_KEY, dto.getForeignKey())
			.assign(CacheProp.NAME, dto.getName())
			.assign(CacheProp.VALUE, dto.getValue())
			.assign(CacheProp.CREATED_TIME, dto.getCreatedTime())
			.where(CacheProp.KEY, Comparison.EQUAL, targetCacheKey)
			.build();
		
		this.template.update(update);
	}
	
	/**
	 * 캐시를 업데이트한다.
	 *
	 * @author 2017. 7. 29. 오전 12:16:22 jeong
	 * @param targetCacheKey
	 *            레코드 키
	 * @param value
	 *            value의 값
	 */
	public void updateCache(final int targetCacheKey, final Object value) {
		
		IntegerInspection.checkBelowZero(targetCacheKey);
		ObjectInspection.checkNull(value);
		this.update(targetCacheKey, CacheProp.VALUE, value);
	}
}
