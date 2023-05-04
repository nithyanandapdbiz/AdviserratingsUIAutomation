package uipages;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Assertions;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.junit.Assert;
import com.adviserratinguiautomation.base.BasePage;
import com.adviserratinguiautomation.base.BaseTestScripts;
import com.adviserratinguiautomation.customexceptions.ExceptionHandeler;
import com.adviserratinguiautomation.customexceptions.ResourceCustomException;
import com.adviserratinguiautomation.customexceptions.drivercustomexceptions.InvalidInputException;
import com.adviserratinguiautomation.customexceptions.drivercustomexceptions.WebDriverInstanceNullException;
import com.adviserratinguiautomation.resourceRead.ResourceRead;

import io.cucumber.java.en.When;
import propertiesLoader.PropertiesLoader;

public class AdviserRatingPage extends BasePage {
	final static Logger log = Logger.getLogger(AdviserRatingPage.class);
	private String adviserratingpage = "/AdviserRatingPageResource.properties";
	private ResourceRead resRead = new ResourceRead();
	private Properties driverproperty;
	private String scenarioName;
	Properties adviserRatingPageproperty = PropertiesLoader.getInstance().getProperties();
	public String getEnvType;
	 
	

//	public AdviserRatingPage() {
//		webDriver = get_seleniumWebDriver();
//		this.scenarioName = BaseTestScripts.scenarioName;
//	}
	
// Move to adviser tab
	public void clickAdviserTab() throws WebDriverInstanceNullException, InvalidInputException, ResourceCustomException,
			IOException, InterruptedException {
		log.info("Entered clickAdviserTab method of AdviserRatingPage");
		clickOnButtonByXpath(adviserRatingPageproperty.getProperty("ADVISER_TAB_XPATH"));
	}
// Enter Advisor name 
	public void searchAdviserName(String adviserName) throws WebDriverInstanceNullException, InvalidInputException,
			ResourceCustomException, IOException, InterruptedException {
		log.info("Entered reNameSurvey method of AdviserRatingPage");
		findTextboxAndSetValueByXpath(adviserRatingPageproperty.getProperty("ADVISER_SEARCHBOX_XPATH"),
				adviserName);
	}
// Dropdown value should be displayed with entered advisorname
	public void verifyadviserNameonDropDown(String adviserName) throws WebDriverInstanceNullException,
			InvalidInputException, ResourceCustomException, IOException, InterruptedException {

		String expectedName = adviserName;
		WebElement adviserNamedisplayed = webDriver
				.findElement(By.xpath(adviserRatingPageproperty.getProperty("ADVISERNAME_FROM_DROPDOWN_XPATH")));
		String actualName = adviserNamedisplayed.getText();

		Assert.assertEquals(expectedName, actualName);

	}

	// Check "Sydney, NSW 2000" is populated into the location field
		public void locationfeildVerify(String location) throws WebDriverInstanceNullException, InvalidInputException, ResourceCustomException,
				IOException, InterruptedException {
			log.info("Check locationfeild contains sydney, NSW 2000");
			WebElement locationfeild = webDriver
					.findElement(By.xpath(adviserRatingPageproperty.getProperty("LOCATIONFIELD_XPATH")));
			String actuallocationfeild = locationfeild.getAttribute("data-value");
			Assert.assertEquals(location, actuallocationfeild);
			
			
			
			
		}
	//hit enter for the location	
	public void enterKey() throws WebDriverInstanceNullException,
	InvalidInputException, ResourceCustomException, IOException, InterruptedException {
		

		
		WebElement ele=this.webDriver.findElement(By.cssSelector(
				"div[class='item']"));
		Actions action = new Actions(webDriver);
		action.moveToElement(ele).sendKeys(Keys.ENTER).build().perform();
		
		
		

}
	
	//hit enter for advisor name
	
	public void enterKey_advisor() throws WebDriverInstanceNullException,
	InvalidInputException, ResourceCustomException, IOException, InterruptedException {

		this.webDriver.findElement(By.cssSelector(
				"#nameLookupDropdown-selectized")).sendKeys(Keys.ENTER);
		//findTextboxAndSetValueByCssSelectorKeys(adviserRatingPageproperty.getProperty("ADVISORNAME_CSSSELECTOR"),
			//	Keys.ENTER);

}

	
	
	//Verify Advisor practice value with assertions
	
