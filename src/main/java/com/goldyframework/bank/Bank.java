/**
 * FileName : {@link Bank}.java
 * Created : 2017. 12. 24. 오후 8:48:54
 * Author : jeong
 * Summary :
 * Copyright (C) 2018 Formal Works Inc. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 (주)포멀웍스에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.bank;

import java.text.MessageFormat;

/**
 * @author 2017. 12. 24. 오후 8:48:54 jeong
 */
public enum Bank {
	
	BANK_002("002", "산업은행", ""),
	INDUSTRIAL("003", "기업은행", "IBKOKRSE"),
	KOOKMIN("004", "국민은행", "CZNBKRSE"),
	BANK_007("007", "수협중앙회", ""),
	BANK_008("008", "수출입은행", ""),
	NACF("011", "농협은행", "NACFKRSE"),
	BANK_012("012", "지역농축협", ""),
	WOORI("020", "우리은행", "HVBKKRSEXXX"),
	STANDARD_CHARTERED_FIRST("023", "SC제일은행", "SCBLKRSE"),
	BANK_027("027", "한국씨티은행", ""),
	DAEGU("031", "대구은행", "DAEBKR22"),
	BUSAN("032", "부산은행", "PUSBKR2P"),
	BANK_034("034", "광주은행", ""),
	BANK_035("035", "제주은행", ""),
	BANK_037("037", "전북은행", ""),
	KYONGNAM("039", "경남은행", "KYNAKR22XXX"),
	BANK_045("045", "새마을금고중앙회", ""),
	BANK_048("048", "신협중앙회", ""),
	BANK_050("050", "상호저축은행", ""),
	BANK_052("052", "모건스탠리은행", ""),
	BANK_054("054", "HSBC은행", ""),
	BANK_055("055", "도이치은행", ""),
	BANK_057("057", "제이피모간체이스은행", ""),
	BANK_058("058", "미즈호은행", ""),
	BANK_059("059", "미쓰비시도쿄UFJ은행", ""),
	BANK_060("060", "BOA은행", ""),
	BANK_061("061", "비엔피파리바은행", ""),
	BANK_062("062", "중국공상은행", ""),
	BANK_063("063", "중국은행", ""),
	BANK_064("064", "산림조합중앙회", ""),
	BANK_065("065", "대화은행", ""),
	KOREA_POST_OFFICE("071", "우체국", "SHBKKRSEPO"),
	HANA("081", "KEB하나은행", "HNBNKRSE"),
	SHINHAN("088", "신한은행", "SCBLRSE"),
	BANK_089("089", "K뱅크", ""),
	CITI_BANK_KAKAO("090", "카카오뱅크", "CITIKRSXKAK"),
	BANK_209("209", "유안타증권", ""),
	BANK_218("218", "KB증권", ""),
	BANK_221("221", "골든브릿지 투자증권", ""),
	BANK_222("222", "한양증권", ""),
	BANK_223("223", "리딩투자증권", ""),
	BANK_224("224", "BNK투자증권", ""),
	BANK_225("225", "IBK투자증권", ""),
	BANK_227("227", "KTB투자증권", ""),
	BANK_238("238", "미래에셋대우", ""),
	BANK_240("240", "삼성증권", ""),
	BANK_243("243", "한국투자증권", ""),
	BANK_247("247", "우리투자증권", ""),
	BANK_261("261", "교보증권", ""),
	BANK_262("262", "하이투자증권", ""),
	BANK_263("263", "현대차투자증권", ""),
	BANK_264("264", "키움증권", ""),
	BANK_265("265", "이베스트투자증권", ""),
	BANK_266("266", "SK증권", ""),
	BANK_267("267", "대신증권", ""),
	BANK_269("269", "한화투자증권", ""),
	BANK_270("270", "하나금융투자", ""),
	BANK_278("278", "신한금융투자", ""),
	BANK_279("279", "동부증권", ""),
	BANK_280("280", "유진투자증권", ""),
	BANK_287("287", "메리츠종합금융증권", ""),
	BANK_290("290", "부국증권", ""),
	BANK_291("291", "신영증권", ""),
	BANK_292("292", "케이프투자증권", ""),
	BANK_293("293", "한국증권금융", ""),
	BANK_294("294", "펀드온라인코리아", ""),
	BANK_295("295", "우리종합금융", "");
	
	private final String code;
	
	private final String bankName;
	
	private final String swiftCode;
	
	Bank(final String code, final String bankName, final String swiftCode) {
		
		this.code = code;
		this.bankName = bankName;
		this.swiftCode = swiftCode;
		
	}
	
	public String toBithumbStyle() {
		
		return MessageFormat.format("{0}_{1}", this.code, this.bankName);
	}
	
}
