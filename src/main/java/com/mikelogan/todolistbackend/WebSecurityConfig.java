package com.mikelogan.todolistbackend;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
 
    @Override
    protected void configure(final HttpSecurity http) throws Exception {
            
        http            
        .authorizeRequests()
            .antMatchers(HttpMethod.OPTIONS).permitAll()
            .anyRequest().authenticated()
            .and()
        .oauth2ResourceServer(oauth2 -> oauth2.jwt());
    }
}
