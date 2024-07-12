package com.example.sampleweb.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.sampleweb.dto.UserComListInfo;
import com.example.sampleweb.entity.UserInfo;
import com.example.sampleweb.repository.UserInfoRepository;
import com.github.dozermapper.core.Mapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommunicationReadingServiceImpl implements CommunicationReadingService{
	
	/*ユーザー情報テーブルDAO*/
	private final UserInfoRepository repository;
	
	/*Dozer Mapper*/
	private final Mapper mapper;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UserComListInfo> editUserComList(){
		return toComReadListInfos(repository.findAll());
	}
	
	public List<UserComListInfo> toComReadListInfos(List<UserInfo> userInfos){
		var userComListInfos = new ArrayList<UserComListInfo>();
		for(UserInfo userInfo : userInfos) {
			var userComListInfo = mapper.map(userInfo, UserComListInfo.class);
			userComListInfos.add(userComListInfo);
		}
		
		return userComListInfos;
	}

}
