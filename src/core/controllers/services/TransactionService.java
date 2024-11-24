/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package core.controllers.services;

import core.controllers.utils.Response;

// Interface para el servicio de transacciones que implementará la clase TransactionServiceImpl para los métodos de deposito, retiro y transferencia
public interface TransactionService {
    Response deposit(String accountId, String amount);

    Response withdraw(String accountId, String amount);

    Response transfer(String sourceAccountId, String destinationAccountId, String amount);

    Response listTransactions();
}