package com.project.nisum.app.utils;

/**
 * Constant is a utility class that contains constant values for messages used throughout the application.
 */
public class Constant {
    /**
     * Error message indicating that the provided email is already in use.
     */
    public static final String MESSAGE_EMAIL_EXISTS = "Error: El correo ya registrado.";

    /**
     * Error message indicating that the provided email has an invalid pattern.
     */
    public static final String MESSAGE_EMAIL_PATTERN = "Error: El formato del correo es inválido.";

    /**
     * Success message indicating that the user has been registered successfully.
     */
    public static final String MESSAGE_USER_REGISTERED = "Usuario registrado con éxito.";

    /**
     * Error message indicating that the provided password does not meet the required criteria.
     * The password must:
     * - Contain at least one numeric digit.
     * - Contain at least one lowercase letter.
     * - Contain at least one uppercase letter.
     * - Contain at least one of the following special characters: @, #, $, %, ^, &, +, =.
     * - Not contain any whitespace.
     * - Be at least 8 characters long.
     */
    public static final String MESSAGE_PASSWORD_PATTERN = "Error: La contraseña debe cumplir con las siguientes reglas: \n"
            + "Debe contener al menos un dígito numérico. \n"
            + "Debe contener al menos una letra minúscula. \n"
            + "Debe contener al menos una letra mayúscula. \n"
            + "Debe contener al menos uno de los siguientes caracteres especiales: @, #, $, %, ^, &, +, =. \n"
            + "No debe contener espacios en blanco. \n"
            + "Debe tener una longitud de al menos 8 caracteres.";

    /**
     * Error message indicating that the provided email is already in use.
     */
    public static final String MESSAGE = "Mensaje:";
    public static final String MESSAGE_BAD_CREDENTIALS = "Solicitud incorrecta.";
}