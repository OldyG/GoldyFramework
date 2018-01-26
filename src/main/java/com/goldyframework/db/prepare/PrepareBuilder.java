/**
 * FileName : {@link PrepareBuilder}.java
 * Created : 2017. 7. 8. 오후 1:38:11
 * Author : jeong
 * Summary :
 * Copyright (C) 2018 Formal Works Inc. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 (주)포멀웍스에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.db.prepare;

import com.goldyframework.db.prepare.statement.delete.DeletePreparePlan;
import com.goldyframework.db.prepare.statement.insert.InsertPreparePlan;
import com.goldyframework.db.prepare.statement.select.SelectPreparePlan;
import com.goldyframework.db.prepare.statement.update.UpdatePreparePlan;

/**
 * @author 2017. 7. 8. 오후 1:38:11 jeong
 */
public class PrepareBuilder {
	
	public static DeletePreparePlan delete(final String tableName) {
		
		return new DeletePreparePlan(tableName);
	}
	
	public static InsertPreparePlan insert(final String tableName) {
		
		return new InsertPreparePlan(tableName);
	}
	
	public static SelectPreparePlan select(final String tableName) {
		
		return new SelectPreparePlan(tableName);
	}
	
	public static UpdatePreparePlan update(final String tableName) {
		
		return new UpdatePreparePlan(tableName);
	}
	
	/**
	 * {@link PrepareBuilder} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author 2017. 7. 8. 오후 1:38:16 jeong
	 */
	private PrepareBuilder() {
		
		super();
	}
	
}
