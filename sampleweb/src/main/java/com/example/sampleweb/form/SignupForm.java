package com.example.sampleweb.form;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class SignupForm {

	/*ログインID*/
	@Length(min = 6,max = 20)
	private String loginId;
	
	
	/*パスワード*/
	@Length(min = 6,max = 20)
	private String password;
	
	/*メールアドレス*/
	@Length(max = 100)
	@Pattern(regexp = "^[A-Za-z0-9+_.-]+@([A-Za-z0-9][A-Za-z0-9-]*[A-Za-z0-9]*\\.)+[A-Za-z]{2,}$", message = "{signup.invalidMailAddress}")
	private String mailAddress;
	
}
