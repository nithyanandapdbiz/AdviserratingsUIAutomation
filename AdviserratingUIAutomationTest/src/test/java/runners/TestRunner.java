package runners;


import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import com.adviserratinguiautomation.base.BasePage;
import com.adviserratinguiautomation.customReport.CustomExtendReport;
import com.adviserratinguiautomation.customexceptions.ResourceCustomException;
import com.adviserratinguiautomation.logger.FrameworkLogger;
import com.adviserratinguiautomation.utilities.DeleteDirectory;
import com.adviserratinguiautomation.utilities.DirectoryCopier;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;
import stepdefinition.Hooks;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = { "pretty:../test-output/cucumber/cucumber.txt",
		"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:", "html:../test-output/cucumber/report",
		"json:../test-output/cucumber/cucumber.json",
		"com.adviserratinguiautomation.utilities.MyTestListener" }, features = {
				"src/test/resources/features" }, glue = { "stepdefinition" }
// ,dryRun = true
		, monochrome = true, snippets = SnippetType.CAMELCASE, tags = "@AdviserRating"
// ,publish = true
)
public class TestRunner extends BasePage {

	static String folderName;

	@BeforeClass
	public static void createTestResultFolder() throws ResourceCustomException, IOException {
		// Create the test folder
   	 folderName =	createParentFolder();

	}

	@AfterClass
	public static void writeExtentReport() throws IOException {

		
		// Copy extent reports to new path
		//CustomExtendReport.copyReport(folderName, Hooks.passStatus);
		
		Path sourcePath = FileSystems.getDefault().getPath(sourceResultFolder);
		Path destinationPath = FileSystems.getDefault().getPath(testRestulDateFolderPath);

		File sourceDir = new File(sourcePath.toString());
		File destinationDir = new File(destinationPath.toString());

		new DirectoryCopier().copyDirectory(sourceDir, destinationDir);
		
        // call deleteDirectory function to delete
        // subdirectory and files
       new DeleteDirectory().deleteDirectory(sourceDir);
 
        // delete main test output folder
       sourceDir.delete();

	}

}
