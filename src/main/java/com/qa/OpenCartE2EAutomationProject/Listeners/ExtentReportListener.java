package com.qa.OpenCartE2EAutomationProject.Listeners;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.qa.OpenCartE2EAutomationProject.DriverFactory.DriverFactory;

public class ExtentReportListener extends DriverFactory implements ITestListener {

	private static final Logger log = LogManager.getLogger(ExtentReportListener.class);
	
	private static final String OUTPUT_FOLDER = "./reports/";
	private static final String FILE_NAME = "TestExecutionReport.html";

	private static ExtentReports extent = init();
	public static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
	private static ExtentReports extentReports;

	private static ExtentReports init() {

		Path path = Paths.get(OUTPUT_FOLDER);
		// if directory exists?
		if (!Files.exists(path)) {
			try {
				Files.createDirectories(path);
			} catch (IOException e) {
				// fail to create directory
				e.printStackTrace();
			}
		}
		extentReports = new ExtentReports();
		ExtentSparkReporter reporter = new ExtentSparkReporter(OUTPUT_FOLDER + FILE_NAME);
		reporter.config().setReportName("OpenCart Automation Test Results");
		extentReports.attachReporter(reporter);
		extentReports.setSystemInfo("System", "Windows");
		extentReports.setSystemInfo("Author", "Tapashi Roy");
		extentReports.setSystemInfo("Build#", "1.1");
		extentReports.setSystemInfo("Team", "QA");
		// extentReports.setSystemInfo("ENV NAME", System.getProperty("env"));
		return extentReports;
	}

	@Override
	public synchronized void onTestStart(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		String qualifiedName = result.getMethod().getQualifiedName();
		int last = qualifiedName.lastIndexOf(".");
		int mid = qualifiedName.substring(0, last).lastIndexOf(".");
		String className = qualifiedName.substring(mid + 1, last);

		log.info(methodName + " started!");
		ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName(),
				result.getMethod().getDescription());

		extentTest.assignCategory(result.getTestContext().getSuite().getName());
		/*
		 * methodName = StringUtils.capitalize(StringUtils.join(StringUtils.
		 * splitByCharacterTypeCamelCase(methodName), StringUtils.SPACE));
		 */
		extentTest.assignCategory(className);
		test.set(extentTest);
		test.get().getModel().setStartTime(getTime(result.getStartMillis()));

	}

	@Override
	public synchronized void onStart(ITestContext context) {
		log.info("Test Suite started!");

	}

	@Override
	public synchronized void onFinish(ITestContext context) {
		log.info(("Test Suite is ending!"));
		extent.flush();
		test.remove();

	}

	@Override
	public synchronized void onTestSuccess(ITestResult result) {
		log.info((result.getMethod().getMethodName() + " passed!"));
		test.get().pass("Test passed");
		test.get().getModel().setEndTime(getTime(result.getEndMillis()));

	}

	@Override
	public synchronized void onTestFailure(ITestResult result) {
		log.info((result.getMethod().getMethodName() + " failed!"));
		test.get().fail(result.getThrowable(), MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot()).build());
		test.get().getModel().setEndTime(getTime(result.getEndMillis()));
	}

	@Override
	public synchronized void onTestSkipped(ITestResult result) {
		log.info((result.getMethod().getMethodName() + " skipped!"));
		test.get().skip(result.getThrowable(), MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot()).build());
		test.get().getModel().setEndTime(getTime(result.getEndMillis()));

	}

	@Override
	public synchronized void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		log.info(("onTestFailedButWithinSuccessPercentage for " + result.getMethod().getMethodName()));
	}

	private Date getTime(long millis) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millis);
		return calendar.getTime();
	}

}
