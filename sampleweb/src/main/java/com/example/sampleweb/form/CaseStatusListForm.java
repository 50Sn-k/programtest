package com.example.sampleweb.form;

import org.hibernate.validator.constraints.Length;

import com.example.sampleweb.constant.db.CaseStatusKind;

import lombok.Data;

/**
 * ユーザー登録画面Formクラス
 * 
 * @author k-suzuki
 * 
 */
@Data
public class CaseStatusListForm {

	/*ログインID*/
	@Length(min = 8,max = 20)
	private String caseId;
	
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
