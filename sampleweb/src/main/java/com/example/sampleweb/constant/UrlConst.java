package com.example.sampleweb.constant;

/**
 * 
 * @author k-suzuki
 * 
 */
public class UrlConst {

	/*ログイン画面*/
	public static final String LOGIN = "/login";
	
	/*ユーザー登録画面*/
	public static final String SIGNUP = "/signup";
	
	/*ユーザー登録情報確認画面*/
	public static final String SIGNUP_CONFIRM = "/signupConfirm";
	
	/*ユーザー登録情報確認画面*/
	public static final String SIGNUP_COMPLETION = "/signupCompletion";
	
	/*メニュー画面*/
	public static final String MENU = "/menu";
	
	/*ユーザー一覧画面*/
	public static final String USER_LIST = "/userList";
	
	/*ユーザー編集画面*/
	public static final String USER_EDIT = "/userEdit";
	
	/*ユーザーコントラクト*/
	public static final String CONTRACT = "/contract";
	
	/*コミュニケーション*/
	public static final String COM_READING = "/comReading";
	
	/** 社内連絡事項入力画面 */
	public static final String COM_INPUT = "/communicationInput";
	
	/** 案件確認画面 */
	public static final String CASE_LOOK = "/caseStatusLook";
	
	/** 新規案件入力画面 */
	public static final String CASE_INPUT = "/caseInput";
	
	/*案件情報一覧*/
	public static final String CASE_STATUS_LIST = "/caseStatusList";
	
	/*案件情報編集*/
	public static final String CASE_STATUS_EDIT = "/caseStatusEdit";
	
	/**スキルシート一覧*/
	public static final String SKILL_SHEET = "/skillSheet";
	
	/*スキルシート一覧*/
	public static final String SKILL_SHEET_LOOK = "/skillSheetLook";
	
	/*認証不要画面*/
	public static final String[] NO_AUTHENTICATION = {LOGIN,SIGNUP,SIGNUP_CONFIRM, SIGNUP_COMPLETION, "/webjars/**","/css/**"};

	



}
