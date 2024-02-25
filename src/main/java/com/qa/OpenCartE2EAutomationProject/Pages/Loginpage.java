package com.qa.OpenCartE2EAutomationProject.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.OpenCartE2EAutomationProject.Constants.AppConstants;
import com.qa.OpenCartE2EAutomationProject.Utils.ElementUtil;

public class Loginpage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	public Loginpage(WebDriver driver) {
		this.driver=driver;
		eleUtil = new ElementUtil(driver);
	}	
	
	//Locators for Login Page
	private By loginPageText = By.xpath("//h2[normalize-space()='Returning Customer']");
	private By registerPageText = By.xpath("//h2[normalize-space()='New Customer']");
	private By emailField = By.id("input-email");
	private By pwdField = By.id("input-password");
	private By forgotPwdLink = By.linkText("Forgotten Password");
	private By loginButton = By.xpath("//input[@value='Login']"); 
	private By continueRegisterButton = By.xpath("//a[normalize-space()='Continue']");
	private By landingPageIcon = By.xpath("//i[@class='fa fa-home']");
	
	//Page Action methods
	public String getLoginPageTitle() {
		String title = eleUtil.waitTitleIsAndFetch(AppConstants.LOGIN_PAGE_TITLE,AppConstants.DEFAULT_SHORT_TIME_OUT);		
		System.out.println("Login Page Title is :" + title);
		return title;
	}
	
	public String getLoginPageUrl() {
		String url = eleUtil.waitURL(AppConstants.LOGIN_PAGE_URL, AppConstants.DEFAULT_SHORT_TIME_OUT);
		System.out.println("Login Page URL is :" + url);
		return url;		
	}
	
	public boolean isForgotPasswordLinkExists() {
		boolean flag = eleUtil.dofindElement(forgotPwdLink, AppConstants.DEFAULT_SHORT_TIME_OUT).isDisplayed();
		if(flag) {
			System.out.println("Forgot Password Link exists in the page. ");
		}
		else {
			System.out.println("Forgot Password Link is missing in the page. ");
		}
		return flag;
	}
	
	public boolean isRegisterPageTextIsDisplayed() {		
		boolean flag =eleUtil.dofindElement(loginPageText, AppConstants.DEFAULT_SHORT_TIME_OUT).isDisplayed();
		return flag;
	}	
	
	public boolean isLoginPageTextIsDisplayed() {
		boolean flag =eleUtil.dofindElement(registerPageText, AppConstants.DEFAULT_SHORT_TIME_OUT).isDisplayed();
		return flag;
	}	
	
	public Accountpage doLogin(String userName, String password) {
		eleUtil.waitPresenceOfElement(emailField, AppConstants.DEFAULT_SHORT_TIME_OUT).sendKeys(userName);
		eleUtil.dosendKeys(pwdField, password);
		eleUtil.doclick(loginButton);		
		return new Accountpage(driver);
	}	
	
	public Registerpage doRegister() {		
		eleUtil.doclick(continueRegisterButton);
		return new Registerpage(driver);
	}
	
	public Landingpage doClickLandingPage() {
		eleUtil.doclick(landingPageIcon);		
		return new Landingpage(driver);
	}
}
