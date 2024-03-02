package com.qa.OpenCartE2EAutomationProject.Tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.OpenCartE2EAutomationProject.Base.BaseTest;
import com.qa.OpenCartE2EAutomationProject.Constants.AppConstants;
import com.qa.OpenCartE2EAutomationProject.Utils.ExcelUtil;

public class RegisterPageTest extends BaseTest {

	@BeforeClass
	public void registerPageSetup() {
		registerPage = loginPage.doRegister();
	}

	@Test(priority = 1)
	public void registerPageTitleTest() {
		String actualTitle = registerPage.getRegisterPageTitle();
		Assert.assertEquals(actualTitle, "Register Account");
	}

	@Test(priority = 2)
	public void registerPageUrlTest() {
		String actualUrl = registerPage.getRegisterPageUrl();
		Assert.assertTrue(actualUrl.contains("account/register"));
	}

	@Test(priority = 3)
	public void registerPageTextIsDisplayedTest() {
		boolean flag = registerPage.isRegisterPageTextIsDisplayed();
		Assert.assertTrue(flag);
	}

	@Test(priority = 4)
	public void loginLinkDisplayedTest() {
		boolean flag = registerPage.isLoginLinkDisplayed();
		Assert.assertTrue(flag);
	}

	public String randomEmailGenerator() {
		String autoEmail = "TestAutomation" + System.currentTimeMillis() + "@gmail.com";
		return autoEmail;
	}

	@DataProvider
	public Object[][] registerData() {
		Object[][] registerData = ExcelUtil.getSheetData(AppConstants.REGISTER_SHEET_NAME);
		return registerData;
	}

	@Test(dataProvider = "registerData", priority = 5)
	public void registerUserTest(String fname, String lname, String phone, String pwd, String subscribe) {
		boolean flag = registerPage.doRegister(fname, lname, randomEmailGenerator(), phone, pwd, subscribe);
		Assert.assertTrue(flag);
	}

}
