package com.qa.OpenCartE2EAutomationProject.Utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtil {
	private WebDriver driver;

	public ElementUtil(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement dofindElement(By locator) {
		return driver.findElement(locator);
	}

	public WebElement dofindElement(By locator, int timeout) {
		return waitVisibilityElement(locator, timeout);
	}

	public void clearField(By locator) {
		WebElement element = dofindElement(locator);
		element.clear();
	}

	public List<WebElement> dofindElements(By locator) {
		return driver.findElements(locator);
	}

	public void dosendKeys(By locator, String value) {
		WebElement element = dofindElement(locator);
		element.clear();
		element.sendKeys(value);
	}

	public void dosendKeysTAB(By locator) {
		dofindElement(locator).sendKeys(Keys.TAB);
	}

	public void doActionsSendKeys(By locator, String value) {
		Actions action = new Actions(driver);
		action.sendKeys(dofindElement(locator), value).build().perform();
	}

	public void doclick(By locator) {
		dofindElement(locator).click();
	}

	public void doActionsClick(By locator) {
		Actions action = new Actions(driver);
		action.click(dofindElement(locator)).build().perform();
	}

	public void doActionsContextClick(By locator) {
		Actions action = new Actions(driver);
		action.contextClick(dofindElement(locator)).build().perform();
	}

	public void doAttributeBasedClick(By locator, String attribute, String value) {
		List<WebElement> list = dofindElements(locator);
		for (WebElement e : list) {
			if (e.getAttribute(attribute).contains(value)) {
				e.click();
			}
		}
	}

	public String dogetText(By locator) {
		return dofindElement(locator).getText();
	}

	public boolean isdisplayed(By locator) {
		return dofindElement(locator).isDisplayed();
	}

	public boolean isSelected(By locator) {
		return dofindElement(locator).isSelected();
	}

	public String getElementAttribute(By locator, String attributename) {
		return dofindElement(locator).getAttribute(attributename);
	}

	public List<WebElement> getElementsAttributes(By locator, String attributename) {
		List<WebElement> elist = dofindElements(locator);
		for (WebElement e : elist) {
			String attributevalue = e.getAttribute(attributename);
			System.out.println(attributevalue);
		}
		return elist;
	}

	public int getTotalElementsCount(By locator) {
		int count = dofindElements(locator).size();
		return count;

	}

	public List<String> getElementsText(By locator) {
		List<String> eTextList = new ArrayList<String>();
		List<WebElement> elist = dofindElements(locator);
		for (WebElement e : elist) {
			String text = e.getText();
			eTextList.add(text);
		}
		return eTextList;
	}
//*******************Select based Drop down utilities *************************************

	public void doSelectDropdownbyIndex(By locator, int index) {
		Select select = new Select(dofindElement(locator));
		select.selectByIndex(index);
	}

	public void doSelectDropdownbyValue(By locator, String Value) {
		Select select = new Select(dofindElement(locator));
		select.selectByValue(Value);
	}

	public void doSelectDropdownbyVisibleText(By locator, String VisibleText) {
		Select select = new Select(dofindElement(locator));
		select.selectByVisibleText(VisibleText);
	}

	public List<WebElement> getDropDownOptions(By locator) {
		Select select = new Select(dofindElement(locator));
		return select.getOptions();
	}

	public List<String> getAllTextValuesInDropDownList(By locator) {
		List<WebElement> optionslist = getDropDownOptions(locator);
		List<String> TextList = new ArrayList<String>();
		for (WebElement e : optionslist) {
			String text = e.getText();
			TextList.add(text);
		}
		return TextList;
	}

	public int TotalElementsCountInADropdownList(By locator) {
		List<WebElement> optionslist = getDropDownOptions(locator);
		return optionslist.size();
	}

	public void ClickonAElementInADropDownlist(By locator, String value) {

		List<WebElement> optionslist = getDropDownOptions(locator);
		for (WebElement e : optionslist) {
			String text = e.getText();
			System.out.println(text);
			if (text.equalsIgnoreCase(value)) {
				e.click();
				break;
			}
		}

	}

	public void doSearchOnSuggestionsAndClick(By locator, String name) {
		List<WebElement> Suggestionlist = dofindElements(locator);
		System.out.println(Suggestionlist.size());

		for (WebElement e : Suggestionlist) {
			String text = e.getText();
			System.out.println(text);
			if (text.equalsIgnoreCase(name)) {
				e.click();
				break;
			}
		}

	}

	public void SwitchWindow(By locator) {
		driver.findElement(locator).click();
		Set<String> getAllWindowHandles = driver.getWindowHandles();
		Iterator<String> it = getAllWindowHandles.iterator();
		while (it.hasNext()) {
			String WindowID = it.next();
			driver.switchTo().window(WindowID);
			System.out.println("The current URL is:" + driver.getCurrentUrl());
			driver.close();
		}

	}

	// *****************Wait Utilities********************************************
	/**
	 * An expectation for checking that an element is present on the DOM of a page.
	 * This does not necessarily mean that the element is visible.
	 *
	 * @param locator used to find the element
	 * @return the WebElement once it is located
	 */
	public WebElement waitPresenceOfElement(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public List<WebElement> waitPresenceOfAllElements(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
	}

	/**
	 * An expectation for checking that an element is present on the DOM of a page
	 * and visible. Visibility means that the element is not only displayed but also
	 * has a height and width that is greater than 0.
	 *
	 * @param locator used to find the element
	 * @return the WebElement once it is located and visible
	 */

	public WebElement waitVisibilityElement(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public List<WebElement> waitVisibilityOfAllElements(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}

	public Alert waitForAlertPresence(int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.alertIsPresent());
	}

	public String getAlertText(int timeout) {
		return waitForAlertPresence(timeout).getText();
	}

	public void alertAccept(int timeout) {
		waitForAlertPresence(timeout).accept();
	}

	public void dismissAccept(int timeout) {
		waitForAlertPresence(timeout).dismiss();
	}

	public void alertSendkeys(int timeout, String value) {
		waitForAlertPresence(timeout).sendKeys(value);
	}

	public String waitTitleContainsAndFetch(String title, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.titleContains(title));
		return driver.getTitle();
	}

	public String waitTitleIsAndFetch(String title, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.titleIs(title));
		return driver.getTitle();
	}

	public String waitURL(String urlFractionValue, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.urlContains(urlFractionValue));
		return driver.getCurrentUrl();
	}

	public void waitforFrameAndSwitch(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
	}

	public void waitforFrameAndSwitchByIndex(int frameIndex, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIndex));
	}

	public void waitforFrameAndSwitchByName(String frameName, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameName));
	}

	/**
	 * An expectation for checking an element is visible and enabled such that you
	 * can click it.
	 * 
	 * @param locator
	 * @param timeout
	 */
	public void waitAndClick(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
	}

	public WebElement waitForElementIsVisibleAndEnable(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	public void doClickWithActionsAndWait(By locator, int timeout) {
		WebElement element = waitForElementIsVisibleAndEnable(locator, timeout);
		Actions action = new Actions(driver);
		action.click(element).build().perform();
	}

	/**
	 * The below method not so widely used,
	 * 
	 * @param locator
	 * @param timeout
	 * @param windownumber
	 */
	public void doClickWaitAndSwitchToWindow(By locator, int timeout, int windownumber) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.numberOfWindowsToBe(windownumber));
		SwitchWindow(locator);
	}

	public WebElement waitForElementPresenceWithFluentWait(By locator, int timeout, int pollingtime) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeout))
				.ignoring(NoSuchElementException.class).ignoring(StaleElementReferenceException.class)
				.pollingEvery(Duration.ofSeconds(pollingtime)).withMessage("Selenium cannot find the Element");
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));

	}

	public void waitForAlertPresenceWithFluentWait(int timeout, int pollingtime) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeout))
				.ignoring(NoAlertPresentException.class).ignoring(StaleElementReferenceException.class)
				.pollingEvery(Duration.ofSeconds(pollingtime)).withMessage("Alert not Found");
		wait.until(ExpectedConditions.alertIsPresent());

	}
}
