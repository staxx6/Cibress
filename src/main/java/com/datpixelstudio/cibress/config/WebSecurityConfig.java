package com.datpixelstudio.cibress.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity // calls security config on every request
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired // to get AuthenticationManagerBuilder - Method name is unimportant
    public void globalSecurityConfiguration(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("user").password("{noop}password").roles("USER");
//        auth.inMemoryAuthentication().withUser("admin").password("{noop}password").roles("USER", "ADMIN");
//        auth.inMemoryAuthentication().withUser("root").password("{noop}root").roles("USER", "ADMIN");
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                    .antMatchers("/noSecurity").permitAll() // ant build system => ant-style path patterns
//                    .antMatchers("/admin").hasRole("ADMIN")
                .and().authorizeRequests()
                    .anyRequest().authenticated() // all request must be logged in
                .and().formLogin().loginPage("/login").permitAll()
                .and().logout().permitAll();
    }
}