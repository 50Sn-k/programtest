package com.example.sampleweb.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.sampleweb.dto.SkillSheetListInfo;
import com.example.sampleweb.entity.SkillSheet;
import com.example.sampleweb.repository.SkillSheetRepository;
import com.github.dozermapper.core.Mapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SkillSheetLookServiceImpl implements SkillSheetLookService{
	
	/*ユーザー情報テーブルDTO*/
	private final SkillSheetRepository repository;
	
	/*Dozer Mapper*/
	private final Mapper mapper;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<SkillSheetListInfo> editSkillSheetList(){
		return toSkillSheetListInfos(repository.findAll());
	}
	
	/**
	 * 社内連絡情報取得
	 * 
	 * 社内連絡情報を取得する
	 * 
	 * @param Communications 社内連絡情報EntityのList
	 * @return 社内連絡情報のList
	 */
	public List<SkillSheetListInfo> toSkillSheetListInfos(List<SkillSheet> SkillSheets){
		var skillSheetListInfos = new ArrayList<SkillSheetListInfo>();
		for(SkillSheet skillSheet : SkillSheets) {
			var skillSheetListInfo = mapper.map(skillSheet, SkillSheetListInfo.class);
			skillSheetListInfos.add(skillSheetListInfo);
		}
		
		return skillSheetListInfos;
	}

}
