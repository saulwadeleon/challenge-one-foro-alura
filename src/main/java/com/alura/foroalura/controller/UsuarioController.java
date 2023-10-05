package com.alura.foroalura.controller;

import com.alura.foroalura.domain.usuario.Usuario;
import com.alura.foroalura.infra.exception.InvalidRoleException;
import com.alura.foroalura.infra.exception.UsernameAlreadyExistsException;
import com.alura.foroalura.repository.RoleRepository;
import com.alura.foroalura.domain.usuario.DatosActualizacionUsuario;
import com.alura.foroalura.domain.usuario.DatosDelUsuario;
import com.alura.foroalura.domain.usuario.DatosRegistroUsuario;
import com.alura.foroalura.domain.usuario.Role;
import com.alura.foroalura.service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Esta clase es un controlador de Spring que maneja las solicitudes
 * relacionadas con los usuarios en la aplicación. Proporciona endpoints para
 * crear, obtener, actualizar, eliminar y desactivar usuarios, así como para
 * consultar todos los usuarios disponibles.
 * 
 * @author Saúl Wade León
 * @version 1.3
 */
@RestController
@RequestMapping("/usuarios")
@SecurityRequirement(name = "bearer-key")
public class UsuarioController {

    @Autowired
    private final UsuarioService usuarioService;

