package com.goldyframework.db.cache;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.goldyframework.inspection.ObjectInspection;

public class IntegerSetCache extends HashSet<Integer> {

	/**
	 * 구분기호
	 */
	private static final String DIV = ";";

	private static final long serialVersionUID = -8893546213438993054L;

	/**
	 * {@link IdListCache} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author 2018. 1. 13. 오후 5:17:42 jeong
	 */
	public IntegerSetCache(final String cacheValue) {

		super();
		ObjectInspection.checkNull(cacheValue);
		super.addAll(this.toSet(cacheValue));
	}

	public void add(final Collection<Integer> integerList) {

		super.addAll(integerList);
	}

	public void add(final int integer) {

		super.add(integer);
	}

	public boolean contains(final int integer) {

		return super.contains(integer);
	}

	public void remove(final int integer) {

		super.remove(integer);
	}

	@Override
	public int size() {

		return super.size();
	}

	public String toCacheString() {

		final StringBuilder builder = new StringBuilder();

		final List<Integer> copiedList = new ArrayList<>(this);

		copiedList.sort((a, b) -> Integer.compare(a, b));

		for (final Integer integer : this) {
			final String strInteger = integer.toString();
			builder.append(strInteger).append(DIV);
		}
		return builder.toString();
	}

	public List<Integer> toList() {

		return new ArrayList<>(this);
	}

	private Set<Integer> toSet(final String str) {

		final String[] strArray = str.split(DIV); // $NON-NLS-1$

		final List<String> strList = Arrays.asList(strArray);

		return strList.stream()
			.map(integer -> Integer.parseInt(integer))
			.collect(Collectors.toSet());
	}

}
