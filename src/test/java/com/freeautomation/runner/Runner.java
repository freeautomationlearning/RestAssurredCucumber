/*
 * Copyright (c) 2024. Designed by Chirag Singh
 */

package com.freeautomation.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = "src/test/resources/features", // Path to your feature files
        glue = {"com.freeautomation.stepdefinations","com.freeautomation.reports"},                 // Path to your step definitions
//        plugin = {"pretty", "html:target/cucumber-reports.html", "json:target/cucumber.json"}, //Cucumber report plugin
        plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        tags = "@get or @post or @put or @patch or @delete",
//        tags = "@get",
        monochrome = true                         // For better console output
)
public class Runner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}

