package supportFiles;

import com.google.common.base.Function;
import com.google.common.base.Predicate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.SkipException;

import objectRepository.Locator;
import supportFiles.LOG;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.math.BigDecimal;
import static org.testng.Assert.assertEquals;

public class SeleniumUtils {

    private static final Log log = LogFactory.getLog(SeleniumUtils.class);

    ////////////////////////////////
    ///// assert helpers
    //////////////////////////////

  
    public static void assertPageTitle(WebDriver driver, String expectedTitle, long seconds) {

        WebDriverWait wait = new WebDriverWait(driver, seconds);
        wait.until(ExpectedConditions.titleIs(expectedTitle));

        String title = driver.getTitle();
        Assert.assertEquals(title, expectedTitle, "we are on the wrong page: title is '" + driver.getTitle() + "' url '" + driver.getCurrentUrl() + "'");
    }

    public static void assertPageTitle(WebDriver driver, String expectedTitle) {
        assertPageTitle(driver, expectedTitle, 20);
    }

    /**
     * Assert an element does not exist
     * @param driver
     * @param by
     */
    public static void assertElementNotExists(WebDriver driver, By by){
        try {
            WebElement el = driver.findElement(by);
            if(el != null) Assert.assertTrue(false, "should not have found '" + by + "' on url '" + driver.getCurrentUrl() + "' title '" + driver.getTitle() + "'");
        } catch(NoSuchElementException ex){
            // good!
        }
    }

   

  }



