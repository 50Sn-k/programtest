package com.example.sampleweb.form;

import lombok.Data;

/**
 * 
 * 社内連絡事項入力Formクラス
 * 
 * @author k-murata
 * 
 */

@Data
public class UserComListForm {
	
	/** ログインID */
	private String loginId;

	/** 連絡事項 */
	private String notice;
	
	/** 備考 */
	private String note;
	
	/** 連絡先 */	
	private String contactAddress;
	
	/** 作業内容 */
	private String workDetails;
	
	/** 作業状況 */
	private String workStatus;
	
	/** 連絡状況確認 */
	private boolean isNoticeWatched;
	
	/** 日報 */
	private String dailyReport;
	
	/** 週報 */
	private String weeklyReport;
	
	/** 月報 */
	private String monthlyReport;
}
