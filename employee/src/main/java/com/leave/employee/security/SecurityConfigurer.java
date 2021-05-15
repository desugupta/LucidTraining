
package com.leave.employee.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.leave.employee.impl.MyUserDetailsService;

@EnableWebSecurity
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {

	@Autowired
	private MyUserDetailsService myUserDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(myUserDetailsService);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().headers().frameOptions().disable().and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
				.antMatchers("/api/mobile-update/version-verify/**").permitAll()
			
				.antMatchers("/management/logs").permitAll()
				.antMatchers("/management/**").hasAuthority(AuthoritiesConstants.ADMIN)*/
				.antMatchers("/swagger-resources/configuration/ui").permitAll().and()
				.apply(securityConfigurerAdapter());  

	}
	
	 @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http
	            .csrf()
	            .disable()
	            .headers()
	            .frameOptions()
	            .disable()
	        .and()
	            .sessionManagement()
	            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	        .and()
	            .authorizeRequests()
	            .antMatchers(URIConstants.BASE_URI + URIConstants.CORP_GET_COMPLETED_DIAG_APPOINTMENTS).permitAll()
	            .antMatchers("/api/**").authenticated()
	            .antMatchers("/management/**").permitAll()
	            .antMatchers("/v2/api-docs").permitAll()
	            /*.antMatchers("/management/health").permitAll()
	            .antMatchers("/management/**").hasAuthority(AuthoritiesConstants.ADMIN)*/
	            .antMatchers("/swagger-resources/configuration/ui").permitAll()
	        .and()
	            .apply(securityConfigurerAdapter());
	    }
	 
	    private JWTConfigurer securityConfigurerAdapter() {
	        return new JWTConfigurer(tokenProvider);
	    }

	
}
