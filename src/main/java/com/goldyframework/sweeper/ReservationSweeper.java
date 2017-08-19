/**
 * FileName : {@link ReservationSweeper}.java
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

import com.goldyframework.does.Because;
import com.goldyframework.does.Does;
import com.goldyframework.inspection.ObjectInspection;
import com.goldyframework.repository.RepositoryException;
import com.goldyframework.sweeper.exception.SweeperException;
import com.goldyframework.sweeper.reservation.AbstractReservationGarbage;
import com.goldyframework.sweeper.reservation.ReservationGarbageBinder;
import com.goldyframework.utils.NullGtils;

/**
 * 예약 쓰레기 청소
 *
 * @author 2017. 6. 18. 오후 2:30:30 jeong
 */
public class ReservationSweeper implements ISweeper {
	
	/**
	 * slf4j Logger
	 *
	 * @author jeong
	 * @since 2017. 5. 22. 오후 9:20:02
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationSweeper.class);
	
	/**
	 * 예약 쓰레기를 추가한다.
	 *
	 * @author 2017. 6. 18. 오후 2:33:50 jeong
	 * @param garbageDirectory
	 * @param garbage
	 *            쓰레기
	 * @throws SweeperException
	 *             청소 도중 발생한 예외 사항
	 */
	public static void addReservationGarbage(final File garbageDirectory, final AbstractReservationGarbage garbage)
		throws SweeperException {
		
		ObjectInspection.checkNull(garbageDirectory);
		ObjectInspection.checkNull(garbage);
		try {
			ReservationGarbageBinder.saveGarbage(garbageDirectory, garbage);
		} catch (IOException | SQLException | RepositoryException e) {
			throw new SweeperException(e);
		}
	}
	
	/**
	 * 청소 대상 쓰레기를 추출한다.
	 *
	 * @author 2017. 6. 18. 오후 2:34:18 jeong
	 * @param allReservationGarbage
	 *            전체 쓰레기 목록
	 * @return 청소 대상 쓰레기 추출
	 */
	private static Map<File, AbstractReservationGarbage> extractCleaningTargetGarbage(
		final Map<File, AbstractReservationGarbage> allReservationGarbage) {
		
		ObjectInspection.checkNull(allReservationGarbage);
		
		final Map<File, AbstractReservationGarbage> returnTarget = new HashMap<>();
		for (final Entry<File, AbstractReservationGarbage> map : allReservationGarbage.entrySet()) {
			if (map.getValue().isCleaningTarget()) {
				returnTarget.put(map.getKey(), map.getValue());
			}
		}
		return returnTarget;
	}
	
	/**
	 * childDirectoryName에 해당하는 자식 디렉토리를 반환한다.
	 *
	 * @author 2017. 6. 18. 오후 2:36:20 jeong
	 * @param baseDirectory
	 *            찾는 기준 디렉토리
	 * @param childDirectoryName
	 *            자식 디렉토리 이름
	 * @return childDirectoryName에 해당하는 파일
	 */
	private static File getChildDirectory(final File baseDirectory, final String childDirectoryName) {
		
		ObjectInspection.checkNull(baseDirectory);
		ObjectInspection.checkNull(childDirectoryName);
		final String absolutePath = baseDirectory.getAbsolutePath();
		
		final String fileName = FilenameUtils.getName(absolutePath);
		
		final String currentDirectoryPath = FilenameUtils.getFullPath(absolutePath);
		final File failureDirectory = new File(currentDirectoryPath, childDirectoryName);
		if (failureDirectory.exists() == false) {
			failureDirectory.mkdirs();
		}
		
		return new File(failureDirectory, fileName);
	}
	
	/**
	 * 청소 작업이 완료된 파일을 Done 폴더로 이동합니다.
	 *
	 * @author 2017. 6. 18. 오후 2:37:57 jeong
	 * @param garbageFile
	 *            청소파일
	 */
	private static void moveToDoneDriectory(final File garbageFile) {
		
		final File movingTarget = getChildDirectory(garbageFile, "Done"); //$NON-NLS-1$
		final boolean success = garbageFile.renameTo(movingTarget);
		Does.notUse(success, Because.DO_NOTHING);
	}
	
