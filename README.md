API Automation Framework
📌 Overview
This is a scalable and modular API automation framework built using Rest Assured, TestNG, and Java. It supports RESTful API testing, GraphQL testing, database validation, terminal command execution, and data-driven testing.

🚀 Features
✅ REST API Testing – Automates GET, POST, PUT, PATCH, and DELETE requests
✅ GraphQL Testing – Supports query and mutation testing
✅ Database Integration – Fetches data dynamically from MySQL for API validation
✅ Data-Driven Testing – Reads test data from JSON, Excel, and DB queries
✅ Request & Response Specifications – Ensures reusable API request configurations
✅ Logging & Debugging – Uses log messages and TestNG listeners
✅ Parallel Execution – Configured in testng.xml for optimized execution
✅ Centralized Configuration Management – Uses config.properties for easy setup
✅ Automation of Terminal Commands – Executes shell commands within tests

🛠 Setup & Installation
🔹 Prerequisites
Install Java (JDK 11 or higher)
Install Maven
Install MySQL (if using DB integration)
Configure IDE (Eclipse/IntelliJ) with TestNG Plugin
🔹 Clone Repository
git clone https://github.com/YOUR_GITHUB_USERNAME/api-automation-framework.git
cd api-automation-framework
🔹 Install Dependencies
mvn clean install
📌 How to Execute Tests?
🔹 Run All Test Cases
mvn test
🔹 Run Specific Test Cases
mvn test -Dgroups=SmokeTest
🔹 Run Tests via testng.xml
mvn test -DsuiteXmlFile=testng.xml
📌 Technologies Used
Java – Core programming language
TestNG – Test execution framework
Rest Assured – API automation
MySQL – Database integration
Apache POI – Excel data handling
Jackson/Gson – JSON serialization & deserialization
Maven – Build & dependency management
👥 Contributors
Yashawanthkumar
