package com.qa.OpenCartE2EAutomationProject.Pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.OpenCartE2EAutomationProject.Utils.ElementUtil;

public class ViewCartPopuppage {
	private WebDriver driver;
	private ElementUtil eleUtil;

	public ViewCartPopuppage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	private By productNames = By.cssSelector(".table.table-striped td.text-left a");
	
	public ArrayList<String> getProductsInCart() {
		List<WebElement> cartList = new ArrayList<WebElement>();
		ArrayList<String> cartProdList = new ArrayList<String>();
		cartList = eleUtil.dofindElements(productNames);
		
		for(WebElement e: cartList) {
			String text = e.getText();
			cartProdList.add(text);
		}
		return cartProdList;
	}
}
