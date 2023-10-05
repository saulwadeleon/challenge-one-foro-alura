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

/**
 * UsuarioService proporciona métodos para gestionar usuarios de la aplicación,
 * incluyendo la creación, obtención, actualización y eliminación de usuarios,
 * así como la gestión de su activación y desactivación. También verifica la
 * existencia de roles y nombres de usuario, y garantiza la encriptación
 * adecuada de las contraseñas antes de guardarlas en la base de datos.
 * 
 * @author Saúl Wade León
 * @version 1.3
 */
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

    /**
     * crearUsuario permite crear un nuevo usuario. Realiza las siguientes acciones:
     * Verifica si ya existe un usuario con el mismo nombre de usuario (username).
     * Si existe, lanza una excepción.
     * Encripta la contraseña proporcionada en el usuario antes de guardarla en la
     * base de datos.
     * Guarda el usuario en la base de datos utilizando el repositorio
     * (usuarioRepository).
     * 
     * @param usuario
     * @return
     */
    public Usuario crearUsuario(Usuario usuario) {
        // Encriptar la contraseña antes de guardarla en la base de datos
        if (usuarioRepository.existsByUsername(usuario.getUsername())) {
            throw new IllegalArgumentException("El usuario ya existe");
        } else {
            usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
            return usuarioRepository.save(usuario);
        }
    }

    /**
     * obtenerTodosLosUsuarios devuelve una lista de todos los usuarios almacenados
     * en la base de datos. Utiliza el método findAll() proporcionado por el
     * repositorio.
     * 
     * @return
     */
    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioRepository.findAll();
    }

    /**
     * obtenerUsuarioPorId permite obtener un usuario específico por su
     * identificador (id). Utiliza el método findById(id) del repositorio y retorna
     * el usuario si se encuentra; de lo contrario, retorna null.
     * 
     * @param id
     * @return
     */
    public Usuario obtenerUsuarioPorId(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    /**
     * actualizarUsuario actualiza un usuario existente por su identificador (id).
     * 
     * @param id
     * @param usuarioActualizado
     * @return
     */
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

    /**
     * eliminarUsuario permite eliminar un usuario por su identificador (id).
     * Verifica si el usuario existe en la base de datos antes de eliminarlo.
     * 
     * @param id
     */
    public void eliminarUsuario(Long id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("El usuario a eliminar no existe");
        }
    }

    /**
     * desactivarUsuario desactiva (realiza un borrado lógico) un usuario por su
     * identificador (id) estableciendo el campo activo en false.
     * 
     * @param id
     */
    public void desactivarUsuario(Long id) {
        Usuario usuarioExistente = obtenerUsuarioPorId(id);
        if (usuarioExistente != null) {
            usuarioExistente.setActivo(false); // Establece el usuario como inactivo
            usuarioRepository.save(usuarioExistente); // Guarda la actualización en la base de datos
        }
    }

    /**
     * activarUsuario activa un usuario por su identificador (id) estableciendo el
     * campo activo en true.
     * 
     * @param id
     */
    public void activarUsuario(Long id) {
        Usuario usuarioExistente = obtenerUsuarioPorId(id);
        if (usuarioExistente != null) {
            usuarioExistente.setActivo(true); // Establece el usuario como activo
            usuarioRepository.save(usuarioExistente); // Guarda la actualización en la base de datos
        }
    }

    /**
     * existeUsuarioPorUsername verifica si existe un usuario en la base de datos
     * con el nombre de usuario (username) proporcionado.
     * 
     * @param username
     * @return
     */
    public boolean existeUsuarioPorUsername(String username) {
        return usuarioRepository.existsByUsername(username);
    }

    /**
     * existeRole verifica si existe un rol con el nombre proporcionado en el
     * catálogo de roles.
     * 
     * @param role
     * @return
     */
    public boolean existeRole(String role) {
        return roleRepository.existsByNombreRole(role);
    }

    /**
     * obtenerUsuarioPorNombre permite obtener un usuario por su nombre (autor).
     * 
     * @param autor
     * @return
     */
    public Usuario obtenerUsuarioPorNombre(String autor) {
        return usuarioRepository.findByNombre(autor);
    }

    /**
     * obtenerUsuariosActivos devuelve una lista de usuarios activos (cuyo campo
     * activo es true).
     * 
     * @return
     */
    public List<Usuario> obtenerUsuariosActivos() {
        return usuarioRepository.findByActivo(true);
    }

    /**
     * existsById verifica si existe un usuario en la base de datos con el
     * identificador proporcionado (id).
     * 
     * @param id
     * @return
     */
    public boolean existsById(Long id) {
        return usuarioRepository.existsById(id);
    }

}
