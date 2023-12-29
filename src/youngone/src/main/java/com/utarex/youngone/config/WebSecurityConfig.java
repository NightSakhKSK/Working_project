package com.utarex.youngone.config;


import com.utarex.youngone.config.auth.JwtAuthenticationEntryPoint;
import com.utarex.youngone.config.auth.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new PasswordEncoder() {

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                if (encodedPassword == null) return false;
                return encodedPassword.equals(encode(rawPassword));
            }

            @Override
            public String encode(CharSequence rawPassword) {
                MessageDigest md;
                try {
                    md = MessageDigest.getInstance("SHA-256");
                    md.update(rawPassword.toString().getBytes());
                    return bytesToHex(md.digest());
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
                return null;
            }

            private String bytesToHex(byte[] bytes) {
                StringBuilder builder = new StringBuilder();
                for (byte b : bytes) {
                    builder.append(String.format("%02x", b));
                }
                return builder.toString();
            }
        };
    }

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;


    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/authenticate", "/join/user", "/join/agency", "/api/device/iot").permitAll() // 로그인 권한은 누구나, resources파일도 모든권한
                // USER, ADMIN 접근 허용
                .antMatchers("/api/user/**").hasRole("USER")
                .antMatchers("/api/agency/**").hasRole("AGENCY")
                .antMatchers("/api/corp/**").hasRole("CORP")
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and()
                .logout()
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .and()
                .cors()
                .and()
                .csrf()
                .disable()
                .headers()
                .frameOptions()
                .disable()
                .and()
                .build();
    }

    /**
     * cors 설정, 모든 url에 대해 Allow, Bean으로 등록
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(Arrays.asList("*"));
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
        configuration.setMaxAge(3600L); // preflight 1시간으로 설정
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
