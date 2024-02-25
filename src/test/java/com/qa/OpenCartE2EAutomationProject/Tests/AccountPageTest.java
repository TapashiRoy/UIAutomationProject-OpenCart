package com.qa.OpenCartE2EAutomationProject.Tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.OpenCartE2EAutomationProject.Base.BaseTest;
import com.qa.OpenCartE2EAutomationProject.Constants.AppConstants;

import io.qameta.allure.Step;

public class AccountPageTest extends BaseTest{
	
	@BeforeClass
	public void accountPageSetup() {
		accountPage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	}
	
	@Step("Geting the Account Page Title")
	@Test
	public void accountPageTitleTest() {
		String actualTitle = accountPage.getAccountPageTitle();
		Assert.assertEquals(actualTitle, AppConstants.ACCOUNT_PAGE_TITLE);
	}
	
	@Test
	public void accountPageUrlTest() {
		String actualUrl = accountPage.getAccountPageUrl();
		Assert.assertTrue(actualUrl.contains(AppConstants.ACCOUNT_PAGE_URL));
	}
	
	@Test
	public void MyAccountsHeadingDisplayedTest() {
		boolean flag = accountPage.isMyAccountsHeadingDisplayed();
		Assert.assertTrue(flag);
	}
	
	@Test
	public void OrdersHeadingDisplayedTest() {
		boolean flag = accountPage.isOrdersHeadingDisplayed();
		Assert.assertTrue(flag);
	}
	
	@Test
	public void MyAffiliateAccountHeadingDisplayedTest() {
		boolean flag = accountPage.isMyAffiliateAccountHeadingDisplayed();
		Assert.assertTrue(flag);
	}
	
	@Test
	public void NewsLetterHeadingDisplayedTest() {
		boolean flag = accountPage.isNewsLetterHeadingDisplayed();
		Assert.assertTrue(flag);
	}
	
	@Test
	public void verifyHeadersCountTest() {
		int actualSize = accountPage.verifyHeadersCount();
		Assert.assertEquals(actualSize, AppConstants.ACCOUNTPAGE_HEADER_COUNT);
	}
	
	@Test
	public void RightPanelDisplayedTest() {
		boolean flag = accountPage.isRightPanelDisplayed();
		Assert.assertTrue(flag);
	}
	
	@Test
	public void LogoutLinkDisplayedTest() {
		boolean flag = accountPage.isLogoutLinkDisplayed();
		Assert.assertTrue(flag);
	}
	
	@Test
	public void searchBarDisplayedTest() {
		boolean flag = accountPage.isSearchDisplayed();
		Assert.assertTrue(flag);
	}
	
	@DataProvider
	public Object[][] ProductCountData() {
		return new Object[][] {
			{"MacBook"},
			{"Samsung"},
			{"Apple"}
		};
	}
	
	@Test(dataProvider = "ProductCountData",priority=11)
	public void searchProductCountTest(String searchKey) {
		psPage = accountPage.doSearch(searchKey);
		int count = psPage.getSearchedProductsCount();		
		Assert.assertTrue(count>0); 		
	}
	
	@Test(priority=12)
	public void sortByDropDownListTest() {
		int count = psPage.getSortByDropdownListItemsCount();		
		Assert.assertEquals(count,9);
	}
	
	@Test(priority=13)
	public void showByDropDownListTest() {
		int count = psPage.getShowDropdownListItemsCount();
		Assert.assertEquals(count,5);
	}
	
	@DataProvider
	public Object[][] ProductData() {
		return new Object[][] {
			{"MacBook", "MacBook Pro"},
			{"Samsung","Samsung Galaxy Tab 10.1"},
			{"Apple","Apple Cinema 30\""}
		};
	}
	
	@Test(dataProvider ="ProductData", priority=14)
	public void searchProductTest(String prodKey, String prodresult) {
		psPage = accountPage.doSearch(prodKey);
		if(psPage.getSearchedProductsCount()>0) {
			pdPage = psPage.selectProduct(prodresult);	
			String actualProdHeader = pdPage.getProductHeaderValue();
			Assert.assertEquals(actualProdHeader, prodresult);			
		}
	}
	
	
	

}
