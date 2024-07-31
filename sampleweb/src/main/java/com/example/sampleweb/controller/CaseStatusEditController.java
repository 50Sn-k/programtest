package com.example.sampleweb.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.context.MessageSource;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.sampleweb.constant.CaseStatusEditMessage;
import com.example.sampleweb.constant.MessageConst;
import com.example.sampleweb.constant.ModelKey;
import com.example.sampleweb.constant.SessionKeyConst;
import com.example.sampleweb.constant.UrlConst;
import com.example.sampleweb.constant.ViewNameConst;
import com.example.sampleweb.constant.db.CaseStatusKind;
import com.example.sampleweb.dto.CaseStatusEditInfo;
import com.example.sampleweb.dto.CaseStatusUpdateInfo;
import com.example.sampleweb.form.CaseStatusEditForm;
import com.example.sampleweb.service.CaseStatusEditService;
import com.example.sampleweb.util.AppUtil;
import com.github.dozermapper.core.Mapper;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

/**
 * ユーザー編集画面Controllerクラス
 * 
 * @author k-suzuki
 *
 */
@Controller
@RequiredArgsConstructor
public class CaseStatusEditController {

	/** ユーザー編集画面Serviceクラス */
	private final CaseStatusEditService service;

	/** セッションオブジェクト */
	private final HttpSession session;

	/** Dozer Mapper */
	private final Mapper mapper;

	/** メッセージソース */
	private final MessageSource messageSource;
	
	/** リダイレクトパラメータ：エラー有 */
	private static final String REDIRECT_PRAM_ERR = "err";

	/**
	 * 前画面で選択されたログインIDに紐づくユーザー情報を画面に表示します。
	 * 
	 * @param model モデル
	 * @param form 入力情報
	 * @return ユーザー編集画面テンプレート名
	 * @throws Exception 
	 */
	@GetMapping(UrlConst.CASE_STATUS_EDIT)
	public String view(Model model, CaseStatusEditForm form) throws Exception {
		var caseId = (String) session.getAttribute(SessionKeyConst.SELECETED_CASE_ID);
		var userInfoOpt = service.searchCaseStatusInfo(caseId);
		if (userInfoOpt.isEmpty()) {
			model.addAttribute(ModelKey.MESSAGE,
					AppUtil.getMessage(messageSource, MessageConst.CASE_STATUSEDIT_NON_EXISTED_CASE_ID));
			return ViewNameConst.CASE_STATUS_EDIT;
		}
		var caseStatusInfo = userInfoOpt.get();
		model.addAttribute("caseStatusEditForm", mapper.map(caseStatusInfo, CaseStatusEditForm.class));
		model.addAttribute("caseStatusEditInfo", mapper.map(caseStatusInfo, CaseStatusEditInfo.class));
		model.addAttribute("caseStatusKindOptions", CaseStatusKind.values());

		return ViewNameConst.CASE_STATUS_EDIT;
	}
	
	/**
	 * 画面の更新エラー時にエラーメッセージを表示します。
	 * 
	 * @param model モデル
	 * @return ユーザー編集エラー画面テンプレート名
	 */
//	@GetMapping(value = UrlConst.USER_EDIT, params = REDIRECT_PRAM_ERR)
//	public String viewWithError(Model model) {
//		return ViewNameConst.USER_EDIT_ERROR;
//	}

	/**
	 * 画面の入力情報をもとにユーザー情報を更新します。
	 * 
	 * @param form 入力情報
	 * @param user 認証済みユーザー情報
	 * @param redirectAttributes リダイレクト用オブジェクト
	 * @return リダイレクトURL
	 */
	@PostMapping(value = UrlConst.CASE_STATUS_EDIT, params = "update")
	public String updateUser(CaseStatusEditForm form, @AuthenticationPrincipal User user,RedirectAttributes redirectAttributes){
		var updateDto = mapper.map(form, CaseStatusUpdateInfo.class);
		updateDto.setCaseId((String) session.getAttribute(SessionKeyConst.SELECETED_CASE_ID));
		if(updateDto.getStartDate().isEmpty()==false) {
			LocalDateTime firstDate = LocalDateTime.parse((updateDto.getStartDate()+"T00:00:00"),DateTimeFormatter.ISO_LOCAL_DATE_TIME);
			updateDto.setCaseStartDate(firstDate);
		}
		if(updateDto.getFinishDate().isEmpty()==false) {
			LocalDateTime endDate = LocalDateTime.parse((updateDto.getFinishDate()+"T00:00:00"),DateTimeFormatter.ISO_LOCAL_DATE_TIME);
			updateDto.setCaseFinishDate(endDate);
		}
		var updateResult = service.updateCaseStatusInfo(updateDto);
		var updateMessage = updateResult.getCaseMessage();
		if (updateMessage == CaseStatusEditMessage.FAILED) {
			redirectAttributes.addFlashAttribute(ModelKey.MESSAGE, AppUtil.getMessage(messageSource, updateMessage.getMessageId()));
			redirectAttributes.addAttribute(REDIRECT_PRAM_ERR,"");
			return AppUtil.doRedirect(UrlConst.CASE_STATUS_EDIT);
		}

		redirectAttributes.addFlashAttribute(ModelKey.IS_ERROR, false);
		redirectAttributes.addFlashAttribute(ModelKey.MESSAGE, AppUtil.getMessage(messageSource, updateMessage.getMessageId()));

		return AppUtil.doRedirect(UrlConst.CASE_STATUS_EDIT);
	}

}