/**
 * FileName : {@link RepositoryServiceImpl}.java
 * Created : 2017. 4. 10.
 * Author : jeong
 * Summary :
 * Copyright (C) 2018 Formal Works Inc. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.goldyframework.Prop;
import com.goldyframework.does.SonarHelper;
import com.goldyframework.inspection.ObjectInspection;
import com.goldyframework.repository.exception.NotRegisteredFileException;
import com.goldyframework.response.Response;
import com.goldyframework.utils.NullGtils;

/**
 * {@link RepositoryService} 구현 내용
 *
 * @author 2017. 6. 18. 오후 1:53:14 jeong
 */
public class RepositoryServiceImpl implements RepositoryService {
	
	/**
	 * slf4j Logger
	 *
	 * @author jeong
	 * @since 2017. 5. 22. 오후 9:20:02
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(RepositoryServiceImpl.class);
	
	/**
	 * 저장소 바디
	 */
	private final RepositoryBody repository;
	
	/**
	 * {@link RepositoryServiceImpl} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author 2017. 6. 18. 오후 1:53:45 jeong
	 * @param body
	 *            저장소 바디
	 */
	@Autowired
	public RepositoryServiceImpl(final RepositoryBody body) {
		
		this.repository = NullGtils.throwIfNull(body);
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * @author 2017. 6. 18. 오후 1:55:26 jeong
	 */
	@Override
	public File change(final MultipartFile multipartFile) throws SQLException, RepositoryException, IOException {
		
		ObjectInspection.checkNull(multipartFile);
		this.delete();
		return this.save(multipartFile);
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * @author 2017. 6. 18. 오후 1:55:27 jeong
	 */
	@Override
	public void delete() throws SQLException, RepositoryException {
		
		try {
			final File file = this.getFile();
			final boolean success = file.delete();
			SonarHelper.noStatic(success);
		} catch (final NotRegisteredFileException e) {
			LOGGER.trace("제거할 파일이 없어 진행하지 않음", e);
			return;
		}
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * @author 2017. 6. 18. 오후 1:55:29 jeong
	 */
	@Override
	public ResponseEntity<byte[]> displayImage()
		throws SQLException, RepositoryException, NotRegisteredFileException, IOException {
		
		return Response.image(this.getFile());
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * @author 2017. 6. 18. 오후 1:55:30 jeong
	 */
	@SuppressWarnings("resource")
	@Override
	public ResponseEntity<InputStreamResource> download()
		throws SQLException, RepositoryException, NotRegisteredFileException, IOException {
		
		final File file = this.getFile();
		final String extension = FilenameUtils.getExtension(file.getAbsolutePath());
		
		final String fileName = this.repository.getDownloadName() + "." + extension;
		final String docName = new String(fileName.getBytes(Prop.DEFAULT_CHARSET), "ISO-8859-1");
		final String contentDispositionHeader = "attachment; filename=\"" + docName + "\"";
		final InputStreamResource body = new InputStreamResource(new FileInputStream(file));
		
		return ResponseEntity
			.ok()
			.header("Content-Disposition", contentDispositionHeader)
			.header("Content-Type", "application/octet-stream")
			.header("Pragma", "no-cache;")
			.header("Content-Transfer-Encoding", "binary;")
			.body(body);
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * @author 2017. 6. 18. 오후 1:55:59 jeong
	 */
	@Override
	public File getFile() throws SQLException, RepositoryException, NotRegisteredFileException {
		
		return this.repository.getRegisteredFile();
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * @author 2017. 6. 18. 오후 1:56:05 jeong
	 */
	@Override
	public byte[] readToByteArray() throws SQLException, RepositoryException, NotRegisteredFileException, IOException {
		
		final File file = this.getFile();
		return FileUtils.readFileToByteArray(file);
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * @author 2017. 6. 18. 오후 1:56:07 jeong
	 */
	@Override
	public String readToString() throws SQLException, RepositoryException, NotRegisteredFileException, IOException {
		
		final File file = this.getFile();
		return FileUtils.readFileToString(file, Prop.DEFAULT_CHARSET.name());
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * @author 2017. 6. 18. 오후 1:56:08 jeong
	 */
	@Override
	public File save(final MultipartFile multipartFile) throws IOException {
		
		ObjectInspection.checkNull(multipartFile);
		final String extension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
		final String savePath = this.repository.generateSavePath(extension);
		
		final File file = new File(savePath);
		final File directory = file.getParentFile();
		if (directory.exists() == false) {
			directory.mkdirs();
		}
		
		final boolean success = file.createNewFile();
		SonarHelper.noStatic(success);
		multipartFile.transferTo(file);
		return file;
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * @author 2017. 6. 18. 오후 1:56:09 jeong
	 */
	@Override
	public File write(final String content) throws SQLException, RepositoryException, IOException {
		
		ObjectInspection.checkNull(content);
		File file = null;
		try {
			file = this.getFile();
		} catch (final NotRegisteredFileException e) {
			LOGGER.trace("파일이 없어 신규 파일을 생성합니다.", e);
			final String savePath = this.repository.generateSavePath();
			file = new File(savePath);
		}
		final boolean success = file.createNewFile();
		SonarHelper.noStatic(success);
		FileUtils.writeStringToFile(file, content, Prop.DEFAULT_CHARSET.name());
		return file;
	}
	
}
