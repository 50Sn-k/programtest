package com.example.sampleweb.dto;

import lombok.Data;

/**
 * 	ユーザー一覧画面DTOクラス
 * 
 * @author k-suzuki
 * 
 */
@Data
public class SkillSheetListInfo {

	/*ログインID*/
	private String loginId;
	
	/*資格*/
	private String qualification;
	
	/*実績*/
	private String achievements;
}