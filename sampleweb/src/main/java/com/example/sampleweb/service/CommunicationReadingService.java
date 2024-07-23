package com.example.sampleweb.service;

import java.time.LocalDateTime;
import java.util.List;

import com.example.sampleweb.dto.UserComListInfo;

public interface CommunicationReadingService {

	/**
	 * 社内連絡一覧に表示する内容
	 * 
	 * @author k-suzuki
	 */
	public List<UserComListInfo> editUserComList();

	/**
	 * メニュー画面に表示する未読の社内連絡内容
	 * 
	 * 社内連絡一覧に表示する内容
	 */
	public List<UserComListInfo> editUserComMenuList();

	public int compareTo(LocalDateTime s);

	/**
	 * {@inheritDoc}
	 */
	boolean watchInfo(UserComListInfo userComListInfo);
	
}
