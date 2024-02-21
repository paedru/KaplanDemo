package main;

import java.io.File;
import java.util.Map;
import java.util.Vector;

import com.relevantcodes.extentreports.ExtentTest;

import supportFiles.ExtentManager;
import supportFiles.ExtentTestManager;
import supportFiles.GlobalConstants;
import supportFiles.GlobalVariables;
import supportFiles.HelperMethods;
import supportFiles.TestConfigurations;


public class Initiator {	
	public static String runenginefile = "";
	public static final String projectDirectoryPath=System.getProperty("user.dir");
	public static String excelpath = "";
	public static void main(String[] args) {
		
		try{
		/*String runenginefile = System.getProperty("runenginename")+".xlsx";
		
		System.out.println("RUNENGINE FILE NAME IS: "+runenginefile);
		
		if(runenginefile.contains("null")){			
			runenginefile = GlobalConstants.defaultrunenginename;			
		}
		excelpath = GlobalConstants.projectDirectoryPath+"/"+runenginefile;*/		
		//excelpath = HelperMethods.getRunEngineFileName();	
			
			
			//Create Extent Report
			
		GlobalConstants.currentReportFolderName="Report_"+GlobalVariables.getTimestamp();
		GlobalConstants.currentReportFolderPath= GlobalConstants.exec_report_path+GlobalConstants.currentReportFolderName;		
		new File(GlobalConstants.currentReportFolderPath).mkdir();		
		GlobalConstants.executionReportName=GlobalConstants.currentReportFolderPath + "/REPORT.html";
		GlobalConstants.browser = "CHROME";
		GlobalConstants.operatingSystem = "WINDOWS";
		GlobalConstants.extent=ExtentManager.getReporter(GlobalConstants.executionReportName);
		
		//HarcodeTestNGFileNow
		HelperMethods.selectSuiteType();		
		HelperMethods.createTestNGFile(GlobalConstants.testng_xml_path);
//		
		
		System.out.println("hello");
		System.out.println("###The URL: " + GlobalConstants.URL);
		System.out.println("###The BROWSER: " + GlobalConstants.browser);	
		
		///#### Delete This####
		
		for (Map.Entry<String, String> entry : GlobalConstants.testToDescMap.entrySet()) {
		    String key = entry.getKey().toString();
		    String value = entry.getValue();
		    System.out.println("***key, " + key + " value " + value);
		}
		
		
		
		///####
		
		
		//Execute TestNG File
		HelperMethods.runTestNG(GlobalConstants.testng_xml_filename);
	
		
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("###***Exception in Initiator***###: " + e.toString());
		}finally{	
			
			
			
			//TestConfigurations.stopTestReport();
			ExtentManager.getReporter().flush();
			ExtentManager.getReporter().close();
		}
		
	}	
		
}