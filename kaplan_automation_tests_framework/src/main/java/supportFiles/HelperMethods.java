package supportFiles;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

//import main.Initiator;

import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlDefine;
import org.testng.xml.XmlGroups;
import org.testng.xml.XmlInclude;
import org.testng.xml.XmlRun;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;
import org.testng.xml.XmlSuite.ParallelMode;



public class HelperMethods {
	private static String modulerunmode;
	private static String moduleid;
	private static String moduleDesc;
	private static String classname;
	private static String tcname;
	private static String tcdesc;
	private static String suitetype; 
	private static int threadCount;
	private static XmlSuite suite;
	private static XmlTest test;
	private static XmlClass testClass;
	private static ArrayList<XmlClass> classes;
	private static ArrayList<XmlInclude> methodsToRun;	
	private static XmlGroups testgroups;
	private static Map<String,String> classExecNames;
	public static final String projectDirectoryPath=System.getProperty("user.dir");
	public static String runengineexcelpath = "";
	
	private static Map<String,String> getModuleClassNames(){
		
		
		try{
		int totalmodules = ExcelUtils.getRowCount(GlobalConstants.sheet_name_modules);		
		////log.info("****************Total Module is:" +totalmodules);
		for(int module = 1; module<totalmodules; module++){
			modulerunmode = ExcelUtils.getCellData(module, GlobalConstants.col_modulerunmode, GlobalConstants.sheet_name_modules);
			moduleid = ExcelUtils.getCellData(module, GlobalConstants.col_moduleid, GlobalConstants.sheet_name_modules);
			moduleDesc=ExcelUtils.getCellData(module, GlobalConstants.col_moduleDesc, GlobalConstants.sheet_name_modules);
			////log.info("********** Module Id's are" + moduleid);
			if(modulerunmode.equalsIgnoreCase("Yes")){				
			
				classname="testSuites."+moduleid;			
				classExecNames.put(moduleid,classname);			
			}}
		}catch(Exception e){
			
			System.out.println("Exeption in getModuleClassNames Method" + e.toString());
		}
		
		
		return classExecNames;	
		
	}
	
	
	
	@SuppressWarnings("deprecation")
	protected static void createSuite(){
		suite = new XmlSuite ();
		suite.setName("Regression_Automation_Test");
		suite.setParallel(ParallelMode.METHODS);
		//suite.setParallel("methods");
		suite.setThreadCount(threadCount);		
		Map<String, String> suiteMetaDataMap = new HashMap<String, String>();
		//suiteMetaDataMap.put("SiteAddress", "${SiteAddress}");
		suiteMetaDataMap.put("SiteAddress", GlobalConstants.URL);
		//suiteMetaDataMap.put("SiteAddress", "http://172.27.104.44:8080/traksmart4/unprotected///login.do");
		suite.setParameters(suiteMetaDataMap);		
		suite.setListeners(addListeners());
	}
	
	private static void createTest(){
		test = new XmlTest(suite);
        test.setPreserveOrder(true); 
        test.setName("All"); 
	}
	
