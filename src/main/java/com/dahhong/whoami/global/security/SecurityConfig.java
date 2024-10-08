package com.dahhong.whoami.global.security;

import com.dahhong.whoami.global.security.authenticationProvider.KakaoAuthenticationProvider;
import com.dahhong.whoami.global.security.filter.KakaoJwtAuthenticationFilter;
import com.dahhong.whoami.user.application.port.out.UserQueryPort;
import com.dahhong.whoami.user.domain.entity.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserQueryPort userQueryPort;

    private final KakaoAuthenticationProvider kakaoAuthenticationProvider;

    private final RestTemplate restTemplate;

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring()
                .requestMatchers("/error", "/favicon.ico");
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers("/swagger", "/swagger-ui.html", "/swagger-ui/**", "/api-docs", "/api-docs/**", "/v3/api-docs/**").permitAll()
                        .requestMatchers("/", "/error", "/favicon.ico").permitAll()
                        .requestMatchers("/api/v1/auth/kakao/login").permitAll()
                        .requestMatchers("/api/v1/auth/kakao/callback").permitAll()
                        .requestMatchers("/api/v1/auth/kakao/refresh").permitAll()
                        .requestMatchers("/api/v1/auth/kakao/**").authenticated()
                        /* page 엔드포인트에 대한 권한 처리 */
                        .requestMatchers("/api/v1/page/create").authenticated()
                        .requestMatchers(HttpMethod.POST, "/api/v1/page/{id}").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/api/v1/page/{id}").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/page/{id}").authenticated()
                        /* User 엔드포인트에 대한 권한 처리 */
                        .requestMatchers("/api/v1/user/**").authenticated()
                        .requestMatchers("/api/v1/user/info/all").hasRole(String.valueOf(Role.ADMIN))
                        .anyRequest().permitAll()
                )
                .addFilterBefore(new KakaoJwtAuthenticationFilter(userQueryPort, kakaoAuthenticationProvider, restTemplate), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

}
