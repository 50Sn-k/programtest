package com.example.sampleweb.service;

import java.util.Optional;

import com.example.sampleweb.dto.CaseStatusEditResult;
import com.example.sampleweb.dto.CaseStatusUpdateInfo;
import com.example.sampleweb.entity.Case;

/**
 * ユーザー編集画面Serviceインターフェース
 * 
 * @author k-suzuki
 *
 */
public interface CaseStatusEditService {

	/**
	 * ログインIDを使ってユーザー情報テーブルを検索し、検索結果を返却します。
	 * 
	 * @param loginId ログインID
	 * @return 該当のユーザー情報テーブル登録情報
	 */
	public Optional<Case> searchCaseStatusInfo(String caseId);

	/**
	 * ユーザー情報テーブルを更新します。
	 * 
	 * @param updateDto ユーザー更新情報
	 * @return 更新結果
	 */
	public CaseStatusEditResult updateCaseStatusInfo(CaseStatusUpdateInfo updateDto);

}