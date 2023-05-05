package com.adviserratinguiautomation.browser;

import com.adviserratinguiautomation.base.BasePage;
import com.adviserratinguiautomation.customexceptions.ExceptionHandeler;
import com.adviserratinguiautomation.customexceptions.ResourceCustomException;
import com.adviserratinguiautomation.customexceptions.drivercustomexceptions.InvalidInputException;
import com.adviserratinguiautomation.resourceRead.ResourceRead;
import org.apache.log4j.Logger;
import org.codehaus.plexus.util.Os;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


public class BrowserInstanceFactory extends BasePage {

	final static Logger log = Logger.getLogger(BrowserInstanceFactory.class);



	/**
	 * @return return the instance of the web driver with the settings for Chrome
	 */
	public static WebDriver chromeWebDriver() {
		try {
			log.info("Entered the ChromeWebDriver method in BrowserInstanceFactory");

			String CHROME_DRIVERNAME_PROPERTY = "ChromeDriverName";
			String CHROME_WINDOWS_DRIVERPATH_PROPERTY = "ChromeDriverWindowsPath";
			String CHROME_LINUX_DRIVERPATH_PROPERTY = "ChromeDriverLinuxPath";
			String TIME_OUT_PROPERTY = "WebDriverTimeOutInSeconds";
			WebDriver seleniumWebdriver = null;
			ResourceRead resourceRead = new ResourceRead();
			// chrome capabilities
			// chrome options
			final ChromeOptions chromeOptions = new ChromeOptions();

			chromeOptions.setHeadless(false);
			chromeOptions.addArguments("no-sandbox");
			chromeOptions.addArguments("window-size=1920,1080");
			chromeOptions.addArguments("disable-gpu");
			chromeOptions.addArguments("disable-application-cache");
			chromeOptions.addArguments("disk-cache-size=0");
			chromeOptions.addArguments("disable-dev-shm-usage");
			chromeOptions.addArguments("--remote-allow-origins=*");

			//=================  Added media stream permission           

			Map<String, Integer> userPrefences = new HashMap<>();
			Map<String, Object> profile = new HashMap<>();
			Map<String, Object> prefs = new HashMap<>();
			userPrefences.put("media_stream", 1);
			profile.put("managed_default_content_settings", userPrefences);
			prefs.put("profile", profile);
			chromeOptions.setExperimentalOption("prefs", prefs);

			String osName = System.getProperty("os.name");
			if(osName.toLowerCase().contains("windows")) { 
				System.out.println("osName: " + osName); System.out.println("ChromeDriverName: " + resourceRead.getResourceValueFromXML().getProperty(CHROME_DRIVERNAME_PROPERTY)); System.out.println("ChromeDriverName: " + resourceRead.getResourceValueFromXML().getProperty(CHROME_WINDOWS_DRIVERPATH_PROPERTY));

				System.setProperty(resourceRead.getResourceValueFromXML().getProperty(CHROME_DRIVERNAME_PROPERTY), resourceRead.getResourceValueFromXML().getProperty(CHROME_WINDOWS_DRIVERPATH_PROPERTY)); 
			} else 
			{
				System.setProperty(resourceRead.getResourceValueFromXML().getProperty(CHROME_DRIVERNAME_PROPERTY), resourceRead.getResourceValueFromXML().getProperty(CHROME_LINUX_DRIVERPATH_PROPERTY)); 
			}

			seleniumWebdriver = new ChromeDriver(chromeOptions);
			seleniumWebdriver.manage().timeouts().pageLoadTimeout(Long.parseLong(resourceRead.getResourceValueFromXML().getProperty(TIME_OUT_PROPERTY)), TimeUnit.SECONDS);
			log.info("Exited the ChromeWebDriver method in BrowserInstanceFactory");
			return seleniumWebdriver;
		} catch (IOException e) {
			new ExceptionHandeler().genricExceptionHandeler(e);
			return null;
		} catch (ResourceCustomException e) {
			new ExceptionHandeler().resourceExceptionHandeler(e);
			return null;
		}
	}

