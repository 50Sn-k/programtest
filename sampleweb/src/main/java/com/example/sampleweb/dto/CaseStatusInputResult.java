package com.example.sampleweb.dto;

import com.example.sampleweb.constant.CaseStatusInputMessage;
import com.example.sampleweb.entity.Case;

import lombok.Data;

/**
 * 案件情報登録結果DTOクラス
 * 
 * @author k-suzuki
 *
 */
@Data
public class CaseStatusInputResult {

	/** 案件登録結果 */
	private Case caseInputInfo;

	/** 案件登録結果メッセージEnum */
	private CaseStatusInputMessage caseMessage;
}