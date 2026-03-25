package com.billing.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * A dedicated component responsible for setting up the database schema.
 * This runs automatically when the Spring IoC container boots up.
 */
@Component
public class DatabaseInitializer {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DatabaseInitializer(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * The @PostConstruct annotation guarantees this method is executed exactly ONCE
     * after Spring has fully instantiated this bean and injected the JdbcTemplate.
     */
    @PostConstruct
    public void setupSchema() {
        System.out.println("🧱 [DATABASE] Running schema initialization...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // We are using 'SERIAL', which is a native PostgreSQL data type for auto-incrementing IDs.
        // H2 is smart enough to understand PostgreSQL dialects, making local testing a breeze!
        String sql = """
            CREATE TABLE IF NOT EXISTS billing_records (
                id SERIAL PRIMARY KEY,
                client_name VARCHAR(255) NOT NULL,
                amount DECIMAL(10, 2) NOT NULL,
                status VARCHAR(50) NOT NULL,
                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
            );
        """;

        // Execute the raw DDL (Data Definition Language) query
        jdbcTemplate.execute(sql);

        System.out.println("✅ [DATABASE] 'billing_records' table is ready and verified!");
    }
}