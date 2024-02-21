package supportFiles;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;

public class LocatorTypes {
	
	//public static final Log log = LogFactory.getLog(TestListener.class);
	
	/**
	 * Provider of a string xpath value
	 * This has a little interface to support string values and string
	 * values created by functions like concat.
	 */
	public static interface XpathTextValue {
		String getString();
	}

	/**
	 * A raw string value, rendered as 'value'
	 */
	public static class XpathText implements XpathTextValue {
		private String v;
		
		public XpathText(String v){
			this.v = v;
		}
		
		@Override public String getString() {
			return "'" + (v==null?"":v) + "'";
		}
	}

	/**
	 * A string value that is actualy a function, like concat('v1', 'v2')
	 * These will be rendered as is.
	 * @author sevans
	 *
	 */
	public static class XpathTextViaFunction implements XpathTextValue {
		private String function;
		
		public XpathTextViaFunction(String function){
			this.function = function;
		}
		
		@Override public String getString() {
			return function;
		}
	}
	
	/**
	 * This can be used to escape single quotes using concat	
	 */
	public static XpathTextValue escapeTextValueOfXpath(String text) {
		if(text == null) return new XpathText(null);
		
		if(!text.contains("'")) return new XpathText(text);
		
		// use concat
		StringBuilder sb = new StringBuilder("concat(");
		int i = 0;
		int previ = 0;
		for(i = text.indexOf('\'', i); i != -1; i = text.indexOf('\'', i)) {
			if(previ != 0) sb.append(",");
			if(previ != i) {
				sb.append("'").append(text.substring(previ, i)).append("'");
				sb.append(",");
			}
			sb.append("\"'\"");
			i++;
			previ = i;
		}
		if(previ != 0 && previ < text.length()) {
			sb.append(",");
			sb.append("'").append(text.substring(previ)).append("'");
		}
		sb.append(")");
		return new XpathTextViaFunction(sb.toString());
	}

    /**
     * Select a span with the given text
     * @param text
     * @return
     */
    public static By spanText(String text){
        return By.xpath("//span[text()[.='" + text + "']]");
    }

    /**
     * Select a span with the given text inside a div that has the given id
     * //div[@id='divid']//span[text()[.='mytext']]
     * @param text
     * @return
     */
    public static By spanTextWithinDivId(String divId, String text){
        return By.xpath("//div[@id='" + divId + "']//span[text()[.='" + text + "']]");
    }

    /**
     * Select a span with the given text inside an element that has the given id
     * //*[@id='id']//span[text()[.='mytext']]
     * @param text
     * @return
     */
    public static By spanTextWithinId(String elementId, String text){
        return By.xpath("//*[@id='" + elementId + "']//span[text()[.='" + text + "']]");
    }
	
    	/**
        * Select a div with the given text inside an element that has the given id
        * //*[@id='id']//div[text()[.='mytext']]
        * @param text
        * @return
        */
       public static By divTextWithinId(String elementId, String text){
           return By.xpath("//*[@id='" + elementId + "']//div[text()[.='" + text + "']]");
       }

    public static By h1Text(String text){
        return By.xpath("//h1[text()[.='" + text + "']]");
    }

    public static By h1TextContains(String text){
        return By.xpath("//h1[text()[contains(.,'" + text + "')]]");
    }

    /**
     * Select a span with the given id
     * @param text
     * @return
     */
    public static By spanId(String id){
        return By.cssSelector("[id='"+id+"']");
    }
    /**
     * Select a span with the given text
     * @param text
     * @return
     */
    public static By h2Text(String text){
        return By.xpath("//h2[text()[.='" + text + "']]");
    }

    public static By h2TextContains(String text){
        return By.xpath("//h2[text()[contains(.,'" + text + "')]]");
    }

    /**
     * Select a span with the given text
     * @param text
     * @return
     */
    public static By spanTextContains(String text){
        return By.xpath("//span[text()[contains(.,'" + text + "')]]");
    }

