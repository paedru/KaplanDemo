package supportFiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import objectRepository.Locator;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

//import org.testng.Assert;


//import org.testng.Assert;

import com.google.common.base.Function;


public class Assert {
	
	public static void assertEquals(String actual, String expected){
		
		try{
			
			if(actual.equalsIgnoreCase(expected))
				LOG.report_PASS("Validate Data", "The Actual Value ["+ actual+"] has matched with Expected Value ["+expected+"]");
			else
				LOG.report_FAIL("Validate Data", "The Actual Value ["+ actual+"] has NOT matched with Expected Value ["+expected+"]");
			
		}catch(Exception e){
			
			LOG.report_ERROR("Validate Data", e);
			
		}		
		
	}
	

	    
	    
	    
}
