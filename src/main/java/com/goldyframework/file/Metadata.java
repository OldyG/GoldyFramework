/**
 * FileName : {@link Metadata}.java
 * Created : 2018. 2. 4. 오후 3:34:28
 * Author : jeong
 * Summary :
 * Copyright (C) 2018 Formal Works Inc. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 (주)포멀웍스에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.file;

import java.io.File;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.stream.ImageInputStream;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class Metadata {
	
	public static void main(final String[] args) {
		
		final File file = new File("C:/SharedPin Files(Mobile)/image/storage/place/1/21_C.jpg");
		
		final Metadata meta = new Metadata();
		
		meta.readAndDisplayMetadata(file.getAbsolutePath());
	}
	
	void displayMetadata(final Node root) {
		
		this.displayMetadata(root, 0);
	}
	
	void displayMetadata(final Node node, final int level) {
		
		// print open tag of element
		this.indent(level);
		System.out.print("<" + node.getNodeName());
		final NamedNodeMap map = node.getAttributes();
		if (map != null) {
			
			// print attribute values
			final int length = map.getLength();
			for (int i = 0; i < length; i++) {
				final Node attr = map.item(i);
				System.out.print(" " + attr.getNodeName() +
					"=\"" + attr.getNodeValue() + "\"");
			}
		}
		
		Node child = node.getFirstChild();
		if (child == null) {
			// no children, so close element and return
			System.out.println("/>");
			return;
		}
		
		// children, so close current tag
		System.out.println(">");
		while (child != null) {
			// print children recursively
			this.displayMetadata(child, level + 1);
			child = child.getNextSibling();
		}
		
		// print close tag of element
		this.indent(level);
		System.out.println("</" + node.getNodeName() + ">");
	}
	
	void indent(final int level) {
		
		for (int i = 0; i < level; i++) {
			System.out.print("    ");
		}
	}
	
	void readAndDisplayMetadata(final String fileName) {
		
		try {
			
			final File file = new File(fileName);
			final ImageInputStream iis = ImageIO.createImageInputStream(file);
			final Iterator<ImageReader> readers = ImageIO.getImageReaders(iis);
			
			if (readers.hasNext()) {
				
				// pick the first available ImageReader
				final ImageReader reader = readers.next();
				
				// attach source to the reader
				reader.setInput(iis, true);
				
				// read metadata of first image
				final IIOMetadata metadata = reader.getImageMetadata(0);
				
				final String[] names = metadata.getMetadataFormatNames();
				final int length = names.length;
				for (int i = 0; i < length; i++) {
					System.out.println("Format name: " + names[i]);
					this.displayMetadata(metadata.getAsTree(names[i]));
				}
			}
		} catch (final Exception e) {
			
			e.printStackTrace();
		}
	}
}