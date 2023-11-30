package Godwin.taxSolution.springSecurity;


import Godwin.taxSolution.exceptions.ExceptionsHandlerFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    JWTAuthFilter jwtAuthFilter;

    @Autowired
    ExceptionsHandlerFilter exceptionsHandlerFilter;

    //here we create various Beans

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //must take this parameter

        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable());
        //disable login from start
        http.formLogin(httpSecurityFormLoginConfigurer -> httpSecurityFormLoginConfigurer.disable());

        //Adding custom filter
        http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(exceptionsHandlerFilter, JWTAuthFilter.class);

        //with the code below we could add or remove protectection from single endpoints
        http.authorizeHttpRequests(request -> request.requestMatchers("/**").permitAll());
        return http.build();
    }

    @Bean
    PasswordEncoder getEncoder(){
        return new BCryptPasswordEncoder(11);
    }

}
