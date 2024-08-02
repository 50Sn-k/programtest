package com.example.sampleweb.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.sampleweb.constant.UrlConst;
import com.example.sampleweb.constant.ViewNameConst;
import com.example.sampleweb.form.ContractForm;
import com.example.sampleweb.form.LoginForm;
import com.example.sampleweb.service.ContractService;

import lombok.RequiredArgsConstructor;

@Controller
/**
 *  契約画面 Controller
 *  
 *  @author k-murata
 *  
 */
@RequiredArgsConstructor
public class ContractController {
	
	/** 契約画面 contract */
	private final ContractService service;
		
	/**
	 * 初期表示
	 * 
	 * @param model モデル
	 * @param form 入力情報
	 * @return 表示画面
	 */
	@GetMapping(UrlConst.CONTRACT)
	public String view(Model model,LoginForm form) {
		
		return ViewNameConst.CONTRACT;
	}
	
	/**
	 * ユーザ登録
	 * @param model モデル
	 * @param form 入力情報
	 * @return 表示画面
	 */
	@PostMapping(UrlConst.CONTRACT)
	public String contract(@AuthenticationPrincipal User user,ContractForm form) {
				
		return service.resistUserInfo(user.getUsername(),form);
	}
	

}