	public static void readClassesMethods() throws Exception{
		int tccount; 
		
		createSuite();
		createTest();              
        classes = new ArrayList<XmlClass>();        
        testClass = new XmlClass();
        //ExcelUtils.setExcelFile(GlobalConstants.excelpath);
        ExcelUtils.setExcelFile(HelperMethods.getRunEngineFileName());
        //ExcelUtils.setExcelFile(Initiator.runenginefile);
		int totalmodules = ExcelUtils.getRowCount(GlobalConstants.sheet_name_modules);		
		////log.info("****************Total Module is:" +totalmodules);
		for(int module = 1; module<totalmodules; module++){
			modulerunmode = ExcelUtils.getCellData(module, GlobalConstants.col_modulerunmode, GlobalConstants.sheet_name_modules);
			moduleid = ExcelUtils.getCellData(module, GlobalConstants.col_moduleid, GlobalConstants.sheet_name_modules);
			moduleDesc=ExcelUtils.getCellData(module, GlobalConstants.col_moduleDesc, GlobalConstants.sheet_name_modules);
			////log.info("********** Module Id's are" + moduleid);
			if(modulerunmode.equalsIgnoreCase("Yes")){
				
				System.out.println("###***ModuleName: " + moduleid );
				//classname = ExcelUtils.getCellData(module, GlobalConstants.col_classnames, GlobalConstants.sheet_name_modules);
				classname="testSuites."+moduleid;
				//url= ExcelUtils.getCellData(module, FrameworkLevelData.col_classnames, FrameworkLevelData.sheet_modules);
				//log.info("Class Names are" + classname);				
				testClass.setName(classname);				
				methodsToRun = new ArrayList<XmlInclude>();				
				//log.info("***********Class Name is:" + classname);	
				//int totaltcs = ExcelUtils.getRowCount(FrameworkLevelData.wrksheet_testcasesnames);
				GlobalConstants.wrksheet_name = moduleid;
				int totaltcs = ExcelUtils.getRowCount(GlobalConstants.wrksheet_name);
				//log.info("Total Methods are: " + totaltcs);
				int rowcountoftc = 0;
				Vector<String> testNames= new Vector();
				for(tccount = 1; tccount<= totaltcs; tccount++){
					//moduleid_inwrksheettcn = ExcelUtils.getCellData(tccount, FrameworkLevelData.col_moduleid, FrameworkLevelData.wrksheet_testcasesnames);
					//String tcrunmode = ExcelUtils.getCellData(tccount, FrameworkLevelData.col_tcrunmode, FrameworkLevelData.wrksheet_testcasesnames);
					String tcrunmode = ExcelUtils.getCellData(tccount, GlobalConstants.col_tcrunmode, GlobalConstants.wrksheet_name);
					////log.info("Module id is:" +moduleid_inwrksheettcn);
					//if(moduleid_inwrksheettcn.equals(moduleid)&& tcrunmode.equals("Yes")){
					if(tcrunmode.equals("Yes")){
						/*//log.info("*******" +ExcelUtils.getCellData(tccount, FrameworkLevelData.col_moduleid, FrameworkLevelData.wrksheet_testcasesnames));
						tcname = ExcelUtils.getCellData(tccount, FrameworkLevelData.wrksheettcn_col_tcnames, FrameworkLevelData.wrksheet_testcasesnames);*/
						//log.info("*******" +ExcelUtils.getCellData(tccount, GlobalConstants.col_moduleid, GlobalConstants.wrksheet_name));
						tcname = ExcelUtils.getCellData(tccount, GlobalConstants.wrksheettcn_col_tcnames, GlobalConstants.wrksheet_name);
						tcdesc = ExcelUtils.getCellData(tccount, GlobalConstants.wrksheettcn_col_tcdesc, GlobalConstants.wrksheet_name);
						
						testNames.add(tcname);		
						
						GlobalConstants.testToDescMap.put(tcname,tcdesc);
						//log.info("Test Case name to be included is:" + tcname);						
						rowcountoftc++;						
						methodsToRun.add(new XmlInclude(tcname));
						testClass.setIncludedMethods(methodsToRun);											
					}					
				}				
				//log.info("Row count of " +moduleid+ " is " +rowcountoftc);
				if(rowcountoftc > 0){
					classes.add(testClass);
				}							
				test.setXmlClasses(classes);
				
				
				GlobalConstants.moduleToTestsMap.put(moduleid,testNames);
				GlobalConstants.moduleToDescMap.put(moduleid, moduleDesc);
				
			}			
			testClass = new XmlClass();		
		}		
	}
	
	protected static ArrayList<String>addListeners(){
		ArrayList<String> listeners = new ArrayList<String>();
		//listeners.add("com.nexant.utilities.RetryListener");
		//listeners.add("SupportFiles.TestListener");
		//listeners.add("org.uncommons.reportng.HTMLReporter");
		//listeners.add("org.uncommons.reportng.JUnitXMLReporter");
		//listeners.add("SupportFiles.TestMethodListener");
		return listeners;
	}
	
