package com.darapay.loanreferral.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
public class OAuth2ServerConfiguration {

    @Configuration
    @EnableResourceServer
    protected static class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

//        @Autowired
//        private JwtAccessTokenConverter jwtAccessTokenConverter;
//
//        @Override
//        public void configure(final ResourceServerSecurityConfigurer resources) {
//            resources
//                    .tokenStore(new JwtTokenStore(jwtAccessTokenConverter));
//        }

        @Override
        public void configure(final HttpSecurity http) throws Exception {
            http
                .csrf().disable()
                .authorizeRequests()
                .anyRequest()
                .authenticated();
        }
    }
}
