# 🚀 Enterprise Billing & Notification Engine (Pure Spring Core)

![Java](https://img.shields.io/badge/Java-17-orange?style=flat&logo=java&logoColor=white)
![Spring](https://img.shields.io/badge/Spring_Core-6.1.4-6DB33F?style=flat&logo=spring&logoColor=white)
![Database](https://img.shields.io/badge/Database-H2%20%7C%20PostgreSQL-336791?style=flat&logo=postgresql&logoColor=white)
![Build](https://img.shields.io/badge/Build-Maven-C71A36?style=flat&logo=apache-maven&logoColor=white)
![Architecture](https://img.shields.io/badge/Architecture-IoC%20%7C%20DI-8A2BE2?style=flat)

An enterprise-grade, standalone backend architecture built entirely with **Pure Spring Core**.

This project strips away the "magic" and auto-configuration of Spring Boot to demonstrate a deep, foundational understanding of the Spring Inversion of Control (IoC) container, manual Dependency Injection, and low-level component wiring.

## 🧠 Core Engineering Concepts Demonstrated

Instead of relying on starter dependencies, this project was engineered from the ground up to showcase:

* **Manual Container Bootstrapping:** Utilizing `AnnotationConfigApplicationContext` to instantiate the Spring engine and load configurations without XML.
* **Strict Dependency Injection:** * **Constructor Injection:** Used for mandatory, immutable dependencies (e.g., wiring the `PaymentProcessor` into the `BillingService`).
    * **Setter Injection:** Used to demonstrate dynamic, mid-flight mutability (swapping `EmailSender` to `SmsSender` at runtime without server restarts).
* **Conflict Resolution:** Utilizing `@Qualifier` to seamlessly route dependencies when multiple interface implementations exist (e.g., Mock Stripe vs. Mock Razorpay).
* **Custom Environment Parsing:** A custom `YamlPropertySourceFactory` engineered from scratch to allow the raw Spring Core engine to parse hierarchical `.yml` files without Spring Boot's native support.
* **Database Infrastructure:** Manual configuration of a **HikariCP** connection pool for optimal TCP connection management.
* **Raw JDBC & Functional Mapping:** Utilizing Spring's `JdbcTemplate` and lambda-based `RowMapper` to safely execute SQL and map `ResultSet` data without boilerplate `SQLException` handling.
* **Lifecycle Hooks:** Leveraging `@PostConstruct` to safely execute PostgreSQL-compatible DDL schema initializations immediately after bean creation.

## 🛠️ Technology Stack

* **Language:** Java 17
* **Framework:** Spring Core 6.1.4 (Context, JDBC, Beans, AOP)
* **Connection Pool:** HikariCP
* **Databases:** H2 (In-Memory for local testing) / PostgreSQL (Production-Ready)
* **Logging:** SLF4J API with Logback Implementation
* **Build Tool:** Maven

## 🚦 How to Run Locally

Because this project is configured dynamically, it runs out-of-the-box using an in-memory database. No external servers are required to test the architecture.

1. **Clone the repository:**
   ```bash
   git clone [https://github.com/anupamkushwaha85/Spring-Core-Billing-System.git](https://github.com/anupamkushwaha85/Spring-Core-Billing-System.git)

2. **Navigate to the directory:**
   ```bash
   cd pure-spring-billing
   ```

3. **Build the project & download dependencies:**
   ```bash
   mvn clean install
   ```

4. **Run the Application Engine:**
   Execute the `main()` method inside `src/main/java/com/billing/Main.java` via your IDE, or run via Maven:
   ```bash
   mvn exec:java -Dexec.mainClass="com.billing.Main"
   ```

## 🗄️ Swapping to PostgreSQL (Production Mode)

This system was engineered to be strictly compliant with standard SQL interfaces. To move from local testing to a production database, you do not need to alter any Java code.

Simply open `src/main/resources/application.yml` and update the credentials:

```yaml
db:
  url: jdbc:postgresql://localhost:5432/your_database_name
  username: your_postgres_user
  password: your_postgres_password
```

---

### About the Developer
A software engineer focused on backend development with Spring Boot and building real-world, scalable systems. I have hands-on experience developing REST APIs, architecting microservices, and integrating complex third-party services like payments and notifications. My technical stack extends to designing robust system architectures and deploying applications using Docker and cloud platforms.

Beyond just writing code, I truly love engineering. My philosophy remains the same: *If I am using a tool, I make it a point to know exactly how it works under the hood.*
<br>

Built with ❤️ by Anupam Kushwaha  
If you like it or found it helpful, please give it a ⭐️!