package com.billing.processor;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("mockRazorpay") // A different specific ID
public class MockRazorpayProcessor implements PaymentProcessor {

    @Override
    public boolean processPayment(double amount, String clientName) {
        System.out.println("🛡️ [MOCK RAZORPAY] Initializing dummy Razorpay gateway...");
        try{
            Thread.sleep(1000);
        }catch(InterruptedException e){
            throw new RuntimeException(e);
        }
        System.out.println("₹ [MOCK RAZORPAY] Capturing payment of $" + amount + " from " + clientName);
        try{
            Thread.sleep(1000);
        }catch(InterruptedException e){
            throw new RuntimeException(e);
        }
        System.out.println("✅ [MOCK RAZORPAY] Transaction Complete!");
        return true;
    }
}
