package com.alura.foroalura.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.alura.foroalura.domain.usuario.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * 
 * La clase TokenService se utiliza para generar y verificar tokens JWT (JSON
 * Web Tokens). Genera tokens JWT firmados y también verifica la validez de
 * los tokens recibidos. Los tokens JWT se utilizan para la autenticación y
 * autorización de usuarios en la aplicación.
 * 
 */
@Service
public class TokenService {

    @Value("${foroalura.security.secret}")
    private String foroAluraSecret; // La clave secreta utilizada para firmar el token

    /**
     * Genera un token JWT con la información del usuario.
     *
     * @param usuario El objeto Usuario que representa al usuario autenticado.
     * @return El token JWT generado.
     */
    public String generarToken(Usuario usuario) {
        try {
            // Crear un algoritmo de firma HMAC256 con la clave secreta
            Algorithm algorithm = Algorithm.HMAC256(foroAluraSecret);

            // Generar un token JWT con el emisor, el nombre de usuario, un claim
            // personalizado (ID) y la fecha de expiración
            return JWT.create()
                    .withIssuer("foro alura")
                    .withSubject(usuario.getUsername())
                    .withClaim("id", usuario.getId())
                    .withExpiresAt(generarFechaExpiracion())
                    .sign(algorithm); // Firmar el token con el algoritmo
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error al crear el token JWT");
        }
    }

    /**
     * Obtiene el sujeto (nombre de usuario) del token JWT.
     *
     * @param token El token JWT a verificar.
     * @return El sujeto (nombre de usuario) del token.
     */
    public String getSubject(String token) {
        if (token == null) {
            throw new RuntimeException("Token nulo");
        }
        DecodedJWT verifier = null;
        try {
            // Crear un algoritmo de verificación HMAC256 con la clave secreta
            Algorithm algorithm = Algorithm.HMAC256(foroAluraSecret);

            // Verificar la firma y descomprimir el token JWT
            verifier = JWT.require(algorithm)
                    .withIssuer("voll med")
                    .build()
                    .verify(token);

            // Obtener el sujeto (nombre de usuario) del token
            return verifier.getSubject(); // Devolver el sujeto (nombre de usuario) del token
        } catch (TokenExpiredException e) {
            // Excepción de token expirado: Renovar el token y devolver el nuevo token
            return renovarToken(token);
        } catch (JWTVerificationException exception) {
            System.out.println(exception.toString());
            throw new RuntimeException("Verificador de token inválido");
        }
    }

    /**
     * Renueva un token JWT utilizando un token expirado como prueba de
     * autenticación.
     *
     * @param tokenExpirado El token JWT expirado.
     * @return El nuevo token JWT generado.
     */
    private String renovarToken(String tokenExpirado) {
        try {
            // Verificar el token expirado para obtener el nombre de usuario
            String username = getSubject(tokenExpirado);

            // Crear un nuevo token con la misma información, pero con una nueva fecha de
            // expiración
            return generarToken(new Usuario(username)); // Debes proporcionar una instancia válida de Usuario
        } catch (RuntimeException e) {
            // Maneja la excepción apropiadamente, por ejemplo, registrándola o lanzándola
            // nuevamente
            throw e;
        }
    }

    /**
     * Genera la fecha de expiración como la hora actual más 2 horas, con un
     * desplazamiento horario específico.
     *
     * @return La fecha de expiración en formato Instant.
     */
    private Instant generarFechaExpiracion() {
        // Generar la fecha de expiración como la hora actual más 2 horas, con un
        // desplazamiento horario específico
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));
    }
}
