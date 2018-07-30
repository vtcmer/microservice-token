package com.vmp.ms.account.accountserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vmp.ms.account.accountserver.client.AuthServiceClient;

@RestController
@RequestMapping("/api/v1/account")
public class AccountController {
	
	@Autowired
	private AuthServiceClient authClient;
	
	@RequestMapping(value="/test", method = RequestMethod.GET)
	public String test() {
		return authClient.privateMethod();
	}

}
