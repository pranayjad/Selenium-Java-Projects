
# 🛍️ Selenium TestNG - Cucumber Framework

This is Selenium Java automation testing framework can be leveraged for any UI automation testing:

- ✅ Java 23
- ✅ Maven
- ✅ Selenium WebDriver
- ✅ Cucumber BDD (Gherkin syntax)
- ✅ TestNG (as runner)
- ✅ Apache POI for Excel data
- ✅ Extent Reports with Screenshot Support
- ✅ Retry Analyzer for flaky test handling
- ✅ Tag-based test execution
- ✅ GitHub Integration

![Java](https://img.shields.io/badge/Java-23+-brightgreen)
![Maven](https://img.shields.io/badge/Maven-Build-blue)
![TestNG](https://img.shields.io/badge/TestNG-7.9-orange)
![Cucumber](https://img.shields.io/badge/Cucumber-BDD-green)
![Selenium](https://img.shields.io/badge/Selenium-WebDriver-yellow)

---

## 📁 Project Structure
<img width="576" alt="image" src="https://github.com/user-attachments/assets/1a114fde-9a9c-44bd-a819-33f247a12bad" />


Selenium_TestNG_Cucumber_Template/
├── pom.xml                          ← Maven build file
├── testSuites/
│   └── testng.xml                   ← TestNG suite configuration
├── reports/
│   ├── ExtentReport.html                   ← Extent report (output)
│   ├── Cucumber.html                       ← Cucumber report (output)
│   └── snapshot.png                        ← Error snapshot (output)
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/projectName/
│   │           ├── abstractComponents/
│   │           │   └── AbstractComponent.java   ← Reusable UI base components
│   │           ├── pageobjects/
│   │           │   └── PageObject.java          ← Page Object class(es)
│   │           └── resources/
│   │               ├── ExtentReporterNG.java    ← Extent report setup
│   │               └── GlobalData.properties    ← Configuration file
│   └── test/
│       └── java/
│           └── com/projectName/
│               ├── cucumber/
│               │   ├── TestNGTestRunner.java    ← Cucumber + TestNG runner
│               │   └── test.feature             ← Gherkin feature file
│               ├── data/
│               │   ├── data.json                ← Test data (JSON)
│               │   └── data.xlsx                ← Test data (Excel)
│               ├── stepDefinition/
│               │   └── StepDefinitionImpl.java  ← Step definitions (Cucumber)
│               ├── testComponents/
│               │   ├── BaseTest.java            ← Common setup/teardown
│               │   ├── Listeners.java           ← TestNG listeners
│               │   └── Retry.java               ← Retry logic on failure
│               └── tests/
│                   └── Test1.java               ← Traditional TestNG test class


📸 Reporting & Logs
✅ Extent Report: reports/ExtentReport.html
✅ Cucumber HTML: reports/Cucumber.html
✅ Failure Screenshots: reports/snapshot.png

📊 Data-Driven Testing
✅ Excel file: testdata/AmazonSearchData.xlsx

✅ Powered by: Apache POI

✅ Scenario: test.feature


👨‍💻 Author
👤 Name: Pranay Jadhav

🌐 GitHub: pranayjad

🔮 Future Enhancements
✅ Allure Reporting Integration
✅ Jenkins CI/CD Pipeline
