/**
 * FileName : DbSource.java
 * Created : 2017. 4. 10.
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Goldy Project. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.util.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * DB 접속에 공통으로 사용되는 클래스
 */
@Component
public class DbSource {

	private static final String EMPTY_STRING = ""; //$NON-NLS-1$

	/**
	 * slf4j Logger
	 *
	 * @author jeong
	 * @since 2017. 5. 22. 오후 9:20:02
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(DbSource.class);

	@Autowired
	private DataSource ds;

	private Connection con;

	private ResultSet resultSet;

	private PreparedStatement stmt;

	/**
	 * DbSource 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author jeong
	 * @since 2017. 3. 5. 오후 2:01:12
	 */
	public DbSource() {}

	/**
	 * DbSource 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author jeong
	 * @since 2017. 4. 10. 오후 9:30:15
	 * @param dataSource
	 */
	public DbSource(final DataSource dataSource) {
		this.ds = dataSource;
	}

	/**
	 * 쿼리 실행을 알립니다.
	 * 이 선언을 할 경우 반드시 {@link #commit()} 또는 {@link #rollback()}를 선언해야합니다.
	 *
	 * @author jeonghyun.kum
	 * @throws SQLException
	 * @since 2016. 5. 7. 오후 9:14:40
	 */
	public void begin() throws SQLException {

		this.update("begin work;"); //$NON-NLS-1$
	}

	void close() {

		try {
			if (this.resultSet != null) {
				this.resultSet.close();
			}
		} catch (final SQLException e) {
			LOGGER.error("ResultSet을 닫지 못했습니다." + e.getMessage(), e); //$NON-NLS-1$
		}

		try {
			if (this.stmt != null) {
				this.stmt.close();
			}
		} catch (final SQLException e) {
			LOGGER.error("PreparedStatement를 닫지 못했습니다." + e.getMessage(), e); //$NON-NLS-1$
		}

		this.closeConnection();
	}

	private void closeConnection() {

		try {
			if (this.con != null) {
				this.con.close();
			}
		} catch (final SQLException e) {
			LOGGER.error("DB Connection을 닫지 못했습니다." + e.getMessage(), e); //$NON-NLS-1$
		}
	}

	/**
	 * 쿼리가 완료됨을 알립니다.
	 * 이 선언을 알리기 전엔 {@link #begin()}을 기준으로 완료됩니다.
	 *
	 * @author jeonghyun.kum
	 * @throws SQLException
	 * @since 2016. 5. 7. 오후 9:17:31
	 */
	public void commit() throws SQLException {

		this.update("commit;"); //$NON-NLS-1$
	}

	public synchronized boolean getBoolean(final String sql) throws SQLException {

		final Collection<String> list = this.getColumnString(sql);
		return !list.isEmpty();
	}

	public synchronized Collection<Integer> getColumnIntegerList(final String sql) throws SQLException {

		final Collection<String> columnStringList = this.getColumnString(sql);

		final Collection<Integer> returnTarget = new LinkedList<>();
		try {
			for (final String columnString : columnStringList) {
				returnTarget.add(Integer.parseInt(columnString));
			}
		} catch (final NumberFormatException e) {
			throw new SQLException("데이터가 Integer 타입이 아닙니다.", e); //$NON-NLS-1$
		}
		return returnTarget;
	}

	public synchronized Collection<String> getColumnString(final String sql) throws SQLException {

		final Collection<String> defines = new ArrayList<>();
		try {
			@SuppressWarnings("resource")
			final ResultSet rs = this.querySQL(sql);

			while (rs.next()) {
				final String define = (rs.getString(1) == null) ? EMPTY_STRING : rs.getString(1);
				defines.add(define.trim());
			}
		} finally {
			this.close();
		}

		return defines;
	}

