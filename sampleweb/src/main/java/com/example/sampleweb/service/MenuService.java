package com.example.sampleweb.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.sampleweb.entity.UserInfo;
import com.example.sampleweb.repository.UserInfoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MenuService {

	/** ユーザ情報テーブルDAO */
	private final UserInfoRepository repository;
	
	/**
	 * ユーザ情報テーブル 主キー検索
	 * @param loginId ログインID
	 * @return 検索結果
	 */
	public Optional<UserInfo>searchUserById(String loginId){
		return repository.findById(loginId);
	}

}