package com.example.sampleweb.controller;

import org.springframework.context.MessageSource;
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
import com.example.sampleweb.service.CaseStatusListService;
import com.github.dozermapper.core.Mapper;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CaseStatusLookController {

	/*案件一覧画面Serviceクラス*/
	private final CaseStatusListService service;
	
	/*Dozer Mapper*/
	private final Mapper mapper;
	
	/*メッセージソース*/
	private final MessageSource messageSource;
	
	/*セッションオブジェクト*/
	private final HttpSession session;
	
	/*ユーザー情報テーブルDAO*/
	private final CaseRepository repositoryCase;
	
	/*ユーザー情報テーブルDAO*/
	private final UserInfoRepository repositoryUser;
	
	

	/*モデルキー：ユーザー情報リストフォーム */
	private static final String KEY_CASE_STATUS_LIST_FORM = "caseStatusListForm";
	
	/*モデルキー:ユーザー情報リスト */
	private static final String KEY_CASE_STATUS_LIST = "caseStatusList";
	
	/*モデルキー:ユーザー情報リスト*/
	private static final String KEY_CASE_STATUS_KIND_OPTIONS ="caseStatusKindOptions";
	
	/*モデルキー:操作種別 */
	private static final String KEY_OPERATION_KIND = "operationKind";
	
	/**
	 * 画面の初期表示を行います。
	 * 
	 * <p>またその際、画面選択項目「アカウント状態」「所有権限」の選択肢を生成して画面に渡します。
	 * 
	 * @param model モデル
	 * @param form 入力情報
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
