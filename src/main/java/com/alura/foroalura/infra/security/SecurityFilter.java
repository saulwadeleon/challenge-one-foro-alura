package com.alura.foroalura.infra.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.alura.foroalura.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * 
 * Este clase filtro se encarga de extraer el token de autenticación de la
 * cabecera HTTP Authorization, verificar su validez y, si es válido, autenticar
 * al usuario correspondiente. Luego, establece esta autenticación en el
 * contexto de seguridad de Spring Security para que las solicitudes posteriores
 * estén autenticadas correctamente.
 * 
 * @author Saúl Wade León
 * @version 1.3
 */
@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * El método doFilterInternal se ejecuta en cada solicitud HTTP. Aquí se realiza
     * el proceso de autenticación y autorización.
     * 
     * @param request
     * @param response
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     * @throws Exception
     * 
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // Obtener el token del header
        var authHeader = request.getHeader("Authorization");
        if (authHeader != null) {
            var token = authHeader.replace("Bearer ", ""); // Extraer el token (eliminar "Bearer")
            var nombreUsuario = tokenService.getSubject(token); // Extraer el nombre de usuario del token

            if (nombreUsuario != null) {
                // Token válido, buscar al usuario en el repositorio
                var usuario = usuarioRepository.findByUsername(nombreUsuario);

                // Crear una autenticación con el usuario y establecerla en el contexto de
                // seguridad
                var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        // Continuar con la cadena de filtros
        filterChain.doFilter(request, response);
    }
}