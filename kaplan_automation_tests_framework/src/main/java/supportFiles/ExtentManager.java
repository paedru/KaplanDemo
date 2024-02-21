package supportFiles;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class ExtentManager {
    private static ExtentReports extent=null;
	public static Map<Long, String> threadToExtentTestMap = new HashMap<Long, String>();
	public static Map<String, ExtentTest> nameToTestMap = new HashMap<String, ExtentTest>();
    
    public synchronized static ExtentReports getReporter(String filePath) {
        if (extent == null) {        	
            extent = new ExtentReports(filePath, false, DisplayOrder.OLDEST_FIRST);
            extent.loadConfig(new File(GlobalConstants.extentConfigFilePath));
            System.out.println("***Extent Report Created: " +filePath );
        }
        
        return extent;
    }
    
    public synchronized static ExtentReports getReporter() {
        return extent;
    }
}