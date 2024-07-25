package com.example.sampleweb.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 案件情報テーブル Entity
 */

@Entity
@Table(name = "case")
@Data
@AllArgsConstructor
public class AssignedCaseStatus {

	/** ログインID */
	@Id
	@Column(name ="login_id")
	private String loginId;
	
	@Column(name = "case_id")
	private int AssignedCaseID;
	
	@Column(name = "campany_name")
	private String PartnerCompanyName;
	
	@Column(name = "campany_address")
	private String PartnerCompanyAddress;
	
	@Column(name = "case_detail")
	private String CaseDetail;
	
	@Column(name = "case_start_date")
	private LocalDateTime CaseStartDate;
	
	@Column(name = "case_finish_date")
	private LocalDateTime CaseEndDate;
	
	@Column(name = "case_status")
	private int CaseOperatingStatus;
	
	public AssignedCaseStatus() {
	}

}
