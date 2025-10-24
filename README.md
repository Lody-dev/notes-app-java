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



CRUDService -> Class that completely operates with DB. Performs CRUD operations. Needs Repository Interface for performing actions. In my case it uses NoteRepository.

## 🎯 What is the Controller? ##

The Controller is the entry point for HTTP requests — it connects your frontend (e.g. forms, buttons, URLs) to your backend logic (services, repositories).

In a layered Spring Boot app, the controller:

Accepts HTTP requests (GET, POST, PUT, DELETE, etc.)

Maps them to Java methods

Interacts with the service layer

Returns a view (HTML) or JSON response

DONE: Define needed API's for a web page
DONE: WRITE A CONTROLLER!

A bunch of controllers are written.

The only last thing i want to implement is pin/unpin logic
so i can pin important notes

Feature: ability to pin the important (or not) note.

What is needed?
Goal 1:
0) A button that sends note id via post ("/pin" action) requests to the back end.
1) A controller that controls ("/pin" route) with logic.
    1.1) Logic description: if (isPinned) {isPinned = false} else {isPinned = true}
         so it works like a switch.

After previous steps satisfied -> the user will be able to change isPinned value via front end.

Goal 2:
0) Index page that correctly visually displays pinned/unpinned status;
   0.1) tymeleaf template that recieves isPinned value and according to that value is will decide what 
        info to show
1) Index page that prefers to display pinned notes first. Most recent updated pinned note should be preffered.
   1.1) Controller Logic: New query, instead of "order by updatedAt desc" use "oreder by isPinned desc, updatedAt desc"


