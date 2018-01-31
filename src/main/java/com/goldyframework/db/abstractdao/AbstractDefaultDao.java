/**
 * FileName : {@link AbstractDefaultDao}.java
 * Created : 2018. 1. 30. 오후 9:50:40
 * Author : jeong
 * Summary :
 * Copyright (C) 2018 Formal Works Inc. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 (주)포멀웍스에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.db.abstractdao;

import java.text.MessageFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.goldyframework.db.JdbcPrepareTemplate;
import com.goldyframework.db.bind.AbstractMapping;
import com.goldyframework.db.exception.DuplicateRecordException;
import com.goldyframework.db.inf.DefaultDao;
import com.goldyframework.db.inf.Dto;
import com.goldyframework.db.prepare.PrepareBuilder;
import com.goldyframework.db.prepare.statement.delete.DeletePrepare;
import com.goldyframework.db.prepare.statement.guide.Comparison;
import com.goldyframework.db.prepare.statement.select.SelectPrepare;
import com.goldyframework.db.prepare.statement.update.UpdatePrepare;
import com.goldyframework.does.SonarHelper;
import com.goldyframework.inspection.IntegerInspection;
import com.goldyframework.inspection.ObjectInspection;
import com.goldyframework.inspection.StringInspection;
import com.goldyframework.utils.StringCollectionGtils;

public abstract class AbstractDefaultDao<DTO extends Dto> implements DefaultDao<DTO> {
	
	/**
	 * SQL을 전송하는 템플릿
	 */
	@Autowired
	private JdbcPrepareTemplate template;
	
	private final String tableKey;
	
	private final String tableName;
	
	private final AbstractMapping<DTO> mapping;
	
	/**
	 * {@link AbstractDefaultDao} 클래스의 새 인스턴스를 초기화 합니다.
	 * 
	 * @author 2018. 1. 30. 오후 9:52:22 jeong
	 */
	public AbstractDefaultDao(final String tableName, final String tableKey, final AbstractMapping<DTO> mapping) {
		
		super();
		this.tableKey = tableKey;
		this.tableName = tableName;
		this.mapping = mapping;
		
	}
	
	/**
	 * {@inheritDoc}
	 * 
	 * @author 2018. 1. 30. 오후 9:51:19 jeong
	 */
	@Override
	public void delete(final int key) {
		
		IntegerInspection.checkBelowZero(key);
		final DeletePrepare delete = PrepareBuilder
			.delete(this.tableName)
			.where(this.tableKey, Comparison.EQUAL, key)
			.build();
		this.template.delete(delete);
		
	}
	
	/**
	 * {@inheritDoc}
	 * 
	 * @author 2018. 1. 30. 오후 9:51:19 jeong
	 */
	@Override
	public abstract DTO insert(final DTO dto) throws DuplicateRecordException;
	
	/**
	 * {@inheritDoc}
	 * 
	 * @author 2018. 1. 30. 오후 9:51:19 jeong
	 */
	@Override
	public DTO select(final int key) {
		
		IntegerInspection.checkBelowZero(key);
		final SelectPrepare select = PrepareBuilder
			.select(this.tableName)
			.where(this.tableKey, Comparison.EQUAL, key)
			.build();
		
		return this.template.select(select, this.mapping.rowMapper());
	}
	
	/**
	 * {@inheritDoc}
	 * 
	 * @author 2018. 1. 30. 오후 9:51:19 jeong
	 */
	@Override
	public Collection<DTO> selectAll() {
		
		final SelectPrepare select = PrepareBuilder
			.select(this.tableName)
			.build();
		
		return this.template.selectAll(select, this.mapping.rowMapper());
	}
	
	protected <T> Collection<DTO> selectsOf(final String columnName, final T value) {
		
		final SelectPrepare select = PrepareBuilder
			.select(this.tableName)
			.where(columnName, Comparison.EQUAL, value)
			.build();
		
		return this.template.selectPart(select, this.mapping.rowMapper());
	}
	
	protected <T> Collection<DTO> selectsOfs(final String columnName,
		final Collection<T> tableKeyCollection) {
		
		ObjectInspection.checkNull(tableKeyCollection);
		
		if (tableKeyCollection.size() == 0) {
			return Collections.emptyList();
		}
		
		final Set<Object> tableKeySet = new HashSet<>(tableKeyCollection);
		
		final List<String> collect = tableKeySet.stream().map(themeKey -> "?").collect(Collectors.toList());
		
		final String quesions = StringCollectionGtils.join(collect, ", ");
		final String sql = MessageFormat.format("SELECT * FROM {0} WHERE {0}.{1} in ({2})",
			this.tableName, columnName, quesions);
		final Object[] args = tableKeySet.toArray(new Object[tableKeySet.size()]);
		return this.template.query(sql, args, this.mapping.rowMapper());
	}
	
	public Collection<DTO> selects(final Collection<Integer> tableKeyCollection) {
		
		return this.selectsOfs(this.tableKey, tableKeyCollection);
	}
	
	/**
	 * {@inheritDoc}
	 * 
	 * @author 2018. 1. 30. 오후 9:51:19 jeong
	 */
	@Override
	public void update(final int key, final String column, final Object value) {
		
		IntegerInspection.checkBelowZero(key);
		StringInspection.checkBlank(column);
		ObjectInspection.checkNull(value);
		SonarHelper.noStatic(this.select(key));
		
		final UpdatePrepare update = PrepareBuilder
			.update(this.tableName)
			.assign(column, value)
			.where(this.tableKey, Comparison.EQUAL, key)
			.build();
		
		this.template.update(update);
	}
	
	/**
	 * {@inheritDoc}
	 * 
	 * @author 2018. 1. 30. 오후 9:51:19 jeong
	 */
	@Override
	public abstract void updateAll(final int key, final DTO dto);
	
}
