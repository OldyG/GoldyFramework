package com.goldyframework.db.cache;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.goldyframework.does.SonarHelper;
import com.goldyframework.inspection.ObjectInspection;

public class IntegerSetCache extends HashSet<Integer> {
	
	
	
	
	private static final long serialVersionUID = 42519105880589381L;
	/**
	 * 구분기호
	 */
	private static final String DIV = ";";
	
	/**
	 * {@link IdListCache} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author 2018. 1. 13. 오후 5:17:42 jeong
	 */
	public IntegerSetCache(String cacheValue) {
		
		super();
		ObjectInspection.checkNull(cacheValue);
		super.addAll(this.toSet(cacheValue));
	}
	
	public void add(Collection<Integer> integerList) {
		
		super.addAll(integerList);
	}
	
	public void add(int integer) {
		
		super.add(integer);
	}
	
	public boolean contains(int integer) {
		
		return super.contains(integer);
	}
	
	public void remove(int integer) {
		
		super.remove(integer);
	}
	
	@Override
	public int size() {
		
		return super.size();
	}
	
	public String toCacheString() {
		
		StringBuilder builder = new StringBuilder();
		
		List<Integer> copiedList = new ArrayList<>(this);
		
		copiedList.sort((a, b) -> Integer.compare(a, b));
		
		for (Integer integer : this) {
			String strInteger = integer.toString();
			builder.append(strInteger).append(DIV);
		}
		return builder.toString();
	}
	
	public List<Integer> toList() {
		
		return new ArrayList<>(this);
	}
	
	private Set<Integer> toSet(String str) {
		
		SonarHelper.noStatic(this);
		String[] strArray = str.split(DIV); // $NON-NLS-1$
		
		List<String> strList = Arrays.asList(strArray);
		
		return strList.stream()
			.map(integer -> Integer.parseInt(integer))
			.collect(Collectors.toSet());
	}
	
}
