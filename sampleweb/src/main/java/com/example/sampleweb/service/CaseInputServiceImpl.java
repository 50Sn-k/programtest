package com.example.sampleweb.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.sampleweb.constant.CaseStatusInputMessage;
import com.example.sampleweb.constant.db.CaseStatusKind;
import com.example.sampleweb.dto.CaseStatusInputResult;
import com.example.sampleweb.dto.CaseStatusListInfo;
import com.example.sampleweb.entity.Case;
import com.example.sampleweb.repository.CaseRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CaseInputServiceImpl implements CaseInputService {

	/** 案件情報情報テーブルDAO */
	private final CaseRepository repository;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<Case> searchCaseById(String selectcase) {
		var newCaseOpt = repository.findById(selectcase);
		return newCaseOpt;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CaseStatusInputResult setNewCase(CaseStatusListInfo caseStatusListInfos) {
		Case newCase = new Case();
		var caseInputResult = new CaseStatusInputResult();
		newCase.setCaseId(caseStatusListInfos.getCaseId());
		newCase.setCampanyName(caseStatusListInfos.getCampanyName());
		newCase.setCampanyAddress(caseStatusListInfos.getCampanyAddress());
		newCase.setCaseDetail(caseStatusListInfos.getCaseDetail());
		if(caseStatusListInfos.getCaseStartDate() == null||caseStatusListInfos.getCaseFinishDate() == null) {
			caseInputResult.setCaseMessage(CaseStatusInputMessage.FAILED);
			return caseInputResult;
		}
		newCase.setCaseStartDate(caseStatusListInfos.getCaseStartDate());
		newCase.setCaseFinishDate(caseStatusListInfos.getCaseFinishDate());
		
		
		if(LocalDateTime.now().isBefore(newCase.getCaseFinishDate())&&LocalDateTime.now().isAfter(newCase.getCaseStartDate())) {
			newCase.setCaseStatus(CaseStatusKind.CASE_DURING);
		}else if(LocalDateTime.now().isBefore(newCase.getCaseStartDate())) {
			newCase.setCaseStatus(CaseStatusKind.CASE_BEFORE);
		}else if(LocalDateTime.now().isAfter(newCase.getCaseFinishDate())) {
			newCase.setCaseStatus(CaseStatusKind.CASE_AFTER);
		}else {
			newCase.setCaseStatus(CaseStatusKind.UNKNOWN);
		}
		if(newCase.getCaseStartDate().isAfter(newCase.getCaseFinishDate())) {
			newCase.setCaseStatus(CaseStatusKind.UNKNOWN);
		}

		try {
			repository.save(newCase);
		}catch(Exception e) {
			caseInputResult.setCaseMessage(CaseStatusInputMessage.FAILED);
			return caseInputResult;
		}
		caseInputResult.setCaseInputInfo(newCase);
		caseInputResult.setCaseMessage(CaseStatusInputMessage.SUCCEED);
		return caseInputResult;
	}
}
