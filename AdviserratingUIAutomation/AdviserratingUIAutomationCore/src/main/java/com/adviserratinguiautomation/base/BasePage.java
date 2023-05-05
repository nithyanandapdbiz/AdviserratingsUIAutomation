package com.adviserratinguiautomation.base;

import com.adviserratinguiautomation.customexceptions.ExceptionHandeler;
import com.adviserratinguiautomation.customexceptions.ResourceCustomException;
import com.adviserratinguiautomation.customexceptions.drivercustomexceptions.*;
import com.adviserratinguiautomation.resourceRead.ResourceRead;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import static com.adviserratinguiautomation.driver.WebDriverFactory.getInstance;

/**
 * This is the base class for all pages that responsible to handle the WebDriver
 * functionality across pages
 */
public abstract class BasePage extends BaseTestFixture {

	final static Logger log = Logger.getLogger(BasePage.class);

//	public void createSFDriverInstanceIfNull() {
//		log.info("Entered the createSFDriverInstanceIfNull method in BasePage");
//		if (webDriver == null) {
//			webDriver = getADRWebDriverInstance();
//		}
//		log.info("Exited the createSFDriverInstanceIfNull method in BasePage");
//	}

	/**
	 * @return current URL that the browse is looking on.
	 */
	public String getCurrentURL() {
		return webDriver.getCurrentUrl();

	}

	/**
	 * @return get the tittle of the current page
	 */
	public String getPageTitle() {
		return webDriver.getTitle();
	}

	/**
	 * @return get the current page source
	 */
	public String getPageSource() {
		return webDriver.getPageSource();
	}

	/**
	 * @param urlKey Launch the URL.
	 * 
	 */
	public void LaunchURL(String url) {
		log.info("Entered the LaunchURL method in BasePage: " + url);
		webDriver.get(url);
		webDriver.manage().window().maximize();
		log.info("Exited the LaunchURL method in BasePage");

	}

	public void clickOnButton(By by) throws WebDriverInstanceNullException {
		try {
			log.info("Entered the clickOnButton method in BasePage");
			if (isWebDriverInstancePresent(webDriver)) {
				waitForElement(by);
				if (isExpectedCondition(ExpectedConditions.elementToBeClickable(by), webDriver)) {
					webDriver.findElement(by).click();
				} else {
					throw new ElementNotClickableException("Element is not clickable");

				}

			}
			log.info("Exited the clickOnButton method in BasePage");
		} catch (ResourceCustomException ex) {
			new ExceptionHandeler().resourceExceptionHandeler(ex);
		} catch (IOException ex) {
			new ExceptionHandeler().genricExceptionHandeler(ex);
		} catch (ElementNotClickableException ex) {
			new ExceptionHandeler().webDriverExceptionhandeler(ex);
		}
	}

	public void clickOnButtonByXpath(String xPathValue) throws WebDriverInstanceNullException, InvalidInputException {
		log.info("Entered the clickOnButtonByXpath method in BasePage");
		if (isWebDriverInstancePresent(webDriver) && isConstantValuePresent(xPathValue)) {
			clickOnButton(By.xpath(xPathValue));
		}
		log.info("Exited the clickOnButtonByXpath method in BasePage");
	}

	public void clickOnButtonByWebElement(WebElement button) throws ResourceCustomException, IOException {
		log.info("Entered the clickOnButtonByWebElement method in BasePage");
		if (isElementPresent(button, getElementFindTimeOut(), webDriver)) {
			button.click();
		}
		log.info("Exited the clickOnButtonByWebElement method in BasePage");
	}

	public void clickButtonById(String idValue) throws WebDriverInstanceNullException, InvalidInputException {
		log.info("Entered the clickButtonById method in BasePage");
		if (isWebDriverInstancePresent(webDriver) && isConstantValuePresent(idValue)) {
			clickOnButton(By.id(idValue));
		}
		log.info("Exited the clickButtonById method in BasePage");
	}

	public void clickButtonByClassName(String classNameValue)
			throws WebDriverInstanceNullException, InvalidInputException {
		log.info("Entered the clickButtonByClassName method in BasePage");
		if (isWebDriverInstancePresent(webDriver) && isConstantValuePresent(classNameValue)) {
			clickOnButton(By.className(classNameValue));
		}
		log.info("Exited the clickButtonByClassName method in BasePage");
	}

	public void clickButtonByPartialLinkText(String partialLinkTextValue)
			throws WebDriverInstanceNullException, InvalidInputException {
		log.info("Entered the clickButtonByPartialLinkText method in BasePage");
		if (isWebDriverInstancePresent(webDriver) && isConstantValuePresent(partialLinkTextValue)) {
			clickOnButton(By.partialLinkText(partialLinkTextValue));
		}
		log.info("Exited the clickButtonByPartialLinkText method in BasePage");
	}

	public void clickButtonByCssSelector(String cssSelectorValue)
			throws WebDriverInstanceNullException, InvalidInputException {
		log.info("Entered the clickButtonByCssSelector method in BasePage");
		if (isWebDriverInstancePresent(webDriver) && isConstantValuePresent(cssSelectorValue)) {
			clickOnButton(By.cssSelector(cssSelectorValue));
		}
		log.info("Exited the clickButtonByCssSelector method in BasePage");
	}

