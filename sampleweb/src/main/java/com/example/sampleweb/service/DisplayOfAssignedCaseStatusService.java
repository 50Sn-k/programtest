package com.example.sampleweb.service;

import java.util.Optional;

import com.example.sampleweb.dto.UserAssignedCaseInfo;
import com.example.sampleweb.entity.UserInfo;

public interface DisplayOfAssignedCaseStatusService {

	public Optional<UserInfo> serchUserById(String loginId);
	
	public UserAssignedCaseInfo getCase();
	
	public void setUserAssignedCase(UserAssignedCaseInfo updateInfos);
	
	public void deleteUserAssignedCase();
	
	public void setAssinedCaseID(int caseId);
}
