package com.projectName.cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/com/projectName/cucumber",glue = "projectName.stepDefinition",
        monochrome = true,tags="@Regression" ,plugin = {"html:reports/Cucumber.html"})
public class TestNGTestRunner extends AbstractTestNGCucumberTests {
}