	public void clickButtonByLinkText(String linkTextValue)
			throws WebDriverInstanceNullException, InvalidInputException {
		log.info("Entered the clickButtonByLinkText method in BasePage");
		if (isWebDriverInstancePresent(webDriver) && isConstantValuePresent(linkTextValue)) {
			clickOnButton(By.linkText(linkTextValue));
		}
		log.info("Exited the clickButtonByLinkText method in BasePage");
	}

	public void selectButtonByName(String nameValue) throws WebDriverInstanceNullException, InvalidInputException {
		log.info("Entered the selectButtonByName method in BasePage");
		if (isWebDriverInstancePresent(webDriver) && isConstantValuePresent(nameValue)) {
			clickOnButton(By.name(nameValue));
		}
		log.info("Exited the selectButtonByName method in BasePage");
	}

	public void selectRadioButtonById(String idValue) throws WebDriverInstanceNullException, InvalidInputException {
		log.info("Entered the selectRadioButtonById method in BasePage");
		if (isWebDriverInstancePresent(webDriver) && isConstantValuePresent(idValue)) {
			clickOnButton(By.id(idValue));
		}
		log.info("Exited the selectRadioButtonById method in BasePage");
	}

	public void selectRadioButtonByName(String nameValue) throws WebDriverInstanceNullException, InvalidInputException {
		log.info("Entered the selectRadioButtonByName method in BasePage");
		if (isWebDriverInstancePresent(webDriver) && isConstantValuePresent(nameValue)) {
			clickOnButton(By.name(nameValue));
		}
		log.info("Exited the selectRadioButtonByName method in BasePage");
	}

	public void selectRadioButtonByXpath(String xPathValue)
			throws WebDriverInstanceNullException, InvalidInputException {
		log.info("Entered the selectRadioButtonByXpath method in BasePage");
		if (isWebDriverInstancePresent(webDriver) && isConstantValuePresent(xPathValue)) {
			clickOnButton(By.xpath(xPathValue));
		}
		log.info("Exited the selectRadioButtonByXpath method in BasePage");
	}

	public void selectRadioButtonByClassName(String classNameValue)
			throws WebDriverInstanceNullException, InvalidInputException {
		log.info("Entered the selectRadioButtonByClassName method in BasePage");
		if (isWebDriverInstancePresent(webDriver) && isConstantValuePresent(classNameValue)) {
			clickOnButton(By.className(classNameValue));
		}
		log.info("Exited the selectRadioButtonByClassName method in BasePage");
	}

	public void selectRadioButtonByCssSelector(String cssSelectorValue)
			throws WebDriverInstanceNullException, InvalidInputException {
		log.info("Entered the selectRadioButtonByCssSelector method in BasePage");
		if (isWebDriverInstancePresent(webDriver) && isConstantValuePresent(cssSelectorValue)) {
			clickOnButton(By.cssSelector(cssSelectorValue));
		}
		log.info("Exited the selectRadioButtonByCssSelector method in BasePage");
	}

	public void selectCheckBoxById(String idValue) throws WebDriverInstanceNullException, InvalidInputException {
		log.info("Entered the selectCheckBoxById method in BasePage");
		if (isWebDriverInstancePresent(webDriver) && isConstantValuePresent(idValue)) {
			clickOnButton(By.id(idValue));
		}
		log.info("Exited the selectCheckBoxById method in BasePage");
	}

	public void selectCheckBoxByXpath(String xPathValue) throws WebDriverInstanceNullException, InvalidInputException {
		log.info("Entered the selectCheckBoxByXpath method in BasePage");
		if (isWebDriverInstancePresent(webDriver) && isConstantValuePresent(xPathValue)) {
			clickOnButton(By.xpath(xPathValue));
		}
		log.info("Exited the selectCheckBoxByXpath method in BasePage");
	}

	public void selectCheckBoxByClassName(String classNameValue)
			throws WebDriverInstanceNullException, InvalidInputException {
		log.info("Entered the selectCheckBoxByClassName method in BasePage");
		if (isWebDriverInstancePresent(webDriver) && isConstantValuePresent(classNameValue)) {
			clickOnButton(By.className(classNameValue));
		}
		log.info("Exited the selectCheckBoxByClassName method in BasePage");
	}

	public void selectCheckBoxByCssSelector(String cssSelectorValue)
			throws WebDriverInstanceNullException, InvalidInputException {
		log.info("Entered the selectCheckBoxByCssSelector method in BasePage");
		if (isWebDriverInstancePresent(webDriver) && isConstantValuePresent(cssSelectorValue)) {
			clickOnButton(By.cssSelector(cssSelectorValue));
		}
		log.info("Exited the selectCheckBoxByCssSelector method in BasePage");
	}

	public void selectCheckBoxByName(String nameValue) throws WebDriverInstanceNullException, InvalidInputException {
		log.info("Entered the selectCheckBoxByName method in BasePage");
		if (isWebDriverInstancePresent(webDriver) && isConstantValuePresent(nameValue)) {
			clickOnButton(By.name(nameValue));
		}
		log.info("Exited the selectCheckBoxByName method in BasePage");
	}

