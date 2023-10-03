package com.alura.foroalura.service;

import com.alura.foroalura.domain.usuario.Role;
import com.alura.foroalura.domain.usuario.Usuario;
import com.alura.foroalura.infra.exception.InvalidRoleException;
import com.alura.foroalura.repository.RoleRepository;
import com.alura.foroalura.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private final UsuarioRepository usuarioRepository;

    @Autowired
    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder,
            RoleRepository roleRepository) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    // Método para crear un nuevo usuario
    public Usuario crearUsuario(Usuario usuario) {
        // Encriptar la contraseña antes de guardarla en la base de datos
        if (usuarioRepository.existsByUsername(usuario.getUsername())) {
            throw new IllegalArgumentException("El usuario ya existe");
        } else {
            usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
            return usuarioRepository.save(usuario);
        }
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
        // Validar que el rol especificado exista en el catálogo de roles
        Role role = roleRepository.findByNombreRole(usuarioActualizado.getRoleName());
        if (roleRepository.existsByNombreRole(role.getNombre())) {

            Usuario usuarioExistente = obtenerUsuarioPorId(id);
            if (usuarioExistente != null) {
                // Actualiza solo los campos específicos
                if (usuarioActualizado.getNombre() != null) {
                    usuarioExistente.setNombre(usuarioActualizado.getNombre());
                }
                if (usuarioActualizado.getApellido() != null) {
                    usuarioExistente.setApellido(usuarioActualizado.getApellido());
                }
                if (usuarioActualizado.getEmail() != null) {
                    usuarioExistente.setEmail(usuarioActualizado.getEmail());
                }
                if (usuarioActualizado.getUsername() != null) {
                    usuarioExistente.setLogin(usuarioActualizado.getUsername());
                }
                if (usuarioActualizado.getPassword() != null) {
                    // Si deseas actualizar la contraseña, encripta la nueva contraseña
                    usuarioExistente.setPassword(passwordEncoder.encode(usuarioActualizado.getPassword()));
                }

                usuarioExistente.setRole(role);

                if (usuarioActualizado.isEnabled()) {
                    usuarioExistente.setActivo(usuarioActualizado.isEnabled());
                }

                return usuarioRepository.save(usuarioExistente);
            }
        } else {
            throw new InvalidRoleException("El role de usuario proporcionado no es válido.");
        }
        return null; // Retornar null si el usuario no se encuentra
    }

    // Método para eliminar un usuario por su ID
    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    // Método para desactivar un usuario por su ID (borrado lógico)
    public void desactivarUsuario(Long id) {
        Usuario usuarioExistente = obtenerUsuarioPorId(id);
        if (usuarioExistente != null) {
            usuarioExistente.setActivo(false); // Establece el usuario como inactivo
            usuarioRepository.save(usuarioExistente); // Guarda la actualización en la base de datos
        }
    }

    // Método para activar un usuario por su ID
    public void activarUsuario(Long id) {
        Usuario usuarioExistente = obtenerUsuarioPorId(id);
        if (usuarioExistente != null) {
            usuarioExistente.setActivo(true); // Establece el usuario como activo
            usuarioRepository.save(usuarioExistente); // Guarda la actualización en la base de datos
        }
    }

    public boolean existeUsuarioPorUsername(String username) {
        return usuarioRepository.existsByUsername(username);
    }

    public boolean existeRole(String role) {
        return roleRepository.existsByNombreRole(role);
    }

    public Usuario obtenerUsuarioPorNombre(String autor) {
        return usuarioRepository.findByNombre(autor);
    }

    public List<Usuario> obtenerUsuariosActivos() {
        return usuarioRepository.findByActivo(true);
    }

}
