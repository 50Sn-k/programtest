package com.example.sampleweb.controller;

import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.sampleweb.constant.SessionKeyConst;
import com.example.sampleweb.constant.UrlConst;
import com.example.sampleweb.constant.ViewNameConst;
import com.example.sampleweb.constant.db.AuthorityKind;
import com.example.sampleweb.dto.SkillSheetListInfo;
import com.example.sampleweb.form.ComReadingForm;
import com.example.sampleweb.service.SkillSheetLookService;
import com.github.dozermapper.core.Mapper;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

/**
 * 社内連絡情報コントローラー
 * 
 * @author k-suzuki
 * 
 */

@Controller
@RequiredArgsConstructor
public class SkillSheetLookController {
	
	/*ユーザー登録画面Serviceクラス*/
	private final SkillSheetLookService service;
	
	/*メッセージソース*/
	private final MessageSource messageSource;
	
	/** オブジェクト間項目輸送クラス */
	private final Mapper mapper;

	/** セッションオブジェクト */
	private final HttpSession session;
	
	/*モデルキー:ユーザー情報リスト */
	private static final String KEY_SKILL_SHEET_LOOK = "skillSheetLook";

	/**
	 * 画面の初期表示を行います
	 * 
	 * @param model モデル
	 * @param form 入力情報
	 * @return 
	 */
	
	@GetMapping(UrlConst.SKILL_SHEET_LOOK)
	public String view(Model model,ComReadingForm form,@AuthenticationPrincipal UserDetails user) {
		session.removeAttribute(SessionKeyConst.SELECETED_LOGIN_ID);
		var userAuthority = user.getAuthorities().stream()
				.allMatch(authority -> authority.getAuthority()
						.equals(AuthorityKind.ITEM_AND_USER_MANAGER.getCode()));
		
		model.addAttribute(KEY_SKILL_SHEET_LOOK,editSkillSheetInfo());
		model.addAttribute("userAuthority",userAuthority);
		
		return ViewNameConst.SKILL_SHEET_LOOK;
	}
	
	
	
	
	/**
	 * a
	 * 
	 * @param model モデル
	 * @return 社内連絡一覧情報
	 */
	private List<SkillSheetListInfo> editSkillSheetInfo() {

		return service.editSkillSheetList();
	}
}