	public void clickImageByXpath(String xPathValue) throws WebDriverInstanceNullException, InvalidInputException {
		log.info("Entered the clickImageByXpath method in BasePage");
		if (isWebDriverInstancePresent(webDriver) && isConstantValuePresent(xPathValue)) {
			clickOnButton(By.xpath(xPathValue));
		}
		log.info("Exited the clickImageByXpath method in BasePage");
	}

	public void clickImageById(String idValue) throws WebDriverInstanceNullException, InvalidInputException {
		log.info("Entered the clickImageById method in BasePage");
		if (isWebDriverInstancePresent(webDriver) && isConstantValuePresent(idValue)) {
			clickOnButton(By.id(idValue));
		}
		log.info("Exited the clickImageById method in BasePage");
	}

	public void clickImageByClassName(String classNameValue)
			throws WebDriverInstanceNullException, InvalidInputException {
		log.info("Entered the clickImageByClassName method in BasePage");
		if (isWebDriverInstancePresent(webDriver) && isConstantValuePresent(classNameValue)) {
			clickOnButton(By.className(classNameValue));
		}
		log.info("Exited the clickImageByClassName method in BasePage");
	}

	public void clickLinkByPartialLinkText(String partialLinkTextValue)
			throws WebDriverInstanceNullException, InvalidInputException {
		log.info("Entered the clickLinkByPartialLinkText method in BasePage");
		if (isWebDriverInstancePresent(webDriver) && isConstantValuePresent(partialLinkTextValue)) {
			clickOnButton(By.partialLinkText(partialLinkTextValue));
		}
		log.info("Exited the clickLinkByPartialLinkText method in BasePage");
	}

	public void clickLinkByLinkText(String linkTextValue) throws WebDriverInstanceNullException, InvalidInputException {
		log.info("Entered the clickLinkByLinkText method in BasePage");
		if (isWebDriverInstancePresent(webDriver) && isConstantValuePresent(linkTextValue)) {
			clickOnButton(By.linkText(linkTextValue));
		}
		log.info("Exited the clickLinkByLinkText method in BasePage");
	}

	public void clickLinkByCssSelector(String cssSelectorValue)
			throws WebDriverInstanceNullException, InvalidInputException {
		log.info("Entered the clickLinkByCssSelector method in BasePage");
		if (isWebDriverInstancePresent(webDriver) && isConstantValuePresent(cssSelectorValue)) {
			clickOnButton(By.cssSelector(cssSelectorValue));
		}
		log.info("Exited the clickLinkByCssSelector method in BasePage");
	}

	public void clickLinkByXpath(String xPathValue) throws WebDriverInstanceNullException, InvalidInputException {
		log.info("Entered the clickLinkByXpath method in BasePage");
		if (isWebDriverInstancePresent(webDriver) && isConstantValuePresent(xPathValue)) {
			clickOnButton(By.xpath(xPathValue));
		}
		log.info("Exited the clickLinkByXpath method in BasePage");
	}

	public void clickLinkById(String idValue) throws WebDriverInstanceNullException, InvalidInputException {
		log.info("Entered the clickLinkById method in BasePage");
		if (isWebDriverInstancePresent(webDriver) && isConstantValuePresent(idValue)) {
			clickOnButton(By.id(idValue));
		}
		log.info("Exited the clickLinkById method in BasePage");
	}

	public void selectTabById(String idValue) throws WebDriverInstanceNullException, InvalidInputException {
		log.info("Entered the selectTabById method in BasePage");
		if (isWebDriverInstancePresent(webDriver) && isConstantValuePresent(idValue)) {
			clickOnButton(By.id(idValue));
		}
		log.info("Exited the selectTabById method in BasePage");
	}

	public void selectTabByClassName(String classNameValue)
			throws WebDriverInstanceNullException, InvalidInputException {
		log.info("Entered the selectTabByClassName method in BasePage");
		if (isWebDriverInstancePresent(webDriver) && isConstantValuePresent(classNameValue)) {
			clickOnButton(By.className(classNameValue));
		}
		log.info("Exited the selectTabByClassName method in BasePage");
	}

	public void selectTabByXpath(String xPathValue) throws WebDriverInstanceNullException, InvalidInputException {
		log.info("Entered the selectTabByXpath method in BasePage");
		if (isWebDriverInstancePresent(webDriver) && isConstantValuePresent(xPathValue)) {
			clickOnButton(By.className(xPathValue));
		}
		log.info("Exited the selectTabByXpath method in BasePage");
	}

	public void findTextBoxAndSetValue(By by, String value) throws WebDriverInstanceNullException {
		try {
			log.info("Entered the findTextBoxAndSetValue method in BasePage");
			if (isWebDriverInstancePresent(webDriver)) {
				waitForElement(by);
				webDriver.findElement(by).click();
				webDriver.findElement(by).clear();
				webDriver.findElement(by).sendKeys(value);
			}
			log.info("Exited the findTextBoxAndSetValue method in BasePage");
		} catch (ResourceCustomException ex) {
			new ExceptionHandeler().resourceExceptionHandeler(ex);
		} catch (IOException ex) {
			new ExceptionHandeler().genricExceptionHandeler(ex);
		}

	}

	public void findTextboxAndSetValueByXpath(String xPathValue, String value)
			throws WebDriverInstanceNullException, InvalidInputException {
		log.info("Entered the findTextboxAndSetValueByXpath method in BasePage");
		if (isWebDriverInstancePresent(webDriver) && isConstantValuePresent(xPathValue)) {
			findTextBoxAndSetValue(By.xpath(xPathValue), value);
		}
		log.info("Exited the findTextboxAndSetValueByXpath method in BasePage");
	}

