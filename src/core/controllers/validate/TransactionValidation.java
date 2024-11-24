/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers.validate;

import core.controllers.utils.Response;
import core.controllers.utils.Status;

public class TransactionValidation {

    public Response validateAmount(String amount) {
        try {
            Double amountD = Double.parseDouble(amount);
            if (amountD <= 0) {
                return new Response(
                        "The amount must be greater than zero. To complete the transaction, enter a valid amount.",
                        Status.BAD_REQUEST);
            }
        } catch (NumberFormatException ex) {
            return new Response("The amount must be a number. To complete the transaction, enter a valid amount.",
                    Status.BAD_REQUEST);
        }
        return null; // Validación exitosa
    }
}
