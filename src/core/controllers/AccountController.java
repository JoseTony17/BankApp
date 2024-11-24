package core.controllers;

import core.controllers.services.AccountService;
import core.controllers.utils.Response;

// Controlador de cuentas
public class AccountController {

    // Instancia del servicio para gestionar las cuentas
    private AccountService accountService;

    public AccountController() {
        this.accountService = new AccountService();
    }

    // Crear una cuenta
    public Response createAccount(String userId, String initialBalance) {
        // Llamar al servicio para crear la cuenta
        return accountService.createAccount(userId, initialBalance);
    }

    // Listar todas las cuentas
    public Response listAccounts() {
        return accountService.listAccounts();
    }
}