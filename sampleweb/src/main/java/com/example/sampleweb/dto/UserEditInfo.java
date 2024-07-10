package com.example.sampleweb.dto;
import java.time.LocalDateTime;

import lombok.Data;

/**
 * ユーザー編集情報DTOクラス
 * 
 * @author k-suzuki
 *
 */
@Data
public class UserEditInfo {

	/** ログインID */
	private String loginId;

	/** ログイン失敗回数 */
	private int loginFailureCount;

	/** アカウントロック日時 */
	private LocalDateTime accountLockedTime;
}