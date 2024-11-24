/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers.services;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.controllers.validate.TransactionValidation;
import core.models.Account;
import core.models.Transaction;
import core.models.TransactionType;
import core.models.storage.AccountStorage;
import core.models.storage.TransactionStorage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;

public class TransactionServiceImpl implements TransactionService {

    private final AccountStorage accountStorage;
    private final TransactionStorage transactionStorage; // Agregar almacenamiento de transacciones
    private final TransactionValidation transactionValidator;

    public TransactionServiceImpl(AccountStorage accountStorage, TransactionStorage transactionStorage) {
        this.accountStorage = accountStorage;
        this.transactionStorage = transactionStorage; // Inicializar almacenamiento de transacciones
        this.transactionValidator = new TransactionValidation(); // Instancia del validador
    }

    // Implementación de los métodos. Metodo para deposito
    @Override
    public Response deposit(String accountId, String amount) {
        try {
            Response validationResponse = transactionValidator.validateAmount(amount);
            if (validationResponse != null) {
                return validationResponse; // Retorna error si la validación falla
            }

            Account account = accountStorage.get(accountId);
            if (account == null) {
                return new Response("Account destination not found. Please enter a correct destination account",
                        Status.NOT_FOUND);
            }

            account.deposit(Double.parseDouble(amount));

            // Obtener la fecha y hora actual
            LocalDateTime now = LocalDateTime.now();
            // Definir el formato deseado
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm:ss");
            // Formatear la fecha y hora a String
            String date = now.format(formatter);

            // Guardar la transacción
            transactionStorage
                    .add(new Transaction(TransactionType.DEPOSIT, null, account, Double.parseDouble(amount), date));
            return new Response("Deposit was made successfully", Status.OK);

        } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }

    // Metodo para retiro
    @Override
    public Response withdraw(String accountId, String amount) {
        try {
            Response validationResponse = transactionValidator.validateAmount(amount);
            if (validationResponse != null) {
                return validationResponse; // Retorna error si la validación falla
            }

            Account account = accountStorage.get(accountId);
            if (account == null) {
                return new Response("Account source not found. Please enter a correct source account",
                        Status.NOT_FOUND);
            }

            if (!account.withdraw(Double.parseDouble(amount))) {
                return new Response("Insufficient balance in source account to carry out the operation",
                        Status.BAD_REQUEST);
            }

            // Obtener la fecha y hora actual
            LocalDateTime now = LocalDateTime.now();
            // Definir el formato deseado
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm:ss");
            // Formatear la fecha y hora a String
            String date = now.format(formatter);

            // Guardar la transacción
            transactionStorage
                    .add(new Transaction(TransactionType.WITHDRAW, account, null, Double.parseDouble(amount), date));
            return new Response("Withdrawal was made successfuly", Status.OK);

        } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }

    // Metodo para transferencia
    @Override
    public Response transfer(String sourceAccountId, String destinationAccountId, String amount) {
        try {
            Response validationResponse = transactionValidator.validateAmount(amount);
            if (validationResponse != null) {
                return validationResponse; // Retorna error si la validación falla
            }

            Account sourceAccount = accountStorage.get(sourceAccountId);
            Account destinationAccount = accountStorage.get(destinationAccountId);

            if (sourceAccount == null) {
                return new Response("Account source not found. Please enter a correct source account",
                        Status.NOT_FOUND);
            }

            if (destinationAccount == null) {
                return new Response("Account destination not found. Please enter a correct destination account",
                        Status.NOT_FOUND);
            }

            if (!sourceAccount.withdraw(Double.parseDouble(amount))) {
                return new Response("Insufficient funds in source account", Status.BAD_REQUEST);
            }

            // Obtener la fecha y hora actual
            LocalDateTime now = LocalDateTime.now();
            // Definir el formato deseado por ejemplo 24/11/24 01:31:00
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm:ss");
            // Formatear la fecha y hora a String
            String date = now.format(formatter);

            destinationAccount.deposit(Double.parseDouble(amount));
            // Guardar la transacción
            transactionStorage.add(new Transaction(TransactionType.TRANSFER, sourceAccount, destinationAccount,
                    Double.parseDouble(amount), date));

            return new Response("Transfer was made successfully", Status.OK);
        } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Response listTransactions() {
        try {
            // Listar todas las transacciones
            List<Transaction> transactions = transactionStorage.listAll();
            if (transactions.isEmpty()) {
                return new Response("No transactions found", Status.NO_CONTENT);
            }
            // Ordenar las transacciones por fecha de las mas recientes a las mas antiguas
            transactions.sort(Comparator.comparing(Transaction::getDate).reversed());

            return new Response("List ofTransactions updated", Status.OK, transactions);

        } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }
}
