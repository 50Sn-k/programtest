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
	
	/*ユーザー情報テーブルDAO*/
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
	
	public List<UserComListInfo> toComReadListInfos(List<Communication> Communications){
		var userComListInfos = new ArrayList<UserComListInfo>();
		for(Communication communication : Communications) {
			var userComListInfo = mapper.map(communication, UserComListInfo.class);
			userComListInfos.add(userComListInfo);
		}
		
		return userComListInfos;
	}

}
