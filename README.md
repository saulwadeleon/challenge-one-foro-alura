# challenge-one-foro-alura
challengeoneforoalura5

# Foro Alura

## Descripción
La API del Foro Alura es una aplicación RESTful desarrollada en Java utilizando el framework Spring. Su propósito es crear y gestionar un foro similar al de Alura, permitiendo a los usuarios realizar una variedad de operaciones relacionadas con tópicos, usuarios y respuestas. A continuación, se describen las principales funcionalidades:
## Funcionalidades

## Capturas de Pantalla
![Screenshot Login](https://github.com/saulwadeleon/challenge-one-foro-alura/assets/128748724/17fb7bca-87b1-4e17-8229-fe7ed11edf81)
![Screenshot Login swagger](https://github.com/saulwadeleon/challenge-one-foro-alura/assets/128748724/8a957685-f305-4ce0-87cc-0a4c11a04296)
![Screenshot Respuestas por Topico](https://github.com/saulwadeleon/challenge-one-foro-alura/assets/128748724/7a8f4dc6-b02a-432b-8fef-766a535dbbca)
![Screenshot Respuestas por Topico 02](https://github.com/saulwadeleon/challenge-one-foro-alura/assets/128748724/1fcee827-7ac9-46f8-9e37-185a80b64e51)

## Características

### Gestión de Cursos
- Los usuarios pueden crear cursos especificando su nombre y categoría.
- Se pueden buscar cursos por nombre y categoría.
- Los cursos se pueden actualizar y eliminar.

### Foro de Discusión
- Los usuarios pueden crear tópicos de discusión con un título, mensaje y curso relacionado.
- Los tópicos pueden estar en diferentes estados, como "No Respondido" o "Respondido".
- Los usuarios pueden buscar tópicos por título, estado y autor.
- Se permite la actualización y eliminación de tópicos.
- Se realiza un seguimiento del número de respuestas en cada tópico.

### Respuestas a Tópicos
- Los usuarios pueden responder a tópicos existentes proporcionando un mensaje.
- Se pueden buscar respuestas por tópico.
- Las respuestas se pueden actualizar y eliminar.

### Usuarios y Roles
- Los usuarios pueden registrarse proporcionando su nombre, apellido, dirección de correo electrónico y contraseña.
- Se manejan roles de usuario, como "USER" y "ADMIN".
- Los usuarios pueden actualizar su información de perfil, incluida la contraseña.
- Los administradores pueden desactivar y activar cuentas de usuario.
- Se realiza una gestión segura de contraseñas con encriptación.

### Seguridad
- Utiliza Spring Security para proteger las rutas y autenticar a los usuarios.
- Se emplea JWT (JSON Web Tokens) para la autenticación basada en token.
- Las excepciones personalizadas manejan errores de seguridad.

### Documentación
- La API está documentada utilizando Springdoc OpenAPI.
- Los endpoints y sus descripciones están disponibles para los desarrolladores.

### Contribución
- Los desarrolladores pueden contribuir al proyecto siguiendo un proceso de bifurcación y solicitud de extracción.
- Se fomenta la contribución al proyecto para mejorarlo continuamente.

### Tecnologías Utilizadas
- Java
- Spring Boot
- Spring Security
- Spring Data JPA
- JWT (JSON Web Tokens)
- Base de datos relacional (ejemplo: MySQL)
- Documentación de API con Springdoc OpenAPI
- Control de versiones con Git y GitHub

Estas características hacen que el Foro Alura sea una plataforma completa y segura para la colaboración y discusión en la comunidad de estudiantes de Alura.


## Paquetes
### Paquete `com.alura.foroalura.domain.curso`
- `Curso`: Representa un curso con su nombre y categoría.
- `Categoria`: Representa una categoría para clasificar los cursos.

### Paquete `com.alura.foroalura.domain.respuesta`
- `Respuesta`: Representa una respuesta a un tópico en el foro.
- `DatosActualizacionRespuesta`: Contiene datos para actualizar una respuesta.
- `DatosConsultaRespuesta`: Contiene datos para consultar una respuesta.
- `DatosRegistroRespuesta`: Contiene datos para registrar una nueva respuesta.

### Paquete `com.alura.foroalura.domain.topico`
- `Topico`: Representa un tópico o pregunta en el foro.
- `DatosActualizacionTopico`: Contiene datos para actualizar un tópico.
- `DatosRegistroTopico`: Contiene datos para registrar un nuevo tópico.
- `StatusTopico`: Enumeración que define el estado de un tópico.

### Paquete `com.alura.foroalura.domain.usuario`
- `Usuario`: Representa un usuario del sistema.
- `Role`: Representa un rol de usuario.

### Paquete `com.alura.foroalura.infra.exception`
- Excepciones personalizadas utilizadas en la aplicación.

### Paquete `com.alura.foroalura.infra.security`
- Configuraciones de seguridad utilizando Spring Security.
- Filtros de seguridad para autenticación basada en token JWT.

### Paquete `com.alura.foroalura.infra.springdoc`
- Configuraciones de Springdoc OpenAPI para documentación.

### Paquete `com.alura.foroalura.repository`
- Repositorios JPA para interactuar con la base de datos.

### Paquete `com.alura.foroalura.service`
- Servicios que gestionan la lógica de negocio de la aplicación.
- Creación, actualización, eliminación y consulta de cursos, respuestas, tópicos y usuarios.
- Gestión de roles, encriptación de contraseñas y validaciones.

## Tecnologías Utilizadas
- Java
- Spring Boot
- Spring Security
- Spring Data JPA
- JWT (JSON Web Tokens)
- Base de datos relacional (ejemplo: MySQL)
- Documentación de API con Springdoc OpenAPI
- Control de versiones con Git y GitHub

## Instalación y Uso
1. Clona el repositorio desde GitHub.
2. Configura la base de datos en el archivo `application.properties` o desde consola con el archivo JAR y `application-prod.properties`.
3. Ejecuta la aplicación Spring Boot.
4. Accede a la API a través de las rutas especificadas en el controlador.

## Contribución
Si deseas contribuir a este proyecto, por favor sigue los pasos:
1. Realiza un fork del repositorio.
2. Crea una rama con una descripción clara de la función o corrección que deseas implementar.
3. Realiza tus cambios y asegúrate de mantener la calidad del código.
4. Crea un pull request a la rama principal del proyecto.

## Créditos

Esta aplicación fue desarrollada por [Saúl Wade León](https://github.com/saulwadeleon) como parte de Challenges BackEnd ONE - G5.

## Licencia

Este proyecto está bajo la Licencia [SWL](LICENSE).

