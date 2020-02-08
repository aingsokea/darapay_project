package com.darapay.loanreferral.security.config;

import com.darapay.loanreferral.security.AccountAuthenticationProvider;
import com.darapay.loanreferral.security.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.header.writers.StaticHeadersWriter;


@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final CustomUserDetailsService userDetailsService;

    private final AccountAuthenticationProvider accountAuthenticationProvider;

    public WebSecurityConfiguration(final CustomUserDetailsService userDetailsService, final AccountAuthenticationProvider accountAuthenticationProvider) {
        this.userDetailsService = userDetailsService;
        this.accountAuthenticationProvider = accountAuthenticationProvider;
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
        auth.authenticationProvider(accountAuthenticationProvider);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .anonymous().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll();
//                .antMatchers("/oauth/token").permitAll();

        http.headers()
                .httpStrictTransportSecurity()
                .and()
                .xssProtection()
                .xssProtectionEnabled(true)
                .and()
                .addHeaderWriter(new StaticHeadersWriter("HttpOnly", "true"));

        http.sessionManagement()
                .sessionFixation()
                .migrateSession()
                .maximumSessions(1);

//        http.cors();
    }
}