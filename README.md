Esta aplicación API RESTful para la gestión de usuarios, permitiendo la creación y validación de usuarios mediante endpoints JSON. 
La API devuelve respuestas JSON estructuradas según los requisitos especificados.

Endpoints:
- Registro de Usuario: /api/auth/signup
 Endpoint para registrar un nuevo usuario.
    Body
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

    Respuesta Exitosa:
    {
        "message": "Usuario registrado con éxito."
    }

    Respuesta Fallida:
    {
        "message": "El correo ya registrado."
    }

    {
        "mensaje": "Formato de correo electrónico incorrecto"
    }

    Validaciones
    El correo electrónico debe seguir un formato válido.
    La contraseña debe tener al menos 8 caracteres, una letra mayúscula, una letra minúscula, un número y un carácter especial.
    
    Login de Usuario: /api/auth/signin
    Endpoint para iniciar sesión en la aplicación.
    Body
    {
        "email": "juan@rodriguez.org",
        "password": "Lima2025$"
    }

    Tecnologías Utilizadas
    Java
    Spring Boot
    Maven
    Swagger para documentación de la API 
    
    Patrones de Diseño Utilizados:
    - Strategy
    -  builder
    - se aplicó patron AAA (Arrange, Act, Assert) en los test unitarios.

    Ejecución
    Para ejecutar la aplicación:
    
    Clona el repositorio.
    Importa el proyecto en tu IDE preferido.
    Ejecuta la aplicación desde tu IDE o mediante Maven con el comando mvn spring-boot:run.
    
    Documentación:
    La documentación de la API se encuentra en la siguiente URL: http://localhost:8080/swagger-ui.html
    
    Autor
    Creado por Jorge Chilcón.
