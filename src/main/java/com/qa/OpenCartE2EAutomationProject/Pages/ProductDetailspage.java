package com.qa.OpenCartE2EAutomationProject.Pages;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.OpenCartE2EAutomationProject.Constants.AppConstants;
import com.qa.OpenCartE2EAutomationProject.Utils.ElementUtil;

public class ProductDetailspage {
	private WebDriver driver;
	private ElementUtil eleUtil;

	public ProductDetailspage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	private By productHeader = By.tagName("h1");
	private By images = By.cssSelector("ul.thumbnails li");
	private By navTab = By.cssSelector("ul.nav.nav-tabs li a");
	private By reviewTab = By.cssSelector("a[href='#tab-review']");
	private By reviewMsg = By.xpath("//p[normalize-space()='There are no reviews for this product.']");
	private By errorReviewSubmit = By.cssSelector("i.fa.fa-exclamation-circle");
	private By reviewName = By.id("input-name");
	private By reviewText = By.id("input-review");
	private By ratingRadioButtons = By.cssSelector("div.col-sm-12");
	private By continueButton = By.id("button-review");
	
	private By rating1Button = By.xpath("(//input[@name='rating'])[1]");
	private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=1]//li");
	private By productPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=2]//li");

	private By quantity = By.id("input-quantity");
	private By addToCartBtn = By.id("button-cart");
	private By cartSuccessMsg = By.cssSelector("div.alert.alert-success");

	private By cart = By.id("cart");

	private Map<String, String> prodInfoMap;

	public String getProductHeaderValue() {
		String text = eleUtil.dogetText(productHeader);
		System.out.println("Product Header is: " + text);
		return text;
	}

	public int getProdImageCount() {
		int count = eleUtil.waitPresenceOfAllElements(images, AppConstants.DEFAULT_MEDIUM_TIME_OUT).size();
		return count;
	}

	public boolean isDescriptionandReviewTabDisplayed() {
		boolean flag = eleUtil.waitForElementIsVisibleAndEnable(navTab, AppConstants.DEFAULT_SHORT_TIME_OUT)
				.isDisplayed();
		return flag;
	}

	public String submitReview(String name, String comment) {
		eleUtil.waitAndClick(reviewTab, AppConstants.DEFAULT_SHORT_TIME_OUT);
		eleUtil.dosendKeys(reviewName, name);
		eleUtil.dosendKeys(reviewText, comment);
		eleUtil.doclick(rating1Button);
		eleUtil.doclick(continueButton);
		eleUtil.waitForAlertPresence(AppConstants.DEFAULT_SHORT_TIME_OUT);
		String text = eleUtil.getAlertText(AppConstants.DEFAULT_SHORT_TIME_OUT);
		return text;
	}

	public Map<String, String> getProductInfo() {
		prodInfoMap = new LinkedHashMap<String, String>();
		// Header
		prodInfoMap.put("ProductHeader", getProductHeaderValue());
		getProductMetaData();
		getProductPricingData();
		return prodInfoMap;
	}

	private void getProductMetaData() {
		// Product Meta Data
		List<WebElement> metaList = eleUtil.dofindElements(productMetaData);
		for (WebElement e : metaList) {
			String metaInfo = e.getText();
			String[] metaData = metaInfo.split(":");
			String key = metaData[0].trim();
			String value = metaData[1].trim();
			prodInfoMap.put(key, value);
		}
	}

	private void getProductPricingData() {
		// Product Pricing Data
		List<WebElement> priceList = eleUtil.dofindElements(productPriceData);
		String price = priceList.get(0).getText();
		String exTax = priceList.get(1).getText();
		String exTaxValue = exTax.split(":")[1].trim();
		prodInfoMap.put("prodprice", price);
		prodInfoMap.put("TaxValue", exTaxValue);
	}

	public void enterQuantity(int qty) {
		System.out.println("Product Quantity is: " + qty);
		eleUtil.dosendKeys(quantity, String.valueOf(qty));
	}

	public String addProductToCart() {
		eleUtil.doclick(addToCartBtn);
		String successMsg = eleUtil
				.waitForElementIsVisibleAndEnable(cartSuccessMsg, AppConstants.DEFAULT_SHORT_TIME_OUT).getText();

		// As Strings are immutable, hence using String builder(String Builder is faster
		// than String Buffer)
		String cartMsg = successMsg.substring(0, successMsg.length() - 1).replace("\n", "");
		System.out.println("Cart Success Message is : " + cartMsg);
		return cartMsg;
	}

	public ViewCartPopuppage openCart() {
		eleUtil.doclick(cart);
		return new ViewCartPopuppage(driver);
	}

}