    /**
     * Select a span containing a class with the given name
     * @param className
     * @return
     */
    public static By spanContainsClass(String className){
        return By.xpath("//span[contains(@class, '" + className + "')]");
    }

    /**
     * Select a div with the given text
     * @param text
     * @return
     */
    public static By divText(String text){
        return By.xpath("//div[text()[.='" + text + "']]");
    }

    /**
     * Select any element with the given text
     * @param text
     * @return
     */
    public static By anyText(String text){
        return By.xpath("//*[text()[.='" + text + "']]");
    }

    /**
     * Select the element with the given text value within the element with the given id
     * @param id The element id to look within
     * @param text The text to look for
     * @return By The locator
     */
    public static By anyTextWithinId(String id, String text){
        return By.xpath("//*[@id='" + id + "']//*[text()[.='" + text + "']]");
    }

    /**
     * A selector for a single element with the given id and the given title
     * @param id
     * @param title
     * @return
     */
    public static By byIdAndTitle(String id, String title){
        return By.xpath("//*[@id='" + id + "' and @title='" + title + "']");
    }


    /**
     * Select any element that contains the given text
     * @param text
     * @return
     */
    public static By anyTextContains(String text){
        return By.xpath("//*[contains(text(),'" + text + "')]");
    }

    /**
     * Select a div containing a class with the given name
     * @param className
     * @return
     */
    public static By divContainsClass(String className){
        return By.xpath("//div[contains(@class, '" + className + "')]");
    }

    /**
     * Select a div with the given text
     * @param text
     * @return
     */
    public static By idText(String id, String text){
        return By.xpath("//*[@id='" + id + "']/text()[.='" + text + "']]");
    }

    /**
     * Select any element within another element id that contains the given text
     * @param id The element id to look within
     * @param text The text to look for
     * @return By The locator
     */
    public static By containsTextWithinId(String id, String text){
        return By.xpath("//*[@id='" + id + "']//*[contains(text(),'" + text + "')]");
    }

    /**
     * Select any element within another element id that contains the given text
     * @param id The element id to look within
     * @param text The text to look for
     * @return By The locator
     */
    public static By textWithinId(String id, String text){
        return By.xpath("//*[@id='" + id + "']//*[text()[.='" + text + "']]");
      
    }

    /**
     * Select any element that contains the given text
     * @param id The element id to look within
     * @param text The text to look for
     * @return By The locator
     */
    public static By anyContainsText(String text){
        return By.xpath("//*[contains(text(),'" + text + "')]");
    }

    /**
     * Select a div with the given text
     * @param text
     * @return
     */
    public static By divTextContains(String text){
        return By.xpath("//div[text()[contains(.,'" + text + "')]]");
    }

    /**
     * Select a div with the given text
     * @param text
     * @return
     */
    public static By liTextContains(String text){
        return By.xpath("//li[text()[contains(.,'" + text + "')]]");
    }

    /**
     * Select a label with the given text
     * @param text
     * @return
     */
    public static By labelText(String text){
        return By.xpath("//label[text()[.='" + text + "']]");
    }

    /**
     * Select a label with the given text
     * //label[text()[contains(.,'mytext')]]
     * @param text
     * @return
     */
    public static By labelTextContains(String text){
        return By.xpath("//label[text()[contains(.,'" + text + "')]]");
    }

    /**
     * Select an anchor with the given text
     * //a[text()[.='mytext']]
     * @param text
     * @return
     */
    public static By anchorText(String text){
        return By.xpath("//a[text()[.='" + text + "']]");
    }

    /**
     * Select an anchor with the given text inside a div that has the given class
     * //div[contains(@class, 'myclass')]//a[text()[.='mytext']]
     * @param text
     * @return
     */
    public static By anchorTextWithinDivWithClass(String divClass, String text){
        return By.xpath("//div[contains(@class, '" + divClass + "')]//a[text()[.='" + text + "']]");
    }

    /**
     * Select an anchor with the given text inside any element that has the given class
     * //*[contains(@class, 'myclass')]//a[text()[.='mytext']]
     * @param text
     * @return
     */
    public static By anchorTextWithinAnyWithClass(String c, String text){
        return By.xpath("//*[contains(@class, '" + c + "')]//a[text()[.='" + text + "']]");
    }

