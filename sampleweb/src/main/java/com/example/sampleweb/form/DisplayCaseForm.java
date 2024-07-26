package com.example.sampleweb.form;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class DisplayCaseForm {

	/** ケースID */
	private int AssignedCaseID;
	
	/** 取引先会社名 */
	private String PartnerCompanyName;

	/** 取引先会社住所 */
	private String PartnerCompanyAddress;
	
	/** 案件内容 */
	private String CaseDetail;
	
	/** 案件開始日時 */
	private LocalDateTime CaseStartDate;
	
	/** 案件終了日時 */
	private LocalDateTime CaseEndDate;
	
	/** 案件ステータス番号 */
	private int CaseOperatingStatus;

	/** 案件稼働状況 */
	private String CaseDetailConfirmation;
	
}
