package com.example.sampleweb.entity;

import java.time.LocalDateTime;

import com.example.sampleweb.constant.db.AuthorityKind;
import com.example.sampleweb.constant.db.UserStatusKind;
import com.example.sampleweb.entity.converter.UserAuthorityConverter;
import com.example.sampleweb.entity.converter.UserStatusConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * ユーザー情報テーブル Entity
 */

@Entity
@Table(name = "user_info")
@Data
@AllArgsConstructor
public class UserInfo {

	/*ログインID*/
	@Id
	@Column(name ="login_id")
	private String loginId;
	
	/*パスワード*/
	private String password;
	
	/*メールアドレス*/
	@Column(name ="mail_address")
	private String mailAddress;
	
	/*ワンタイムコード*/
	@Column(name ="one_time_code")
	private String oneTimeCode;
	
	/*ワンタイムコード有効期限*/
	@Column(name ="one_time_code_send_time")
	private LocalDateTime oneTimeCodeSendTime;
	
	/** 契約日時 */
	@Column(name="contract_time")
	private LocalDateTime contract_time;
	
	/*ログイン失敗回数*/
	@Column(name ="login_failure_count")
	private int loginFailureCount = 0;
	
	/*アカウントロック日時*/
	@Column(name ="account_locked_time")
	private LocalDateTime accountLockedTime;
	
	/*利用可能か(true:利用可能)*/
	@Column(name ="is_disabled")
	@Convert(converter = UserStatusConverter.class)
	private UserStatusKind userStatusKind;
	
	/*ユーザー権限 */
	@Column(name ="authority")
	@Convert(converter = UserAuthorityConverter.class)
	private AuthorityKind authorityKind;
	
	/*本登録完了有無(仮登録状態ならfalse)*/
	@Column(name = "is_signup_completed")
	private boolean signupCompleted;
	
	/*登録日時 */
	@Column(name ="create_time")
	private LocalDateTime createTime;
	
	/*最終更新日時 */
	@Column(name ="update_time")
	private LocalDateTime updateTime;
	
	/*最終更新ユーザー */
	@Column(name ="update_user")
	private String updateUser;
	
	/** 会社名 */
	@Column(name="company_name")
	private String companyName;
	
	/** 会社住所 */
	@Column(name="company_address")
	private String companyAddress;
	
	/** 電話番号 */
	@Column(name="telephone_number")
	private String telephoneNumber;
	
	/** 案件ID */
	@Column(name="case_id")
	private String caseId;
	
	public UserInfo(){
	}
	
	/**
	 *ログイン失敗回数をインクリメントする 
	 *
	 *@return ログイン失敗回数がインクリメントされたUserInfo
	 */
	public UserInfo incrementLoginFailureCount() {
		return new UserInfo(loginId,password,mailAddress, oneTimeCode, oneTimeCodeSendTime,
				contract_time, ++loginFailureCount,accountLockedTime,
				userStatusKind,authorityKind,signupCompleted, createTime,updateTime,updateUser, companyAddress, companyAddress, companyAddress,caseId);
		
	}
	
	
	/**
	 *ログイン失敗情報をリセットする 
	 *
	 *@return ログイン失敗情報がリセットされたUserInfo
	 */
	public UserInfo resetLoginFailureInfo() {
		return new UserInfo(loginId,password,mailAddress, oneTimeCode, oneTimeCodeSendTime,
				contract_time, 0,null,
				userStatusKind,authorityKind,signupCompleted, createTime,updateTime,updateUser, companyAddress, companyAddress, companyAddress,caseId);
		
	}
	
	/**
	 *ログイン失敗情報をリセットする 
	 *
	 *@return ログイン失敗情報がリセットされたUserInfo
	 */
	public UserInfo updateAccountLocked() {
		return new UserInfo(loginId,password,mailAddress, oneTimeCode, oneTimeCodeSendTime,
				contract_time, 0,LocalDateTime.now(),
				userStatusKind,authorityKind,signupCompleted, createTime,updateTime,updateUser, companyAddress, companyAddress, companyAddress,caseId);
	}
	
	/**
	 * 契約日時を更新する
	 * 
	 * @return 契約日時が更新されたUserInfo
	 */
	public UserInfo completeContract() {
		return new UserInfo(loginId,password,mailAddress,oneTimeCode,oneTimeCodeSendTime,
				LocalDateTime.now(),loginFailureCount,
				accountLockedTime,userStatusKind,
				authorityKind,signupCompleted,createTime,updateTime,updateUser
				,companyName,companyAddress,telephoneNumber,caseId);
	}
}
