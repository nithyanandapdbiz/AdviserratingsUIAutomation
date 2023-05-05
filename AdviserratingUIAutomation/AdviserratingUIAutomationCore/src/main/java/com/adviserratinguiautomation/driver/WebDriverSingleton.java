package com.adviserratinguiautomation.driver;

import com.adviserratinguiautomation.browser.BrowserProcessFactory;
import com.adviserratinguiautomation.customexceptions.ExceptionHandeler;
import com.adviserratinguiautomation.customexceptions.ResourceCustomException;
import com.adviserratinguiautomation.resourceRead.ResourceRead;
import org.apache.log4j.Logger;
import org.jutils.jprocesses.JProcesses;
import org.jutils.jprocesses.model.ProcessInfo;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.util.List;

/**
 * This is the base class for the selenium framework web driver
 */
public class WebDriverSingleton {

	final static Logger log = Logger.getLogger(WebDriverSingleton.class);

	public static WebDriver webDriver = null;
	private static WebDriverSingleton _webDriverSingleton = null;
	
	
	WebDriverFactory _WebDriverFactory = new WebDriverFactory();

	private String browser;

	/**
	 * @return To get the instance of the browser
	 */
	public String Browser() {
		return browser;
	}



	/**
	 * @return To get the instance of the Framework web driver
	 */
	public static WebDriverSingleton getInstance() {
		log.info("Entered the getSFWebDriverInstance method");
		if (_webDriverSingleton == null) {
			_webDriverSingleton = new WebDriverSingleton();
		}
		log.info("Exited the getSFWebDriverInstance method");
		return _webDriverSingleton;
	}

	/**
	 * @return Returns the instance of the selenium web driver
	 */

	public WebDriver webDriver() {
		log.info("Entered the getSeleniumWebDriver method");
		if (_webDriverSingleton == null) {
			_webDriverSingleton = new WebDriverSingleton();
		}
		log.info("Exited the getSeleniumWebDriver method");
		return webDriver;
	}

	/**
	 * Reset the singleton
	 */
	public void resetSingleton() {
		log.info("Entered the resetSFWebDriver method");
		_webDriverSingleton = new WebDriverSingleton();
		log.info("Exited the resetSFWebDriver method");
	}

	/**
	 * @param browser Reset the selenium Framework Web Driver with the browser Name
	 */
	public void resetNewBrowser(String browser) {
		log.info("Entered the resetNewBrowser method");
		_webDriverSingleton = new WebDriverSingleton(browser);
		log.info("Exited the resetNewBrowser method");
	}

	/**
	 * Creates an instance of the web driver
	 */
	protected WebDriverSingleton() {
		log.info("Entered the WebDriverSingleton method");
		cleanUp();
		WebDriverFactory.init();
		browser = WebDriverFactory.getBrowser();
	   webDriver = WebDriverFactory.getInstance();
		if (webDriver != null) {
			webDriver.manage().deleteAllCookies();
			webDriver.manage().window().maximize();
		}
		log.info("Exited the WebDriverSingleton method");
	}

	protected WebDriverSingleton(String browser) {
		log.info("Entered the WebDriverSingleton method with browser name");
		WebDriverFactory.init();
		WebDriverFactory.getBrowserInstance(browser);
		webDriver.manage().window().maximize();
		log.info("Exited the WebDriverSingleton method with browser name");
	}

	/**
	 * Cleans up the web driver and related process
	 */
	public void cleanUp() {
		try {
			log.info("Entered the cleanUp method");
			String browserProcessKillSwitch = new ResourceRead().getResourceValueFromXML()
					.getProperty("browserProcessInstanceKillSwitch");
			if (browserProcessKillSwitch.equalsIgnoreCase("enable")) {
				BrowserProcessFactory browserProcessFactory = new BrowserProcessFactory();
				List<ProcessInfo> processToKillList = browserProcessFactory.getCurrentProcessInstance();
				if (processToKillList != null && !processToKillList.isEmpty()) {
					for (ProcessInfo processToKill : processToKillList) {
						JProcesses.killProcess(Integer.parseInt(processToKill.getPid()));
						if (webDriver != null) {
							webDriver.quit();
							webDriver.close();
						}
					}
				}
			} else {
				if (webDriver != null) {
					webDriver.quit();
					webDriver.close();
				} else {
				}
			}
			log.info("Exited the cleanUp method");
		} catch (ResourceCustomException ex) {
			new ExceptionHandeler().resourceExceptionHandeler(ex);
		} catch (IOException ex) {
			new ExceptionHandeler().genricExceptionHandeler(ex);
		}

	}

}
