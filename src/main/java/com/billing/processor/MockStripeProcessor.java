package com.billing.processor;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("mockStripe") // We explicitly name this bean so we can ask for it later
public class MockStripeProcessor implements PaymentProcessor {

    @Override
    public boolean processPayment(double amount, String clientName) {
        System.out.println("💳 [MOCK STRIPE] Connecting to simulated Stripe API...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("💵 [MOCK STRIPE] Processing $" + amount + " for " + clientName);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("✅ [MOCK STRIPE] Payment Successful!");
        return true;
    }
}