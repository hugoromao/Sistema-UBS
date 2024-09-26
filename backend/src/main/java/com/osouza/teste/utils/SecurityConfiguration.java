package com.osouza.teste.utils;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.osouza.teste.service.UsuarioDetailsService;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@ComponentScan
public class SecurityConfiguration {
    
    @Autowired
    UsuarioDetailsService usuarioDetailsService;

    @Autowired
    private TokenValidationFilter tokenValidationFilter;

	@Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {

       http
               .cors()
               .and()
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("home", "authenticate", "/docs/**")
                .permitAll()

                .requestMatchers("/usuario/**").hasAnyRole("USER", "ADMIN")

                .requestMatchers("admin").hasRole("ADMIN").anyRequest()
                .authenticated()
            )
            .httpBasic(withDefaults())
            .csrf().disable();

            http.addFilterBefore(tokenValidationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();

    }

}
