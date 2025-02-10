# Pinger Test Automation Framework

This project is an **automated testing framework** for the **Pinger application**. It validates the behavior of a tool
that pings endpoints and generates reports.

---

## 📖 Table of Contents

1. [Overview](#1-overview)
2. [Prerequisites](#2-prerequisites)
   - [Java Development Kit (JDK 21+)](#21-java-development-kit-jdk-21)
   - [Apache Maven](#22-apache-maven-build-tool)
   - [Git](#23-git-for-cloning-the-repository)
   - [Allure Report](#24-allure-report-for-viewing-test-results)
3. [Installation](#3-installation)
   - [Clone the Repository](#31-clone-the-repository)
   - [Navigate to the Project Folder](#32-navigate-to-the-project-folder)
   - [Install Dependencies](#33-install-dependencies)
4. [Running Tests](#4-running-tests)
   - [Run All Tests](#41-run-all-tests)
   - [Run a Specific Test](#42-run-a-specific-test)
   - [Run Tests with Logging](#43-run-tests-with-logging)
5. [Viewing Test Reports](#5-viewing-test-reports)
   - [View Reports in the Console](#51-view-reports-in-the-console)
   - [View Reports in Allure](#52-view-reports-in-allure)

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

## 2. Prerequisites

Before you start, **install the following tools**:

### Required Software:

### 2.1 Java Development Kit (JDK 21+)

- Download: [Adoptium JDK](https://adoptium.net/)
- Verify installation:

```sh
java -version
```

### 2.2 Apache Maven (Build tool)

- Download: [Maven Official Site](https://maven.apache.org/)
- Verify installation:

```sh
mvn -version
```

### 2.3 Git (For cloning the repository)

- Download: [Git Website](https://git-scm.com/)
- Verify installation:

```sh
git --version
```

### 2.4 Allure Report (For viewing test results)

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

## 3 Installation

### 3.1 Clone the Repository

```sh
git clone https://github.com/your-repo/pinger-automation.git
```

### 3.2 Navigate to the Project Folder

```sh
cd pinger-automation
```

### 3.3 Install Dependencies

```sh
mvn clean install
```

## 4 Running Tests

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

## 5 Viewing Test Reports

### 5.1 View Reports in the Console

```
Test results will be printed in the terminal.
```

### 5.2 View Reports in Allure

```sh
allure serve target/allure-results
```
