package org.example.bazaaranalyze.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConf {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {

//        http
//                .cors(AbstractHttpConfigurer::disable)
//                .csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(
//                        auth -> {
//
//                            auth.requestMatchers(
//                                    "/**",
//                                    "/api/auth/save",
//                                    "api/auth/login",
//                                    "/api/weather/**",
//                                    "/api/locations/address",
//                                    "/api/population/world",
//                                    "/api/currency/all",
//                                    "/api/currency/global",
//                                    "/api/news/daily",
//                                    "/api/crypt/btc",
//                                    "/api/crypt/eth",
//                                    "/api/combinant/metalls",
//                                    "/api/calculate/rates",
//                                    "/api/transaction/**",
//                                    "/api/cards/**"
//
//                            ).permitAll();
//
//                            // Swagger permitions
//                            auth.requestMatchers(
//                                    "/api/auth/**",
//                                    "/v2/api-docs",
//                                    "/v3/api-docs",
//                                    "/v3/api-docs/**",
//                                    "/swagger-resources",
//                                    "/swagger-resources/**",
//                                    "/configuration/ui",
//                                    "/configuration/security",
//                                    "/swagger-ui/**",
//                                    "/webjars/**",
//                                    "/swagger-ui.html/**"
//                            ).permitAll();
//
//                            auth.anyRequest().authenticated();
//                        }
//                );
//
//        return http.build();

        http
                .cors(cors -> cors.configurationSource(request -> {
                    var corsConfig = new org.springframework.web.cors.CorsConfiguration();
//                    corsConfig.addAllowedOrigin("https://birthday-frontend-d8395a6906eb.herokuapp.com"); // Frontend manzili
                    corsConfig.addAllowedOrigin("https://bazaar-analyze-front.vercel.app/"); // Frontend manzili
                    corsConfig.addAllowedMethod("*"); // Barcha metodlarga ruxsat berish
                    corsConfig.addAllowedHeader("*"); // Barcha headerlarga ruxsat berish
                    corsConfig.setAllowCredentials(true); // Credentiallarni ruxsat berish
                    return corsConfig;
                }))
                .csrf(AbstractHttpConfigurer::disable) // CSRF himoyasini o'chirish
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/**", "/api/**", "/api/message/**").permitAll();
                    auth.anyRequest().authenticated(); // Boshqa barcha so'rovlar autentifikatsiya talab qiladi
                });

        return http.build();
    }

    // JWT part
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
