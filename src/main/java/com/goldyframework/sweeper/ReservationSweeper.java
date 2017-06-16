/**
 * FileName : ReservationSweeper.java
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
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.goldyframework.property.IRepositoryProperty;
import com.goldyframework.repository.RepositoryException;
import com.goldyframework.util.SpringUtil;

public class ReservationSweeper implements ISweeper {
 
	/**
	 * slf4j Logger
	 *
	 * @author jeong
	 * @since 2017. 5. 22. 오후 9:20:02
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationSweeper.class);

	public static void addReservationGarbage(final ReservationGarbage garbage) throws SweeperException {
			try {
				ReservationGarbageBinder.saveGarbage(garbage);
			} catch (IOException | SQLException | RepositoryException e) {
				throw new SweeperException(e);
			} 
	}

	private static Map<File, ReservationGarbage> extractCleaningTargetGarbage(final Map<File, ReservationGarbage> allReservationGarbage) {
		final Map<File, ReservationGarbage> returnTarget = new HashMap<>();
		for (final Entry<File, ReservationGarbage> map : allReservationGarbage.entrySet()) {
			if (map.getValue().isCleaningTarget()) {
				returnTarget.put(map.getKey(), map.getValue());
			}
		}
		return returnTarget;
	}

	private static File getChildDirectoryFile(final File key, final String childDirectoryName) {
		final String absolutePath = key.getAbsolutePath();

		final String fileName = FilenameUtils.getName(absolutePath);

		final String currentDirectory = FilenameUtils.getFullPath(absolutePath);
		final String failureDirectory = currentDirectory + childDirectoryName;
		final File file = new File(failureDirectory);
		if (file.exists() == false) {
			file.mkdirs();
		}

		final File movingTarget = new File(failureDirectory + File.separatorChar + fileName);
		return movingTarget;
	}

	private static void moveToDoneDriectory(final File key) {
		final File movingTarget = getChildDirectoryFile(key, "Done"); //$NON-NLS-1$
		key.renameTo(movingTarget);

	}

	private static void moveToFailureDirectory(final File key) {
		final File movingTarget = getChildDirectoryFile(key, "Failure"); //$NON-NLS-1$
		key.renameTo(movingTarget);
	}

	private final IRepositoryProperty repositoryProperty;

	/**
	 * ReservationSweeper 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author jeong
	 * @since 2017. 4. 10. 오후 9:35:18
	 */
	public ReservationSweeper() {
		this.repositoryProperty = new SpringUtil().getBean(IRepositoryProperty.class);
	}

	private Map<File, ReservationGarbage> getAllReservationGarbage() throws IOException {
		final List<File> garbageFiles = (List<File>) this.getGarbageFiles();
		final Map<File, ReservationGarbage> returnTarget = new HashMap<>();

		for (final File file : garbageFiles) {
			if (file.isFile()) {
				final ReservationGarbage garbage = ReservationGarbageBinder.readGarbage(file);
				returnTarget.put(file, garbage);
			}
		}

		return returnTarget;
	}

	private Collection<File> getGarbageFiles() {
		final File directory = new File(this.repositoryProperty.reservationGarbage());

		return Arrays.asList(directory.listFiles());
	}

	@Override
	public void run() throws SweeperException {
		try {
			// 전체 삭제항목을 가져옵니다.
			final Map<File, ReservationGarbage> allReservationGarbage = this.getAllReservationGarbage();

			// 삭제 대상에 포함된 항목을 추출합니다.
			final Map<File, ReservationGarbage> cleaningTarget = extractCleaningTargetGarbage(allReservationGarbage);

			LOGGER.debug(MessageFormat.format("전체 예약 항목 : {0}, 삭제 대상 항목 : {1}", allReservationGarbage.size(), cleaningTarget.size())); //$NON-NLS-1$

			for (final Entry<File, ReservationGarbage> map : cleaningTarget.entrySet()) {
				final File garbageFile = map.getKey();
				final ReservationGarbage garbage = map.getValue();

				try {
					garbage.clean();
				} catch (final Exception e) {
					LOGGER.error(garbageFile.getName() + "파일에서 예외가 발생하였습니다. :" + e.getMessage(), e); //$NON-NLS-1$
					moveToFailureDirectory(garbageFile);
					continue;
				}
				if (garbage.isTestGarbage() == false) {
					moveToDoneDriectory(garbageFile);
				}
			}
		} catch (final IOException e) {
			throw new SweeperException(e);
		}
	}

}
