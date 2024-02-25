package com.qa.OpenCartE2EAutomationProject.Utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JSUtil {
	private WebDriver driver;
	private ElementUtil eleUtil;

	public JSUtil(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	public String getTitleByJS() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("return document.title;").toString();
	}

	public void ClickByJS(String locator) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.getElementByID(‘element id ’).click");
	}

	public void getSendKeysByJS(String locator, String value) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.getElementByID('" + locator + "').value='" + value + "'");
	}

	public void getpageRefreshByJS() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("history.go(0);");
	}

	public void goPageBackByJS() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("history.go(-1)");
	}

	public void goPageForwardByJS() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("history.go(1)");
	}

	public String getPageInnerTextByJS() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("return document.documentElement.innerText;").toString();
	}

	public String getTextByJS(By locator) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement element = eleUtil.dofindElement(locator);
		String elementText = (String) js.executeScript("return arguments[0].innerText;", element);
		return elementText;
	}

	public void scrollintoViewByJS(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public void scrollPageDownByJS() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
	}

	public void scrollPageUpByJS() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(document.body.scrollHeight,0)");
	}

	public void scrollPageDown(String Pixels) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,'" + Pixels + "')");
	}

	public void scrollPageUpByJS(String Pixels) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo('" + Pixels + "' ,0)");
	}

	public void scrollPageMiddleByJS() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,document.body.scrollHeight/2)");
	}

	public void ZoomByJS(String zoomFactor) {
		// This Zoom function will work in Chrome and Edge Browser only
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.body.style.zoom='" + zoomFactor + "%'");
	}

	public void ZoomByJSInFirefox(String scaleFactor) {
		// This Zoom function will work in only FireFox
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.body.style.MozTransfor='scale(" + scaleFactor + ")'");
	}

}
