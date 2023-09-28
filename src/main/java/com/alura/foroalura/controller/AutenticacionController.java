package com.alura.foroalura.controller;

import jakarta.validation.Valid;
import com.alura.foroalura.domain.usuario.DatosAutenticacionUsuario;
import com.alura.foroalura.domain.usuario.Usuario;
import com.alura.foroalura.infra.security.DatosJWTToken;
import com.alura.foroalura.infra.security.TokenService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

        // Autentica al usuario usando el AuthenticationManager
        authenticationManager.authenticate(autenticacionToken);

        // Obtiene el usuario autenticado
        var usuarioAutenticado = authenticationManager.authenticate(autenticacionToken);

        // Genera un token JWT para el usuario autenticado
        var JWTToken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());

        // Devuelve una respuesta exitosa con el token JWT
        return ResponseEntity.ok(new DatosJWTToken(JWTToken));
    }
}
