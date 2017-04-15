package com.mlaz.api.config;

import com.mlaz.api.Repositories.MlazUserProfileRepository;
import com.mlaz.api.services.RepositoryUserDetailsService;
import com.mlaz.api.services.SimpleSocialUsersDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.social.security.SpringSocialConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private MlazUserProfileRepository mlazUserProfileRepository;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/static/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
            .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login/authenticate")
                .failureUrl("/login?param.error=bad_credentials")
                .permitAll()
        .and()
            .logout()
                .logoutUrl("/logout")
                .deleteCookies("JSESSIONID")
        .and()
            .authorizeRequests()
                .antMatchers(
                        "/auth/**",
                        "/login",
                        "/signup/**",
                        "/favicon.ico",
                        "/static-resources/**").permitAll()
                .antMatchers("/**").authenticated()
        .and()
            .rememberMe()
        .and()
            .apply(new SpringSocialConfigurer()
                .postLoginUrl("/")
                .alwaysUsePostLoginUrl(true));
    }

    @Bean
    public SocialUserDetailsService socialUsersDetailService() {
        return new SimpleSocialUsersDetailService(userDetailsService());
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new RepositoryUserDetailsService(mlazUserProfileRepository);
    }
}