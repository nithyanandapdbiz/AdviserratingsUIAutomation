package stepdefinition;

import java.util.ArrayList;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.TakesScreenshot;
import com.adviserratinguiautomation.base.BasePage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;

public class Hooks extends BasePage {

	private static final Logger LOG = LogManager.getLogger(Hooks.class);
	public static boolean passStatus = true;
	final static Logger log = Logger.getLogger(Hooks.class);
	String imageName;

	@Before
	public void testStart(Scenario scenario) {
		LOG.info("*****************************************************************************************");
		LOG.info("	Scenario: " + scenario.getName());
		scenarioName = scenario.getName();
		// get all the scenario tags from the scenario head.
		final ArrayList<String> scenarioTags = new ArrayList<>();
		// Get the name of all the scenario
		scenarioTags.addAll(scenario.getSourceTagNames());
		LOG.info("*****************************************************************************************");
	}

	@After
	public void AfterScenario(Scenario scenario) throws Exception {

		LOG.info("Entered Step Hooks AfterScenario method in StepHooks class");

		scenarioName = scenario.getName();

		if (scenario.isFailed()) {

			passStatus = false;
			TakesScreenshot ts = (TakesScreenshot) webDriver;
			byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
			scenario.attach(screenshot, "image/png", scenario.getName());

		}

		LOG.info("Exited  Step Hooks AfterScenario method in StepHooks class");
	}

}
