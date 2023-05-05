package stepdefinition;

import org.openqa.selenium.WebDriver;
import com.adviserratinguiautomation.base.BasePage;
import com.adviserratinguiautomation.base.BaseTestScripts;
import com.adviserratinguiautomation.customexceptions.ExceptionHandeler;
import com.adviserratinguiautomation.customexceptions.ResourceCustomException;
import com.adviserratinguiautomation.customexceptions.drivercustomexceptions.InvalidInputException;
import com.adviserratinguiautomation.customexceptions.drivercustomexceptions.WebDriverInstanceNullException;
import com.adviserratinguiautomation.resourceRead.ResourceRead;

import java.io.IOException;
import org.apache.log4j.Logger;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import uipages.AdviserRatingPage;

public class AdviserRatingsSteps extends BasePage {

	final static Logger log = Logger.getLogger(AdviserRatingsSteps.class);

	String appUrl;
	public String scenarioName;
	private AdviserRatingPage adviserratingpage = new AdviserRatingPage();

	
	// Launching url page with driver instance is performed

	@Given("I Navigate to {string}")
	public void user_opens_URL(String urlType) {
		try {
			log.info("Step: Navigated to {String}");

			String getAppUrl = new ResourceRead().getEnvironmentConfigValue().getProperty("adviserratingurl");
			String getEnvType = new ResourceRead().getEnvironmentConfigValue().getProperty("envType");
			// loginPage.verifyLogout();
			switch (urlType) {
			case "AdviserratingURL":

				appUrl = String.format(getAppUrl, getEnvType);
				break;

			}

			LaunchURL(appUrl);
			System.out.println("appUrl formation:" + appUrl);
			log.info("Opened URL: " + appUrl);
		} catch (Exception ex) {
			new ExceptionHandeler().genricExceptionHandeler(ex);
		}
	}

	
// Move to Advisortab

	@When("I Click on Adviser Tab")
	public void click_on_adviser_tab() {
		try {
			log.info("Step: Click on Adviser Tab");
			Thread.sleep(2000);
			adviserratingpage.clickAdviserTab();
		} catch (Exception ex) {
			new ExceptionHandeler().genricExceptionHandeler(ex);
		}
	}

	@When("I Enter {string} inside the Search box")
	public void enter_inside_the_search_box1(String value) {
		log.info("Step: Enter the value" + value + "on Search box");
		try {
			Thread.sleep(2000);
			adviserratingpage.searchValue(value);
		} catch (Exception ex) {
			new ExceptionHandeler().genricExceptionHandeler(ex);
		}
	}

	@When("Enter {string} on the Search box")
	public void enter_on_the_search_box(String adviserName) {
		log.info("Step: Enter the adviser name" + adviserName + "on Search box");
		try {
			Thread.sleep(2000);
			adviserratingpage.searchAdviserName(adviserName);
		} catch (Exception ex) {
			new ExceptionHandeler().genricExceptionHandeler(ex);
		}
	}

	@Then("Dropdown should be displayed with {string}")
	public void dropdown_should_be_displayed_with(String adviserName) {
		try {
			Thread.sleep(2000);
			adviserratingpage.verifyadviserNameonDropDown(adviserName);
		} catch (Exception ex) {
			new ExceptionHandeler().genricExceptionHandeler(ex);
		}
	}

	@Then("Dropdown should be highlighted for the {string}")
	public void dropdown_should_be_highlighted_for_the(String adviserName) {
		try {
			Thread.sleep(1000);
			adviserratingpage.verifyadviserNameHighlighted(adviserName);
		} catch (Exception ex) {
			new ExceptionHandeler().genricExceptionHandeler(ex);
		}
	}

	@Then("Practice under the {string} should be {string}")
	public void practice_under_the_should_be(String adviserName, String practise) {
		try {
			Thread.sleep(2000);
			adviserratingpage.verifyadviserPractise(adviserName, practise);
		} catch (Exception ex) {
			new ExceptionHandeler().genricExceptionHandeler(ex);
		}
	}