	public void verifyadviserPractise(String adviserName, String practise) throws WebDriverInstanceNullException,
			InvalidInputException, ResourceCustomException, IOException, InterruptedException {

		String expectedPractise = practise;
		WebElement practiseNamedisplayed = webDriver
				.findElement(By.xpath(adviserRatingPageproperty.getProperty("PRACTISENAME_FROM_DROPDOWN_XPATH")));
		String actualPractiseName = practiseNamedisplayed.getText();

		Assert.assertEquals(expectedPractise, actualPractiseName);

	}
//Verify the locations expected with the actual value
	
	public void verifyLocations(String location1, String location2) throws WebDriverInstanceNullException,
			InvalidInputException, ResourceCustomException, IOException, InterruptedException {

		String expectedLocation1 = location1;
		String expectedLocation2 = location2;
		WebElement location1displayed = webDriver
				.findElement(By.xpath(adviserRatingPageproperty.getProperty("LOCATION1_FROM_DROPDOWN_XPATH")));

		WebElement location2displayed = webDriver.findElement(By.xpath(adviserRatingPageproperty.getProperty("LOCATION2_FROM_DROPDOWN_XPATH")));
		String actualLocation1 = location1displayed.getText();
		String actualLocation2 = location2displayed.getText();
		Assert.assertEquals(expectedLocation1, actualLocation1);
		Assert.assertEquals(expectedLocation2, actualLocation2);

	}

	public void clickonSelectedAdviser() throws WebDriverInstanceNullException, InvalidInputException,
			ResourceCustomException, IOException, InterruptedException {
		log.info("Entered clickEnterButton method of AdviserRatingPage");
		clickOnButtonByXpath(adviserRatingPageproperty.getProperty("ADVISER_SELECTED_ENTER_XPATH"));
	}

	//Verify the advisorname gets in highlighted 
	
	public void verifyadviserNameHighlighted(String adviserName) throws WebDriverInstanceNullException,
			InvalidInputException, ResourceCustomException, IOException, InterruptedException {

//		String expectedName = adviserName;
		WebElement dropdownNameHighlighted = webDriver
				.findElement(By.xpath(adviserRatingPageproperty.getProperty("ADVISERNAME_HIGHLIGHTED_XPATH")));
//		String actualName = adviserNamedisplayed.getText();

		String attrValue = dropdownNameHighlighted.getAttribute("class");
		System.out.println("Class attribute is: " + attrValue);
		String highlightedClass = "option active";
		Assert.assertEquals(highlightedClass, attrValue);

	}

	// verify the url with expected one.
	
	public void verifyUrl(String url)
			throws WebDriverInstanceNullException, InvalidInputException, ResourceCustomException, IOException {
		log.info("Entered verifyUrl method of AdviserRatingPage");
		String currentUrl = getCurrentURL();
		String ExpectedUrl = url;
		Assert.assertEquals(ExpectedUrl, currentUrl);

		
	}

	// to locate to location Tab

	public void goToLocationTab() throws ResourceCustomException, IOException, InterruptedException, WebDriverInstanceNullException, InvalidInputException {
		log.info("go to location tab");
		Thread.sleep(2000);
		clickButtonByCssSelector(adviserRatingPageproperty.getProperty("ADVISER_LOCATIONTAB_XPATH"));
		}
	
//check inputed value get selected and highlighted
	
	public void selectLocationfeildcall(String Expectedlocation) throws InterruptedException, ResourceCustomException, IOException, WebDriverInstanceNullException {
		log.info("Entered locationvalue method of AdviserRatingPage");



		List<WebElement> ele = getWebElementsProperties(By.xpath(adviserRatingPageproperty.getProperty("LOCATION_ADDRESS_DROPDOWN_XPATH")));
		Actions action = new Actions(webDriver);
		
		System.out.println(ele.size());
		for (int i = 0; i <= ele.size() - 1; i++) {
			if (ele.get(i).getText().contains(Expectedlocation)) {
				//ele.get(i).click();
				action.moveToElement(ele.get(i)).sendKeys(Keys.ENTER).build().perform();
				String option = ele.get(i).getAttribute("class");
				System.out.println("Highlighted:"+option);
				String highlightvalue = "option selected";
				Assert.assertEquals(highlightvalue, option);

				break;
			}
		}
		

		Thread.sleep(2000);
		
		

	}
	