    /**
     * Select an anchor with the given text inside any element that has the given class
     * //*[contains(@class, 'myclass')]//a[contains(text(),'mytext')]
     * @param text
     * @return
     */
    public static By anchorTextContainsWithinAnyWithClass(String c, String text){
        return By.xpath("//*[contains(@class, '" + c + "')]//a[contains(text(),'" + text + "')]");
    }

    /**
     * Select an anchor with the given text inside any element that has the given id
     * //*[@id='myid']//a[contains(text(),'mytext')]
     * @param text
     * @return
     */
    public static By anchorTextContainsWithinId(String elementId, String text){
        return By.xpath("//*[@id='" + elementId + "']//a[contains(text(),'" + text + "')]");
    }

    /**
     * Select an anchor with the given text inside a div that has the given id
     * //div[@id='myid']//a[text()[.='mytext']]
     * @param text
     * @return
     */
    public static By anchorTextWithinDivId(String divId, String text){
        return By.xpath("//div[@id='" + divId + "']//a[text()[.='" + text + "']]");
    }

    /**
     * Select an anchor with the given text inside a div that has the given id
     * //*[@id='myid']//a[text()[.='mytext']]
     * @param text
     * @return
     */
    public static By anchorTextWithinId(String elementId, String text){
        return By.xpath("//*[@id='" + elementId + "']//a[text()[.='" + text + "']]");
    }

    /**
     * FInd the element with the given text residing within an element that has the given css class
     * //*[contains(@class, 'myclass')]//*[text()[.='mytext']]
     * @param text
     * @return
     */
    public static By textWithinElementWithClass(String classname, String text){
        return By.xpath("//*[contains(@class, '" + classname + "')]//*[text()[.='" + text + "']]");
    }

    /**
     * Select an div with the given text inside a div with a given class
     * //div[contains(@class, 'myclass')]//div[text()[.='mytext']]
     * @param text
     * @return
     */
    public static By divTextWithinDivWithClass(String divClass, String text){
        return By.xpath("//div[contains(@class, '" + divClass + "')]//div[text()[.='" + text + "']]");
    }

    
    /**
     * Select an anchor that contains the given text
     * @param text
     * @return
     */
    public static By anchorTextContains(String text){
        return By.xpath("//a[text()[contains(.,'" + text + "')]]");
    }

    /**
     * Select an anchor that as a child span with the given text (EXT style link)
     *  //a/span[text()[.='mytext']]/..
     * @param text
     * @return
     */
    public static By anchorWithSpanText(String text){
        return By.xpath("//a/span[text()[.='" + text + "']]/..");
    }

    /**
     * Select an anchor that as a child span with the given text (EXT style link)
     * //div[contains(@class, 'myclass')]//a/span[text()[.='mytext']]/..
     * @param text
     * @return
     */
    public static By anchorWithSpanTextWithinDivWithClass(String divClass, String text){
        return By.xpath("//div[contains(@class, '" + divClass + "')]//a/span[text()[.='" + text + "']]/..");
    }

    /**
     * Select an anchor that as a child span with the given text (EXT style link)
     * //*[@id='myid')]//a/span[text()[.='mytext']]/..
     * @param text
     * @return
     */
    public static By anchorWithSpanTextWithinId(String elementId, String text){
        return By.xpath("//*[@id='" + elementId + "']//a/span[text()[.='" + text + "']]/..");
    }

    /**
     * Locate a main tab
     * @param name
     * @return
     */
    public static By tab(String name) {
       // return By.linkText(name);
        return By.xpath("//div/ul/li/a[text()[starts-with(.,'" + name + "')]]");
    }

