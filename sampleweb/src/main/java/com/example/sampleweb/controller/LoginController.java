package com.example.sampleweb.controller;

import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.sampleweb.constant.ModelKey;
import com.example.sampleweb.constant.UrlConst;
import com.example.sampleweb.constant.ViewNameConst;
import com.example.sampleweb.form.LoginForm;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

/**
 * ログイン画面Controllerクラス
 * 
 * @author k-suzuki
 *
 */
@Controller
@RequiredArgsConstructor
public class LoginController {

	/** セッション情報 */
	private final HttpSession session;

	/**
	 * 画面の初期表示を行います
	 * 
	 * @param model モデル
	 * @param form 入力情報
	 * @return ログイン画面テンプレート名
	 */
	@GetMapping(UrlConst.LOGIN)
	public String view(Model model, LoginForm form) {
		return ViewNameConst.LOGIN;
	}

	/**
	 * ログインエラー時にセッションからエラーメッセージを取得して画面の表示を行います
	 * 
	 * @param model モデル
	 * @param form 入力情報
	 * @return ログイン画面テンプレート名
	 */
	@GetMapping(value = UrlConst.LOGIN, params = "error")
	public String viewWithError(Model model, LoginForm form) {
		var errorInfo = (Exception) session.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
		model.addAttribute(ModelKey.MESSAGE, errorInfo.getMessage());
		model.addAttribute(ModelKey.IS_ERROR, true);
		return ViewNameConst.LOGIN;
	}

}
