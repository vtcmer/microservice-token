package com.vmp.ms.auth.authserver.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.vmp.ms.auth.authserver.model.User;
import com.vmp.ms.auth.authserver.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService{
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public void create(User user) {
		
		
		String hash = bCryptPasswordEncoder.encode(user.getPassword());
		user.setPassword(hash);
		
		log.info("Created user "+user.getUserName()+ " : "+user.getPassword());
		
	}

}
