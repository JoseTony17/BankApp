/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storage;

import core.models.User;
import java.util.ArrayList;
import java.util.List;

public class UserStorage implements Storage<User> {

    // Lista para almacenar usuarios
    private ArrayList<User> users = new ArrayList<>();

    @Override
    public boolean add(User user) {
        // agregar un usuario
        for (User u : users) {
            if (u.getId() == user.getId()) {
                return false; // El usuario ya existe
            }
        }
        users.add(user); // Agregar el nuevo usuario
        return true; // Usuario agregado con éxito
    }

    @Override
    public User get(String id) {
        try {
            int userID = Integer.parseInt(id);
            // obtener un usuario por ID
            for (User user : users) {
                if (user.getId() == userID) {
                    return user; // Usuario encontrado
                }
            }
        } catch (NumberFormatException exc) {
            return null;
        }
        return null; // Usuario no encontrado
    }

    @Override
    public List<User> listAll() {
        // listar todos los usuarios
        return new ArrayList<>(users); // Retorna una copia de la lista de usuarios
    }

}
