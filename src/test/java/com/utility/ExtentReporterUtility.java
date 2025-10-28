package com.utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterUtility {
	
	private static ExtentReports extentReports;//one report is more than enough that's why made it static
	private static ThreadLocal<ExtentTest> extentTest= new ThreadLocal<ExtentTest>();//go and create a thread local variable for individual tests
	
	public static void setupSparkReporter(String reportName) {
		
		ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") +"/" + reportName);
		extentReports = new ExtentReports();
		extentReports.attachReporter(extentSparkReporter);

	}
	
	public static void createExtentTest(String testName) {
		
		ExtentTest test= extentReports.createTest(testName);//creates a new entry in the report with the given test name and returns extent test object
		
//		extentTest is likely a ThreadLocal<ExtentTest> variable.
//
//		extentTest.set(test) assigns the newly created ExtentTest object to the current thread.
//
//		This allows parallel test execution without overwriting logs for other tests, as each thread maintains its own copy of the test log.
		extentTest.set(test);
			
	}
	
	public static ExtentTest getTest() {
		
		return extentTest.get();
	}
	
	public static void flushReports() {
		
		extentReports.flush();
		
	}
	
	}


