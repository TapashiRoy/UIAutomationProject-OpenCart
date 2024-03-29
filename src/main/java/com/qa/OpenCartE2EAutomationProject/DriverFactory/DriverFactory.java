package com.qa.OpenCartE2EAutomationProject.DriverFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.util.FileUtil;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.qa.OpenCartE2EAutomationProject.Exceptions.FrameworkExceptions;


public class DriverFactory {
	public WebDriver driver;
	public Properties prop;
	public OptionsManager op;
	FileInputStream ip;
	
	private static final Logger log = LogManager.getLogger(DriverFactory.class);

	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

	/**
	 * This method is initializing the driver
	 */
	public WebDriver initDriver(Properties prop) {
		op = new OptionsManager(prop);
		String browserName = prop.getProperty("browser");
		log.info("The browser name is : " + browserName);

		if (browserName.trim().equalsIgnoreCase("chrome")) {
			if(Boolean.parseBoolean(prop.getProperty("remote"))) {
				//Running the TCs in a remote machine
				init_remoteDriver("chrome");
			}
			else {
				//Running the TCs locally
				tlDriver.set(new ChromeDriver(op.getChromeOptions()));
			}
		}

		else if(browserName.trim().equalsIgnoreCase("firefox")) {
			if(Boolean.parseBoolean(prop.getProperty("remote"))) {
				//Running the TCs in a remote machine
				init_remoteDriver("firefox");
			}
			else {
				//Running the TCs locally
				tlDriver.set(new FirefoxDriver(op.getFireFoxOptions()));
			}

		}

		else if(browserName.trim().equalsIgnoreCase("Edge")) {
			if(Boolean.parseBoolean(prop.getProperty("remote"))) {
				//Running the TCs in a remote machine
				init_remoteDriver("Edge");
			}
			else {
				//Running the TCs locally
				tlDriver.set(new EdgeDriver(op.getEdgeOptions()));
			}
		}

		else {
			log.info("Please pass the correct Browser name");
		}

		getThreadLocalDriver().manage().deleteAllCookies();
		getThreadLocalDriver().manage().window().maximize();
		getThreadLocalDriver().get(prop.getProperty("url"));
		return getThreadLocalDriver();
	}

	private void init_remoteDriver(String browserName) {

		log.info("Running tests on Selenim GRID with browser: " + browserName);

		try {
			switch (browserName) {
			case "chrome":
				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), op.getChromeOptions()));
				break;
			case "Edge":
				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), op.getEdgeOptions()));
				break;
			case "firefox":
				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), op.getFireFoxOptions()));
				break;
			default:
				log.info("Wrong browser info....can not run on grid remote machine....");
				throw new FrameworkExceptions("NO REMOTE BROWSER AVAILABLE");
			}

		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

	}


	/**
	 * This method will return the ThreadLocal synchronized version of the driver	
	 */

	public synchronized static WebDriver getThreadLocalDriver() {
		return tlDriver.get();
	}

	/**
	 * This method is loading the properties file and interacting with it.
	 */
	public Properties initProp() {
		prop = new Properties();
		String envName = System.getProperty("env");
		log.info("Running the TCs in " + envName);

		try {
			if (envName == null) {
				log.info("No environment is passed, so running the TCs in QA Environment");
				ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
			} else {
				switch (envName.trim()) {
				case "QA":
					ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
					break;
				case "DEV":
					ip = new FileInputStream("./src/test/resources/config/dev.config.properties");
					break;
				case "STAGE":
					ip = new FileInputStream("./src/test/resources/config/stage.config.properties");
					break;
				case "PROD-BETA":
					ip = new FileInputStream("./src/test/resources/config/prodbeta.config.properties");
					break;
				case "PROD":
					ip = new FileInputStream("./src/test/resources/config/prod.config.properties");
					break;
				default:
					throw new FrameworkExceptions("Wrong environment details are passed");
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}

	public String getScreenshot() {
		File src = ((TakesScreenshot) getThreadLocalDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		try {
			FileUtil.copyFile(src, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}

}
