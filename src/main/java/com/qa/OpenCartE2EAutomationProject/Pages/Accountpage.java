package com.qa.OpenCartE2EAutomationProject.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.OpenCartE2EAutomationProject.Constants.AppConstants;
import com.qa.OpenCartE2EAutomationProject.Utils.ElementUtil;


public class Accountpage {
	private WebDriver driver;
	private ElementUtil eleUtil;

	public Accountpage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	// Locators for Accountpage
	private By accountHeading = By.xpath("//h2[normalize-space()='My Account']");
	private By ordersHeading = By.xpath("//h2[normalize-space()='My Orders']");
	private By affiliateHeading = By.xpath("//h2[normalize-space()='My Affiliate Account']");
	private By newsletterHeading = By.xpath("//h2[normalize-space()='Newsletter']");
	private By accountPageHeaders = By.xpath("//div[@id='content']/h2");
	private By rightPanelList = By.xpath("//aside[@id='column-right']//a");
	private By logoutLink = By.linkText("Logout");
	private By search = By.xpath("//input[@name='search']");
	private By searchIcon = By.xpath("//i[@class='fa fa-search']");


	//Accountpage Actions
	public String getAccountPageTitle() {
		String title = eleUtil.waitTitleIsAndFetch(AppConstants.ACCOUNT_PAGE_TITLE,AppConstants.DEFAULT_SHORT_TIME_OUT);
		System.out.println("Account Page Title is :" + title);
		return title;
	}

	public String getAccountPageUrl() {
		String url = eleUtil.waitURL(AppConstants.ACCOUNT_PAGE_URL, AppConstants.DEFAULT_SHORT_TIME_OUT);
		System.out.println("Account Page URL is :" + url);
		return url;
	}

	public boolean isMyAccountsHeadingDisplayed() {
		boolean flag = eleUtil.isdisplayed(accountHeading);
		if(flag) {
			System.out.println("My Account Heading is displayed in the page. ");
		}
		else {
			System.out.println("My Account Heading is missing in the page. ");
		}
		return flag;
	}

	public boolean isOrdersHeadingDisplayed() {
		boolean flag = eleUtil.isdisplayed(ordersHeading);
		if(flag) {
			System.out.println("Orders Heading is displayed in the page. ");
		}
		else {
			System.out.println("Orders Heading is missing in the page. ");
		}
		return flag;
	}

	public boolean isMyAffiliateAccountHeadingDisplayed() {
		boolean flag = eleUtil.isdisplayed(affiliateHeading);
		if(flag) {
			System.out.println("My Affiliate Account Heading is displayed in the page. ");
		}
		else {
			System.out.println("My Affiliate Account Heading is missing in the page. ");
		}
		return flag;
	}

	public boolean isNewsLetterHeadingDisplayed() {
		boolean flag = eleUtil.isdisplayed(newsletterHeading);
		if(flag) {
			System.out.println("NewsLetter Heading is displayed in the page. ");
		}
		else {
			System.out.println("NewsLetter Heading is missing in the page. ");
		}
		return flag;
	}

	public int verifyHeadersCount() {
		int count = eleUtil.waitPresenceOfAllElements(accountPageHeaders, AppConstants.DEFAULT_SHORT_TIME_OUT).size();
		System.out.println("Count of HeadersList in Accounts page: " + count);
		return count;
	}

	public boolean isRightPanelDisplayed() {
		boolean flag = eleUtil.isdisplayed(rightPanelList);
		if(flag) {
			System.out.println("Right Panel of Links is displayed in the page. ");
		}
		else {
			System.out.println("Right Panel of Links is missing in the page. ");
		}
		return flag;
	}

	public boolean isSearchDisplayed() {
		boolean flag = eleUtil.isdisplayed(search);
		if (flag) {
			System.out.println("Search exists in the page. ");
		} else {
			System.out.println("Search is missing in the page. ");
		}
		return flag;
	}

	public boolean isLogoutLinkDisplayed() {
		boolean flag = eleUtil.isdisplayed(logoutLink);
		if(flag) {
			System.out.println("Logout Link is displayed in the page. ");
		}
		else {
			System.out.println("Logout Link is missing in the page. ");
		}
		return flag;
	}

	public ProductSearchpage doSearch(String searchKey) {
		if(isSearchDisplayed()) {
			eleUtil.dosendKeys(search, searchKey);
			eleUtil.doclick(searchIcon);
			return new ProductSearchpage(driver);
		}
		else {
			System.out.println("Search is missing in the page. ");
			return null;
		}
	}



}
