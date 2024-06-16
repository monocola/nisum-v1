# Evaluación: Aplicación de Registro y Autenticación de Usuarios
# Autor: Jorge Chilcón
# Fecha: 16-06-2024

Esta aplicación Java expone una API RESTful para la gestión de usuarios, permitiendo el registro y autenticación mediante endpoints JSON. 
La API devuelve respuestas JSON estructuradas según los requisitos especificados.

# Endpoints:
Registro de Usuario
Endpoint para registrar un nuevo usuario.

```
POST /api/auth/signUp
```
```json
{
        "name": "Juan Perez",
        "email": "juan@rodriguez.org",
        "password": "Lima2025$",
        "phones": [
            {
            "number": "1234567",
            "cityCode": "12",
            "countryCode": "60"
            }
        ]
}
```

# Respuesta Exitosa
```json
{
  "message": "Usuario registrado con éxito."
}
```
# Respuesta Fallida:
```json
{
  "message": "El correo ya registrado."
}
```
```json
{
  "message": "El formato del correo es inválido."
}
```
```json
{
  "message": "Error: La contraseña debe cumplir con las siguientes reglas: \nDebe contener al menos un dígito numérico. \nDebe contener al menos una letra minúscula. \nDebe contener al menos una letra mayúscula. \nDebe contener al menos uno de los siguientes caracteres especiales: @, #, $, %, ^, &, +, =. \nNo debe contener espacios en blanco. \nDebe tener una longitud de al menos 8 caracteres."
}
```
# Validaciones

1. El correo electrónico debe seguir un formato válido.
2. La contraseña debe tener al menos 8 caracteres, una letra mayúscula, una letra minúscula, un número y un carácter especial.

# Inicio de Sesión
Endpoint para iniciar sesión.

```
POST /api/auth/signIn
```
```json
{
  "email": "juan@rodriguez.org",    
    "password": "Lima2025$"
}
```
# Respuesta Exitosa
```json
{
    "id": "c0a81206-901f-1a35-8190-1f726bd90000",
    "email": "juan@rodriguez.org",
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqdWFuQHJvZHJpZ3Vlei5vcmciLCJpYXQiOjE3MTg1MTQ1MTMsImV4cCI6MTcxODYwMDkxM30.dFXvONVyg_2kh41m2jJND088lM6odO8nlWusoxZD-5I",
    "created": "2024-06-16T05:08:30.735+00:00",
    "modified": "2024-06-16T05:08:30.735+00:00",
    "last_login": "2024-06-16T05:08:30.735+00:00",
    "isactive": true
}
```
# Respuesta Fallida
```json
{
  "message": "Solicitud incorrecta."
}
```
# Tecnologías Utilizadas
1. Java 17
2. Spring Boot 3.3.0
3. Maven
4. Swagger para documentación de la API
5. Programación Funcional.
6. Base de Datos en Memoria H2.
7. Buenas practicas de programación (clean Code).

# Patrones de Diseño Utilizados
1. Strategy
2. Builder
3. Dto
4. Factory
5. AAA (Arrange, Act, Assert) para pruebas unitarias realizadas al 83% de cobertura.
6. Solid

# Ejecución
Para ejecutar la aplicación:
1. Clona el repositorio.
2. Importa el proyecto en tu IDE preferido.
3. Ejecuta la aplicación desde tu IDE o mediante Maven con el comando mvn spring-boot:run.

# Documentación de la API:
1. Accede a la documentación de la API en http://localhost:8080/doc/swagger-ui/index.html
2. Prueba los endpoints desde Swagger.
3. Se ha documentado cada endpoint con su respectiva descripción, parámetros y respuestas.

# Diagrama de Solucion:
![diagrama_solucion.png](images/diagrama_solucion.png)

# Autor
Creado por Jorge Chilcón.