package com.noternal.app.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ApplicationConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests().anyRequest().permitAll();
                http.csrf().disable();
                http.authorizeRequests().antMatchers("/register**", "/login", "resources/static/**").permitAll()
                .and().authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin() .loginPage("/login")
                .and()
                .logout() .invalidateHttpSession(true)
                .clearAuthentication(true);
    }

//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web
//                .ignoring()
//                .antMatchers("/resources/static/**");
////                .antMatchers("/publics/**");
//    }
}
