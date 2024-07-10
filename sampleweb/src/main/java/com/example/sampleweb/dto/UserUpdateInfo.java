package com.example.sampleweb.dto;

import com.example.sampleweb.constant.db.AuthorityKind;
import com.example.sampleweb.constant.db.UserStatusKind;

import lombok.Data;

/**
 * ユーザー更新情報DTOクラス
 * 
 * @author k-suzuki
 * 
 */
@Data
public class UserUpdateInfo {

	/*ログインID*/
	private String loginId;
	
	/*ログイン失敗状況をりせっとするか(リセットするならtrue)*/
	private boolean resetsLoginFailure;
	
	/*アカウント状態種別*/
	private UserStatusKind userStatusKind;
	
	/*ユーザー権限種別*/
	private AuthorityKind authorityKind;
	
	/*更新ユーザーID*/
	private String updateUserId;
}