    /**
     * traksmart style buttons that use <button ...><span ...>Save</span><span ..></span></button>
     * @param text
     * @return
     */
    public static By buttonWithSpanText(String text) {
        //return By.xpath("//button/span[text()[.='" + text + "']]/..");
        return By.xpath("(//button/span[text()[.='" + text + "']])[last()]");
    }
	
	
    /**
        * html button element within an id
        * //*[@id='myid']//button[text()[.='mytext']]
        * @param id The element id
        * @param text The text of the button element
        * @return
        */
       public static By buttonWithTextWithinId(String id, String text) {
           return By.xpath("//*[@id='" + id + "']//button[text()[.='" + text + "']]");
       }

    /**
     * traksmart style buttons that use the html pattern: <a ...><span ...><span ...><span ...>Save
     * This is specific probably to the ext version, but is in many places.
     * If methods use this method, then once place can be used to update all the buttons using this style.
     * @param text
     * @return
     */
    public static By traksmartButtonAnchorStyle(String text) {
        //return By.xpath("//button/span[text()[.='" + text + "']]/..");
        return By.xpath("(//a/span/span/span[text()[.='" + text + "']])/../../..");
    }

    /**
     * traksmart style buttons on a popup window that use the html pattern: <a ...><span ...><span ...><span ...>Save
     * @param text
     * @return
     */
    public static By traksmartButtonAnchorStyleOnPopup(String text) {
        //return By.xpath("//button/span[text()[.='" + text + "']]/..");
        return By.xpath("(//div[contains(@class, 'x-window')]//a/span/span/span[text()[.='" + text + "']])/../../..");
    }

    public static By traksmartButtonAnchorStyleOnPopupTitle(String popupTitle, String text) {
        return By.xpath("(//div[text()[.='" + popupTitle + "']]/../../../../..//a/span/span/span[text()[.='" + text + "']])/../../..");
    }

    /**
     * Match on an html input element that has the name attribute set
     * We specify a popup title so we only look within a popup (since there may be other elements on the page)
     * ie. <input name='aaa'>...</input>
     * @param popupTitle The popup title
     * @param inputValue The value attribute of the input element
     * @return By The locator 
     */
    public static By inputWithNameOnPopupTitle(String popupTitle, String inputName) {
        return By.xpath("//div[text()[.='" + popupTitle + "']]/../../../../..//input[@name='" + inputName + "']");
    }

    public static By inputWithNameOnPopupTitleParent(String popupTitle, String inputName) {
        return By.xpath("//div[text()[.='" + popupTitle + "']]/../../../../..//input[@name='" + inputName + "']/..");
    }

    /**
     * Match on an html input element that has the value attribute set
     * We specify a popup title so we only look within a popup (since there may be other elements on the page)
     * ie. <input value='aaa'>...</input>
     * @param popupTitle The popup title
     * @param inputValue The value attribute of the input element
     * @return By The locator 
     */
    public static By inputWithValueOnPopupTitle(String popupTitle, String inputValue) {
        return By.xpath("//div[text()[.='" + popupTitle + "']]/../../../../..//input[@value='" + inputValue + "']");
    }

    public static By divTextWithinPopupTitle(String popupTitle, String divText) {
        return By.xpath("//div[text()[.='" + popupTitle + "']]/../../../../..//div[text()[.='" + divText + "']]");
    }

    /**
     * A helper to find any text string within a popup title
     * The popup title is a way to locate the popup without needing the internal ids
     * This is EXT specific helper
     * @param popupTitle The popup title
     * @param text The text to find
     * @return By The Locator
     */
    public static By anyTextWithinPopupTitle(String popupTitle, String text) {
        return By.xpath("//div[text()[.='" + popupTitle + "']]/../../../../..//*[text()[.='" + text + "']]");
    }

    public static By spanTextWithinPopupTitle(String popupTitle, String spanText) {
        return By.xpath("//div[text()[.='" + popupTitle + "']]/../../../../..//span[text()[.='" + spanText + "']]");
    }

    public static By textWithinPopupTitle(String popupTitle, String text) {
        return By.xpath("//div[text()[.='" + popupTitle + "']]/../../../../..//*[text()[.='" + text + "']]");
    }

