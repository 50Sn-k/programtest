package com.example.sampleweb.dto;

import com.example.sampleweb.constant.db.AuthorityKind;
import com.example.sampleweb.constant.db.UserStatusKind;

import lombok.Data;

/**
 * 	ユーザー一覧画面検索用DTOクラス
 * 
 * @author k-suzuki
 * 
 */
@Data
public class UserSearchInfo {

	/*ログインID*/
	private String loginId;
	
	/*アカウント状態種別*/
	private UserStatusKind userStatusKind;
	
	/*ユーザー権限種別*/
	private AuthorityKind authorityKind;
	
	/*案件ID*/
	private String caseId;
	
}
