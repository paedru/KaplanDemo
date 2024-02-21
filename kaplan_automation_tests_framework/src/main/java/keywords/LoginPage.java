package keywords;


import objectRepository.Locator.Login;
import objectRepository.Locator.Programs;

import org.openqa.selenium.By;

import supportFiles.Assert;
import supportFiles.GlobalConstants;
import supportFiles.GlobalVariables;
import supportFiles.LOG;
import supportFiles.SeleniumUtils;
import supportFiles.TestConfigurations;
import supportFiles.TestData;


public class LoginPage {
	
	
	public static void login(){
		try{
			System.out.println("**************System Property******" +System.getProperty("user.dir"));
			System.out.println("**************System Property******" +System.getProperty("user.home"));
			System.out.println("**************System Property******" +System.getProperty("os.name"));
			TestConfigurations testConfig= GlobalVariables.getConfigMap().get(Thread.currentThread().getName());
			
			supportFiles.Assert.assertEquals(testConfig.getDriver().getTitle(), "Google");
			
	}catch(Exception e){
		     LOG.report_FAIL("Login into Google", e);	
		}
	}
	
	}
