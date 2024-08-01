package com.example.sampleweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sampleweb.constant.db.AuthorityKind;
import com.example.sampleweb.constant.db.UserStatusKind;
import com.example.sampleweb.entity.UserInfo;

public interface UserInfoRepository extends JpaRepository<UserInfo,String>{

	/**
	 * ログインIDの部分一致検索を行います。
	 * 
	 * @param loginId ログインID
	 * @param caseId 案件ID
	 * @return 検索でヒットしたユーザー情報のリスト
	 */
	List<UserInfo> findByLoginIdLike(String loginId,String caseId);

	/**
	 * ログインID、アカウント状態の項目を使って検索を行います。
	 * 
	 * <p>■検索条件
	 * <lu>
	 * <li>ログインID：部分一致</li>
	 * <li>アカウント状態：完全一致</li>
	 * </lu>
	 * <p>
	 * 
	 * @param loginId ログインID
	 * @param userStatusKind アカウント状態
	 * @return 検索でヒットしたユーザー情報のリスト
	 */
	List<UserInfo> findByLoginIdLikeAndUserStatusKind(String loginId,String caseId, UserStatusKind userStatusKind);

	/**
	 * ログインID、権限の項目を使って検索を行います。
	 * 
	 * <p>■検索条件
	 * <lu>
	 * <li>ログインID：部分一致</li>
	 * <li>権限：完全一致</li>
	 * </lu>
	 * <p>
	 * 
	 * @param loginId ログインID
	 * @param authorityKind 権限
	 * @return 検索でヒットしたユーザー情報のリスト
	 */
	List<UserInfo> findByLoginIdLikeAndAuthorityKind(String loginId,String caseId, AuthorityKind authorityKind);

	/**
	 * ログインID、アカウント状態、権限の項目を使って検索を行います。
	 * 
	 * <p>■検索条件
	 * <lu>
	 * <li>ログインID：部分一致</li>
	 * <li>アカウント状態：完全一致</li>
	 * <li>権限：完全一致</li>
	 * </lu>
	 * <p>
	 * 
	 * @param loginId ログインID
	 * @param userStatusKind アカウント状態
	 * @param authorityKind 権限
	 * @return 検索でヒットしたユーザー情報のリスト
	 */
	List<UserInfo> findByLoginIdLikeAndUserStatusKindAndAuthorityKind(String loginId,String caseId, UserStatusKind userStatusKind,
			AuthorityKind authorityKind);
	
	/**
	 * 案件IDの部分一致検索を行います。
	 * 
	 * @param caseId 案件ID
	 * @return 検索でヒットしたユーザー情報のリスト
	 */
	List<UserInfo> findByCaseIdLike(String caseId);

}