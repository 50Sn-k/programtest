package com.example.sampleweb.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.sampleweb.dto.UserComListInfo;
import com.example.sampleweb.entity.UserInfo;
import com.example.sampleweb.repository.UserInfoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommunicationInputServiceImpl implements CommunicationInputService {

	private final CommunicationInputServiceImpl service;

	/** ユーザ情報テーブルDAO */
	private final UserInfoRepository repository;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<UserInfo> serchUserById(String userInfo) {
		var updateInfoOpt = repository.findById(userInfo);
		return updateInfoOpt;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setContactAddress(String address) {
		service.setContactAddress(address);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setCom(UserComListInfo userComListInfos) {
		var updateInfoOpt = repository.findById(userComListInfos.getLoginId());
		var updateInfo = updateInfoOpt.get();
//		updateInfo.setNotice(userComListInfos.getNotice());
//		updateInfo.setCompanyAddress(userComListInfos.getNote());
//		updateInfo.setTelephoneNumber(userComListInfos.getContactAddress());
//		updateInfo.setTelephoneNumber(userComListInfos.getWorkDetails());
//		updateInfo.setTelephoneNumber(userComListInfos.getWorkStatus());
//		updateInfo.setTelephoneNumber(userComListInfos.isNoticeWatched());
//		updateInfo.setTelephoneNumber(userComListInfos.getDailyReport());
//		updateInfo.setTelephoneNumber(userComListInfos.getWeeklyReport());
//		updateInfo.setTelephoneNumber(userComListInfos.getMonthlyReport());

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
	public boolean isSubmissonDay(UserComListInfo userComListInfos) {
		boolean isDay;
		
		if(userComListInfos.getDailyReport() == null) {
			isDay = false;
		}
		else {
			isDay = true;
		}
		
		return isDay;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isSubmissonWeek(UserComListInfo userComListInfos) {
		boolean isWeek;
		
		if(userComListInfos.getDailyReport() == null) {
			isWeek = false;
		}
		else {
			isWeek = true;
		}
		
		return isWeek;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isSubmissonMonth(UserComListInfo userComListInfos) {
		boolean isMonth;
		
		if(userComListInfos.getDailyReport() == null) {
			isMonth = false;
		}
		else {
			isMonth = true;
		}
		
		return isMonth;
	}

}
