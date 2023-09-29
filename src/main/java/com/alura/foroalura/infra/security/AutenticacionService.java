package com.alura.foroalura.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.alura.foroalura.domain.usuario.Usuario;
import com.alura.foroalura.repository.UsuarioRepository;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;
import java.util.ArrayList;

/**
 * 
 * AutenticacionService es un componente de servicio que se encarga de cargar
 * detalles del usuario durante el proceso de autenticación. Utiliza un
 * repositorio de usuarios (UsuarioRepository) para buscar usuarios por el
 * nombre de usuario y devuelve un objeto UserDetails, que se utiliza en el
 * proceso de autenticación de Spring Security.
 * 
 */
@Service
public class AutenticacionService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Este método se implementa de la interfaz UserDetailsService y se utiliza para
    // cargar detalles de usuario por su nombre de usuario.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Llama al método "findByUsername" del repositorio de usuarios para buscar un
        // usuario por su nombre de usuario.
        // La implementación de este método en el repositorio debe devolver un objeto
        // UserDetails.
        try {
            // Utiliza el repositorio de usuarios para buscar un usuario por su nombre de
            // usuario.
            Usuario usuario = (Usuario) usuarioRepository.findByUsername(username);

            if (usuario == null) {
                throw new UsernameNotFoundException("Usuario no encontrado: " + username);
            }

            // Construir una lista de roles/granted authorities a partir de los roles del
            // usuario
            List<GrantedAuthority> authorities = new ArrayList<>();
            for (String role : usuario.getRole()) {
                authorities.add(new SimpleGrantedAuthority("ROLE_" + role)); // Spring Security espera que los roles
                                                                             // comiencen con "ROLE"
            }

            // Devolver un objeto UserDetails con el nombre de usuario, contraseña y roles
            return new org.springframework.security.core.userdetails.User(
                    usuario.getLogin(),
                    usuario.getPassword(),
                    authorities);
        } catch (Exception e) {
            throw new UsernameNotFoundException("Error al buscar el usuario: " + e.getMessage());
        }
    }

}
