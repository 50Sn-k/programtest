package com.example.sampleweb.form;

import org.hibernate.validator.constraints.Length;

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
public class UserListForm {

	/*ログインID*/
	@Length(min = 8,max = 20)
	private String loginId;
	
	/*アカウント状態種別*/
	private UserStatusKind userStatusKind;
	
	/*アカウント状態種別*/
	private AuthorityKind authorityKind;
	
	/*ユーザー一覧情報から選択されたログインID*/
	private String selectedLoginId;
	
	/**
	 * ユーザー情報から指定されたログインIDをクリアします。
	 * 
	 * @return ユーザー一覧情報から選択されたログインIDクリア後のインスタンス
	 */
	public UserListForm clearSelectedLoginId() {
		this.selectedLoginId = null;
		
		return this;
	}
}
