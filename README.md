# Angular + Spring Boot Web Application

This repository contains a full-stack web application built with Angular and Spring Boot.

## Overview

The project demonstrates the integration of an Angular frontend with a Spring Boot backend. It utilizes a unified Maven-based build process that orchestrates both frontend and backend compilation, making deployment and packaging straightforward.

## Technology Stack

### Backend
- **Framework:** Spring Boot 3.2.3
- **Language:** Java 17
- **Data Access:** Spring Data JPA, Hibernate ORM
- **Database:** H2 Database (In-Memory for development/runtime)
- **Object Mapping:** MapStruct 1.5.5.Final
- **Build Tool:** Maven

### Frontend
- **Framework:** Angular 8 (Angular CLI 8.3.19)
- **UI Components:** Angular Material
- **Language:** TypeScript
- **Package Manager:** npm (Node.js v12.13.1)

## Project Structure

- `/src`: Contains the backend Java source code (Spring Boot application).
- `/angular`: Contains the frontend Angular source code.
- `pom.xml`: The root Maven build configuration that manages both backend dependencies and frontend build execution (via `frontend-maven-plugin`).
- `install-backend-deps.ps1`: A PowerShell script to automatically install required backend development dependencies (JDK and Maven) on Windows.
- `install-backend-deps.sh`: A Bash script to automatically install required backend development dependencies (JDK and Maven) on macOS and Linux (Debian/Ubuntu, Fedora/RHEL).

## Getting Started

### Prerequisites

You need the following installed on your system to run the application:
- **Java 17** (or use the provided `install-backend-deps.ps1` script)
- **Node.js** (v12.13.1 recommended) and **npm**
- **Maven** (or use the provided `mvnw` wrapper)

**Windows Users:** You can run `.\install-backend-deps.ps1` as Administrator to automatically install the JDK and Maven.

**macOS Users:** You can run `./install-backend-deps.sh` (do not use `sudo`) to automatically install the JDK and Maven via Homebrew.

**Linux Users:** You can run `sudo ./install-backend-deps.sh` to automatically install the JDK and Maven.

### Building the Project

This project uses the `frontend-maven-plugin` to download Node/npm and build the Angular application during the Maven build process. You do not need to build the frontend separately for production packaging.

To build the complete application (both backend and frontend), run:
```bash
./mvnw clean install
```
*Note: On Windows, use `mvnw.cmd clean install` or simply `mvn clean install` if Maven is in your PATH.*

### Running the Application

#### Option 1: Run as a Full-Stack Application
Once built, you can run the application directly via Spring Boot. The Spring Boot application will serve the built Angular static files.

```bash
./mvnw spring-boot:run
```
The application will be available at `http://localhost:8080`.

#### Option 2: Development Mode (Separate Servers)
For active development, it's recommended to run the frontend and backend servers separately to take advantage of Angular's live reload.

1. **Start the Backend:**
   ```bash
   ./mvnw spring-boot:run
   ```
   *The API will be available at `http://localhost:8080`.*

2. **Start the Frontend:**
   Open a new terminal window and navigate to the `angular` directory:
   ```bash
   cd angular
   npm install
   npm start
   ```
   *The Angular application will be available at `http://localhost:4200` and will automatically proxy API requests to `http://localhost:8080`.*

## Docker Support

The project includes a `Dockerfile` and is configured with the `dockerfile-maven-plugin`. You can package the application into a Docker container.

To build the Docker image:
```bash
./mvnw dockerfile:build
```
