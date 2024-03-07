package com.qa.OpenCartE2EAutomationProject.Tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.OpenCartE2EAutomationProject.Base.BaseTest;
import com.qa.OpenCartE2EAutomationProject.Constants.AppConstants;

import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;

@Epic("Login Page for OpenCart Project")
@Story("US101 : Login Page Scenarios")
public class LoginPageTest extends BaseTest {
	
	private static final Logger log = LogManager.getLogger(LoginPageTest.class);
	
	@Test(priority=1)
	public void loginPageTitleTest() {
		String actualTitle = loginPage.getLoginPageTitle();
		log.info("The actual Login Page Title is : " +actualTitle);
		Assert.assertEquals(actualTitle, AppConstants.LOGIN_PAGE_TITLE);
	}

	@Test(priority=2)
	public void loginPageUrlTest() {
		String actualUrl = loginPage.getLoginPageUrl();
		log.info("The actual Login Page URL is : " + actualUrl);
		Assert.assertTrue(actualUrl.contains(AppConstants.LOGIN_PAGE_URL));
	}

	@Test(priority=3)
	public void loginPageTextDisplayedTest() {
		boolean flag = loginPage.isLoginPageTextIsDisplayed();
		Assert.assertTrue(flag);
	}

	@Test(priority=4)
	public void loginPageForgotPasswordExistsTest() {
		boolean flag = loginPage.isForgotPasswordLinkExists();
		Assert.assertTrue(flag);
	}

	@Step("Login Scenario with username : {0} and password :{1}")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority=5)
	public void verifyloginTest() {
		accountPage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
		Assert.assertTrue(accountPage.isLogoutLinkDisplayed());
	}

}
