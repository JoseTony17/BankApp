/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers.validate;

import core.controllers.utils.Response;
import core.controllers.utils.Status;

// Clase de validación de cuentas. Sirve para separar la logica de validación de datos de la creación de cuentas
public class AccountValidation {

    // Método para validar el saldo inicial
    public static Response validateInitialBalance(String initialBalance) {
        double balance;
        try {
            balance = Double.parseDouble(initialBalance);

            if (balance < 0) {
                return new Response(
                        "The initial account balance cannot be less than 0. Please enter a correct balance.",
                        Status.BAD_REQUEST);
            }
        } catch (NumberFormatException ex) {
            return new Response("The account's opening balance must be number. Please enter a correct balance.",
                    Status.BAD_REQUEST);
        }
        return null; // Validación exitosa
    }
}
