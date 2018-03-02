/**
 * FileName : {@link LastAutoIncrementPreparePlan}.java
 * Created : 2018. 2. 5. 오후 11:10:35
 * Author : jeong
 * Summary :
 * Copyright (C) 2018 Formal Works Inc. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.db.prepare.statement.select;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.goldyframework.db.JdbcPrepareTemplate;

public class LastAutoIncrementPreparePlan {
	
	private final String databaseName;
	
	private final String tableName;
	
	public LastAutoIncrementPreparePlan(String databaseName, String tableName) {
		
		super();
		this.databaseName = databaseName;
		this.tableName = tableName;
		
	}
	
	public int get(JdbcPrepareTemplate template) {
		
		String sql = "SELECT `AUTO_INCREMENT` FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = '"
			+ this.databaseName + "' AND TABLE_NAME = '" + this.tableName + "'";
		return template.queryForObject(sql, new RowMapper<Integer>() {
			
			@Override
			public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				return rs.getInt("AUTO_INCREMENT");
			}
			
		});
		
	}
	
}
