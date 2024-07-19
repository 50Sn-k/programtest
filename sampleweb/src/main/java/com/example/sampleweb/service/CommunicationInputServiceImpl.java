package com.example.sampleweb.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.sampleweb.dto.UserComListInfo;
import com.example.sampleweb.entity.Communication;
import com.example.sampleweb.repository.CommunicationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommunicationInputServiceImpl implements CommunicationInputService {

	/** ユーザ情報テーブルDAO */
	private final CommunicationRepository repository;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<Communication> serchUserById(String userInfo) {
		var updateInfoOpt = repository.findById(userInfo);
		return updateInfoOpt;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setCom(UserComListInfo userComListInfos) {
		Communication updateInfo = new Communication();
		updateInfo.setLoginId(userComListInfos.getLoginId());
		updateInfo.setNotice(userComListInfos.getNotice());
		updateInfo.setNote(userComListInfos.getNote());
		updateInfo.setContactAddress(userComListInfos.getContactAddress());
		updateInfo.setWorkDetails(userComListInfos.getWorkDetails());
		updateInfo.setWorkStatus(userComListInfos.getWorkStatus());
		updateInfo.setNoticeWatched(false);
		updateInfo.setDailyReport(userComListInfos.getDailyReport());
		updateInfo.setWeeklyReport(userComListInfos.getWeeklyReport());
		updateInfo.setMonthlyReport(userComListInfos.getMonthlyReport());
		updateInfo.setUpdateTime(LocalDateTime.now());

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
