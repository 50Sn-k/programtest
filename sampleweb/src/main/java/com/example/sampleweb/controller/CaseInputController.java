package com.example.sampleweb.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.context.MessageSource;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.sampleweb.constant.ModelKey;
import com.example.sampleweb.constant.UrlConst;
import com.example.sampleweb.constant.ViewNameConst;
import com.example.sampleweb.constant.db.AuthorityKind;
import com.example.sampleweb.dto.CaseStatusListInfo;
import com.example.sampleweb.form.CaseStatusListForm;
import com.example.sampleweb.service.CaseInputServiceImpl;
import com.example.sampleweb.util.AppUtil;
import com.github.dozermapper.core.Mapper;

import lombok.RequiredArgsConstructor;

/**
 * 
 * 社内連絡入力画面
 * 
 * @author k-suzuki
 * 
 */

@Controller
@RequiredArgsConstructor
public class CaseInputController {
	
	/** 画面で使用するフォームクラス名 */
	private static final String FORM_CLASS_NAME = "CaseStatusListForm";

	/** 案件情報登録画面Serviceクラス */
	private final CaseInputServiceImpl service;

	/** Dozer Mapper */
	private final Mapper mapper;
	
	/** メッセージソース */
	private final MessageSource messageSource;
	
	/**
	 * 案件IDに紐づく案件情報を画面に表示します。
	 * 
	 * @param model モデル
	 * @param form 入力情報
	 * @param user 認証済みユーザー情報
	 * @return ユーザー編集画面テンプレート名
	 * @throws Exception 
	 */
	@GetMapping(UrlConst.CASE_INPUT)
	public String view(Model model,CaseStatusListForm form,@AuthenticationPrincipal UserDetails user) {
		var isInitialDisp = !model.containsAttribute(FORM_CLASS_NAME);
		if(isInitialDisp) {
			model.addAttribute(FORM_CLASS_NAME,new CaseStatusListForm());
		}
		var userAuthority = user.getAuthorities().stream()
				.allMatch(authority -> authority.getAuthority()
						.equals(AuthorityKind.ITEM_AND_USER_MANAGER.getCode()));
		model.addAttribute("userAuthority",userAuthority);
		return ViewNameConst.CASE_INPUT;
	}
	
	/**
	 * 画面の入力情報をもとにユーザー情報を更新します。
	 * 
	 * @param model モデル
	 * @param form 入力情報
	 * @param user 認証済みユーザー情報
	 * @param redirectAttributes リダイレクト用オブジェクト
	 * @return リダイレクトURL
	 */
	@PostMapping(value = UrlConst.CASE_INPUT, params = "input")
	public String signup(Model model,CaseStatusListForm form,@AuthenticationPrincipal User user,RedirectAttributes redirectAttributes) {
		var newCase = mapper.map(form, CaseStatusListInfo.class);
		if(newCase.getStartDate().isEmpty()==false) {
			LocalDateTime firstDate = LocalDateTime.parse((newCase.getStartDate()+"T00:00:00"),DateTimeFormatter.ISO_LOCAL_DATE_TIME);
			newCase.setCaseStartDate(firstDate);
		}
		if(newCase.getFinishDate().isEmpty()==false) {
			LocalDateTime endDate = LocalDateTime.parse((newCase.getFinishDate()+"T00:00:00"),DateTimeFormatter.ISO_LOCAL_DATE_TIME);
			newCase.setCaseFinishDate(endDate);
		}
		var inputResult = service.setNewCase(newCase);
		var updateMessage = inputResult.getCaseMessage();
		redirectAttributes.addFlashAttribute(ModelKey.IS_ERROR, false);
		redirectAttributes.addFlashAttribute(ModelKey.MESSAGE, AppUtil.getMessage(messageSource, updateMessage.getMessageId()));

		return AppUtil.doRedirect(UrlConst.CASE_INPUT);
	}
}
