/**
 * FileName : ReservationGarbageBinder.java
 * Created : 2017. 4. 10.
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Goldy Project. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.sweeper;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.goldyframework.repository.RepositoryBody;
import com.goldyframework.repository.RepositoryException;
import com.goldyframework.repository.RepositoryService;
import com.goldyframework.repository.RepositoryServiceImpl;

class ReservationGarbageBinder {
	
	private static final ObjectMapper MAPPER = new ObjectMapper();
	static {
		MAPPER.enable(SerializationFeature.INDENT_OUTPUT);
	}
	
	public static final ReservationGarbage readGarbage(final File garbageFile) throws IOException {
		
		// 파일을 읽어 옴 내용은 Json형태
		final String json = FileUtils.readFileToString(garbageFile);
		
		// Json을 객체로 형변환
		final ReservationGarbageBinderModel garbageModel = MAPPER.readValue(json, ReservationGarbageBinderModel.class);
		
		// 변환될 클래스를 읽어옴
		final Class<ReservationGarbage> garbageImpl = garbageModel.getTarget();
		
		// 변환될 클래스에 할당 될 필드값들을 읽어 Json으로 변환함
		final String fieldValuesJson = MAPPER.writeValueAsString(garbageModel.getSet());
		
		// Json을 변환될 클래스로 변형함
		return MAPPER.readValue(fieldValuesJson, garbageImpl);
	}
	
	public static final void saveGarbage(
		final ReservationGarbage garbage) throws IOException, SQLException, RepositoryException {
		
		final ReservationGarbageBinderModel model = new ReservationGarbageBinderModel();
		model.setTarget(garbage.getClass().getName());
		model.setSet(garbage);
		
		final RepositoryBody repository = new ReservationGarbageRepositoryBody();
		final RepositoryService service = new RepositoryServiceImpl(repository);
		
		service.write(MAPPER.writeValueAsString(model));
	}
	
	/**
	 * ReservationGarbageBinder 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author jeong
	 * @since 2017. 4. 10. 오후 9:34:53
	 */
	public ReservationGarbageBinder() {
		super();
	}
	
}
