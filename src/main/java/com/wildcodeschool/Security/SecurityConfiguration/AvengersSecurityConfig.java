package com.wildcodeschool.Security.SecurityConfiguration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class AvengersSecurityConfig {
    
     @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> {
            auth.requestMatchers("/").permitAll();
            auth.requestMatchers("/avengers/assemble").hasRole("CHAMPION");
            auth.requestMatchers("/secret-base").hasRole("DIRECTOR");
            auth.anyRequest().authenticated();
        })
        .formLogin(Customizer.withDefaults()).build();

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails champion = User.builder()
                .username("Steve")
                .password(passwordEncoder().encode("motdepasse"))
                .roles("CHAMPION")
                .build();
        UserDetails director = User.builder()
                .username("Nick")
                .password(passwordEncoder().encode("flerken"))
                .roles("DIRECTOR", "CHAMPION")
                .build();

        return new InMemoryUserDetailsManager(champion, director);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
