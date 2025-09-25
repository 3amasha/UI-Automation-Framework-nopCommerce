
# **UI Automation Framework-nopCommerce** 🚀

![Java](https://img.shields.io/badge/Java-21-orange)
![Maven](https://img.shields.io/badge/Maven-Dependency%20Manager-blue)
![Selenium](https://img.shields.io/badge/Selenium-WebDriver-green)
![TestNG](https://img.shields.io/badge/TestNG-Testing%20Framework-brightgreen)
![Allure](https://img.shields.io/badge/Allure-Reporting-purple)


> **Purpose:**
> A **full-stack test automation framework** designed to automate **UI testing** and **data-driven testing** for modern e-commerce applications, featuring **clean architecture**, **scalability**, and **reusability**.
> Tesing [nopCommerce Demo Store](https://demo.nopcommerce.com/) on both the official online site and a local deployment.

---

## **🌟 Features**

* **Page Object Model (POM)** with Fluent Design Pattern for clean and maintainable UI tests.
* **Data-Driven Testing** using JSON test data and dynamic data binding via **POJO classes**.
* **Parallel Test Execution** powered by TestNG & ThreadLocal WebDriver.
* **Allure Reports** for professional, visual reporting (with screenshots on failure).
* **Configurable Environment Setup** using Maven profiles.
* **Modular Structure:** Separate layers for UI, API, and test utilities.
* **Reusable Utilities:** JSON parsing, test data management, custom logging, and more.

---

## **🗂 Project Structure**

```
📦 automation-framework
 ┣ 📂 src
 │  ┣ 📂 main
 │  │  ┣ 📂 java
 │  │  │  ┣ 📂 base                # Base classes (BasePage) for reusable util methods
 │  │  │  ┣ 📂 config              # ConfigReader, EnvManager
 │  │  │  ┣ 📂 drivers             # DriverManager & BrowserFactory
 │  │  │  ┣ 📂 pages               # Page Object Model classes
 │  │  │  ┣ 📂 utils               # Utility classes (JsonUtils, Logger, WaitUtils)
 │  │  ┗ 📂 resources
 │  │     ┗ config.properties # Configurations (URLs, timeouts, etc.)
 │  │
 │  ┣ 📂 test
 │     ┣ 📂 java
 │     │  ┣ 📂 base                # BaseTest
 │     │  ┣ 📂 tests               # TestNG test classes
 │     │  ┗ 📂 dataproviders       # Data-driven test providers
 │     ┗ 📂 resources
 │        ┗ 📂 testdata            # JSON test data files
 │
 ┣ 📂 allure-results               # Auto-generated test reports
 ┣ 📜 pom.xml                       # Maven dependencies
 ┗ 📜 README.md                     # Documentation of the project
```

---

## **⚙️ Tech Stack**

| Layer                | Technology Used        |
| -------------------- | ---------------------- |
| Programming Language | **Java 21**            |
| Build Tool           | **Maven**              |
| UI Testing           | **Selenium WebDriver** |
| Test Framework       | **TestNG**             |
| Reporting            | **Allure Reports**     |
| Browser Support      | Microsoft Edge         |
| Data Storage         | JSON (for test data)   |

---


