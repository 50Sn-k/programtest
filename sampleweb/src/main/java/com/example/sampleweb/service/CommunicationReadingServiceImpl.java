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
	 * @return 未読の社内連絡情報のList(最大5件まで)
	 */
	public List<UserComListInfo> toComMenuListInfos(List<Communication> Communications){
		var allcomListInfo = toComReadListInfos(Communications);
		List<UserComListInfo> newComList = new ArrayList<UserComListInfo>();
		for(int i = 0;i < allcomListInfo.size();i++) {
			if(allcomListInfo.get(i).isNoticeWatched()==false) {
				newComList.add(allcomListInfo.get(i));
			}
			if(newComList.size()>=5){
				break;
			}
		}
		return newComList;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean watchInfo(UserComListInfo userComListInfo) {

		// 現在の登録情報を取得
		var comReadingInfoOpt = repository.findById(userComListInfo.getLoginId());
		if (comReadingInfoOpt.isEmpty()) {
			return false;
		}

		// 画面の入力情報等をセット
		var updateInfo = comReadingInfoOpt.get();
		updateInfo.setNoticeWatched(true);

		try {
			repository.save(updateInfo);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public int compareTo(LocalDateTime s) {
		return 0;
	}
	
}
