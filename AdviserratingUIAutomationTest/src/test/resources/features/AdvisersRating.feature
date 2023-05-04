@AdviserRating
Feature: Adviser Rating Search functionality validation


  Scenario: Check adviser's profile page displays correct practice and addresses
    Given I Navigate to "AdviserratingURL"
    When I Click on Adviser Tab
    And Enter "Brett Dillon" on the Search box
    And Iterate the dropdown and select "Brett Dillon"
    Then The current Page URL which should contain "Brett-Dillon" 
    And The Advisers profile image is displayed with src url contains "brett-andrew-dillon"
    And The name is "Brett Dillon"
    And The Advisor name is "Saige Financial Planning Pty Ltd" 
    And The location will be "Erina, NSW 2250"
    And About tab is "About Brett"
    When The google map is displayed with the pin to the address in "Erina, NSW 2250"
    Then The Map URL center parameter value should contains "center=-33.4386,151.3868"
    And The  practice name is displayed under the map is 'Saige Financial Planning Pty Ltd'
    And The practice logo is displayed
    And Address check is performed
    
    #Scenario: Search for adviser by Location - use enter key
    #Given I Navigate to "AdviserratingURL"
    #And Go to location Tab
    #When I Enter "Sydney" inside the Search box
    #And Find "Sydney, NSW 2000" in the address drop down search box and check it is highlighted
    #And Check "Sydney, NSW 2000" is populated into the location field
    #When click on Enter key
    #Then The current Page URL which should contain "Sydney-NSW-2000"
    #And location name should be "Sydney, NSW 2000"
    #And Distance should be displayed as "10km"
    #And check the view is ListView
    #And check ascendingorder of KMRange
    #
    #Scenario: Search by adviser name and check search drop down box displays correct practice and addresses
    #Given I Navigate to "AdviserratingURL"
    #When I Click on Adviser Tab
    #And Enter "Brett Dillon" on the Search box
    #Then Dropdown should be displayed with "Brett Dillon"
    #And Dropdown should be highlighted for the "Brett Dillon"
    #And Practice under the "Brett Dillon" should be "Saige Financial Planning Pty Ltd"
    #And Two locations should be listed next to the adviser as "Erina, NSW 2250" and "Sydney, NSW 2000"
    #When On clicking Enter key
    #Then The current Page URL which should contain "Brett-Dillon"
    #
    #Scenario: Search by location and distance
#	  Given I Navigate to "AdviserratingURL"
    #And Go to location Tab
    #When I Enter "800" inside the Search box
    #Then Find "Darwin, NT 0800" from the Address search dropdown and click on it
    #And select for "5km" from dropdown and click search
#		Then The current Page URL which should contain "Darwin-NT-0800"
    #And location name should be "Darwin, NT 0800"
    #And Distance should be displayed as "5km"
    #And check ascendingorder of KMRange
    #And check descendingorder of KMRange
    #And Logout the browser
    
