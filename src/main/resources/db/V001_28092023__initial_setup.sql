--
-- Table structure for table categoria
--
DROP TABLE IF EXISTS categoria;

CREATE TABLE
  categoria (
    id bigint NOT NULL AUTO_INCREMENT,
    dsc_categoria varchar(45) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY dsc_categoria_UNIQUE (dsc_categoria)
  );

--
-- Table structure for table curso
--
DROP TABLE IF EXISTS curso;

CREATE TABLE
  curso (
    id bigint NOT NULL AUTO_INCREMENT,
    nombre_curso varchar(200) NOT NULL,
    categoria_id bigint NOT NULL,
    PRIMARY KEY (id),
    KEY fk_curso_categoria_idx (categoria_id),
    CONSTRAINT fk_curso_categoria FOREIGN KEY (categoria_id) REFERENCES categoria (id) ON DELETE RESTRICT ON UPDATE CASCADE
  );

--
-- Table structure for table role
--
DROP TABLE IF EXISTS role;

CREATE TABLE
  role (
    id bigint NOT NULL AUTO_INCREMENT,
    nombre_role varchar(45) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY nombre_role_UNIQUE (nombre_role)
  );

--
-- Table structure for table usuario
--
DROP TABLE IF EXISTS usuario;

CREATE TABLE
  usuario (
    id bigint NOT NULL AUTO_INCREMENT,
    nombre_usuario varchar(100) NOT NULL,
    apellido_usuario varchar(150) DEFAULT NULL,
    email varchar(200) DEFAULT NULL,
    username varchar(45) NOT NULL,
    password varchar(255) NOT NULL,
    role_id bigint NOT NULL,
    activo tinyint NOT NULL DEFAULT '1',
    PRIMARY KEY (id),
    UNIQUE KEY username_UNIQUE (username),
    KEY fk_usuario_role_idx (role_id),
    CONSTRAINT fk_usuario_role FOREIGN KEY (role_id) REFERENCES role (id) ON DELETE RESTRICT ON UPDATE CASCADE
  );

--
-- Table structure for table topico
--
DROP TABLE IF EXISTS topico;

CREATE TABLE
  topico (
    id bigint NOT NULL AUTO_INCREMENT,
    titulo_topico varchar(200) NOT NULL,
    mensaje_topico text NOT NULL,
    fecha_creacion datetime NOT NULL,
    autor_id bigint NOT NULL,
    curso_id bigint NOT NULL,
    estatus_topico varchar(45) NOT NULL,
    num_respuestas int DEFAULT '0',
    PRIMARY KEY (id),
    KEY fk_topico_autor_idx (autor_id),
    KEY fk_topico_curso_idx (curso_id),
    CONSTRAINT fk_topico_autor FOREIGN KEY (autor_id) REFERENCES usuario (id) ON DELETE RESTRICT ON UPDATE CASCADE,
    CONSTRAINT fk_topico_curso FOREIGN KEY (curso_id) REFERENCES curso (id)
  );

--
-- Table structure for table respuesta
--
DROP TABLE IF EXISTS respuesta;

CREATE TABLE
  respuesta (
    id bigint NOT NULL AUTO_INCREMENT,
    mensaje_respuesta text NOT NULL,
    topico_id bigint NOT NULL,
    fecha_respuesta datetime NOT NULL,
    autor_id bigint NOT NULL,
    solucion tinyint (1) NOT NULL,
    PRIMARY KEY (id),
    KEY fk_respuesta_topico_idx (topico_id),
    KEY fk_respuesta_autor_idx (autor_id),
    CONSTRAINT fk_respuesta_autor FOREIGN KEY (autor_id) REFERENCES usuario (id) ON DELETE RESTRICT ON UPDATE CASCADE,
    CONSTRAINT fk_respuesta_topico FOREIGN KEY (topico_id) REFERENCES topico (id) ON DELETE RESTRICT ON UPDATE CASCADE
  );