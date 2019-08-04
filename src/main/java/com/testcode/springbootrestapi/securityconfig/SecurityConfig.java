package com.testcode.springbootrestapi.securityconfig;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	@Autowired
	private DataSource dataSource;
	
	
	
	/*@Bean
	@ConfigurationProperties("spring.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}*/
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.jdbcAuthentication().dataSource(dataSource)
		.authoritiesByUsernameQuery("select u.name, r.role from users u inner join user_role ur on u.userid=ur.userid inner join role r on ur.roleid = r.roleid  where u.name=?")
		.usersByUsernameQuery("select u.name, u.password, 1 as enabled from users u where u.name=?");
	}
	/*{
		auth.inMemoryAuthentication()
		.withUser(user.getName())
		.password(user.getPassword())
		.roles("ADMIN");
	}*/
	
	 @Override
	  protected void configure(HttpSecurity http) throws Exception {
		 http
	    .csrf().disable()
	    .authorizeRequests().anyRequest().authenticated()
	    .and()
	    .httpBasic();
		// http.csrf().disable();
	  }

	@Bean
	public PasswordEncoder passWordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
