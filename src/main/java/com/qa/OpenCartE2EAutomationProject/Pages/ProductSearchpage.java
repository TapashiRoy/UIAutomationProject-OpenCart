package com.qa.OpenCartE2EAutomationProject.Pages;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.OpenCartE2EAutomationProject.Constants.AppConstants;
import com.qa.OpenCartE2EAutomationProject.Utils.ElementUtil;

public class ProductSearchpage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private static final Logger log = LogManager.getLogger(ProductSearchpage.class);

	public ProductSearchpage(WebDriver driver) {
		this.driver=driver;
		eleUtil = new ElementUtil(driver);
	}

	//Page Locators
	By prodSearchItems = By.cssSelector("div#content div.product-layout");
	By sortByDropdownListItems = By.cssSelector("select#input-sort");
	By showDropdownListItems = By.cssSelector("select#input-limit");


	//Page Actions
	public ProductDetailspage selectProduct(String productName) {
		By prodLocator = By.linkText(productName);
		eleUtil.waitForElementIsVisibleAndEnable(prodLocator, AppConstants.DEFAULT_SHORT_TIME_OUT).click();
		return new ProductDetailspage(driver);
	}

	public int getSearchedProductsCount() {
		int count = eleUtil.waitPresenceOfAllElements(prodSearchItems, AppConstants.DEFAULT_SHORT_TIME_OUT).size();
		log.info("Number of products displayed in the Search is : " + count);
		return count;
	}

	public int getSortByDropdownListItemsCount() {
		eleUtil.ClickonAElementInADropDownlist(sortByDropdownListItems, "Price (Low > High)");
		List<WebElement> elements = eleUtil.getDropDownOptions(sortByDropdownListItems);
		int count = elements.size();
		log.info("Number of products displayed in the Search is : " + count);
		return count;
	}

	public int getShowDropdownListItemsCount() {
		eleUtil.ClickonAElementInADropDownlist(showDropdownListItems, "25");
		List<WebElement> elements = eleUtil.getDropDownOptions(showDropdownListItems);
		int count = elements.size();
		log.info("Number of products displayed in the Search is : " + count);
		return count;
	}


}
