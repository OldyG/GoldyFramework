/**
 * FileName : {@link JdbcPrepareTemplate}.java
 * Created : 2017. 7. 1. 오후 3:42:15
 * Author : jeong
 * Summary :
 * Copyright (C) 2018 Formal Works Inc. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.db;

import java.text.MessageFormat;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

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
import com.goldyframework.db.prepare.statement.delete.DeletePrepare;
import com.goldyframework.db.prepare.statement.insert.InsertPrepare;
import com.goldyframework.db.prepare.statement.select.SelectPrepare;
import com.goldyframework.db.prepare.statement.update.UpdatePrepare;
import com.goldyframework.does.SonarHelper;
import com.goldyframework.utils.json.JsonGtils;

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
	public JdbcPrepareTemplate(DataSource dataSource) {
		
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
	
	public void delete(DeletePrepare delete) {
		
		super.update(delete.toPrepareSql(), this.toArray(delete.getArgs()));
	}
	
	public void insert(InsertPrepare insert) throws DuplicateRecordException {
		
		String prepareSql = insert.toPrepareSql();
		Collection<Object> args = insert.getArgs();
		
		try {
			super.update(prepareSql, this.toArray(args));
			this.writeLogArgs(args);
		} catch (DuplicateKeyException e) {
			String message = MessageFormat.format("{0}테이블에 중복 데이터가 존재합니다.", insert.getTableName());
			throw new DuplicateRecordException(message, e);
		}
	}
	
	/**
	 * @author 2017. 7. 13. 오후 7:17:23 jeong
	 */
	public void rollback() {
		
		super.update("rollback work;");
	}
	
	public <T> T select(SelectPrepare select, RowMapper<T> mapper) {
		
		LOGGER.trace(select.toPrepareSql());
		try {
			return super.queryForObject(select.toPrepareSql(), this.toArray(select.getArgs()), mapper);
		} catch (IncorrectResultSizeDataAccessException e) {
			
			String message = MessageFormat.format("쿼리 [{0}]에 해당하는 값이 1개가 아닙니다. : {1}",
				select.toPrepareSql(), e.getMessage()); // $NON-NLS-1$
			throw new NoSingleDataException(message, e);
		}
	}
	
	public <T> Set<T> selectAll(SelectPrepare select, RowMapper<T> mapper) {
		
		return new HashSet<>(super.query(select.toPrepareSql(), mapper));
	}
	
	public <T> Set<T> selectPart(SelectPrepare select, RowMapper<T> createVoMapper) {
		
		return new HashSet<>(super.query(select.toPrepareSql(), this.toArray(select.getArgs()), createVoMapper));
	}
	
	/**
	 * {@link List}를 Array로 치환합니다.
	 *
	 * @author 2017. 7. 23. 오후 3:41:35 jeong
	 * @param target
	 *            치환 대상 {@link List}
	 * @return
	 */
	private Object[] toArray(Collection<Object> target) {
		
		SonarHelper.noStatic(this);
		return target.toArray(new Object[target.size()]);
	}
	
	/**
	 * @author 2017. 7. 3. 오후 11:05:27 jeong
	 * @param update
	 * @return
	 */
	public void update(UpdatePrepare update) {
		
		if (update.isValid() == false) {
			return;
		}
		
		super.update(update.toPrepareSql(), this.toArray(update.getArgs()));
	}
	
	private void writeLogArgs(Collection<Object> args) {
		
		LOGGER.trace(JsonGtils.toGsonPretty(args));
	}
	
}
