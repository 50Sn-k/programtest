package com.example.sampleweb.service;

import java.util.ArrayList;
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
		
		return userComListInfos;
	}

}
