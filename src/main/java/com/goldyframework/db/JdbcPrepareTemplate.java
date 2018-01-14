/**
 * FileName : {@link JdbcPrepareTemplate}.java
 * Created : 2017. 7. 1. 오후 3:42:15
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Goldy Project. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 (주)Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.db;

import java.text.MessageFormat;
import java.util.Collection;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.goldyframework.db.exception.DuplicateRecordException;
import com.goldyframework.db.exception.NoSingleDataException;
import com.goldyframework.db.prepare.statement.Prepare;
import com.goldyframework.db.prepare.statement.delete.DeletePrepare;
import com.goldyframework.db.prepare.statement.insert.InsertPrepare;
import com.goldyframework.db.prepare.statement.select.SelectPrepare;
import com.goldyframework.db.prepare.statement.update.UpdatePrepare;

/**
 * {@link JdbcTemplate} 클래스를 상속받은 클래스로 {@link Prepare} 클래스에 호환되는 클래스
 *
 * @author 2017. 7. 1. 오후 3:42:15 jeong
 */
@Service
public class JdbcPrepareTemplate extends JdbcTemplate {
	
	/**
	 * slf4j Logger
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(JdbcPrepareTemplate.class);
	
	/**
	 * {@link JdbcPrepareTemplate} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author 2017. 7. 1. 오후 3:45:45 jeong
	 * @param dataSource
	 *            {@link DataSource}
	 */
	@Autowired
	public JdbcPrepareTemplate(final DataSource dataSource) {
		
		super(dataSource);
	}
	
	/**
	 * @author 2017. 7. 30. 오후 3:15:57 jeong
	 */
	public void begin() {
		
		super.update("begin work;"); 
		
	}
	
	/**
	 * @author 2017. 7. 13. 오후 7:17:29 jeong
	 */
	public void commit() {
		
		super.update("commit;"); 
	}
	
	/**
	 * @author 2017. 7. 2. 오후 5:52:24 jeong
	 * @param delete
	 */
	public void delete(final DeletePrepare delete) {
		
		super.update(delete.toPrepareSql(), this.toArray(delete.getArgs()));
	}
	
	/**
	 * @author 2017. 7. 2. 오후 12:29:00 jeong
	 * @param insert
	 */
	public void insert(final InsertPrepare insert) throws DuplicateRecordException {
		
		final String prepareSql = insert.toPrepareSql();
		final Collection<Object> args = insert.getArgs();
		
		try {
			super.update(prepareSql, this.toArray(args));
		} catch (final DuplicateKeyException e) {
			final String message = MessageFormat.format("{0}테이블에 중복 데이터가 존재합니다.", insert.getTableName()); 
			throw new DuplicateRecordException(message, e);
		}
	}
	
	/**
	 * @author 2017. 7. 13. 오후 7:17:23 jeong
	 */
	public void rollback() {
		
		super.update("rollback work;"); 
	}
	
	/**
	 * @author 2017. 7. 8. 오후 11:49:05 jeong
	 * @param <T>
	 * @param select
	 * @param mapper
	 * @return
	 */
	public <T> T select(final SelectPrepare select, final RowMapper<T> mapper) {
		
		LOGGER.trace(select.toPrepareSql());
		try {
			return super.queryForObject(select.toPrepareSql(), this.toArray(select.getArgs()), mapper);
		} catch (final IncorrectResultSizeDataAccessException e) {
			
			final String message = MessageFormat.format("쿼리 [{0}]에 해당하는 값이 1개가 아닙니다. : {1}", 
				select.toPrepareSql(), e.getMessage()); // $NON-NLS-1$
			throw new NoSingleDataException(message, e);
		}
	}
	
	/**
	 * @author 2017. 7. 8. 오후 11:38:59 jeong
	 * @param <T>
	 * @param select
	 * @param mapper
	 * @return
	 */
	public <T> Collection<T> selectAll(final SelectPrepare select, final RowMapper<T> mapper) {
		
		return super.query(select.toPrepareSql(), mapper);
	}
	
	/**
	 * @author 2017. 7. 10. 오후 10:50:58 jeong
	 * @param <T>
	 * @param select
	 * @param createVoMapper
	 * @return
	 */
	public <T> Collection<T> selectPart(final SelectPrepare select, final RowMapper<T> createVoMapper) {
		
		return super.query(select.toPrepareSql(), this.toArray(select.getArgs()), createVoMapper);
	}
	
	/**
	 * {@link List}를 Array로 치환합니다.
	 *
	 * @author 2017. 7. 23. 오후 3:41:35 jeong
	 * @param target
	 *            치환 대상 {@link List}
	 * @return
	 */
	private Object[] toArray(final Collection<Object> target) {
		
		return target.toArray(new Object[target.size()]);
	}
	
	/**
	 * @author 2017. 7. 3. 오후 11:05:27 jeong
	 * @param update
	 * @return
	 */
	public void update(final UpdatePrepare update) {
		
		if (update.isValid() == false) {
			return;
		}
		
		super.update(update.toPrepareSql(), this.toArray(update.getArgs()));
	}
	
}
