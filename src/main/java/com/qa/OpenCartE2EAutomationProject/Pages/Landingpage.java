package com.qa.OpenCartE2EAutomationProject.Pages;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.OpenCartE2EAutomationProject.Constants.AppConstants;
import com.qa.OpenCartE2EAutomationProject.Utils.ElementUtil;

public class Landingpage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private static final Logger log = LogManager.getLogger(Landingpage.class);

	public Landingpage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	// Locators for Landing Page
	private By currency = By.xpath("//span[normalize-space()='Currency']");
	private By currencyDropDownMenu = By.xpath("//ul[@class='dropdown-menu']/li");
	private By contactNumber = By.cssSelector("i.fa.fa-phone");
	private By myAccountLink = By.cssSelector("i.fa.fa-user");
	private By myAccountMenuItems = By.xpath("//ul[@class='dropdown-menu dropdown-menu-right']//li");
	private By wishList = By.id("wishlist-total");
	private By shoppingCart = By.xpath("//span[normalize-space()='Shopping Cart']");
	private By checkout = By.xpath("//span[normalize-space()='Checkout']");
	private By logo = By.xpath("//img[@title='naveenopencart']");
	private By headerMenuItems = By.xpath("//ul[@class='nav navbar-nav']/li");
	private By featuredProducts = By.xpath("//div[@class='product-thumb transition']//div//a//img");
	private By footerMenuItems = By.xpath("//footer//div[@class='container']//ul//li");
	private By footerText = By.xpath("//footer//p");

	// Landing Page Actions

	public String getLandingPageTitle() {
		String title = eleUtil.waitTitleIsAndFetch(AppConstants.LANDING_PAGE_TITLE,AppConstants.DEFAULT_SHORT_TIME_OUT);
		log.info("Landing Page Title is :" + title);
		return title;
	}

	public String getLandingPageUrl() {
		String url = eleUtil.waitURL(AppConstants.LANDING_PAGE_URL, AppConstants.DEFAULT_SHORT_TIME_OUT);
		log.info("Landing Page URL is :" + url);
		return url;
	}

	public boolean isCurrencyDropDownMenuDisplayed() {
		eleUtil.waitPresenceOfElement(currency, AppConstants.DEFAULT_SHORT_TIME_OUT);
		boolean flag = eleUtil.isdisplayed(currency);
		if (flag) {
			log.info("Currency Dropdown Menu exists in the page. ");
		} else {
			log.info("Currency Dropdown Menu is missing in the page. ");
		}
		return flag;
	}

	public List<String> verifyCurrencyDropDownMenuItems() {
		eleUtil.doActionsClick(currency);
		List<WebElement> items = eleUtil.dofindElements(currencyDropDownMenu);
		List<String> CurrencyDropDownItems = new ArrayList<>();
		for (WebElement e : items) {
			String text = e.getText();
			CurrencyDropDownItems.add(text);
		}
		return CurrencyDropDownItems;
	}

	public boolean isPhoneNumberDisplayed() {
		boolean flag = eleUtil.isdisplayed(contactNumber);
		if (flag) {
			log.info("contactNumber exists in the page. ");
		} else {
			log.info("contactNumber is missing in the page. ");
		}
		return flag;
	}

	public boolean isMyAccountLinkDisplayed() {
		boolean flag = eleUtil.isdisplayed(myAccountLink);
		if (flag) {
			log.info("MyAccount Link exists in the page. ");
		} else {
			log.info("MyAccount Link is missing in the page. ");
		}
		return flag;
	}

	public List<String> verifyMyAccountLinkMenuItems() {
		eleUtil.doClickWithActionsAndWait(myAccountLink, AppConstants.DEFAULT_SHORT_TIME_OUT);
		List<WebElement> items = driver.findElements(myAccountMenuItems);
		List<String> myAccountDropDownItems = new ArrayList<>();
		for (WebElement e : items) {
			String text = e.getText();
			myAccountDropDownItems.add(text);
		}
		log.info(myAccountDropDownItems);
		return myAccountDropDownItems;
	}

	public boolean isWishListDisplayed() {
		boolean flag = eleUtil.isdisplayed(wishList);
		if (flag) {
			log.info("wishList Link exists in the page. ");
		} else {
			log.info("wishList Link is missing in the page. ");
		}
		return flag;
	}

	public boolean isShoppingCartDisplayed() {
		boolean flag =eleUtil.isdisplayed(shoppingCart);
		if (flag) {
			log.info("shoppingCart Link exists in the page. ");
		} else {
			log.info("shoppingCart Link is missing in the page. ");
		}
		return flag;
	}

	public boolean isCheckOutDisplayed() {
		boolean flag = eleUtil.isdisplayed(checkout);
		if (flag) {
			log.info("checkout Link exists in the page. ");
		} else {
			log.info("checkout Link is missing in the page. ");
		}
		return flag;
	}

	public boolean isLogoDisplayed() {
		boolean flag = eleUtil.isdisplayed(logo);
		if (flag) {
			log.info("logo exists in the page. ");
		} else {
			log.info("logo is missing in the page. ");
		}
		return flag;
	}

	public boolean isHeaderDisplayed() {
		boolean flag = eleUtil.isdisplayed(headerMenuItems);
		if (flag) {
			log.info("Header Menu Items are displayed in the page. ");
		} else {
			log.info("Header Menu Items are missing in the page. ");
		}
		return flag;
	}

	public List<String> verifyHeaderMenuItems() {
		eleUtil.waitPresenceOfElement(headerMenuItems, AppConstants.DEFAULT_SHORT_TIME_OUT);
		List<WebElement> items = eleUtil.dofindElements(headerMenuItems);
		List<String> headerMenuItems = new ArrayList<>();
		for (WebElement e : items) {
			String text = e.getText();
			headerMenuItems.add(text);
		}
		log.info(headerMenuItems);
		return headerMenuItems;
	}

	public boolean isFeaturedProductsDisplayed() {
		boolean flag = eleUtil.isdisplayed(featuredProducts);
		if (flag) {
			log.info("Featured Products are displayed in the page. ");
		} else {
			log.info("Featured Products are missing in the page. ");
		}
		return flag;
	}

	public int verifyFeaturedProductsCount() {
		int number = eleUtil.getTotalElementsCount(featuredProducts);
		log.info("Number of Featured Products displayed are : " + number);
		return number;
	}

	public int verifyFooterMenuItemsCount() {
		int number = eleUtil.getTotalElementsCount(footerMenuItems);
		log.info("Number of Footer Menu Items are : " + number);
		return number;
	}

	public String verifyFooterText() {
		String text =eleUtil.dogetText(footerText);
		return text;
	}
}
