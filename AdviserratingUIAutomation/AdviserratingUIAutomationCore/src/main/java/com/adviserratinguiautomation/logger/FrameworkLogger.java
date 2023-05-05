package com.adviserratinguiautomation.logger;

import com.adviserratinguiautomation.customexceptions.ExceptionHandeler;
import com.adviserratinguiautomation.customexceptions.LogDirectoryCreationException;
import com.adviserratinguiautomation.customexceptions.ResourceCustomException;
import com.adviserratinguiautomation.resourceRead.ResourceRead;
import com.adviserratinguiautomation.utilities.DirectoryCopier;
import com.adviserratinguiautomation.utilities.FileUtilities;
import com.adviserratinguiautomation.utilities.GenericUtilities;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class FrameworkLogger {

	final static Logger log = Logger.getLogger(FrameworkLogger.class);
	public static final String LOG_FOLDER_PARENT_PATH = "LogFolderParentPath";
	public static String testRestulDateFolderPath = "";
	public static String cucumberReportPath = "";
	public static String extentReportFolderPath = "";
	public static String logFolderPath = "";
	public static String sourceResultFolder = "";

	public static void checkExtentReportFolder() throws ResourceCustomException, IOException {
		String extentsourceFolder = ResourceRead.getResourceValueFromXML().getProperty("ExtentReportSourceLocation");
		File extentFolderFile = new File(extentsourceFolder);
		if (extentFolderFile.exists()) {
			// Delete the folder
			extentFolderFile.delete();
		}
	}

	public static String createParentFolder() {

		log.info("Entered the createLogReport method in TestRunSeleniumFrameWork");
		String testFolderName = "";
		String testRestulDateFolderName = "";
		String extentReportFolderName = "";
		String logFolderName = "";
		String cucumberReportFolderName = "";

		String source, dest;

		try {

			String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

			File currentDirectory = new File(new File(".").getAbsolutePath());
			Path path = Paths.get(currentDirectory.toURI());
			Path root = path.getParent().getParent();

			File srcFile = new File(root.toFile(), "/test-output");
			sourceResultFolder = srcFile.toString();

			testFolderName = new ResourceRead().getEnvironmentConfigValue().getProperty("TestResultFolderName");
			testRestulDateFolderName = String
					.format(new ResourceRead().getEnvironmentConfigValue().getProperty("TestResultDateFoldername") + "_"
							+ dateName);

			Path testResultFolderParentPath = root.resolve(testFolderName);

			File testResultFolderFile = new File(testResultFolderParentPath.toString());
			if (!testResultFolderFile.exists()) {
				testResultFolderFile.mkdir();

				testRestulDateFolderPath = createTestRestulDateFolderName(testResultFolderParentPath.toFile(),
						testRestulDateFolderName);
			} else {
				testRestulDateFolderPath = createTestRestulDateFolderName(testResultFolderParentPath.toFile(),
						testRestulDateFolderName);

			}
			// Return the folder path
			log.info("Exited the createLogReport method in TestRunSeleniumFrameWork");

		} catch (ResourceCustomException ex) {
			new ExceptionHandeler().resourceExceptionHandeler(ex);
		} catch (IOException ex) {
			new ExceptionHandeler().genricExceptionHandeler(ex);
		} catch (LogDirectoryCreationException ex) {
			new ExceptionHandeler().genricExceptionHandeler(ex);
		}

		return testRestulDateFolderPath;

	}

	public static String createTestRestulDateFolderName(File testResultFolderParentPath,
			String testRestulDateFolderName)
			throws LogDirectoryCreationException, ResourceCustomException, IOException {
		log.info("Entered the createSubFolders method in TestRunSeleniumFrameWork");
		String TEST_REPORT = "TestReport";
		String testRestulDateFolderPath = "";

		if (testResultFolderParentPath.exists()) {
			List<String> subFolders = new ArrayList<>();
			Properties property = ResourceRead.getResourceValueFromXML();
			subFolders.add(property.getProperty(TEST_REPORT));
			for (String sub : subFolders) {
				File logChildFoldersFile = new File(testResultFolderParentPath, testRestulDateFolderName);
				logChildFoldersFile.mkdirs();
				testRestulDateFolderPath = logChildFoldersFile.toString();
			}
		} else {
			throw new LogDirectoryCreationException(testRestulDateFolderPath + "directory not found");
		}

		log.info("Exited the createSubFolders method in TestRunSeleniumFrameWork");
		return testRestulDateFolderPath;
	}
}
