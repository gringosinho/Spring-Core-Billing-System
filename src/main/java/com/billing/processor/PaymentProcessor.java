package com.billing.processor;

public interface PaymentProcessor {
    /*
        * Process a payment for a given amount and client name.
        * @param amount The amount to be processed.
        * @param ClientName The name of the client making the payment.
        * @return true if the payment was successful, false otherwise.
     */
    boolean processPayment(double amount, String ClientName);
}