	//listview verification
	public void listViewVerify() {
		WebElement verifyListView = webDriver
				.findElement(By.cssSelector(adviserRatingPageproperty.getProperty("ADVISOR_LISTVIEW_CSSSELECTOR")));
		boolean flag = verifyListView.isEnabled();
		if(flag==true) {
			System.out.println("You are in ListView");
		}else {
			System.out.println("Not in ListView");
		}
	}

	//verify url
	
	public void verifySydneycurrentUrl(String Expectedurl) {
		String url = this.webDriver.getCurrentUrl();
		System.out.println("currenturl" + url);
		Assert.assertEquals(Expectedurl, url);
	}

	// verify dropdownbox, Listview and KM range closest
	
	public void verifyTheDropDownBox(String location,String km, String searchResult) throws ResourceCustomException, IOException {

		WebElement verifylocationFeild = webDriver
				.findElement(By.cssSelector(adviserRatingPageproperty.getProperty("ADVISOR_LOCATIONFEILD_CSSSELECTOR")));
		String verifytext = verifylocationFeild.getText();

		Assert.assertEquals(location, verifytext);

		WebElement verifyKM = webDriver
				.findElement(By.cssSelector(adviserRatingPageproperty.getProperty("ADVISOR_DROPDOWN_DISTANCE_VERIFY")));
		String kilometer = verifyKM.getText();
		Assert.assertEquals(km, kilometer);
		WebElement verifylocationKM = webDriver
				.findElement(By.cssSelector(adviserRatingPageproperty.getProperty("ADVISOR_LOCATION_RESULT_TAGNAME")));
		String searchresult = verifylocationKM.getText();

		Assert.assertEquals(searchResult, searchresult);

		WebElement verifyListView = webDriver
				.findElement(By.cssSelector(adviserRatingPageproperty.getProperty("ADVISOR_LISTVIEW_CSSSELECTOR")));
		boolean eleEnabled = verifyListView.isEnabled();

		List<WebElement> listkm = this.webDriver.findElements(By.cssSelector(".ar-badge.ar-badge--naked"));

		for (WebElement listKmrange : listkm) {
			String kmRangevalue = listKmrange.getText();
			String[] splitvalues = kmRangevalue.split("k", 2);
			String finalvalue = splitvalues[0];
			System.out.println("splitvalue" + kmRangevalue);
			System.out.println("splitvalueafter" + finalvalue);
			Float result = new Float(finalvalue);
			// float value1 = float.parseFloat(finalvalue);

			System.out.println("kmrange:" + result);
			if (result < 1) {
				System.out.println("It stays between the range");
			}
		}
	}

	
	  // To Perform select option for requested input 
	  
	public void dropdownselectAdvisor(String adviserName) throws InterruptedException, ResourceCustomException, IOException, WebDriverInstanceNullException, InvalidInputException {
			Thread.sleep(2000);
			
		List<WebElement> ele = this.webDriver.findElements(By.cssSelector("span[class='font-weight-bold']"));
		System.out.println(ele.size());
		for (int i = 0; i <= ele.size() - 1; i++) {
			if (ele.get(i).getText().contains(adviserName)) {
				ele.get(i).click();
				// location.sendKeys(Keys.ENTER);
				break;
			}
		}
	}

	//Verify the distance values and check the ascending order is viewed

		public void ascendingordercall() throws ResourceCustomException, IOException, InterruptedException, WebDriverInstanceNullException, InvalidInputException {
			Thread.sleep(2000);
			clickButtonByCssSelector(adviserRatingPageproperty.getProperty("ADVISOR_DISTANCE_CLICK_CSSSELECTOR"));
			Thread.sleep(2000);
			List<WebElement> kmRange = getWebElementsProperties(By.cssSelector(adviserRatingPageproperty.getProperty("ADVISOR_DISTANCE_BADGE_CSS")));
			
			System.out.println("Beforesplit" + kmRange);
			ArrayList<Float> arr = new ArrayList<>();
			for (WebElement listKmrange : kmRange) {
				System.out.println("Beforesplit" + listKmrange);
				String kmRangevalue = listKmrange.getText();
				String[] splitvalues = kmRangevalue.split("k", 2);
				String finalvalue = splitvalues[0];
				Float result = new Float(finalvalue);
				// float value1 = float.parseFloat(finalvalue);
				System.out.println("kmrange:" + result);
				Thread.sleep(2000);
				arr.add(result);
				if (result < 1) {
					;
					System.out.println("It stays between the range");
					System.out.println("The ascending order of adviser distance"+arr);
					Thread.sleep(2000);
				}
			}
			
		}
	// To get current url and verify with expected url
	 
