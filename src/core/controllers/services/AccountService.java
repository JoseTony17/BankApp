/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers.services;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.controllers.validate.AccountValidation;
import core.controllers.validate.UserValidation;
import core.models.Account;
import core.models.User;
import core.models.storage.AccountStorage;
import core.models.storage.StorageManager;
import core.models.storage.UserStorage;
import java.util.List;
import java.util.Random;

// Servicio para realizar operaciones relacionadas con cuentas
public class AccountService {

    // Método para crear una cuenta
    public Response createAccount(String userId, String initialBalance) {

        try {// Validaciones
            Response userIdValidation = UserValidation.validateUserId(userId);
            Response initialBalanceValidation = AccountValidation.validateInitialBalance(initialBalance);
            if (userIdValidation != null || initialBalanceValidation != null) {
                return userIdValidation != null ? userIdValidation : initialBalanceValidation;
            }
            // Si ambas validaciones son exitosas, se procede a crear la cuenta
            // Obtener la instancia de StorageManager
            StorageManager storageManager = StorageManager.getInstance();
            UserStorage userStorage = storageManager.getUserStorage();
            AccountStorage accountStorage = storageManager.getAccountStorage();

            // Validar que el usuario exista
            User user = userStorage.get(userId);
            if (user == null) {
                return new Response("User with id: " + userId + " is not registered. Please register first.",
                        Status.NOT_FOUND);
            }
            // Generar un ID de cuenta único
            String accountId = generateUniqueAccountId();
            // Crear la cuenta
            Account newAccount = new Account(accountId, user, Double.parseDouble(initialBalance));
            accountStorage.add(newAccount);

            return new Response("Account with id: " + accountId + " created successfully", Status.CREATED);

        } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }

    private String generateUniqueAccountId() {
        try {
            StorageManager storageManager = StorageManager.getInstance();
            AccountStorage accountStorage = storageManager.getAccountStorage();
            Random random = new Random();
            String accountId;
            do {
                int first = random.nextInt(1000);
                int second = random.nextInt(1000000);
                int third = random.nextInt(100);

                accountId = String.format("%03d", first) + "-" + String.format("%06d", second) + "-"
                        + String.format("%02d", third);

            } while (accountStorage.get(accountId) != null); // Asegurarse de que el ID sea único
            return accountId;
        } catch (Exception ex) {
            return null;
        }
    }

    public Response listAccounts() {
        try {// Obtener la instancia de StorageManager
            StorageManager storageManager = StorageManager.getInstance();
            AccountStorage accountStorage = storageManager.getAccountStorage();

            List<Account> accounts = accountStorage.listAll();
            if (accounts.isEmpty()) {
                return new Response("There are no registered accounts so far.", Status.NO_CONTENT);
            }
            accounts.sort((obj1, obj2) -> (obj1.getId().compareTo(obj2.getId())));

            return new Response("List of Accounts updated", Status.OK, accounts);
        } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }
}
