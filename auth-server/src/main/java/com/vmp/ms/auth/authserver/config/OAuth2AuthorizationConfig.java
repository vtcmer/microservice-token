package com.vmp.ms.auth.authserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;


@Configuration
@EnableAuthorizationServer
public class OAuth2AuthorizationConfig extends AuthorizationServerConfigurerAdapter {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private TokenStore tokenStore;
	
	@Autowired
	@Qualifier("authenticationManagerBean")
	private AuthenticationManager authenticationManager;
	
	@Override
	public void configure(ClientDetailsServiceConfigurer configurer) throws Exception {
		configurer.inMemory()
		        .withClient("browser")
				.authorizedGrantTypes("refresh_token", "password")
				.scopes("ui")
		.and()
				.withClient("account-service")
				.secret("123456780")
				//.secret(env.getProperty("ACCOUNT_SERVICE_PASSWORD"))
				.authorizedGrantTypes("client_credentials", "refresh_token")
				.scopes("server")
		.and()
				.withClient("statistics-service")
				.secret("123456781")
				//.secret(env.getProperty("STATISTICS_SERVICE_PASSWORD"))
				.authorizedGrantTypes("client_credentials", "refresh_token")
				.scopes("server")
		.and()
				.withClient("notification-service")
				.secret("123456782")
				//.secret(env.getProperty("NOTIFICATION_SERVICE_PASSWORD"))
				.authorizedGrantTypes("client_credentials", "refresh_token")
				.scopes("server");
	}
	
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints
				.tokenStore(tokenStore)
				.authenticationManager(authenticationManager)
				.userDetailsService(userDetailsService);
	}	
	
	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
		oauthServer
				.tokenKeyAccess("permitAll()")
				.checkTokenAccess("isAuthenticated()");
	}


}
