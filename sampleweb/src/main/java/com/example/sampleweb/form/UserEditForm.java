package com.example.sampleweb.form;

import com.example.sampleweb.constant.db.AuthorityKind;
import com.example.sampleweb.constant.db.UserStatusKind;

import lombok.Data;

/**
 * ユーザー登録画面Formクラス
 * 
 * @author k-suzuki
 * 
 */
@Data
public class UserEditForm {

	/*ログイン失敗状況をリセットするか(リセットするならtrue)*/
	private boolean resetsLoginFailure;
	
	/*アカウント状態種別*/
	private UserStatusKind userStatusKind;
	
	/*ユーザー権限種別*/
	private AuthorityKind authorityKind;
	
}
