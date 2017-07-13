/**
 * FileName : {@link WhereBuilder}.java
 * Created : 2017. 7. 2. 오후 5:29:03
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Goldy Project. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 (주)Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.db.prepare.statement;

import java.text.MessageFormat;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import com.goldyframework.inspection.ObjectInspection;
import com.goldyframework.utils.NullGtils;
import com.goldyframework.utils.StringCollectionGtils;
import com.google.common.annotations.VisibleForTesting;

/**
 * @author 2017. 7. 2. 오후 5:29:03 jeong
 */
public class WhereBuilder {

	@VisibleForTesting
	class ComparisonValue {

		private final Comparison comparison;

		private final Object value;

		/**
		 * {@link ComparisonValue} 클래스의 새 인스턴스를 초기화 합니다.
		 *
		 * @author 2017. 7. 2. 오후 9:55:42 jeong
		 * @param comparison
		 * @param value
		 */
		@VisibleForTesting
		ComparisonValue(final Comparison comparison, final Object value) {
			this.comparison = NullGtils.throwIfNull(comparison);
			this.value = NullGtils.throwIfNull(value);
		}

		/**
		 * comparison를 반환합니다.
		 *
		 * @return comparison
		 * @author 2017. 7. 2. 오후 10:00:41 jeong
		 * @see {@link #comparison}
		 */
		@VisibleForTesting
		Comparison getComparison() {

			return this.comparison;
		}

		/**
		 * value를 반환합니다.
		 *
		 * @return value
		 * @author 2017. 7. 2. 오후 9:57:16 jeong
		 * @see {@link #value}
		 */
		@VisibleForTesting
		Object getValue() {

			return this.value;
		}
	}

	private final Map<String, ComparisonValue> whereMap = new ConcurrentHashMap<>();

	private final String tableName;

	/**
	 * {@link WhereBuilder} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author 2017. 7. 2. 오후 6:03:36 jeong
	 * @param tableName
	 *            테이블 이름
	 */
	public WhereBuilder(final String tableName) {
		super();
		this.tableName = NullGtils.throwIfNull(tableName);
	}

	public void append(final String columnName, final Comparison comparison, final Object value) {

		ObjectInspection.checkNull(columnName);
		ObjectInspection.checkNull(comparison);
		ObjectInspection.checkNull(value);

		if (value != null) {
			if (value.getClass().isEnum()) {
				this.whereMap.put(columnName, new ComparisonValue(comparison, value.toString()));
			} else {
				this.whereMap.put(columnName, new ComparisonValue(comparison, value));
			}
		}

	}

	public String build() {
		
		final List<String> tableColumnList = this.createTableColumnList();
		
		return StringCollectionGtils.join(tableColumnList, " AND "); //$NON-NLS-1$
	}

	/**
	 * @author jeonghyun.kum
	 * @since 2017. 7. 13. 오전 10:29:29
	 * @return
	 */
	@VisibleForTesting
	List<String> createTableColumnList() {
		
		final List<String> appended = this.eachAppendComparisonValue();
		
		return StringCollectionGtils.eachPrepend(this.tableName + '.', appended);
	}

	@VisibleForTesting
	List<String> eachAppendComparisonValue() {

		final Map<String, ComparisonValue> copiedWhereMap = this.getCopiedWhereMap();

		return copiedWhereMap.keySet()
			.stream()
			.map(key -> {
				final ComparisonValue comparisonValue = copiedWhereMap.get(key);
				final String comparison = comparisonValue.getComparison().getComparison();

				return MessageFormat.format("{0} {1} {2}", key, comparison, '?'); //$NON-NLS-1$
			})
			.collect(Collectors.toList());
	}

	public Collection<Object> getArgs() {

		return this.whereMap.values()
			.stream()
			.map(ComparisonValue::getValue)
			.collect(Collectors.toList());
	}

	public Collection<String> getColumns() {

		return this.whereMap.keySet()
			.stream()
			.collect(Collectors.toList());
	}

	/**
	 * whereMap를 반환합니다.
	 *
	 * @return whereMap
	 * @author 2017. 7. 2. 오후 6:08:45 jeong
	 * @see {@link #whereMap}
	 */
	@VisibleForTesting
	Map<String, ComparisonValue> getCopiedWhereMap() {

		return new ConcurrentHashMap<>(this.whereMap);
	}

	/**
	 * @author 2017. 7. 9. 오전 12:06:38 jeong
	 * @return
	 */
	public boolean isEmpty() {

		return this.whereMap.isEmpty();
	}

}
