package com.example.sampleweb.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.sampleweb.constant.UrlConst;
import com.example.sampleweb.constant.ViewNameConst;
import com.example.sampleweb.dto.SkillSheetListInfo;
import com.example.sampleweb.form.SkillSheetForm;
import com.example.sampleweb.form.UserComListForm;
import com.example.sampleweb.service.SkillSeatServiceImpl;
import com.example.sampleweb.util.AppUtil;
import com.github.dozermapper.core.Mapper;

import lombok.RequiredArgsConstructor;

/**
 * スキルシート情報入力画面コントローラー
 * 
 * @author k-murata
 */

@Controller
@RequiredArgsConstructor
public class SkillSeatController {
	
	
	/** 画面で使用するフォームクラス名 */
	private static final String FORM_CLASS_NAME = "SkillSheetForm";
	
	/** ユーザー編集画面Serviceクラス */
	private final SkillSeatServiceImpl service;

	/** Dozer Mapper */
	private final Mapper mapper;
	
	/**
	 * ログインIDに紐づくユーザー情報を画面に表示します。
	 * 
	 * @param model モデル
	 * @param form 入力情報
	 * @return ユーザー編集画面テンプレート名
	 * @throws Exception 
	 */
	@GetMapping(UrlConst.SKILL_SHEET_LOOK)
	public String view(Model model,SkillSheetForm form) {
		var isInitialDisp = !model.containsAttribute(FORM_CLASS_NAME);
		if(isInitialDisp) {
			model.addAttribute(FORM_CLASS_NAME,new UserComListForm());
		}
		return ViewNameConst.SKILL_SHEET_LOOK;
	}
	
	/**
	 * 画面の入力情報をもとにユーザー情報を更新します。
	 * 
	 * @param form 入力情報
	 * @param user 認証済みユーザー情報
	 * @param redirectAttributes リダイレクト用オブジェクト
	 * @return リダイレクトURL
	 */
	@PostMapping(value = UrlConst.SKILL_SHEET_LOOK, params = "update")
	public String signup(Model model,SkillSheetForm form,@AuthenticationPrincipal User user) {
		var updateDto = mapper.map(form, SkillSheetListInfo.class);
		updateDto.setLoginId(user.getUsername());
		service.inputSkill(updateDto);

		return AppUtil.doRedirect(UrlConst.SKILL_SHEET_LOOK);
	}

}