	public void findTextboxAndSetValueById(String idValue, String value)
			throws WebDriverInstanceNullException, InvalidInputException {
		log.info("Entered the findTextboxAndSetValueById method in BasePage");
		if (isWebDriverInstancePresent(webDriver) && isConstantValuePresent(idValue)) {
			findTextBoxAndSetValue(By.id(idValue), value);
		}
		log.info("Exited the findTextboxAndSetValueById method in BasePage");
	}

	public void findTextboxAndSetValueByClassName(String classNameValue, String value)
			throws WebDriverInstanceNullException, InvalidInputException {
		log.info("Entered the findTextboxAndSetValueByClassName method in BasePage");
		if (isWebDriverInstancePresent(webDriver) && isConstantValuePresent(classNameValue)) {
			findTextBoxAndSetValue(By.className(classNameValue), value);
		}
		log.info("Exited the findTextboxAndSetValueByClassName method in BasePage");
	}

	public void findTextboxAndSetValueByCssSelector(String cssSelectorValue, String value)
			throws WebDriverInstanceNullException, InvalidInputException {
		log.info("Entered the findTextboxAndSetValueByCssSelector method in BasePage");
		if (isWebDriverInstancePresent(webDriver) && isConstantValuePresent(cssSelectorValue)) {
			findTextBoxAndSetValue(By.cssSelector(cssSelectorValue), value);
		}
		log.info("Exited the findTextboxAndSetValueByCssSelector method in BasePage");
	}

	public void findTextboxAndSetValueByName(String nameValue, String value)
			throws WebDriverInstanceNullException, InvalidInputException {
		log.info("Entered the findTextboxAndSetValueByName method in BasePage");
		if (isWebDriverInstancePresent(webDriver) && isConstantValuePresent(nameValue)) {
			findTextBoxAndSetValue(By.name(nameValue), value);
		}
		log.info("Exited the findTextboxAndSetValueByName method in BasePage");
	}

	public void fillEmptyText(By by) throws WebDriverInstanceNullException {
		try {
			log.info("Entered the findTextBoxAndSetValue method in BasePage");
			if (isWebDriverInstancePresent(webDriver)) {
				waitForElement(by);
				webDriver.findElement(by).clear();
				webDriver.findElement(by).sendKeys("");
			}
			log.info("Exited the findTextBoxAndSetValue method in BasePage");
		} catch (ResourceCustomException ex) {
			new ExceptionHandeler().resourceExceptionHandeler(ex);
		} catch (IOException ex) {
			new ExceptionHandeler().genricExceptionHandeler(ex);
		}

	}

	public void fillEmptyTextById(String idValue) throws WebDriverInstanceNullException, InvalidInputException {
		log.info("Entered the fillEmptyTextById method in BasePage");
		if (isWebDriverInstancePresent(webDriver) && isConstantValuePresent(idValue)) {
			fillEmptyText(By.id(idValue));
		}
		log.info("Exited the fillEmptyTextById method in BasePage");
	}

	public void fillEmptyTextByxPath(String xPathValue) throws WebDriverInstanceNullException, InvalidInputException {
		log.info("Entered the fillEmptyTextByxPath method in BasePage");
		if (isWebDriverInstancePresent(webDriver) && isConstantValuePresent(xPathValue)) {
			fillEmptyText(By.xpath(xPathValue));
		}
		log.info("Exited the fillEmptyTextByxPath method in BasePage");
	}

	public void fillEmptyTextByPartialLinkText(String partialLinkTextValue)
			throws WebDriverInstanceNullException, InvalidInputException {
		log.info("Entered the fillEmptyTextByPartialLinkText method in BasePage");
		if (isWebDriverInstancePresent(webDriver) && isConstantValuePresent(partialLinkTextValue)) {
			fillEmptyText(By.partialLinkText(partialLinkTextValue));
		}
		log.info("Exited the fillEmptyTextByPartialLinkText method in BasePage");
	}

	public void fillEmptyTextByClassName(String classNameValue)
			throws WebDriverInstanceNullException, InvalidInputException {
		log.info("Entered the fillEmptyTextByClassName method in BasePage");
		if (isWebDriverInstancePresent(webDriver) && isConstantValuePresent(classNameValue)) {
			fillEmptyText(By.className(classNameValue));
		}
		log.info("Exited the fillEmptyTextByClassName method in BasePage");
	}

	public void focusOnElementById(String idValue) throws WebDriverInstanceNullException, InvalidInputException {
		log.info("Entered the focusOnElementById method in BasePage");
		if (isWebDriverInstancePresent(webDriver) && isConstantValuePresent(idValue)) {
			fillEmptyText(By.id(idValue));
		}
		log.info("Exited the focusOnElementById method in BasePage");
	}

	public void focusOnElementByCssSelector(String cssSelectorValue)
			throws WebDriverInstanceNullException, InvalidInputException {
		log.info("Entered the focusOnElementByCssSelector method in BasePage");
		if (isWebDriverInstancePresent(webDriver) && isConstantValuePresent(cssSelectorValue)) {
			fillEmptyText(By.cssSelector(cssSelectorValue));
		}
		log.info("Exited the focusOnElementByCssSelector method in BasePage");
	}

