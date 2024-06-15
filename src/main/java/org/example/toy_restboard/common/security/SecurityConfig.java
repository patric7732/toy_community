package org.example.toy_restboard.common.security;

import lombok.RequiredArgsConstructor;
import org.example.toy_restboard.common.security.handler.FormAuthenticationFailureHandler;
import org.example.toy_restboard.common.security.handler.FormAuthenticationSuccessHandler;
import org.example.toy_restboard.common.security.service.FormUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@EnableMethodSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final FormUserDetailsService userDetailsService;
    private final FormAuthenticationFailureHandler failureHandler;
    private final FormAuthenticationSuccessHandler successHandler;

    @Bean
    public static RoleHierarchy roleHierarchy(){
        return RoleHierarchyImpl.fromHierarchy("""
                ROLE_ADMIN > ROLE_USER
                """);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth.
                        requestMatchers("/css/**", "/js/**", "/images/**", "/fonts/**").permitAll()
                        .requestMatchers("/", "/signup", "/logout").permitAll()
                        .anyRequest().permitAll())

                .formLogin(form -> form.
                        loginPage("/login")
                        .usernameParameter("loginId")
                        .successHandler(successHandler)
                        .failureHandler(failureHandler))
                .userDetailsService(userDetailsService);

        return http.build();

    }
}
