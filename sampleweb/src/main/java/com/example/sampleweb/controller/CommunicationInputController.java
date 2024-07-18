package com.example.sampleweb.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.sampleweb.constant.UrlConst;
import com.example.sampleweb.constant.ViewNameConst;
import com.example.sampleweb.dto.UserComListInfo;
import com.example.sampleweb.form.UserComListForm;
import com.example.sampleweb.service.CommunicationInputServiceImpl;
import com.example.sampleweb.util.AppUtil;
import com.github.dozermapper.core.Mapper;

import lombok.RequiredArgsConstructor;

/**
 * 
 * 社内連絡入力画面
 * 
 * @author k-murata
 * 
 */

@Controller
@RequiredArgsConstructor
public class CommunicationInputController {
	
	/** 画面で使用するフォームクラス名 */
	private static final String FORM_CLASS_NAME = "UserComListForm";

	/** ユーザー編集画面Serviceクラス */
	private final CommunicationInputServiceImpl service;

//	/** セッションオブジェクト */
//	private final HttpSession session;

	/** Dozer Mapper */
	private final Mapper mapper;
	
//	/** メッセージソース */
//	private final MessageSource messageSource;
	
	/**
	 * ログインIDに紐づくユーザー情報を画面に表示します。
	 * 
	 * @param model モデル
	 * @param form 入力情報
	 * @return ユーザー編集画面テンプレート名
	 * @throws Exception 
	 */
	@GetMapping(UrlConst.COM_INPUT)
	public String view(Model model,UserComListForm form) {
		var isInitialDisp = !model.containsAttribute(FORM_CLASS_NAME);
		if(isInitialDisp) {
			model.addAttribute(FORM_CLASS_NAME,new UserComListForm());
		}
		return ViewNameConst.COM_INPUT;
	}
	
	/**
	 * 画面の入力情報をもとにユーザー情報を更新します。
	 * 
	 * @param form 入力情報
	 * @param user 認証済みユーザー情報
	 * @param redirectAttributes リダイレクト用オブジェクト
	 * @return リダイレクトURL
	 */
	@PostMapping(value = UrlConst.COM_INPUT, params = "update")
	public String signup(Model model,UserComListForm form,@AuthenticationPrincipal User user) {
		var updateDto = mapper.map(form, UserComListInfo.class);
		updateDto.setLoginId(user.getUsername());
		service.setCom(updateDto);

		return AppUtil.doRedirect(UrlConst.COM_INPUT);
	}


}
