# Foro Hub

## Índice
1. [Descripción](#descripción)
2. [Tecnologías utilizadas](#tecnologías-utilizadas)
3. [Estructura del Proyecto](#estructura-del-proyecto)
4. [Endpoints](#endpoints)
   - [Autenticación](#autenticación)
   - [Gestión de Tópicos](#gestión-de-tópicos)
   - [Gestión de Respuestas](#gestión-de-respuestas)
   - [Gestión de Usuarios](#gestión-de-usuarios)
5. [Diagrama de la Base de Datos](#diagrama-de-la-base-de-datos)
6. [Configuración del Proyecto](#configuración-del-proyecto)
   - [Configuración de la Base de Datos](#configuración-de-la-base-de-datos)
   - [Ejecutar el Proyecto](#ejecutar-el-proyecto)
7. [Contribuciones](#contribuciones)
8. [Licencia](#licencia)
9. [Acerca de mí](#acerca-de-mí)

## Descripción
Este proyecto es un desafío de Alura implementado con Spring Boot, que permite gestionar un foro de discusión. Los usuarios pueden interactuar con temas, responder y gestionar sus perfiles, y los administradores pueden realizar acciones como agregar cursos y gestionar usuarios. El proyecto emplea autenticación de usuarios y está respaldado por una base de datos MySQL.

## Tecnologías utilizadas
- **Spring Boot**: Framework principal para el desarrollo del backend.
- **Spring Security**: Para la autenticación y autorización de usuarios.
- **Spring Data JPA**: Para interactuar con la base de datos de forma sencilla y eficiente.
- **MySQL**: Sistema de gestión de bases de datos relacional.
- **Flyway**: Herramienta para la migración de bases de datos.
- **Lombok**: Para reducir la cantidad de código repetitivo.
- **JWT (JSON Web Tokens)**: Para la autenticación basada en tokens.

## Estructura del Proyecto
El proyecto está organizado en varios paquetes, cada uno responsable de una parte del sistema:

- **Controller**: Contiene las clases que gestionan las solicitudes HTTP y la lógica del negocio.
- **Service**: Contiene la lógica del negocio.
- **Repository**: Contiene las interfaces que interactúan con la base de datos.
- **Model**: Contiene las clases que representan las entidades del sistema.
- **Security**: Contiene la configuración de seguridad para manejar la autenticación y autorización de usuarios.

## Endpoints

### Autenticación
- **POST /login**: Autenticación de usuarios.
  - **Request body**: 
    ```json
    {
      "email": "usuario@dominio.com",
      "password": "contraseña"
    }
    ```
  - **Response**: Retorna un JWT válido para el usuario autenticado.

### Gestión de Tópicos
- **GET /topics**: Obtener todos los tópicos.
- **GET /topics/{id}**: Obtener un tópico por su ID.
- **POST /topics**: Crear un nuevo tópico.
  - **Request body**:
    ```json
    {
      "titulo": "Título del Tópico",
      "mensaje": "Mensaje del tópico",
      "curso_id": 1
    }
    ```
- **PUT /topics/{id}**: Actualizar un tópico.
  - **Request body**:
    ```json
    {
      "titulo": "Nuevo título",
      "mensaje": "Nuevo mensaje",
      "estado": "ACTIVO"
    }
    ```
- **DELETE /topics/{id}**: Eliminar un tópico.

### Gestión de Respuestas
- **GET /responses**: Obtener todas las respuestas.
- **GET /responses/{id}**: Obtener una respuesta por su ID.
- **POST /responses**: Crear una nueva respuesta.
  - **Request body**:
    ```json
    {
      "mensaje": "Respuesta al tópico",
      "topico_id": 1,
      "solucion": false
    }
    ```
- **PUT /responses/{id}**: Actualizar una respuesta.
  - **Request body**:
    ```json
    {
      "mensaje": "Respuesta modificada",
      "solucion": true
    }
    ```
- **DELETE /responses/{id}**: Eliminar una respuesta.

### Gestión de Usuarios
- **GET /users**: Obtener todos los usuarios.
- **GET /users/{id}**: Obtener un usuario por su ID.
- **POST /users**: Crear un nuevo usuario.
  - **Request body**:
    ```json
    {
      "nombre": "Nombre del Usuario",
      "email": "usuario@dominio.com",
      "contrasenha": "contraseña",
      "perfil_id": 1
    }
    ```
- **PUT /users/{id}**: Actualizar un usuario.
  - **Request body**:
    ```json
    {
      "nombre": "Nuevo nombre",
      "email": "nuevo@dominio.com"
    }
    ```
- **DELETE /users/{id}**: Eliminar un usuario.

## Diagrama de la Base de Datos
A continuación se muestra el diagrama de la base de datos para el proyecto:

![Diagrama de la Base de Datos](./docs/db-diagram.png)

## Configuración del Proyecto

### Configuración de la Base de Datos
Asegúrate de tener MySQL instalado y configurado correctamente. Usa las siguientes variables de entorno para la configuración en el archivo `application.properties` o `application.yml`:

```properties
spring.application.name=foro-hub
spring.datasource.url=jdbc:mysql://localhost:3306/${DB_Name}
spring.datasource.username=${DB_UserName}
spring.datasource.password=${DB_Password}
api.security.secret=${JWT_SECRET}
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
