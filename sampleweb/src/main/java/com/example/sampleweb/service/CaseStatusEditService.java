package com.example.sampleweb.service;

import java.util.Optional;

import com.example.sampleweb.dto.CaseStatusEditResult;
import com.example.sampleweb.dto.CaseStatusUpdateInfo;
import com.example.sampleweb.entity.Case;

/**
 * 案件情報編集画面Serviceインターフェース
 * 
 * @author k-suzuki
 *
 */
public interface CaseStatusEditService {

	/**
	 * 案件IDを使って案件情報テーブルを検索し、検索結果を返却します。
	 * 
	 * @param caseId 案件ID
	 * @return 該当の案件情報テーブル登録情報
	 */
	public Optional<Case> searchCaseStatusInfo(String caseId);

	/**
	 * 案件情報テーブルを更新します。
	 * 
	 * @param updateDto 案件更新情報
	 * @return 更新結果
	 */
	public CaseStatusEditResult updateCaseStatusInfo(CaseStatusUpdateInfo updateDto);

	/**
	 * 案件IDで案件情報テーブルを検索します。
	 * 
	 * @param caseId 案件ID
	 * @return 検索結果
	 */
	public String searchCaseMember(String caseId);

}