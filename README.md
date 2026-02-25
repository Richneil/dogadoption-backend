# Dog Adoption Backend (Spring Boot + MySQL)

Backend API for the City Veterinary Dog Adoption system.

---

## Requirements

- Java 17+ (Recommended: Java 17 or 21)
- Maven 3.9+
- MySQL Server
- MySQL Workbench (Optional)
- Postman (Optional)

---

## Clone the Repository

```bash
git clone https://github.com/Richneil/dogadoption-backend.git
cd dogadoption-backend
```

---

## Database Setup (MySQL)

### 1) Create Database

Open MySQL Workbench and run:

```sql
CREATE DATABASE dog_adoption_db;
```

### 2) Configure Connection (application.properties)

Open:

```
src/main/resources/application.properties
```

Update with your credentials:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/dog_adoption_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=CHANGE_ME
spring.jpa.hibernate.ddl-auto=update
```

---

## Run the Backend

### Option A: IntelliJ

Run:

```
DogadoptionApplication.java
```

### Option B: Command Line

```bash
mvn clean spring-boot:run
```

Application runs at:

```
http://localhost:8080
```

---

## Default Admin Account

The admin account is automatically created by the database seeder:

- **Username:** admin
- **Password:** admin123

---

## Register a User (Public Endpoint)

**POST**

```
http://localhost:8080/auth/register
```

**Request Body**

```json
{
  "username": "user1",
  "password": "1234"
}
```

---

## Postman Testing (Basic Auth)

Use the following credentials:

- admin / admin123
- user1 / 1234

---

## Key Endpoints

```text
GET    /dogs
POST   /dogs
PUT    /dogs/{dogId}
DELETE /dogs/{dogId}

POST   /dogs/{dogId}/requests?userId={userId}
GET    /requests

PUT    /requests/{requestId}/status?status=APPROVED
PUT    /requests/{requestId}/status?status=REJECTED
```

---

## Commit and Push README

```bat
git add README.md
git commit -m "Add README setup guide"
git push
```

---

## Database Guide (For Team Members)

### 6.1 Create Database

```sql
CREATE DATABASE dog_adoption_db;
```

### 6.2 Verify Database Exists

```sql
SHOW DATABASES;
```

### 6.3 Check Tables After Running Backend

After the application runs once (with `ddl-auto=update`), execute:

```sql
USE dog_adoption_db;
SHOW TABLES;
SELECT * FROM users;
SELECT * FROM dogs;
SELECT * FROM adoption_requests;
```

They should appear after the application starts and endpoints are accessed.
