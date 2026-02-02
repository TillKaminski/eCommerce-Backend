# Finance Dashboard – Spring Boot Backend

![Java](https://img.shields.io/badge/java_17-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/spring_boot_3.2-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![H2 Database](https://img.shields.io/badge/Database_H2-004482?style=for-the-badge&logo=databricks&logoColor=white)
![Maven](https://img.shields.io/badge/apachemaven-C71A36.svg?style=for-the-badge&logo=apachemaven&logoColor=white)

> Companion Frontend Repository: [eCommerce-Frontend](https://github.com/TillKaminski/eCommerce-Frontend-Angular)

This repository contains the REST API for a finance transaction dashboard. It was developed as a one-week technical coding challenge to demonstrate a core business workflow: **User Deposits** and **Staff-Authorized Withdrawals.**

---

## 📺 Fullstack Workflow
![Fullstack Demo GIF](./src/main/resources/e-commerce_demo.gif)
*User requests a withdrawal (Pending) → Staff authorizes → Transaction completes (Successful) → Balance updates.*

## 🏗️ Architecture
The backend is designed with a service-oriented approach to manage financial transactions and user states efficiently:

- **Centralized Business Logic:** A dedicated **Payment Service** handles the complex transaction lifecycle. It orchestrates updates across both User (balances) and Deposit (status) entities.
- **Transaction Workflow:** 
  - **Submission:** Users trigger a deposit request. The system increments a transaction counter and handles the entry as *pending* without affecting the balance yet.
  - **Authorization:** Staff can "resubmit" these pending entries. The service then processes the final transaction and updates the user's balance.
- **Data Persistence:** Implemented via **Spring Data JPA** (utilizing `JpaRepository`). The relational model ensures data integrity between users and their linked transaction histories.
- **Instant Demo Mode:** Uses an **H2 In-Memory database** pre-populated with test data via `CommandLineRunner` for an immediate "out-of-the-box" demonstration.

## 🛠️ Tech Stack
- **Framework:** Spring Boot (REST API)
- **Persistence:** Spring Data JPA / Hibernate
- **Database:** H2 (Relational, In-Memory)
- **Language:** Java 17

## 🌐 API Endpoints
- Configured Cross-Origin Resource Sharing (**CORS**) for Angular frontend integration.

### Transactions (`/api`)
- `POST /addpayment` – Create a new user transaction.
- `GET /{userId}/payments` – Retrieve all transactions for a specific user.
- `GET /payments/sumperiod/{dateBegin}/{dateEnd}` – Filter and sum transactions within a date range (used for staff reporting).
- `PUT /resubpayment` – Authorize and process a pending withdrawal (Staff action).

### User Management (`/api/user`)
- `POST /register` – Register a new account.
- `GET /all` – List all registered users (for demonstration purposes).

## 🚀 How to Run
1. Clone the repository.
2. Ensure you have **Java 17** installed.
3. Run the application:
   ```bash
   ./mvnw spring-boot:run
   ```
4. **Backend URL**: The API will be available at `http://localhost:8080`.

## License

This project is licensed under the MIT License - see the [LICENSE](./LICENSE) file for details.