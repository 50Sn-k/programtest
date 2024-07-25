package com.example.sampleweb.entity.converter;

import com.example.sampleweb.constant.db.CaseStatusKind;

import jakarta.persistence.AttributeConverter;

public class CaseStatusConverter implements AttributeConverter<CaseStatusKind, String>{

	/**
	 * 	引数で受け取った権限種別Enumを、権限種別のコード値に変換します
	 * 
	 * @param 権限種別Enum
	 * @return 引数で受け取った権限種別Enumに保管されているコード値
	 */
	@Override
	public String convertToDatabaseColumn(CaseStatusKind caseStatusKind){
		return caseStatusKind.getCode();//7:31
	}
	
	/**
	 * 引数で受け取った権限種別のコード値を、権限種別Enumに変換します
	 * 
	 * @param 権限種別のコード値
	 * @return 引数で受け取った権限種別のコード値から逆引きした権限種別Enum
	 */
	@Override
	public CaseStatusKind convertToEntityAttribute(String value){
		return CaseStatusKind.from(value);
	}
}
