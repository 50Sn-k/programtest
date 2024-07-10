package com.example.sampleweb.dto;

import com.example.sampleweb.constant.UserEditMessage;
import com.example.sampleweb.entity.UserInfo;

import lombok.Data;

/**
 * ユーザー編集結果DTOクラス
 * 
 * @author k-suzuki
 *
 */
@Data
public class UserEditResult {

	/** ユーザー更新結果 */
	private UserInfo updateUserInfo;

	/** ユーザー更新結果メッセージEnum */
	private UserEditMessage updateMessage;
}