	/**
	 * 청소 작업이 실패하여 파일을 Failure 폴더로 이동합니다.
	 *
	 * @author 2017. 6. 18. 오후 2:38:35 jeong
	 * @param garbageFile
	 *            청소 파일
	 */
	private static void moveToFailureDirectory(final File garbageFile) {
		
		final File movingTarget = getChildDirectory(garbageFile, "Failure"); //$NON-NLS-1$
		final boolean success = garbageFile.renameTo(movingTarget);
		Does.notUse(success, Because.DO_NOTHING);
	}
	
	private final File garbageDirectory;
	
	/**
	 * {@link ReservationSweeper} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author jeong
	 * @param garbageDirectory
	 *            쓰레기 파일이 관리되는 디렉토리
	 * @since 2017. 4. 10. 오후 9:35:18
	 */
	public ReservationSweeper(final File garbageDirectory) {
		
		this.garbageDirectory = NullGtils.throwIfNull(garbageDirectory);
	}
	
	/**
	 * 청소 작업을 진행합니다.
	 *
	 * @author 2017. 6. 19. 오후 10:03:38 jeong
	 * @param garbageFile
	 *            청소 대상 파일
	 * @param garbage
	 *            청소 대상 쓰레기
	 */
	private void doClean(final File garbageFile, final AbstractReservationGarbage garbage) {
		
		Does.notUse(this, Because.WANT_NOT_STATIC_FUNCTION);
		try {
			garbage.clean();
		} catch (final SweeperException e) {
			LOGGER.error(
				MessageFormat.format("{0}파일에서 예외가 발생하였습니다. :{1}", garbageFile.getName(), e.getMessage()), e); //$NON-NLS-1$
			moveToFailureDirectory(garbageFile);
			return;
		}
		if (garbage.isTestGarbage() == false) {
			moveToDoneDriectory(garbageFile);
		}
	}
	
	/**
	 * 모든 예약 쓰레기를 반환한다.
	 *
	 * @author 2017. 6. 18. 오후 2:39:03 jeong
	 * @return 모든 예약 쓰레기
	 * @throws IOException
	 *             파일을 읽어오는 중 예외 사항
	 */
	private Map<File, AbstractReservationGarbage> getAllReservationGarbage() throws IOException {
		
		final List<File> garbageFiles = (List<File>) this.getGarbageFiles();
		final Map<File, AbstractReservationGarbage> returnTarget = new HashMap<>();
		
		for (final File file : garbageFiles) {
			if (file.isFile()) {
				final AbstractReservationGarbage garbage = ReservationGarbageBinder.readGarbage(file);
				returnTarget.put(file, garbage);
			}
		}
		
		return returnTarget;
	}
	
	/**
	 * 모든 예약 쓰레기 파일을 가져온다.
	 *
	 * @author 2017. 6. 18. 오후 2:39:33 jeong
	 * @return 쓰레기 파일 리스트
	 */
	private Collection<File> getGarbageFiles() {
		
		return Arrays.asList(this.garbageDirectory.listFiles());
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * @author 2017. 6. 18. 오후 2:31:11 jeong
	 */
	@Override
	public void run() throws SweeperException {
		
		try {
			// 전체 삭제항목을 가져옵니다.
			final Map<File, AbstractReservationGarbage> allReservationGarbage = this.getAllReservationGarbage();
			
			// 삭제 대상에 포함된 항목을 추출합니다.
			final Map<File, AbstractReservationGarbage> cleaningTarget = extractCleaningTargetGarbage(
				allReservationGarbage);
			
			final String message = MessageFormat.format("전체 예약 항목 : {0}, 삭제 대상 항목 : {1}", //$NON-NLS-1$
				allReservationGarbage.size(), cleaningTarget.size());
			LOGGER.debug(message);
			
			for (final Entry<File, AbstractReservationGarbage> map : cleaningTarget.entrySet()) {
				final File garbageFile = map.getKey();
				final AbstractReservationGarbage garbage = map.getValue();
				
				this.doClean(garbageFile, garbage);
			}
		} catch (final IOException e) {
			throw new SweeperException(e);
		}
	}
	
}
