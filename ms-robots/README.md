
# 🤖 ms-robots

Microservice developped in Java with Spring Boot applying hexagonal architecture.  
This microservice manage operations without persistence in DB.

---

## 📦 Project structure

```
src/main/java/com/example/msrobots/
  ├── application/service            # Use Cases
  ├── common                         # class for common constants, functions, etc..
  ├── domain/                        # entities, exceptions and uses case interfaces
  ├── infrastructure/
  │     └── controller/              # REST controllers (in adapters)
  │── MsRobotsApplication.java       # application main class
  ├── ressources/                    # properties files
  ├── test/                          # test classes       

```

---

## ⚙️ Config

Config properties are set in:

```
src/main/resources/application.properties
```

Example:

```properties
server.port=8080
```

---

## 🚀 Availables endpoints

http://localhost:8080/robots/lines

### 📌 Receive command lines for a robot

**POST** `/robots/lines`

- **Description:** Receives lines for processing commands.
- **Body:**

```json
{
  "lines": 
    [
      "5 5",
      "1 2 N",
      "LMLMLMLMM",
      "3 3 E",
      "MMRMMRMRRM"
    ]  
}
```

- **Response:** `200 OK`

    [
    "1 3 N",
    "5 1 E"
    ]

---

## 🧪 Unit tests

The project includes unit tests with **JUnit 5** and **Mockito** to:

- Check the result of the cleaning use case 
- 
To launch tests:

```bash
./mvnw test
```

---

## 📖 Notes

---

## 🛠️ Technologies

- Java 17+
- Spring Boot 3+
- Hexagonal Architecture
- JUnit 5
- Mockito
- Lombok

---

## 📚 How to execute

From terminal:

```bash
./mvnw spring-boot:run
```

O from IDE (IntelliJ IDEA / Eclipse / VS Code).

---

## ✨ Author

- **Ks-robots project**
- Hexagonal architecture by Jose Antonio Adriano Muñoz
