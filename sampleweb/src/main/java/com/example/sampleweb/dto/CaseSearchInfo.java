package com.example.sampleweb.dto;

import com.example.sampleweb.constant.db.CaseStatusKind;

import lombok.Data;

/**
 * 	案件一覧画面検索用DTOクラス
 * 
 * @author k-suzuki
 * 
 */
@Data
public class CaseSearchInfo {

	/*案件ID*/
	private String caseId;
	
	/*案件状況種別*/
	private CaseStatusKind caseStatusKind;
	
}
