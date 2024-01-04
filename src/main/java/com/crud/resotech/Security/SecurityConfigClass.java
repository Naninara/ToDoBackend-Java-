package com.crud.resotech.Security;


import com.crud.resotech.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfigClass{


    @Autowired
    UserService userService;



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {


        http.authorizeHttpRequests((req)->req
                .requestMatchers("/login/**").permitAll()
                .requestMatchers("/user/**").hasAnyAuthority("user")
                .anyRequest().authenticated()).csrf(AbstractHttpConfigurer::disable)
                .userDetailsService(userService)
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

}
