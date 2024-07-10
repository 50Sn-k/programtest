package com.example.sampleweb.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.sampleweb.entity.UserInfo;
import com.example.sampleweb.repository.UserInfoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginService {

	private final UserInfoRepository repository;
	
	public Optional<UserInfo> searchUserById(String loginId){
		return repository.findById(loginId);
	}
}
