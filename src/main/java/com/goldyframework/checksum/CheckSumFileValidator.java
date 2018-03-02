/**
 * @author 2017. 6. 17. 오후 11:55:45 jeong
 */
package com.goldyframework.checksum;

import java.io.File;

import com.goldyframework.inspection.FileInspection;
import com.goldyframework.inspection.Inspection;
import com.goldyframework.inspection.ObjectInspection;

/**
 * 체크섬 생성 대상 {@link File} 객체 검사도구
 *
 * @author 2017. 6. 17. 오후 11:55:45 jeong
 */
public class CheckSumFileValidator implements Inspection<File> {
	
	/**
	 * Checksum 생성 대상 파일을 검사합니다.
	 *
	 * @author 2017. 6. 17. 오후 11:56:10 jeong
	 * @param target
	 *            검사 대상 파일
	 */
	@Override
	public void check(File target) {
		
		ObjectInspection.checkNull(target);
		FileInspection.checkExistsFile(target);
	}
	
}
