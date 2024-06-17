package org.example.toy_restboard.common.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.toy_restboard.common.security.handler.FormAuthenticationFailureHandler;
import org.example.toy_restboard.common.security.handler.FormAuthenticationSuccessHandler;
import org.example.toy_restboard.common.security.service.FormUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
@EnableMethodSecurity
@Configuration
@RequiredArgsConstructor
@Slf4j
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
    @Order(1)
    public SecurityFilterChain ApiSecurityFilterChain(HttpSecurity http) throws Exception {
        http.securityMatchers(security -> security.requestMatchers("/api/**"))
                .authorizeHttpRequests(auth -> auth.
                        requestMatchers("/css/**", "/js/**", "/images/**", "/fonts/**").permitAll()
                        .requestMatchers("/", "/signup", "/logout").permitAll()
                        .requestMatchers("/api/admin/**").hasRole("ADMIN")
                        .requestMatchers("/api/user/**").hasRole("USER")
                        .anyRequest().permitAll())

                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .headers(header -> header.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))

                .cors(cors -> cors.configurationSource(configurationSource()));



        return http.build();

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
                .userDetailsService(userDetailsService)
                .csrf(AbstractHttpConfigurer::disable);

        return http.build();

    }

    public CorsConfigurationSource configurationSource(){
        log.debug("디버그: CorsConfigurationSource cors 설정이 SecurityFilterChain에 등록됨");
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedHeader("*"); // 모든 헤더 허용
        configuration.addAllowedMethod("*"); // Get, Post, Put, Delete 허용
        configuration.addAllowedOriginPattern("*"); // 모든 IP 주소 허용
        configuration.setAllowCredentials(true); // 쿠키 요청 허용
        configuration.addExposedHeader("Authorization");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // 모든 주소요청에 위 설정을 집어넣겠다
        return source;
    }
}
