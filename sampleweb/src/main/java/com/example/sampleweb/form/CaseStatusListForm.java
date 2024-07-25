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
	private CaseStatusKind caseStatusKind;
	
	/*案件一覧情報から選択された案件ID*/
	private String selectedcaseId;
	
	/**
	 * ユーザー情報から指定された案件IDをクリアします。
	 * 
	 * @return 案件一覧情報から選択された案件IDクリア後のインスタンス
	 */
	public CaseStatusListForm clearSelectedCaseId() {
		this.selectedcaseId = null;
		
		return this;
	}
}