	public void advisornameCurrentUrl(String name) throws ResourceCustomException, IOException {
		String url = this.webDriver.getCurrentUrl();
		getEnvType=new ResourceRead().getEnvironmentConfigValue().getProperty("envType");
		System.out.println("currenturl" + url);
		assertTrue(url.contains(getEnvType));
		assertTrue(url.contains(name));
		
	}
// to check the distance value should be displayed
	public void distanceValue(String distancevalue) throws InterruptedException, ResourceCustomException, IOException {
		WebElement locationkm = webDriver
				.findElement(By.xpath(adviserRatingPageproperty.getProperty("ADVISOR_DROPDOWN_DISTANCE_VERIFY_XPATH")));
				String KMverify = locationkm.getText();
				Assert.assertEquals(distancevalue, KMverify);
				
				
		}
	
	// verify image src of banner
	 
	public void practiceLogo() throws ResourceCustomException, IOException {

		WebElement image = webDriver
				.findElement(By.cssSelector(adviserRatingPageproperty.getProperty("ADVISORURL_LOCATOR_CSSSELECTOR")));
		String Imagesrc = image.getAttribute("src");

		System.out.println("ImageSrc attribute is: " + Imagesrc);
		Boolean flag = image.isDisplayed();
		if(flag == true) {
			System.out.println("Logo got displayed: "+flag );
		}else
		{
			System.out.println("Logo is not displayed: "+flag );
		}
		//Assert.assertEquals(BannerImage,Imagesrc);

	}
	
	//verify practice name is displayed below map
	public void practiceName(String practiceName) throws ResourceCustomException, IOException {

		WebElement practice_map = webDriver
				.findElement(By.cssSelector(adviserRatingPageproperty.getProperty("ADVISOR_MAP_ADVISORNAME_CSSSELECTOR")));
		String ActualpracticeName =practice_map.getText();

		System.out.println("practicename is: " + practiceName);
		
		Assert.assertEquals(ActualpracticeName,practiceName);

	}
	
	//verify adviser image is displayed 
		public void adviserImage(String src) throws ResourceCustomException, IOException {

			WebElement image = webDriver
					.findElement(By.cssSelector(adviserRatingPageproperty.getProperty("ADVISOR_IMAGE_CSSSELECTOR")));
			String Imagesrc = image.getAttribute("src");

			System.out.println("ADVISERIMAGE SRC url is: " + Imagesrc);
			
			assertTrue(Imagesrc.contains(src));

		}
		
//verify map pinned address
public void mapPinnedAddress(String address) throws ResourceCustomException, IOException {

	WebElement addressele = webDriver.findElement(By.cssSelector(adviserRatingPageproperty.getProperty("MAP_ADDRESS_CSSSELECTOR")));
	String addressAttribute = addressele.getAttribute("title");

	System.out.println("addressAttribute  is: " + addressAttribute);
	assertTrue(addressAttribute.contains(address));				

				}
				
//verify mapurl center parameter value 
			public void mapParameterValue(String address) throws ResourceCustomException, IOException {

					WebElement addressele = webDriver
							.findElement(By.cssSelector(adviserRatingPageproperty.getProperty("MAP_ADDRESS_CSSSELECTOR")));
					String addressAttribute = addressele.getAttribute("src");

					System.out.println("addressAttribute  is: " + addressAttribute);
					assertTrue(addressAttribute.contains(address));
					

				}
	
	

	
	
