# Library-Management-System

This document outlines the RESTful API endpoints for a Library Management System. The system manages books, patrons, and borrowing records.

## Entities

### Book
Represents a book in the library.

### Patron
Represents a library patron.

### Borrowing Record
Tracks the borrowing details between books and patrons.

## API Endpoints

Swagger ui for interacting with the endpoints if you run this project on local host then the url will be : http://localhost:8080/swagger-ui.html
<br>
The endpoints categorized in 2 categories

- **Auth Apis**: 
  Here you can find the apis for registering the users and for authentication and getting the tokens.
- **Management Apis**: 
  Here you can find the apis for managing the system.


### Book Management

- **GET /api/books**
  Retrieves a list of all books.

- **GET /api/books/{id}**
  Retrieves details of a specific book by ID.

- **POST /api/books**
  Adds a new book to the library.

- **PUT /api/books/{id}**
  Updates an existing book's information.

- **DELETE /api/books/{id}**
  Removes a book from the library.

### Patron Management

- **GET /api/patrons**
  Retrieves a list of all patrons.

- **GET /api/patrons/{id}**
  Retrieves details of a specific patron by ID.

- **POST /api/patrons**
  Adds a new patron to the system.

- **PUT /api/patrons/{id}**
  Updates an existing patron's information.

- **DELETE /api/patrons/{id}**
  Removes a patron from the system.

### Borrowing Management

- **POST /api/borrow/{bookId}/patron/{patronId}**
  Allows a patron to borrow a book.

- **PUT /api/return/{bookId}/patron/{patronId}**
  Records the return of a borrowed book by a patron.

## Usage

To use these endpoints, send HTTP requests with the appropriate method (GET, POST, PUT, DELETE) to the specified paths. Include any required parameters, such as `id`, `bookId`, or `patronId`, in the path. For POST and PUT requests, include the relevant entity data in the request body as JSON.
