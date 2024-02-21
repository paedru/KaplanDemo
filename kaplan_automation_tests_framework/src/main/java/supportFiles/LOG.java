package supportFiles;

import keywords.GenericKeywords;

import com.relevantcodes.extentreports.LogStatus;

public class LOG {
	
	
	public static void report_PASS(String stepName, String detailMessage){		
		TestConfigurations testConfig= GlobalVariables.getConfigMap().get(Thread.currentThread().getName());
		
		
			testConfig.getReporter().log(LogStatus.PASS, stepName ,detailMessage);
			if(GlobalConstants.flagTakeScreenShotForPASS){
				
				String screenShot=GenericKeywords.captureCurrentScreenShot();
				String path=System.getProperty("user.dir")+"/ReportLogs/"+GlobalConstants.currentReportFolderName+"/"+screenShot;			
				testConfig.getReporter().log(LogStatus.INFO, "Snapshot Below: "+testConfig.getReporter().addScreenCapture(path));
				
			}
	}
	
	public static void report_FAIL(String stepName, String errorMessage){
		TestConfigurations testConfig= GlobalVariables.getConfigMap().get(Thread.currentThread().getName());
		
	
		
		testConfig.getReporter().log(LogStatus.FAIL, stepName ,errorMessage);
		if(GlobalConstants.flagTakeScreenShotForFAIL){
			
			String screenShot=GenericKeywords.captureCurrentScreenShot();
			String path=System.getProperty("user.dir")+"/ReportLogs/"+GlobalConstants.currentReportFolderName+"/"+screenShot;
			
			testConfig.getReporter().log(LogStatus.INFO, "Snapshot Below: "+testConfig.getReporter().addScreenCapture(path));			
		}
		
		GenericKeywords.stopApplicationAfterErrorOrFailure("Test Stopped Forcefully Due to Test Failure");
		
	}
	
	public static void reportFailAndContinue(String stepName, String errorMessage){
		TestConfigurations testConfig= GlobalVariables.getConfigMap().get(Thread.currentThread().getName());
		
	
		
		testConfig.getReporter().log(LogStatus.FAIL, stepName ,errorMessage);
		if(GlobalConstants.flagTakeScreenShotForFAIL){
			
			String screenShot=GenericKeywords.captureCurrentScreenShot();
			String path=System.getProperty("user.dir")+"/ReportLogs/"+GlobalConstants.currentReportFolderName+"/"+screenShot;
			
			testConfig.getReporter().log(LogStatus.INFO, "Snapshot Below: "+testConfig.getReporter().addScreenCapture(path));			
		}
		
		
		
	}
	public static void report_FAIL(String stepName, Throwable e){
		TestConfigurations testConfig= GlobalVariables.getConfigMap().get(Thread.currentThread().getName());
	
		
		testConfig.getReporter().log(LogStatus.FAIL, stepName ,e);
		if(GlobalConstants.flagTakeScreenShotForFAIL){
			String screenShot=GenericKeywords.captureCurrentScreenShot();
			String path=System.getProperty("user.dir")+"/ReportLogs/"+GlobalConstants.currentReportFolderName+"/"+screenShot;			
			testConfig.getReporter().log(LogStatus.INFO, "Snapshot Below: "+testConfig.getReporter().addScreenCapture(path));			
		}
		GenericKeywords.stopApplicationAfterErrorOrFailure("Test Stopped Forcefully Due to Test Failure");
		
	}
	
	public static void reportFailAndContinue(String stepName, Throwable e){
		TestConfigurations testConfig= GlobalVariables.getConfigMap().get(Thread.currentThread().getName());
	
		
		testConfig.getReporter().log(LogStatus.FAIL, stepName ,e);
		if(GlobalConstants.flagTakeScreenShotForFAIL){
			String screenShot=GenericKeywords.captureCurrentScreenShot();
			String path=System.getProperty("user.dir")+"/ReportLogs/"+GlobalConstants.currentReportFolderName+"/"+screenShot;			
			testConfig.getReporter().log(LogStatus.INFO, "Snapshot Below: "+testConfig.getReporter().addScreenCapture(path));			
		}
		//GenericKeywords.stopApplicationAfterErrorOrFailure("Application Stopped Forcefully Due to Test Failure");
		
	}
	
	public static void report_INFO(String stepName, String detailMessage){
		TestConfigurations testConfig= GlobalVariables.getConfigMap().get(Thread.currentThread().getName());
		
		testConfig.getReporter().log(LogStatus.INFO, stepName ,detailMessage);

		if(GlobalConstants.flagTakeScreenShotForINFO){
			String screenShot=GenericKeywords.captureCurrentScreenShot();
			String path=System.getProperty("user.dir")+"/ReportLogs/"+GlobalConstants.currentReportFolderName+"/"+screenShot;			
			testConfig.getReporter().log(LogStatus.INFO, "Snapshot Below: "+testConfig.getReporter().addScreenCapture(path));			
		}
		
	}
	
	public static void report_WARNING(String stepName, String warningMessage){
		TestConfigurations testConfig= GlobalVariables.getConfigMap().get(Thread.currentThread().getName());
		
	
		testConfig.getReporter().log(LogStatus.WARNING, stepName ,warningMessage);
		if(GlobalConstants.flagTakeScreenShotForWARNING){
			String screenShot=GenericKeywords.captureCurrentScreenShot();
			String path=System.getProperty("user.dir")+"/ReportLogs/"+GlobalConstants.currentReportFolderName+"/"+screenShot;			
			testConfig.getReporter().log(LogStatus.INFO, "Snapshot Below: "+testConfig.getReporter().addScreenCapture(path));			
		}
		
	}
	
	public static void report_ERROR(String stepName, Throwable e){
		TestConfigurations testConfig= GlobalVariables.getConfigMap().get(Thread.currentThread().getName());
		
	
		testConfig.getReporter().log(LogStatus.ERROR, stepName ,e);
		if(GlobalConstants.flagTakeScreenShotForERROR){
			String screenShot=GenericKeywords.captureCurrentScreenShot();
			String path=System.getProperty("user.dir")+"/ReportLogs/"+GlobalConstants.currentReportFolderName+"/"+screenShot;			
			testConfig.getReporter().log(LogStatus.INFO, "Snapshot Below: "+testConfig.getReporter().addScreenCapture(path));			
		}
		
		GenericKeywords.stopApplicationAfterErrorOrFailure("Test Stopped Forcefully Due to Error");
		
	}
	public static void report_ERROR(String stepName, String errorMessage){
		TestConfigurations testConfig= GlobalVariables.getConfigMap().get(Thread.currentThread().getName());
	
		testConfig.getReporter().log(LogStatus.ERROR, stepName ,errorMessage);
		if(GlobalConstants.flagTakeScreenShotForERROR){
			String screenShot=GenericKeywords.captureCurrentScreenShot();
			String path=System.getProperty("user.dir")+"/ReportLogs/"+GlobalConstants.currentReportFolderName+"/"+screenShot;		
			testConfig.getReporter().log(LogStatus.INFO, "Snapshot Below: "+testConfig.getReporter().addScreenCapture(path));			
		}
		
		GenericKeywords.stopApplicationAfterErrorOrFailure("Test Stopped Forcefully Due to Error");
	}
		

}
