package com.vmp.ms.auth.authserver.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vmp.ms.auth.authserver.model.User;
import com.vmp.ms.auth.authserver.service.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/public", method = RequestMethod.GET)
	public String publicMethod() {
		return "public Method";
	}
	
	@RequestMapping(value="/private", method = RequestMethod.GET)
	public String privateMethod() {
		return "Private Method";
	}
	
	@RequestMapping(value = "/current", method = RequestMethod.GET)
	public Principal getUser(Principal principal) {
		return principal;
	}
	
	@PreAuthorize("#oauth2.hasScope('server')")
	@RequestMapping(method = RequestMethod.POST)
	public void createUser(@Valid @RequestBody User user) {
		userService.create(user);
	}

}
