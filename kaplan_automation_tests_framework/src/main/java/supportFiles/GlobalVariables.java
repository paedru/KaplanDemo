package supportFiles;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;

public class GlobalVariables
{

	public static Map<String ,TestConfigurations> configMap=new HashMap<String,TestConfigurations>() ;
	
	
	
	public static Map<String, TestConfigurations> getConfigMap() {
		return configMap;
	}
	
	public static void setConfigMap(String thread, TestConfigurations tc){
		
		configMap.put(thread, tc);
		
	}

	public static void setTestConfiguration(String threadName, String moduleName){
		
		try{
			
			Thread.currentThread().setName(threadName);
			WebDriver driver= SeleniumDriverSetup.getDriver(GlobalConstants.browser);	
			TestConfigurations testConfig=new TestConfigurations(Thread.currentThread().getName(), driver);
			GlobalVariables.setConfigMap(Thread.currentThread().getName(), testConfig);
			String description=GlobalConstants.testToDescMap.get(threadName);
			testConfig.setModuleName(moduleName);
			testConfig.startTestReport(description);
			
			
		}catch(Exception e){
			
			e.printStackTrace();
		}
	}
	
	public static String getTimestamp()
	{
		
		String timeStamp = new SimpleDateFormat("MM.dd.yyyy__hh.mm.ssa").format(new Date());
		
		return timeStamp;
	}


}
