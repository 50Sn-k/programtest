package com.example.sampleweb.service.common;

/**
 * メール送信Serviceクラス
 * 
 * @author k-suzuki
 *
 */
public interface MailSendService {

	/**
	 * メールを送信します。
	 * 
	 * @param mailTo 宛先
	 * @param mailSubject 件名
	 * @param mailText 本文
	 * @return 送信結果(成功ならtrue)
	 */
	public boolean sendMail(String mailTo, String mailSubject, String mailText);
}