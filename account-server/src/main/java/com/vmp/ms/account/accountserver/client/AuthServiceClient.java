package com.vmp.ms.account.accountserver.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "auth-service")
public interface AuthServiceClient {
	
	@RequestMapping(method = RequestMethod.GET, value = "/auth/api/v1/users/private", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	String 	privateMethod();
}
