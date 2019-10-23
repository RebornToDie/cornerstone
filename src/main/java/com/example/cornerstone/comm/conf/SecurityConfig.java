package com.example.cornerstone.comm.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author reborntodie
 * @date 2019/10/23 9:36
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/").access("hasRole('READER')")
        .antMatchers("/**").permitAll()
        /*.and()
        .formLogin().loginPage("/login").failureForwardUrl("login?error=true")*/;
    }
}
