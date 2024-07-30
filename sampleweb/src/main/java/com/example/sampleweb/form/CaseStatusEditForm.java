package com.example.sampleweb.form;

import java.time.LocalDateTime;

import com.example.sampleweb.constant.db.CaseStatusKind;

import lombok.Data;

/**
 * ユーザー登録画面Formクラス
 * 
 * @author k-suzuki
 * 
 */
@Data
public class CaseStatusEditForm {

	/*案件ID*/
	private String caseId;
	
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
	
	/*案件開始日保存*/
	private String startDate;
	
	/*案件終了日保存*/
	private String finishDate;
	
	/*案件状況種別*/
	private CaseStatusKind caseStatus;
	
}
