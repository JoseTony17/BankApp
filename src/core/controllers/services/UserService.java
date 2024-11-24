/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers.services;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.controllers.validate.UserValidation;
import core.models.User;
import core.models.storage.StorageManager;
import core.models.storage.UserStorage;
import java.util.List;

// Clase para manejar las operaciones relacionadas con los usuarios
public class UserService {

    public Response registerUser(String id, String firstname, String lastname, String age) {
        try {// Validar los datos del usuario
            Response validateID = UserValidation.validateUserId(id);
            Response validateName = UserValidation.validateName(firstname, lastname);
            Response validateAge = UserValidation.validateAge(age);

            if (validateID != null || validateName != null || validateAge != null) {
                // Retorna cual fue el error
                return validateID != null ? validateID : validateName != null ? validateName : validateAge;

            }
            // Si los datos son válidos
            int idInt = Integer.parseInt(id);
            int ageInt = Integer.parseInt(age);

            // Obtener la instancia de StorageManager
            StorageManager storageManager = StorageManager.getInstance();
            UserStorage storage = storageManager.getUserStorage();

            // Agregar el nuevo usuario
            if (!storage.add(new User(idInt, firstname, lastname, ageInt))) {
                // El ID del usuario ya existe en el almacenamiento
                return new Response(
                        "There is already a user with id " + id
                                + ". Please check your data, the id must be unique.",
                        Status.BAD_REQUEST);
            }

            return new Response("User with id: " + id + " created successfully", Status.CREATED);

        } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }

    // Listar todos los usuarios del almacenamiento
    public Response listUsers() {
        try {// Obtener la instancia de StorageManager
            StorageManager storageManager = StorageManager.getInstance();
            UserStorage storage = storageManager.getUserStorage();

            List<User> users = storage.listAll();

            if (users.isEmpty()) {
                // No se encontraron usuarios reigistrados
                return new Response("There are no registered users so far.", Status.NO_CONTENT);
            }

            // Ordenar la lista de usuarios por ID ascendente
            users.sort((obj1, obj2) -> (obj1.getId() - obj2.getId()));

            return new Response("List of Users Updated", Status.OK, users);

        } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }

    }
}
