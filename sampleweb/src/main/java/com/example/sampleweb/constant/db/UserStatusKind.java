package com.example.sampleweb.constant.db;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ユーザー状態識別
 * 
 * @author k-suzuki
 */
@Getter
@AllArgsConstructor
public enum UserStatusKind {

	/*使用可能*/
	ENABLED(false,"利用可能"),
	
	/*使用不可*/
	DISABLED(true,"利用不可");
	
	/*DB登録準*/
	private boolean isDisabled;
	
	/*画面表示する説明文*/
	private String displayValue;
}
