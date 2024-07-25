package com.example.sampleweb.constant.db;

import java.util.Arrays;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 案件状況識別
 * 
 * @author k-suzuki
 */
@Getter
@AllArgsConstructor
public enum CaseStatusKind {

	/*案件情報の確認が可能*/
	UNKNOWN("","登録内容が不正"),
	
	/*案件の開始前*/
	CASE_BEFORE("1","案件開始前"),
	
	/*実施中の案件*/
	CASE_DURING("2","案件実施中"),
	
	/*終了済みの案件*/
	CASE_AFTER("3","案件終了後");
	
	/*案件状況種別*/
	private String code;
	
	/*画面表示する説明文*/
	private String displayValue;
	
	/**
	 * Enum逆引き
	 * 
	 * 案件状況種別からEnumを逆引きします
	 * 
	 * @param code 案件状況種別コード値
	 * @return 引数の案件状況種別コード値に対応するEnum、ただし見つからなかった場合はUNKNOWN
	 */
	public static CaseStatusKind from(String code){
		return Arrays.stream(CaseStatusKind.values())
				.filter(CaseStatusKind -> CaseStatusKind.getCode().equals(code))
				.findFirst()
				.orElse(UNKNOWN);
	}
}