	public static void createTestNGFile(String filepath) throws IOException{
		File file = new File(filepath);
		//File file = new File("D:\\testit.xml");
		FileWriter writer = new FileWriter(file);
		writer.write(suite.toXml());
		writer.close();	
	}
	
	public static void runTestNG(String filename){
		TestNG runner=new TestNG();		  
		// Create a list of String 
		List<String> suitefiles=new ArrayList<String>();			  
		// Add xml file which you have to execute
		suitefiles.add(GlobalConstants.testng_xml_filename);		
		// now set xml file for execution
		runner.setTestSuites(suitefiles);		
		// finally execute the runner using run method
		runner.run();
	}
	
	public static String[] readGroups() throws Exception{
		createSuite();
		createTest();		       
		testgroups = new XmlGroups();
		XmlDefine define = new XmlDefine();
		Vector<String> testcasesNames= new Vector<String>();
		XmlRun run = new XmlRun();		
		testgroups.setRun(run);
		//ExcelUtils.setExcelFile(GlobalConstants.excelpath);
		ExcelUtils.setExcelFile(HelperMethods.getRunEngineFileName());
		//ExcelUtils.setExcelFile(Initiator.runenginefile);
		
		int rowcount = ExcelUtils.getRowCount(GlobalConstants.wrksheet_groupname);
		//log.info("****************Total rowcount is:" +rowcount);
		
		String[] allGroupNames2= new String[rowcount];
		int gn=0;
		for(int row = 1; row<rowcount; row++){
			String groupname = ExcelUtils.getCellData(row, GlobalConstants.col_groupname, GlobalConstants.wrksheet_groupname);
			String runmode = ExcelUtils.getCellData(row, GlobalConstants.col_groupname_runmode, GlobalConstants.wrksheet_groupname);
			String groupdesc=ExcelUtils.getCellData(row, GlobalConstants.col_groupname_des, GlobalConstants.wrksheet_groupname);
			if(runmode.equals("Yes")){
				test.addIncludedGroup(groupname);
				allGroupNames2[gn]=groupname;
				GlobalConstants.goupNameToDescMap.put(groupname, groupdesc);
				gn++;
			}
		}
		 String[] allGroupNames= new String[gn];
		 for(int i=0;i<gn;i++)
			 allGroupNames[i]=allGroupNames2[i];
		 
		
		classes = new ArrayList<XmlClass>();        
	    testClass = new XmlClass();
		int totalmodules = ExcelUtils.getRowCount(GlobalConstants.sheet_name_modules);		
		//log.info("****************Total Module is:" +totalmodules);
		for(int module = 1; module<totalmodules; module++){
			/*modulerunmode = ExcelUtils.getCellData(module, GlobalConstants.col_modulerunmode, GlobalConstants.sheet_name_modules);
			moduleid = ExcelUtils.getCellData(module, GlobalConstants.col_moduleid, GlobalConstants.sheet_name_modules);*/
			//ExcelUtils.setExcelFile(GlobalConstants.excelpath);
			ExcelUtils.setExcelFile(HelperMethods.getRunEngineFileName());
			//ExcelUtils.setExcelFile(Initiator.runenginefile);
			modulerunmode = ExcelUtils.getCellData(module, GlobalConstants.col_modulerunmode, GlobalConstants.sheet_name_modules);
			moduleid = ExcelUtils.getCellData(module, GlobalConstants.col_moduleid, GlobalConstants.sheet_name_modules);
			moduleDesc=ExcelUtils.getCellData(module, GlobalConstants.col_moduleDesc, GlobalConstants.sheet_name_modules);
			
			//log.info("********** Module Id's are" + moduleid);
			if((modulerunmode.equalsIgnoreCase("Yes")) && !(moduleid.equalsIgnoreCase("GroupsSetup"))){
				//System.out.println("###***ModuleName: " + moduleid );
				classname="testSuites."+moduleid;
				//classname = ExcelUtils.getCellData(module, GlobalConstants.col_classnames, GlobalConstants.sheet_name_modules);
				//log.info("Class Names are" + classname);				
				testClass.setName(classname);	
				classes.add(testClass);
				test.setXmlClasses(classes);	
				GlobalConstants.moduleToDescMap.put(moduleid, moduleDesc);
				////*************************ADDING CODE************************************
				
				//GlobalConstants.wrksheet_name = moduleid;
				System.out.println("MOdule ID is:" +moduleid);
				int totaltcs = ExcelUtils.getRowCount(moduleid);
				//log.info("Total Methods are: " + totaltcs);
				int rowcountoftc = 0;
				
				for(int tccount = 1; tccount<= totaltcs; tccount++){
					
						String grpName= ExcelUtils.getCellData(tccount, GlobalConstants.col_tcgrougName, moduleid);
						
						//System.out.println("##Group Name From TC Sheet: "  +grpName);
						String[] temp=grpName.split(",");
						boolean flag=false;
						
						for (String forFlag:allGroupNames){					
							
							//System.out.println("##Group Name: "  +forFlag);
							
							for(String forFlag2:temp){
								//System.out.println("IN HP Group Name: " +forFlag2);
								
								if (forFlag2.equalsIgnoreCase(forFlag.trim()))
								flag=true;
						}
							
							
						
						}
								
						
						
						if (flag){
							tcname = ExcelUtils.getCellData(tccount, GlobalConstants.wrksheettcn_col_tcnames, moduleid);
							tcdesc = ExcelUtils.getCellData(tccount, GlobalConstants.wrksheettcn_col_tcdesc, moduleid);
							System.out.println("Things Getting Added: Key: "+tcname+" Value: "+tcdesc);				
						
							GlobalConstants.testToDescMap.put(tcname,tcdesc);
							testcasesNames.add(tcname);
							
						}
										
				}		
				
				
				
				////*******************************************************************************
			}
			
		GlobalConstants.moduleToTestsMap.put(moduleid, testcasesNames);
		testClass = new XmlClass();			
	}
		
		classname="testSuites.GroupsSetup";					
		testClass.setName(classname);	
		classes.add(testClass);
		test.setXmlClasses(classes);
		return allGroupNames;
	}
	
	
	public static void createGroupsToTestsMap(String[] grpNames){
		//GlobalConstants.groupNameToTestsMap.put(forFlag, testcasesNames);
		try{
			
			
			
		for(String group:grpNames){
			Vector<String> testcaseNames=new Vector<String>();
			//ExcelUtils.setExcelFile(GlobalConstants.excelpath);
			ExcelUtils.setExcelFile(HelperMethods.getRunEngineFileName());
			//ExcelUtils.setExcelFile(Initiator.runenginefile);
			int totalmodules = ExcelUtils.getRowCount(GlobalConstants.sheet_name_modules);
			System.out.println("Total Modules are:"+totalmodules);
			for(int module = 1; module<totalmodules; module++){
				String modulerunmode = ExcelUtils.getCellData(module, GlobalConstants.col_modulerunmode, GlobalConstants.sheet_name_modules);
				String moduleid = ExcelUtils.getCellData(module, GlobalConstants.col_moduleid, GlobalConstants.sheet_name_modules);
				
				if((modulerunmode.equalsIgnoreCase("Yes")) && !(moduleid.equalsIgnoreCase("GroupsSetup")) ){
					
					int totaltcs = ExcelUtils.getRowCount(moduleid);
					for(int tccount = 1; tccount<= totaltcs; tccount++){
						
						 
						String grpName= ExcelUtils.getCellData(tccount, GlobalConstants.col_tcgrougName, moduleid);
						String tcName= ExcelUtils.getCellData(tccount, GlobalConstants.col_tcname, moduleid);
						
						String temp[]=grpName.split(",");
						for(String tn:temp){
							if(tn.equalsIgnoreCase(group))
							{testcaseNames.add(tcName);}
							
						}
						
					
						}
					
						
				}
				
			}
			
			
			GlobalConstants.groupNameToTestsMap.put(group, testcaseNames);
		}
			
		}catch(Exception e){
			
			e.printStackTrace();
		}
			
	}
		
		
	
	
	public static void selectSuiteType() throws Exception{
		//ExcelUtils.setExcelFile(GlobalConstants.excelpath);
		System.out.println("Excel Path in selectSuiteType() method is: "+getRunEngineFileName());
		//ExcelUtils.setExcelFile(HelperMethods.getRunEngineFileName());
		runengineexcelpath = getRunEngineFileName();
		System.out.println("EXCEL PATH IS : " +runengineexcelpath);
		ExcelUtils.setExcelFile(runengineexcelpath);
		//ExcelUtils.setExcelFile(Initiator.runenginefile);
		
		int rowcount = ExcelUtils.getRowCount(GlobalConstants.wrksheet_suitetype);		
		//log.info("****************Total rowcount is:" +rowcount);
		System.out.println("***Number Of Rows: " + rowcount);
		
		boolean flag=false;
		for(int row = 1; row<rowcount; row++){
			
			//suitetype = ExcelUtils.getCellData(row, GlobalConstants.wrksheet_suitetype_col_suitetype, GlobalConstants.wrksheet_suitetype);
			String select= ExcelUtils.getCellData(row, GlobalConstants.wrksheet_suitetype_select_mode, GlobalConstants.wrksheet_suitetype);
				
			if (select.equalsIgnoreCase("Yes")){
			
				suitetype = ExcelUtils.getCellData(row, GlobalConstants.wrksheet_suitetype_col_suitetype, GlobalConstants.wrksheet_suitetype);
				GlobalConstants.URL= ExcelUtils.getCellData(row, GlobalConstants.wrksheet_suitetype_col_url, GlobalConstants.wrksheet_suitetype);
				GlobalConstants.browser=ExcelUtils.getCellData(row, GlobalConstants.wrksheet_suitetype_col_selectBrowser, GlobalConstants.wrksheet_suitetype);
				threadCount=Integer.parseInt(ExcelUtils.getCellData(row, GlobalConstants.wrksheet_suitetype_col_paralellThreads, GlobalConstants.wrksheet_suitetype));
				GlobalConstants.operatingSystem=ExcelUtils.getCellData(row, GlobalConstants.wrksheet_suitetype_OperatingSystem, GlobalConstants.wrksheet_suitetype);
				GlobalConstants.environmenttype=ExcelUtils.getCellData(row, GlobalConstants.wrksheet_environmenttype, GlobalConstants.wrksheet_suitetype);
				flag=true;
			}		
							
	}		
			
			if(suitetype.equals("InSequence")){
				readClassesMethods();
			}
			if(suitetype.equals("InGroups")){
				String[] grpNames=readGroups();
				createGroupsToTestsMap(grpNames);
			}
			
			
}

	public static String getRunEngineFileName(){
		String excelpath = "";		 
		try{			
			String runenginefile = System.getProperty("runenginename")+".xlsx";			
			System.out.println("RUNENGINE FILE NAME IS: "+runenginefile);			
			if(runenginefile.contains("null")){
				runenginefile = GlobalConstants.defaultrunenginename;
				System.out.println("Run Engine File Name in case of null is "+GlobalConstants.defaultrunenginename);
			}
			System.out.println("*********Excel Path is "+GlobalConstants.projectDirectoryPath+"\"+runenginefile);
			excelpath = GlobalConstants.projectDirectoryPath+"\"+runenginefile;	
			ExcelUtils.setExcelFile(excelpath);

		}
		catch(Exception e){
			System.out.println(e);
		}
		return excelpath;
	}
}
