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

import java.util.Collection;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.goldyframework.db.prepare.DeletePrepare;
import com.goldyframework.db.prepare.InsertPrepare;

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

	private Object[] toArray(final Collection<Object> target) {

		return target.toArray(new Object[target.size()]);
	}

}
