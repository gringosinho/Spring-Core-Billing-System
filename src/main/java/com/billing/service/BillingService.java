package com.billing.service;

import com.billing.processor.PaymentProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * The core business logic service for handling client billing.
 * Annotated with @Service (a specialization of @Component) to indicate
 * that this class holds business rules and should be managed by the Spring IoC container.
 */
@Service
public class BillingService {

    // The dependency is marked 'final' to ensure immutability.
    // Once this service is created, its payment processor cannot be swapped out mid-flight.
    private final PaymentProcessor paymentProcessor;

    /**
     * Constructor Injection for mandatory dependencies.
     * * @param paymentProcessor Injected by Spring. We use @Qualifier("mockStripe")
     * to explicitly tell Spring WHICH implementation we want,
     * preventing a NoUniqueBeanDefinitionException.
     */
    @Autowired // Note: In modern Spring (4.3+), @Autowired is optional if there's only one constructor, but it's good for clarity.
    public BillingService(@Qualifier("mockStripe") PaymentProcessor paymentProcessor) {
        System.out.println("🔧 [CONTAINER] Injecting PaymentProcessor into BillingService via Constructor...");
        this.paymentProcessor = paymentProcessor;
    }

    /**
     * Executes the billing process for a client.
     * * @param clientName The name of the client being billed.
     * @param amount The total invoice amount.
     */
    public void billClient(String clientName, double amount) {
        System.out.println("\n📝 [BILLING SERVICE] Generating invoice for " + clientName);
        System.out.println("📝 [BILLING SERVICE] Total Amount Due: $" + amount);

        // The service doesn't know or care IF it's Stripe or Razorpay.
        // It just knows it has an object that follows the PaymentProcessor contract.
        boolean isSuccess = paymentProcessor.processPayment(amount, clientName);

        if (isSuccess) {
            System.out.println("🧾 [BILLING SERVICE] Invoice marked as PAID and closed.");
        } else {
            System.out.println("❌ [BILLING SERVICE] Payment failed. Initiating retry protocol.");
        }
    }
}