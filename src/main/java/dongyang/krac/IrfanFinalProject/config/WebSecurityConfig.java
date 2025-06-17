package dongyang.krac.IrfanFinalProject.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())  // if you must disable it
            .authorizeHttpRequests(auth -> auth
                .anyRequest()
                .permitAll()
            );
        return http.build();
    }
}