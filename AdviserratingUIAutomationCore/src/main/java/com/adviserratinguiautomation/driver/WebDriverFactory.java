package com.adviserratinguiautomation.driver;

import static com.adviserratinguiautomation.browser.BrowserInstanceFactory.chromeWebDriver;
import static com.adviserratinguiautomation.browser.BrowserInstanceFactory.firefoxWebDriver;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.adviserratinguiautomation.base.BaseTestFixture;
import com.adviserratinguiautomation.customexceptions.ExceptionHandeler;
import com.adviserratinguiautomation.resourceRead.ResourceRead;

/**
 * This Factory class is used to get the instance of the web driver
 */
public class WebDriverFactory {

	final static Logger log = Logger.getLogger(WebDriverFactory.class);
	private static String browserName;

	/**
	 * @return Get the instance of the webbrowser on based on the test browser
	 */
	public static WebDriver getInstance() {
		log.info("Entered the getInstance method in SFWebdriverFactory");
		WebDriver webDriver = null;

		switch (browserName) {
		case "Chrome": {
			webDriver = chromeWebDriver();
			break;
		}
		case "Firefox": {
			webDriver = firefoxWebDriver();
			break;
		}

		default:
			throw new IllegalArgumentException("The suggested browser was not found");

		}
		log.info("Exited the getSFWebDriverInstance method in SFWebdriverFactory");
		return webDriver;
	}

	public static void init() {
		log.info("Attempting to get Browser details");
		try {
			if (browserName == null) {
				log.info("Attempting to get Browser");
				// To read from Jenkins parameter
				if (new ResourceRead().getEnvironmentConfigValue().getProperty("Browser").equalsIgnoreCase("Chrome")) {
					browserName = "Chrome";

				} else if (new ResourceRead().getEnvironmentConfigValue().getProperty("Browser")
						.equalsIgnoreCase("FireFox")) {
					browserName = "FireFox";

				} else if (new ResourceRead().getEnvironmentConfigValue().getProperty("Browser")
						.equalsIgnoreCase("Edge")) {
					browserName = "Edge";

				}
			}
		} catch (Exception e) {
			new ExceptionHandeler().genricExceptionHandeler(e);
		}
	}

	/**
	 * @return Get the instance of the browser
	 */
	public static String getBrowser() {
		return browserName;
	}

	
	/**
	 * @param browserName
	 * @return Return the instance of the web driver based on the test browser
	 */
	public static WebDriver getBrowserInstance(String browserName) {
		log.info("Entered the getBrowserInstance method in SFWebdriverFactory");
		WebDriver webDriver = null;
		switch (browserName) {
		case BaseTestFixture.Chrome: {
			webDriver = chromeWebDriver();
			break;
		}
		case BaseTestFixture.FireFox: {
			webDriver = firefoxWebDriver();
			break;
		}
		default:
			throw new IllegalArgumentException("The suggested browser was not found");

		}
		log.info("Exited the getBrowserInstance method in SFWebdriverFactory");
		return webDriver;

	}

}