	/**
	 * @return return the instance of the web driver with the settings for Firefox
	 */
	public static WebDriver firefoxWebDriver() {
		try {
			log.info("Entered the firefoxWebDriver method in BrowserInstanceFactory");
			String FIREFOX_DRIVERNAME_PROPERTY = "FireFoxDriverName";
			String FIREFOX_DRIVERPATH_PROPERTY = "FireFoxDriverPath";
			String TIME_OUT_PROPERTY = "WebDriverTimeOutInSeconds";
			WebDriver seleniumWebdriver = null;
			ResourceRead resourceRead = new ResourceRead();
			// firefox capabilities
			// final DesiredCapabilities capabilities = DesiredCapabilities.firefox();
			//firefox options
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			firefoxOptions.addArguments("headless");
			firefoxOptions.addArguments("window-size=1200x600");
			firefoxOptions.addArguments("disable-application-cache");
			firefoxOptions.addArguments("disk-cache-size=0");
			firefoxOptions.addArguments("disable-popup-blocking");
			firefoxOptions.addArguments("no-sandbox");


			FirefoxProfile firefoxProfile = new FirefoxProfile();
			firefoxProfile.setPreference("intl.accept_languages", "en");
			firefoxProfile.setPreference("disable-popup-blocking", true);
			firefoxOptions.setProfile(firefoxProfile);

			//firefox driver path
			System.setProperty(resourceRead.getResourceValueFromXML().getProperty(FIREFOX_DRIVERNAME_PROPERTY), resourceRead.getResourceValueFromXML().getProperty(FIREFOX_DRIVERPATH_PROPERTY));
			seleniumWebdriver = new FirefoxDriver();
//			seleniumWebdriver = new FirefoxDriver(firefoxOptions);
			seleniumWebdriver.manage().timeouts().pageLoadTimeout(Long.parseLong(resourceRead.getResourceValueFromXML().getProperty(TIME_OUT_PROPERTY)), TimeUnit.SECONDS);
			log.info("Exited the firefoxWebDriver method in BrowserInstanceFactory");
			return seleniumWebdriver;
		} catch (IOException e) {
			new ExceptionHandeler().genricExceptionHandeler(e);
			return null;
		} catch (ResourceCustomException e) {
			new ExceptionHandeler().resourceExceptionHandeler(e);
			return null;
		}
	}


	
public static WebDriver edgeWebDriver() {
	try {
		log.info("Entered the ChromeWebDriver method in BrowserInstanceFactory");

		String EDGE_DRIVERNAME_PROPERTY = "EdgeDriverName";
		// String EDGE_DRIVERPATH_PROPERTY = "ChromeDriverPath";
		String EDGE_WINDOWS_DRIVERPATH_PROPERTY = "EdgeDriverWindowsPath";
		String EDGE_LINUX_DRIVERPATH_PROPERTY = "EdgeDriverLinuxPath";
		String TIME_OUT_PROPERTY = "WebDriverTimeOutInSeconds";
		WebDriver seleniumWebdriver = null;
		ResourceRead resourceRead = new ResourceRead();
		final EdgeOptions edgeOptions = new EdgeOptions();
		

		edgeOptions.setHeadless(false);
		edgeOptions.addArguments("no-sandbox");
		edgeOptions.addArguments("window-size=1920,1080");
		edgeOptions.addArguments("disable-gpu");
		edgeOptions.addArguments("disable-application-cache");
		edgeOptions.addArguments("disk-cache-size=0");
		edgeOptions.addArguments("disable-dev-shm-usage");

		//=================  Added media stream permission           

		Map<String, Integer> userPrefences = new HashMap<>();
		Map<String, Object> profile = new HashMap<>();
		Map<String, Object> prefs = new HashMap<>();
		userPrefences.put("media_stream", 1);
		profile.put("managed_default_content_settings", userPrefences);
		prefs.put("profile", profile);
		//            userPrefences.put("intl.accept_languages", "en");
		//            userPrefences.put("disable-popup-blocking", true);
		//            //user preferences for download default directory.... need to ask
		edgeOptions.setExperimentalOption("prefs", prefs);

		
		String osName = System.getProperty("os.name");
		if(osName.toLowerCase().contains("windows")) { 
			System.out.println("osName: " + osName); System.out.println("EdgeDriverName: " + resourceRead.getResourceValueFromXML().getProperty(EDGE_DRIVERNAME_PROPERTY)); System.out.println("EdgeDriverName: " + resourceRead.getResourceValueFromXML().getProperty(EDGE_WINDOWS_DRIVERPATH_PROPERTY));

			System.setProperty(resourceRead.getResourceValueFromXML().getProperty(EDGE_DRIVERNAME_PROPERTY), resourceRead.getResourceValueFromXML().getProperty(EDGE_WINDOWS_DRIVERPATH_PROPERTY)); 
		} else 
		{
			System.setProperty(resourceRead.getResourceValueFromXML().getProperty(EDGE_DRIVERNAME_PROPERTY), resourceRead.getResourceValueFromXML().getProperty(EDGE_LINUX_DRIVERPATH_PROPERTY)); 
		}

		seleniumWebdriver = new EdgeDriver(edgeOptions);
		seleniumWebdriver.manage().timeouts().pageLoadTimeout(Long.parseLong(resourceRead.getResourceValueFromXML().getProperty(TIME_OUT_PROPERTY)), TimeUnit.SECONDS);
		log.info("Exited the EdgeWebDriver method in BrowserInstanceFactory");
		return seleniumWebdriver;
	} catch (IOException e) {
		new ExceptionHandeler().genricExceptionHandeler(e);
		return null;
	} catch (ResourceCustomException e) {
		new ExceptionHandeler().resourceExceptionHandeler(e);
		return null;
	}
}
}

