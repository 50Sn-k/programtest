package com.example.sampleweb.form;

import java.time.LocalDateTime;

import org.hibernate.validator.constraints.Length;

import com.example.sampleweb.constant.db.CaseStatusKind;

import lombok.Data;

/**
 * 案件情報一覧画面Formクラス
 * 
 * @author k-suzuki
 * 
 */
@Data
public class CaseStatusListForm {

	/*案件ID*/
	@Length(min = 8,max = 20)
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
	
	/*案件一覧情報から選択された案件ID*/
	private String selectedCaseId;
	
	/**
	 * ユーザー情報から指定された案件IDをクリアします。
	 * 
	 * @return 案件一覧情報から選択された案件IDクリア後のインスタンス
	 */
	public CaseStatusListForm clearSelectedCaseId() {
		this.selectedCaseId = null;
		
		return this;
	}
}
