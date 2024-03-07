package com.qa.OpenCartE2EAutomationProject.Tests;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.OpenCartE2EAutomationProject.Base.BaseTest;
import com.qa.OpenCartE2EAutomationProject.Constants.AppConstants;

public class LandingPageTest extends BaseTest {
	
	private static final Logger log = LogManager.getLogger(LandingPageTest.class);

	@BeforeClass
	public void landingPageSetup() {
		landingPage = loginPage.doClickLandingPage();
	}

	@Test
	public void landingPageTitleTest() {
		String actualTitle = landingPage.getLandingPageTitle();
		log.info("The actual Landing Page Title is : " +actualTitle);
		Assert.assertEquals(actualTitle, "Your Store");
	}

	@Test
	public void landingPageUrlTest() {
		String actualUrl = landingPage.getLandingPageUrl();
		log.info("The actual Landing Page URL is : " + actualUrl);
		Assert.assertTrue(actualUrl.contains("common/home"));
	}

	@Test
	public void currencyDropDownMenuDisplayedTest() {
		boolean flag = landingPage.isCurrencyDropDownMenuDisplayed();
		Assert.assertTrue(flag);
	}

	@Test
	public void currencyDropDownMenuItemsTest() {
		List<String> actualItems = landingPage.verifyCurrencyDropDownMenuItems();
		Assert.assertEquals(actualItems, AppConstants.CURRENCY_DROPDOWN_LIST);
	}



	@Test
	public void phoneNumberDisplayedTest() {
		boolean flag = landingPage.isPhoneNumberDisplayed();
		Assert.assertTrue(flag);
	}

	@Test
	public void myAccountLinkDisplayedTest() {
		boolean flag = landingPage.isMyAccountLinkDisplayed();
		Assert.assertTrue(flag);
	}

	@Test
	public void verifyMyAccountLinkMenuItemsTest() {
		List<String> actualItems = landingPage.verifyMyAccountLinkMenuItems();
		Assert.assertEquals(actualItems, AppConstants.MYACCOUNT_MENU_ITEMS);
	}

	@Test
	public void wishListDisplayedTest() {
		boolean flag = landingPage.isWishListDisplayed();
		Assert.assertTrue(flag);
	}

	@Test
	public void ShoppingCartDisplayedTest() {
		boolean flag = landingPage.isShoppingCartDisplayed();
		Assert.assertTrue(flag);
	}

	@Test
	public void checkOutDisplayedTest() {
		boolean flag = landingPage.isCheckOutDisplayed();
		Assert.assertTrue(flag);
	}

	@Test
	public void logoDisplayedTest() {
		boolean flag = landingPage.isLogoDisplayed();
		Assert.assertTrue(flag);
	}

	@Test
	public void headerDisplayedTest() {
		boolean flag = landingPage.isHeaderDisplayed();
		Assert.assertTrue(flag);
	}

	@Test
	public void verifyHeaderMenuItemsTest() {
		List<String> actualItems = landingPage.verifyHeaderMenuItems();
		Assert.assertEquals(actualItems, AppConstants.HEADER_MENU_ITEMS);
	}

	@Test
	public void FeaturedProductsDisplayedTest() {
		boolean flag = landingPage.isFeaturedProductsDisplayed();
		Assert.assertTrue(flag);
	}

	@Test
	public void verifyFeaturedProductsCountTest() {
		int count = landingPage.verifyFeaturedProductsCount();
		Assert.assertEquals(count, AppConstants.FEATURED_PRODUCTS_COUNT);
	}

	@Test
	public void verifyFooterMenuItemsCount() {
		int count = landingPage.verifyFooterMenuItemsCount();
		Assert.assertEquals(count, AppConstants.FOOTER_MENU_COUNT);
	}

	@Test
	public void verifyFooterTextTest() {
		String text = landingPage.verifyFooterText();
		Assert.assertTrue(text.contains(AppConstants.FOOTER_TEXT));
	}

}
