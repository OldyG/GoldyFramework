/**
 * FileName : {@link Response}.java
 * Created : 2017. 4. 10.
 * Author : jeong
 * Summary :
 * Copyright (C) 2018 Formal Works Inc. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.response;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import com.goldyframework.inspection.ObjectInspection;
import com.goldyframework.utils.StringCollectionGtils;
import com.goldyframework.utils.json.JsonGtils;

/**
 * 반환 Response 방법이 문자열일 경우에 사용하는 객체입니다.
 * ResponseEntity 대신에 사용하는 이유는 "charset=UTF-8" 설정을 위해서 사용함
 *
 * @author jeonghyun.kum
 * @since 2016. 4. 23. 오후 8:42:54
 */
public class Response {
	
	/**
	 * slf4j Logger
	 *
	 * @author jeong
	 * @since 2017. 5. 22. 오후 9:20:02
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(Response.class);
	
	/**
	 * content type, charset 기본값
	 */
	private static final String CONTENT_TYPE = "{0};charset=UTF-8";
	
	public static ResponseEntity<String> badRequest(BindingResult bindingResult) {
		
		List<String> messages = bindingResult.getAllErrors()
			.stream()
			.map(ObjectError::getDefaultMessage)
			.collect(Collectors.toList());
		String message = StringCollectionGtils.join(messages, "<br>");
		return Response.badRequest(message);
	}
	
	/**
	 * 요청자에게 400 Bad Request으로 응답합니다.<br>
	 * <h1>[Note]</h1>
	 * <li>400(잘못된 요청): 서버가 요청의 구문을 인식하지 못했다.</li>
	 *
	 * @author jeonghyun.kum
	 * @param <T>
	 *            클래스 타입
	 * @since 2016. 4. 24. 오후 5:39:47
	 * @param body
	 *            응답 메세지
	 * @return {@link ResponseEntity}
	 */
	public static <T> ResponseEntity<T> badRequest(T body) {
		
		return Response.badRequest(body, MediaType.TEXT_PLAIN);
	}
	
	/**
	 * 요청자에게 400 Bad Request으로 응답합니다.<br>
	 * <h1>[Note]</h1>
	 * <li>400(잘못된 요청): 서버가 요청의 구문을 인식하지 못했다.</li>
	 *
	 * @author jeonghyun.kum
	 * @param <T>
	 *            클래스 타입
	 * @since 2016. 4. 24. 오후 5:39:47
	 * @param body
	 *            응답 메세지
	 * @param mediaType
	 *            {@link MediaType}
	 * @return {@link ResponseEntity}
	 */
	public static <T> ResponseEntity<T> badRequest(T body, MediaType mediaType) {
		
		return Response.send(body, HttpStatus.BAD_REQUEST, mediaType);
	}
	
	/**
	 * {@link HttpHeaders} 를 반환한다.
	 *
	 * @author 2017. 6. 18. 오후 2:19:08 jeong
	 * @param mediaType
	 *            {@link MediaType}
	 * @return {@link HttpHeaders}
	 */
	private static HttpHeaders getHeaders(MediaType mediaType) {
		
		return getHeaders(mediaType.toString());
	}
	
	/**
	 * {@link HttpHeaders} 를 반환한다
	 *
	 * @author 2017. 6. 18. 오후 2:19:09 jeong
	 * @param mediaType
	 *            {@link MediaType} 타입
	 * @return {@link HttpHeaders}
	 */
	private static HttpHeaders getHeaders(String mediaType) {
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", MessageFormat.format(Response.CONTENT_TYPE, mediaType));
		return headers;
	}
	
	/**
	 * 파일의 이미지를 보낸다.
	 *
	 * @author 2017. 6. 18. 오후 2:19:10 jeong
	 * @param file
	 *            파일
	 * @return 이미지
	 * @throws IOException
	 *             {@link FileUtils#readFileToByteArray(File)} 기능 수행중 예외 사항
	 */
	public static ResponseEntity<byte[]> image(File file) throws IOException {
		
		byte[] content = FileUtils.readFileToByteArray(file);
		
		String extension = FilenameUtils.getExtension(file.getAbsolutePath()).toLowerCase(Locale.getDefault());
		if ("jpg".equals(extension)) {
			extension = "jpeg";
		}
		
		String mediaType = "image/" + extension;
		
		return ok(content, mediaType);
		
	}
	
	public static ResponseEntity<List<byte[]>> images(List<File> files) throws IOException {
		
		List<byte[]> contents = new ArrayList<>();
		
		for (File file : files) {
			byte[] content = FileUtils.readFileToByteArray(file);
			contents.add(content);
		}
		
		return Response.ok(contents, MediaType.ALL);
	}
	
	public static ResponseEntity<String> notImplemented() {
		
		return notImplemented("Not Implemented");
	}
	
	public static ResponseEntity<String> notImplemented(String message) {
		
		return send(message, HttpStatus.NOT_IMPLEMENTED, MediaType.TEXT_PLAIN);
	}
	
	/**
	 * 아무런 반환데이터 없이 완료되었음을 알린다.
	 * ("success" 문자열 반환)
	 *
	 * @author 2017. 6. 18. 오후 2:19:11 jeong
	 * @return ("success" 문자열 반환)
	 */
	public static ResponseEntity<String> ok() {
		
		return Response.okText("success");
	}
	