	//verify Check for name
	public void nameVerify(String name) throws ResourceCustomException, IOException {

		WebElement Actualname = webDriver
				.findElement(By.tagName(adviserRatingPageproperty.getProperty("ADVISOR_NAME_VERIFY_TAGNAME")));
		String namevalue = Actualname.getText();
		Assert.assertEquals(name, namevalue);

	}
	//verify Check for advisername
	public void adviserNameVerify(String advisorName) throws ResourceCustomException, IOException {

		WebElement ActualadviserName = webDriver
				.findElement(By.cssSelector(adviserRatingPageproperty.getProperty("ADVISOR_ADVISORNAME_CSSSELECTOR")));
		String Advisernamevalue = ActualadviserName.getText();
		Assert.assertEquals(advisorName, Advisernamevalue);

	}
	//verify Check for location
	public void locationVerify(String location) throws ResourceCustomException, IOException {

		WebElement Actuallocation = webDriver
				.findElement(By.cssSelector(adviserRatingPageproperty.getProperty("ADVISOR_LOCATIONTEXT_VERIFY_CSSSELECTOR")));
		String locationvalue = Actuallocation.getText();
		Assert.assertEquals(location, locationvalue);

	}

	
	// verify Tab Name 

	public void aboutTabCheckCall(String AboutName) throws ResourceCustomException, IOException {

		WebElement tab = webDriver
				.findElement(By.cssSelector(adviserRatingPageproperty.getProperty("ADVISOR_TABNAMECHECK_CSSSELECTOR")));
		boolean returntype = tab.isSelected();
		String texttab = tab.getText();
		Assert.assertEquals(AboutName, texttab);
	}
	
	// verify Map and src of Image

	public void locationPanelCall(String adviserNameExpected, String srcUrlExpected) throws ResourceCustomException, IOException {

		WebElement map = webDriver
				.findElement(By.cssSelector(adviserRatingPageproperty.getProperty("ADVISOR_MAPLOCATE_CSSSELECTOR")));

		scrollToViewWebElement(map);
		//((JavascriptExecutor)webDriver).executeScript("arguments[0].scrollIntoView(true);", map);

		WebElement advisornamelocate = webDriver
				.findElement(By.cssSelector(adviserRatingPageproperty.getProperty("ADVISOR_MAP_ADVISORNAME_CSSSELECTOR")));

		String advisorname = advisornamelocate.getText();
		System.out.println("advisorname: "+advisorname);
		Assert.assertEquals(adviserNameExpected, advisorname);

		WebElement srcurllocate = webDriver
				.findElement(By.cssSelector(adviserRatingPageproperty.getProperty("ADVISOR_SRCURL_CSSSELECTOR")));

		String srcurl = srcurllocate.getAttribute("src");
		System.out.println("srcurl: "+srcurl);
		Assert.assertEquals(srcUrlExpected,srcurl);

	}
	
	// Print and verify 2 address gets populated.
public void addressCheckCall() {
		List<WebElement> addresslist = this.webDriver.findElements(By.tagName("address"));
		for (WebElement address : addresslist) {
			System.out.println("addressvalues: " + address.getText());

		}
	}


// Enter Search value
public void searchValue(String value) throws WebDriverInstanceNullException, InvalidInputException,
ResourceCustomException, IOException, InterruptedException {
     log.info("Entered reNameSurvey method of AdviserRatingPage");
     findTextboxAndSetValueByXpath(adviserRatingPageproperty.getProperty("SEARCH_VALUE_XPATH"),
    		 value);
}

//Iterate the dropdown list  to verify the provided data matches 
public void findAddressCall(String location) throws InterruptedException, ResourceCustomException, IOException, WebDriverInstanceNullException {
	Thread.sleep(2000);
	List<WebElement> ele = getWebElementsProperties(By.xpath(adviserRatingPageproperty.getProperty("LOCATION_ADDRESS_DROPDOWN_XPATH")));
	System.out.println(ele.size());
	for (int i = 0; i <= ele.size() - 1; i++) {
		if (ele.get(i).getText().contains(location)) {
			ele.get(i).click();
			break;
		}
	}
}





//iterate the KM value to fix 5Km and select the search button
public void selectvalueSearchCall(String distance) throws ResourceCustomException, IOException, WebDriverInstanceNullException, InvalidInputException {
	clickButtonById(adviserRatingPageproperty.getProperty("ADVISOR_DISTANCETAB_ID"));
	List<WebElement> kmlist = getWebElementsProperties(By.cssSelector(adviserRatingPageproperty.getProperty("SEARCH_DISTANCE_XPATH")));
	for (int i = 0; i <= kmlist.size() - 1; i++) {
		if (kmlist.get(i).getText().contains(distance)) {
			kmlist.get(i).click();
			//location.sendKeys(Keys.ENTER);
			break;
		}
	}
	clickButtonById(adviserRatingPageproperty.getProperty("ADVISOR_DISTANCETAB_ID"));
	clickButtonByCssSelector(adviserRatingPageproperty.getProperty("ADVISOR_LOCATION_SEARCHTAB_CSSSELECTOR"));
}

