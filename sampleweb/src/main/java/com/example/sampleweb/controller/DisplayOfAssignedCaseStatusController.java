package com.example.sampleweb.controller;

import org.springframework.stereotype.Controller;

import com.example.sampleweb.service.DisplayOfAssignedCaseStatusServiceImpl;

import lombok.RequiredArgsConstructor;

/**
 *  案件情報入力画面 Controller
 *  
 *  @author k-murata
 *  
 */

@Controller
@RequiredArgsConstructor
public class DisplayOfAssignedCaseStatusController {

	/** 案件情報入力画面 */
	private final DisplayOfAssignedCaseStatusServiceImpl service;
}
