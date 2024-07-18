package com.example.sampleweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sampleweb.entity.SkillSheet;

public interface SkillSheetRepository extends JpaRepository<SkillSheet,String>{
}