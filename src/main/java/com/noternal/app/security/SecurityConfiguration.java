//package com.noternal.app.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//
////    @Autowired
////    private UserDetailsService userDetailsService;
//
//    // Spring Security requires a user details object.
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//            .authorizeRequests().anyRequest().permitAll();
//        http.csrf().disable();
//        http.headers().frameOptions().disable();
////            .and()
////            .authorizeRequests().antMatchers("/h2-console**").permitAll()
//////            .and()
//////            .formLogin().loginPage("/")
//////            .and()
//////            .httpBasic().disable();
//
//    }
//
//}
