package com.alura.foroalura.controller;

import jakarta.validation.Valid;
import com.alura.foroalura.domain.usuario.DatosAutenticacionUsuario;
// import com.alura.foroalura.domain.usuario.Usuario;
import com.alura.foroalura.infra.security.DatosJWTToken;
import com.alura.foroalura.infra.security.TokenService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.AuthenticationException;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<?> autenticarUsuario(
            @RequestBody @Valid DatosAutenticacionUsuario datosAutenticacionUsuario) {
        // Crea un token de autenticación con las credenciales proporcionadas
        Authentication autenticacionToken = new UsernamePasswordAuthenticationToken(
                datosAutenticacionUsuario.username(),
                datosAutenticacionUsuario.password());

        try {
            // Autentica al usuario usando el AuthenticationManager
            Authentication authentication = authenticationManager.authenticate(autenticacionToken);

            // Guarda la autenticación en el contexto de seguridad
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Accede a la información del usuario autenticado
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            // Genera un token JWT para el usuario autenticado
            var JWTToken = tokenService.generarToken(userDetails); // Cambio a userDetails en lugar de Usuario

            // Devuelve una respuesta exitosa con el token JWT
            return ResponseEntity.ok(new DatosJWTToken((String) JWTToken));
        } catch (AuthenticationException ex) {
            // Manejar el caso en el que la autenticación falla
            // Devolver una respuesta de error o realizar algún otro manejo de error
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}

/*
 * public ResponseEntity<?> autenticarUsuario(
 * 
 * @RequestBody @Valid DatosAutenticacionUsuario datosAutenticacionUsuario) {
 * // Crea un token de autenticación con las credenciales proporcionadas
 * Authentication autenticacionToken = new UsernamePasswordAuthenticationToken(
 * datosAutenticacionUsuario.username(),
 * datosAutenticacionUsuario.password());
 * 
 * try {
 * // Autentica al usuario usando el AuthenticationManager
 * Authentication authentication =
 * authenticationManager.authenticate(autenticacionToken);
 * 
 * // Guarda la autenticación en el contexto de seguridad
 * SecurityContextHolder.getContext().setAuthentication(authentication);
 * 
 * // Accede a la información del usuario autenticado
 * UserDetails userDetails = (UserDetails) authentication.getPrincipal();
 * 
 * // Genera un token JWT para el usuario autenticado
 * var JWTToken = tokenService.generarToken((Usuario) userDetails);
 * 
 * // Devuelve una respuesta exitosa con el token JWT
 * return ResponseEntity.ok(new DatosJWTToken(JWTToken));
 * } catch (AuthenticationException ex) {
 * // Manejar el caso en el que la autenticación falla
 * // Devolver una respuesta de error o realizar algún otro manejo de error
 * return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
 * }
 * }
 */

/*
 * @PostMapping
 * public ResponseEntity<?> autenticarUsuario(
 * 
 * @RequestBody @Valid DatosAutenticacionUsuario datosAutenticacionUsuario) {
 * // Crea un token de autenticación con las credenciales proporcionadas
 * Authentication autenticacionToken = new UsernamePasswordAuthenticationToken(
 * datosAutenticacionUsuario.username(),
 * datosAutenticacionUsuario.password());
 * 
 * // Autentica al usuario usando el AuthenticationManager
 * authenticationManager.authenticate(autenticacionToken);
 * 
 * // Obtiene el usuario autenticado
 * var usuarioAutenticado =
 * authenticationManager.authenticate(autenticacionToken);
 * 
 * // Genera un token JWT para el usuario autenticado
 * var JWTToken = tokenService.generarToken((Usuario)
 * usuarioAutenticado.getPrincipal());
 * 
 * // Devuelve una respuesta exitosa con el token JWT
 * return ResponseEntity.ok(new DatosJWTToken(JWTToken));
 * }
 */
