package supportFiles;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

//import main.Initiator;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;



public class GlobalConstants {
	
	
	//*** SELENIUM RELATED PAREMATERS
	public static final int implicitWaitTime=10;
	public static final int explicitWaitTime=60;
	public static final int pollingWaitTime=5;	
	public static final boolean flagTakeScreenShotForPASS= false;
	public static final boolean flagTakeScreenShotForFAIL= true;
	public static final boolean flagTakeScreenShotForINFO= false;
	public static final boolean flagTakeScreenShotForERROR= true;
	public static final boolean flagTakeScreenShotForWARNING= false;
	
	
	//*** FRAMEWORK PARAMETERS
	String projectRootFolder = System.getProperty("projectRootFolder");
	public static final String projectDirectoryPath=System.getProperty("user.dir")+projectRootFolder; 
	public static String URL=null;
	public static String browser=null;
	public static ExtentReports extent;
	public static String operatingSystem=null;
	public static String environmenttype=null;
	
	//*** ALL THE RESOURCES PATHS ****	
	//public static final String excelpath = "./RunEngine.xlsx";
	//public static final String testng_xml_path = "./testit.xml";
	//public static final String exec_report_path="./ReportLogs/";
	
	//public static final String excelpath = projectDirectoryPath+"/RunEngine.xlsx";
	public static final String defaultrunenginename = "RunEngine.xlsx";
	//public static final String excelpath = projectDirectoryPath+"/"+Initiator.runenginefile;
	public static final String testng_xml_path = projectDirectoryPath+"/testit.xml";
	public static final String exec_report_path=projectDirectoryPath+"/reportLogs/";
	public static final String librariesPath=projectDirectoryPath+"/libraries/";
	public static final String MeasureFilespath = projectDirectoryPath+"/Files/MeasureFile.txt";
	
	public static String executionReportName;
	public static String currentReportFolderPath;
	public static String currentReportFolderName;
	
	
	//*** DataSheets
	
	public static final String dataSheetsPath=projectDirectoryPath+"/dataSheets/";
	//public static final String logindataexcelpath = projectDirectoryPath+"/dataSheets/LoginData.xlsx";
	//public static final String logindatasheetname = "Login";
	
	
	//*** CONTANSTS FOR RUN ENGINE ***
	
	public static final String testng_xml_filename = "testit.xml";
	public static final String sheet ="LoginTC";
	public static final String sheet_name_modules = "Modules";
	public static final String sheet_name_login_tc = "LoginTC";
	public static final String module_id_login = "Login";
	public static final String module_id_onsite = "Onsite";
	public static final String wrksheet_testcasesnames = "TestCaseNames";
	public static String wrksheet_name = "";
	
	
	//***SuiteType Sheet
	public static final String wrksheet_suitetype = "RunEngineMain";
	public static final int wrksheet_suitetype_col_suitetype = 0;
	public static final int wrksheet_suitetype_col_selectBrowser=2;
	public static final int wrksheet_suitetype_col_url=1;
	public static final int wrksheet_suitetype_col_paralellThreads=3;
	public static final int wrksheet_suitetype_select_mode=6;
	public static final int wrksheet_suitetype_OperatingSystem=4;
	public static final int wrksheet_suitetype_col_workflowtype=5;
	public static final int wrksheet_environmenttype=7;
	
	
	//***For Extent Reports
	
	public static final String extentConfigFilePath=projectDirectoryPath+"/extent-config.xml";
	public static Map<String,String> testToDescMap= new HashMap<String,String>();
	public static Map<String,String> moduleToDescMap= new HashMap<String,String>();
	public static Map<String, Vector<String>> moduleToTestsMap= new HashMap<String, Vector<String>>();
	public static Map<String,ExtentTest> testReportMap= new HashMap<String, ExtentTest>();
	
	
	public static final String wrksheet_groupname = "GroupNames";
	public static final String suitetype_seq = "InSequence";
	public static final String suitetype_groups = "InGroups";
	public static final int col_groupname = 0;
	public static final int col_priority = 2;
	public static final int col_groupname_runmode = 2;
	public static final int wrksheettcn_col_tcnames = 1;
	public static final int wrksheettcn_col_tcdesc = 2;
	public static final int col_classnames = 3;	
	public static final int col_url = 2;
	public static final int col_moduleid = 0;
	public static final int col_moduleDesc=1;
	public static final int col_modulerunmode = 2;
	public static final int col_tcrunmode = 4;
	public static final int col_tcgrougName = 3;
	public static final int col_results = 3;
	public static final int col_tcname = 1;

	public static final int startcolumn = 5;
	public static final int col_username = 4;
	public static final int col_pwd = 5;
	public static final int col_elementname = 0;
	public static final int col_locatortype = 1;
	public static final int col_locators = 2;

	
	
	//Added later
	
	//////////////////Variables for Login///////////////////////
	/* public interface Login{
		 public static final String logindataexcelpath = "./src/test/resources/LoginData.xlsx";
		 public static final String logindatasheetname = "Login";
	 }*/
	 //public static final String logindataexcelpath = "./src/test/resources/LoginData.xlsx";
	public static final String logindataexcelpath = "./dataSheets/LoginData.xlsx";
	
	 public static final String logindatasheetname = "Login";
	
	/////////////Variables for Program////////////////////////////		
	public static final String programdataexcelpath = "./dataSheets/ProgramData.xlsx";
	public static final String programdatasheetname = "Program";
	
