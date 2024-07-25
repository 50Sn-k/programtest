package com.example.sampleweb.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.sampleweb.dto.UserAssignedCaseInfo;
import com.example.sampleweb.entity.AssignedCaseStatus;
import com.example.sampleweb.repository.AssignedCaseStatusRepository;
import com.example.sampleweb.repository.UserInfoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DisplayOfAssignedCaseStatusServiceImpl implements DisplayOfAssignedCaseStatusService {

	/** ユーザ情報テーブルDAO */
	private final AssignedCaseStatusRepository repository;
	private final UserInfoRepository userInfoRepository;
	
	@Override
	public Optional<AssignedCaseStatus> serchUserById(String userInfo) {
		var updateInfoOpt = repository.findById(userInfo);
		return updateInfoOpt;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UserAssignedCaseInfo getCase() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setUserAssignedCase(UserAssignedCaseInfo updateInfos) {
		AssignedCaseStatus updateInfo = new AssignedCaseStatus();
		updateInfo.setAssignedCaseID(updateInfos.getAssignedCaseID());
		updateInfo.setPartnerCompanyName(updateInfos.getPartnerCompanyName());
		updateInfo.setPartnerCompanyAddress(updateInfos.getPartnerCompanyAddress());
		updateInfo.setCaseDetail(updateInfos.getCaseDetail());
		updateInfo.setCaseStartDate(updateInfos.getCaseStartDate());
		updateInfo.setCaseEndDate(updateInfos.getCaseEndDate());
		try {
			repository.save(updateInfo);
		}catch(Exception e) {
			e.getStackTrace();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteUserAssignedCase(String assignedID) {
		repository.deleteById(assignedID);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setAssignedCaseID(String loginId) {
		var updateInfoOpt = userInfoRepository.findById(loginId);
		var updateInfo = updateInfoOpt.get();
		
	}

}