	public void focusOnElementByClassName(String classNameValue)
			throws WebDriverInstanceNullException, InvalidInputException {
		log.info("Entered the focusOnElementByClassName method in BasePage");
		if (isWebDriverInstancePresent(webDriver) && isConstantValuePresent(classNameValue)) {
			fillEmptyText(By.className(classNameValue));
		}
		log.info("Exited the focusOnElementByClassName method in BasePage");
	}

	public void focusOnElementByPartialLinkText(String partialLinkTextValue)
			throws WebDriverInstanceNullException, InvalidInputException {
		log.info("Entered the focusOnElementByPartialLinkText method in BasePage");
		if (isWebDriverInstancePresent(webDriver) && isConstantValuePresent(partialLinkTextValue)) {
			fillEmptyText(By.partialLinkText(partialLinkTextValue));
		}
		log.info("Exited the focusOnElementByPartialLinkText method in BasePage");
	}

	public void focusOnElementByLinkText(String linkTextValue)
			throws WebDriverInstanceNullException, InvalidInputException {
		log.info("Entered the focusOnElementByLinkText method in BasePage");
		if (isWebDriverInstancePresent(webDriver) && isConstantValuePresent(linkTextValue)) {
			fillEmptyText(By.linkText(linkTextValue));
		}
		log.info("Exited the focusOnElementByLinkText method in BasePage");
	}

	public void focusOnElementByXpath(String xPathValue) throws WebDriverInstanceNullException, InvalidInputException {
		log.info("Entered the focusOnElementByXpath method in BasePage");
		if (isWebDriverInstancePresent(webDriver) && isConstantValuePresent(xPathValue)) {
			fillEmptyText(By.xpath(xPathValue));
		}
		log.info("Exited the focusOnElementByXpath method in BasePage");
	}

	/**
	 * @param webElement        Element to move to
	 * @param webDriver Moves the mouse to the middle of the element.
	 *                          Actions : The user-facing API for emulating complex
	 *                          user gestures.Use this class rather than using the
	 *                          Keyboard or Mouse directly. Implements the builder
	 *                          pattern: Builds a CompositeAction containing all
	 *                          actions specified by the method calls. MoveToElement
	 *                          : Moves the mouse to the middle of the element.The
	 *                          element is scrolled into view and its location is
	 *                          calculated using getBoundingClientRect. Perform : A
	 *                          convenience method for performing the actions
	 *                          without calling build() first.
	 */
	public void performMoveToElementAction(WebElement webElement) throws WebDriverInstanceNullException {
		try {
			log.info("Entered the performMoveToElementAction method in BasePage");
			if (isWebDriverInstancePresent(webDriver)
					&& isElementPresent(webElement, getElementFindTimeOut(), webDriver)) {
				new Actions(webDriver).moveToElement(webElement).perform();
			}
			log.info("Entered the performMoveToElementAction method in BasePage");
		} catch (ResourceCustomException ex) {
			new ExceptionHandeler().resourceExceptionHandeler(ex);
		} catch (IOException ex) {
			new ExceptionHandeler().resourceExceptionHandeler(ex);
		}
	}

	/**
	 * @param webElement        Defines the interface through which the user
	 *                          controls elements on the page.
	 * @param webDriver Simulate focus on element by Moves the mouse to the
	 *                          middle of the element. The element is scrolled into
	 *                          view and its location is calculated using
	 *                          getBoundingClientRect. This is working with google
	 *                          chome and other browsers.
	 */
	public void performFocusOnElementAction(WebElement webElement)
			throws WebDriverInstanceNullException, ResourceCustomException, IOException {
		log.info("Entered the performFocusOnElementAction method in BasePage");
		if (isWebDriverInstancePresent(webDriver)
				&& isElementPresent(webElement, getElementFindTimeOut(), webDriver)) {
			performMoveToElementAction(webElement);
		}
		log.info("Exited the performFocusOnElementAction method in BasePage");
	}

	/**
	 * @param idLocatorValue    Defines the interface by locating element Id through
	 *                          which the user controls elements on the page.
	 * @param webDriver Simulate focus on element id by Moves the mouse to
	 *                          the middle of the element. The element is scrolled
	 *                          into view and its location is calculated using
	 *                          getBoundingClientRect. This is working with google
	 *                          chome and other browsers.
	 */
	public void performFocusOnElementActionById(String idLocatorValue) throws WebDriverInstanceNullException,
			InvalidInputException, ResourceCustomException, ElementNotFoundException, IOException {
		log.info("Entered the performFocusOnElementActionById method in BasePage");
		if (isWebDriverInstancePresent(webDriver) && isConstantValuePresent(idLocatorValue)) {
			WebElement idLocator = getWebElementPropertiesById(idLocatorValue);
			performFocusOnElementAction(idLocator);
		}
		log.info("Exited the performFocusOnElementActionById method in BasePage");
	}

