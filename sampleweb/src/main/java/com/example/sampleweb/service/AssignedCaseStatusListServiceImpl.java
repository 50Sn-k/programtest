package com.example.sampleweb.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.sampleweb.constant.UserDeleteResult;
import com.example.sampleweb.dto.AssignedCaseStatusListInfo;
import com.example.sampleweb.dto.CaseSearchInfo;
import com.example.sampleweb.entity.AssignedCaseStatus;
import com.example.sampleweb.repository.CaseRepository;
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
public class AssignedCaseStatusListServiceImpl implements AssignedCaseStatusListService{
	
	/*ユーザー情報テーブルDAO*/
	private final CaseRepository repository;
	
	/*Dozer Mapper*/
	private final Mapper mapper;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<AssignedCaseStatusListInfo> editCaseList(){
		return toCaseListInfos(repository.findAll());
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<AssignedCaseStatusListInfo> editCaseListByParam(CaseSearchInfo dto){
		return toCaseListInfos(findUserInfoByParam(dto));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public UserDeleteResult deleteCaseInfoById(String CaseId){
		var userInfo = repository.findById(CaseId);
		if(userInfo.isEmpty()) {
			return UserDeleteResult.ERROR;
		}
		repository.deleteById(CaseId);
		
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
	private List<AssignedCaseStatus> findUserInfoByParam(CaseSearchInfo dto){
		var caseIdParam = AppUtil.addWildcard(dto.getCaseId());

		if (dto.getCaseStatusKind() != null) {
			return repository.findByCaseIdLikeAndCaseStatusKind(caseIdParam, dto.getCaseStatusKind());
		} else {
			return repository.findByCaseIdLike(caseIdParam);
		}
	}
	
	/**
	 * ユーザー情報EntityのListをユーザー一覧情報DTOのListに変換します
	 * 
	 * @param list ユーザー情報EntityのList
	 * @return ユーザー一覧情報DTOのList
	 */
	private List<AssignedCaseStatusListInfo> toCaseListInfos(List<AssignedCaseStatus> list){
		var assignedCaseStatusInfos = new ArrayList<AssignedCaseStatusListInfo>();
		for(AssignedCaseStatus assignedCaseStatuss : list) {
			var assignedCaseStatusInfo = mapper.map(assignedCaseStatuss, AssignedCaseStatusListInfo.class);
			assignedCaseStatusInfo.setCaseStatus(assignedCaseStatuss.getCaseStatus().getDisplayValue());
			assignedCaseStatusInfos.add(assignedCaseStatusInfo);
		}
		
		return assignedCaseStatusInfos;
	}
	
	
	
}
