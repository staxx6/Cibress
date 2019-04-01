package com.datpixelstudio.cibress.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

@Configuration
@EnableWebSecurity // calls security config on every request
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

//    @Autowired
//    private CibressBasicAuthenticationEntryPoint authenticationEntryPoint;

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
                .antMatchers("/**").permitAll()
                .antMatchers("/home").permitAll()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/img/**").permitAll()
                .antMatchers("/noSecurity").permitAll() // ant build system => ant-style path patterns
                .antMatchers("/registration").permitAll()
                .antMatchers(HttpMethod.POST, "/registration").permitAll()
//                    .antMatchers("/admin").hasRole("ADMIN")
                .and().authorizeRequests()
                    .anyRequest().authenticated() // all request must be logged in
                .and().formLogin().loginPage("/login").permitAll()
                .and().logout().permitAll()
                .and().httpBasic().and().csrf().disable(); // TODO SECURITY WARNING! ONLY FOR DEVELOPING

        // TODO SECURITY WARNING! ONLY FOR DEVELOPING
        http.headers().frameOptions().disable();

        // For Postman auth (Basic Auth)
//        http.httpBasic().authenticationEntryPoint(authenticationEntryPoint);
    }

    // TODO SECURITY WARNING! password encryption
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//        return bCryptPasswordEncoder;
//    }
}