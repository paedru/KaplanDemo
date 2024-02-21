package testSuites;

import java.util.ArrayList;


import keywords.GenericKeywords;
import keywords.LoginPage;
import objectRepository.Locator;
import objectRepository.Locator.PublicUserInterface;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import supportFiles.GlobalConstants;
import supportFiles.GlobalVariables;
import supportFiles.LOG;
import supportFiles.SeleniumUtils;
import supportFiles.TestConfigurations;

public class Smoke {
	static String programName;


	@Test(groups = { "Smoke" }, dependsOnMethods = { "toCheckLogin2" })
	public void toCheckLogin1() {
		try {
			GlobalVariables.setTestConfiguration(new Object() {
			}.getClass().getEnclosingMethod().getName(), this.getClass().getSimpleName());
			GenericKeywords.startApplication();
			// Log into the application
			LoginPage.login();
			LOG.report_INFO("Check payment functionalites", "Check payment functionalities completed");

		} catch (Exception e) {
			LOG.report_FAIL("Check Test Case 1 Failure",e);
		} finally {
			GenericKeywords.stopApplication();
			TestConfigurations.stopTestReport();
		}

	}

	
	@Test(groups = { "Smoke" })
	public void toCheckLogin2() {
		try {
			System.out.println("TC 2");
			GlobalVariables.setTestConfiguration(new Object() {
			}.getClass().getEnclosingMethod().getName(), this.getClass().getSimpleName());
			GenericKeywords.startApplication(); // Log into the application
			LoginPage.login();
			LOG.report_INFO("Smoke Test Completion", "Smoke Test Completed");
		} catch (Exception e) {
			LOG.report_FAIL("Check Test Case 2 Failure",e);
		} finally {
			GenericKeywords.stopApplication();
			TestConfigurations.stopTestReport();
		}

	}

	@Test(groups = { "Smoke" })
	public void toCheckLogin3() {
		try {
			System.out.println("TC 3");
			GlobalVariables.setTestConfiguration(new Object() {
			}.getClass().getEnclosingMethod().getName(), this.getClass().getSimpleName());
			GenericKeywords.startApplication();
			// Log into the application
			LoginPage.login();
		
			LOG.report_INFO("Smoke Test Completion", "Smoke Test Completed");
		} catch (Exception e) {
			LOG.report_FAIL("Check Test Case 3 Failure",e);
		} finally {
			GenericKeywords.stopApplication();
			TestConfigurations.stopTestReport();
		}
													}
        
	@AfterClass
	public void suiteTearDown() {

		GenericKeywords.generateTestSuiteReport(this.getClass().getSimpleName());
	}

}
