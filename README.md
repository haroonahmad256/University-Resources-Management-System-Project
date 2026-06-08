# University Resources Management System

> A full-stack desktop application built in Java for managing university timetables, teachers, rooms, and batches — with smart clash detection and PDF export.

---

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Tech Stack](#tech-stack)
- [Project Structure](#project-structure)
- [Architecture](#architecture)
- [Module Breakdown](#module-breakdown)
- [How to Run](#how-to-run)
- [Dependencies](#dependencies)
- [Screenshots](#screenshots)
- [Author](#author)

---

## Overview

The University Resources Management System (URM) is a desktop application that helps university administrators manage their core scheduling resources — teachers, rooms, batches, and timetable entries — from a single interface.

It includes a full authentication system, real-time dashboard stats, CRUD operations across all resource types, intelligent timetable clash detection, and one-click PDF export for individual teacher or batch schedules.

Built as a second-semester Object-Oriented Programming course project at the University of Gujrat.

---

## Features

### Authentication
- User registration with details stored in MySQL
- Login with username and password
- Forgot password flow with email verification
- Password reset after successful email verification

### Main Dashboard
- Live stats: total teachers, total rooms, total batches, total timetable clashes
- Top 10 schedule entries displayed in a sortable table
- Manual resource checker: enter a time slot to check whether a specific teacher, room, or batch is free or occupied

### Teacher Management
- Add, update, delete teacher records
- Search teachers by ID or name
- Full form-based data entry

### Room Management
- Add, update, delete room records (including capacity)
- Search rooms by ID or name

### Batch Management
- Add, update, delete batch records (including student strength)
- Search batches by ID or name

### Timetable Scheduling
- Add, update, and delete schedule entries
- Real-time clash validation before any entry is saved
- If a clash is detected, the system blocks the entry and returns a specific error message:
  - Room capacity is too low for the batch strength
  - Teacher is already assigned to another class in that time slot
  - Batch is already scheduled in that time slot
  - Room is already occupied in that time slot
- Free slot suggestions: the system shows which time slots are available for that specific resource

### PDF Export
- Select export type: by Teacher or by Batch
- Enter the resource ID
- Full timetable for that resource loads in a preview table
- Click Save Timetable to choose a file location and export as a formatted PDF

---

## Tech Stack

| Layer | Technology |
|---|---|
| Language | Java |
| GUI Framework | Java Swing |
| GUI Design Tool | IntelliJ IDEA Swing GUI Form Designer |
| Database | MySQL |
| Database Connectivity | JDBC (mysql-connector-java-9.6.0) |
| PDF Export | iTextPDF 5.5.9 |
| Form Rendering | forms_rt.jar |
| IDE | IntelliJ IDEA |

---

## Project Structure

```
University Resources Management/
├── src/
│   ├── Database/
│   │   └── DBConnection.java
│   ├── GUI/
│   │   ├── Styling.java
│   │   ├── AddBatchDialog/
│   │   │   ├── AddBatchDialog.java
│   │   │   └── AddBatchDialog.form
│   │   ├── AddRoomDialog/
│   │   │   ├── AddRoomDialog.java
│   │   │   └── AddRoomDialog.form
│   │   ├── AddTeacherDialog/
│   │   │   ├── AddTeacherDialog.java
│   │   │   └── AddTeacherDialog.form
│   │   ├── BatchAndTeacherWiseTimeTable/
│   │   │   ├── BatchAndTeacherWiseTimeTable.java
│   │   │   └── BatchAndTeacherWiseTimeTable.form
│   │   ├── CreateAccountDialog/
│   │   │   ├── CreateAccountDialog.java
│   │   │   └── CreateAccountDialog.form
│   │   ├── DeleteBatchDialog/
│   │   ├── DeleteRoomDialog/
│   │   ├── DeleteSchedule/
│   │   ├── DeleteTeacherDialog/
│   │   ├── ForgotPasswordDialog/
│   │   ├── FreeSlotsRepresenter/
│   │   ├── LoginUser/
│   │   ├── MainDashboard/
│   │   ├── ShowOnSearchBatchDialog/
│   │   ├── ShowOnSearchRoomDialog/
│   │   ├── ShowOnSearchTeacherForm/
│   │   ├── TeacherSidePanel/
│   │   ├── UpdateBatchDialog/
│   │   ├── UpdateRoomDialog/
│   │   ├── UpdateSchedule/
│   │   └── UpdateTeacherDialog/
│   ├── Main/
│   │   └── App.java
│   └── Model/
│       ├── Batch.java
│       ├── BatchDAO.java
│       ├── ClashesChecker.java
│       ├── CRUDOperations.java
│       ├── Login.java
│       ├── Room.java
│       ├── RoomDAO.java
│       ├── Schedule.java
│       ├── Teacher.java
│       └── TeacherDAO.java
└── External Libraries/
    ├── forms_rt.jar
    ├── itextpdf-5.5.9.jar
    └── mysql-connector-java-9.6.0.jar
```

---

## Architecture

This project follows an MVC-style layered architecture:

**Model Layer** (`src/Model/`)
Contains all data classes and database access objects. Each resource has a plain Java class (Batch, Room, Teacher, Schedule) and a corresponding DAO class (BatchDAO, RoomDAO, TeacherDAO) that handles all SQL operations. `CRUDOperations.java` provides shared database utility methods. `ClashesChecker.java` contains the business logic for validating schedule entries before they are saved.

**View + Controller Layer** (`src/GUI/`)
Each dialog or panel is an IntelliJ Swing form. The `.form` file defines the layout using the drag-and-drop designer. The `.java` file handles all events, calls the model, and updates the UI. `Styling.java` provides shared UI styling constants.

**Database Layer** (`src/Database/`)
`DBConnection.java` manages the MySQL connection using JDBC. All DAO classes use this single connection class.

**Entry Point** (`src/Main/App.java`)
Launches the application and opens the Login screen.

---

## Module Breakdown

### DBConnection.java
Establishes and returns the MySQL connection. All DAO classes call this to get a connection before running queries.

### ClashesChecker.java
The core business logic class. Before any schedule entry is inserted or updated, this class checks:
- Whether the teacher is already scheduled at that time
- Whether the room is already booked at that time
- Whether the batch already has a class at that time
- Whether the room capacity is sufficient for the batch strength

If any check fails, it throws a descriptive exception message and returns the free time slots for the affected resource.

### FreeSlotsRepresenter
A dialog that displays the available time slots for a teacher, room, or batch when a clash is detected. Shown automatically after a failed schedule entry.

### BatchAndTeacherWiseTimeTable
Handles the PDF export flow. Accepts a resource type (teacher or batch) and an ID, loads all schedule entries for that resource into a preview table, and exports the result as a PDF using iTextPDF when the Save Timetable button is clicked.

### CRUDOperations.java
A shared utility class with reusable methods for common database operations used across multiple DAO classes.

---

## How to Run

**Prerequisites**
- Java JDK 21 or above
- IntelliJ IDEA (recommended) or any Java IDE
- MySQL Server running locally
- MySQL Workbench (optional, for database setup)

**Steps**

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/university-resources-management.git
   ```

2. Import the project into IntelliJ IDEA as an existing project.

3. Add the following JARs to the project's library path:
   - `forms_rt.jar`
   - `itextpdf-5.5.9.jar`
   - `mysql-connector-java-9.6.0.jar`

4. Set up the MySQL database:
   - Create a database named `university_resources` (or match the name in `DBConnection.java`)
   - Run the provided SQL schema file to create tables

5. Update database credentials in `DBConnection.java`:
   ```java
   String url = "jdbc:mysql://localhost:3306/university_resources";
   String username = "your_mysql_username";
   String password = "your_mysql_password";
   ```

6. Run `App.java` as the main class.

---

## Dependencies

| JAR | Purpose |
|---|---|
| `mysql-connector-java-9.6.0.jar` | JDBC driver for MySQL connectivity |
| `itextpdf-5.5.9.jar` | PDF generation for timetable export |
| `forms_rt.jar` | Runtime support for IntelliJ Swing GUI forms |

---

## Screenshots

<img width="584" height="356" alt="image" src="https://github.com/user-attachments/assets/ffb2c4ad-994b-44c7-b07e-24779dde7ec5" />
<img width="1203" height="801" alt="image" src="https://github.com/user-attachments/assets/19f2cf6a-546e-48af-a373-c43e7cc1ae94" />
<img width="1203" height="801" alt="image" src="https://github.com/user-attachments/assets/aab01c08-25ac-4420-a6c2-89054dad1aa6" />


---

## Author

**Haroon Ahmad**
BS Artificial Intelligence — Semester 2
University of Gujrat, Main Campus

Course: Object-Oriented Programming

[LinkedIn](https://linkedin.com/in/haroon-ahmed-48b947381) · [GitHub](https://github.com/haroonahmad256/)
