package com.example.sampleweb.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.sampleweb.constant.UrlConst;

import lombok.RequiredArgsConstructor;

/**
 * 
 * @author k-suzuki
 * 
 */

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig {

	/*パスワードエンコーダー*/
	private final PasswordEncoder passwordEncoder;
	
	/*ユーザー情報取得Service*/
	private final UserDetailsService userDetailsService;
	
	/*メッセージ取得*/
	private final MessageSource messageSource;
	
	 /*ユーザー名のname属性*/
	private final String USERNAME_PARAMETER = "loginId";
	
	/**
	 * 
	 * @param http カスタマイズパラメータ
	 * @return カスタマイズ結果
	 * @throws Exception 予期せぬ例外
	 */
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		
		http
			.authorizeHttpRequests(
					authorize -> authorize.requestMatchers(UrlConst.NO_AUTHENTICATION).permitAll()
						.anyRequest().authenticated())
			.formLogin(
					login -> login.loginPage(UrlConst.LOGIN)//自作ログイン画面(Controller)を使うための設定
					.usernameParameter(USERNAME_PARAMETER)//ユーザー名パラメータのname属性
					.defaultSuccessUrl(UrlConst.MENU))//ログイン画成功後のリダイレクトURL
		.logout(logout -> logout.logoutSuccessUrl(UrlConst.LOGIN));
		return http.build();
	}
	
	/**
	 * Provider定義
	 * 
	 * @return カスタマイズProvider情報
	 */
	
	@Bean
	AuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(passwordEncoder);
		provider.setMessageSource(messageSource);
		
		return provider;
		
	}

}
