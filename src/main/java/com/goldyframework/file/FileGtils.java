/**
 * FileName : {@link FileGtils}.java
 * Created : 2018. 2. 4. 오후 2:36:21
 * Author : jeong
 * Summary :
 * Copyright (C) 2018 Formal Works Inc. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 (주)포멀웍스에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.file;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileOwnerAttributeView;
import java.nio.file.attribute.UserDefinedFileAttributeView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;

public class FileGtils {
	
	private static final Charset utf8 = StandardCharsets.UTF_8;
	
	/**
	 * slf4j Logger
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(FileGtils.class);
	
	public static BasicFileAttributes getAttributes(final File file) throws IOException {
		
		final Path path = Paths.get(file.toURI());
		
		return Files.readAttributes(path, BasicFileAttributes.class, LinkOption.NOFOLLOW_LINKS);
	}
	
	public static String getAttrValue(final File file, final String attrName) throws IOException {
		
		final Path path = Paths.get(file.toURI());
		final UserDefinedFileAttributeView view = Files.getFileAttributeView(path, UserDefinedFileAttributeView.class);
		final int attrSize = view.size(attrName);
		final ByteBuffer buf = ByteBuffer.allocate(attrSize);
		view.read(attrName, buf);
		
		return new String(buf.array(), utf8);
		
	}
	
	public static FileOwnerAttributeView getFileOwnerAttributeView(final File file) {
		
		final Path path = Paths.get(file.toURI());
		return Files.getFileAttributeView(path, FileOwnerAttributeView.class, LinkOption.NOFOLLOW_LINKS);
	}
	
	public static Metadata getImageMetadata(final File file) throws ImageProcessingException, IOException {
		
		final Metadata metadata = ImageMetadataReader.readMetadata(file);
		for (final Directory directory : metadata.getDirectories()) {
			for (final Tag tag : directory.getTags()) {
				if (tag.getTagName().equals("User Comment")) {
					continue;
				}
			}
			for (final String error : directory.getErrors()) {
				System.err.println("ERROR: " + error);
			}
		}
		return metadata;
	}
	
	public static UserDefinedFileAttributeView getUserDefinedAttributeView(final File file) {
		
		final Path path = Paths.get(file.toURI());
		
		return Files.getFileAttributeView(path, UserDefinedFileAttributeView.class, LinkOption.NOFOLLOW_LINKS);
	}
	
	public static void setAttrValue(final File file, final String attrName, final String value) throws IOException {
		
		final UserDefinedFileAttributeView udav = getUserDefinedAttributeView(file);
		final byte[] bytes = value.getBytes(utf8);
		final ByteBuffer buf = ByteBuffer.wrap(bytes);
		udav.write(attrName, buf);
	}
}