    @Autowired
    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    public UsuarioController(UsuarioService usuarioService, RoleRepository roleRepository,
            PasswordEncoder passwordEncoder) {
        this.usuarioService = usuarioService;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Endpoint para crear un nuevo usuario
    @PostMapping
    @Transactional
    @Operation(summary = "Crear un nuevo usuario")
    /**
     * Este método maneja las solicitudes POST en la ruta "/usuarios"
     * y se encarga de crear un nuevo usuario.
     * 
     * @param datosRegistroUsuario
     * @return
     */
    public ResponseEntity<String> crearUsuario(@RequestBody @Valid DatosRegistroUsuario datosRegistroUsuario) {
        Role role = roleRepository.findByNombreRole(datosRegistroUsuario.role()); // Busca el rol por nombre

        // Verificar si el nombre de usuario ya existe
        if (usuarioService.existeUsuarioPorUsername(datosRegistroUsuario.username())) {
            throw new UsernameAlreadyExistsException("El nombre de usuario ya está en uso.");
        }

        if (role == null) {
            return new ResponseEntity<String>("El rol especificado no existe", HttpStatus.BAD_REQUEST);
        }

        // Verificar si el rol existe en el catálogo de roles
        if (!usuarioService.existeRole(datosRegistroUsuario.role())) {
            throw new InvalidRoleException("El role de usuario proporcionado no es válido.");
        }

        // Crear un nuevo usuario con los datos proporcionados
        Usuario nuevoUsuario = new Usuario(datosRegistroUsuario, role);
        Usuario usuarioCreado = usuarioService.crearUsuario(nuevoUsuario);
        return new ResponseEntity<String>(usuarioCreado.getNombre() + " " + usuarioCreado.getApellido(),
                HttpStatus.CREATED);
    }

    // Endpoint para obtener todos los usuarios y mostrar los datos de un usuario
    @GetMapping
    @Operation(summary = "Obtener la lista de todos los usuarios")
    /**
     * Este método maneja las solicitudes GET en la ruta "/usuarios" y se encarga de
     * obtener todos los usuarios disponibles.
     * 
     * @return
     */
    public ResponseEntity<List<DatosDelUsuario>> obtenerTodosLosUsuarios() {
        List<Usuario> usuarios = usuarioService.obtenerTodosLosUsuarios();
        List<DatosDelUsuario> usuariosInfo = usuarios.stream()
                .map(DatosDelUsuario::new) // Convierte Usuario a DatosDelUsuario
                .collect(Collectors.toList());

        return new ResponseEntity<>(usuariosInfo, HttpStatus.OK);
    }

    // Endpoint para obtener un usuario por su ID y mostrar sus datos
    @GetMapping("/{id}")
    @Operation(summary = "Obtener un usuario por su ID")
    /**
     * Este método maneja las solicitudes GET en la ruta "/usuarios/{id}" y se
     * encarga de obtener los datos de un usuario por su ID.
     * 
     * @param id
     * @return
     */
    public ResponseEntity<DatosDelUsuario> obtenerUsuarioPorId(@PathVariable Long id) {
        Usuario usuario = usuarioService.obtenerUsuarioPorId(id);
        if (usuario != null) {
            DatosDelUsuario usuarioInfo = new DatosDelUsuario(usuario);
            return new ResponseEntity<>(usuarioInfo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint para actualizar un usuario por su ID
    @PutMapping("/{id}")
    @Transactional
    @Operation(summary = "Actualizar un usuario por su ID")
    /**
     * Este método maneja las solicitudes PUT en la ruta "/usuarios/{id}" y se
     * encarga de actualizar los datos de un usuario por su ID.
     * 
     * @param id
     * @return
     */
    public ResponseEntity<String> actualizarUsuario(@PathVariable Long id,
            @RequestBody DatosActualizacionUsuario datosActualizacionUsuario) {
        Usuario usuario = usuarioService.obtenerUsuarioPorId(id);
        if (usuario != null) {
            // Actualizar los campos relevantes del usuario existente con los datos de
            // DatosActualizacionUsuario
            usuario.setNombre(datosActualizacionUsuario.nombre());
            usuario.setApellido(datosActualizacionUsuario.apellido());
            usuario.setEmail(datosActualizacionUsuario.email());
            usuario.setLogin(datosActualizacionUsuario.username());
            usuario.setActivo(datosActualizacionUsuario.activo());

            // Si deseas actualizar la contraseña, encripta la nueva contraseña
            if (datosActualizacionUsuario.password() != null &&
                    !datosActualizacionUsuario.password().isEmpty()) {
                usuario.setPassword(passwordEncoder.encode(datosActualizacionUsuario.password()));
            }

            // Mapear el nombre del role a role_id y actualizarlo en la tabla de usuarios
            Role role = roleRepository.findByNombreRole(datosActualizacionUsuario.role());
            if (role != null) {
                usuario.setRole(role);
            } else {
                // Manejar el caso donde el rol no existe
                return new ResponseEntity<String>("El rol de usuario proporcionado no es válido",
                        HttpStatus.BAD_REQUEST);
            }

            Usuario usuarioActualizado = usuarioService.actualizarUsuario(id, usuario);
            DatosActualizacionUsuario usuarioInfo = new DatosActualizacionUsuario(usuarioActualizado);
            return new ResponseEntity<>(usuarioInfo.nombre() + " " + usuarioInfo.apellido(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Usuario no encontrado", HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint para eliminar un usuario por su ID
    @DeleteMapping("/{id}")
    @Transactional
    @Operation(summary = "Eliminar un usuario por su ID")
    /**
     * Este método maneja las solicitudes DELETE en la ruta "/usuarios/{id}" y
     * se encarga de eliminar un usuario por su ID.
     * 
     * @param id
     * @return
     */
    public ResponseEntity<String> eliminarUsuario(@PathVariable Long id) {
        if (usuarioService.existsById(id)) {
            usuarioService.eliminarUsuario(id);
            return new ResponseEntity<>("El usuario fue eliminado exitosamente", HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>("El usuario que intenta eliminar no existe", HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint para desactivar un usuario por su ID (borrado lógico)
    @PatchMapping("/{id}/desactivar")
    @Operation(summary = "Desactivar un usuario por su ID")
    /**
     * Este método maneja las solicitudes PATCH en la ruta
     * "/usuarios/{id}/desactivar"
     * y se encarga de desactivar un usuario por su ID.
     * 
     * @param id
     * @return
     */
    public ResponseEntity<Void> desactivarUsuario(@PathVariable Long id) {
        usuarioService.desactivarUsuario(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
