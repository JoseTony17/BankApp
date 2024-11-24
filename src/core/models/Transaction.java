/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models;

import core.models.Account;

public class Transaction {

    private TransactionType type;
    private Account sourceAccount;
    private Account destinationAccount;
    private double amount;
    private String date;

    public Transaction(TransactionType type, Account sourceAccount, Account destinationAccount,  double amount, String date) {
        this.type = type;
        this.sourceAccount = sourceAccount;
        this.destinationAccount = destinationAccount;
        this.amount = amount;
        this.date = date;

    }

    public TransactionType getType() {
        return type;
    }

    public Account getSourceAccount() {
        return sourceAccount;
    }

    public Account getDestinationAccount() {
        return destinationAccount;
    }

    public double getAmount() {
        return amount;
    }
    
    public String getDate() {
        return date;
    }

}
