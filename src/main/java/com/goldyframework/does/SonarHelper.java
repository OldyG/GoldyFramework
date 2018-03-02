/**
 * FileName : {@link SonarHelper}.java
 * Created : 2017. 6. 19. 오후 9:04:49
 * Author : jeong
 * Summary :
 * Copyright (C) 2018 Formal Works Inc. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.does;

import com.goldyframework.inspection.ObjectInspection;
import com.goldyframework.utils.ITestSet;
import com.google.common.annotations.VisibleForTesting;

/**
 * 함수를 static으로 변형하지 못하도록 도와줍니다.
 *
 * @author 2017. 6. 19. 오후 9:04:49 jeong
 */
public class SonarHelper {
	
	@VisibleForTesting
	static class TestSet implements ITestSet<SonarHelper> {
		
		/**
		 * {@inheritDoc}
		 *
		 * @author 2017. 6. 19. 오후 11:01:42 jeong
		 */
		@Override
		public SonarHelper createNewInstance() {
			
			return new SonarHelper();
		}
		
	}
	
	/**
	 * 이 함수에 this를 설정하세요
	 * ex) {@link SonarHelper#notUse}(this, Because)
	 *
	 * @author 2017. 6. 19. 오후 9:07:54 jeong
	 * @param inputYourObjectAtHere
	 *            this를 집어넣으세요
	 */
	public static void noStatic(Object obj) {
		
		ObjectInspection.checkNull(obj);
	}
	
	/**
	 * 반환값을 사용하지 않는 경우 호출하세요 return 값을 obj에 설정하세요
	 * 
	 * @author 2018. 2. 11. 오후 4:43:45 jeong
	 */
	public static void unuse(Object obj) {
		
		ObjectInspection.checkNull(obj);
	}
	
	/**
	 * {@link SonarHelper} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author 2017. 6. 19. 오후 9:04:49 jeong
	 */
	SonarHelper() {
		
		throw new IllegalStateException("Utility class");
	}
	
}
