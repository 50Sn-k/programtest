package com.example.sampleweb.entity;

import java.time.LocalDateTime;

import com.example.sampleweb.constant.db.CaseStatusKind;
import com.example.sampleweb.entity.converter.CaseStatusConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 案件情報テーブル Entity
 */

@Entity
@Table(name = "`case`")
@Data
@AllArgsConstructor
public class Case {

	/*案件ID*/
	@Id
	@Column(name ="case_id")
	private String caseId;
	
	/*企業名*/
	@Column(name ="campany_name")
	private String campanyName;
	
	/*企業住所*/
	@Column(name ="campany_address")
	private String campanyAddress;
	
	/*案件詳細*/
	@Column(name ="case_detail")
	private String caseDetail;
	
	/*案件開始日*/
	@Column(name ="case_start_date")
	private LocalDateTime caseStartDate;
	
	/*案件終了日*/
	@Column(name ="case_finish_date")
	private LocalDateTime caseFinishDate;
	
	/*案件状況*/
	@Column(name ="case_status")
	@Convert(converter = CaseStatusConverter.class)
	private CaseStatusKind caseStatus;
	
	
	public Case(){
	}
}
