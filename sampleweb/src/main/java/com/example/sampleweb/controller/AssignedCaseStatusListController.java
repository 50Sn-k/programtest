package com.example.sampleweb.controller;

import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.sampleweb.constant.ModelKey;
import com.example.sampleweb.constant.SessionKeyConst;
import com.example.sampleweb.constant.UrlConst;
import com.example.sampleweb.constant.UserDeleteResult;
import com.example.sampleweb.constant.ViewNameConst;
import com.example.sampleweb.constant.db.AuthorityKind;
import com.example.sampleweb.constant.db.CaseStatusKind;
import com.example.sampleweb.dto.AssignedCaseStatusListInfo;
import com.example.sampleweb.dto.CaseSearchInfo;
import com.example.sampleweb.form.AssignedCaseStatusListForm;
import com.example.sampleweb.service.AssignedCaseStatusListService;
import com.example.sampleweb.util.AppUtil;
import com.github.dozermapper.core.Mapper;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AssignedCaseStatusListController {

	/*案件一覧画面Serviceクラス*/
	private final AssignedCaseStatusListService service;
	
	/*Dozer Mapper*/
	private final Mapper mapper;
	
	/*メッセージソース*/
	private final MessageSource messageSource;
	
	/*セッションオブジェクト*/
	private final HttpSession session;

	/*モデルキー：ユーザー情報リストフォーム */
	private static final String KEY_ASSIGNED_CASE_STATUS_LIST_FORM = "assignedCaseStatusListForm";
	
	/*モデルキー:ユーザー情報リスト */
	private static final String KEY_ASSIGNED_CASE_STATUS_LIST = "assignedCaseStatusList";
	
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
	@GetMapping(UrlConst.ASSIGNED_CASE_STATUS_LIST)
	public String view(Model model,AssignedCaseStatusListForm form,@AuthenticationPrincipal UserDetails user) {
		session.removeAttribute(SessionKeyConst.SELECETED_CASE_ID);
		
		var userAuthority = user.getAuthorities().stream()
				.allMatch(authority -> authority.getAuthority()
						.equals(AuthorityKind.ITEM_AND_USER_MANAGER.getCode()));
		model.addAttribute(KEY_ASSIGNED_CASE_STATUS_LIST,editCaseListInfo(model));
		
		model.addAttribute(KEY_CASE_STATUS_KIND_OPTIONS,CaseStatusKind.values());
		model.addAttribute("userAuthority",userAuthority);
		
		return ViewNameConst.ASSIGNED_CASE_STATUS_LIST;
	}
	
	/**
	 * 初期表示、検索後や削除後のリダイレクトによる再表示のいずれかかを判定して画面に表示する一覧情報を作成します。
	 * 
	 * @param model モデル
	 * @return ユーザー一覧情報
	 */
	@SuppressWarnings("unchecked")
	private List<AssignedCaseStatusListInfo> editCaseListInfo(Model model) {
		var doneSearchOrDelete = model.containsAttribute(KEY_OPERATION_KIND);
		if (doneSearchOrDelete) {
			var operationKind = (OperationKind) model.getAttribute(KEY_OPERATION_KIND);
			if (operationKind == OperationKind.SEARCH) {
				return (List<AssignedCaseStatusListInfo>) model.getAttribute(KEY_ASSIGNED_CASE_STATUS_LIST);
			}
			if (operationKind == OperationKind.DELETE) {
				var searchDto = mapper.map((AssignedCaseStatusListForm) model.getAttribute(KEY_ASSIGNED_CASE_STATUS_LIST_FORM), CaseSearchInfo.class);
				return service.editCaseListByParam(searchDto);
			}
		}

		return service.editCaseList();
	}

	
	/**
	 * 検索条件に合致するユーザー情報を画面に表示します。
	 * 
	 * @param form 入力情報
	 * @param redirectAttributes リダイレクト用オブジェクト
	 * @return リダイレクトURL
	 */
	@PostMapping(value = UrlConst.ASSIGNED_CASE_STATUS_LIST, params = "search")
	public String searchUser(AssignedCaseStatusListForm form, RedirectAttributes redirectAttributes) {
		var searchDto = mapper.map(form, CaseSearchInfo.class);
		var userInfos = service.editCaseListByParam(searchDto);
		redirectAttributes.addFlashAttribute(KEY_ASSIGNED_CASE_STATUS_LIST, userInfos);
		redirectAttributes.addFlashAttribute(KEY_ASSIGNED_CASE_STATUS_LIST_FORM, form);
		redirectAttributes.addFlashAttribute(KEY_OPERATION_KIND, OperationKind.SEARCH);

		return AppUtil.doRedirect(UrlConst.ASSIGNED_CASE_STATUS_LIST);
	}
	
	/**
	 * 選択行のユーザー情報を編集する画面に遷移します
	 * 
	 * @param form 入力情報
	 * @return リダイレクトURL
	 */
	@PostMapping(value = UrlConst.ASSIGNED_CASE_STATUS_LIST, params = "edit")
	public String updateUser(AssignedCaseStatusListForm form) {
		session.setAttribute(SessionKeyConst.SELECETED_CASE_ID,form.getSelectedcaseId());
	return AppUtil.doRedirect(UrlConst.ASSIGNED_CASE_STATUS_LIST);
	}
	/**
	 * 選択行のユーザー情報を削除して、最新情報で画面を再表示します
	 * 
	 * @param form 入力情報
	 * @param redirectAttributes リダイレクト用オブジェクト
	 * @return リダイレクトURL
	 */
	
	@PostMapping(value = UrlConst.ASSIGNED_CASE_STATUS_LIST, params = "delete")
	public String deleteUser(AssignedCaseStatusListForm form, RedirectAttributes redirectAttributes) {
		var executeResult = service.deleteCaseInfoById(form.getSelectedcaseId());
		redirectAttributes.addFlashAttribute(ModelKey.IS_ERROR,executeResult == UserDeleteResult.ERROR);
		redirectAttributes.addFlashAttribute(ModelKey.MESSAGE,AppUtil.getMessage(messageSource, executeResult.getMessageId()));
		
		//削除後、フォーム情報の「選択されたログインID」は不要になるため、クリアします
		redirectAttributes.addFlashAttribute(KEY_ASSIGNED_CASE_STATUS_LIST_FORM, form.clearSelectedCaseId());
		redirectAttributes.addFlashAttribute(KEY_OPERATION_KIND, OperationKind.DELETE);

		return AppUtil.doRedirect(UrlConst.ASSIGNED_CASE_STATUS_LIST);
	}

	/**
	 * 操作種別Enum
	 * 
	 * @author k-suzuki
	 */
	public enum OperationKind {
		SEARCH, DELETE
	}
}
