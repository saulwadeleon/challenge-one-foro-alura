package com.alura.foroalura.service;

import com.alura.foroalura.domain.usuario.Usuario;
import com.alura.foroalura.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Método para crear un nuevo usuario
    public Usuario crearUsuario(Usuario usuario) {
        // Encriptar la contraseña antes de guardarla en la base de datos
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return usuarioRepository.save(usuario);
    }

    // Método para obtener todos los usuarios
    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioRepository.findAll();
    }

    // Método para obtener un usuario por su ID
    public Usuario obtenerUsuarioPorId(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    // Método para actualizar un usuario por su ID
    public Usuario actualizarUsuario(Long id, Usuario usuarioActualizado) {
        Usuario usuarioExistente = obtenerUsuarioPorId(id);
        if (usuarioExistente != null) {
            // Actualizar los campos relevantes del usuario existente con los datos del
            // usuario actualizado
            usuarioExistente.setNombre(usuarioActualizado.getNombre());
            usuarioExistente.setApellido(usuarioActualizado.getApellido());
            usuarioExistente.setEmail(usuarioActualizado.getEmail());
            usuarioExistente.setUsername(usuarioActualizado.getUsername());

            // Si deseas actualizar la contraseña, encripta la nueva contraseña
            if (usuarioActualizado.getPassword() != null && !usuarioActualizado.getPassword().isEmpty()) {
                usuarioExistente.setPassword(passwordEncoder.encode(usuarioActualizado.getPassword()));
            }

            return usuarioRepository.save(usuarioExistente);
        }
        return null; // Retornar null si el usuario no se encuentra
    }

    // Método para eliminar un usuario por su ID
    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
}
