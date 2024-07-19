package com.example.sampleweb.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.sampleweb.constant.UrlConst;
import com.example.sampleweb.constant.ViewNameConst;
import com.example.sampleweb.constant.db.AuthorityKind;
import com.example.sampleweb.dto.UserComListInfo;
import com.example.sampleweb.service.CommunicationReadingService;
import com.example.sampleweb.service.MenuService;

import lombok.RequiredArgsConstructor;

/**
 * メインコントローラー
 * 
 * @author k-suzuki
 * 
 */

@Controller
@RequiredArgsConstructor
public class MenuController {
	
	/*ユーザー登録画面Serviceクラス*/
	private final CommunicationReadingService comservice;
	
	/*モデルキー:ユーザー情報リスト */
	private static final String KEY_COM_READ_MENU = "comReading";
	
	/**
	 * 画面の初期表示を行います。
	 * 
	 * <p>その際、ユーザー情報から権限を確認し、ユーザー管理が可能かどうかを判定した結果を画面に渡します。
	 * 
	 * @param user 認証済みユーザー情報
	 * @param model モデル
	 * @return メニュー画面テンプレート名
	 */
	
	/** ログイン画面 service */
	private final MenuService service;

	@GetMapping(UrlConst.MENU)
	public String view(@AuthenticationPrincipal User user ,Model model){
		var hasUserManageAuth = user.getAuthorities().stream()
				.allMatch(authority -> authority.getAuthority()
						.equals(AuthorityKind.ITEM_AND_USER_MANAGER.getCode()));
		var hasUserEditAuth = user.getAuthorities().stream()
				.allMatch(authority -> authority.getAuthority()
						.equals(AuthorityKind.ITEM_MANAGER.getCode()));
		var userInfo = service.searchUserById(user.getUsername());
		var unSigned = userInfo.get().getContract_time()==null;
		var usernames = userInfo.get().getLoginId();
		model.addAttribute("hasUserManageAuth",hasUserManageAuth);
		model.addAttribute("hasUserEditAuth",hasUserEditAuth);
		model.addAttribute("unSigned",unSigned);
		model.addAttribute("usernames",usernames);
		if(hasUserEditAuth==false) {
			model.addAttribute(KEY_COM_READ_MENU,editUserListInfo());
		}
		
		return ViewNameConst.MENU;
	}
	
	
	/**
	 * a
	 * 
	 * @param model モデル
	 * @return 社内連絡一覧情報
	 */
	private List<UserComListInfo> editUserListInfo() {

		return comservice.editUserComMenuList();
	}
	
}
