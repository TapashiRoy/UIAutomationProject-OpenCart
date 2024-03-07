package com.qa.OpenCartE2EAutomationProject.Pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.OpenCartE2EAutomationProject.Constants.AppConstants;
import com.qa.OpenCartE2EAutomationProject.DriverFactory.DriverFactory;
import com.qa.OpenCartE2EAutomationProject.Utils.ElementUtil;


public class Accountpage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private static final Logger log = LogManager.getLogger(Accountpage.class);

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
		log.info("Account Page Title is :" + title);
		return title;
	}

	public String getAccountPageUrl() {
		String url = eleUtil.waitURL(AppConstants.ACCOUNT_PAGE_URL, AppConstants.DEFAULT_SHORT_TIME_OUT);
		log.info("Account Page URL is :" + url);
		return url;
	}

	public boolean isMyAccountsHeadingDisplayed() {
		boolean flag = eleUtil.isdisplayed(accountHeading);
		if(flag) {
			log.info("My Account Heading is displayed in the page. ");
		}
		else {
			log.info("My Account Heading is missing in the page. ");
		}
		return flag;
	}

	public boolean isOrdersHeadingDisplayed() {
		boolean flag = eleUtil.isdisplayed(ordersHeading);
		if(flag) {
			log.info("Orders Heading is displayed in the page. ");
		}
		else {
			log.info("Orders Heading is missing in the page. ");
		}
		return flag;
	}

	public boolean isMyAffiliateAccountHeadingDisplayed() {
		boolean flag = eleUtil.isdisplayed(affiliateHeading);
		if(flag) {
			log.info("My Affiliate Account Heading is displayed in the page. ");
		}
		else {
			log.info("My Affiliate Account Heading is missing in the page. ");
		}
		return flag;
	}

	public boolean isNewsLetterHeadingDisplayed() {
		boolean flag = eleUtil.isdisplayed(newsletterHeading);
		if(flag) {
			log.info("NewsLetter Heading is displayed in the page. ");
		}
		else {
			log.info("NewsLetter Heading is missing in the page. ");
		}
		return flag;
	}

	public int verifyHeadersCount() {
		int count = eleUtil.waitPresenceOfAllElements(accountPageHeaders, AppConstants.DEFAULT_SHORT_TIME_OUT).size();
		log.info("Count of HeadersList in Accounts page: " + count);
		return count;
	}

	public boolean isRightPanelDisplayed() {
		boolean flag = eleUtil.isdisplayed(rightPanelList);
		if(flag) {
			log.info("Right Panel of Links is displayed in the page. ");
		}
		else {
			log.info("Right Panel of Links is missing in the page. ");
		}
		return flag;
	}

	public boolean isSearchDisplayed() {
		boolean flag = eleUtil.isdisplayed(search);
		if (flag) {
			log.info("Search exists in the page. ");
		} else {
			log.info("Search is missing in the page. ");
		}
		return flag;
	}

	public boolean isLogoutLinkDisplayed() {
		boolean flag = eleUtil.isdisplayed(logoutLink);
		if(flag) {
			log.info("Logout Link is displayed in the page. ");
		}
		else {
			log.info("Logout Link is missing in the page. ");
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
			log.info("Search is missing in the page. ");
			return null;
		}
	}



}
