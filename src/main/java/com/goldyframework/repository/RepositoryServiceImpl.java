/**
 * FileName : RepositoryServiceImpl.java
 * Created : 2017. 4. 10.
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Goldy Project. All rights reserved.
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

import com.goldyframework.response.Response;

public class RepositoryServiceImpl implements RepositoryService {

	/**
	 * slf4j Logger
	 *
	 * @author jeong
	 * @since 2017. 5. 22. 오후 9:20:02
	 */
	@SuppressWarnings("unused")
	private static final Logger LOGGER = LoggerFactory.getLogger(RepositoryServiceImpl.class);

	private final RepositoryBody repository;

	@Autowired
	public RepositoryServiceImpl(final RepositoryBody repository) {
		this.repository = repository;
	}

	public File change(final MultipartFile multipartFile) throws SQLException, RepositoryException, IOException {

		this.delete();
		return this.save(multipartFile);
	}

	@Override
	public void delete() throws SQLException, RepositoryException {

		try {
			final File file = this.getFile();
			file.delete();
		} catch (final NotRegisteredFileException e) {
			return;
		}
	}

	@Override
	public ResponseEntity<byte[]> displayImage() throws SQLException, RepositoryException, NotRegisteredFileException, IOException {

		return Response.image(this.getFile());
	}

	@SuppressWarnings("resource")
	@Override
	public ResponseEntity<InputStreamResource> download() throws SQLException, RepositoryException, NotRegisteredFileException, IOException {

		final File file = this.getFile();
		final String fileName = this.getDownloadName();
		final String docName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1"); //$NON-NLS-1$//$NON-NLS-2$
		final String contentDispositionHeader = "attachment; filename=\"" + docName + "\""; //$NON-NLS-1$//$NON-NLS-2$
		final InputStreamResource body = new InputStreamResource(new FileInputStream(file));

		return ResponseEntity
			.ok()
			.header("Content-Disposition", contentDispositionHeader) //$NON-NLS-1$
			.header("Content-Type", "application/octet-stream") //$NON-NLS-1$//$NON-NLS-2$
			.header("Pragma", "no-cache;") //$NON-NLS-1$//$NON-NLS-2$
			.header("Content-Transfer-Encoding", "binary;") //$NON-NLS-1$//$NON-NLS-2$
			.body(body);
	}

	private String getDownloadName() throws SQLException, RepositoryException, NotRegisteredFileException {

		return this.repository.getDownloadName();
	}

	@Override
	public File getFile() throws SQLException, RepositoryException, NotRegisteredFileException {

		return this.repository.getRegisteredFile();
	}

	@Override
	public byte[] readToByteArray() throws SQLException, RepositoryException, NotRegisteredFileException, IOException {

		final File file = this.getFile();
		return FileUtils.readFileToByteArray(file);
	}

	@Override
	public String readToString() throws SQLException, RepositoryException, NotRegisteredFileException, IOException {

		final File file = this.getFile();
		return FileUtils.readFileToString(file);
	}

	@Override
	public File save(final MultipartFile multipartFile) throws IOException {

		final String extension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
		final String savePath = this.repository.generateSavePath(extension);

		final File file = new File(savePath);
		file.createNewFile();
		multipartFile.transferTo(file);
		return file;
	}

	@Override
	public File write(final String content) throws SQLException, RepositoryException, IOException {

		File file = null;
		try {
			file = this.getFile();
		} catch (final NotRegisteredFileException e1) {
			final String savePath = this.repository.generateSavePath();
			file = new File(savePath);
		}
		file.createNewFile();
		FileUtils.writeStringToFile(file, content, "UTF-8"); //$NON-NLS-1$
		return file;
	}

}