	@Then("Two locations should be listed next to the adviser as {string} and {string}")
	public void two_locations_should_be_listed_next_to_the_and(String location1, String location2) {
		try {
			Thread.sleep(2000);
			adviserratingpage.verifyLocations(location1, location2);
		} catch (Exception ex) {
			new ExceptionHandeler().genricExceptionHandeler(ex);
		}
	}
//click enter

	@When("click on Enter key")
	public void click_on_enter_key() {
		try {
			log.info("Step: Click on Enter Key");
			Thread.sleep(2000);

			adviserratingpage.enterKey();
		} catch (Exception ex) {
			new ExceptionHandeler().genricExceptionHandeler(ex);
		}
	}

	@When("On clicking Enter key")
	public void enter_key() {
		try {
			log.info("Step: Click on Enter Key");
			Thread.sleep(2000);

			adviserratingpage.enterKey_advisor();
		} catch (Exception ex) {
			new ExceptionHandeler().genricExceptionHandeler(ex);
		}
	}
//url verification 

	@Then("User should be redirected to the url {string}")
	public void user_should_be_redirected_to_the(String url) {
		try {
			log.info("Step: User should be redirected to the url");
			Thread.sleep(2000);
			adviserratingpage.verifyUrl(url);
		} catch (Exception ex) {
			new ExceptionHandeler().genricExceptionHandeler(ex);
		}
	}

//To go to Location Tab

	@And("Go to location Tab")
	public void Go_to_location_Tab() {
		try {
			Thread.sleep(2000);
			adviserratingpage.goToLocationTab();
		} catch (Exception ex) {
			new ExceptionHandeler().genricExceptionHandeler(ex);
		}
	}

//Step Called - Provide input value and click enter from dropdown and verify it is highlighted 

	@And("Find {string} in the address drop down search box and check it is highlighted")
	public void selectLocationFeild(String Expectedlocation) throws InterruptedException,
			WebDriverInstanceNullException, InvalidInputException, ResourceCustomException, IOException {
		try {
			Thread.sleep(2000);
			adviserratingpage.selectLocationfeildcall(Expectedlocation);
		} catch (Exception ex) {
			new ExceptionHandeler().genricExceptionHandeler(ex);
		}

	}

// Verify the current url value with expected one

	@And("verify it is redirected to the url {string}")
	public void verify_CurrentUrl(String url) {
		try {
			Thread.sleep(2000);
			adviserratingpage.verifySydneycurrentUrl(url);
		} catch (Exception ex) {
			new ExceptionHandeler().genricExceptionHandeler(ex);
		}

	}

// Verify dropdown box values results , listview is selected and value range should be closest advisers in location Sydney, NSW 2000 are listed in the search results page on page pagination 1

	@And("verify the dropdown box contains location as {string}, listview and KMRange {string} in {string}")
	public void verify_the_dropdown_box(String location, String km, String searchResult) {
		try {
			Thread.sleep(2000);
			adviserratingpage.verifyTheDropDownBox(location, km, searchResult);
		} catch (Exception ex) {
			new ExceptionHandeler().genricExceptionHandeler(ex);
		}

	}

	@And("check the view is ListView")
	public void listViewVerify() {
		try {
			Thread.sleep(2000);
			adviserratingpage.listViewVerify();
		} catch (Exception ex) {
			new ExceptionHandeler().genricExceptionHandeler(ex);
		}

	}

// To visit Adviser Tab and enter the "Brett Dillon" and select from dropdown 
	/*
	 * @When ("I Navigate to Adviser Tab, enter {string} and click enter") public
	 * void dropdownselect(String adviserName) throws InterruptedException,
	 * ResourceCustomException, IOException { try{ Thread.sleep(2000);
	 * adviserratingpage.dropdownselectAdvisor(adviserName); }catch (Exception ex) {
	 * new ExceptionHandeler().genricExceptionHandeler(ex); }
	 * 
	 * }
	 */

