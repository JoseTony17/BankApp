/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers.validate;

import core.controllers.utils.Response;
import core.controllers.utils.Status;

// Clase de validación de usuarios. Sirve para separar la logica de validación de datos de la creación de usuarios
public class UserValidation {

    // Método para validar un id de usuario
    public static Response validateUserId(String userId) {
        int userIdInt;
        if (userId == null || userId.isEmpty()) {
            return new Response("The user id must not be empty. Please enter a correct ID.", Status.BAD_REQUEST);
        }
        if (userId.length() > 9) {
            return new Response("The user ID must not be more than nine digits long. Please enter a valid ID.",
                    Status.BAD_REQUEST);
        }
        try {
            userIdInt = Integer.parseInt(userId);
            if (userIdInt < 0) {
                return new Response("The user id must be greater than or equal to zero. Please enter a correct ID.",
                        Status.BAD_REQUEST);
            }
        } catch (NumberFormatException ex) {
            return new Response("The user id must be numeric. Please enter a correct ID.", Status.BAD_REQUEST);
        }
        return null; // Validación exitosa
    }

    public static Response validateName(String firstname, String lastname) {
        // Validación del nombre
        if (firstname == null || firstname.trim().isEmpty()) {
            return new Response("FirstName cannot be empty. Please enter a valid name.", Status.BAD_REQUEST);
        }

        // Validación del apellido
        if (lastname == null || lastname.trim().isEmpty()) {
            return new Response("Lastname cannot be empty. Please enter a valid lastname.", Status.BAD_REQUEST);
        }

        return null; // Validación exitosa
    }

    public static Response validateAge(String age) {
        // Validación de la edad
        int ageInt;
        try {
            ageInt = Integer.parseInt(age);
            if (ageInt < 18) {
                return new Response("Age must be 18 years or older. Please enter a valid age.", Status.BAD_REQUEST);
            }
        } catch (NumberFormatException ex) {
            return new Response("Age must be numeric. Please enter a valid age.", Status.BAD_REQUEST);
        }
        return null; // Validación exitosa
    }
}
