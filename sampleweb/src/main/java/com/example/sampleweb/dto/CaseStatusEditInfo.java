package com.example.sampleweb.dto;
import java.time.LocalDateTime;

import com.example.sampleweb.constant.db.CaseStatusKind;

import lombok.Data;

/**
 * 案件情報編集情報DTOクラス
 * 
 * @author k-suzuki
 *
 */
@Data
public class CaseStatusEditInfo {

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
	
	/*案件状況*/
	private CaseStatusKind caseStatus;
}