/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package core.models.storage;

import java.util.List;

// Interfaz para simular almacenamiento en storage
public interface Storage<T> {
    boolean add(T item);
    T get(String id);
    List<T> listAll();  // Método para listar todos los elementos
}