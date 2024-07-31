package com.example.sampleweb.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.sampleweb.constant.CaseStatusEditMessage;
import com.example.sampleweb.constant.db.CaseStatusKind;
import com.example.sampleweb.dto.CaseStatusEditResult;
import com.example.sampleweb.dto.CaseStatusUpdateInfo;
import com.example.sampleweb.entity.Case;
import com.example.sampleweb.repository.CaseRepository;

import lombok.RequiredArgsConstructor;

/**
 * ユーザー編集画面Service実装クラス
 * 
 * @author k-suzuki
 *
 */
@Service
@RequiredArgsConstructor
public class CaseStatusEditServiceImpl implements CaseStatusEditService {

	/** ユーザー情報テーブルRepository */
	private final CaseRepository repository;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<Case> searchCaseStatusInfo(String caseId) {
		return repository.findById(caseId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CaseStatusEditResult updateCaseStatusInfo(CaseStatusUpdateInfo caseStatusUpdateInfo) {
		 var caseUpdateResult = new CaseStatusEditResult();

		// 現在の登録情報を取得
		var updateInfoOpt = repository.findById(caseStatusUpdateInfo.getCaseId());
		if (updateInfoOpt.isEmpty()) {
			caseUpdateResult.setCaseMessage(CaseStatusEditMessage.FAILED);
			return caseUpdateResult;
		}

		// 画面の入力情報等をセット
		var updateInfo = updateInfoOpt.get();
		updateInfo.setCaseStatus(caseStatusUpdateInfo.getCaseStatus());
		updateInfo.setCampanyName(caseStatusUpdateInfo.getCampanyName());
		updateInfo.setCampanyAddress(caseStatusUpdateInfo.getCampanyAddress());
		updateInfo.setCaseDetail(caseStatusUpdateInfo.getCaseDetail());
		
		//案件の日程に変更があれば更新
		if(caseStatusUpdateInfo.getStartDate().isEmpty()==false) {
			updateInfo.setCaseStartDate(caseStatusUpdateInfo.getCaseStartDate());
		}
		if(caseStatusUpdateInfo.getFinishDate().isEmpty()==false) {
			updateInfo.setCaseFinishDate(caseStatusUpdateInfo.getCaseFinishDate());
		}
		
		//案件の日程によって案件状況を設定
		if(LocalDateTime.now().isBefore(updateInfo.getCaseFinishDate())&&LocalDateTime.now().isAfter(updateInfo.getCaseStartDate())) {
			updateInfo.setCaseStatus(CaseStatusKind.CASE_DURING);
		}else if(LocalDateTime.now().isBefore(updateInfo.getCaseStartDate())) {
			updateInfo.setCaseStatus(CaseStatusKind.CASE_BEFORE);
		}else if(LocalDateTime.now().isAfter(updateInfo.getCaseFinishDate())) {
			updateInfo.setCaseStatus(CaseStatusKind.CASE_AFTER);
		}else {
			updateInfo.setCaseStatus(CaseStatusKind.UNKNOWN);
		}
		if(updateInfo.getCaseStartDate().isAfter(updateInfo.getCaseFinishDate())) {
			updateInfo.setCaseStatus(CaseStatusKind.UNKNOWN);
		}

		try {
			repository.save(updateInfo);
		} catch (Exception e) {
			caseUpdateResult.setCaseMessage(CaseStatusEditMessage.FAILED);
			return caseUpdateResult;
		}

		caseUpdateResult.setUpdateCaseInfo(updateInfo);
		caseUpdateResult.setCaseMessage(CaseStatusEditMessage.SUCCEED);
		return caseUpdateResult;
	}

}