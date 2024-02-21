package supportFiles;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Iterator;

//import main.Initiator;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class ExcelUtils {
	
	private static XSSFSheet ExcelWSheet;
    private static XSSFWorkbook ExcelWBook;
    private static org.apache.poi.ss.usermodel.Cell Cell;
    //private static XSSFCell Cell;
    private static XSSFRow Row;
    public Row row;
    private static int rownumber;
    public String tcrunmode;
    private static FileInputStream ExcelFile;
    public static String[][] tabArray = null;
    
    

//This method is to set the File path and to open the Excel file
//Pass Excel Path and SheetName as Arguments to this method
	public static void setExcelFile(String Path) throws Exception {
			
		try{
			ExcelFile = new FileInputStream(Path);
	        ExcelWBook = new XSSFWorkbook(ExcelFile);
		}
		catch (Exception e){
			//Log.error("Class Utils | Method setExcelFile | Exception desc : "+e.getMessage());
			//DriverScript.bResult = false;
			//Run.bResult = false;
			}
	        //ExcelWSheet = ExcelWBook.getSheet(SheetName);
	       }
	
	public static XSSFSheet getsheet(String FilePath, String sheetname) throws Exception{
			ExcelFile = new FileInputStream(FilePath);
	        ExcelWBook = new XSSFWorkbook(ExcelFile);
	        ExcelWSheet = ExcelWBook.getSheet(sheetname);
		
		return ExcelWSheet;		
	}
	
	public ExcelUtils getdata(String FilePath, String sheetname) throws Exception{
		//String tcrunmode;
		System.out.println("In Excel Utils................................");
		ExcelUtils eu = new ExcelUtils();
		ExcelWSheet = ExcelUtils.getsheet(FilePath, sheetname);
		Iterator<Row> rowIterator = ExcelWSheet.iterator();
		while (rowIterator.hasNext()) {
			eu.row = (Row) rowIterator.next();
			
			rownumber = eu.row.getRowNum();
			//String tcrunmode = ExcelUtils.getCellData(rownumber, FrameworkLevelData.col_tcrunmode, SheetName);
			Cell = ExcelWSheet.getRow(rownumber).getCell(GlobalConstants.col_tcrunmode);			
			String CellData = Cell.getStringCellValue();
			eu.tcrunmode = CellData;			
				
		}
		return eu;
	}
	//retrieved_value = fmt.formatCellValue(requestedField_cell1);
	
	//This method is to read the test data from the Excel cell
	//In this we are passing parameters/arguments as Row Num and Col Num
	public static String getCellData(int RowNum, int ColNum, String SheetName) throws Exception{		  
		DataFormatter fmt = new DataFormatter();
		try{
		    ExcelWSheet = ExcelWBook.getSheet(SheetName);	  
			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);			
			String CellData = fmt.formatCellValue(Cell);
			return CellData;
		  }
		  catch(Exception e){
		    //Log.error("Class Utils | Method getCellData | Exception desc : "+e.getMessage());
			//DriverScript.bResult = false;
			//Run.bResult = false;
			return"";
		  }
		}
	
	//This method is to get the row count used of the excel sheet
	public static int getRowCount(String SheetName){
		  int iNumber=0;
		  try{
			  ExcelWSheet = ExcelWBook.getSheet(SheetName);
			  iNumber=ExcelWSheet.getLastRowNum()+1;
		  }
		  catch (Exception e){
				//Log.error("Class Utils | Method getRowCount | Exception desc : "+e.getMessage());
				//DriverScript.bResult = false;
			 // Run.bResult = false;
			  
			  	System.out.println("Exception in ExcelUtils: getRowCount, SheetName: " + SheetName);
			  	e.printStackTrace();
				}
		  		
			return iNumber;
		}
	
	public static int getColumnCount(String SheetName, int row){
		int iNumber=0;
		try{
			ExcelWSheet = ExcelWBook.getSheet(SheetName);
			iNumber=ExcelWSheet.getRow(row).getLastCellNum();
		}
		catch (Exception e){
			//Log.error("Class Utils | Method getRowCount | Exception desc : "+e.getMessage());
			//DriverScript.bResult = false;
		  //Run.bResult = false;
			}
	  		
		return iNumber;
	}
	
	public static int agetRowCount(String SheetName){
		  int iNumber=0;
		  try{
			  ExcelWSheet = ExcelWBook.getSheet(SheetName);
			  iNumber=ExcelWSheet.getLastRowNum()+1;
		  }
		  catch (Exception e){
				//Log.error("Class Utils | Method getRowCount | Exception desc : "+e.getMessage());
				//DriverScript.bResult = false;
			  //Run.bResult = false;
		  }
		  		
			return iNumber;
		}
	
	//This method is to get the Row number of the test case
	//This methods takes three arguments(Test Case name , Column Number & Sheet name)
	public static int getRowContains(String sTestCaseName, int colNum,String SheetName) throws Exception{
		int iRowNum=0;	
		try {
			//ExcelWSheet = ExcelWBook.getSheet(SheetName);
			int rowCount = ExcelUtils.getRowCount(SheetName);
			for (; iRowNum<rowCount; iRowNum++){
				if  (ExcelUtils.getCellData(iRowNum,colNum,SheetName).equalsIgnoreCase(sTestCaseName)){
					break;
				}
			}       			
		} catch (Exception e){
			//Log.error("Class Utils | Method getRowContains | Exception desc : "+e.getMessage());
			///DriverScript.bResult = false;
			//Run.bResult = false;
			}
		return iRowNum;
		}
	
	//This method is to get the count of the test methods
	//This method takes three arguments (Sheet name, Test Case Id & Test case row number)
	public static int getTestMethods(String SheetName, String sTestCaseID, int iTestCaseStart) throws Exception{
		try {
			for(int i=iTestCaseStart;i<=ExcelUtils.getRowCount(SheetName);i++){
				if(!sTestCaseID.equals(ExcelUtils.getCellData(i, GlobalConstants.col_moduleid, SheetName))){
					int number = i;
					return number;      				
					}
				}
			ExcelWSheet = ExcelWBook.getSheet(SheetName);
			int number=ExcelWSheet.getLastRowNum()+1;
			return number;
		} catch (Exception e){
			//Log.error("Class Utils | Method getRowContains | Exception desc : "+e.getMessage());
			//DriverScript.bResult = false;
			return 0;
		}
	}
	
	
	//This method is use to write value in the excel sheet
	//This method accepts four arguments (Result, Row Number, Column Number & Sheet Name)
	public static void setCellData(String Path, String data,  int RowNum, int ColNum, String SheetName) throws Exception    {
		   try{
			   ExcelFile = new FileInputStream(Path);
		       ExcelWBook = new XSSFWorkbook(ExcelFile);
			   ExcelWSheet = ExcelWBook.getSheet(SheetName);
			   Row  = ExcelWSheet.getRow(RowNum);			   
			   Cell = Row.getCell(ColNum, org.apache.poi.ss.usermodel.Row.RETURN_BLANK_AS_NULL);
			   if (Cell == null) {
				   
				   Cell = Row.createCell(ColNum);
				   Cell.setCellValue(data);
			   } else {
					Cell.setCellValue(data);
					}
				// Constant variables Test Data path and Test Data file name
				FileOutputStream fileOut = new FileOutputStream(Path);
				ExcelWBook.write(fileOut);
				//fileOut.flush();
				//fileOut.close();
				//ExcelWBook = new XSSFWorkbook(new FileInputStream(GlobalConstants.excelpath));
			 }catch(Exception e){
				// Run.bResult = false;
				}
	}
	
	public static String excelMapping(String moduleid){
		String excelsheetname ="";
		if(moduleid.equals(GlobalConstants.module_id_login)){
			//ExcelWSheet = ExcelWBook.getSheet("Login_TC");
			excelsheetname = ExcelWBook.getSheetName(1);	
		}
		else if(moduleid.equals(GlobalConstants.module_id_onsite)){
			//ExcelWSheet = ExcelWBook.getSheet("Login_TC");
			excelsheetname = ExcelWBook.getSheetName(3);	
		}
		else if(moduleid.equals("OnsiteProgSettings")){
			//ExcelWSheet = ExcelWBook.getSheet("Login_TC");
			excelsheetname = ExcelWBook.getSheetName(4);	
		}
		return excelsheetname;
	}
	
	public static String excelSheetMapping(String classname){
		String excelsheetname ="";
		if(classname.equals("[TestClass name=class com.nexant.tradeallyfunctionaltests.OnsiteProgram]")){
			//ExcelWSheet = ExcelWBook.getSheet("Login_TC");
			excelsheetname = ExcelWBook.getSheetName(4);	
		}
		else if(classname.equals(GlobalConstants.module_id_onsite)){
			//ExcelWSheet = ExcelWBook.getSheet("Login_TC");
			excelsheetname = ExcelWBook.getSheetName(3);	
		}
		else if(classname.equals("OnsiteProgSettings")){
			//ExcelWSheet = ExcelWBook.getSheet("Login_TC");
			excelsheetname = ExcelWBook.getSheetName(4);	
		}
		return excelsheetname;
	}
	
	
	
	public static int excelFileName(){
		int excelfile = ExcelWBook.getNumberOfSheets();
		return excelfile;
		
	}
	
	public static Object[][] getTableArray(String sheetname) throws Exception { 	
		   System.out.println("**********In Get Table Array Method****************");
		   ///System.out.println("Row Number is :" +row);
		  // System.out.println("%%%%%%%%%%%in Get Table Array Methods%%%%%" + sheetName);
		   //String[][] tabArray = null;
		   int ci,cj;
		   int totaltcs = ExcelUtils.getRowCount(sheetname);
		   int totalcolumns = ExcelUtils.getColumnCount(sheetname, 0);
		   System.out.println("Total Columns are :" +totalcolumns);
		   tabArray=new String[totaltcs+1][totalcolumns-4];
		  
		   try{ 
			   ExcelUtils.setExcelFile(HelperMethods.getRunEngineFileName());
			  // int totaltcs = ExcelUtils.getRowCount(sheetname);
			   System.out.println("Total Row count of sheet: " +totaltcs);
			   ci=0;
			   for(int tc =1;tc<totaltcs;tc++, ci++){
				   String tcrunmode = ExcelUtils.getCellData(tc, GlobalConstants.col_tcrunmode, sheetname);
					System.out.println("****************Run Mode of TC is:" +tcrunmode);
					if(tcrunmode.equalsIgnoreCase("Yes")){
						cj=0;
						//int totalCols = 5;
						//int totalcolumns = ExcelUtils.getColumnCount(sheetname, tc -1);
						System.out.println("&&&&&&&&&&Total colummns are &&&&&&&& " +totalcolumns);
						  for (int j=GlobalConstants.startcolumn;j<=totalcolumns;j++, cj++){
							 System.out.println("From for loop Row number is :" +tc);
						   	 System.out.println("From for loop column number is :" +j);
						   	 System.out.println("From for loop sheet name is :" +sheetname);
						   	 System.out.println("Cell Data is equal to: " +getCellData(tc,j-1,sheetname));
							 tabArray[ci][cj]=getCellData(tc,j-1,sheetname); 
							 System.out.println("What is ci: " +ci);
							 System.out.println("What is cj: " +cj);
							 System.out.println("Objects From Excel Sheets are:" +tabArray[ci][cj]);
						 }						
					}
			   }			   
		}				
			catch (FileNotFoundException e){
				System.out.println("Could not read the Excel sheet");
				e.printStackTrace();
				}
			catch (IOException e){
				System.out.println("Could not read the Excel sheet");
				e.printStackTrace();
				}
			return(tabArray);		   
			}	
	
	public static String getURL(){
		String url = "";
		try {
			ExcelUtils.setExcelFile(HelperMethods.getRunEngineFileName());	
			 url = ExcelUtils.getCellData(1, 2, "SuiteType");
		} catch (Exception e1) {			
			e1.printStackTrace();
		}
		return url;
		
	}

	
	public static void setCellDataInCaseOfBlankRow(String Path, String data,  int RowNum, int ColNum, String SheetName)throws Exception{
		  try{
		      ExcelFile = new FileInputStream(Path);
		         ExcelWBook = new XSSFWorkbook(ExcelFile);
		      ExcelWSheet = ExcelWBook.getSheet(SheetName);
		      int rowcount = ExcelUtils.getRowCount(SheetName);
		      System.out.println("***************RowCount before create is :" +rowcount);
		     // Row = ExcelWSheet.getRow(RowNum);
		      Row =  ExcelWSheet.createRow(RowNum);
		      Cell = Row.getCell(ColNum, org.apache.poi.ss.usermodel.Row.RETURN_BLANK_AS_NULL);
		      if (Cell == null) {
		       
		       Cell = Row.createCell(ColNum);
		       Cell.setCellValue(data);
		      } else {
		     Cell.setCellValue(data);
		     }
		    // Constant variables Test Data path and Test Data file name
		    FileOutputStream fileOut = new FileOutputStream(Path);
		    ExcelWBook.write(fileOut);   
		  }
		  catch(Exception e){
		   
		  }
		 }
	
	
	public static String getSheetName(String FilePath, int sheetindex) throws IOException{
		  String sheetname = "";
		  ExcelFile = new FileInputStream(FilePath);
		        ExcelWBook = new XSSFWorkbook(ExcelFile);  
		  sheetname = ExcelWBook.getSheetName(sheetindex);
		  return sheetname;
		 }
	
	
	public static void setCellDataForDate(String Path, String data,  int RowNum, int ColNum, String SheetName) throws Exception    {
	     try{
	      ExcelFile = new FileInputStream(Path);
	         ExcelWBook = new XSSFWorkbook(ExcelFile);
	      ExcelWSheet = ExcelWBook.getSheet(SheetName);
	      XSSFCellStyle dateCellStyle = ExcelWBook.createCellStyle();
	      short df;
	      //CellStyle cellStyle = ExcelWBook.createCellStyle();
	      //XSSFDataFormat df = ExcelWBook.createDataFormat();
	      //CreationHelper createHelper = ExcelWBook.getCreationHelper();
	     // cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("mm-dd-yy"));
	      Row  = ExcelWSheet.getRow(RowNum);       
	      Cell = Row.getCell(ColNum, org.apache.poi.ss.usermodel.Row.RETURN_BLANK_AS_NULL);     
	      if (Cell == null) {
	       
	       Cell = Row.createCell(ColNum);
	       
	       Cell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
	       
	       SimpleDateFormat datetemp = new SimpleDateFormat("MM-dd-yy");
	       Date cellValue = datetemp.parse(data);
	       Cell.setCellValue(cellValue);
	       df = ExcelWBook.createDataFormat().getFormat("MM-dd-yyyy");
	       dateCellStyle.setDataFormat(df);
	       Cell.setCellStyle(dateCellStyle);
	       
	       //Cell.setCellValue(data);
	      // Cell.setCellStyle(cellStyle);
	      } else {
	     //Cell.setCellValue(data);
	     
	      Cell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
	        
	        SimpleDateFormat datetemp = new SimpleDateFormat("MM-dd-yy");
	        Date cellValue = datetemp.parse(data);
	        Cell.setCellValue(cellValue);
	        df = ExcelWBook.createDataFormat().getFormat("MM-dd-yyyy");
	        dateCellStyle.setDataFormat(df);
	        Cell.setCellStyle(dateCellStyle);
	     
	     //Cell.setCellStyle(cellStyle);
	     }
	    // Constant variables Test Data path and Test Data file name
	    FileOutputStream fileOut = new FileOutputStream(Path);
	    ExcelWBook.write(fileOut);
	    //fileOut.flush();
	    //fileOut.close();
	    //ExcelWBook = new XSSFWorkbook(new FileInputStream(GlobalConstants.excelpath));
	    }catch(Exception e){
	    // Run.bResult = false;
	    }
	 }
	
	 public static void setCellData2(String Path, String data,  int RowNum, int ColNum, String SheetName) throws Exception    {
	      try{
	       System.out.println("Cell Type1");
	       FileInputStream ExcelFile = new FileInputStream(Path);
	       System.out.println("Cell Type2");
	       XSSFWorkbook ExcelWBook = new XSSFWorkbook(ExcelFile);
	       System.out.println("Cell Type3");
	       XSSFSheet ExcelWSheet = ExcelWBook.getSheet(SheetName);
	       System.out.println("Cell Type4");
	       XSSFRow Row  = ExcelWSheet.getRow(RowNum); 
	       System.out.println("Cell Type5"+Row.getRowNum()+Row.getFirstCellNum()+Row.getPhysicalNumberOfCells());
	       Cell = Row.createCell(ColNum);
	       Cell.setCellType(CellType.STRING);
	       Cell.setCellValue(data);
	       
	     // Constant variables Test Data path and Test Data file name
	     FileOutputStream fileOut = new FileOutputStream(Path);
	     System.out.println("Cell Type8");
	     ExcelWBook.write(fileOut);
	     System.out.println("Cell Type9");
	     //fileOut.flush();
	     fileOut.close();
	     //ExcelWBook = new XSSFWorkbook(new FileInputStream(GlobalConstants.excelpath));
	     }catch(Exception e){
	     System.out.println(e.toString());
	     }
	  }
	
	
}
