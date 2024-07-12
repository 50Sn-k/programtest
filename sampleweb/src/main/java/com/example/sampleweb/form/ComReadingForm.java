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
public class ComReadingForm {

	/*ログインID*/
	@Length(min = 8,max = 20)
	private String loginId;
	
}
