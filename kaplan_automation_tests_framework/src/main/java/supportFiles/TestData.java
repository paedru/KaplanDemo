package supportFiles;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

public class TestData {
	private static XSSFRow row;
	private static int rownumber;
	private static int col_num_tcmode = 2;
	private static org.apache.poi.ss.usermodel.Cell Cell;
	private static ArrayList<TestData> arrayList;
	private static TestData[][] tabArray = null;
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	
	

	public static String getData(String FilePath, String SheetName,String TestCase,String Field) throws Exception {
		
		String retrieved_value = null;
		arrayList = new ArrayList<TestData>();
		Map<String, Integer> map = new HashMap<String,Integer>(); //Create map
		sheet = ExcelUtils.getsheet(FilePath, SheetName);
		XSSFRow row = sheet.getRow(0); //Get first row
		short minColIx = row.getFirstCellNum(); //get the first column index for a row
		short maxColIx = row.getLastCellNum(); //get the last column index for a row
		for(short colIx=minColIx; colIx<maxColIx; colIx++) { //loop from first to last index
			XSSFCell cell = row.getCell(colIx); //get the cell
			
			map.put(cell.getStringCellValue(),cell.getColumnIndex()); //add the cell contents (name of column) and cell index to the map
			}		
		
		//List<ReportRow> listOfDataFromReport = new ArrayList<ReportRow>();
		Iterator<Row> rowIterator = sheet.iterator();
		rowIterator.next();//This is to move the iterator from 1st row i.e row header to the rows with data
		while (rowIterator.hasNext()){//need to change 20
			row = (XSSFRow) rowIterator.next();
			
			int idxForTestCase = map.get("TestCaseID"); 
			
			int number_of_rows = sheet.getLastRowNum()+1;
			System.out.println("Number of rows in entire excel is " + number_of_rows);
			//identifying the row based on the Test Case ID recieved
			
				
				XSSFCell requestedField_cell = row.getCell(idxForTestCase);
				String Cell_Content = requestedField_cell.getStringCellValue().trim();
				System.out.println("the Testcase ID read from excel sheet is " +Cell_Content+ " and the TestCase ID passed from Test Case is " + TestCase);
				if (Cell_Content.equals(TestCase)){
					
				//System.out.println("The Test Case has been located in Datasheet and the field value is being retrieved...");
				int idxForrequestedField = map.get(Field);
				XSSFCell requestedField_cell1 = row.getCell(idxForrequestedField);
				
				 retrieved_value =  requestedField_cell1.toString().trim();
				//System.out.println("retrievd value is " +retrieved_value);
				break;
				
			}else{
				
				System.out.println("Did not yet locate the Test Case ID from in the Data sheet");
			}
				
		}System.out.println("Retrievd Data is " +retrieved_value);
			return retrieved_value;			
	}	
	
	
	public static String getData(String module,String Field)  {
		String retrieved_value = null;
		try{
		TestConfigurations testConfig= GlobalVariables.getConfigMap().get(Thread.currentThread().getName());
		
		String FilePath= GlobalConstants.dataSheetsPath+module+".xlsx";
		String SheetName=module;
		String TestCase= testConfig.thread;
		
		
		arrayList = new ArrayList<TestData>();
		Map<String, Integer> map = new HashMap<String,Integer>(); //Create map
		sheet = ExcelUtils.getsheet(FilePath, SheetName);
		XSSFRow row = sheet.getRow(0); //Get first row
		short minColIx = row.getFirstCellNum(); //get the first column index for a row
		short maxColIx = row.getLastCellNum(); //get the last column index for a row
		for(short colIx=minColIx; colIx<maxColIx; colIx++) { //loop from first to last index
			XSSFCell cell = row.getCell(colIx); //get the cell
			/*System.out.println("****************Content cell " +cell.getStringCellValue());
			System.out.println("****************Index of cell" +cell.getColumnIndex());*/
			map.put(cell.getStringCellValue(),cell.getColumnIndex()); //add the cell contents (name of column) and cell index to the map
			}		
		
		//List<ReportRow> listOfDataFromReport = new ArrayList<ReportRow>();
		
		Iterator<Row> rowIterator = sheet.iterator();
		rowIterator.next();//This is to move the iterator from 1st row i.e row header to the rows with data
		while (rowIterator.hasNext()){//need to change 20
			row = (XSSFRow) rowIterator.next();
			
			int idxForTestCase = map.get("TestCaseID"); 
			
			int number_of_rows = sheet.getLastRowNum()+1;
			//System.out.println("Number of rows in entire excel is " + number_of_rows);
			//identifying the row based on the Test Case ID recieved
			
				
				XSSFCell requestedField_cell = row.getCell(idxForTestCase);
				String Cell_Content = requestedField_cell.getStringCellValue().trim();
				System.out.println("the Testcase ID read from excel sheet is " +Cell_Content+ " and the TestCase ID passed from Test Case is " + TestCase);
				if (Cell_Content.equals(TestCase)){
					
				System.out.println("The Test Case has been located in Datasheet and the field value is being retrieved...");
				int idxForrequestedField = map.get(Field);
				XSSFCell requestedField_cell1 = row.getCell(idxForrequestedField);
				
				 retrieved_value =  requestedField_cell1.toString().trim();
				//System.out.println("retrievd value is " +retrieved_value);
				break;
				
			}else{
				
				System.out.println("Did not yet locate the Test Case ID from in the Data sheet");
			}
				
		}
			
			
		}catch(Exception e){
			
			LOG.report_ERROR("Get Test Data", "Error in Getting Test Data For Module:["+ module+ "]\n***" + e.toString());
		}System.out.println("retrievd value is " +retrieved_value);
		return retrieved_value;	
	}
	
