package com.example.sampleweb.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ユーザー更新結果メッセージ
 * 
 * @author k-suzuki
 */
@Getter
@AllArgsConstructor
public enum CaseStatusInputMessage {

	/*更新成功*/
	SUCCEED(MessageConst.CASE_INPUT_SUCCEED),
	
	/*更新失敗*/
	FAILED(MessageConst.CASE_INPUT_FAILED);
	
	/*メッセージID*/
	private String messageId;
	
}
