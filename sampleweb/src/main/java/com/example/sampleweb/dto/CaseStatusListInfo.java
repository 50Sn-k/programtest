package com.example.sampleweb.dto;

import java.time.LocalDateTime;

import lombok.Data;

/**
 * 	案件一覧画面DTOクラス
 * 
 * @author k-suzuki
 * 
 */
@Data
public class CaseStatusListInfo {

	/*案件ID*/
	private int caseId;
	
	/*企業名*/
	private String campanyName;
	
	/*企業住所*/
	private String campanyAddress;
	
	/*案件詳細*/
	private String caseDetail;
	
	/*案件開始日*/
	private LocalDateTime caseStartDate;
	
	/*案件終了日*/
	private LocalDateTime caseFinishDate;
	
	/*案件状況*/
	private String caseStatus;
}
