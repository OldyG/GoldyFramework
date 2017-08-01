/**
 * FileName : {@link ModelMapWithJson}.java
 * Created : 2017. 7. 31. 오후 7:32:20
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Goldy Project. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 (주)Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.jsp.modelcon;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.ui.ModelMap;

import com.goldyframework.utils.json.JsonGtils;

/**
 * @author 2017. 7. 31. 오후 7:32:20 jeong
 */
public class ModelMapWithJson {
	
	private final String JSON_KEY = "JSON_MODEL"; //$NON-NLS-1$
	
	private final Map<String, Object> map = new ConcurrentHashMap<>();
	
	private final ModelMap modelMap;
	
	/**
	 * {@link ModelMapWithJson} 클래스의 새 인스턴스를 초기화 합니다.
	 * 
	 * @author 2017. 7. 31. 오후 7:32:47 jeong
	 */
	public ModelMapWithJson(final ModelMap modelMap) {
		
		super();
		this.modelMap = modelMap;
	}
	
	/**
	 * @author 2017. 7. 31. 오후 7:34:43 jeong
	 * @param string
	 * @param default1
	 */
	public void addAttribute(final String attributeName, final Object attributeValue) {
		
		this.map.put(attributeName, attributeValue);
		this.modelMap.addAttribute(attributeName, attributeValue);
		this.modelMap.addAttribute(this.JSON_KEY, JsonGtils.toGson(this.map));
	}
	
}
