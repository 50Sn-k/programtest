package com.example.sampleweb.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.sampleweb.dto.UserComListInfo;
import com.example.sampleweb.entity.Communication;
import com.example.sampleweb.repository.CommunicationRepository;
import com.github.dozermapper.core.Mapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommunicationReadingServiceImpl implements CommunicationReadingService{
	
	/*ユーザー情報テーブルDTO*/
	private final CommunicationRepository repository;
	
	/*Dozer Mapper*/
	private final Mapper mapper;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UserComListInfo> editUserComList(){
		return toComReadListInfos(repository.findAll());
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UserComListInfo> editUserComMenuList(){
		return toComMenuListInfos(repository.findAll());
	}
	
	/**
	 * 社内連絡情報取得
	 * 
	 * 社内連絡情報を取得する
	 * 
	 * @param Communications 社内連絡情報EntityのList
	 * @return 社内連絡情報のList
	 */
	public List<UserComListInfo> toComReadListInfos(List<Communication> Communications){
		var userComListInfos = new ArrayList<UserComListInfo>();
		for(Communication communication : Communications) {
			var userComListInfo = mapper.map(communication, UserComListInfo.class);
			userComListInfos.add(userComListInfo);
		}
		userComListInfos.sort(Comparator.comparing(UserComListInfo::getUpdateTime).reversed());
		return userComListInfos;
	}

	
	/**
	 * メニュー画面用未読社内連絡情報取得
	 * 
	 * メニュー画面に未読の社内連絡情報を5件まで取得する
	 * 
	 * @param Communications 社内連絡情報EntityのList
	 * @return 社内連絡情報のList
	 */
	public List<UserComListInfo> toComMenuListInfos(List<Communication> Communications){
		var userComListInfos = new ArrayList<UserComListInfo>();
		for(Communication communication : Communications) {
			var userComListInfo = mapper.map(communication, UserComListInfo.class);
			if(userComListInfo.isNoticeWatched()==false){
				userComListInfos.add(userComListInfo);
				if(userComListInfos.size()>=5) {
					break;
				}
			}
		}
		userComListInfos.sort(Comparator.comparing(UserComListInfo::getUpdateTime).reversed());
		return userComListInfos;
	}

	@Override
	public int compareTo(LocalDateTime s) {
		return 0;
	}
	
}
