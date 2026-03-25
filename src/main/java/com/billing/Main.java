package com.billing;

import com.billing.config.AppConfig;
import com.billing.service.BillingService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * The manual bootstrap entry point for our Pure Spring Core application.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("🚀 Booting up Enterprise Spring IoC Container...\n");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        // 1. Initialize the Container using our AppConfig blueprint
        // This triggers component scanning, property loading, and bean creation.
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        System.out.println("\n✅ Container is Ready! Executing Business Logic...\n");
        try{
            Thread.sleep(1000);
        }catch(InterruptedException e){
            throw new RuntimeException(e);
        }

        // 2. Fetch the fully wired BillingService from the container
        BillingService billingService = context.getBean(BillingService.class);

        // 3. Run the application logic
        billingService.billClient("Wayne Enterprises", 25000.50);

        // 4. Gracefully shut down the container
        // This ensures things like our HikariCP database pool close their connections cleanly.
        System.out.println("\n🛑 Shutting down container...");
        context.close();
    }
}