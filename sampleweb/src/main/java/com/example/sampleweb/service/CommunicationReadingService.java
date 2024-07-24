package com.example.sampleweb.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.example.sampleweb.dto.UserComListInfo;
import com.example.sampleweb.entity.Communication;

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
	Optional<Communication> comWatchInfo(String selectId);

	/**
	 * {@inheritDoc}
	 */
	boolean watchInfo(String selectId);
	
}