	@And("Iterate the dropdown and select {string}")
	public void dropdownselect(String adviserName) throws InterruptedException, ResourceCustomException, IOException {
		try {
			Thread.sleep(2000);
			adviserratingpage.dropdownselectAdvisor(adviserName);
		} catch (Exception ex) {
			new ExceptionHandeler().genricExceptionHandeler(ex);
		}
	}
// Get the current Page URL and verify

	@Then("The current Page URL which should contain {string}")
	public void currentUrl(String name) {
		try {
			Thread.sleep(2000);
			adviserratingpage.advisornameCurrentUrl(name);
		} catch (Exception ex) {
			new ExceptionHandeler().genricExceptionHandeler(ex);
		}
	}

	@And("Check {string} is populated into the location field")
	public void verifylocation(String location) {
		try {
			Thread.sleep(2000);
			adviserratingpage.locationfeildVerify(location);
		} catch (Exception ex) {
			new ExceptionHandeler().genricExceptionHandeler(ex);
		}
	}

	@And("The  practice name is displayed under the map is {string}")
	public void practiceName(String practiceName) throws ResourceCustomException, IOException {
		try {
			Thread.sleep(2000);
			adviserratingpage.practiceName(practiceName);
		} catch (Exception ex) {
			new ExceptionHandeler().genricExceptionHandeler(ex);
		}
	}

// Check the practice logo information is correct

	@And("The practice logo is displayed")
	public void practiceLogo() throws ResourceCustomException, IOException {
		try {
			Thread.sleep(2000);
			adviserratingpage.practiceLogo();
		} catch (Exception ex) {
			new ExceptionHandeler().genricExceptionHandeler(ex);
		}
	}

//verify adviser profile image
	@And("The Advisers profile image is displayed with src url contains {string}")
	public void adviserProfile(String src) throws ResourceCustomException, IOException {
		try {
			Thread.sleep(2000);
			adviserratingpage.adviserImage(src);
		} catch (Exception ex) {
			new ExceptionHandeler().genricExceptionHandeler(ex);
		}
	}

//verify map pinnedaddress
	@When("The google map is displayed with the pin to the address in {string}")
	public void mapPinnedAddress(String address) throws ResourceCustomException, IOException {
		try {
			Thread.sleep(2000);
			adviserratingpage.mapPinnedAddress(address);
		} catch (Exception ex) {
			new ExceptionHandeler().genricExceptionHandeler(ex);
		}
	}

// verify mapurl center parameter value 
	@Then("The Map URL center parameter value should contains {string}")
	public void mapParameterValue(String address) throws ResourceCustomException, IOException {
		try {
			Thread.sleep(2000);
			adviserratingpage.mapParameterValue(address);
		} catch (Exception ex) {
			new ExceptionHandeler().genricExceptionHandeler(ex);
		}
	}

// Check the name, advisername and location

	@And("The name is {string}")
	public void nameValidation(String name) throws ResourceCustomException, IOException {
		try {
			Thread.sleep(2000);
			adviserratingpage.nameVerify(name);
		} catch (Exception ex) {
			new ExceptionHandeler().genricExceptionHandeler(ex);
		}
	}

	@And("The Advisor name is {string}")
	public void adviserNameValidation(String adviserName) throws ResourceCustomException, IOException {
		try {
			Thread.sleep(2000);
			adviserratingpage.adviserNameVerify(adviserName);
		} catch (Exception ex) {
			new ExceptionHandeler().genricExceptionHandeler(ex);
		}
	}

	@And("The location will be {string}")
	public void locationValidation(String location) throws ResourceCustomException, IOException {
		try {
			Thread.sleep(2000);
			adviserratingpage.locationVerify(location);
		} catch (Exception ex) {
			new ExceptionHandeler().genricExceptionHandeler(ex);
		}
	}

// verify Tab Info 