	/**
	 * @param xPathLocatorValue Defines the interface by locating element xpath
	 *                          through which the user controls elements on the
	 *                          page.
	 * @param webDriver Simulate focus on element id by Moves the mouse to
	 *                          the middle of the element. The element is scrolled
	 *                          into view and its location is calculated using
	 *                          getBoundingClientRect. This is working with google
	 *                          chome and other browsers.
	 */
	public void performFocusOnElementActionByXpath(String xPathLocatorValue) throws WebDriverInstanceNullException,
			InvalidInputException, ResourceCustomException, ElementNotFoundException, IOException {
		log.info("Entered the performFocusOnElementActionByXpath method in BasePage");
		if (isWebDriverInstancePresent(webDriver) && isConstantValuePresent(xPathLocatorValue)) {
			WebElement idLocator = getWebElementPropertiesByXpath(xPathLocatorValue);
			performFocusOnElementAction(idLocator);
		}
		log.info("Exited the performFocusOnElementActionByXpath method in BasePage");
	}

	/**
	 * @param classNameLocatorValue Defines the interface by locating element class
	 *                              name through which the user controls elements on
	 *                              the page.
	 * @param webDriver     Simulate focus on element id by Moves the mouse
	 *                              to the middle of the element. The element is
	 *                              scrolled into view and its location is
	 *                              calculated using getBoundingClientRect. This is
	 *                              working with google chome and other browsers.
	 */
	public void performFocusOnElementActionByClassName(String classNameLocatorValue)
			throws WebDriverInstanceNullException, InvalidInputException, ResourceCustomException,
			ElementNotFoundException, IOException {
		log.info("Entered the performFocusOnElementActionByClassName method in BasePage");
		if (isWebDriverInstancePresent(webDriver) && isConstantValuePresent(classNameLocatorValue)) {
			WebElement idLocator = getWebElementPropertiesByClassName(classNameLocatorValue);
			performFocusOnElementAction(idLocator);
		}
		log.info("Exited the performFocusOnElementActionByClassName method in BasePage");
	}

	/**
	 * @param frameName         The name of the frame window, the id of the frame or
	 *                          iframe element, or the (zero-based) index.
	 * @param webDriver Select a frame by its name or ID. Frames located by
	 *                          matching name attributes are always given precedence
	 *                          over those matched by ID.
	 */
	public void switchToFrame(String frameName) throws WebDriverInstanceNullException, InvalidInputException {
		log.info("Entered the switchToFrame method in BasePage");
		if (isWebDriverInstancePresent(webDriver) && isConstantValuePresent(frameName)) {
			webDriver.switchTo().frame(frameName);
		}
		log.info("Exited the switchToFrame method in BasePage");
	}

	/**
	 * @param webElement        The frame element to switch to.
	 * @param webDriver Select a frame using its previously located
	 *                          WebElement.
	 */
	public void switchToFrameByWebElement(WebElement webElement)
			throws WebDriverInstanceNullException, ResourceCustomException, IOException {
		log.info("Entered the switchToFrameByWebElement method in BasePage");
		if (isWebDriverInstancePresent(webDriver)
				&& isElementPresent(webElement, getElementFindTimeOut(), webDriver)) {
			webDriver.switchTo().frame(webElement);
		}
		log.info("Exited the switchToFrameByWebElement method in BasePage");
	}

	/**
	 * @param nameValue
	 * @param webDriver Select a frame by its name. Frames located by
	 *                          matching name attributes are always given precedence
	 *                          over those matched by ID.
	 */
	public void switchToFrameByName(String nameValue) throws WebDriverInstanceNullException, InvalidInputException {
		try {
			log.info("Entered the switchToFrameByName method in BasePage");
			if (isWebDriverInstancePresent(webDriver) && isConstantValuePresent(nameValue)) {
				waitForElement(By.name(nameValue));
				switchToFrame(nameValue);
			}
			log.info("Exited the switchToFrameByName method in BasePage");
		} catch (ResourceCustomException ex) {
			new ExceptionHandeler().resourceExceptionHandeler(ex);
		} catch (IOException ex) {
			new ExceptionHandeler().genricExceptionHandeler(ex);
		}

	}

	/**
	 * @param indexNumber       (zero-based) index.
	 * @param webDriver Select a frame by its (zero-based) index. That is,
	 *                          if a page has three frames, the first frame would be
	 *                          at index "0", the second at index "1" and the third
	 *                          at index "2". Once the frame has been selected, all
	 *                          subsequent calls on the WebDriver interface are made
	 *                          to that frame.
	 */
	public void switchToFrameByIndex(int indexNumber) throws WebDriverInstanceNullException {
		log.info("Entered the switchToFrameByIndex method in BasePage");
		if (isWebDriverInstancePresent(webDriver)) {
			webDriver.switchTo().frame(indexNumber);
		}
		log.info("Exited the switchToFrameByIndex method in BasePage");
	}

	/**
	 * @param idValue           This is iFrame id atribute value.
	 * @param webDriver Select a frame by its name or ID. Frames located by
	 *                          matching name attributes are always given precedence
	 *                          over those matched by ID.
	 */
	public void switchToFrameById(String idValue) throws WebDriverInstanceNullException, InvalidInputException {
		try {
			log.info("Entered the switchToFrameById method in BasePage");
			if (isWebDriverInstancePresent(webDriver) && isConstantValuePresent(idValue)) {
				waitForElement(By.id(idValue));
				switchToFrame(idValue);
			}
			log.info("Exited the switchToFrameById method in BasePage");
		} catch (ResourceCustomException ex) {
			new ExceptionHandeler().resourceExceptionHandeler(ex);
		} catch (IOException ex) {
			new ExceptionHandeler().genricExceptionHandeler(ex);
		}

	}