//public class BrowserInstanceFactory extends BasePage {
//
//	final static Logger log = Logger.getLogger(BrowserInstanceFactory.class);
//
//	// proxy Type
//	public static final String COGNIZANT_PROXY = "cognizantProxy";
//
//	/**
//	 * @return return the instance of the web driver with the settings for Chrome
//	 */
//	public static WebDriver chromeWebDriver() {
//		try {
//			log.info("Entered the ChromeWebDriver method in BrowserInstanceFactory");
//
//			String CHROME_DRIVERNAME_PROPERTY = "ChromeDriverName";
//			String CHROME_WINDOWS_DRIVERPATH_PROPERTY = "ChromeDriverWindowsPath";
//			String CHROME_LINUX_DRIVERPATH_PROPERTY = "ChromeDriverLinuxPath";
//			String TIME_OUT_PROPERTY = "WebDriverTimeOutInSeconds";
//			String downloadFilePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
//					+ File.separator + "resources" + File.separator + "CustomReports";
//			HashMap<String, Object> prefs = new HashMap<String, Object>();
//			WebDriver seleniumWebdriver = null;
//			ResourceRead resourceRead = new ResourceRead();
//			// chrome capabilities
//			// final DesiredCapabilities capabilities = DesiredCapabilities.chrome();
//			// chrome options
//			final ChromeOptions chromeOptions = new ChromeOptions();
//
//			// Run headless if set True
//			chromeOptions.setHeadless(true);
//			chromeOptions.addArguments("--disable-gpu");
//			chromeOptions.addArguments("--disable-extensions");
//			chromeOptions.addArguments("--no-sandbox");
//			chromeOptions.addArguments("--disable-dev-shm-usage");
//			chromeOptions.addArguments("--window-size=1920,1080");
//			// chromeOptions.addArguments("--disable-application-cache");
//			// chromeOptions.addArguments("--disk-cache-size=0");
//			chromeOptions.addArguments("--disable-popup-blocking");
//			chromeOptions.addArguments("app-cache-force-enabled");
//
//			// prefs.put("profile.default_content_settings.popups", 0);
//			prefs.put("download.prompt_for_download", false);
//			prefs.put("download.directory_upgrade", true);
//			prefs.put("profile.default_content_setting_values.automatic_downloads", 1);
//			prefs.put("download.default_directory", downloadFilePath);
//			prefs.put("profile.default_content_setting_values.geolocation", 1);
//			prefs.put("profile.default_content_setting_values.notifications", 1);
//			chromeOptions.setExperimentalOption("prefs", prefs);
//
//			// chrome driver path
//			// Based on Windows or Linux select the path
//			if (System.getProperty("os.name").toLowerCase().contains("windows")) {
//				System.setProperty(resourceRead.getResourceValueFromXML().getProperty(CHROME_DRIVERNAME_PROPERTY),
//						resourceRead.getResourceValueFromXML().getProperty(CHROME_WINDOWS_DRIVERPATH_PROPERTY));
//			} else {
//				System.setProperty(resourceRead.getResourceValueFromXML().getProperty(CHROME_DRIVERNAME_PROPERTY),
//						resourceRead.getResourceValueFromXML().getProperty(CHROME_LINUX_DRIVERPATH_PROPERTY));
//			}
//			seleniumWebdriver = new ChromeDriver(chromeOptions);
//			seleniumWebdriver.manage().timeouts().pageLoadTimeout(
//					Long.parseLong(resourceRead.getResourceValueFromXML().getProperty(TIME_OUT_PROPERTY)),
//					TimeUnit.SECONDS);
//
//			log.info("Exited the ChromeWebDriver method in BrowserInstanceFactory");
//			return seleniumWebdriver;
//		} catch (IOException e) {
//			new ExceptionHandeler().genricExceptionHandeler(e);
//			return null;
//		} catch (ResourceCustomException e) {
//			new ExceptionHandeler().resourceExceptionHandeler(e);
//			return null;
//		}
//	}
//
//	/**
//	 * @return return the instance of the web driver with the settings for Firefox
//	 */
//	public static WebDriver firefoxWebDriver() {
//		try {
//			log.info("Entered the firefoxWebDriver method in BrowserInstanceFactory");
//			String FIREFOX_DRIVERNAME_PROPERTY = "FireFoxDriverName";
//			String FIREFOX_DRIVERPATH_PROPERTY = "FireFoxDriverPath";
//			String TIME_OUT_PROPERTY = "WebDriverTimeOutInSeconds";
//			WebDriver seleniumWebdriver = null;
//			ResourceRead resourceRead = new ResourceRead();
//
//			// firefox capabilities
//			FirefoxOptions options = new FirefoxOptions();
//			options.setHeadless(true);
////            firefoxOptions.addArguments("headless");
//			options.addArguments("--window-size=1920,1080");
////        //    firefoxOptions.addArguments("disable-application-cache");
//			options.addArguments("disk-cache-size=0");
//			options.addArguments("--disable-popup-blocking");
//			options.addArguments("--no-sandbox");
//			options.addArguments("--disable-dev-shm-usage");
//			options.addArguments("--disable-gpu");
//
//			FirefoxProfile firefoxProfile = new FirefoxProfile();
//			firefoxProfile.setPreference("intl.accept_languages", "en");
//			firefoxProfile.setPreference("disable-popup-blocking", true);
//			options.setProfile(firefoxProfile);
//
//			// firefox driver path
//			System.setProperty(resourceRead.getResourceValueFromXML().getProperty(FIREFOX_DRIVERNAME_PROPERTY),
//					resourceRead.getResourceValueFromXML().getProperty(FIREFOX_DRIVERPATH_PROPERTY));
//			seleniumWebdriver = new FirefoxDriver(options);
//			seleniumWebdriver.manage().timeouts().pageLoadTimeout(
//					Long.parseLong(resourceRead.getResourceValueFromXML().getProperty(TIME_OUT_PROPERTY)),
//					TimeUnit.SECONDS);
//			log.info("Exited the firefoxWebDriver method in BrowserInstanceFactory");
//			return seleniumWebdriver;
//		} catch (IOException e) {
//			new ExceptionHandeler().genricExceptionHandeler(e);
//			return null;
//		} catch (ResourceCustomException e) {
//			new ExceptionHandeler().resourceExceptionHandeler(e);
//			return null;
//		}
//	}
//
//	/**
//	 * @return return the instance of the web driver with the settings for Edge
//	 */
//	public static WebDriver edgeDriver() {
//		try {
//			log.info("Entered the edgeDriver method in BrowserInstanceFactory");
//			String FIREFOX_DRIVERNAME_PROPERTY = "FireFoxDriverName";
//			String FIREFOX_DRIVERPATH_PROPERTY = "FireFoxDriverPath";
//			String TIME_OUT_PROPERTY = "WebDriverTimeOutInSeconds";
//			WebDriver seleniumWebdriver = null;
//			ResourceRead resourceRead = new ResourceRead();
//			// firefox capabilities
//			// final DesiredCapabilities capabilities = DesiredCapabilities.firefox();
//			// firefox options
//			EdgeOptions options = new EdgeOptions();
//			options.setHeadless(true);
//			options.addArguments("--window-size=1920,1080");
//			options.addArguments("--disable-popup-blocking");
//			options.addArguments("--no-sandbox");
//
//			// firefox driver path
//			System.setProperty(resourceRead.getResourceValueFromXML().getProperty(FIREFOX_DRIVERNAME_PROPERTY),
//					resourceRead.getResourceValueFromXML().getProperty(FIREFOX_DRIVERPATH_PROPERTY));
//			seleniumWebdriver = new EdgeDriver(options);
//			seleniumWebdriver.manage().timeouts().pageLoadTimeout(
//					Long.parseLong(resourceRead.getResourceValueFromXML().getProperty(TIME_OUT_PROPERTY)),
//					TimeUnit.SECONDS);
//			log.info("Exited the edgeDriver method in BrowserInstanceFactory");
//			return seleniumWebdriver;
//		} catch (IOException e) {
//			new ExceptionHandeler().genricExceptionHandeler(e);
//			return null;
//		} catch (ResourceCustomException e) {
//			new ExceptionHandeler().resourceExceptionHandeler(e);
//			return null;
//		}
//	}
//
//	/**
//	 * @param proxyType
//	 * @return proxy object This method returns the proxy details from the
//	 *         configuration file
//	 */
//	private static Proxy getProxyFromConfigFile(String proxyType) {
//		try {
//			Proxy proxy = null;
//			if (isConstantValuePresent(proxyType)) {
//				switch (proxyType) {
//				case COGNIZANT_PROXY: {
//					String COGNIZANT_PROXY_SWITCH = new ResourceRead().getResourceValueFromXML()
//							.getProperty("CognizantProxySwitch");
//					if (isConstantValuePresent(COGNIZANT_PROXY_SWITCH)
//							&& COGNIZANT_PROXY_SWITCH.equalsIgnoreCase("enable")) {
//						String cognizantAddress = new ResourceRead().getResourceValueFromXML()
//								.getProperty("CognizantProxyAddress");
//						String cognizantAddressPort = new ResourceRead().getResourceValueFromXML()
//								.getProperty("CognizantProxyPort");
//						proxy = new Proxy();
//						proxy.setProxyType(Proxy.ProxyType.MANUAL);
//						proxy.setHttpProxy(cognizantAddress + ":" + cognizantAddressPort);
//						proxy.setSslProxy(cognizantAddress + ":" + cognizantAddressPort);
//						proxy.setFtpProxy(cognizantAddress + ":" + cognizantAddressPort);
//						proxy.setSocksProxy(cognizantAddress + ":" + cognizantAddressPort);
//					}
//					break;
//				}
//				}
//			}
//			return proxy;
//		} catch (IOException e) {
//			new ExceptionHandeler().genricExceptionHandeler(e);
//			return null;
//		} catch (ResourceCustomException e) {
//			new ExceptionHandeler().resourceExceptionHandeler(e);
//			return null;
//		} catch (InvalidInputException ex) {
//			new ExceptionHandeler().webDriverExceptionhandeler(ex);
//			return null;
//		}
//	}
//}
