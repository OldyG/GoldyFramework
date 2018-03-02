/**
 * FileName : {@link ShortUrlDao}.java
 * Created : 2017. 6. 26. 오후 7:44:19
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Formal Works Inc. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.shorturl.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.goldyframework.db.JdbcPrepareTemplate;
import com.goldyframework.db.abstractdao.AbstractStandardDao;
import com.goldyframework.db.exception.DuplicateRecordException;
import com.goldyframework.db.prepare.PrepareBuilder;
import com.goldyframework.db.prepare.statement.delete.DeletePrepare;
import com.goldyframework.db.prepare.statement.guide.Comparison;
import com.goldyframework.db.prepare.statement.insert.InsertPrepare;
import com.goldyframework.db.prepare.statement.select.SelectPrepare;
import com.goldyframework.db.prepare.statement.update.UpdatePrepare;
import com.goldyframework.does.SonarHelper;
import com.goldyframework.inspection.IntegerInspection;
import com.goldyframework.inspection.ObjectInspection;
import com.goldyframework.inspection.StringInspection;

/**
 * ShortUrl 테이블 DAO
 *
 * @author 2017. 7. 23. 오후 5:32:13 jeong
 */
@Repository
public class ShortUrlDao extends AbstractStandardDao<ShortUrlDto, ShortUrlPrimaryDto> {
	
	/**
	 * SQL을 전송하는 템플릿
	 */
	@Autowired
	private JdbcPrepareTemplate template;
	
	/**
	 * 핀 매핑정보
	 */
	private final ShortUrlMapping mapping = new ShortUrlMapping();
	
	/**
	 * {@link ShortUrlDao} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author 2017. 7. 15. 오후 7:13:27 jeong
	 */
	public ShortUrlDao() {
		
		super(ShortUrlProp.TABLE_NAME, ShortUrlProp.KEY, new ShortUrlMapping());
	}
	
	/**
	 * {@inheritDoc}
	 * 
	 * @author 2017. 7. 30. 오후 11:38:59 jeong
	 */
	@Override
	public void delete(final int targetShortUrlKey) {
		
		IntegerInspection.checkBelowZero(targetShortUrlKey);
		final DeletePrepare delete = PrepareBuilder
			.delete(ShortUrlProp.TABLE_NAME)
			.where(ShortUrlProp.KEY, Comparison.EQUAL, targetShortUrlKey)
			.build();
		
		this.template.delete(delete);
		
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * @author 2017. 7. 30. 오후 5:43:41 jeong
	 */
	@Override
	public void delete(final ShortUrlPrimaryDto primary) {
		
		ObjectInspection.checkNull(primary);
		final DeletePrepare delete = PrepareBuilder
			.delete(ShortUrlProp.TABLE_NAME)
			.where(ShortUrlProp.API, Comparison.EQUAL, primary.getApi())
			.where(ShortUrlProp.DATA, Comparison.EQUAL, primary.getData())
			.build();
		
		this.template.delete(delete);
		
	}
	
	public void deleteOfUrl(String url) {
		
		StringInspection.checkBlank(url);
		final DeletePrepare delete = PrepareBuilder
			.delete(ShortUrlProp.TABLE_NAME)
			.where(ShortUrlProp.URL, Comparison.EQUAL, url)
			.build();
		
		this.template.delete(delete);
		
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * @author 2017. 7. 23. 오후 8:28:38 jeong
	 */
	@Override
	public ShortUrlDto insert(final ShortUrlDto dto) throws DuplicateRecordException {
		
		ObjectInspection.checkNull(dto);
		try {
			final InsertPrepare insert = PrepareBuilder
				.insert(ShortUrlProp.TABLE_NAME)
				.assign(ShortUrlProp.API, dto.getApi())
				.assign(ShortUrlProp.URL, dto.getUrl())
				.assign(ShortUrlProp.DATA, dto.getData())
				.build();
			
			this.template.insert(insert);
			return this.select(dto);
		} catch (final DuplicateRecordException e) {
			throw new DuplicateRecordException("이미 등록된 Short URL이 존재합니다.", e);
		}
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * @author 2017. 7. 23. 오후 8:28:32 jeong
	 */
	@Override
	public ShortUrlDto select(final int targetShortUrlKey) {
		
		IntegerInspection.checkBelowZero(targetShortUrlKey);
		final SelectPrepare select = PrepareBuilder
			.select(ShortUrlProp.TABLE_NAME)
			.where(ShortUrlProp.KEY, Comparison.EQUAL, targetShortUrlKey)
			.build();
		
		return this.template.select(select, this.mapping.rowMapper());
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * @author 2017. 7. 29. 오후 12:54:19 jeong
	 */
	@Override
	public ShortUrlDto select(final ShortUrlPrimaryDto primary) {
		
		ObjectInspection.checkNull(primary);
		final SelectPrepare select = PrepareBuilder
			.select(ShortUrlProp.TABLE_NAME)
			.where(ShortUrlProp.API, Comparison.EQUAL, primary.getApi())
			.where(ShortUrlProp.DATA, Comparison.EQUAL, primary.getData())
			.build();
		
		return this.template.select(select, this.mapping.rowMapper());
	}
	
	public ShortUrlDto selectOfUrl(String url) {
		
		return super.selectOfUniqueKey(ShortUrlProp.URL, url);
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * @author 2017. 7. 23. 오후 8:28:10 jeong
	 */
	@Override
	public void update(final int targetShortUrlKey, final String column, final Object value) {
		
		IntegerInspection.checkBelowZero(targetShortUrlKey);
		StringInspection.checkBlank(column);
		ObjectInspection.checkNull(value);
		SonarHelper.noStatic(this.select(targetShortUrlKey));
		final UpdatePrepare update = PrepareBuilder
			.update(ShortUrlProp.TABLE_NAME)
			.assign(column, value)
			.where(ShortUrlProp.KEY, Comparison.EQUAL, targetShortUrlKey)
			.build();
		
		this.template.update(update);
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * @author 2017. 7. 23. 오후 8:28:02 jeong
	 */
	@Override
	public void updateAll(final int targetShortUrlKey, final ShortUrlDto dto) {
		
		IntegerInspection.checkBelowZero(targetShortUrlKey);
		ObjectInspection.checkNull(dto);
		SonarHelper.noStatic(this.select(targetShortUrlKey));
		
		final UpdatePrepare update = PrepareBuilder
			.update(ShortUrlProp.TABLE_NAME)
			.assign(ShortUrlProp.URL, dto.getUrl())
			.assign(ShortUrlProp.API, dto.getApi())
			.assign(ShortUrlProp.DATA, dto.getData())
			.assign(ShortUrlProp.CREATED_TIME, dto.getCreatedTime())
			.where(ShortUrlProp.KEY, Comparison.EQUAL, targetShortUrlKey)
			.build();
		
		this.template.update(update);
	}
	
}
