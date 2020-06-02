package com.example.demo.security;

import com.example.demo.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                authorizeRequests()
                    .antMatchers("/").permitAll()
                    .antMatchers("/login").permitAll()
                    .antMatchers("/sign-in").permitAll()
                    .antMatchers("/sign-up").permitAll()
                    .antMatchers("/sign-up-action").permitAll()
                    .antMatchers("/admin-panel").hasAuthority("ADMIN")
                    .antMatchers("/user-manager").hasAuthority("ADMIN")
                    .antMatchers("/lesson-manager").hasAuthority("ADMIN")
                    .antMatchers("/users-view").hasAuthority("ADMIN")
                    .antMatchers("/lessons-view").hasAuthority("ADMIN")
                    .antMatchers("/admin-panel-old").hasAuthority("ADMIN")
                    .anyRequest()
                    .authenticated()
                    .and().csrf().disable()
                .formLogin()
                    .loginPage("/login")
                    .failureUrl("/login")
                    .defaultSuccessUrl("/homepage")
                    .usernameParameter("sign-in-email-input")
                    .passwordParameter("sign-in-password-input")
                .and().logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/login").and().exceptionHandling();
    }

}