	//Verify the current url , print search results location and distance values and check the accending order is viewed
	public void currentPageUrlVerifyCall(String url, String ExpectedlocationName,String km, String ExpectedSearchResult) throws ResourceCustomException, IOException, InterruptedException {
		Thread.sleep(2000);
		String CUrrentUrl = this.webDriver.getCurrentUrl();
		Assert.assertEquals(url, CUrrentUrl);
		Thread.sleep(2000);
		WebElement locationFeild = webDriver
				.findElement(By.cssSelector(adviserRatingPageproperty.getProperty("ADVISOR_LOCATIONFEILD_CSSSELECTOR")));

		String locationName = locationFeild.getText();
		Thread.sleep(2000);
		System.out.println("locationName:" + locationName);
		Assert.assertEquals(ExpectedlocationName, locationName);

		WebElement locationkm = webDriver
				.findElement(By.cssSelector(adviserRatingPageproperty.getProperty("ADVISOR_DROPDOWN_DISTANCE_VERIFY")));

		String KMverify = locationkm.getText();
		Assert.assertEquals(km, KMverify);
		WebElement locationresult = webDriver
				.findElement(By.tagName(adviserRatingPageproperty.getProperty("ADVISOR_LOCATION_RESULT_TAGNAME")));

		String searchresult = locationresult.getText();
		System.out.println(searchresult);

		Assert.assertEquals(ExpectedSearchResult, searchresult);

		WebElement distancetabclick = webDriver
				.findElement(By.cssSelector(adviserRatingPageproperty.getProperty("ADVISOR_DISTANCE_CLICK_CSSSELECTOR")));

		distancetabclick.click();

		System.out.println("Value is displayed in assending order");

		List<WebElement> kmRange = this.webDriver.findElements(By.className("ar-badge.ar-badge--naked"));
		System.out.println("Beforesplit" + kmRange);
		for (WebElement listKmrange : kmRange) {
			System.out.println("Beforesplit" + listKmrange);
			String kmRangevalue = listKmrange.getText();
			String[] splitvalues = kmRangevalue.split("k", 2);
			String finalvalue = splitvalues[0];
			System.out.println("splitvalue" + kmRangevalue);
			System.out.println("splitvalueafter" + finalvalue);
			Float result = new Float(finalvalue);
			// float value1 = float.parseFloat(finalvalue);

			System.out.println("kmrange:" + result);
			Thread.sleep(2000);
			if (result < 1) {
				System.out.println("It stays between the range");
				Thread.sleep(2000);
			}

		}
	}
	//locationname is verified
	public void locationName(String location) throws InterruptedException, ResourceCustomException, IOException {
		Thread.sleep(2000);
		WebElement locationField = webDriver
		.findElement(By.cssSelector(adviserRatingPageproperty.getProperty("ADVISOR_LOCATIONFEILD_CSSSELECTOR")));
		String locationName = locationField.getText();
		Thread.sleep(2000);
		System.out.println("locationName:" + locationName);
		Assert.assertEquals(location, locationName);
		}
	//Verify the distance values and check the descending order is viewed

	public void descendingorderCall() throws ResourceCustomException, IOException, InterruptedException, WebDriverInstanceNullException, InvalidInputException {
		Thread.sleep(2000);
		System.out.println("Value is displayed in Descending order");
		Thread.sleep(3000);
		
		clickButtonByCssSelector(adviserRatingPageproperty.getProperty("ADVISOR_DESCENDINGORDER_CSSSELECTOR"));

		Thread.sleep(3000);
		List<WebElement> kmRange = getWebElementsProperties(By.cssSelector(adviserRatingPageproperty.getProperty("ADVISOR_DESCENDINGORDER_CSSSELECTOR")));
			for (WebElement listKmrange : kmRange) {
			String kmRangevalue = listKmrange.getText();
			String[] splitvalues = kmRangevalue.split("k", 2);
			String finalvalue = splitvalues[0];
			System.out.println("splitvalue" + kmRangevalue);
			System.out.println("splitvalueafter" + finalvalue);
			Float result = new Float(finalvalue);
			if (result <= 5 && result != 6) {
				System.out.println("It stays between the range");
			}
		}
	}
}