	/**
	 * @param nameValue         This is iFrame source attribute value.
	 * @param webDriver Select a frame by its SOURCE. Frames located by
	 *                          matching SRC attributes
	 */
	public void switchToIFrameBySource(String nameValue) throws WebDriverInstanceNullException,
			ElementNotFoundException, ResourceCustomException, InvalidInputException, IOException {
		try {
			log.info("Entered the switchToIFrameBySource method in BasePage");
			if (isWebDriverInstancePresent(webDriver) && isConstantValuePresent(nameValue)) {
				List<WebElement> frames = webDriver.findElements(By.tagName("iframe"));
				if (frames == null) {
					return;
				}
				for (WebElement iframe : frames) {
					if (iframe.getAttribute("src").contains(nameValue)) {
						switchToFrameByWebElement(iframe);
						break;
					} else {
						throw new IFrameNotFoundException("Iframe " + nameValue + " do not exists");
					}
				}
			}
			log.info("Exited the switchToIFrameBySource method in BasePage");
		} catch (IFrameNotFoundException ex) {
			new ExceptionHandeler().webDriverExceptionhandeler(ex);
		}
	}

	/**
	 * @param webDriver Selects either the first frame on the page, or the
	 *                          main document when a page contains iframes.
	 *                          DefaultContent : This driver focused on the top
	 *                          window/first frame.
	 */
	public void switchToDefaultPageContent(WebDriver webDriver) throws WebDriverInstanceNullException {
		log.info("Entered the switchToDefaultPageContent method in BasePage");
		if (isWebDriverInstancePresent(webDriver)) {
			webDriver.switchTo().defaultContent();
		}
		log.info("Exited the switchToDefaultPageContent method in BasePage");
	}

	/**
	 * @param webDriver Switches to the element that currently has focus
	 *                          within the document currently "switched to", or the
	 *                          body element if this cannot be detected. This
	 *                          matches the semantics of calling
	 *                          "document.activeElement" in Javascript. Active
	 *                          Element : The WebElement with focus, or the body
	 *                          element if no element with focus can be detected.
	 */
	public void switchToActivePageElement(WebDriver webDriver) throws WebDriverInstanceNullException {
		log.info("Entered the switchToActivePageElement method in BasePage");
		if (isWebDriverInstancePresent(webDriver)) {
			webDriver.switchTo().activeElement();
		}
		log.info("Exited the switchToActivePageElement method in BasePage");
	}

	/**
	 * @param webDriver Switch the focus of future commands for this driver
	 *                          to the parent window with the given empty string.
	 */
	public void switchToDefaultWindow(WebDriver webDriver) throws WebDriverInstanceNullException {
		log.info("Entered the switchToDefaultWindow method in BasePage");
		if (isWebDriverInstancePresent(webDriver)) {
			webDriver.switchTo().defaultContent();
		}
		log.info("Exited the switchToDefaultWindow method in BasePage");
	}

	/**
	 * @param webDriver Switch the focus of future commands for this driver
	 *                          to the parent window with the given empty string
	 */
	public void switchToOtherWindow(WebDriver webDriver) throws WebDriverInstanceNullException {
		log.info("Entered the switchToOtherWindow method in BasePage");
		if (isWebDriverInstancePresent(webDriver)) {
			Set<String> allWindows = webDriver.getWindowHandles();
			for (String handel : allWindows) {
				webDriver.switchTo().window(handel);
			}
			webDriver.switchTo().defaultContent();
		}
		log.info("Exited the switchToOtherWindow method in BasePage");
	}

	public WebElement getWebElementProperties(By by) throws WebDriverInstanceNullException {
		try {
			WebElement element = null;
			if (isWebDriverInstancePresent(webDriver)) {
				log.info("Entered the getWebElementProperties method in BasePage");
				waitForElement(by);
				log.info("Exited the getWebElementProperties method in BasePage");
				element = webDriver.findElement(by);
			}
			return element;
		} catch (ResourceCustomException ex) {
			new ExceptionHandeler().resourceExceptionHandeler(ex);
			return null;
		} catch (IOException ex) {
			new ExceptionHandeler().genricExceptionHandeler(ex);
			return null;
		}
	}

	public List<WebElement> getWebElementsProperties(By by) throws WebDriverInstanceNullException {
		try {
			List<WebElement> elementList = new ArrayList<>();
			if (isWebDriverInstancePresent(webDriver)) {
				log.info("Entered the getWebElementsProperties method in BasePage");
				waitForElement(by);
				log.info("Exited the getWebElementsProperties method in BasePage");
				elementList = webDriver.findElements(by);
			}
			return elementList;
		} catch (ResourceCustomException ex) {
			new ExceptionHandeler().resourceExceptionHandeler(ex);
			return null;
		} catch (IOException ex) {
			new ExceptionHandeler().genricExceptionHandeler(ex);
			return null;
		}
	}

