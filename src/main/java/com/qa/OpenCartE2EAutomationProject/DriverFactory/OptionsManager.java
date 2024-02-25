package com.qa.OpenCartE2EAutomationProject.DriverFactory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager {
	private Properties prop;
	private ChromeOptions co;
	private EdgeOptions eo;
	private FirefoxOptions fo;
	
	public OptionsManager(Properties prop) {
		this.prop = prop;
	}
	
	public ChromeOptions getChromeOptions() {
		co = new ChromeOptions();		
		if (Boolean.parseBoolean(prop.getProperty("headless").trim()))co.addArguments("--headless");
		if (Boolean.parseBoolean(prop.getProperty("incognito").trim()))co.addArguments("--incognito");
		return co;
	}
	
	public EdgeOptions getEdgeOptions() {
		eo = new EdgeOptions();		
		if (Boolean.parseBoolean(prop.getProperty("headless").trim()))co.addArguments("--headless");
		if (Boolean.parseBoolean(prop.getProperty("incognito").trim()))co.addArguments("--incognito");
		return eo;
	}
	
	public FirefoxOptions getFireFoxOptions() {
		fo = new FirefoxOptions();
		if (Boolean.parseBoolean(prop.getProperty("headless").trim()))co.addArguments("--headless");
		if (Boolean.parseBoolean(prop.getProperty("incognito").trim()))co.addArguments("--incognito");
		return fo; 
	}
}
