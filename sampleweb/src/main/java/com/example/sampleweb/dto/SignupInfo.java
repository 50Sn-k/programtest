package com.example.sampleweb.dto;

import lombok.Data;

/**
 * ユーザー登録用DTOクラス
 * 
 * @author k-suzuki
 * 
 */
@Data
public class SignupInfo {

	/*ログインID*/
	private String loginId;
	
	/*パスワード*/
	private String password;
	
	/*メールアドレス*/
	private String mailAddress;
}
