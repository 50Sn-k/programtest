package com.example.sampleweb.form;



import org.hibernate.validator.constraints.Length;

import lombok.Data;

/**
 * ユーザー登録画面Formクラス
 * 
 * @author k-suzuki
 * 
 */
@Data
public class SkillSheetForm {

	/*ログインID*/
	@Length(min = 8,max = 20)
	private String loginId;
	
	/*資格*/
	private String qualification;
	
	/*実績*/
	private String achievements;
	
}