    /**
     * Match on an html textarea element that has the name attribute set within a popup.
     * We specify a popup title so we only look within a popup (since there may be other elements on the page)
     * ie. <textarea name='aaa'>...</input>
     * @param popupTitle The popup title
     * @param inputValue The name attribute of the input element
     * @return By The locator 
     */
    public static By textareaWithNameOnPopupTitle(String popupTitle, String textAreaName) {
        return By.xpath("//div[text()[.='" + popupTitle + "']]/../../../../..//textarea[@name='" + textAreaName + "']");
    }

    public static By inputWithinLabel(String label, String text) {
        return By.xpath("//span[text()[.='" + label + "']]/../..//input[@name='" + text + "']");
    }


    public static By traksmartButtonAnchorStyleOnPopupId(String popupId, String text) {
        return By.xpath("(//*[@id='" + popupId + "']//a/span/span/span[text()[.='" + text + "']])/../../..");
    }

    /**
     * Get an input given the name attribute (<input name='myname' ...)
     * @param name The value of the name attribute of the input element
     * @return By The locator
     */
    public static By inputWithName(String name) {
    	return By.xpath("//input[@name='" + name + "']");
    }


    /**
     * Get an input given the name attribute (<input value='Apply' ...)
     * @param value The value of the input element
     * @return By The locator
     */
    public static By inputWithValue(String value) {
    	return By.xpath("//input[@value='" + value + "']");
    }

    /**
     * Locator an input given a name, but only with an element with the given element id
     * eg to find elements within a section of the page (like a popup box)
     * @param id The element id to look within
     * @param name The input element name attribute value
     * @return The Locator
     */
    public static By inputWithNameWithinElementId(String id, String name) {
    	return By.xpath("//*[@id='" + id + "']//input[@name='" + name + "']");
    }

    /**
     * Locator for an element with a given id, but only with an element with the given element id
     * eg to find elements within a section of the page (like a popup box)
     * @param id The element id to look within
     * @param id The element id to find
     * @return The Locator
     */
    public static By anyWithIdWithinElementId(String id, String elementId) {
    	return By.xpath("//*[@id='" + id + "']//*[@id='" + elementId + "']");
    }

    /**
        * Locator an input given a name, but only with an element with the given element id
        * eg to find elements within a section of the page (like a popup box)
        * @param id The element id to look within
        * @param name The input element name attribute value
        * @return The Locator
        */
       public static By inputWithNameAndTypeWithinElementId(String id, String name, String type) {
       	return By.xpath("//*[@id='" + id + "']//input[@name='" + name + "' and @type='" + type + "']");
       }	

    public static By inputWithType(String type) {
        return By.xpath("//input[@type='" + type + "']");
    }

    /**
     * Get the parent element of input element given the name attribute (parent of <input name='myname' ...)
     * @param name The value of the name attribute of the input element
     * @return By The locator
     */
    public static By inputWithNameParent(String name) {
    	return By.xpath("//input[@name='" + name + "']/..");
    }

    /**
     * Get an input given the name attribute (<input name='myname' ...)
     * @param id The value of the name attribute of the input element
     * @return By The locator
     */
    public static By inputWithId(String id) {
    	return By.xpath("//input[@id='" + id + "']");
    }

    public static By inputWithRole(String role) {
        return By.xpath("//input[@role='" + role + "']");
    }

    /**
     * Get the parent element of input element given the name attribute (parent of <input name='myname' ...)
     * @param id The value of the name attribute of the input element
     * @return By The locator
     */
    public static By inputWithIdParent(String id) {
    	return By.xpath("//input[@id='" + id + "']/..");
    }

    /**
     * Find a textarea anywhere with the given name
     * Careful with a page that may contain multiple textareas with the same name attribute, the locator could match multiple web elements
     * @param name The name attribute value of the textarea element
     * @return By The Locator
     */
    public static By textareaWithName(String name) {
    	return By.xpath("//textarea[@name='" + name + "']");
    }

