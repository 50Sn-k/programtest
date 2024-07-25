package com.example.sampleweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sampleweb.constant.db.CaseStatusKind;
import com.example.sampleweb.entity.AssignedCaseStatus;

public interface CaseRepository extends JpaRepository<AssignedCaseStatus,String>{

	/**
	 * ログインIDの部分一致検索を行います。
	 * 
	 * @param loginId ログインID
	 * @return 検索でヒットしたユーザー情報のリスト
	 */
	List<AssignedCaseStatus> findByCaseIdLike(String caseId);

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
	List<AssignedCaseStatus> findByCaseIdLikeAndCaseStatusKind(String caseId, CaseStatusKind caseStatusKind);

}