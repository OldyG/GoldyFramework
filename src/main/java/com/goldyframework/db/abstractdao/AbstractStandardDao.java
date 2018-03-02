/**
 * FileName : {@link AbstractStandardDao}.java
 * Created : 2018. 1. 30. 오후 10:01:49
 * Author : jeong
 * Summary :
 * Copyright (C) 2018 Formal Works Inc. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.db.abstractdao;

import com.goldyframework.db.bind.AbstractMapping;
import com.goldyframework.db.exception.DuplicateRecordException;
import com.goldyframework.db.inf.Dto;
import com.goldyframework.db.inf.PrimaryDto;
import com.goldyframework.db.inf.StandardDao;

public abstract class AbstractStandardDao<DTO extends Dto, PDTO extends PrimaryDto>
	extends AbstractDefaultDao<DTO>
	implements StandardDao<DTO, PDTO> {
	
	/**
	 * {@link AbstractStandardDao} 클래스의 새 인스턴스를 초기화 합니다.
	 * 
	 * @author 2018. 1. 30. 오후 10:03:22 jeong
	 */
	public AbstractStandardDao(String tableName, String tableKey, AbstractMapping<DTO> mapping) {
		
		super(tableName, tableKey, mapping);
	}
	
	/**
	 * {@inheritDoc}
	 * 
	 * @author 2018. 1. 30. 오후 10:02:42 jeong
	 */
	@Override
	public abstract void delete(PDTO primary);
	
	/**
	 * {@inheritDoc}
	 * 
	 * @author 2018. 1. 30. 오후 10:02:42 jeong
	 */
	@Override
	public abstract DTO insert(DTO dto) throws DuplicateRecordException;
	
	/**
	 * {@inheritDoc}
	 * 
	 * @author 2018. 1. 30. 오후 10:02:42 jeong
	 */
	@Override
	public abstract DTO select(PDTO primary);
	
	/**
	 * {@inheritDoc}
	 * 
	 * @author 2018. 1. 30. 오후 10:02:42 jeong
	 */
	@Override
	public abstract void updateAll(int key, DTO dto);
	
}