    /**
     * Get a textarea element given the name attribute, only looking within the given element id
     * eg find a textarea with the given name within a popup
     * @param id The id of an element to look within
     * @param name The name attribute value of a textarea element
     * @return By The Locator
     */
    public static By textareaWithNameWithinElementId(String id, String name) {
    	return By.xpath("//*[@id='" + id + "']//textarea[@name='" + name + "']");
    }

    public static By L_BUTTON_WITH_SAVE_SPAN_TEXT = buttonWithSpanText("Save");
    public static By L_BUTTON_WITH_OK_SPAN_TEXT = buttonWithSpanText("OK");
   public static By L_BUTTON_WITH_UPDATE_SPAN_TEXT = traksmartButtonAnchorStyle("Update");

    public static By L_BUTTON_WITH_SUBMIT_SPAN_TEXT = buttonWithSpanText("Submit");

    public static By L_TAB_DASHBOARD = tab("DASHBOARD");
    public static By L_TAB_PROGRAMS = tab("PROGRAMS");
    public static By L_TAB_PROJECTS = tab("PROJECTS");
    public static By L_TAB_MARKETING = tab("MARKETING");
    public static By L_TAB_PLANNING = tab("PLANNING");
    public static By L_TAB_ASSESSMENTS = tab("Assessments");
    public static By L_TAB_LIBRARIES = tab("LIBRARIES");
    public static By L_TAB_USERS = tab("USERS");
    public static By L_TAB_ADMIN = tab("ADMIN");
	public static By L_TAB_SCHEDULING = tab("SCHEDULING");
	public static By L_TAB_CUSTOMERS = tab("CUSTOMERS");
	public static By maintab = By.xpath("//ul[contains(@id,'mainNav')]");
    public static By tabslist = By.tagName("li");
    /**
     * Locate a library link under the LIBRARIES Tab
     * @param name
     * @return
     */
    public static By library(String name) {
        return By.xpath("//li[@id='tabLibraries']//a[text()[starts-with(.,'" + name + "')]]");
    }

    /**
     * Locate a library link under the LIBRARIES Tab
     * @param name
     * @return
     */
    public static By admin(String name) {
        return By.xpath("//li[@id='tabAdmin']//a[text()[starts-with(.,'" + name + "')]]");
    }

    /**
     * Locate a assessment link under the ASSESSMENTS Tab
     * @param name
     * @return
     */
    public static By assessment(String name) {
        return By.xpath("//li[@id='tabAssessment']//a[text()[starts-with(.,'" + name + "')]]");
    }
    
    /* public static By getLocatorData(String elementname, String sheetname) throws Exception{
    	By element = null;
    	LocatorTypes obj = new LocatorTypes();
    	ExcelUtils.setExcelFile(GlobalConstants.locatorexcelpath);
    	int totallocators = ExcelUtils.getRowCount(GlobalConstants.locatorswrksheet);
    	log.info("Total Locators row count is " + totallocators);
    	for (int i = 1; i <= totallocators;i++){
    		String ele = ExcelUtils.getCellData(i, GlobalConstants.col_elementname, sheetname);    		
    		if(ele.equals(elementname)){
    			String locatortype = ExcelUtils.getCellData(i, GlobalConstants.col_locatortype, sheetname);
        		String locators = ExcelUtils.getCellData(i, GlobalConstants.col_locators, sheetname);
        		log.info("Locator Type is " + locatortype );
        		log.info("Locator is " + locators);
        		if (locatortype.equals("cssSelector")){
        			element = By.cssSelector(locators);
        		}
        		if (locatortype.equals("xpath")){
        			element = By.xpath(locators);
        		}
        		if (locatortype.equals("linkText")){
        			element = By.linkText(locators);
        		}
        		if (locatortype.equals("id")){
        			element = By.id(locators);
        		}
        		if (locatortype.equals("className")){
        			element = By.className(locators);
        		}
        		if (locatortype.equals("name")){
        			element = By.name(locators);
        		}
        		if (locatortype.equals("tagName")){
        			element = By.tagName(locators);
        		}
        		if (locatortype.equals("partialLinkText")){
        			element = By.partialLinkText(locators);
        		}
    		}    		
    	}
		return element;    	
    }*/
  
}
