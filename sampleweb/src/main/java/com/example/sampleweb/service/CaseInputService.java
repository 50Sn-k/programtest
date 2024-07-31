package com.example.sampleweb.service;

import java.util.Optional;

import com.example.sampleweb.dto.CaseStatusInputResult;
import com.example.sampleweb.dto.CaseStatusListInfo;
import com.example.sampleweb.entity.Case;


public interface CaseInputService {

	/** 案件情報呼び出し */
	public Optional<Case> searchCaseById(String selectcase);

	
	/** 連絡事項、備考、作業内容、作業状況入力 
	 * @return */
	public CaseStatusInputResult setNewCase(CaseStatusListInfo caseStatusListInfos);
	
}
