# Coding Challenge
You will build a simple REST-API for ToDo tasks.

###Technologies to be used:
* Java 17
* Maven
* Spring Boot 2.7.x
* JPA
* a SQL database of your choice
* Springdoc for OpenAPI documentation
* JUnit / Mockito for testing

## Task 1: Project Setup
Set up the project ready to be used in your favorite IDE.

### Goals:
* project is correctly set up in the IDE
* Maven build is running and managing all dependencies
* the otherwise empty Spring Application is starting successfully

## Task 2: Database Setup
Set up the database to be used. Use a database running in Docker container.

You can pick an official image from Docker Hub.

### Goals:
* make sure the database is running and accessible

>I will send you instructions on how to set up Docker in WSL (Windows Subsystem for Linux) in German

## Task 3: Create the CRUD Service
Develop a REST-ful service for creating, fetching, updating and deleting ToDo tasks.  
Create an additional endpoint to fetch a list of all ToDo tasks.

### Requirements for the ToDo model
| field  | description  | type  | example | notes |
|---|---|---|---|---|
| `id` | unique identifier of the ToDo  | UUID | f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454 | |
| `name` | the name of the ToDo | String  | Do the washing  | |
| `dueDate`  | a due date for the task | _figure out on your own_ | | optional value  |
| `priority` | an enumerated value for the task priority | Enum  | `HIGH` | _figure out the best JPA mapping for this_ |

These are the possible values for priority enum: `LOW`, `NORMAL`, `HIGH`, `TOP`

Focus on a clean architecture, solid test coverage and understandable code. This could be a real project ;-)

## Task 4: Develop a front-end as an Angular SPA
Build a web app in which a user can create new ToDo items, retrieve a list of all ToDos, update the contents of an existing ToDo and discard existing tasks.

Use Angular Material for implementing the user interface.

### Goals:
* build an application front-end
* separate application logic into services
* create a REST data layer to access the back-end functionality
* provide at least tests for the service layer



