/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storage;

public class StorageManager {

    // Instancia Singleton
    private static StorageManager instance;

    // Atributos de almacenamiento para usuarios, cuentas y transacciones
    private UserStorage userStorage;
    private AccountStorage accountStorage;
    private TransactionStorage transactionStorage;

    private StorageManager() {
        // Inicializar los almacenamientos
        this.userStorage = new UserStorage();
        this.accountStorage = new AccountStorage();
        this.transactionStorage = new TransactionStorage();
    }

    public static StorageManager getInstance() {
        if (instance == null) {
            instance = new StorageManager();
        }
        return instance;
    }

    // Getters de los almacenamientos
    public UserStorage getUserStorage() {
        return userStorage;
    }

    public AccountStorage getAccountStorage() {
        return accountStorage;
    }

    public TransactionStorage getTransactionStorage() {
        return transactionStorage;
    }
}