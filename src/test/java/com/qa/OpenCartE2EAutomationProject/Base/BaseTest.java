package com.qa.OpenCartE2EAutomationProject.Base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.qa.OpenCartE2EAutomationProject.DriverFactory.DriverFactory;
import com.qa.OpenCartE2EAutomationProject.Pages.Accountpage;
import com.qa.OpenCartE2EAutomationProject.Pages.Landingpage;
import com.qa.OpenCartE2EAutomationProject.Pages.Loginpage;
import com.qa.OpenCartE2EAutomationProject.Pages.ProductDetailspage;
import com.qa.OpenCartE2EAutomationProject.Pages.ProductSearchpage;
import com.qa.OpenCartE2EAutomationProject.Pages.Registerpage;
import com.qa.OpenCartE2EAutomationProject.Pages.ViewCartPopuppage;

public class BaseTest {
	DriverFactory df;
	WebDriver driver;
	protected Properties prop;

	protected SoftAssert softAssert;

	protected Accountpage accountPage;
	protected Landingpage landingPage;
	protected Loginpage loginPage;
	protected ProductDetailspage pdPage;
	protected ProductSearchpage psPage;
	protected Registerpage registerPage;
	protected ViewCartPopuppage vcPage;

	@BeforeTest
	public void setup() {
		df = new DriverFactory();
		prop = df.initProp();
		driver = df.initDriver(prop);
		loginPage = new Loginpage(driver);

		softAssert = new SoftAssert();
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
