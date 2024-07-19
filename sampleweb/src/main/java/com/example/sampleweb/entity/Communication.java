package com.example.sampleweb.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 社内連絡情報テーブル Entity
 */

@Entity
@Table(name = "communication")
@Data
@AllArgsConstructor
public class Communication {

	/*ログインID*/
	@Id
	@Column(name ="login_id")
	private String loginId;
	
	/*連絡事項*/
	private String notice;
	
	/*備考*/
	private String note;
	
	/*連絡先*/
	@Column(name ="contact_address")
	private String contactAddress;
	
	/*作業内容*/
	@Column(name ="work_details")
	private String workDetails;
	
	/*作業状況*/
	@Column(name ="work_status")
	private String workStatus;
	
	/*連絡確認状況*/
	@Column(name ="is_notice_watched")
	private boolean isNoticeWatched;
	
	/*日報*/
	@Column(name ="daily_report")
	private String dailyReport;
	
	/*週報*/
	@Column(name ="weekly_report")
	private String weeklyReport;
	
	/*月報*/
	@Column(name ="monthly_report")
	private String monthlyReport;
	
	/*最終更新日時 */
	@Column(name ="update_time")
	private LocalDateTime updateTime;
	
	public Communication(){
	}
}
