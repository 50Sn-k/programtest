package com.example.sampleweb.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 処理結果識別
 * 
 * @author k-suzuki
 */
@Getter
@AllArgsConstructor
public enum UserDeleteResult {

	/*エラーなし*/
	SUCCEED(MessageConst.USERLIST_DELETE_SUCCEED),
	
	/*エラーあり*/
	ERROR(MessageConst.USERLIST_NON_EXISTED_LOGIN_ID);
	
	/*メッセージID*/
	private String messageId;
}