	//Method to read .docx File and store date with a specific pattern like ${*}

	 public static ArrayList<String> getMatchingString(String absoluteFilePath) throws Exception
	    {
	          ArrayList<String> FoundWords = new ArrayList<String>();
	          //System.out.println("Pattern1");
	          // Create a FileInputStream object to read the input MS Word Document /
	          FileInputStream input_document = new FileInputStream(new File(absoluteFilePath));
	          //System.out.println("Pattern2");
	          XWPFDocument document = new XWPFDocument(input_document);
	          //System.out.println("Pattern3");
	          input_document.close();
	          //streamLimiter.getNewInputStream());
	          // Create Word Extractor object /
	          XWPFWordExtractor my_word=new XWPFWordExtractor(document);                
	          //System.out.println("Pattern4");
	          // Create Scanner object /             
	          Scanner document_scanner = new Scanner(my_word.getText());
	          // Define Search Pattern /
	          Pattern words = Pattern.compile("\\$\\{.*\\}");
	          Matcher matcher = words.matcher(my_word.getText());
	          int count = 0;
	          while(matcher.find()) {
	    count++;
	    FoundWords.add(matcher.group());
	    System.out.println("The matched String was "+matcher.group());
	     }
	     System.out.println("Found total of "+ count+"matches");
	            return FoundWords;

	    }
	 
	 public static int getRowIndexofTestCase(String FilePath, String SheetName,String TestCase) throws Exception {
		  
		  String retrieved_value = null;
		  int rowIndex=0;
		  int row_col_Index[] =  new int[2];
		  arrayList = new ArrayList<TestData>();
		  Map<String, Integer> map = new HashMap<String,Integer>(); //Create map
		  sheet = ExcelUtils.getsheet(FilePath, SheetName);
		  XSSFRow row = sheet.getRow(0); //Get first row
		  short minColIx = row.getFirstCellNum(); //get the first column index for a row
		  short maxColIx = row.getLastCellNum(); //get the last column index for a row
		  for(short colIx=minColIx; colIx<maxColIx; colIx++) { //loop from first to last index
		   XSSFCell cell = row.getCell(colIx); //get the cell
		   
		   map.put(cell.getStringCellValue(),cell.getColumnIndex()); //add the cell contents (name of column) and cell index to the map
		   }  
		  Iterator<Row> rowIterator = sheet.iterator();
		  rowIterator.next();//This is to move the iterator from 1st row i.e row header to the rows with data
		  while (rowIterator.hasNext()){//need to change 20
		  row = (XSSFRow) rowIterator.next();
		  int idxForTestCase = map.get("TestCaseID"); 
		  int number_of_rows = sheet.getLastRowNum()+1;
		  XSSFCell requestedField_cell = row.getCell(idxForTestCase);
		  String Cell_Content = requestedField_cell.getStringCellValue().trim();
		  if (Cell_Content.equals(TestCase)){
		  rowIndex = requestedField_cell.getRowIndex();
		  System.out.println("Index of Row of Test Case is "+rowIndex);
		  break;
		  }else{
		    
		    //System.out.println("Did not yet locate the Test Case ID from in the Data sheet");
		   }
		    
		  }//System.out.println("Retrievd Data is " +retrieved_value);
		   return rowIndex;   
		 }
	 
	 public static void verifyDataExistsInPDF(String absoluteFilePath,String variablesAndData[][]) throws Exception
	  {
	  int pdfWordCount=0;
	  //int templateWordCount=0;
	  FileInputStream input_document = new FileInputStream(new File(absoluteFilePath));
	  PDDocument document = PDDocument.load(input_document);
	  if (!document.isEncrypted()) {
	  PDFTextStripper stripper = new PDFTextStripper();
	  String text = stripper.getText(document);
	  // split by whitespace
	        String lines[] = text.split("\\r?\\n");
	        for (String line : lines) {
	         for(int i =0;i<variablesAndData.length;i++)
	            {
	             if(variablesAndData[i][1].trim().equals(line.trim()))
	              {LOG.report_PASS("Locate the template data in Genererated PDF", "Located the template data "+variablesAndData[i][1]+" in generated PDF");
	              pdfWordCount=pdfWordCount+1;
	              }
	             /*else
	              {LOG.report_WARNING("Locate the template data in Genererated PDF", "Unable to locate the template data"+variablesAndData[i][1]+" in the generated PDF file");
	              //templateWordCount=templateWordCount+1;
	              }*/
	            }
	   
	        }
	  
	  }
	  //input_document.c
	  document.close();
	  input_document.close();
	  if(pdfWordCount==variablesAndData.length)LOG.report_PASS("Validate data in generated PDF", "Validated the data in generated PDF is matching with template data in terms of number of variable data value");
	  else LOG.report_WARNING("Validate data in generated PDF", "Validated the data in generated PDF is not mathcing with template data in terms of number of variable data value");
	  
	    }
	}




