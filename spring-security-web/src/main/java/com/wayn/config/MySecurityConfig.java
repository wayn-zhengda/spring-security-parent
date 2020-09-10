package com.wayn.config;

import com.wayn.core.properties.WaynSecurityProperties;
import com.wayn.core.validate.ValidateCodeSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private WaynSecurityProperties securityProperties;
    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;

    @Bean
    public PasswordEncoder passwordEncodeBean(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.apply(validateCodeSecurityConfig)
                .and()
            .formLogin()
            .loginPage("/require/auth")
            .loginProcessingUrl("/form/login")
            .and()
            .authorizeRequests()
            .antMatchers("/html/*", "/require/auth", securityProperties.getBrowser().getSignPage(), "/code/image")
            .permitAll()
            .anyRequest()
            .authenticated().and().csrf().disable();
    }
}
