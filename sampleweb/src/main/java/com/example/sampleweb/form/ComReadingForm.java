package com.example.sampleweb.form;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

/**
 * 社内連絡登録画面Formクラス
 * 
 * @author k-suzuki
 * 
 */
@Data
public class ComReadingForm {

	/*ログインID*/
	@Length(min = 8,max = 20)
	private String loginId;
	
	/*ユーザー一覧情報から選択されたログインID*/
	private String selectedLoginId;
}
