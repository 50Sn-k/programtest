package com.example.sampleweb.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.sampleweb.constant.SessionKeyConst;
import com.example.sampleweb.constant.UrlConst;
import com.example.sampleweb.constant.ViewNameConst;
import com.example.sampleweb.constant.db.AuthorityKind;
import com.example.sampleweb.dto.UserComListInfo;
import com.example.sampleweb.form.ComReadingForm;
import com.example.sampleweb.service.CommunicationReadingService;
import com.example.sampleweb.util.AppUtil;

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
public class CommunicationReadingController {
	
	/*社内連絡情報一覧画面Serviceクラス*/
	private final CommunicationReadingService service;

	/** セッションオブジェクト */
	private final HttpSession session;
	
	/*モデルキー:社内連絡情報リスト */
	private static final String KEY_COM_READING = "comReading";

	/**
	 * 画面の初期表示を行います
	 * 
	 * @param model モデル
	 * @param form 入力情報
	 * @param user 認証済みユーザー情報
	 * @return 社内連絡表示画面テンプレート名
	 */
	
	@GetMapping(UrlConst.COM_READING)
	public String view(Model model,ComReadingForm form,@AuthenticationPrincipal UserDetails user) {
		session.removeAttribute(SessionKeyConst.SELECETED_LOGIN_ID);
		var userAuthority = user.getAuthorities().stream()
				.allMatch(authority -> authority.getAuthority()
						.equals(AuthorityKind.ITEM_AND_USER_MANAGER.getCode()));
		
		model.addAttribute(KEY_COM_READING,editUserListInfo());
		model.addAttribute("userAuthority",userAuthority);
		
		return ViewNameConst.COM_READING;
	}
	
	/**
	 * 社内連絡を既読状態に更新します。
	 * 
	 * @param form 入力情報
	 * @param user 認証済みユーザー情報
	 * @param redirectAttributes リダイレクト用オブジェクト
	 * @return リダイレクトURL
	 */
	@PostMapping(value = UrlConst.COM_READING, params = "nowatch")
	public String noticeWatch(ComReadingForm form,@AuthenticationPrincipal UserDetails user,RedirectAttributes redirectAttributes){
		session.setAttribute(SessionKeyConst.SELECETED_LOGIN_ID,form.getSelectedLoginId());
		form.setLoginId(form.getSelectedLoginId());
		var selectId = (String) session.getAttribute(SessionKeyConst.SELECETED_LOGIN_ID);
		service.watchInfo(selectId);

		return AppUtil.doRedirect(UrlConst.COM_READING);
	}
	
	/**
	 * 社内連絡情報をテーブルから取得します
	 * 
	 * @param model モデル
	 * @return 社内連絡一覧情報
	 */
	private List<UserComListInfo> editUserListInfo() {

		return service.editUserComList();
	}
}
