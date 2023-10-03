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

    /*
     * public ResponseEntity<Usuario> crearUsuario(@RequestBody @Valid
     * DatosRegistroUsuario datosRegistroUsuario) {
     * Role role = roleRepository.findByNombreRole(datosRegistroUsuario.role()); //
     * Busca el rol por nombre
     * 
     * // Verificar si el nombre de usuario ya existe
     * if (usuarioService.existeUsuarioPorUsername(datosRegistroUsuario.username()))
     * {
     * throw new
     * UsernameAlreadyExistsException("El nombre de usuario ya está en uso.");
     * }
     * 
     * if (role == null) {
     * // Manejar el caso donde el rol no existe
     * System.out.println("El rol especificado no existe");
     * }
     * 
     * // Verificar si el rol existe en el catálogo de roles
     * if (!usuarioService.existeRole(datosRegistroUsuario.role())) {
     * throw new
     * InvalidRoleException("El role de usuario proporcionado no es válido.");
     * }
     * 
     * // Crear un nuevo usuario con los datos proporcionados
     * Usuario nuevoUsuario = new Usuario(datosRegistroUsuario, role);
     * 
     * /*
     * Usuario nuevoUsuario = new Usuario(
     * null,
     * datosRegistroUsuario.nombre(),
     * datosRegistroUsuario.apellido(),
     * datosRegistroUsuario.email(),
     * datosRegistroUsuario.username(),
     * datosRegistroUsuario.password(),
     * role.getId());
     * 
     * 
     * Usuario usuarioCreado = usuarioService.crearUsuario(nuevoUsuario);
     * return new ResponseEntity<>(usuarioCreado, HttpStatus.CREATED);
     * }
     */

    // Endpoint para obtener todos los usuarios y mostrar los datos de un usuario
    @GetMapping
    @Operation(summary = "Obtener la lista de todos los usuarios")
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
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Endpoint para desactivar un usuario por su ID (borrado lógico)
    @PatchMapping("/{id}/desactivar")
    @Operation(summary = "Desactivar un usuario por su ID")
    public ResponseEntity<Void> desactivarUsuario(@PathVariable Long id) {
        usuarioService.desactivarUsuario(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
