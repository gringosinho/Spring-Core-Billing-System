package com.billing.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * Main configuration class for the Spring IoC Container.
 * This class replaces the traditional applicationContext.xml file.
 * It bootstraps the application by defining component scanning, property sources,
 * and manually configured infrastructure beans like the database connection pool.
 */
@Configuration
@ComponentScan(basePackages = "com.billing")
@PropertySource(value = "classpath:application.yml", factory = YamlPropertySourceFactory.class)
public class AppConfig {

    // --- Field Injection for Externalized Properties ---
    // Spring uses reflection to inject these values from application.properties
    // before any @Bean methods are called.

    @Value("${db.url}")
    private String dbUrl;

    @Value("${db.username}")
    private String dbUsername;

    @Value("${db.password}")
    private String dbPassword;

    /**
     * Manually configures the HikariCP Connection Pool.
     * In XML, this would be a <bean id="dataSource" class="...HikariDataSource">.
     * * @return A fully initialized HikariDataSource managed as a Singleton by Spring.
     */
    @Bean
    public DataSource dataSource() {
        System.out.println("⚙️ [CONTAINER] Initializing HikariCP Database Connection Pool...");
        try{
            Thread.sleep(1000);
        }catch(InterruptedException e){
            throw new RuntimeException(e);
        }

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(dbUrl);
        config.setUsername(dbUsername);
        config.setPassword(dbPassword);

        // Essential connection pool tuning (Engineering best practices)
        config.setMaximumPoolSize(10); // Don't overwhelm the database with idle connections
        config.setMinimumIdle(2);      // Keep at least 2 connections ready for immediate use
        config.setConnectionTimeout(30000); // Wait max 30 seconds for a connection before failing

        return new HikariDataSource(config);
    }

    /**
     * Configures the Spring JdbcTemplate.
     * JdbcTemplate is a thread-safe, stateless class that handles the boilerplate
     * of raw JDBC (opening connections, creating prepared statements, and translating SqlExceptions).
     * * @param dataSource Injected automatically by Spring from the dataSource() bean above.
     * @return A configured JdbcTemplate ready for repository injection.
     */
    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        System.out.println("⚙️ [CONTAINER] Wiring JdbcTemplate with DataSource...");
        try{
            Thread.sleep(1000);
        }catch(InterruptedException e){
            throw new RuntimeException(e);
        }
        return new JdbcTemplate(dataSource);
    }
}