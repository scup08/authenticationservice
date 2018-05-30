package com.xhld.authenticationservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xhld.authenticationservice.service.ITestService;

@RestController
@RequestMapping(value = "/authTest")
public class TransationTestController {

	@Autowired
	private ITestService testService;
	
	@RequestMapping(value = "/testInfo", method = RequestMethod.POST)
	public void addTestInfo( @RequestParam(value = "aa")String aa){
		testService.addTestInfo(aa);
	}
}
