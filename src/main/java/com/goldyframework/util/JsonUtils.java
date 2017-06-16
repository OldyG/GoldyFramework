/**
 * FileName : JsonUtils.java
 * Created : 2017. 4. 10.
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Goldy Project. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

public class JsonUtils {
	
	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
	
	private static final Gson GSON = new Gson();
	
	public static final <T> T fromGson(final String json, final Class<T> clazzOfT) {
		
		return GSON.fromJson(json, clazzOfT);
	}
	
	public static final <T> T fromJackson(final String json, final Class<T> clazzOfT)
		throws JsonParseException, JsonMappingException, IOException {
		
		return OBJECT_MAPPER.readValue(json, clazzOfT);
	}
	
	public static final String toGson(final Object object) {
		
		return GSON.toJson(object);
	}
	
	public static final String toJackson(final Object object)
		throws JsonParseException, JsonMappingException, IOException {
		
		return OBJECT_MAPPER.writeValueAsString(object);
	}
	
	/**
	 * JsonUtils 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author jeong
	 * @since 2017. 5. 22. 오후 9:42:17
	 */
	public JsonUtils() {
		super();
	}
}
