package keywords;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
//import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import supportFiles.ExtentTestManager;
import supportFiles.GlobalConstants;
import supportFiles.GlobalVariables;
import supportFiles.LOG;
import supportFiles.LocatorTypes;
import supportFiles.SeleniumDriverSetup;
import supportFiles.SeleniumUtils;
import supportFiles.TestConfigurations;

import com.google.common.base.Function;
import com.relevantcodes.extentreports.ExtentTest;


public class GenericKeywords extends SeleniumDriverSetup
{

	public static String parentWindowHandle;
	
	//
	public static   WebElement getElement(final By locator)
	{
		TestConfigurations testConfig= GlobalVariables.getConfigMap().get(Thread.currentThread().getName());
		WebElement webElement = null;
		
		try
		{
			Wait<WebDriver> wait = new FluentWait<WebDriver>(testConfig.getDriver())
					.withTimeout(GlobalConstants.explicitWaitTime, TimeUnit.SECONDS)
					.pollingEvery(GlobalConstants.pollingWaitTime, TimeUnit.SECONDS)
					.ignoring(NoSuchElementException.class);			
		
				webElement = wait.until(new Function<WebDriver, WebElement>() {
					public WebElement apply(WebDriver driver) {
						WebElement element =null;							
						element = driver.findElement(locator);
						return element;
					}
				});			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			LOG.report_ERROR("Element Not Found, Check Locator:" + locator , e);
		}
		

		return webElement;

	}
	
	 public static void generateTestSuiteReport(String module){
		  
			//TestConfigurations testConfig= GlobalVariables.getConfigMap().get(Thread.currentThread().getName());
				
				//String module= this.getClass().getSimpleName();
				
				//boolean flag=false;
				String moduleDesc= GlobalConstants.moduleToDescMap.get(module);		
				ExtentTest parent2 = ExtentTestManager.startTest(module,moduleDesc);
				Map<String, ExtentTest> reportMap= GlobalConstants.testReportMap;
				Map<String, Vector<String>> moduleToTest= GlobalConstants.moduleToTestsMap;
				
				System.out.println("###Executing Tear Down");
				
				for (Map.Entry<String, ExtentTest> entry : reportMap.entrySet()){
					
					Vector<String> vec= moduleToTest.get(module);		
					
					
					System.out.println("\n###Module:" +entry.getKey()+"\t ExtentTest:" + entry.getValue());
					
					
					if (vec.contains(entry.getKey()))
					{
						
						System.out.println("********************InSide Module:" +entry.getKey());
						ExtentTest test=entry.getValue();
						parent2.appendChild(test);	
						
					}
				}
				
				ExtentTestManager.getExtentReports().endTest(parent2);			  
		  }
	 
		 
	public static   List<WebElement> getElementCollection(final By locator)
	{
		TestConfigurations testConfig= GlobalVariables.getConfigMap().get(Thread.currentThread().getName());
		List<WebElement> webElements = null;
		
		try
		{
			Wait<WebDriver> wait = new FluentWait<WebDriver>(testConfig.getDriver())
					.withTimeout(GlobalConstants.explicitWaitTime, TimeUnit.SECONDS)
					.pollingEvery(GlobalConstants.pollingWaitTime, TimeUnit.SECONDS)
					.ignoring(NoSuchElementException.class);			
		
			webElements = wait.until(new Function<WebDriver, List<WebElement>>() {
					public List<WebElement> apply(WebDriver driver) {
						List<WebElement> element =null;							
						element = driver.findElements(locator);
						return element;
					}
				});			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			LOG.report_ERROR("Element Not Found, Check Locator:" + locator , e);
		}
		

		return webElements;

	}
	

  
	
	public  static   void startApplication()
	{
		
		 	
			TestConfigurations testConfig= GlobalVariables.getConfigMap().get(Thread.currentThread().getName());
			try
			{
				
										
				testConfig.getDriver().get(GlobalConstants.URL);			
					
				LOG.report_INFO("Launch Application", "Application "+ GlobalConstants.URL +" is launched");
				
				
				
			}catch(Exception e)
			{
				e.printStackTrace();
				LOG.report_ERROR("Launch Application", e);
				
			}


	}
		