	public synchronized int getInteger(final String sql) throws SQLException, NotSingleException {

		final List<Integer> values = new ArrayList<>(this.queryForIntegerList(sql));
		if (values.isEmpty() || (values.size() > 2)) {
			throw new NotSingleException();
		}
		return values.get(0).intValue();
	}

	public synchronized Collection<Integer> getRowIntegerList(final String sql) throws SQLException {

		final Collection<String> rowStringList = this.getRowString(sql);

		final Collection<Integer> returnTarget = new LinkedList<>();
		try {
			for (final String rowString : rowStringList) {
				returnTarget.add(Integer.parseInt(rowString));
			}
		} catch (final NumberFormatException e) {
			throw new SQLException("데이터가 Integer 타입이 아닙니다.", e); //$NON-NLS-1$
		}

		return returnTarget;
	}

	public synchronized Collection<String> getRowString(final String sql) throws SQLException {

		final Collection<Collection<String>> arrayList = this.getTable(sql);

		Collection<String> array = new LinkedList<>();
		if (arrayList.isEmpty() == false) {
			array = arrayList.iterator().next();
		}

		return array;
	}

	public synchronized String getString(final String sql) throws SQLException, NotSingleException {

		final List<String> results = new ArrayList<>(this.getColumnString(sql));

		if (results.isEmpty() || (results.size() > 2)) {
			throw new NotSingleException();
		}

		return results.get(0);
	}

	public synchronized Collection<Collection<String>> getTable(final String sql) throws SQLException {

		final Collection<Collection<String>> defines = new ArrayList<>();
		try {

			@SuppressWarnings("resource")
			final ResultSet rs = this.querySQL(sql);
			final int size = rs.getMetaData().getColumnCount();
			while (rs.next()) {

				final LinkedList<String> tt = new LinkedList<>();

				for (int i = 0; i < size; i++) {
					tt.add(rs.getString(i + 1));
				}

				defines.add(tt);
			}
		} finally {
			this.close();
		}

		return defines;
	}

	public synchronized Collection<Integer> queryForIntegerList(final String sql) throws SQLException {

		final Collection<Integer> results = new ArrayList<>();
		try {
			@SuppressWarnings("resource")
			final ResultSet rs = this.querySQL(sql);

			while (rs.next()) {
				final int value = rs.getInt(1);
				results.add(Integer.valueOf(value));
			}
		} finally {
			this.close();
		}

		return results;
	}

	synchronized ResultSet querySQL(final String sql) throws SQLException {

		LOGGER.trace(sql);
		try {
			this.con = this.ds.getConnection();
			this.stmt = this.con.prepareStatement(sql);
			this.resultSet = this.stmt.executeQuery();

			return this.resultSet;
		} catch (final SQLException e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		}
	}

	// update , insert , delete 할때 결과값을 받아오기 위해 만듬
	int queryUpdateSQL(final String sql) throws SQLException {

		LOGGER.trace(sql);
		this.con = this.ds.getConnection();
		this.stmt = this.con.prepareStatement(sql);

		return this.stmt.executeUpdate();
	}

	/**
	 * 쿼리가 취소됨을 알립니다.
	 * 이는 {@link #begin()}이 후 오류가 발생하였을때 사용합니다.
	 *
	 * @author jeonghyun.kum
	 * @throws SQLException
	 * @since 2016. 5. 7. 오후 9:18:24
	 */
	public void rollback() throws SQLException {

		this.update("rollback work;"); //$NON-NLS-1$
	}

	// update , insert , delete 할때 결과값을 받아오기 위해 만듦
	public synchronized void update(final String sql) throws SQLException {

		try {
			final int result = this.queryUpdateSQL(sql);
			if ((result > 0) == false) {
				final Collection<String> notList = Arrays.asList("commit;", "begin work;", "rollback work;"); //$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$
				if (notList.contains(sql) == false) {
					throw new SQLException("Fail for " + sql); //$NON-NLS-1$
				}
			}
		} finally {
			this.close();
		}
	}

}
