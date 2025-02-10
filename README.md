# Pinger Test Automation Framework

This project is an **automated testing framework** for the **Pinger application**. It validates the behavior of a tool
that pings endpoints and generates reports.

---

## Table of Contents

1. [Overview](#1-overview)
2. [Prerequisites for Running Tests Locally](#2-prerequisites-for-running-tests-locally)
   - [Java Development Kit (JDK 21+)](#21-java-development-kit-jdk-21)
   - [Apache Maven (Build Tool)](#22-apache-maven-build-tool)
   - [Git (For Cloning the Repository)](#23-git-for-cloning-the-repository)
   - [Allure Report (For Viewing Test Results)](#24-allure-report-for-viewing-test-results)
3. [Installation](#3-installation)
   - [Clone the Repository](#31-clone-the-repository)
   - [Navigate to the Project Folder](#32-navigate-to-the-project-folder)
   - [Install Dependencies](#33-install-dependencies)
   - [Compile the Pinger App](#34-compile-the-pinger-app)
4. [Running Tests Locally](#4-running-tests)
   - [Run All Tests](#41-run-all-tests)
   - [Run a Specific Test](#42-run-a-specific-test)
   - [Run Tests with Logging](#43-run-tests-with-logging)
5. [Viewing Test Reports](#5-viewing-test-reports)
   - [View Reports in the Console](#51-view-reports-in-the-console)
   - [View Reports in Allure](#52-view-reports-in-allure)
6. [Run Test Cases Using GitHub Actions](#6-run-test-cases-using-github-actions)
7. [Test Coverage And Bugs](#7-test-coverage-and-bugs)

---

## 1. Overview

### **What Does This Project Do?**

- **Automates testing** for the Pinger application.
- **Sends pings to specified endpoints**.
- **Generates JSON reports** and validates them.
- **Uses Maven and TestNG** for test execution.
- **Provides Allure reports** for test results.

### **Who Can Use This?**

This guide assumes **no prior Java knowledge**. If you can follow instructions, you can run the tests!

---

## 2. Prerequisites for Running Tests Locally

Before you start, **install the following tools**:

### Required Software:

### 2.1 Java Development Kit (JDK 21+)

- Download: [Temurin JDK](https://adoptium.net/en-GB/temurin/releases/?version=21&package=jdk&os=linux)
- Verify installation:

```sh
java -version
```

### 2.2 Apache Maven (Build Tool)

- Download: [Maven Official Site](https://maven.apache.org/)
- Verify installation:

```sh
mvn -version
```

### 2.3 Git (For Cloning the Repository)

- Download: [Git Website](https://git-scm.com/)
- Verify installation:

```sh
git --version
```

### 2.4 Allure Report (For Viewing Test Results)

- Install Allure:

```sh
brew install allure    # macOS
sudo apt update && sudo apt install allure    # Linux
```

- Verify installation:

```sh
allure --version
```

---

## 3. Installation

### 3.1 Clone the Repository

```sh
git clone https://github.com/your-repo/pinger-automation.git
```

### 3.2 Navigate to the Project Folder

```sh
cd pinger-automation
```

### 3.3 Install Dependencies

If you're running tests from IntelliJ IDEA, enable **Annotation Processing**:

Navigate to:

```
Preferences -> Build, Execution, Deployment -> Compiler -> Annotation Processors
```

Then, execute:

```sh
mvn clean install
```

### 3.4 Compile the Pinger App

#### 3.4.1 Go to the [Pinger Application Repository](https://github.com/NordSecurity-Interviews/llt-pinger-home-assignment)

#### 3.4.2 Clone the Repository

```sh
git clone https://github.com/NordSecurity-Interviews/llt-pinger-home-assignment
```

#### 3.4.3 Navigate to the Project Folder

```sh
cd path/to/pinger/repository/
```

#### 3.4.4 Build the Pinger Application

```sh
go build
```

#### 3.4.5 Move the Pinger Executable to the Automation Folder

```sh
mv /path/to/pinger/file ~/src/main/resources/testData/
```

Example:

```sh
mv ~/Downloads/file.txt ~/Documents/
```

#### 3.4.6 Compile the Project
```sh
mvn clean install
```

---

## 4. Running Tests

### 4.1 Run All Tests

```sh
mvn test
```

### 4.2 Run a Specific Test

```sh
mvn -Dtest=TestClassName test
```

Example:

```sh
mvn -Dtest=TTC007_VerifyPingerFlowWithValidReportTest test
```

### 4.3 Run Tests with Logging

```sh
mvn test -Dorg.slf4j.simpleLogger.defaultLogLevel=debug
```

---

## 5. Viewing Test Reports

### 5.1 View Reports in the Console

Test results will be printed in the terminal.

### 5.2 View Reports in Allure

```sh
allure serve target/allure-results
```

---

## 6. Run Test Cases Using GitHub Actions

### 6.1 Go to the [ns_sqa2 Repository](https://github.com/ZeroTull/ns_sqa2)

### 6.2 Navigate to the **Actions** Tab

### 6.3 Click **Execute Pinger Test Cases**

### 6.4 Click **Run Workflow** and Confirm by Clicking **Run Workflow** Again

#### **To Check Reports:**

### 6.5 Click **All Workflows**

### 6.6 Click **Pages Build and Deployment**

### 6.7 Click **[View Reports](https://ZeroTull.github.io/ns_sqa2/)** at the Deploy Section

## 7. Test Coverage and Bugs

A detailed list of **test coverage** and **reported bugs** is maintained in the following Google Sheet:

🔗 [Test Coverage & Bugs Sheet](https://docs.google.com/spreadsheets/d/1n0aArhWWt23icpuaUhhzXYnUWfgkP5NkKgCbRunTQW8/edit?gid=168947223#gid=168947223)

This document includes:

- Test cases
- Identified defects

---

