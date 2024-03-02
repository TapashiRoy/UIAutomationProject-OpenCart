package com.qa.OpenCartE2EAutomationProject.Tests;

import java.util.ArrayList;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.OpenCartE2EAutomationProject.Base.BaseTest;

public class ProductDetailsPageTest extends BaseTest {

	@BeforeClass
	public void accountPageSetup() {
		accountPage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	}

	@Test(priority = 2)
	public void isDescriptionAndReviewsDisplayed() {
		boolean flag = pdPage.isDescriptionandReviewTabDisplayed();
		Assert.assertTrue(flag);
	}

	@DataProvider
	public Object[][] imageCountData() {
		return new Object[][] { { "MacBook", "MacBook Pro", 4 }, { "Samsung", "Samsung Galaxy Tab 10.1", 7 },
				{ "Apple", "Apple Cinema 30\"", 6 } };
	}

	@Test(dataProvider = "imageCountData", priority = 1)
	public void searchProductTest(String prodKey, String prodresult, int imageCount) {
		psPage = accountPage.doSearch(prodKey);
		if (psPage.getSearchedProductsCount() > 0) {
			pdPage = psPage.selectProduct(prodresult);
			String actualProdHeader = pdPage.getProductHeaderValue();
			Assert.assertEquals(actualProdHeader, prodresult);
			Assert.assertEquals(pdPage.getProdImageCount(), imageCount);
		}
	}

	@DataProvider
	public Object[][] reviewData() {
		return new Object[][] { { "MacBook", "MacBook Pro", "test", "test this product, i like it very much" } };
	}

	/*
	 * @Test(dataProvider ="reviewData", priority=2) public void
	 * submitReviewTest(String prodKey, String prodresult, String name, String
	 * comment) { psPage = accountPage.doSearch(prodKey);
	 * if(psPage.getSearchedProductsCount()>0) pdPage =
	 * psPage.selectProduct(prodresult); String text = pdPage.submitReview(name,
	 * comment); //Will have to check the code //Assert.assertEquals(text,
	 * "Thank you for your review. It has been submitted to the webmaster for approval."
	 * ); }
	 */

	@DataProvider
	public Object[][] prodData() {
		return new Object[][] { { "MacBook", "MacBook Pro", "Apple", "In Stock", "Product 18", "800", "$2,000.00" } };
	}


	@Test(dataProvider = "prodData", priority = 2)
	public void getProductDetailsTest(String key, String prod, String Brand, String isAvailable, String code, String reward, String price) {
		psPage = accountPage.doSearch(key);
		pdPage = psPage.selectProduct(prod);
		Map<String, String> prodDetailsMap = pdPage.getProductInfo();
		System.out.println(prodDetailsMap);

		softAssert.assertEquals(prodDetailsMap.get("Brand"), Brand);
		softAssert.assertEquals(prodDetailsMap.get("Availability"), isAvailable);
		softAssert.assertEquals(prodDetailsMap.get("ProductHeader"), prod);
		softAssert.assertEquals(prodDetailsMap.get("Product Code"), code);
		softAssert.assertEquals(prodDetailsMap.get("Reward Points"), reward);
		softAssert.assertEquals(prodDetailsMap.get("prodprice"), price);

		softAssert.assertAll();
	}

	@DataProvider
	public Object[][] cartData() {
		return new Object[][] { { "MacBook", "MacBook Pro", 1}};
	}

	@Test(dataProvider = "cartData", priority = 3)
	public void productAddToCartTest(String key,String prod, int quantity) {
		psPage = accountPage.doSearch(key);
		pdPage = psPage.selectProduct(prod);
		pdPage.enterQuantity(quantity);
		String successMsg = pdPage.addProductToCart();
		Assert.assertEquals(successMsg, "Success: You have added " + prod +" to your shopping cart!");

		vcPage = pdPage.openCart();
		ArrayList<String> cartItems = vcPage.getProductsInCart();

	}

}
