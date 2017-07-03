/**
 * FileName : {@link Response}.java
 * Created : 2017. 4. 10.
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Goldy Project. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.response;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Locale;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.goldyframework.inspection.ObjectInspection;
import com.goldyframework.utils.json.JsonGtils;

/**
 * 반환 Response 방법이 문자열일 경우에 사용하는 객체입니다.
 * ResponseEntity 대신에 사용하는 이유는 "charset=UTF-8" 설정을 위해서 사용함
 *
 * @author jeonghyun.kum
 * @since 2016. 4. 23. 오후 8:42:54
 */
public final class Response {

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
	private static final String CONTENT_TYPE = "{0};charset=UTF-8"; //$NON-NLS-1$

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
	public static <T> ResponseEntity<T> badRequest(final T body) {

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
	public static <T> ResponseEntity<T> badRequest(final T body, final MediaType mediaType) {

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
	private static HttpHeaders getHeaders(final MediaType mediaType) {

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
	private static HttpHeaders getHeaders(final String mediaType) {

		final HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", MessageFormat.format(Response.CONTENT_TYPE, mediaType)); //$NON-NLS-1$
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
	public static ResponseEntity<byte[]> image(final File file) throws IOException {

		final byte[] content = FileUtils.readFileToByteArray(file);

		String extension = FilenameUtils.getExtension(file.getAbsolutePath()).toLowerCase(Locale.getDefault());
		if ("jpg".equals(extension)) { //$NON-NLS-1$
			extension = "jpeg"; //$NON-NLS-1$
		}

		final String mediaType = "image/" + extension;  //$NON-NLS-1$

		return ok(content, mediaType);

	}

	/**
	 * 아무런 반환데이터 없이 완료되었음을 알린다.
	 * ("success" 문자열 반환)
	 *
	 * @author 2017. 6. 18. 오후 2:19:11 jeong
	 * @return ("success" 문자열 반환)
	 */
	public static ResponseEntity<String> ok() {

		return Response.ok("success"); //$NON-NLS-1$
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
	public static <T> ResponseEntity<T> ok(final T body) {

		return Response.ok(body, MediaType.TEXT_PLAIN);
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
	public static <T> ResponseEntity<T> ok(final T body, final MediaType mediaType) {

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
	public static <T> ResponseEntity<T> ok(final T body, final String mediaType) {

		return Response.send(body, HttpStatus.OK, mediaType);
	}

	/**
	 * 주어진 URL로 Redirect 합니다.
	 *
	 * @author 2017. 6. 18. 오후 2:19:19 jeong
	 * @param url
	 *            Redirect 경로
	 * @return Redirect 경로
	 */
	public static ResponseEntity<String> redirect(final String url) {

		final HttpHeaders headers = new HttpHeaders();
		headers.add("Location", url); //$NON-NLS-1$
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
	private static <T> ResponseEntity<T> send(final T body, final HttpStatus status, final MediaType mediaType) {

		ObjectInspection.checkNull(body);
		ObjectInspection.checkNull(status);
		ObjectInspection.checkNull(mediaType);

		final HttpHeaders headers = Response.getHeaders(mediaType);
		if (MediaType.APPLICATION_JSON.equals(mediaType)) {
			final String bodyjson = JsonGtils.toGson(body);
			LOGGER.info(bodyjson);
		} else {
			final String string;
			if (body == null) {
				string = "null"; //$NON-NLS-1$
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
	private static <T> ResponseEntity<T> send(final T body, final HttpStatus status, final String mediaType) {

		ObjectInspection.checkNull(body);
		ObjectInspection.checkNull(status);
		ObjectInspection.checkNull(mediaType);
		final HttpHeaders headers = Response.getHeaders(mediaType);
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
	public static <T> ResponseEntity<T> serverError(final T body) {

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
	public static <T> ResponseEntity<T> serverError(final T body, final MediaType mediaType) {

		return Response.send(body, HttpStatus.INTERNAL_SERVER_ERROR, mediaType);
	}

	/**
	 * {@link Response} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author jeong
	 * @since 2017. 4. 10. 오후 9:24:33
	 */
	private Response() {
		throw new IllegalStateException("Utility class"); //$NON-NLS-1$
	}
}
