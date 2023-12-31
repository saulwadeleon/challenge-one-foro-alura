package com.alura.foroalura.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Esta clase configura la seguridad de la aplicación. Define reglas de
 * autorización para diferentes rutas y configura cómo se maneja la
 * autenticación. También se deshabilita la protección CSRF y se establece la
 * política de creación de sesiones como "sin estado". Esto es común en
 * aplicaciones modernas basadas en tokens JWT.
 * 
 * @author Saúl Wade León
 * @version 1.3
 */
@Configuration
@EnableWebSecurity
@SuppressWarnings("all")
public class SecurityConfigurations {

    @Autowired
    private SecurityFilter securityFilter;

    /**
     * El método securityFilterChain configura las reglas de
     * autorización y establece la política de creación de sesiones como "sin
     * estado" (STATELESS).
     * 
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(csrf -> csrf.disable()) // Deshabilitar protección CSRF
                .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeRequests(requests -> requests // Comenzar la configuración de reglas de autorización
                        // Permitir todas las solicitudes POST a "/login" sin autenticación
                        .requestMatchers(HttpMethod.POST, "/login").permitAll()
                        .requestMatchers("/swagger-ui.html", "/v3/api-docs/**", "/swagger-ui/**").permitAll()
                        // Requiere el rol "USER" para algunas solicitudes específicas
                        .requestMatchers(HttpMethod.POST, "/topicos", "/respuestas")
                        .hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/topicos", "/usuarios", "/respuestas")
                        .hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/topicos", "/usuarios", "/respuestas")
                        .hasAnyRole("USER", "ADMIN")
                        // Requiere el rol "ADMIN" para algunas solicitudes específicas
                        .requestMatchers(HttpMethod.PUT, "/usuarios").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/usuarios").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/topicos", "/usuarios", "/respuestas").hasRole("ADMIN")
                        // Requiere autenticación para cualquier otra solicitud no especificada
                        // anteriormente
                        .anyRequest().authenticated())
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build(); // Finalizar la configuración y construir la cadena de filtros de seguridad
    }

    /**
     * El método authenticationManager: Configura el administrador de autenticación.
     * 
     * @param configuration
     * @return
     * @throws Exception
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    /**
     * El método passwordEncoder: Configura el codificador de contraseñas, en este
     * caso, se utiliza BCryptPasswordEncoder.
     * 
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Configurar el codificador de contraseñas (BCrypt)
    }
}