	public WebElement getWebElementPropertiesById(String idValue)
			throws WebDriverInstanceNullException, InvalidInputException {
		WebElement element = null;
		if (isWebDriverInstancePresent(webDriver) && isConstantValuePresent(idValue)) {
			log.info("Entered the getWebElementPropertiesById method in BasePage");
			element = getWebElementProperties(By.id(idValue));
			log.info("Exited the getWebElementPropertiesById method in BasePage");
		}
		return element;
	}

	public WebElement getWebElementPropertiesByXpath(String xPathValue)
			throws WebDriverInstanceNullException, InvalidInputException {
		WebElement element = null;
		if (isWebDriverInstancePresent(webDriver) && isConstantValuePresent(xPathValue)) {
			log.info("Entered the getWebElementPropertiesByXpath method in BasePage");
			element = getWebElementProperties(By.xpath(xPathValue));
			log.info("Exited the getWebElementPropertiesByXpath method in BasePage");
		}
		return element;
	}

	public WebElement getWebElementPropertiesByClassName(String classNameValue)
			throws WebDriverInstanceNullException, InvalidInputException {
		WebElement element = null;
		if (isWebDriverInstancePresent(webDriver) && isConstantValuePresent(classNameValue)) {
			log.info("Entered the getWebElementPropertiesByClassName method in BasePage");
			element = getWebElementProperties(By.className(classNameValue));
			log.info("Exited the getWebElementPropertiesByClassName method in BasePage");
		}
		return element;
	}

	private void selectDropDownValueByText(By by, String value) throws WebDriverInstanceNullException {
		try {
			log.info("Entered the selectDropDownValueByText method in BasePage");
			if (isWebDriverInstancePresent(webDriver)) {
				waitForElement(by);
				Select dropDown = new Select(webDriver.findElement(by));
				dropDown.selectByVisibleText(value);
			}
			log.info("Exited the selectDropDownValueByText method in BasePage");
		} catch (ResourceCustomException ex) {
			new ExceptionHandeler().resourceExceptionHandeler(ex);
		} catch (IOException ex) {
			new ExceptionHandeler().genricExceptionHandeler(ex);
		}
	}

	public void selectDropDownValueThroughTextById(String id, String selectTextValue)
			throws WebDriverInstanceNullException {
		log.info("Entered the selectDropDownValueThroughTextById method in BasePage");
		selectDropDownValueByText(By.id(id), selectTextValue);
		log.info("Exited the selectDropDownValueThroughTextById method in BasePage");
	}

	public void selectDropDownValueThroughTextByCssSelector(String cssSelector, String selectTextValue)
			throws WebDriverInstanceNullException {
		log.info("Entered the selectDropDownValueThroughTextById method in BasePage");
		selectDropDownValueByText(By.cssSelector(cssSelector), selectTextValue);
		log.info("Exited the selectDropDownValueThroughTextById method in BasePage");
	}

	public void selectDropDownValueThroughTextByName(String name, String selectTextValue)
			throws WebDriverInstanceNullException {
		log.info("Entered the selectDropDownValueThroughTextById method in BasePage");
		selectDropDownValueByText(By.name(name), selectTextValue);
		log.info("Exited the selectDropDownValueThroughTextById method in BasePage");
	}

	public void selectDropDownValueThroughTextByXpath(String xpathValue, String selectTextValue)
			throws WebDriverInstanceNullException {
		log.info("Entered the selectDropDownValueThroughTextById method in BasePage");
		selectDropDownValueByText(By.xpath(xpathValue), selectTextValue);
		log.info("Exited the selectDropDownValueThroughTextById method in BasePage");
	}

	public void closeAllBrowserWindow() throws WebDriverInstanceNullException {
		try {
			log.info("Entered the closeAllBrowserWindow method in BasePage");
			if (isWebDriverInstancePresent(webDriver)) {
				Thread.sleep(1000);
				webDriver.quit();
				Thread.sleep(1000);
			}
			log.info("Exited the closeAllBrowserWindow method in BasePage");
		} catch (InterruptedException ex) {
			new ExceptionHandeler().genricExceptionHandeler(ex);
		}
	}

	public void scrollUpTheBrowser() throws WebDriverInstanceNullException {
		log.info("Entered the scrollUpTheBrowser method in BasePage");
		if (isWebDriverInstancePresent(webDriver)) {
			JavascriptExecutor jse = (JavascriptExecutor) webDriver;
			jse.executeScript("scroll(0, -250);");
		}
		log.info("Exited the scrollUpTheBrowser method in BasePage");
	}

	public void scrollDownTheBrowser(int scrollDownVal) throws WebDriverInstanceNullException {
		log.info("Entered the scrollDownTheBrowser method in BasePage");
		if (isWebDriverInstancePresent(webDriver)) {
			JavascriptExecutor jse = (JavascriptExecutor) webDriver;
			jse.executeScript("scroll(0," + scrollDownVal + ");");
		}
		log.info("Exited the scrollDownTheBrowser method in BasePage");
	}

	public void scrollToViewWebElement(WebElement element) {
		log.info("Entered the scrollToViewWebElement method in BasePage");
		((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();", element);
		log.info("Exited the scrollToViewWebElement method in BasePage");
	}

	public void clickByJavaScriptWebElement(WebElement element) {
		log.info("Entered the clickByJavaScriptWebElement method in BasePage");
		((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", element);
		log.info("Exited the clickByJavaScriptWebElement method in BasePage");
	}

}
