/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storage;

import java.util.ArrayList;
import java.util.List;

import core.models.Account;

public class AccountStorage implements Storage<Account> {

    // lista para almacenar las cuentas
    private ArrayList<Account> accounts = new ArrayList<>();

    @Override
    public boolean add(Account account) {
        // agregar una cuenta
        for (Account a : accounts) {
            if (a.getId().equals(account.getId())) {
                return false; // La cuenta ya existe
            }
        }
        accounts.add(account); // Agregar nueva Cuenta
        return true; // Cuenta agregada con éxito
    }

    @Override
    public Account get(String id) {
        // Buscar la cuenta por ID
        for (Account account : accounts) {
            if (account.getId().equals(id)) {
                return account; // Cuenta encontrada
            }
        }
        return null; // Cuenta no encontrada
    }

    @Override
    public List<Account> listAll() {
        // listar todas las cuentas
        return new ArrayList<>(accounts); // Retorna una copia de la lista de cuentas
    }

}
