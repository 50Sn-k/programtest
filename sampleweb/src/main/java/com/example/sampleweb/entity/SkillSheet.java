package com.example.sampleweb.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 社内連絡情報テーブル Entity
 */

@Entity
@Table(name = "skill_sheet")
@Data
@AllArgsConstructor
public class SkillSheet {

	/*ログインID*/
	@Id
	@Column(name ="login_id")
	private String loginId;
	
	/*資格*/
	private String qualification;
	
	/*実績*/
	private String achievements;
	
	public SkillSheet(){
	}
}
