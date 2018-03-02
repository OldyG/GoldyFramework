/**
 * FileName : {@link FileGtils}.java
 * Created : 2018. 2. 4. 오후 2:36:21
 * Author : jeong
 * Summary :
 * Copyright (C) 2018 Formal Works Inc. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
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

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;

public class FileGtils {
	
	private static final Charset utf8 = StandardCharsets.UTF_8;
	
	public static BasicFileAttributes getAttributes(File file) throws IOException {
		
		Path path = Paths.get(file.toURI());
		
		return Files.readAttributes(path, BasicFileAttributes.class, LinkOption.NOFOLLOW_LINKS);
	}
	
	public static String getAttrValue(File file, String attrName) throws IOException {
		
		Path path = Paths.get(file.toURI());
		UserDefinedFileAttributeView view = Files.getFileAttributeView(path, UserDefinedFileAttributeView.class);
		int attrSize = view.size(attrName);
		ByteBuffer buf = ByteBuffer.allocate(attrSize);
		view.read(attrName, buf);
		
		return new String(buf.array(), utf8);
		
	}
	
	public static FileOwnerAttributeView getFileOwnerAttributeView(File file) {
		
		Path path = Paths.get(file.toURI());
		return Files.getFileAttributeView(path, FileOwnerAttributeView.class, LinkOption.NOFOLLOW_LINKS);
	}
	
	public static Metadata getImageMetadata(File file) throws ImageProcessingException, IOException {
		
		Metadata metadata = ImageMetadataReader.readMetadata(file);
		for (Directory directory : metadata.getDirectories()) {
			for (Tag tag : directory.getTags()) {
				if (tag.getTagName().equals("User Comment")) {
					continue;
				}
			}
			for (String error : directory.getErrors()) {
				System.err.println("ERROR: " + error);
			}
		}
		return metadata;
	}
	
	public static UserDefinedFileAttributeView getUserDefinedAttributeView(File file) {
		
		Path path = Paths.get(file.toURI());
		
		return Files.getFileAttributeView(path, UserDefinedFileAttributeView.class, LinkOption.NOFOLLOW_LINKS);
	}
	
	public static void setAttrValue(File file, String attrName, String value) throws IOException {
		
		UserDefinedFileAttributeView udav = getUserDefinedAttributeView(file);
		byte[] bytes = value.getBytes(utf8);
		ByteBuffer buf = ByteBuffer.wrap(bytes);
		udav.write(attrName, buf);
	}
}
