# Dog Adoption Backend (Spring Boot + MySQL)

Backend API for the City Veterinary Dog Adoption system.

## Requirements
- Java 17+ (recommended: Java 17 or 21)
- Maven 3.9+
- MySQL Server + MySQL Workbench (optional)
- Postman (optional)

## Clone the repository
```bash
git clone https://github.com/Richneil/dogadoption-backend.git
cd dogadoption-backend

Database setup (MySQL)
**1) Create database**
Open MySQL Workbench and run:
CREATE DATABASE dog_adoption_db;

**2) Configure connection in application.properties**
Open:
src/main/resources/application.properties
Update This:
spring.datasource.url=jdbc:mysql://localhost:3306/dog_adoption_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=CHANGE_ME
spring.jpa.hibernate.ddl-auto=update

**Run the backend**
**Option A:** IntelliJ
Run:
DogadoptionApplication.java

**Option B: Command line**
mvn clean spring-boot:run

App runs at:
http://localhost:8080

**Default Admin Account
Admin is auto-created by the seeder:**
username: admin
password: admin123

Register a User (Public)

POST:
http://localhost:8080/auth/register
Body:
{
  "username": "user1",
  "password": "1234"
}

Postman testing (Basic Auth)
Use Basic Auth credentials:

admin / admin123
user1 / 1234

**Key endpoints**

GET /dogs (public)
POST /dogs (admin only)
PUT /dogs/{dogId} (admin only)
DELETE /dogs/{dogId} (admin only)
POST /dogs/{dogId}/requests?userId={userId} (authenticated user)
GET /requests (admin only)
PUT /requests/{requestId}/status?status=APPROVED (admin only)
PUT /requests/{requestId}/status?status=REJECTED (admin only)
Now commit and push README:
```bat
git add README.md
git commit -m "Add README setup guide"
git push

6) Database guide you can send to your team (copy/paste)
**6.1 Create DB **
In MySQL Workbench: CREATE DATABASE dog_adoption_db;
**6.2 Verify DB exists:** 
SHOW DATABASES;
**6.3 (Optional) Check tables after running backend: 
After the app runs once (with ddl-auto=update), run: **
USE dog_adoption_db;
SHOW TABLES;
SELECT * FROM users;
SELECT * FROM dogs;
SELECT * FROM adoption_requests;
✅ They should appear after the app starts and you hit endpoints.




















