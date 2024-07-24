package com.example.sampleweb.service;

import java.util.Optional;

import com.example.sampleweb.dto.SkillSheetListInfo;
import com.example.sampleweb.entity.SkillSheet;

public interface SkillSeatService {

	/** ユーザデータ呼び出し */
	public Optional<SkillSheet> serchUserById(String loginId);
	
	/** 取得資格入力 */
	public void inputSkill(SkillSheetListInfo skillSheetListInfos);
}
