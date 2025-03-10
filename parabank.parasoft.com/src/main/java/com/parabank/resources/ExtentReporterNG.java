package com.parabank.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {

    public static ExtentReports getReportObject()
    {
        String reportPath=System.getProperty("user.dir")+"//reports//index.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);

        reporter.config().setReportName("Prabank Web Automation Result");
        reporter.config().setDocumentTitle("Automation Test Result");

        ExtentReports extent=new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester","Pranay");
        return extent;
    }
}
