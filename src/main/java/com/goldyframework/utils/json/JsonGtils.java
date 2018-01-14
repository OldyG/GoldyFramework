/**
 * FileName : {@link JsonGtils}.java
 * Created : 2017. 4. 10.
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Goldy Project. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.utils.json;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.goldyframework.inspection.ObjectInspection;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Json의 공통 작업 목록
 *
 * @author 2017. 6. 18. 오후 1:15:23 jeong
 */
public final class JsonGtils {
	
	/**
	 * Jackson 객체
	 */
	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
	
	/**
	 * Google 일반 Gson 객체
	 */
	private static final Gson DEFAULT_GSON;
	
	/**
	 * Google HTML Escpae를 하지않는 Gson 객체
	 */
	private static final Gson NON_ESCAPE_GSON;
	
	/**
	 * Google toString()용 Gson 객체
	 */
	private static final Gson PRETTY_GSON;
	
	/**
	 * slf4j Logger
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(JsonGtils.class);
	
	static {
		
		DEFAULT_GSON = new Gson();
		
		NON_ESCAPE_GSON = new GsonBuilder()
			.disableHtmlEscaping()
			.create();
		
		PRETTY_GSON = new GsonBuilder()
			.disableHtmlEscaping()
			.setPrettyPrinting()
			.create();
	}
	
	/**
	 * Gson을 사용하여 {@link String} 문자열을 주어진 클래스 타입으로 변환합니다.
	 * setter 함수와 무관하게 필드에 직접 접근하여 값을 초기화합니다.
	 *
	 * @author 2017. 6. 18. 오후 1:16:13 jeong
	 * @param <T>
	 *            클래스 타입
	 * @param json
	 *            문자열 Json
	 * @param clazzOfT
	 *            변환 대상 클래스 타입
	 * @return 적용된 클래스
	 */
	@SuppressWarnings("null")
	public static <T> T fromGson(final String json, final Class<T> clazzOfT) {
		
		ObjectInspection.checkNull(json);
		ObjectInspection.checkNull(clazzOfT);
		
		try {
			return DEFAULT_GSON.fromJson(json, clazzOfT);
		} catch (final RuntimeException e) {
			LOGGER.error("fromGson을 수행중 오류 발생", e); 
			return null;
		}
	}
	
	/**
	 * Jackson을 사용하여 {@link String} 문자열을 주어진 클래스 타입으로 변환합니다.<br>
	 * 각 필드의 setter 함수를 통해 필드 값을 초기화합니다.<br>
	 * <br>
	 * 만약 {'name' : 'namevalue'}를 객체화하기 위해서는 public void setName(String arg1){} 함수가 반드시 정의되어있어야 합니다.
	 * set_name, setNAme 등으로 정의되어있다면 초기화가 불가능합니다.
	 * <br>
	 * Jackson은 Gson에 비해 속도가 빠르며 대용량 처리에 탁월합니다.
	 *
	 * @author 2017. 6. 18. 오후 1:16:13 jeong
	 * @param <T>
	 *            클래스 타입
	 * @param json
	 *            문자열 Json
	 * @param clazzOfT
	 *            변환 대상 클래스 타입
	 * @return 적용된 클래스
	 */
	public static <T> T fromJackson(final String json, final Class<T> clazzOfT) {
		
		ObjectInspection.checkNull(json);
		ObjectInspection.checkNull(clazzOfT);
		try {
			return OBJECT_MAPPER.readValue(json, clazzOfT);
		} catch (final IOException e) {
			LOGGER.error("fromJackson을 수행중 오류 발생", e); 
			return null;
		}
	}
	
	/**
	 * Gson을 사용하여 객체의 값을 필드를 읽습니다.
	 * getter 함수와 무관하게 직접 필드에서 값을 읽어옵니다.
	 *
	 * @author 2017. 6. 17. 오후 8:53:08 jeong
	 * @param object
	 *            변경대상 객체
	 * @return 변형된 객체
	 */
	public static String toGson(final Object object) {
		
		ObjectInspection.checkNull(object);
		try {
			final String json = DEFAULT_GSON.toJson(object);
			
			if ((json == null) || (json.equals("null"))) { 
				return "{}"; 
			}
			return json;
			
		} catch (final RuntimeException e) {
			LOGGER.error("toGson을 수행중 오류 발생", e); 
			return "[ERROR] JsonGtils.toGoson"; 
		}
	}
	
	/**
	 * @author 2017. 6. 30. 오후 6:58:40 jeong
	 * @param object
	 *            변경대상 객체
	 * @return 변형된 객체
	 */
	public static String toGsonNonHtmlEscaping(final Object object) {
		
		ObjectInspection.checkNull(object);
		
		try {
			return NON_ESCAPE_GSON.toJson(object);
		} catch (final RuntimeException e) {
			LOGGER.error("toGsonPretty을 수행중 오류 발생", e); 
			return "[ERROR] JsonGtils.toGsonNonHtmlEscaping"; 
		}
	}
	
	/**
	 * @author 2017. 6. 30. 오후 6:58:41 jeong
	 * @param object
	 *            변경대상 객체
	 * @return 변형된 객체
	 */
	public static String toGsonPretty(final Object object) {
		
		ObjectInspection.checkNull(object);
		
		try {
			return PRETTY_GSON.toJson(object);
		} catch (final RuntimeException e) {
			LOGGER.error("toGsonPretty을 수행중 오류 발생", e); 
			return "[ERROR] JsonGtils.toGsonPretty"; 
		}
	}
	
	/**
	 * 객체를 값을 Getter, Setter를 통하여 읽습니다
	 *
	 * @author 2017. 6. 17. 오후 8:52:44 jeong
	 * @param object
	 *            변경대상 객체
	 * @return 변형된 객체
	 */
	public static String toJackson(final Object object) {
		
		ObjectInspection.checkNull(object);
		
		try {
			return OBJECT_MAPPER.writeValueAsString(object);
		} catch (final JsonProcessingException e) {
			LOGGER.error("toJackson을 수행중 오류 발생", e); 
			return "[ERROR] JsonGtils.toJackson"; 
		}
	}
	
	/**
	 * {@link JsonGtils} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author jeong
	 * @since 2017. 5. 22. 오후 9:42:17
	 */
	private JsonGtils() {
		
		throw new IllegalStateException("Utility class"); 
	}
}
