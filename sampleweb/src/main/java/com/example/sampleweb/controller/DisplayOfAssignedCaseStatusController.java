package com.example.sampleweb.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.sampleweb.constant.UrlConst;
import com.example.sampleweb.constant.ViewNameConst;
import com.example.sampleweb.dto.UserAssignedCaseInfo;
import com.example.sampleweb.form.DisplayCaseForm;
import com.example.sampleweb.service.DisplayOfAssignedCaseStatusServiceImpl;
import com.example.sampleweb.util.AppUtil;
import com.github.dozermapper.core.Mapper;

import lombok.RequiredArgsConstructor;

/**
 *  案件情報入力画面 Controller
 *  
 *  @author k-murata
 *  
 */

@Controller
@RequiredArgsConstructor
public class DisplayOfAssignedCaseStatusController {

	/** 案件情報入力画面 */
	private final DisplayOfAssignedCaseStatusServiceImpl service;
	
	/** 画面で使用するフォームクラス名 */
	private static final String FORM_CLASS_NAME = "DisplayCaseForm";
	
	/** Dozer Mapper */
	private final Mapper mapper;

	@GetMapping(UrlConst.DISPLAY_CASE)
	public String view(Model model,DisplayCaseForm form) {
		var isInitialDisp = !model.containsAttribute(FORM_CLASS_NAME);
		if(isInitialDisp) {
			model.addAttribute(FORM_CLASS_NAME,new DisplayCaseForm());
		}
		return ViewNameConst.DISPLAY_CASE;
	}
	
	@PostMapping(UrlConst.DISPLAY_CASE)
	public String signup(Model model,DisplayCaseForm form,@AuthenticationPrincipal User user) {
		var updateDto = mapper.map(form, UserAssignedCaseInfo.class);
		updateDto.setAssignedCaseID(1);
		service.setUserAssignedCase(updateDto);
		
		/** 案件情報が重複してた際のエラー */
		//model.addAttribute("message",MessageConst.DISPLAY_CASE_FAILD);


		return AppUtil.doRedirect(UrlConst.DISPLAY_CASE);
	}
}
