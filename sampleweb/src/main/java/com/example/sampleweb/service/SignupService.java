package com.example.sampleweb.service;

import com.example.sampleweb.constant.SignupResult;
import com.example.sampleweb.dto.SignupInfo;

/**
 * ユーザー登録画面serviceクラス
 * 
 * @author k-suzuki
 * 
 */
public interface SignupService {

	/**
	 * 画面の入力情報を元にユーザー情報テーブルの仮登録を行います。
	 *
	 * <p>ただし、以下のテーブル項目はこの限りではありません。
	 * <ul>
	 * <li>パスワード：画面で入力したパスワードがハッシュ化され登録されます。</li>
	 * <li>権限：常に「商品情報の確認が可能」のコード値が登録されます。</li>
	 * </ul>
	 * 
	 * @param dto 仮登録用情報
	 * @return 仮登録情報(ユーザー情報Entity)、既に同じユーザIDで登録がある場合はEmpty
	 */
	public SignupResult signup(SignupInfo dto);
}
