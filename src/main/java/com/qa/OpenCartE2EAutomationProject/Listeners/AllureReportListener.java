package com.qa.OpenCartE2EAutomationProject.Listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.qa.OpenCartE2EAutomationProject.DriverFactory.DriverFactory;

import io.qameta.allure.Attachment;

public class AllureReportListener implements ITestListener {
	
	private static final Logger log = LogManager.getLogger(AllureReportListener.class);
	
	private static String getTestMethodName(ITestResult iTestResult) {
		return iTestResult.getMethod().getConstructorOrMethod().getName();
	}

	// Text attachments for Allure
	@Attachment(value = "Page screenshot", type = "image/png")
	public byte[] saveScreenshotPNG(WebDriver driver) {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	}

	// Text attachments for Allure
	@Attachment(value = "{0}", type = "text/plain")
	public static String saveTextLog(String message) {
		return message;
	}

	// HTML attachments for Allure
	@Attachment(value = "{0}", type = "text/html")
	public static String attachHtml(String html) {
		return html;
	}

	@Override
	public void onStart(ITestContext iTestContext) {
		log.info("Execution started " + iTestContext.getName());
	}

	@Override
	public void onFinish(ITestContext iTestContext) {
		log.info("Execution finished " + iTestContext.getName());
	}

	@Override
	public void onTestStart(ITestResult iTestResult) {
		log.info("Execution started " + getTestMethodName(iTestResult));
	}

	@Override
	public void onTestSuccess(ITestResult iTestResult) {
		log.info( getTestMethodName(iTestResult) + " succeed");
	}

	@Override
	public void onTestFailure(ITestResult iTestResult) {
		log.info(getTestMethodName(iTestResult) + " failed");
		Object testClass = iTestResult.getInstance();
		if (DriverFactory.getThreadLocalDriver() instanceof WebDriver) {
			log.info("Screenshot captured for test case:" + getTestMethodName(iTestResult));
			saveScreenshotPNG(DriverFactory.getThreadLocalDriver());
		}
		// Save a log on allure.
		saveTextLog(getTestMethodName(iTestResult) + " failed and screenshot taken!");
	}

	@Override
	public void onTestSkipped(ITestResult iTestResult) {
		log.info("Execution" + getTestMethodName(iTestResult) + " skipped");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
		log.info("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
	}
}
