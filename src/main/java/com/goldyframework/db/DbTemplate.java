/**
 * FileName : {@link DbTemplate}.java
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
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.goldyframework.db.exception.NoSingleDataException;
import com.goldyframework.db.prepare.statement.delete.DeletePrepare;
import com.goldyframework.db.prepare.statement.insert.InsertPrepare;
import com.goldyframework.db.prepare.statement.select.SelectPrepare;
import com.goldyframework.db.prepare.statement.update.UpdatePrepare;

/**
 * @author 2017. 7. 1. 오후 3:42:15 jeong
 */
@Component
public class DbTemplate extends JdbcTemplate {

	/**
	 * slf4j Logger
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(DbTemplate.class);

	/**
	 * {@link DbTemplate} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author 2017. 7. 1. 오후 3:45:45 jeong
	 * @param dataSource
	 *            {@link DataSource}
	 */
	@Autowired
	public DbTemplate(final DataSource dataSource) {
		super(dataSource);
	}

	/**
	 * @author 2017. 7. 2. 오후 5:52:24 jeong
	 * @param delete
	 */
	public void delete(final DeletePrepare delete) {

		this.update(delete.toPrepareSql(), this.toArray(delete.getArgs()));
	}

	/**
	 * @author 2017. 7. 2. 오후 12:29:00 jeong
	 * @param insert
	 */
	public void insert(final InsertPrepare insert) {

		final String prepareSql = insert.toPrepareSql();
		final Collection<Object> args = insert.getArgs();

		LOGGER.trace(prepareSql);
		LOGGER.trace(args.toString());
		this.update(prepareSql, this.toArray(args));
	}

	/**
	 * @author 2017. 7. 8. 오후 11:49:05 jeong
	 * @param select
	 * @param mapper
	 * @return
	 * @throws NoSingleDataException
	 */
	public <T> T select(final SelectPrepare select, final RowMapper<T> mapper) throws NoSingleDataException {

		LOGGER.trace(select.toPrepareSql());
		try {
			return super.queryForObject(select.toPrepareSql(), this.toArray(select.getArgs()), mapper);
		} catch (final EmptyResultDataAccessException e) {
			final String message = MessageFormat.format("쿼리 [{0}]에 해당하는 값이 1개가 아닙니다.", select.toPrepareSql()); //$NON-NLS-1$
			throw new NoSingleDataException(message, e);
		}
	}

	/**
	 * @author 2017. 7. 8. 오후 11:38:59 jeong
	 * @param select
	 * @param mapper
	 * @return
	 */
	public <T> Collection<T> selectAll(final SelectPrepare select, final RowMapper<T> mapper) {

		return super.query(select.toPrepareSql(), mapper);
	}

	/**
	 * @author 2017. 7. 10. 오후 10:50:58 jeong
	 * @param select
	 * @param createVoMapper
	 * @return
	 */
	public <T> Collection<T> selectPart(final SelectPrepare select, final RowMapper<T> createVoMapper) {

		return super.query(select.toPrepareSql(), this.toArray(select.getArgs()), createVoMapper);
	}

	private Object[] toArray(final Collection<Object> target) {

		return target.toArray(new Object[target.size()]);
	}

	/**
	 * @author 2017. 7. 3. 오후 11:05:27 jeong
	 * @param update
	 * @return
	 */
	public void update(final UpdatePrepare update) {

		this.update(update.toPrepareSql(), this.toArray(update.getArgs()));
	}

}
