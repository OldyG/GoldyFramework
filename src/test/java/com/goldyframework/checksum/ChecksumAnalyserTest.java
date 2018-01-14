/**
 * FileName : {@link ChecksumAnalyserTest}.java
 * Created : 2017. 6. 18. 오후 5:08:55
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Goldy Project. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 (주)Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.checksum;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.NoSuchAlgorithmException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import com.goldyframework.inspection.exception.InspectionException;
import com.goldyframework.utils.ClassLoaderGtils;

/**
 * @author 2017. 6. 18. 오후 5:08:55 jeong
 */
@SuppressWarnings("nls")
@RunWith(MockitoJUnitRunner.class)
public class ChecksumAnalyserTest extends Mockito {
	
	private static final File TEST_FILE = ClassLoaderGtils.getFile("checksum/Test File.xml");
	
	private static final File NONEEXISTENT_TEST_FILE = new File("src/test/resources/none");
	
	@Spy
	private final ChecksumAnalyser targetSpy = new ChecksumAnalyser.TestSet().createNewInstance();
	
	/**
	 * Test method for {@link com.goldyframework.checksum.ChecksumAnalyser#analyze()}.
	 */
	@Test
	public void testAnalye() {
		
		final ChecksumAnalyser analyser = new ChecksumAnalyser(TEST_FILE);
		final Checksum actual = analyser.analyze();
		Assert.assertEquals("MD2 알고리즘 비교 시험", "0c195fd9a63d4e0d5b7c507450663a4a", actual.getMd2());
		Assert.assertEquals("MD5 알고리즘 비교 시험", "e863cdd854d55b83545d8ad1a2b4225f", actual.getMd5());
		Assert.assertEquals("SHA1 알고리즘 비교 시험", "f7d7ec1072b1487e6e45fb148190e4abb4393d21", actual.getSha1());
		Assert.assertEquals("SHA256 알고리즘 비교 시험", "25ccd47453eb98a1cefc7d89a1682529781c6d478b715fd843dc7b27703e7b6e",
			actual.getSha256());
		Assert.assertEquals("SHA384 알고리즘 비교 시험",
			"f4d7267a4575d9e16e9a845cebb3a2f7bcdca76c040cf8384e3e3b5c5c1c7ea27878c10677b87d09daa0eeafa7e6502a",
			actual.getSha384());
		Assert.assertEquals("SHA512 알고리즘 비교 시험",
			"a14c0881afd6e6a41d35ab4da63efd7bd90362525d0c71fd47238722b9af035e92aa901f1a0108726158cb557932fdda9f17f30b7af2e94c76f1c1a790270eaf",
			actual.getSha512());
		
	}
	
	@Test(expected = InspectionException.class)
	public void testConstructorEmptyFile() {
		
		new ChecksumAnalyser(new File(""));
	}
	
	/**
	 * Test method for {@link com.goldyframework.checksum.ChecksumAnalyser#ChecksumAnalyser(java.io.File)}.
	 */
	@Test(expected = InspectionException.class)
	public void testConstructorInputNullArgument() {
		
		new ChecksumAnalyser(null);
	}
	
	@Test
	public void testCoverageByAnalysisCheckSum() {
		
		doThrow(NoSuchAlgorithmException.class).when(this.targetSpy)
			.convertDigestToString(Matchers.<byte[]> any());
		
		final String actual = this.targetSpy.analysisCheckSum(Algorithm.MD2);
		Assert.assertEquals("", "FAIL : null", actual);
		
	}
	
	/**
	 * 생성자에 존재하지 않는 파일을 넣었을때
	 *
	 * @author jeonghyun.kum
	 * @since 2016. 6. 28. 오후 1:47:23
	 */
	@SuppressWarnings("unused")
	@Test(expected = InspectionException.class)
	public void testNonexistentFileInstance() {
		
		new ChecksumAnalyser(NONEEXISTENT_TEST_FILE);
	}
	
	/**
	 * 생성자에 null을 발생하였을 떄
	 *
	 * @author jeonghyun.kum
	 * @since 2016. 6. 28. 오후 1:47:49
	 */
	@SuppressWarnings("unused")
	@Test(expected = InspectionException.class)
	public void testNullInstance() {
		
		new ChecksumAnalyser(null);
	}
	
	@Test
	public void testResult()
		throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		final ChecksumAnalyser analyser = new ChecksumAnalyser(TEST_FILE);
		final Checksum checksum = analyser.analyze();
		final Method[] declaredMethods = checksum.getClass().getDeclaredMethods();
		for (final Method method : declaredMethods) {
			if (method.getName().startsWith("get")) { 
				Assert.assertNotNull("null 이 아닌지 검사", method.invoke(checksum));
			}
		}
	}
}
