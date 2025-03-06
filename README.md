# AutomanTask_Robusta
# Playwright Java Test Automation

This project is a test automation framework built using **Playwright with Java**, **TestNG**, and **Maven**. It supports **parallel execution**, uses the **Page Object Model (POM)** design pattern, and includes a `BasePage` for handling common actions like clicks, input, etc.

## Prerequisites

Before running the tests, ensure you have the following installed:
- **Java JDK 22 or higher**
- **Maven 3.8.x or higher**

## Setup

1. **Clone the repository** (if applicable):
   ```bash
   git clone <repository-url>
   cd <project-directory>
   mvn clean install
   mvn test -DsuiteXmlFile=testNG.xml

 ##  Project Structure
src/main/java/pages: Contains the Page Object Model (POM) classes. Each class represents a page in the application and contains locators and methods for interacting with that page.

src/main/java/base: Contains the BasePage class, which includes reusable methods for common actions like:

click(locator): Clicks on a web element.

fill(locator, text): Types text into an input field.

navigateTo(url): Navigates to a specific URL.

And more.

src/main/resources: Contains configuration files like config.properties, where you can define environment-specific settings.

src/test/java/tests: Contains TestNG test classes. Each class contains test methods that interact with the pages defined in the pages directory.

testNG.xml: The TestNG configuration file used to define test suites, parallel execution settings, and other configurations.

Page Object Model (POM)
The framework follows the Page Object Model (POM) design pattern to enhance maintainability and readability.

Each page in the application has its own class in the src/main/java/pages directory.

These classes extend the BasePage to reuse common actions like clicking, typing, etc.

BasePage
The BasePage class is the parent class for all page classes.

It contains reusable methods for common actions, such as:

click(locator): Clicks on a web element.

fill(locator, text): Types text into an input field.

navigateTo(url): Navigates to a specific URL.

Other utility methods for handling web elements.
