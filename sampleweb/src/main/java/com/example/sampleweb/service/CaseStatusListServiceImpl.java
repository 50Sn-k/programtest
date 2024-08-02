package com.example.sampleweb.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.sampleweb.constant.CaseDeleteResult;
import com.example.sampleweb.dto.CaseSearchInfo;
import com.example.sampleweb.dto.CaseStatusListInfo;
import com.example.sampleweb.entity.Case;
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
public class CaseStatusListServiceImpl implements CaseStatusListService{
	
	/*案件情報テーブルDAO*/
	private final CaseRepository repository;
	
	/*Dozer Mapper*/
	private final Mapper mapper;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<CaseStatusListInfo> editCaseList(){
		return toCaseListInfos(repository.findAll());
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<CaseStatusListInfo> editCaseListByParam(CaseSearchInfo dto){
		return toCaseListInfos(findCaseInfoByParam(dto));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public CaseDeleteResult deleteCaseInfoById(String CaseId){
		var userInfo = repository.findById(CaseId);
		if(userInfo.isEmpty()) {
			return CaseDeleteResult.ERROR;
		}
		repository.deleteById(CaseId);
		
		return CaseDeleteResult.SUCCEED;
	}
	
	/**
	 * 案件情報取得(条件付き)
	 * 
	 * 案件情報リストを条件取得する
	 * 
	 * @param dto 検索情報
	 * @return 検索結果
	 */
	private List<Case> findCaseInfoByParam(CaseSearchInfo dto){
		var caseIdParam = AppUtil.addWildcard(dto.getCaseId());

		if (dto.getCaseStatus() != null) {
			
			return repository.findByCaseIdLikeAndCaseStatus(caseIdParam, dto.getCaseStatus());
		} else {
			return repository.findByCaseIdLike(caseIdParam);
		}
	}
	
	/**
	 * 案件情報EntityのListをユーザー一覧情報DTOのListに変換します
	 * 
	 * @param list 案件情報EntityのList
	 * @return 案件一覧情報DTOのList
	 */
	private List<CaseStatusListInfo> toCaseListInfos(List<Case> list){
		var caseStatusInfos = new ArrayList<CaseStatusListInfo>();
		for(Case caseStatus : list) {
			var caseStatusInfo = mapper.map(caseStatus, CaseStatusListInfo.class);
			caseStatusInfo.setCaseStatus(caseStatus.getCaseStatus().getDisplayValue());
			caseStatusInfos.add(caseStatusInfo);
		}
		
		return caseStatusInfos;
	}
	
	
	
}
