package com.example.sampleweb.service;

import java.util.Optional;

import com.example.sampleweb.dto.UserComListInfo;
import com.example.sampleweb.entity.UserInfo;


public interface CommunicationInputService {

	/** ユーザデータ呼び出し */
	public Optional<UserInfo> serchUserById(String userInfo);
	
	/** 連絡先入力 */
	public void setContactAddress(String address);
	
	/** 連絡事項、備考、作業内容、作業状況入力 */
	public void setCom(UserComListInfo userComListInfos);
	
	/** 日報提出状況 */
	public boolean isSubmissonDay(UserComListInfo userComListInfos);

	/** 週報提出状況 */
	public boolean isSubmissonWeek(UserComListInfo userComListInfos);

	/** 月報提出状況 */
	public boolean isSubmissonMonth(UserComListInfo userComListInfos);
	
}
