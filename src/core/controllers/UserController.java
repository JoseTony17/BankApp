/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.services.UserService;
import core.controllers.utils.Response;

// Controlador para manejar las operaciones correspondientes a usuarios
public class UserController {

    // Instancia del servicio para manejar las operaciones
    private UserService userService;

    public UserController() {
        this.userService = new UserService();
    }

    // Operación para registrar un nuevo usuario
    public Response register(String id, String firstname, String lastname, String age) {
        // Llamada al servicio para registrar el usuario
        return userService.registerUser(id, firstname, lastname, age);
    }

    // Operación para listar todos los usuarios
    public Response list() {
        // Llamada al servicio para obtener la lista de usuarios
        return userService.listUsers();
    }
}