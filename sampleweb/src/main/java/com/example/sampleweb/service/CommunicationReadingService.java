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
	 * 
	 * @author k-suzuki
	 */
	public List<UserComListInfo> editUserComMenuList();

	public int compareTo(LocalDateTime s);

	/**
	 * 未読の社内連絡を既読にする
	 * 
	 * @author k-suzuki
	 */
	Optional<Communication> comWatchInfo(String selectId);

	/**
	 * 未読の社内連絡を既読にする
	 * 
	 * @author k-suzuki
	 */
	boolean watchInfo(String selectId);
	
}
