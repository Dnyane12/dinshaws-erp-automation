# Dinshaws-ERP Selenium Automation Framework

This project contains an automation framework developed using Selenium WebDriver with Java and TestNG for testing ERP web application business workflows.
The framework automates major Inventory and Sales module flows to validate end-to-end ERP business transactions.

## Tech Stack

- Java
- Selenium WebDriver
- TestNG
- Maven
- Page Object Model (POM)
- GitHub
- CI/CD

## Automated Business Flows
### Sales Module Automation
The following end-to-end sales workflow has been automated:

1. Multi Sale Order Creation
2. Sale Dispatch
3. Tax Invoice Creation


### Inventory Module Automation
The following inventory workflow has been automated:

1. Purchase Order Creation
2. Goods Receipt Note (GRN)
3. GRN Posting
4. Purchase Return Note (PRN)


**ERP-Selenium-Automation-Framework**
│
├── src
│   ├── main
│   │   └── java
│   │       └── base
│   │           └── BaseClass.java
│   │
│   └── test
│       └── java
│           ├── pages
│           │     ├── LoginPage.java
│           │     ├── SalesOrderPage.java
│           │     ├── SaleDispatchPage.java
│           │     ├── TaxInvoicePage.java
│           │     ├── PurchaseOrderPage.java
│           │     ├── GRNPage.java
│           │     └── PurchaseReturnPage.java
│           │
│           ├── testcases
│           │     ├── SalesFlowTest.java
│           │     └── InventoryFlowTest.java
│           │
│           ├── utilities
│           │     ├── DriverFactory.java
│           │     ├── ConfigReader.java
│           │     └── TestDataUtil.java
│           │
│           └── listeners
│                 └── TestListener.java
│
├── testng.xml
├── pom.xml
├── README.md
└── reports
      └── test-output

## How to Execute Tests
1. Clone the repository
2. Import project into IDE
3. Install Maven dependencies
4. Run TestNG suite file

## Author
Dnyaneshwari Nare
Junior QA Engineer