	@And("About tab is {string}")
	public void aboutTab(String AboutName) throws ResourceCustomException, IOException {

		try {
			Thread.sleep(2000);
			adviserratingpage.aboutTabCheckCall(AboutName);
		} catch (Exception ex) {
			new ExceptionHandeler().genricExceptionHandeler(ex);
		}
	}

// Invoke Method locationPanelCall to locate map and check the image

	@And("Check the location panel with advisor name {string} and src url {string}")
	public void locationPanel(String adviserName, String srcUrl) throws ResourceCustomException, IOException {
		try {
			Thread.sleep(2000);
			adviserratingpage.locationPanelCall(adviserName, srcUrl);
		} catch (Exception ex) {
			new ExceptionHandeler().genricExceptionHandeler(ex);
		}
	}

// Invoke Method addressCheckCall to print the address

	@And("Address check is performed")
	public void addressCheck() {
		try {
			Thread.sleep(2000);
			adviserratingpage.addressCheckCall();
		} catch (Exception ex) {
			new ExceptionHandeler().genricExceptionHandeler(ex);
		}

	}

	@When("Enter {string} inside the Search box")
	public void enter_inside_the_search_box(String value) {
		log.info("Step: Enter the value" + value + "on Search box");
		try {
			Thread.sleep(2000);
			adviserratingpage.searchValue(value);
		} catch (Exception ex) {
			new ExceptionHandeler().genricExceptionHandeler(ex);
		}
	}

	@Then("Find {string} from the Address search dropdown and click on it")
	public void Address_dropdown_should_be_displayed_with(String location) {
		try {
			Thread.sleep(2000);
			adviserratingpage.findAddressCall(location);
//adviserratingpage.verifyadviserNameonDropDown(location);
		} catch (Exception ex) {
			new ExceptionHandeler().genricExceptionHandeler(ex);
		}
	}

	@And("select for {string} from dropdown and click search")
	public void selectvalueSearch(String distance) {

		try {
			Thread.sleep(2000);
			adviserratingpage.selectvalueSearchCall(distance);
		} catch (Exception ex) {
			new ExceptionHandeler().genricExceptionHandeler(ex);
		}

	}

	@Then("Get CurrentPageUrl as{string}, location name {string},  distance {string} and verify search result as {string}")
	public void currentPageurlVerify(String url, String locationName, String km, String SearchResult)
			throws InterruptedException, ResourceCustomException, IOException {

		Thread.sleep(2000);
		adviserratingpage.currentPageUrlVerifyCall(url, locationName, km, SearchResult);

	}

	@And("Distance should be displayed as {string}")
	public void Distance_value(String distance) {
		try {
			Thread.sleep(2000);
			adviserratingpage.distanceValue(distance);
		} catch (Exception ex) {
			new ExceptionHandeler().genricExceptionHandeler(ex);
		}

	}

	@And("check ascendingorder of KMRange")
	public void ascendingorder() throws InterruptedException {
		try {
			Thread.sleep(2000);
			adviserratingpage.ascendingordercall();
		} catch (Exception ex) {
			new ExceptionHandeler().genricExceptionHandeler(ex);
		}
	}

	@And("check descendingorder of KMRange")
	public void descendingOrder() throws InterruptedException {
		try {
			Thread.sleep(2000);
			adviserratingpage.descendingorderCall();

		} catch (Exception ex) {
			new ExceptionHandeler().genricExceptionHandeler(ex);
		}
	}

	@And("location name should be {string}")
	public void location_name(String locName) {
		try {
			Thread.sleep(2000);
			adviserratingpage.locationName(locName);
		} catch (Exception ex) {
			new ExceptionHandeler().genricExceptionHandeler(ex);
		}
	}

	@And("Logout the browser")
	public void logout() throws InterruptedException {
		Thread.sleep(2000);
		webDriver.quit();
	}

}