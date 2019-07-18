package com.sarki.micro.sec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired 
	private UserDetailsService userDetailsService;
	@Autowired 
	//private BCryptPasswordEncoder bCryptPasswordEncoder; 
	
	@Override 
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	//	auth.userDetailsService(userDetailsService) .passwordEncoder(bCryptPasswordEncoder);
	
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf()
			.disable() // don't create session
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.authorizeRequests() 
			.antMatchers("/users/**",
						 "/login/**",
						 "/apimicro/employes",
						 "/apimicro/employe",
						 "/apiagence/agences",
						 "/apiagence/agence/*",
						 "/apiagence/agence",
						 "/apimicro/agence",
						 "/apiclient/clients",
						 "/apiclient/client",
						 "/apiclient/client/**",
						 "/apicompte/comptecourant",
						 "/apicompte/comptecourant/*",
						 "/apicompte/compteEpargne/*",
						 "/apicompte/compte/*",
						 "/apicompte/compte/retrait/*",
						 "/apicompte/comptes",
						 "/apicompte/compteEpargne/*",
						 "/apicompte/compte/*",
						 "/apicompte/compte/retrait/*",
						 "apiconpte/compte/emprunt/*",
						 "apiconpte/compte/remboursement/*",
						 "/apicompteEpargne/**",
						 "/apiCompteBloque/**",
						 "/apiCompteCollecte/**",
						 "/apicompte/comptes") 
			
			
			.permitAll() 
			.antMatchers(HttpMethod.POST,"/tasks/**")
			.hasAuthority("ADMIN") 
			.anyRequest()
			.authenticated() 
			.and()
			.addFilter(new JWTAuthenticationFilter(authenticationManager()))
			.addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
		}
	
}
