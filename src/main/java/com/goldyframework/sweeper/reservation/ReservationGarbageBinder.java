/**
 * FileName : {@link ReservationGarbageBinder}.java
 * Created : 2017. 4. 10.
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Goldy Project. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.sweeper.reservation;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.goldyframework.Prop;
import com.goldyframework.inspection.ObjectInspection;
import com.goldyframework.repository.RepositoryBody;
import com.goldyframework.repository.RepositoryException;
import com.goldyframework.repository.RepositoryService;
import com.goldyframework.repository.RepositoryServiceImpl;

/**
 * 예약 쓰레기 파일이 기록된 Json 파일을 읽어 해당하는 모델 객체로 바인딩합니다.
 *
 * @author 2017. 6. 18. 오후 2:00:56 jeong
 */
public final class ReservationGarbageBinder {

	/**
	 * Jackson 객체
	 */
	private static final ObjectMapper MAPPER = new ObjectMapper();

	static {
		MAPPER.enable(SerializationFeature.INDENT_OUTPUT);
	}

	/**
	 * 예약 쓰레기 Json 파일을을 읽습니다.
	 *
	 * @author 2017. 6. 18. 오후 2:03:27 jeong
	 * @param garbageFile
	 *            파일
	 * @return 변환된 객체
	 * @throws IOException
	 *             Signals that an I/O exception of some sort has occurred. This class is the general class of
	 *             exceptions produced by failed or interrupted I/O operations.
	 */
	public static AbstractReservationGarbage readGarbage(final File garbageFile) throws IOException {

		ObjectInspection.checkNull(garbageFile);
		// 파일을 읽어 옴 내용은 Json형태
		final String json = FileUtils.readFileToString(garbageFile, Prop.DEFAULT_CHARSET.name());

		// Json을 객체로 형변환
		final ReservationGarbageBinderModel garbageModel = MAPPER.readValue(json, ReservationGarbageBinderModel.class);

		// 변환될 클래스를 읽어옴
		final Class<AbstractReservationGarbage> garbageImpl = garbageModel.getTarget();

		// 변환될 클래스에 할당 될 필드값들을 읽어 Json으로 변환함
		final String fieldValuesJson = MAPPER.writeValueAsString(garbageModel.getSet());

		// Json을 변환될 클래스로 변형함
		return MAPPER.readValue(fieldValuesJson, garbageImpl);
	}

	/**
	 * 예약쓰레기를 json 파일로 저장합니다.
	 *
	 * @author 2017. 6. 18. 오후 2:04:14 jeong
	 * @param garbageDirectory
	 *            쓰레기가 관리되는 디렉토리
	 * @param garbage
	 *            예약 쓰레기
	 * @throws IOException
	 *             Signals that an I/O exception of some sort has occurred. This class is the general class of
	 *             exceptions produced by failed or interrupted I/O operations.
	 * @throws SQLException
	 *             SQL 관련 예외사항
	 * @throws RepositoryException
	 *             저장소 관련 예외사항
	 */
	public static void saveGarbage(final File garbageDirectory, final AbstractReservationGarbage garbage)
		throws IOException, SQLException, RepositoryException {

		ObjectInspection.checkNull(garbageDirectory);
		ObjectInspection.checkNull(garbage);

		final ReservationGarbageBinderModel model = new ReservationGarbageBinderModel();
		model.setTarget(garbage.getClass().getName());
		model.setSet(garbage);

		final RepositoryBody repository = new ReservationGarbageRepositoryBody(garbageDirectory);
		final RepositoryService service = new RepositoryServiceImpl(repository);

		service.write(MAPPER.writeValueAsString(model));
	}

	/**
	 * {@link ReservationGarbageBinder} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author jeong
	 * @since 2017. 4. 10. 오후 9:34:53
	 */
	private ReservationGarbageBinder() {
		throw new IllegalStateException("Utility class"); //$NON-NLS-1$
	}

}
