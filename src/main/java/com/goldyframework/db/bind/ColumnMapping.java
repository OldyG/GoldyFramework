/**
 * FileName : {@link ColumnMapping}.java
 * Created : 2017. 6. 30. 오후 8:57:06
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Goldy Project. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 (주)Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.db.bind;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import com.goldyframework.utils.StringCollectionGtils;

/**
 * @author 2017. 6. 30. 오후 8:57:06 jeong
 */
public class ColumnMapping extends LinkedHashMap<String, ResultSetBinder<Object>> {
	
	/**
	 * Serializable UID
	 */
	private static final long serialVersionUID = -2715338087992385635L;
	
	private final String schemaName;
	
	private final String tableName;
	
	/**
	 * {@link ColumnMapping} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author 2017. 7. 1. 오후 4:24:50 jeong
	 * @param schemaName
	 * @param tableName
	 */
	public ColumnMapping(final String schemaName, final String tableName) {
		super();
		this.schemaName = schemaName;
		this.tableName = tableName;
	}
	
	public <T> T bind(final ResultSet rs, final String column) throws SQLException {
		
		return (T) super.get(column).bind(rs, column);
	}
	
	/**
	 * 주어진 column이 등록된었는지 검사 후 table.column 형태로 반환합니다.
	 *
	 * @author 2017. 7. 1. 오후 4:53:50 jeong
	 * @param key
	 *            가져오는 키
	 * @return 결과 값
	 */
	public String getValidFullColumn(final String key) {
		
		if (this.containsKey(key) == false) {
			throw new IllegalArgumentException(key + "가 존재하지 않습니다."); //$NON-NLS-1$
		}
		
		return new StringBuilder()
			.append(this.tableName)
			.append('.')
			.append(key)
			.toString();
	}
	
	public String joinFullColumn() {
		
		final Set<String> keys = super.keySet();
		final List<String> eachPrepend = StringCollectionGtils.eachPrepend(this.tableName + '.', keys);
		return StringCollectionGtils.join(eachPrepend, ", "); //$NON-NLS-1$
		
	}
	
	/**
	 * @author 2017. 7. 1. 오후 6:18:41 jeong
	 * @param loginType
	 * @param value
	 */
	public void put(final String loginType, final IBinder<?> value) {
		
		super.put(loginType, (rs, column) -> value.bind(rs.getString(column)));
	}
	
}
