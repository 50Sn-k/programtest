package com.example.sampleweb.form;

import lombok.Data;

@Data
public class ContractForm {	
		
		/** ログインID */
		private String loginId;
		
		/** パスワード */
		private String password;
		
		/** 契約時刻  */
		private String contract_time;
		
		/** 契約是非  */
		private String privacy;

}
