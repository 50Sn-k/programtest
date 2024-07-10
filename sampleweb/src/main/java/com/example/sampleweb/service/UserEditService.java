package com.example.sampleweb.service;

import java.util.Optional;

import com.example.sampleweb.dto.UserEditResult;
import com.example.sampleweb.dto.UserUpdateInfo;
import com.example.sampleweb.entity.UserInfo;

/**
 * ユーザー編集画面Serviceインターフェース
 * 
 * @author k-suzuki
 *
 */
public interface UserEditService {

	/**
	 * ログインIDを使ってユーザー情報テーブルを検索し、検索結果を返却します。
	 * 
	 * @param loginId ログインID
	 * @return 該当のユーザー情報テーブル登録情報
	 */
	public Optional<UserInfo> searchUserInfo(String loginId);

	/**
	 * ユーザー情報テーブルを更新します。
	 * 
	 * @param userUpdateInfo ユーザー更新情報
	 * @return 更新結果
	 */
	public UserEditResult updateUserInfo(UserUpdateInfo userUpdateInfo);

}