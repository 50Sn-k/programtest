package com.example.sampleweb.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.sampleweb.dto.SkillSheetListInfo;
import com.example.sampleweb.entity.SkillSheet;
import com.example.sampleweb.repository.SkillSheetRepository;

import lombok.RequiredArgsConstructor;

/**
 * スキルシート情報入力画面サービス
 * 
 * @author k-murata
 */

@Service
@RequiredArgsConstructor
public class SkillSeatServiceImpl implements SkillSeatService {

	/** ユーザ情報テーブルDAO */
	private final SkillSheetRepository repository;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<SkillSheet> serchUserById(String loginId) {
		var skillSheetInfoOpt = repository.findById(loginId);
		return skillSheetInfoOpt;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void inputSkill(SkillSheetListInfo skillSheetListInfos) {
		SkillSheet skillSheetInfo = new SkillSheet();
		
		skillSheetInfo.setLoginId(skillSheetListInfos.getLoginId());
		skillSheetInfo.setQualification(skillSheetListInfos.getQualification());
		skillSheetInfo.setAchievements(skillSheetListInfos.getAchievements());
		
		try {
			repository.save(skillSheetInfo);
		}catch(Exception e) {
			e.getStackTrace();
		}		
	}

}