	//Data for PUI
	public static final String puidataexcelpath = "./dataSheets/PUIData.xlsx";
	public static final String puidatasheetname = "PUI";
	
/////////////Variables for DashBoard////////////////////////////	
	public static final String dashboardataexcelpath = "./dataSheets/DashBoardData.xlsx";
	public static final String dashboarddatasheetname = "DashBoard";
	
///////////////////////////////////////////Variables for Form/////////////////////////
public final static String formdataexcelpath = "./dataSheets/FormData.xlsx";
public final static String formdatasheetname = "Form";

/////////////Variables for Program////////////////////////////	
public static final String measuredataexcelpath = "./dataSheets/MeasureData.xlsx";
public static final String measuredatasheetname = "Measure";

/////////////Variables for Budget and Accounting//////////////////////////
public static final String budgetandaccountingdataexcelpath = "./dataSheets/BudgetAndAccountingData.xlsx";
public static final String budgetandaccountingdatasheetname = "BudgetAndAccounting";

/////////////Variables for Intalio//////////////////////////
public static final String intaliodataexcelpath = "./dataSheets/IntalioWorkFlowData.xlsx";
public static final String intaliodatasheetname = "WorkFlow";

/////////////Variables for Projects//////////////////////////
public static final String projectdataexcelpath = "./dataSheets/ProjectData.xlsx";
public static final String projectdatasheetname = "Project";

/////////////Variables for Payments//////////////////////////
public static final String paymentdataexcelpath = "./dataSheets/PaymentData.xlsx";
public static final String paymentdatasheetname = "Payment";
public static final String batchfilepath = "./dataSheets/BatchFile.xlsx";
public static final String NewURL = null;

/////////////Variables for Activiti //////////////////////////
public static final String activitidataexcelpath = "./dataSheets/ActivitiWorkFlowData.xlsx";
public static final String activitidatasheetname = "Activiti";

public static Map<String, Vector<String>> groupNameToTestsMap=new HashMap<String, Vector<String>>();
public static Map<String,String> goupNameToDescMap=new HashMap<String,String>();
public static int col_groupname_des=1;

/////////Variables for Batch Review/////////////////////////////
public static final String batchreviewdataexcelpath = "./dataSheets/BatchReviewPageData.xlsx";
public static final String batchreviewdatasheetname = "BatchReview";

/////////Variables for UserManagement////////////////////////////
public static final String userDataManagementdataexcelpath = "./dataSheets/UserData.xlsx";
public static final String UserDataManagementdatasheetname = "User";

/////////Variables for Admin Module////////////////////////////
public static final String AdminDataexcelpath = "./dataSheets/AdminData.xlsx";
public static final String AdminDatasheetname = "Admin";

/////////Variables for Formula////////////////////////////
public static final String FormulaDataManagementdataexcelpath = "./dataSheets/FormulaData.xlsx";
public static final String FormulaDataManagementdatasheetname = "Formula";

/////////Variables for ServiceProvider////////////////////////////
public static final String ServiceProviderdataexcelpath = "./dataSheets/ServiceProviderData.xlsx";
public static final String ServiceProviderdatasheetname = "ServiceProvider";

/////////Variables for Customer////////////////////////////
public static final String Customerdataexcelpath = "./dataSheets/CustomerData.xlsx";
public static final String Customerdatasheetname = "Customer";

/////////Variables for ContactManagement////////////////////////////
public static final String ContactManagementdataexcelpath = "./dataSheets/ContactmanagementData.xlsx";
public static final String ContactManagementdatasheetname = "Contact";

/////////Variables for Equipment////////////////////////////
public static final String Equipmentdataexcelpath = "./dataSheets/EquipmentData.xlsx";
public static final String Equipmentdatasheetname = "Equipment";

/////////Variables for Partner////////////////////////////
public static final String Partnerexcelpath = "./dataSheets/PartnerData.xlsx";
public static final String Partnerdatasheetname = "Partner";


/////////Variables for DSMC Cost Effectives////////////////////////////
public static final String DSMCostexcelpath = "./dataSheets/DSMCostEffectiveness.xlsx";
public static final String DSMCostdatasheetname = "DSMCostEffectiveness";

/////////Variables for Scheduling////////////////////////////
public static final String Schedulingexcelpath = "./dataSheets/SchedulingData.xlsx";
public static final String Schedulingdatasheetname = "Scheduling";

/////////Variables for Planning////////////////////////////
public static final String planningexcelpath = "./dataSheets/PlanningData.xlsx";
public static final String planningdatasheetname = "Planning";

/////////Variables for Campaign////////////////////////////
public static final String campaignexcelpath = "./dataSheets/CampaignData.xlsx";
public static final String campaigndatasheetname = "Campaign";

/////////Variables for Onsite////////////////////////////
public static final String Onsiteexcelpath = "./dataSheets/OnsiteData.xlsx";
public static final String Onsitedatasheetname = "Onsite";

/////////Variables for Onsite////////////////////////////
public static final String publicUserInterfaceexcelpath = "./dataSheets/PublicUserInterfaceData.xlsx";
public static final String publicUserInterfacedatasheetname = "PUI";

public static final String tokeneexcelpath = "./dataSheets/TokenData.xlsx";
public static final String tokendatasheetname = "Token";


}
