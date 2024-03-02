package com.qa.OpenCartE2EAutomationProject.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.OpenCartE2EAutomationProject.Constants.AppConstants;
import com.qa.OpenCartE2EAutomationProject.Utils.ElementUtil;

public class Registerpage {
	private WebDriver driver;
	private ElementUtil eleUtil;

	public Registerpage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	// Locators for Login Page
	private By registerPageText = By.xpath("//h1[normalize-space()='Register Account']");
	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmPassword = By.id("input-confirm");

	private By subscribeYes = By.xpath("//label[normalize-space()='Yes']");
	private By subscribeNo = By.xpath("//label[normalize-space()='No']");

	private By privacyPolicyCheckBox = By.xpath("//input[@name='agree']");
	private By continueButton = By.xpath("//input[@type='submit']");
	private By successMessage = By.cssSelector("div#content h1");
	private By loginLink = By.xpath("//a[@class='list-group-item'][normalize-space()='Login']");
	private By logoutLink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");

	//Register Page Methods
	public String getRegisterPageTitle() {
		String title = eleUtil.waitTitleContainsAndFetch(AppConstants.REGISTER_PAGE_TITLE, AppConstants.DEFAULT_SHORT_TIME_OUT);
		System.out.println("Register Page Title is :" + title);
		return title;
	}

	public String getRegisterPageUrl() {
		String url = eleUtil.waitURL(AppConstants.REGISTER_PAGE_URL, AppConstants.DEFAULT_SHORT_TIME_OUT);
		System.out.println("Register Page URL is :" + url);
		return url;
	}

	public boolean isRegisterPageTextIsDisplayed() {
		boolean flag =eleUtil.isdisplayed(registerPageText);
		return flag;
	}

	public boolean isLoginLinkDisplayed() {
		boolean flag =eleUtil.isdisplayed(loginLink);
		return flag;
	}

	public boolean doRegister(String fname, String lname, String mail, String phone, String pwd, String subscribe) {
		eleUtil.waitVisibilityElement(firstName, AppConstants.DEFAULT_SHORT_TIME_OUT).sendKeys(fname);
		eleUtil.dosendKeys(lastName, lname);
		eleUtil.dosendKeys(email, mail);
		eleUtil.dosendKeys(telephone, phone);

		eleUtil.dosendKeys(password, pwd);
		eleUtil.dosendKeys(confirmPassword, pwd);

		if(subscribe.equalsIgnoreCase("Yes")) {
			eleUtil.doclick(subscribeYes);
		} else {
			eleUtil.doclick(subscribeNo);
		}
		eleUtil.doActionsClick(privacyPolicyCheckBox);
		eleUtil.doclick(continueButton);

		String successMsg = eleUtil.waitForElementIsVisibleAndEnable(successMessage, AppConstants.DEFAULT_SHORT_TIME_OUT).getText();
		System.out.println("User received User Registration Success Message : " + successMsg);

		if(successMsg.contains(AppConstants.CUSTOMER_REGISTRATION_SUCCESS_MSG)) {
			eleUtil.doclick(logoutLink);
			eleUtil.doclick(registerLink);
			return true;
		}
		return false;
	}




}