	public static  void enterText(By locator, String input)
	{
		
		try
		{
			GenericKeywords.getElement(locator).sendKeys(input);
			Thread.sleep(2000);
			LOG.report_INFO("Enter Text", "Entered Text \""+ input+ "\"");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			LOG.report_ERROR("Enter Text", e);
			
		}

	}


	
	
	
	public static  void VerifyText(By locator,String input)
	{
		
	
		try
		{
			
			String text=GenericKeywords.getElement(locator).getText();
			
			if (text.equals(input)){
				
				LOG.report_PASS("Verify Element Text", "Element Text \""+input +"\" has matched");
			}
			else{
				
				LOG.report_FAIL("Verify Element Text", "Element Text Should have been \""+input +"\" but instead text \""+text+"\" has found");
			}
			
			
			
		}catch(Exception e)
		{
			e.printStackTrace();
			LOG.report_ERROR("Verify Element Text", e);
			

		}
		
	}


	
	public static  void clickElement(By locator)
	{
		

		try
		{
			GenericKeywords.getElement(locator).click();
			Thread.sleep(2000);
			LOG.report_INFO("Click Element", "Clicked Element Successfully");
			
		}catch(Exception e)
		{
			e.printStackTrace();
			LOG.report_ERROR("Click Element", e);
			
		}

	}
	
	
	public static void sleepSecs(int secs){
        try{
            Thread.sleep(secs * 1000);
        }catch (InterruptedException ex){}
    }
	
	
	
	
	public  static   void stopApplication()
	{
		
		
			TestConfigurations testConfig= GlobalVariables.getConfigMap().get(Thread.currentThread().getName());
			try
			{
			
				LOG.report_INFO("Close Application", "Application Closed");	
				
				testConfig.getDriver().quit();			
				//LOG.report_INFO("Close Application", "Application Closed");
				/*ExcelUtils.setExcelFile(GlobalConstants.excelpath);
				String os_Type = ExcelUtils.getCellData(1, GlobalConstants.wrksheet_suitetype_col_os, GlobalConstants.wrksheet_suitetype);
				if(os_Type.equalsIgnoreCase("Linux")){
				Runtime.getRuntime().exec("pkill chrome").destroy();
				}*/
				
				
			}catch(Exception e)
			{
				e.printStackTrace();
				LOG.report_ERROR("Error While Closing Application", e);				
			
			}finally{
				
				TestConfigurations.stopTestReport();
			}
	}
	
	public  static   void stopApplicationAfterErrorOrFailure(String massages)
	{
		
		
			TestConfigurations testConfig= GlobalVariables.getConfigMap().get(Thread.currentThread().getName());
			try
			{
			
				LOG.report_INFO("Test Aborted", massages);						
				testConfig.getDriver().quit();			
				//LOG.report_INFO("Close Application", "Application Closed");
				
				
			}catch(Exception e)
			{
				e.printStackTrace();
				LOG.report_ERROR("Error While Closing Application", e);				
			
			}finally{				
				
				TestConfigurations.stopTestReport();
				/*ExtentManager.getReporter().flush();
				ExtentManager.getReporter().close();*/
				//System.out.println("****************The test has been stopped");
				Assert.fail("The test has been stopped");
			}
	}
	
	public static String captureCurrentScreenShot(){
		
		TestConfigurations testConfig= GlobalVariables.getConfigMap().get(Thread.currentThread().getName());
		WebDriver driver= testConfig.getDriver();
		String titlename = driver.getTitle().replace(":", "_");
		System.out.println("TITLE NAME IS:" +titlename);
		String screenShotName= GlobalVariables.getConfigMap().get(Thread.currentThread().getName()) + titlename + GlobalVariables.getTimestamp() +".png";		
		String screenShotPath=GlobalConstants.currentReportFolderPath+"/"+ screenShotName;
		try{
			
			File scr = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scr, new File(screenShotPath));
			
			
		}catch(Exception e){
			
			e.printStackTrace();
			//LOG.report_ERROR("Error is Taking Screenshot", e);
			
			
		}
		return screenShotName;
	}
	
	
	
	/*Following are the Keyword copies and modified from Previous File SeleniumUtils.java * 
	 * 
	 * 
	 * 
	 */
	
	
	
	
	
	
	  
	
	
	
	  
	
		
}
