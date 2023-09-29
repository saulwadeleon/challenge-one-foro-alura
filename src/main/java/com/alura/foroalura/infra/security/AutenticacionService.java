package com.alura.foroalura.infra.security;

import com.alura.foroalura.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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
        // Llama al método "findByLogin" del repositorio de usuarios para buscar un
        // usuario por su nombre de usuario.
        // La implementación de este método en el repositorio debe devolver un objeto
        // UserDetails.
        return usuarioRepository.findByUsername(username);
    }
}
