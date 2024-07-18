package com.example.sampleweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.sampleweb.constant.UrlConst;
import com.example.sampleweb.constant.ViewNameConst;

/**
 * ユーザー登録情報確認結果画面Controllerクラス
 * 
 * @author k-suzuki
 *
 */
@Controller
public class SignupCompletionController {

	/**
	 * 画面の初期表示を行います。
	 * 
	 * @return ユーザー登録情報確認結果画面テンプレート名
	 */
	@GetMapping(UrlConst.SIGNUP_COMPLETION)
	public String view(Model model) {

		return ViewNameConst.SIGNUP_COMPLETION;
	}

}