package com.example.sampleweb.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.sampleweb.constant.CaseStatusEditMessage;
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
			caseUpdateResult.setUpdateMessage(CaseStatusEditMessage.FAILED);
			return caseUpdateResult;
		}

		// 画面の入力情報等をセット
		var updateInfo = updateInfoOpt.get();
		updateInfo.setCaseStatus(caseStatusUpdateInfo.getCaseStatus());
		updateInfo.setCampanyName(caseStatusUpdateInfo.getCampanyName());
		updateInfo.setCampanyAddress(caseStatusUpdateInfo.getCampanyAddress());
		updateInfo.setCaseDetail(caseStatusUpdateInfo.getCaseDetail());
		if(caseStatusUpdateInfo.getStartDate().isEmpty()==false) {
		updateInfo.setCaseStartDate(caseStatusUpdateInfo.getCaseStartDate());
		}
		if(caseStatusUpdateInfo.getFinishDate().isEmpty()==false) {
		updateInfo.setCaseFinishDate(caseStatusUpdateInfo.getCaseFinishDate());
		}

		try {
			repository.save(updateInfo);
		} catch (Exception e) {
			caseUpdateResult.setUpdateMessage(CaseStatusEditMessage.FAILED);
			return caseUpdateResult;
		}

		caseUpdateResult.setUpdateCaseInfo(updateInfo);
		caseUpdateResult.setUpdateMessage(CaseStatusEditMessage.SUCCEED);
		return caseUpdateResult;
	}

}