	/**
	 * 요청자에게 200 OK으로 응답과 데이터를 보냅니다.
	 *
	 * @author jeonghyun.kum
	 * @param <T>
	 *            클레스 타입
	 * @since 2016. 4. 24. 오후 5:39:47
	 * @param body
	 *            응답 메세지
	 * @param mediaType
	 *            {@link MediaType}
	 * @return {@link ResponseEntity}
	 */
	public static <T> ResponseEntity<T> ok(T body, MediaType mediaType) {
		
		return Response.send(body, HttpStatus.OK, mediaType);
	}
	
	/**
	 * 요청자에게 200 OK으로 응답과 데이터를 보냅니다.
	 *
	 * @author 2017. 6. 18. 오후 2:19:18 jeong
	 * @param <T>
	 *            클레스 타입
	 * @param body
	 *            T에 해당하는 객체
	 * @param mediaType
	 *            미디어 타입
	 * @return 응답 데이터
	 */
	public static <T> ResponseEntity<T> ok(T body, String mediaType) {
		
		return Response.send(body, HttpStatus.OK, mediaType);
	}
	
	/**
	 * 요청자에게 200 OK으로 응답합니다.
	 *
	 * @author jeonghyun.kum
	 * @param <T>
	 *            클레스 타입
	 * @since 2016. 4. 24. 오후 5:39:47
	 * @param body
	 *            응답 메세지
	 * @return 응답 데이터
	 */
	public static <T> ResponseEntity<T> okJson(T body) {
		
		return Response.ok(body, MediaType.APPLICATION_JSON);
	}
	
	/**
	 * 요청자에게 200 OK으로 응답합니다.
	 *
	 * @author jeonghyun.kum
	 * @param <T>
	 *            클레스 타입
	 * @since 2016. 4. 24. 오후 5:39:47
	 * @param body
	 *            응답 메세지
	 * @return 응답 데이터
	 */
	public static <T> ResponseEntity<T> okText(T body) {
		
		return Response.ok(body, MediaType.TEXT_PLAIN);
	}
	
	/**
	 * 주어진 URL로 Redirect 합니다.
	 *
	 * @author 2017. 6. 18. 오후 2:19:19 jeong
	 * @param url
	 *            Redirect 경로
	 * @return Redirect 경로
	 */
	public static ResponseEntity<String> redirect(String url) {
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", url);
		return new ResponseEntity<>(headers, HttpStatus.FOUND);
	}
	
	/**
	 * 요청자에게 200 OK으로 응답과 데이터를 보냅니다.
	 *
	 * @author 2017. 6. 18. 오후 2:19:20 jeong
	 * @param <T>
	 *            클레스 타입
	 * @param body
	 *            T에 해당하는 객체
	 * @param status
	 *            {@link HttpStatus}
	 * @param mediaType
	 *            미디어 타입
	 * @return 응답 데이터
	 */
	private static <T> ResponseEntity<T> send(T body, HttpStatus status, MediaType mediaType) {
		
		ObjectInspection.checkNull(body);
		ObjectInspection.checkNull(status);
		ObjectInspection.checkNull(mediaType);
		
		HttpHeaders headers = Response.getHeaders(mediaType);
		if (MediaType.APPLICATION_JSON.equals(mediaType)) {
			String bodyjson = JsonGtils.toGson(body);
			LOGGER.info(bodyjson);
		} else {
			String string;
			if (body == null) {
				string = "null";
			} else {
				string = body.toString();
			}
			LOGGER.info(string);
		}
		
		return ResponseEntity.status(status).headers(headers).body(body);
	}
	
	/**
	 * 요청자에게 200 OK으로 응답과 데이터를 보냅니다.
	 *
	 * @author 2017. 6. 18. 오후 2:19:21 jeong
	 * @param <T>
	 *            클레스 타입
	 * @param body
	 *            T에 해당하는 객체
	 * @param status
	 *            {@link HttpStatus}
	 * @param mediaType
	 *            미디어 타입
	 * @return 응답 데이터
	 */
	private static <T> ResponseEntity<T> send(T body, HttpStatus status, String mediaType) {
		
		ObjectInspection.checkNull(body);
		ObjectInspection.checkNull(status);
		ObjectInspection.checkNull(mediaType);
		HttpHeaders headers = Response.getHeaders(mediaType);
		return ResponseEntity.status(status).headers(headers).body(body);
	}
	
	/**
	 * 요청자에게 500 Internal Server Error으로 응답합니다.
	 *
	 * @author jeonghyun.kum
	 * @param <T>
	 *            클레스 타입
	 * @since 2016. 4. 24. 오후 5:39:47
	 * @param body
	 *            T에 해당하는 객체
	 * @return 응답 데이터
	 */
	public static <T> ResponseEntity<T> serverError(T body) {
		
		return Response.serverError(body, MediaType.TEXT_PLAIN);
	}
	
	/**
	 * 요청자에게 500 Internal Server Error으로 응답합니다.
	 *
	 * @author jeonghyun.kum
	 * @param <T>
	 *            클레스 타입
	 * @since 2016. 4. 24. 오후 5:39:47
	 * @param body
	 *            응답 메세지
	 * @param mediaType
	 *            {@link MediaType}
	 * @return {@link ResponseEntity}
	 */
	public static <T> ResponseEntity<T> serverError(T body, MediaType mediaType) {
		
		return Response.send(body, HttpStatus.INTERNAL_SERVER_ERROR, mediaType);
	}
	
	/**
	 * {@link Response} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author jeong
	 * @since 2017. 4. 10. 오후 9:24:33
	 */
	private Response() {
		
		throw new IllegalStateException("Utility class");
	}
}
