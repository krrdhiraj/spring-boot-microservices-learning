
# 🧩 Spring Microservices Project from Scratch

A robust, scalable, and secure Microservices-based application built using **Java 21**, **Spring Boot (latest)**, and **Spring Cloud**, integrated with **Keycloak** for modern JWT-based Authentication and Authorization.

This project serves as a complete end-to-end example of how to build production-grade microservices using:
- Spring Core
- Spring Data JPA & Hibernate
- Spring Boot
- Spring Cloud
- Keycloak (Auth Server)
- JWT Token-based Security

---

## 🛠️ Tech Stack

| Layer              | Technology                                      |
|-------------------|--------------------------------------------------|
| Programming Lang. | Java 21                                          |
| Core Framework    | Spring Core, Spring Boot                         |
| Data Persistence  | Spring Data JPA, Hibernate                       |
| Microservices     | Spring Cloud (Eureka, Gateway, Config Server)    |
| Security          | JWT, Keycloak (OpenID Connect)                   |
| Database          | PostgreSQL / MySQL (pluggable)                   |
| Build Tool        | Maven / Gradle                                   |
| Containerization  | Docker (Optional)                                |

---

## 📦 Project Structure

```bash
spring-microservices/
├── config-server/             # Centralized config management
├── discovery-server/          # Eureka service registry
├── api-gateway/               # API Gateway (Spring Cloud Gateway)
├── auth-server/               # Keycloak server integration
├── service-user/              # User microservice
├── service-product/           # Product microservice
├── service-order/             # Order microservice
└── common-lib/                # Shared classes/utilities across services
```

---

## 🔐 Authentication & Authorization

- **Keycloak** acts as the central Identity Provider (IdP).
- Each microservice validates **JWT tokens** issued by Keycloak.
- Fine-grained **Role-based Access Control (RBAC)** is enforced.
- Supports `Public`, `Confidential`, and `Bearer-only` client flows.

---

## 🚀 Features

✅ Build scalable microservices from scratch  
✅ Secure endpoints using JWT and Keycloak  
✅ Centralized config and service discovery  
✅ Role-based access to microservices  
✅ Clean separation of concerns and modularity  
✅ Error handling, validation, logging, and resilience

---

## 🧪 How to Run

1. **Clone the repository**  
   ```bash
   git clone https://github.com/your-username/spring-microservices-keycloak.git
   cd spring-microservices-keycloak
   ```

2. **Spin up Keycloak**  
   Use the provided Docker Compose or install manually.  
   Configure realms, clients, and roles.

3. **Start Config & Discovery Servers**  
   ```bash
   cd config-server && ./mvnw spring-boot:run
   cd discovery-server && ./mvnw spring-boot:run
   ```

4. **Start Microservices & Gateway**  
   Run each service individually or automate via Docker Compose.

---

## 🧭 API Endpoints (Sample)

| Method | Endpoint                    | Description                  |
|--------|-----------------------------|------------------------------|
| GET    | /api/user/me                | Get current user info        |
| POST   | /api/order/place            | Place a new order            |
| GET    | /api/product/list           | Fetch all products           |
| POST   | /api/auth/token             | Get access token from Keycloak |

> All secured routes require a valid JWT in the `Authorization` header.

---

## 🧰 Tools Used

- **Postman** for testing APIs  
- **Spring Boot DevTools** for rapid development  
- **Lombok** for cleaner model and DTO classes  
- **ModelMapper** for DTO to entity conversion

---

## 🙌 Contributions

Feel free to fork and submit PRs. Suggestions and improvements are always welcome!

---

## 💬 Connect

📧 Email: k4.dhiraj4@gmail.com 
🔗 LinkedIn: [YourLinkedInProfile](https://www.linkedin.com/in/dhirajkr1/)
🐙 GitHub: [@your-username](https://github.com/krrdhiraj)

---

> Built with ❤️ using Spring Boot & Keycloak
