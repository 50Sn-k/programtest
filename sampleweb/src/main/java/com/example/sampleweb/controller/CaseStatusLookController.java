package com.example.sampleweb.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.sampleweb.constant.UrlConst;
import com.example.sampleweb.constant.ViewNameConst;
import com.example.sampleweb.entity.Case;
import com.example.sampleweb.form.CaseStatusListForm;
import com.example.sampleweb.repository.CaseRepository;
import com.example.sampleweb.repository.UserInfoRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CaseStatusLookController {
	
	/*案件情報情報テーブルDTO*/
	private final CaseRepository repositoryCase;
	
	/*ユーザー情報テーブルDTO*/
	private final UserInfoRepository repositoryUser;
	
	/**
	 * 画面の初期表示を行います。
	 * 
	 * @param model モデル
	 * @param form 入力情報
	 * @param user 認証済みユーザー情報
	 * @return ユーザー一覧画面テンプレート名
	 */
	@GetMapping(UrlConst.CASE_LOOK)
	public String view(Model model,CaseStatusListForm form,@AuthenticationPrincipal UserDetails user) {
		try {
			var userInfo = repositoryUser.findById(user.getUsername());
			var userCaseInfo = repositoryCase.findByCaseIdLike(userInfo.get().getCaseId());
			model.addAttribute("caseStatusListInfo",userCaseInfo.getFirst());
			model.addAttribute("noinfo",true);
		}catch(Exception e) {
			model.addAttribute("caseStatusListInfo",new Case());
			model.addAttribute("noinfo",false);
		}
		
		return ViewNameConst.CASE_LOOK;
	}
}
