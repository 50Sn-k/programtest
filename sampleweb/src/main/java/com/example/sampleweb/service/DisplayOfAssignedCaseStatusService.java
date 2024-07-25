package com.example.sampleweb.service;

import java.util.Optional;

import com.example.sampleweb.dto.UserAssignedCaseInfo;
import com.example.sampleweb.entity.AssignedCaseStatus;

public interface DisplayOfAssignedCaseStatusService {

	/** ユーザデータ呼び出し */
	public Optional<AssignedCaseStatus> serchUserById(String loginId);
	
	/** 案件情報リストの取得 */
	public UserAssignedCaseInfo getCase();
	
	/** 案件情報リスト登録 */
	public void setUserAssignedCase(UserAssignedCaseInfo updateInfos);
	
	/** 案件情報リスト削除 */
	public void deleteUserAssignedCase(String assignedID);
	
	/** 案件情報の割り当て */
	public void setAssignedCaseID(String loginId);
}
