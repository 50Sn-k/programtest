package com.example.sampleweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sampleweb.constant.db.CaseStatusKind;
import com.example.sampleweb.entity.Case;

public interface CaseRepository extends JpaRepository<Case,String>{
	List<Case> findByCaseIdLike(String caseId);
//	List<Case> findByCaseIdLikeAndCaseStatusKind(String caseId, CaseStatusKind caseStatusKind);
	List<Case> findByCaseIdLikeAndCaseStatus(String caseIdParam, CaseStatusKind caseStatusq);
}