# Dog Adoption Backend (Spring Boot + MySQL)

Backend API for the City Veterinary Dog Adoption system.

## Requirements
- Java 17+ (recommended: Java 17 or 21)
- Maven 3.9+
- MySQL Server + MySQL Workbench (optional)
- Postman (optional)

## Check requirements in Command Prompt (Verify installed techs)

### Java
```bash
java -version
```

### Maven
```bash
mvn -version
```

### MySQL
```bash
mysql --version
```

(Optional) Test MySQL login (if MySQL Server is running):
```bash
mysql -u root -p
```

## Clone the repository
```bash
git clone https://github.com/Richneil/dogadoption-backend.git
cd dogadoption-backend
```

Database setup (MySQL)

## 1) Create database

Open Command Prompt and login to MySQL:

```bash
mysql -u root -p
```

Enter your MySQL root password when prompted.

Then run:

```sql
CREATE DATABASE dog_adoption_db;
```

(Optional) Verify it was created:

```sql
SHOW DATABASES;
```

## 2) Configure connection in application.properties
Open:
```
src/main/resources/application.properties
```

Update This:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/dog_adoption_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=CHANGE_ME
spring.jpa.hibernate.ddl-auto=update
```

Run the backend

## Option A: IntelliJ
Run:
```
DogadoptionApplication.java
```

## Option B: Command line
```bash
mvn clean spring-boot:run
```

App runs at POSTMAN or SWAGGER (any backend api testing):
```
http://localhost:8080
```

Default Admin Account  
Admin is auto-created by the seeder:

username: admin  
password: admin123  

Register a User (Public) (No Auth Needed since this is registration)

POST:
```
http://localhost:8080/auth/register
```

Body:
```json
{
  "username": "user1",
  "password": "1234"
}
```

Postman testing (Basic Auth)  
Use Basic Auth credentials:

admin / admin123  
user1 / 1234  

Key endpoints
```text
GET /dogs (public)
POST /dogs (admin only)
PUT /dogs/{dogId} (admin only)
DELETE /dogs/{dogId} (admin only)
POST /dogs/{dogId}/requests?userId={userId} (authenticated user)
GET /requests (admin only)
PUT /requests/{requestId}/status?status=APPROVED (admin only)
PUT /requests/{requestId}/status?status=REJECTED (admin only)
```

Now commit and push README:
```bat
git add README.md
git commit -m "Add README setup guide"
git push
```

6) Database guide you can send to your team (copy/paste)

## 6.1 Create DB
In MySQL Workbench:
```sql
CREATE DATABASE dog_adoption_db;
```

## 6.2 Verify DB exists:
```sql
SHOW DATABASES;
```

## 6.3 (Optional) Check tables after running backend:
After the app runs once (with ddl-auto=update), run:
```sql
USE dog_adoption_db;
SHOW TABLES;
SELECT * FROM users;
SELECT * FROM dogs;
SELECT * FROM adoption_requests;
```

✅ They should appear after the app starts and you hit endpoints.

For Frontend Team see this note:
## 🔗 API Endpoints Summary (Frontend Guide)

### 🐶 Dog Management

```text
GET /dogs
Access: Public
Description: Returns the list of all dogs available for adoption.
Authentication: Not required
```

```text
POST /dogs
Access: Admin only
Description: Creates a new dog record in the system.
Authentication: Basic Auth (admin account)
```

```text
PUT /dogs/{dogId}
Access: Admin only
Description: Updates the details of a specific dog (name, breed, age, status).
Authentication: Basic Auth (admin account)
```

```text
DELETE /dogs/{dogId}
Access: Admin only
Description: Deletes a specific dog from the system.
Authentication: Basic Auth (admin account)
```

---

### 📩 Adoption Requests

```text
POST /dogs/{dogId}/requests?userId={userId}
Access: Authenticated user
Description: Creates an adoption request for a selected dog.
Behavior: Automatically sets request status to PENDING.
Authentication: Basic Auth (user or admin)
```

```text
GET /requests
Access: Admin only
Description: Returns the list of all adoption requests submitted by users.
Authentication: Basic Auth (admin account)
```

```text
PUT /requests/{requestId}/status?status=APPROVED
Access: Admin only
Description: Approves a specific adoption request.
Behavior:
- Changes request status to APPROVED
- Automatically updates the dog status to ADOPTED
Authentication: Basic Auth (admin account)
```

```text
PUT /requests/{requestId}/status?status=REJECTED
Access: Admin only
Description: Rejects a specific adoption request.
Behavior:
- Changes request status to REJECTED
- Dog remains AVAILABLE (if not already adopted)
Authentication: Basic Auth (admin account)
```

---

### 🔐 Authentication Notes

```text
Public Routes:
GET /dogs

Secured Routes:
All other endpoints require Basic Auth

Default Admin Account:
username: admin
password: admin123

Note:
Users must register first before submitting adoption requests.
```
