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
public enum CaseDeleteResult {

	/*エラーなし*/
	SUCCEED(MessageConst.CASE_STATUSLIST_DELETE_SUCCEED),
	
	/*エラーあり*/
	ERROR(MessageConst.CASE_STATUSLIST_NON_EXISTED_CASE_ID);
	
	/*メッセージID*/
	private String messageId;
}
