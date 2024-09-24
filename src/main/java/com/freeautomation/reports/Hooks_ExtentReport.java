/*
 * Copyright (c) 2024. Created by Chirag Singh.
 */
package com.freeautomation.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.freeautomation.base.APIFactoryImplementation;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks_ExtentReport {

    /*
        We are using extent adapter to generate the extent report.
        We commented this file. If incase we need to use extent report
        without extent adapter then we can uncommented the below lines
     */

/*    private APIFactoryImplementation obj;
    private static ExtentReports extentReports;
    private static ExtentSparkReporter htmlReporter;
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();


     @Before
    public  void setup(Scenario scenario)
    {
        obj = new APIFactoryImplementation();
        obj.invokeAPI();
        if (extentReports == null) {
            // Initialize the ExtentHtmlReporter
            htmlReporter = new ExtentSparkReporter("target/ExtentReports.html");
            extentReports = new ExtentReports();
            extentReports.attachReporter(htmlReporter);
        }

        // Create a new test for each scenario
        ExtentTest test = extentReports.createTest(scenario.getName());
        extentTest.set(test);
    }

    @AfterStep
    public void logStep(Scenario scenario) {
        if (scenario.isFailed()) {
            extentTest.get().fail("Step failed: " + scenario.getStatus().name());
        } else {
            extentTest.get().pass("Step passed: " + scenario.getStatus().name());
        }
    }

    @After
    public void tearDown(Scenario scenario)
    {
        if (scenario.isFailed()) {
            extentTest.get().fail("Scenario failed");
        } else {
            extentTest.get().pass("Scenario passed");
        }
        extentReports.flush();
        obj.releaseRequest();
    } */
}
