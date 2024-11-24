/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.services.TransactionService;
import core.controllers.services.TransactionServiceImpl;
import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.storage.AccountStorage;
import core.models.storage.StorageManager;
import core.models.storage.TransactionStorage;

public class TransactionController {

    private TransactionService transactionService;

    public TransactionController() {
        // Inicializa el almacenamiento y el servicio de transacciones internamente
        AccountStorage accountStorage = StorageManager.getInstance().getAccountStorage();
        TransactionStorage transactionStorage = StorageManager.getInstance().getTransactionStorage();
        this.transactionService = new TransactionServiceImpl(accountStorage, transactionStorage);
    }

    public Response executeTransaction(String type, String sourceAccountId, String destinationAccountId,
            String amount) {

        // Lógica de decisión de transacción
        switch (type.toUpperCase()) {
            case "DEPOSIT":
                return transactionService.deposit(destinationAccountId, amount);
            case "WITHDRAW":
                return transactionService.withdraw(sourceAccountId, amount);
            case "TRANSFER":
                return transactionService.transfer(sourceAccountId, destinationAccountId, amount);
            default:
                return new Response("Invalid transaction type", Status.BAD_REQUEST);
        }
    }

    public Response listTransactions() {
        return transactionService.listTransactions();

    }
}