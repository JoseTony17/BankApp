/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storage;

import core.models.Transaction;
import java.util.ArrayList;
import java.util.List;

public class TransactionStorage implements Storage<Transaction> {

    // lista de transacciones
    private ArrayList<Transaction> transactions = new ArrayList<>();

    @Override
    public boolean add(Transaction transaction) {
        // agregar una transacción
        return transactions.add(transaction);
    }

    @Override
    public List<Transaction> listAll() {
        // listar todas las transacciones
        return new ArrayList<>(transactions); // Retorna una copia de la lista de transacciones
    }

    @Override
    public Transaction get(String id) {
        // TODO Auto-generated method stub
        // obtener una transacción por ID no es necesario pero debe estar por la
        // interfaz
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }
}