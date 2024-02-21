package supportFiles;



import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentTest;



public class TestConfigurations extends SeleniumDriverSetup {


	
	public String testConfigs = null;	
	public String thread= null;
	public ExtentTest report;	
	public WebDriver driver = null;
	public String moduleName=null;
	public Map<String,ExtentTest> testReportMap= new HashMap<String, ExtentTest>();
	
	
	
	public String getThread() {
		return thread;
	}

	public void setThread(String thread) {
		this.thread = thread;
	}

	
	
	public TestConfigurations (String thread, WebDriver driver){
		
		this.thread=thread;
		this.driver=driver;	
		
	}
	
	
	public void startTestReport(String testDescription){
		
		this.report= ExtentTestManager.startTest(this.thread,testDescription);
		GlobalConstants.testReportMap.put(this.thread, this.report);
		
	}
	
	public Map<String, ExtentTest> getTestReportMap(){
		
		return this.testReportMap;
	}
	

	public static void stopTestReport(){
		
		ExtentTestManager.endTest();
	}
	
	public ExtentTest getReporter(){
		
		return this.report;
	}
	
	public void setModuleName(String moduleName){
		
		this.moduleName=moduleName;
	}
	
	public String getModuleName(){
		
		return moduleName;
	}
	


	public  WebDriver getDriver() {
		return driver;
	}

}
