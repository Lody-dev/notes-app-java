Dependencies used:
    Spring Web
    Spring DevTools
    Thymeleaf
    MySQL driver
    Spring Data JPA

Java Notes App implemented with Layered architecture;

Layers:

    - Presentation layer  -> Controllers, http request handeling
    - Service layer       -> Coordinates calls between layers
    - Repository layer    -> Interfaces with DB via JPA, only for CRUD operations

## Project structure ##
com.example.notesapp\
├── controller    → Handles HTTP requests\
├── service       → Business logic\
├── repository    → DB access\
├── model         → Entity

# Development Plan for Notes App (Layered Architecture)

1. **Define the Data Entity in Java**  
   - Create the `Note` entity class with JPA annotations to map to the database table structure.  
   - Include fields like `id`, `title`, `content`, `pinned`, and timestamps (`createdAt`, `updatedAt`).  

2. **Set Up the MySQL Database**  
   - Create a new MySQL database instance/schema where your app will store data.  

3. **Configure Spring Boot to Connect to MySQL**  
   - In `application.properties` or `application.yml`, set the connection properties (URL, username, password).  
   - Include the MySQL driver dependency to enable communication.  
   - Configure Hibernate (via Spring Data JPA) to create or update tables automatically based on your entity (`spring.jpa.hibernate.ddl-auto=update` recommended during development).  

4. **Create Repository Interface**  
   - Define the `NoteRepository` extending `JpaRepository` to manage CRUD operations.  

5. **Run the Application to Automatically Generate the Notes Table**  
   - Use JPA/Hibernate auto-ddl feature to create the `notes` table in your database based on your entity class — no need to manually create the table yourself unless you want fine control.  

## What is entity? ##

Java class that represents the database table

Each instance of the class == one row in the database table

Defining note instance:

    - long        id
    - String      title
    - String      content
    - DateTime    creationDate
    - DateTime    updatedAt
    - boolean     isPinned

updateAt and isPinned are for comfortable notes sorting.

## What is Repository Interface ? ##
Abstraction -> is needed for performing CRUD operations.

Code example: public interface NoteRepository extends JpaRepository<NoteEntity, Long>
NoteEntity -> is entity i created previously to represend database tables in Java.
