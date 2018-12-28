package com.socog.website.config;

import com.socog.website.service.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.filter.HttpPutFormContentFilter;

/**
 * @author xinghao
 * @descreption springsecurity≈‰÷√¿‡
 * @date 2018/12/18
 */

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final MyUserDetailService userDetailService;

    private final
    LoginSuccessHandler loginSuccessHandler;

    @Autowired
    public WebSecurityConfig(MyUserDetailService userDetailService, LoginSuccessHandler loginSuccessHandler) {
        this.userDetailService = userDetailService;
        this.loginSuccessHandler = loginSuccessHandler;
    }

    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/resource","/resource/*").authenticated()
                .anyRequest().permitAll()
                .and()

                .formLogin()
                .loginPage("/user/login")
                .loginProcessingUrl("/user/login")
                /*.successHandler(loginSuccessHandler)*/
                .defaultSuccessUrl("/index")
                .failureUrl("/user/login?error")
                .and()

                .csrf()
                .disable();
    }
}
