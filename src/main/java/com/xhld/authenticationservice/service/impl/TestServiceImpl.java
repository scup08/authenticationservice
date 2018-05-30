package com.xhld.authenticationservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xhld.authenticationservice.model.entity.TTest;
import com.xhld.authenticationservice.persistence.TTestMapper;
import com.xhld.authenticationservice.service.ITestService;

@Service
public class TestServiceImpl implements ITestService {

	
	@Autowired
	private TTestMapper tTestMapper;
	
	@Override
	public void addTestInfo(String aa) {
		
		TTest test = new TTest();
		test.setMobile(aa);
		tTestMapper.insert(test);
		
	}

}
