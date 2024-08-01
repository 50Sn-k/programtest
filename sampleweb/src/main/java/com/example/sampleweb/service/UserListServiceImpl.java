package com.example.sampleweb.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.sampleweb.constant.UserDeleteResult;
import com.example.sampleweb.dto.UserListInfo;
import com.example.sampleweb.dto.UserSearchInfo;
import com.example.sampleweb.entity.UserInfo;
import com.example.sampleweb.repository.UserInfoRepository;
import com.example.sampleweb.util.AppUtil;
import com.github.dozermapper.core.Mapper;

import lombok.RequiredArgsConstructor;

/**
 * ユーザー一覧画面Serviceクラス
 * 
 * @author k-suzuki
 * 
 */
@Service
@RequiredArgsConstructor
public class UserListServiceImpl implements UserListService{
	
	/*ユーザー情報テーブルDAO*/
	private final UserInfoRepository repository;
	
	/*Dozer Mapper*/
	private final Mapper mapper;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UserListInfo> editUserList(){
		return toUserListInfos(repository.findAll());
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UserListInfo> editUserListByParam(UserSearchInfo dto){
		return toUserListInfos(findUserInfoByParam(dto));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public UserDeleteResult deleteUserInfoById(String loginId){
		var userInfo = repository.findById(loginId);
		if(userInfo.isEmpty()) {
			return UserDeleteResult.ERROR;
		}
		repository.deleteById(loginId);
		
		return UserDeleteResult.SUCCEED;
	}
	
	/**
	 * ユーザー情報取得(条件付き)
	 * 
	 * ユーザー情報を条件取得する
	 * 
	 * @param form 入力情報
	 * @return 検索結果
	 */
	private List<UserInfo> findUserInfoByParam(UserSearchInfo dto){
		var loginIdParam = AppUtil.addWildcard(dto.getLoginId());
		var caseIdParam = AppUtil.addWildcard(dto.getCaseId());

		if (dto.getUserStatusKind() != null && dto.getAuthorityKind() != null) {
			return repository.findByLoginIdLikeAndUserStatusKindAndAuthorityKind(loginIdParam,caseIdParam,
					dto.getUserStatusKind(), dto.getAuthorityKind());
		} else if (dto.getUserStatusKind() != null) {
			return repository.findByLoginIdLikeAndUserStatusKind(loginIdParam,caseIdParam, dto.getUserStatusKind());
		} else if (dto.getAuthorityKind() != null) {
			return repository.findByLoginIdLikeAndAuthorityKind(loginIdParam,caseIdParam, dto.getAuthorityKind());
		} else {
			return repository.findByLoginIdLike(loginIdParam,caseIdParam);
		}
	}
	
	/**
	 * ユーザー情報EntityのListをユーザー一覧情報DTOのListに変換します
	 * 
	 * @param userInfos ユーザー情報EntityのList
	 * @return ユーザー一覧情報DTOのList
	 */
	private List<UserListInfo> toUserListInfos(List<UserInfo> userInfos){
		var userListInfos = new ArrayList<UserListInfo>();
		for(UserInfo userInfo : userInfos) {
			var userListInfo = mapper.map(userInfo, UserListInfo.class);
			userListInfo.setStatus(userInfo.getUserStatusKind().getDisplayValue());
			userListInfo.setAuthority(userInfo.getAuthorityKind().getDisplayValue());
			userListInfos.add(userListInfo);
		}
		
		return userListInfos;
	}
	
	
	
}
