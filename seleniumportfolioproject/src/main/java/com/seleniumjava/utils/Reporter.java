package com.seleniumjava.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Reporter {
    private static ExtentReports extent;
    private static ExtentTest test;

    // Inicia el reporte
    public static void startReport(String reportName) {
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("reports/" + reportName + ".html");
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
    }

    // Establece si el caso de prueba aprueba en el reporte.
    public static void logTest(String testName, boolean status) {
        test = extent.createTest(testName);
        if (status) {
            test.pass("Test Passed");
        } else {
            test.fail("Test Failed");
        }
    }

    // Finaliza el reporte.
    public static void endReport() {
        extent.flush();
    }
}