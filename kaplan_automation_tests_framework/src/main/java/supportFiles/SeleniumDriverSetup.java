package supportFiles;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

//import main.Initiator;

import java.util.logging.Level;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SeleniumDriverSetup extends Thread {   

	
	public  static WebDriver getDriver(String strBrowser) throws Exception
	{
		WebDriver driver = null;
		ExcelUtils.setExcelFile(HelperMethods.getRunEngineFileName());
		String OS = GlobalConstants.operatingSystem;
		
		if(OS.equalsIgnoreCase("Windows")){
			if(strBrowser.trim().toUpperCase().equals("IE"))
			{
				//Create IE Driver with required capabilities
				
			}else if(strBrowser.trim().toUpperCase().equals("FIREFOX"))
			{
				//Create FireFox Driver with required capabilities
			}
			
			else if(strBrowser.trim().toUpperCase().equals("CHROME"))
			{
				System.setProperty("webdriver.chrome.driver",GlobalConstants.librariesPath +"chromedriver.exe"); 
				ChromeOptions options = new ChromeOptions();
			    Map<String, Object> prefs = new HashMap<String, Object>();
			    prefs.put("profile.default_content_settings.popups", 0);
			    prefs.put("plugins.always_open_pdf_externally", true);
			    options.setExperimentalOption("prefs", prefs);
			    driver = new ChromeDriver(options);
			    //driver = new ChromeDriver();
			    driver.manage().window().maximize();
			    System.out.println("***CHROME DRIVER HAS BEEN CREATED");
				
			}
			
		}
		
		else if(OS.equalsIgnoreCase("Linux")) {
			if(strBrowser.trim().toUpperCase().equals("IE"))
			{
				//System.out.println("IE driver created");
				System.setProperty("webdriver.ie.driver", GlobalConstants.librariesPath +"IEDriverServer.exe");

				//DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
				//ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
				//driver= new InternetExplorerDriver(ieCapabilities);
				
				DesiredCapabilities capabilities = new DesiredCapabilities(); 
				//capabilities.internetExplorer();
				capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
				capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING,true);
				capabilities.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true); 
				driver = new InternetExplorerDriver(capabilities); 
				driver.manage().window().maximize();
				
			}else if(strBrowser.trim().toUpperCase().equals("FIREFOX"))
			{
				//create fireFoxdriver
				
			}
			
			else if(strBrowser.trim().toUpperCase().equals("CHROME"))
			{
				if(GlobalConstants.environmenttype.equals("NonDocker")){
					//Code For Non Docker Starts Here
					/*System.setProperty("webdriver.chrome.driver",GlobalConstants.librariesPath +"chromedriver_linux64");
					DesiredCapabilities caps = DesiredCapabilities.chrome();
			        LoggingPreferences logPrefs = new LoggingPreferences();
			        logPrefs.enable(LogType.BROWSER, Level.ALL);
			        System.setProperty("webdriver.chrome.logfile", GlobalConstants.exec_report_path+"chromedriver.log");
			        caps.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
			        driver = new ChromeDriver(caps);*/
					
					 ChromeOptions options = new ChromeOptions();
					    Map<String, Object> prefs = new HashMap<String, Object>();
					    prefs.put("profile.default_content_settings.popups", 0);
					    prefs.put("plugins.always_open_pdf_externally", true);
					    options.setExperimentalOption("prefs", prefs);

					    
					    driver = new ChromeDriver(options);
					    
					    
					    //driver = new ChromeDriver();
					    
			        //Code for Non Docker ends here
				}
				if(GlobalConstants.environmenttype.equals("Docker")){
					//Below statement is for execution of selenium tests on docker container hosted on my local laptop
					//add confgs and keys to Docker and run on Docker 
					driver = new RemoteWebDriver(new URL("http://172.17.0.1:4444/wd/hub"), new DesiredCapabilities() );
				}
				
				driver.manage().window().maximize();
				System.out.println("***CHROME DRIVER HAS BEEN CREATED");
				
			}
		}	
		
		
			
				driver.manage().window().maximize();
				System.out.println("***CHROME DRIVER HAS BEEN CREATED");
				return driver;
				
			}
		
		
		
		
	}
	
	

	

	
	
	/*
	
	public  static RemoteWebDriver getDriver(DesiredCapabilities capabilities, String hub)
	throws WebDriverException
	{
		RemoteWebDriver driver = null;
		try {
				driver = new RemoteWebDriver(new URL("http://"+hub+":4444/wd/hub"), capabilities);
				//driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
			} catch (MalformedURLException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return driver;
	}
	
	public  static DesiredCapabilities getCapability(String Browser)
	{
		DesiredCapabilities capability= new DesiredCapabilities();
		if(Browser.trim().toUpperCase().equals("FIREFOX"))
		{
			FirefoxProfile firefoxprofile = new FirefoxProfile();
			firefoxprofile.setAssumeUntrustedCertificateIssuer(false);
			capability.setCapability(FirefoxDriver.PROFILE, firefoxprofile);
			capability.setBrowserName("firefox");
			capability.setJavascriptEnabled(true);
		}else if(Browser.trim().toUpperCase().equals("IE"))
		{
			capability.setBrowserName("internet explorer");
			capability.setPlatform(Platform.ANY);
			capability.setJavascriptEnabled(true);
		}else if(Browser.trim().toUpperCase().equals("CHROME"))
		{
			capability = capability.chrome();
			capability.setBrowserName("chrome");
			
		}
		
		return capability;
	}
	
*/




