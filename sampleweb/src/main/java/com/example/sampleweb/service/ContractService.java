package com.example.sampleweb.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.sampleweb.entity.UserInfo;
import com.example.sampleweb.form.ContractForm;
import com.example.sampleweb.repository.UserInfoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ContractService {

	/** ユーザ情報テーブルDAO */
	private final UserInfoRepository repository;
	
	/** Dozer Mapper */
//	private final Mapper mapper;
	
	/**
	 * ユーザ情報テーブル 主キー検索
	 * @param loginId ログインID
	 * @return 登録情報(ユーザ情報Entitiy)
	 */
	public String resistUserInfo(String userId,ContractForm form){
		
		String sendhtml = null;
		
		if(form.getPrivacy()==null) {
			sendhtml="menu";
		}else if(form.getPrivacy().equals("agree")) {
		
			repository.findById(userId).ifPresent(userInfo -> {
				repository.save(userInfo.completeContract());
			});
			sendhtml="contracted";
		}
		return sendhtml;
	}
	
	
	/**
	 * ユーザ情報テーブル 主キー検索
	 * @param loginId ログインID
	 * @return 検索結果
	 */
	public Optional<UserInfo>serchUserById(String loginId){
		return repository.findById(loginId);
	}


}
