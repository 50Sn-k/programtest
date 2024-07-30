package com.example.sampleweb.dto;

import com.example.sampleweb.constant.CaseStatusEditMessage;
import com.example.sampleweb.entity.Case;

import lombok.Data;

/**
 * ユーザー編集結果DTOクラス
 * 
 * @author k-suzuki
 *
 */
@Data
public class CaseStatusEditResult {

	/** ユーザー更新結果 */
	private Case updateCaseInfo;

	/** ユーザー更新結果メッセージEnum */
	private CaseStatusEditMessage updateMessage;
}