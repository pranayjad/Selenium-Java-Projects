package com.projectName.cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/template/cucumber",glue = "practice.stepDefinition",
        monochrome = true,tags="@Regression" ,plugin = {"html:reports/cucumber.html"})
public class TestNGTestRunner extends AbstractTestNGCucumberTests {
}