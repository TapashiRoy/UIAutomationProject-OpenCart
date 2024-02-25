package com.qa.OpenCartE2EAutomationProject.Listeners;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.qa.OpenCartE2EAutomationProject.DriverFactory.DriverFactory;

import io.qameta.allure.Attachment;

public class AllureReportListener implements ITestListener {
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
		System.out.println("Execution started " + iTestContext.getName());
	}

	@Override
	public void onFinish(ITestContext iTestContext) {
		System.out.println("Execution finished " + iTestContext.getName());
	}

	@Override
	public void onTestStart(ITestResult iTestResult) {
		System.out.println("Execution started " + getTestMethodName(iTestResult));
	}

	@Override
	public void onTestSuccess(ITestResult iTestResult) {
		System.out.println( getTestMethodName(iTestResult) + " succeed");
	}

	@Override
	public void onTestFailure(ITestResult iTestResult) {
		System.out.println(getTestMethodName(iTestResult) + " failed");
		Object testClass = iTestResult.getInstance();
		if (DriverFactory.getThreadLocalDriver() instanceof WebDriver) {
			System.out.println("Screenshot captured for test case:" + getTestMethodName(iTestResult));
			saveScreenshotPNG(DriverFactory.getThreadLocalDriver());
		}
		// Save a log on allure.
		saveTextLog(getTestMethodName(iTestResult) + " failed and screenshot taken!");
	}

	@Override
	public void onTestSkipped(ITestResult iTestResult) {
		System.out.println("Execution" + getTestMethodName(iTestResult) + " skipped");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
		System.out.println("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
	}
}
