 package objectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


public class Locator {
	
	/**
	 * Provider of a string xpath value
	 * This has a little interface to support string values and string
	 * values created by functions like concat.
	 */
	public static interface XpathTextValue {
		String getString();
	}

	
	  /**
     * traksmart style buttons that use the html pattern: <a ...><span ...><span ...><span ...>Save
     * This is specific probably to the ext version, but is in many places.
     * If methods use this method, then once place can be used to update all the buttons using this style.
     * @param text
     * @return
     */
    public static By traksmartButtonAnchorStyleStartsWith(String text) {
        //return By.xpath("//button/span[text()[.='" + text + "']]/..");
        return By.xpath("(//a/span/span/span[text()[starts-with(.,'" + text + "')]])/../../..");
    }
    
    /**
     * Select an anchor that contains the given text
     * Useful in situations where the Text has leading or trailing spaces
     * //a[text()[.='mytext']]
     * @param text
     * @return
     */
    public static By containsAnchorText(String text){
        return By.xpath("//a[contains(text(),'"+text+"')]");
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
	 * A string value that is actually a function, like concat('v1', 'v2')
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
     * Select an anchor with the given text
     * //a[text()[.='mytext']]
     * @param text
     * @return
     */
    public static By listOfValues(String text){
        return By.xpath("//ul//li[text()='"+text+"']");
              
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
    
    public static By anchorClassContains(String anchorclass){
        return By.xpath("//a[contains(@class, '" + anchorclass + "')]");
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
        return By.xpath("//*[@id='mainNav']//a[text()[starts-with(.,'" + name + "')]]");
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

    public static By traksmartButtonAnchorStyleOnPopupWindowWithTitle(String popupTitle, String text) {
        //return By.xpath("//div[text()[.='" + popupTitle + "']]/../../../../..//a/span/span/span[text()[.='" + text + "']]");
    	return By.xpath("//div[text()[.='" + popupTitle + "']]/../../../../..//a/span/span/span[text()[.='" + text + "']]//ancestor::a");
    }

  
    public static By anchorWithClassname(String className){
        return By.xpath("//a[@class='" + className + "']");
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
    
    public static By inputIdContains(String id) {
    	return By.xpath("//input[contains(@id, '" + id + "')]");
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
    
    public static By validateProjectDataEnteringFieldName(String fieldname){
    	return By.xpath("//div[contains(text(),'"+fieldname+"')]/../div[2]"); 	
    	
    }

    public static By L_ADMIN_SYSTEM = admin("System");
    public static By L_ADMIN_USER = admin("Users");
    public static By L_LIBRARIES_EQUIPMENT = library("Equipment");
    public static By L_LIBRARIES_MEASURES = library("Measures");
    public static By L_LIBRARIES_PARTNERS = library("Partners");
    public static By L_LIBRARIES_SERVICE_PROVIDERS = library("Service Providers");
    public static By L_LIBRARIES_SURVEY = library("Survey");
    public static By L_LIBRARIES_CONTACT_MANAGEMENT = library("Contact Management");
    public static By L_LIBRARIES_DSM_COST_EFFECTIVENESS = library("DSM Cost Effectiveness");

    public static By AS_ASSESSMENTS_ONSITE_PROGRAMS = assessment("Onsite Programs");
    public static By AS_ASSESSMENTS_ASSESSMENTS = assessment("Assessments");
    public static By AS_ASSESSMENTS_FORMULAS = assessment("Formulas");

    public interface Text {
    	 public static final Long WAIT_TIME = (long) 10;
         public static final Long WAIT_TIME_1 = (long) 3;
         public static final Long POLLING_TIME = (long) 1;
         public static final String ERROR_PLEASE_FIX_THE_VALIDATION_ERRORS = "Please fix the validation errors!";

         public static final String FIRST_NAME = "Automation";
         public static final String LAST_NAME = "Test";
         public static final String ADDRESS1 = "1810 Gateway Dr";
         public static final String ADDRESS2 = "Suite 300";
         public static final String CITY = "San Mateo";
         public static final String COUNTRY = "United States of America";
         public static final String STATE = "CA";
         public static final String ZIP_CODE = "94404";
         public static final String DESCRIPTION = "Nexant iEnergy provides utilities with comprehensive business process management driving the effective rollout and management of an entire portfolio of energy efficiency, renewable energy and demand side management (DSM) programs";
         public static final String EMAIL = "anonymous@nexant.com";
         public static final String PHONE = "123-456-7890";
         public static final String WEBSITE = "http://www.nexant.com";
         public static final String L_SERVPROV_URL = "http://www.nexant.com";
         public static final String LATITUDE = "37.235";
         public static final String LONGITUDE = " -115.811111";
         public static final String PRIMARY_COUNTRY_CODE = "1";
         public static final String PRIMARY_AREA_CODE = "650";
         public static final String PRIMARY_PREFIX_CODE = "258";
         public static final String PRIMARY_SUFFIX_CODE = "2544";
         public static final String PRIMARY_EXT_CODE = "587";
         public static final String ALT_COUNTRY_CODE = "1";
         public static final String ALT_AREA_CODE = "147";
         public static final String ALT_PREFIX_CODE = "258";
         public static final String ALT_SUFFIX_CODE = "4456";
         public static final String ALT_EXT_CODE = "7895";
         public static final String COMPANY_NAME = "Nexant Inc";
         public static final String NAME = "Just automation name";
         public static final String STRING = "Just string from automation";
        // public static final String DATE_FROM = "08/22/2020";
         public static final String DATE_FROM = "01/01/2015";
         public static final String DATE_TO = "08/22/2030";
         public static final String DATE = "08/14/2016";
         public static final String SECTOR = "Mining";
         public static final String CONTACTEDBY = "QA";
         public static final String NOTE_PROGRAM_NAME = "!-meter tracking";
         public static final String NOTE_PROJECT_NAME = "!-meter tracking";
         public static final String NOTE_TYPE_OF_CONTACT = "Campaign";
         public static final String L_CONTACT_DATE = "08/14/2014";
         public static final String L_CONTACT_BY = "nmtest";
         public static final String L_CONTACT_NUMBER = "123456789";
         public static final String L_SERVPROV_BRANDKEY = "12345AUTO6789";
         public static final String L_SERVPROV_DISTRNUM = "234567";
         public static final String L_TYPE_CONTACT = "Campaign";
         public static final String L_SUBJECT = "Subject Test add";
         public static final String L_NOTE = "Add note test";
         public static final String L_PROGRAM_NOTE = "AZ Smarter Greener Better Commercial Rebates Program";
         public static final String L_Project = "";
         public static final String L_EMAIL_NOTE = "anonymous@nexant.com";
         public static final String L_EMAIL_TEXT = "test";
         public static final String L_FOLLOW_UP = "03/20/2015";



    }

    public interface Login {
    	public static final String LOGIN_PATH = ":8080/traksmart4/unprotected/login.do";
        public static final String LOGIN_TRADE_ALLY = ":8080/tradeally/unprotected/login.do";
        public static final String LOGIN_PATH_150_PROJECT = ":8080/traksmart4/projects/project_list.do";
        public static final By L_LOGIN = By.cssSelector("#j_username");
        public static final String LOGIN = "progmgr";
        public static final By L_PASSWORD = By.cssSelector("#j_password");
        public static final String PASSWORD = "Test.123";
        public static final By L_LOGIN_BTN = By.cssSelector("#frmLogin div.form_login input");
        public static final String LOGIN_PATH_150_USER = ":8080/traksmart4/users/user_list.do";
        public static final By pui_login = By.xpath("//input[@name='j_username']");
        public static final By pui_password = By.xpath("//input[@name='j_password']");
        public static final By pui_btn_login = By.xpath("//input[@value='Login']");
        public static final By pui_validation_msg = By.xpath("//div[@class='global-message-holder']");
    }

    public interface ServiceProvider {
		public static By L_Add_New_Contact = By
				.xpath("//input[@type='button' and @value='+ Add New Contact']");
		public static By L_drpdwn_statecontact = By
				.id("primaryContact-inputEl");
		public static By L_drpdwn_stateOfcontact = By.id("state_contact-inputEl");
		public static By L_country_contact = By.id("country_contact-inputEl");
		public static By L_postalCode_contact = By
				.id("postalCode_contact-inputEl");
		public static By L_primaryContact = By.id("primaryContact-inputEl");
		public static By L_first_name = By
				.xpath("//div/div/span[text()[.='First Name']]");
		public static By L_txt_first_name = By.id("firstName-textEl");
		public static By L_last_name = By
				.xpath("//div/div/span[text()[.='Last Name']]");
		public static By L_txt_last_name = By
				.xpath("//div/div/span[text()[.='Last Name']]");
		public static By L_email = By
				.xpath("//div/div/span[text()[.='Email']]");
		public static By L_primary = By
				.xpath("//div/div/span[text()[.='Primary']]");
		public static By L_Last_Update = By
				.xpath("//div/div/span[text()[.='Last Update']]");
    	 public static final By LSP_SORT_BY_NAME = By.xpath("//div/a/span[text()[contains(., 'Name')]]");
         public static final By LSP_SORT_BY_CITY = By.xpath("//div/a/span[text()[contains(., 'City')]]");
         public static final By LSP_SORT_BY_STATE = By.xpath("//div/a/span[text()[contains(., 'State')]]");
         public static final By LSP_SORT_BY_ACTIVE = By.xpath("//div/a/span[text()[contains(., 'Active')]]");
         public static final By LSP_SORT_BY_LAST_UPDATED = By.xpath("//div/a/span[text()[contains(., 'Last Updated')]]");
         public static final By L_ADD_SERVICE_PROVIDER = By.cssSelector("[value='+ Add Service Providers']");
         public static final By L_SERVICE_PROVIDER_NUMBER = By.cssSelector("#serviceProviderNum-inputEl");
         public static final By L_SERVICE_PROVIDER_NAME = By.cssSelector("#serviceProviderName-inputEl");
         public static final By L_ADDRESS_1 = By.cssSelector("#addressLine1_serviceProvider-inputEl");
         public static final By L_ADDRESS_2 = By.cssSelector("#addressLine2_serviceProvider-inputEl");
         public static final By L_CITY = By.cssSelector("#city_serviceProvider-inputEl");
         public static final By L_STATE = By.cssSelector("#state_serviceProvider-trigger-picker");
         public static final By L_STATE1 = By.cssSelector(".x-boundlist-item:nth-child(7)");
         public static final By L_COUNTRY = By.cssSelector("#country_serviceProvider-inputEl");
         public static final By L_ZIP = By.cssSelector("#postalCode_serviceProvider-inputEl");
         public static final By L_BRANDKEY = By.cssSelector("#brandKey-inputEl");
         public static final By L_DISTRICT_NUMBER = By.cssSelector("#districtNum-inputEl");
         public static final By L_URL = By.cssSelector("#url-inputEl");
         public static final By L_PHONE_COUNTRY = By.cssSelector("#textfield-1013-inputEl");
         public static final By L_PHONE_AREA = By.cssSelector("#textfield-1015-inputEl");
         public static final By L_PHONE_PREFIX = By.cssSelector("#textfield-1017-inputEl");
         public static final By L_PHONE_SUFFIX = By.cssSelector("#textfield-1019-inputEl");
         public static final By L_PHONE_EXT = By.cssSelector("#textfield-1021-inputEl");
         public static final By L_FAX_COUNTRY = By.cssSelector("#textfield-1023-inputEl");
         public static final By L_FAX_AREA = By.cssSelector("#textfield-1025-inputEl");
         public static final By L_FAX_PREFIX = By.cssSelector("#textfield-1027-inputEl");
         public static final By L_FAX_SUFFIX = By.cssSelector("#textfield-1029-inputEl");
         public static final By L_FAX_EXT = By.cssSelector("#faxExtension-inputEl");
         public static final By L_ACTIVE = By.cssSelector("#activeFlag-trigger-picker");
         public static final By L_ACTIVE1 = By.cssSelector(".x-boundlist-item:nth-child(1)");
         public static final By L_ACTIVE2 = By.cssSelector(".x-boundlist-item:nth-child(2)");
         public static final By L_DESCRIPTION = By.cssSelector("#description-inputEl");
         public static final By L_SAVE_BTN = traksmartButtonAnchorStyle("Save");
         public static final By L_SERVPROV_SAVEOK = By.cssSelector("#button-1005-btnIconEl");
         public static final By L_SERVPROV_ADCN = By.cssSelector("#service_add_new_content");
         public static final By L_SERVPROV_ADCN_CONTACT_NUMB = By.cssSelector("#contactNumber-inputEl");
         public static final By L_SERVPROV_ADCN_FIRSTNM = By.cssSelector("#contactFirstName-inputEl");
         public static final By L_SERVPROV_ADCN_LASTNM = By.cssSelector("#contactLastName-inputEl");
         public static final By L_SERVPROV_ADCN_EMAIL = By.cssSelector("#contactEmailAddress-inputEl");
         public static final By L_SERVPROV_ADCN_ADDR1 = By.cssSelector("#addressLine1_contact-inputEl");
         public static final By L_SERVPROV_ADCN_ADDR2 = By.cssSelector("#addressLine2_contact-inputEl");
         public static final By L_SERVPROV_ADCN_CITY = By.cssSelector("#city_contact-inputEl");
         public static final By L_SERVPROV_ADCN_STATE = By.cssSelector("#state_contact-inputEl");
         public static final By L_SERVPROV_ADCN_STATE1 = By.xpath("//*[@id=\"state_contact-trigger-picker\"]");
         public static final By L_SERVPROV_ADCN_STATE2 = By.cssSelector(".x-boundlist-item:nth-child(7)");
         public static final By L_SERVPROV_ADCN_COUNTRY = By.cssSelector("#country_contact-inputEl");
         public static final By L_SERVPROV_ADCN_ZIPCODE = By.cssSelector("#postalCode_contact-inputEl");
         public static final By L_SERVPROV_ADCN_PRIMCONT = By.cssSelector("#primaryContact-trigger-picker");
         public static final By L_SERVPROV_ADCN_PRIMCONT2 = By.xpath("//div/ul/li[text()[contains(., 'True')]]");
         public static final By L_SERVPROV_ADCN_SAVE = By.cssSelector("#addNewContact-save-btnIconEl");
         public static final By L_SERVPROV_ADCN_INNER_TXT = By.xpath("//*[@id=\"messagebox-1001-displayfield-inputEl\"]");
         public static By L_BUTTON_WITH_OK_TEXT = By.xpath("//*[@id=\"button-1005-btnIconEl\"]");
         public static By L_HEADER_SUCCESS = By.xpath("//div[7]/div[1]/div/div/div[1]/div");
    }

    public interface Customer {
    	//public static final By L_SEARCH = By.cssSelector("[name='paramSearch']");(Not working after build 1946
    	public static final By L_SEARCH = By.xpath(".//a[1][@alt='Delete']//preceding::div[1]//input");
    	 public static final By L_ADD_NEW_CUSTOMER = By.cssSelector("[value='+ Add New Customer']");
         public static final By L_CUSTOMER_NUMBER = inputWithName("customerNumber");
         public static final By L_ADDRESS_1 = inputWithName("addressLine1");
         public static final By L_ADDRESS_2 = inputWithName("addressLine2");
         public static final By L_FIRST_NAME = inputWithName("firstName");
         public static final By L_LAST_NAME = inputWithName("lastName");
         public static final By L_CITY = inputWithName("city");
         public static final By L_EMAIL_ADDRESS = inputWithName("emailAddress");
         public static final By L_COUNTRY = inputWithName("country");
         public static final By L_DESCRIPTION = textareaWithName("description");
         public static final By L_ZIP = inputWithName("postalCode");
         public static final By L_LATITUDE = inputWithName("latitude");
         public static final By L_LONGITUDE = inputWithName("longitude");
         public static final By L_CustomerSaveMessage = By.xpath(".//div[@class='global-message-holder' and text()='Customer save succeeded.']");
         public static final By L_PRIMARY_COUNTRY_CODE = inputWithName("phoneCountryCode");
         public static final By L_PRIMARY_AREA_CODE = inputWithName("phoneAreaCode");
         public static final By L_PRIMARY_PREFIX_CODE = inputWithName("phonePrefix");
         public static final By L_PRIMARY_SUFFIX_CODE = inputWithName("phoneSuffix");
         public static final By L_PRIMARY_EXT_CODE = inputWithName("phoneExtension");
         public static final By L_ALT_COUNTRY_CODE = inputWithName("altPhoneCountryCode");
         public static final By L_ALT_AREA_CODE = inputWithName("altPhoneAreaCode");
         public static final By L_ALT_PREFIX_CODE = inputWithName("altPhonePrefix");
         public static final By L_ALT_SUFFIX_CODE = inputWithName("altPhoneSuffix");
         public static final By L_ALT_EXT_CODE = inputWithName("altPhoneExtension");
         public static final By L_SUBMIT_BTN = Locator.traksmartButtonAnchorStyle("Save");
         public static final By L_STATE_DRPBX = By.cssSelector("#state_customer-inputEl");
         public static final By L_SECTOR = By.cssSelector("[name='sectorId']");
         public static final By L_SECTOR_ITEMS = By.cssSelector("[role='option']");
         public static final By L_COMPANY_NAME = inputWithName("customerName");

         public static final By L_CONTACT_DATE = By.xpath("//*[@id=\"contactDateString-inputEl\"]");
         public static final By L_CONTACT_BY = By.xpath("//*[@id=\"contactedBy-inputEl\"]");
         public static final By L_TYPE_CONTACT = By.xpath("//*[@id=\"noteTypeId-inputEl\"]");
         public static final By L_SUBJECT = By.xpath("//*[@id=\"subject-inputEl\"]");
         public static final By L_NOTE = By.xpath("//*[@id=\"notes-inputEl\"]");
         public static final By L_PROGRAM_NOTE = By.xpath("//*[@id=\"programId-inputEl\"]");
         public static final By L_Project = By.xpath("//*[@id=\"projectId-inputEl\"]");
         public static final By followUpRequired = By.xpath("//*[@id=\"followUpRequired-inputEl\"]");
         public static final By L_EMAIL_NOTE = By.xpath("//*[@id=\"followUpEmailAddress-inputEl\"]");
         public static final By L_EMAIL_TEXT = By.xpath("//*[@id=\"followUpEmailText-inputEl\"]");
         public static final By L_FOLLOW_UP = By.xpath("//*[@id=\"followUpDateString-inputEl\"]");
         public static final By L_SAVE_BUTTON = By.xpath("//*[@id=\"file_upload_button\"]");
         public static final By L_CANCELBUTTON = By.xpath("//*[\" value=\"Cancel\"]");
         public static final By L_SERVICE_PROVIDER = By.cssSelector("[name='serviceProviderId']");

         public static final By CREATE_PROJECT = By.linkText("Create Project");
         public static final By SELECT_PROGRAM = inputWithName("programId");

         // Appointments
         public static final By SORT_BY_NAME = By.xpath("//span[@class='x-column-header-text' and text()[.='Name']");
         
         //public static final By SORT_BY_NAME = By.xpath("//span[@class='x-column-header-text' and text()[.='Name']");

    }

    public interface Common {
    	 public static final By L_SEARCH_BY_FIELD = By.cssSelector("#keyword_type_filter-inputEl");
         public static final By L_SEARCH_BY_FIELD_MEASURE = By.cssSelector("#keyword_search_textfield-inputEl");
         public static final By L_SEARCH_BY_FIELD_PARTNER = By.cssSelector("#keyword_search_textfield-inputEl");
         public static final By L_SEARCH_BY_FIELD_DSM = By.cssSelector("#keyword_search_textfield-inputEl");
         public static final By L_SEARCH_BY_FIELD_PROGRAM = By.cssSelector("#keyword_search_textfield-inputEl");
         public static final By L_SEARCH_BY_FIELD_USER = By.cssSelector("#keyword_search_textfield-inputEl");
         public static final By L_SEARCH_FIELD = By.cssSelector("#keyword_search_textfield-inputEl");
    }

    public interface Bill_Account {
    	 public static final By L_ADD_BILL_ACCOUNT_BTN = By.cssSelector("[value='+ Bill Account']");
         public static final By L_ADD_BILL_ACCOUNT_NUMBER = By.cssSelector("#accountNumber-inputEl");
         public static final By L_ADD_BILL_ACCOUNT_NAME = By.cssSelector("#accountName-inputEl");
         public static final By L_ADD_BILL_ACCOUNT_DESCRIPTION = By.cssSelector("#billAcct_description-inputEl");
         public static final By L_ADD_BILL_ACCOUNT_ADDRESS1 = By.cssSelector("#addressLine1_billAccount-inputEl");
         public static final By L_ADD_BILL_ACCOUNT_ADDRESS2 = By.cssSelector("#addressLine2_billAccount-inputEl");
         public static final By L_ADD_BILL_ACCOUNT_CITY = By.cssSelector("#city_billAccount-inputEl");
         public static final By L_ADD_BILL_ACCOUNT_COUNTRY = By.cssSelector("#country_billAccount-inputEl");
         public static final By L_ADD_BILL_ACCOUNT_ZIP = By.cssSelector("#postalCode_billAccount-inputEl");
         public static final By L_ADD_BILL_ACCOUNT_LATITUDE = By.cssSelector("#latitude_billAccount-inputEl");
         public static final By L_ADD_BILL_ACCOUNT_LONGITUDE = By.cssSelector("#longitude_billAccount-inputEl");
         public static final By L_ADD_BILL_ACCOUNT_STATE = By.cssSelector("#state_billAccount-inputEl");
         public static final By L_ADD_BILL_ACCOUNT_SAVE_BTN = By.cssSelector("#bill_account_save_button");
         public static final By L_ADD_BILL_ACCOUNT_CANCEL_BTN = By.xpath("(//div/input[@value='Cancel'])[2]");
         public static final By L_ADD_BILL_ACCOUNT_ACTIVE_INDICATOR_FIELD = By.cssSelector("[name='activeFlag']");
         public static final By L_ADD_BILL_ACCOUNT_REVENUE_CLASS = By.xpath("//input[@name='revenueId']");
         public static final By L_ADD_BILL_ACCOUNT_ACTIVATION_DATE = By.cssSelector("#accountStartDateString-inputEl");
         public static final By L_ADD_BILL_ACCOUNT_TERMINATION_DATE = By.cssSelector("#accountEndDateString-inputEl");
    }

    public interface Note {
    	public static final By L_ADD_NOTE_LINK = Locator.spanId("Notes-btnIconEl");
		public static final By L_ADD_CUSTOMERNOTE_BTN = By.xpath("//input[@value='+ Add Customer Note']"); 
    	public static final By L_ADD_CUTOMER_NOTE_HEADER = By.id("notesHeader");
        public static final By L_ADD_NOTE_BTN = By.xpath("//input[@value='+ Add Customer Note']"); // //input[@name='']
        public static final By L_ADD_DATE = By.xpath("//input[@componentid='contactDateString']");
        public static final By L_ADD_NOTE_CONTACTED_BY = By.xpath("//input[@componentid='contactedBy']");
        public static final By L_ADD_NOTE_SUBJECT = By.xpath("//input[@componentid=\"subject\"]");
        public static final By L_ADD_NOTE = By.xpath("//textarea[@componentid='notes']");
        public static final By L_ADD_NOTE_TYPE_OF_CONTACT = By.xpath("//input[@id='noteTypeId-inputEl']");
        public static final By L_ADD_NOTE_PROGRAM = By.xpath("//input[@componentid='programId']");
        public static final By L_ADD_NOTE_PROJECT = By.xpath("//input[@componentid='projectId']");
        public static final By L_ADD_NOTE_FOLLOW_UP = By.xpath("//input[@componentid='followUpRequired']");
        public static final By L_ADD_NOTE_EMAIL = By.xpath("//input[@componentid='followUpEmailAddress']");
        public static final By L_ADD_NOTE_EMAIL_TEXT = By.xpath("//textarea[@componentid='followUpEmailText']");
        public static final By L_ADD_NOTE_FOLLOW_UP_DATE = By.xpath("//input[@componentid='followUpDateString']");
        public static final By L_ADD_NOTE_SAVE_BTN = By.xpath("//input[@id='file_upload_button']");
        public static final By L_ADD_NOTE_CANCEL_BTN = By.xpath("(//div/input[@value='Cancel'])[last()]");
        public static final By L_ADD_DATE_OF_CONTACT_CSS = By.xpath("#contactDateString-triggerWrap>tbody>tr>td:nth-child(2)>div");
        public static final By L_ADD_DATE_FROM_CALENDER_CSS = By.xpath(".x-datepicker-inner>tbody>tr:nth-child(4)>td:nth-child(7)>a>em>span");
        //public static final By L_ADD_TYPE_OF_CONTACT_VALUE_XPATH = By.xpath("//div[17]/div/ul/li[1]");

        public static final By L_ADD_SITE_NOTE_BTN = By.xpath("//input[@value='+ Add Site Note']"); 
		public static final By L_Select_Future_Date = By
				.xpath("//div[@id='followUpDateString-trigger-picker']");
		public static final By L_Subject = By
				.xpath("//a/span[text()[.='Subject']]");
		public static final By L_NoteType = By
				.xpath("//a/span[text()[.='Note Type']]");
		public static final By L_ContactBy = By
				.xpath("//a/span[text()[.='Contact By']]");
		public static final By L_ContactDate = By
				.xpath("//a/span[text()[.='Contact Date']]");
    }

	public interface CustomerUsage {
		public static final By L_CUSTOMER_USAGE_LINK = Locator.traksmartButtonAnchorStyle("Usage");
	}

    public interface Contact {
		public static final By L_ADD_CONTACT_BTN = By
				.xpath("//input[@value='+ Add Contact']");
		public static final By L_SET_CONTACT_AS_PRIMARY = By
				.xpath("//input[@id='primaryContact-inputEl']");
		public static final By L_ADD_CONTACT_NUMBER = By
				.xpath("//*[@name='contactNumber']");
		public static final By L_ADD_CONTACT_FIRST_NAME = By
				.xpath("//*[@componentid='contactFirstName']");
		public static final By L_ADD_CONTACT_LAST_NAME = By
				.xpath("//*[@componentid='contactLastName']");
		public static final By L_ADD_CONTACT_TITLE = By
				.xpath("//*[@componentid='title_contact']");
		public static final By L_ADD_CONTACT_EMAIL = By
				.xpath("//*[@componentid='contactEmailAddress']");
		public static final By L_ADD_CONTACT_PHONE_COUNTRY_CODE = By
				.xpath("//*[@name='phCntryCode']");
		public static final By L_ADD_CONTACT_PHONE_AREA_CODE = By
				.xpath("//*[@name='phAreaCode']");
		public static final By L_ADD_CONTACT_PHONE_PREFIX_CODE = By
				.xpath("//*[@name='phPrefix']");
		public static final By L_ADD_CONTACT_PHONE_SUFFIX_CODE = By
				.xpath("//*[@name='phSuffix']");
		public static final By L_ADD_CONTACT_PHONE_EXT_CODE = By
				.xpath("//*[@name='phExt']");
		public static final By L_ADD_CONTACT_ADDRESS1 = By
				.xpath("//*[@componentid='addressLine1_contact']");
		public static final By L_ADD_CONTACT_ADDRESS2 = By
				.xpath("//*[@componentid='addressLine2_contact']");
		public static final By L_ADD_CONTACT_CITY = By
				.xpath("//*[@componentid='city_contact']");
		public static final By L_ADD_CONTACT_COUNTRY = By
				.xpath("//*[@componentid='country_contact']");
		public static final By L_ADD_CONTACT_POSTAL_CODE = By
				.xpath("//*[@componentid='postalCode_contact']");
		public static final By L_ADD_CONTACT_COMMENTS = By
				.xpath("//*[@componentid='comments']");
		public static final By L_ADD_CONTACT_STATE = By
				.xpath("//*[@componentid='state_contact']");
		// /html/body/div[11]/div[2]/div[2]/div/div/div/div[10]/div/div/a[1]/span/span/span[1]
		public static final By L_ADD_CONTACT_SAVE_BTN = By
				.xpath("//div[10]/div/div/a/span/span/span");
		public static final By L_ADD_CONTACT_CANCEL_BTN = By
				.xpath("//a/span/span/span[text()[contains(.,'Cancel')]]");
		// title of pop up
		public static final By L_CONTACT_POPUP_HEADER = By
				.id("contact_popup_header_hd-textEl");
    }

    public interface ContactManagement {
    	public static final By L_CONTACT_SEARCH = By
				.cssSelector("#keyword_1_field-inputEl");
		public static final By L_ADD_CONTACT_BTN = By
				.cssSelector("[value= '+ Add Contact']");
		public static final By L_SET_CONTACT_COMPANY_NAME = By
				.xpath("//input[@name='companyName']");
		public static final By L_ADD_CONTACT_FIRST_NAME = By
				.xpath("//input[@name='firstName']");
		public static final By L_ADD_CONTACT_LAST_NAME = By
				.xpath("//input[@name='lastName']");
		public static final By L_ADD_CONTACT_TITLE = By
				.xpath("//input[@name='role']");
		public static final By L_ADD_CONTACT_EMAIL = By
				.xpath("//input[@name='email']");
		public static final By L_ADD_CONTACT_SECONDARY_EMAIL = By
				.xpath("//input[@name='secondaryEmail']");
		public static final By L_ADD_CONTACT_PHONE_COUNTRY_CODE = By
				.xpath("//input[@name='phCntryCode']");
		public static final By L_ADD_CONTACT_PHONE_AREA_CODE = By
				.xpath("//input[@name='phAreaCode']");
		public static final By L_ADD_CONTACT_PHONE_PREFIX_CODE = By
				.xpath("//input[@name='phPrefix']");
		public static final By L_ADD_CONTACT_PHONE_SUFFIX_CODE = By
				.xpath("//input[@name='phSuffix']");
		public static final By L_ADD_CONTACT_PHONE_EXT_CODE = By
				.xpath("//input[@name='phExt']");
		public static final By L_ADD_CONTACT_ADDRESS1 = By
				.xpath("//input[@name='address1']");
		public static final By L_ADD_CONTACT_ADDRESS2 = By
				.xpath("//input[@name='address2']");
		public static final By L_ADD_CONTACT_CITY = By
				.xpath("//input[@name='city']");
		public static final By L_ADD_CONTACT_COUNTRY = By
				.xpath("//input[@name='country']");
		public static final By L_ADD_CONTACT_POSTAL_CODE = By
				.xpath("//input[@name='postalCode']");
		public static final By L_ADD_CONTACT_STATE = By
				.xpath("//input[@name='state']");
		public static final By L_ADD_NOTE_BTN = By
				.cssSelector("[value='+ Add Note']");
		public static final By L_ADD_CONTACT_SAVE_BTN = By
				.cssSelector("[value=Save]");

		// added on 09/27/2016 to get all Xpaths from
		// TestAddNewCOntactManagement.java by Paedru Fernando
		public static final By Last_Updated = By
				.xpath("//a/span[text()[.='Last Updated']]");
		public static final By Company_Name = By
				.xpath("//a/span[text()[.='Company Name']]");
		public static final By Email = By.xpath("//a/span[text()[.='Email']]");
		public static final By Last_Name = By
				.xpath("//a/span[text()[.='Last Name']]");
		public static final By New_Contact_Label = By
				.xpath("//label[text()[contains(., 'New Contact')]]");

		// the id of the popup for add note on the contact management page. This
		// is the popup div that will hide and show.
		public static final By L_ADD_NOTE_POPUP = By
				.id("add_note_popup_header-body");

		// Reminder
		public static final By L_CONTACT_M_REMINDER = By
				.xpath("//a[text()[.='Reminder']]");
		public static final By REMINDER_SORT_BY_REMINDER_DATE = By
				.xpath("//div/div/span[text()[.='Reminder Date']]");
		public static final By REMINDER_SORT_BY_NAME = By
				.xpath("//div/div/span[text()[.='Name']]");
		public static final By REMINDER_SORT_BY_ENTERED_BY = By
				.xpath("//div/div/span[text()[.='Entered By']]");
		public static final By REMINDER_SORT_BY_DATE_ENTERED = By
				.xpath("//div/div/span[text()[.='Date Entered']]");
		public static final By REMINDER_SORT_BY_EMAIL = By
				.xpath("//div/div/span[text()[.='Email Recipient']]");
		public static final By REMINDER_SORT_BY_STATUS = By
				.xpath("//div/div/span[text()[.='Status']]");
		public static final By REMINDER_SORT_BY_DELETE = By
				.xpath("//div/div/span[text()[.='Delete']]");

    }

    public interface User {
    	public static final By L_CLICK_ADD_NEW_USER = By
				.cssSelector("#measure_library_top_button_left");
		public static final By L_ADD_USER_NAME = By
				.cssSelector("#userName-inputEl");
		public static final By L_ADD_FIRST_NAME = By
				.cssSelector("#firstName-inputEl");
		public static final By L_ADD_LAST_NAME = By
				.cssSelector("#lastName-inputEl");
		public static final By L_PASSWORD = By
				.cssSelector("#encryptedPassword-inputEl");
		public static final By L_CONFIRM_PASSWORD = By
				.cssSelector("#password_confirm-inputEl");
		public static final By L_SECRET_QUESTION = By
				.cssSelector("#securityQuestion-inputEl");
		public static final By L_SECRET_QUESTION_VALUE = By
				.cssSelector(".x-boundlist-item:nth-child(2)");
		public static final By L_SECRET_ANSWER = By
				.cssSelector("#securityAnswer-inputEl");
		public static final By L_EMAIL = By
				.cssSelector("#emailAddress-inputEl");
		public static final By L_SAVE = By.cssSelector("#saveUser");

    }
    
    

    public interface DSM_CE {
    	public static final By L_NAME = By.cssSelector("[name='loadShapeName']");
		public static final By L_IS_SYSTEM = By.xpath("//input[@role='checkbox']");
		public static final By L_DESCRIPTION = By.cssSelector("[name='description']");
		public static final By L_TYPE = By.cssSelector("[name='measureType']");
		public static final By L_SECTOR = By.cssSelector("[name='sector']");
		public static final By L_SUBTYPE = By.cssSelector("[name='measureSubType']");
		public static final By L_CLIMATE_ZONE = By.cssSelector("[name='climateZone']");
		public static final By L_ADD_SHAPE = By.cssSelector("[value='+ Add New Shape']");
		public static final By L_ADD_SHAPE_ID = By.cssSelector("#measure_library_top_button_right");
		public static final By LCE_SORT_BY_NAME = By.xpath("//div/a/span[text()[contains(., 'Name')]]");
		public static final By LCE_SORT_BY_LAST_UPDATED = By.xpath("//div/a/span[text()[contains(., 'Last Updated')]]");
		public static final By LCE_SORT_BY_START_YEAR = By.xpath("//div/a/span[text()[contains(., 'Start Year')]]");
		public static final By LCE_SHAPES = By.xpath("//div/ul/li/a[text()[contains(., 'Shapes')]]");
		public static final By LCE_COSTS = By.xpath("//div/ul/li/a[text()[contains(., 'Costs')]]");
		public static final By LCE_RATES = By.xpath("//div/ul/li/a[text()[contains(., 'Rates')]]");
		public static final By LCE_OTHER = By.xpath("//div/ul/li/a[text()[contains(., 'Other')]]");
		public static final By LCE_CUSTOMERS = By.xpath("//div/ul/li/a[text()[contains(., 'Customers')]]");
		public static final By LCE_Libraries = By.xpath(".//*[@id='libraries_flyout']/div/div/a[7]");
		public static final By LCE_Sort_Name = By.xpath("//a/span[text()[.='Name']]");
		public static final By LCE_Sort_LastUpdate = By.xpath("//a/span[text()[.='Last Updated']]");
		public static final By LCE_Measure_Lib = By.id("measure_library_top_button_right");
		public static final By LCE_ShapeFile =  By.xpath("//input[@name='shapeFile']");
		public static final By LCE_Msg_Btn_Ok = By.xpath("//div[contains(@class,'main')]/../div[contains(@id,'messagebox')]/div[3]//span[contains(text(),'OK')]");
		public static final By LCE_MarginalCostName = By.xpath("//input[@name='marginalCostName']");
		public static final By LCE_MarginalRateName = By.xpath("//input[@name='marginalRateName']");
		public static final By LCE_RateFile = By.xpath("//input[@name='rateFile']");
		public static final By LCE_textbox_name = By.xpath("//input[@name='inputName']");
		public static final By LCE_Customers = By.linkText("Customers");
		public static final By LCE_textbox_forecastname = By.xpath("//input[@name='planningCustForecastLibName']");
		public static final By LCE_textbox_forecastfile = By.xpath("//input[@name='forecastFile']");
		public static final By LCE_Survey = By.id("measure_library_top_button_right");
		public static final By editedcostname = By.xpath("//div[@id='editMarginalCostDetailsForm-body']//tr[1]/td[1]//div[@role='textbox']");
		public static final By LCE_Search_TextBox = By.xpath(".//input[@id='keyword_search_textfield-inputEl']");
        public static final By LCE_Search_Results_ShapeNames = By.xpath(".//table//tbody//div//span[@class='pg_blk_links pg_name']//a");
        public static final By editedratename = By.xpath("//div[@id='editRateDetailsForm-body']//tr[1]/td[1]//div[@role='textbox']");
        public static final By LCE_Period_Save = By.xpath(".//div[contains(@id,'editcostingperiod')]//span[text()='Save']");
        public static final By LCE_Period_Cancel = By.xpath(".//a[contains(@class,'cancelButton')]");
        public static final By LCE_Manage_periods = By.xpath(".//div[@class='top_button_right_active']//input[@value='+ Manage Periods']");
        public static final By LCE_Add_New_Period = By.xpath(".//span[text()='+ Add New']");
        public static final By LCE_Period_Name = By.xpath(".//input[@name='costingPeriodName']");
        public static final By editedforecastname = By.xpath("//div[@id='editForecastDetailsForm-body']//tr[1]/td[1]//div[@role='textbox']");
        public static final By lastupdateddates = By.xpath("//span[@class='pg_blk_links pg_update']");
        public static final By shapesnames = By.xpath("//span[@class='pg_blk_links pg_name']/a");
        public static final By startyear = By.xpath("//div[contains(text(),'Start Year')]/../div[2]");
        public static final By LCE_templateFile =  By.xpath(".//input[@role='button' and @type='file']");
        

    }

    public interface Measure {
    	
    	
    	public static final By sortBy_Last_Updated = By.xpath("//a/span[text()[.='Last Updated']]");
    	public static final By sortBy_SubType = By.xpath("//a/span[text()[.='Sub-type']]");
    	public static final By sortBy_Type = By.xpath("//a/span[text()[.='Type']]");
    	public static final By sortBy_Category = By.xpath("//a/span[text()[.='Category']]");
    	public static final By L_CATEGORYCOUNTER = By.cssSelector("#categoryListComp_countLabel");
		public static final By L_TYPECOUNTER = By.cssSelector("#typeListComp_countLabel");
		public static final By L_SUBTYPECOUNTER = By.cssSelector("#subTypeListComp_countLabel");
		public static final By ADD_NEW_MESAURE_CATEGORY_POPUP_LOCATOR = Locator.divText("Add new Measure Category");
		public static final By ADD_NEW_MESAURE_TYPE_POPUP_LOCATOR = Locator.divText("Add new Measure Type");
		public static final By ADD_NEW_MESAURE_SUBTYPE_POPUP_LOCATOR = Locator.divText("Add new Measure SubType");
		public static final By ADD_NEW_TRACKING_FIELD = Locator.spanTextContains("+ Add");
		public static final By TRACKING_FIELD = Locator.spanTextContains("TRACKING FIELDS");
		public static final By Visisble_Measure_Panel = By.xpath("//div[@id='manageMeasure-body']/div"); 
		public static final By category_list = By.xpath("//div[@id='categoryListComp']//div[contains(@class, 'x-mask')]");
		//public static final By category_list_multiselect = By.xpath("//div[contains(@id,'categoryListComp_multiselect-innerCt')]//ul/li[contains(text(),'" + TestMeasureSavings.categoryname + "' )]");
		public static final By typelistcomp =  By.xpath("//div[@id='typeListComp']//div[contains(@class, 'x-mask')]");
		//public static final By catagorytype = By.xpath("(//div/ul/li[text()[starts-with(.,'" + TestMeasureSavings.categorytypename + "')]])");
		public static final By categorytype_list =  By.xpath("//div[@id='subTypeListComp']//div[contains(@class, 'x-mask')]");
		//public static final By catagorysubtype = By.xpath("(//div/ul/li[text()[starts-with(.,'" + TestMeasureSavings.categorysubtypename + "')]])");
		public static final By trackingfield = Locator.spanText("TRACKING FIELDS");
		public static final By btn_trackingfield =By.id("trackFieldButton");
		public static final By link_kwhsavings = By.xpath("//div[contains(@class, 'x-window')]//table//tr/td/div[contains(text(),'kWh Savings')]");
		public static final By link_kwhinstalled = By.xpath("//div[contains(@class, 'x-window')]//table//tr/td/div[contains(text(),'kW Installed')]");
		public static final By link_thermsavings = By.xpath("//div[contains(@class, 'x-window')]//table//tr/td/div[contains(text(),'Therm Savings')]");
		public static final By link_watersavings = By.xpath("//div[contains(@class, 'x-window')]//table//tr/td/div[contains(text(),'Therm Savings')]");
		public static final By btn_save = Locator.traksmartButtonAnchorStyleOnPopupTitle("Available Tracking Fields", "Save");	
		public static final By measuretrackingfield_kwhsavings = By.xpath(".//*[@id='measureTrackingFields']//table//div[contains(text(),'kWhSavings')]");
		public static final By measuretrackingfield_kWInstalled = By.xpath(".//*[@id='measureTrackingFields']//table//div[contains(text(),'kWInstalled')]");
		public static final By measuretrackingfield_ThermSavings = By.xpath(".//*[@id='measureTrackingFields']//table//div[contains(text(),'ThermSavings')]");
		public static final By measuretrackingfield_WaterSavings = By.xpath(".//*[@id='measureTrackingFields']//table//div[contains(text(),'WaterSavings')]");
		public static final By attributes = Locator.spanText("ATTRIBUTES");
		public static final By btn_attributes = By.id("attributeButton-btnIconEl");
		public static final By window_kwsavings = By.xpath("//div[contains(@class, 'x-window')]//table//tr/td/div[contains(text(),'KW Savings')]");
		public static final By btn_attributesavings = Locator.traksmartButtonAnchorStyleOnPopupTitle("Available Attributes", "Save");
		public static final By measureattri_kwsavings = By.xpath(".//*[@id='measureAttributes']//div[contains(text(),'KWSavings')]");
		public static final By measuregrid = By.xpath("//div[contains(@class,'x-grid-cell-inner')]");
		public static final By measure_btn_addmeasure = By.xpath("//div[contains(@class,'x-panel-body x-panel-body-default x-table-layout-ct x-panel-body-default')]//table[1]//tr[1]/td[2]//a[1]");
		public static final By measure_btn_collapse = By.xpath("//*[@class='x-table-layout']//tr//td[@class='x-table-layout-cell ']//div[@class='x-tool x-box-item x-tool-default collapse_tool x-tool-after-title']//preceding-sibling::*[1]");
		public static final By measure_sectionheadertool = By.xpath("//*[@class='x-table-layout']//tr[2]//td[@class='x-table-layout-cell ']//span//ancestor::a[@class='x-btn sectionHeaderTool copy_without_data x-unselectable x-box-item x-btn-default-small x-btn-after-title']");
		public static final By measure_Last_Updated = By.xpath("//a/span[text()[.='Last Updated']]");
		public static final By measure_SubType = By.xpath("//a/span[text()[.='Sub-type']]");
		public static final By measure_Type = By.xpath("//a/span[text()[.='Type']]");
		public static final By measure_Category = By.xpath("//a/span[text()[.='Category']]");
		public static final By measure_Status = By.xpath("//div[contains(@class,'main')]/..//input[contains(@name,'statusId') and contains(@componentid,'combo')]");
		public static final By measure_boundlist = By.xpath("//div[contains(@class,'main')]/../div[contains(@id,'boundlist')]//li[1]");
		public static final By measure_MngTaxanomy_BtnCancel = By.xpath("//div[contains(@id,'addTaxonomyElement')]//div[contains(@id,'toolbar-')]//span[contains(text(),'Cancel')]");
		public static final By measure_Category_List = By.xpath("//div[@id='categoryListComp']//div[contains(@class, 'x-mask')]");
		public static final By measure_trackingfield_Btn_Add = By.id("trackFieldButton-btnIconEl");
		public static final By measure_gridcontainer = By.xpath("//div[contains(@class, 'x-window')]//table[contains(@class, 'x-grid-item')]/tbody/tr/td[2]/div");
		public static final By measure_gridcontainer_AHRI = By.xpath("//div[contains(@class, 'x-window')]//table[contains(@class, 'x-grid-item')]/tbody/tr/td[2]/div[text()='AHRI #']");
		public static final By measure_txtbox_name = By.xpath("//input[@name='name']");
		public static final By measure_txtbox_desc = By.xpath(".//*[@id='textarea-1036-inputEl']");
		public static final By measure_status = By.name("measureDefinitionStatusId");
		public static final By measure_startdate = By.id("effectiveStartDate-inputEl");
		public static final By measure_enddate = By.id("effectiveEndDate-inputEl");
		public static final By measure_drpdwn_categoryname = By.id("categoryCombo-inputEl");
		public static final By measure_drpdwn_categorytypename = By.id("typesCombo-inputEl");
		public static final By measure_drpdwn_categorysubtypename = By.id("subTypesCombo-inputEl");
		public static final By measure_title = By.xpath(".//*[@id='title']/div/div/span[3]/a");
		public static final By measure_label_measure = By.xpath(".//*[@id='title']/div/div//span/a[contains(text(),'Measures')]");
		public static final By measure_drpdwn_status = By.xpath("//input[@name='measureDefinitionStatusId']");
		public static final By measure_drpdwn_categoryname1 = By.xpath("//input[@name='measureCategoryId']");
		public static final By measure_drpdwn_categorytype = By.xpath("//input[@name='measTypeId']");
		public static final By measure_drpdwn_categorysubtype = By.xpath("//input[@name='measSubTypeId']");
		public static final By measure_MngMeasure_BtnCancel = By.xpath("//div[contains(@id,'manageMeasure-body')]//span[contains(text(),'Cancel')]");
		public static final By measure_measureelegridrowexpander = By.xpath("//div[contains(@id,'measureElements-body')]/div[2]/div[2]//table//tr//div[contains(@class,'x-grid-row-expander')]");
		public static final By measure_measureelegridrowexpander1 = By.cssSelector(".x-grid-row-expander");
		public static final By measureprogassopg_txtbox_measurename = By.id("measure_name1_field-inputEl");
		public static final By savings_kwh = By.xpath("//div[contains(@class, 'x-window')]//table//tr/td/div[contains(text(),'kWh Savings')]");
		public static final By savings_kw_installed = By.xpath("//div[contains(@class, 'x-window')]//table//tr/td/div[contains(text(),'kW Installed')]");
		public static final By savings_therm = By.xpath("//div[contains(@class, 'x-window')]//table//tr/td/div[contains(text(),'Therm Savings')]");
		public static final By savings_water = By.xpath("//div[contains(@class, 'x-window')]//table//tr/td/div[contains(text(),'Water Savings (Gal)')]");
		public static final By textboxsearchvalue = By.xpath("//input[@name='paramSearch' and @role='textbox']");
		public static final By dropdownsearchmeasurevalue = By.xpath("//input[@name='paramSearchCombo' and @role='combobox']");
		public static final By textboxsearchmeasurevalue = By.xpath("//div[contains(@id,'parameter_search_container')]//input[@role='textbox']");
		public static final By measuresearchrecords = By.xpath("//table");
		public static final By measuresearcmsgfornorecods = By.xpath("//div[@id='library-measure-notfound']");
		public static final By measureversionstartdate = By.xpath("//div[contains(@id,'effectiveStartDateV')]//input");
		public static final By measureversionenddate = By.xpath("//div[contains(@id,'effectiveEndDateV')]//input");
		public static final By measureversionvalue = By.xpath("//table//div[text()='2']");
		public static final By measureversionexpander = By.xpath("//table//div[text()='2']/../..//div[@class='x-grid-row-expander']");
		public static final By measureversion2startdate = By.xpath("//table//div[text()='2']/../../..//span[contains(text(),'Effective Start Date')]/../..//span[@class='measure_grid_sub_contents']");
		public static final By measureversion2enddate = By.xpath("//table//div[text()='2']/../../..//span[contains(text(),'Effective End Date')]/../..//span[@class='measure_grid_sub_contents']");
		public static final By measureversion_btn_Add = By.xpath("//span[contains(text(),'Add')]");
		public static final By measureversion_btn_Save = By.xpath("//div[contains(@class,'x-window-closable')]//span[text()[.='Save']]");
		public static final By measurepage_records = By.xpath("//table[contains(@id,'record')]");
		public static final By measure_textbox_formula = By.xpath("//input[@name='formulaName']");
		public static final By measure_textbox_formuladef = By.xpath("//textarea[@name='formulaDefn']");
		//public static final By measure_textbox_Files = By.xpath("//label/span[contains(text(),'File')]/../..//input[@role='textbox']");
		public static final By measure_textbox_Files = By.xpath("//input[@name='fileName']");
		public static final By measure_btn_FilesUpload = By.id("file_upload_button");
		public static final By measure_EditAttribute = By.xpath("//div[@id='measureAttributes-body']//tr[1]//a");
		public static final By measure_EditValue = By.xpath("//input[@name='attributeValue']");
		public static final By measure_drpdown_Formulas = By.xpath("//input[contains(@class,'measure_formula_combo_dispaly')]");
		public static final By measure_btn_Savepara = By.xpath("//input[contains(@class,'measure_formula_combo_dispaly')]");
		public static final By measure_value1 = By.xpath("//div[contains(@id,'propertygrid')]//table[1]//td[2]");
		public static final By measure_value2 = By.xpath("//div[contains(@id,'propertygrid')]//table[2]//td[2]");
		public static final By measure_value_textbox = By.xpath("//div[contains(@id,'propertygrid')]//input");
		public static final By measure_value_field = By.xpath("//span[contains(@style,'color')]");
		public static final By measure_attributes_expander = By.xpath("//div[@id='measureAttributes']//table[1]//div[@class='x-grid-row-expander']");
		public static final By measure_attributes_btn_addfiles = By.xpath("//div[@id='measureAttributes']//div[@class='x-grid-row-expander']");
		public static final By measure_attributes_textbox_name = By.xpath("//div[contains(@id,'editAttributeValuePopup')]//input[@name='name']");
		public static final By measure_drpdown_docref = By.xpath("//div[contains(@id,'editAttributeValuePopup')]//input[@name='documentId']");
		public static final By measure_btn_savefiles = By.xpath("//div[contains(@id,'editAttributeValuePopup')]//span[text()='Save']");
		public static final By measure_filesname = By.xpath("//a[@class='measureDataGridMeasureName']");
		public static final By measure_statename = By.xpath("//div[contains(@id,'measureWhereUsed')]//table//td[1]/div");
		public static final By measure_dropdownbox_searchWhereUsed = By.xpath("//label[contains(text(),'Select Where Used')]/..//div[contains(@class,'x-form-search-trigger')]");
		public static final By measure_btn_addState = By.xpath("//div[@id='measureWhereUsedWrapper']//span[text()[contains(.,'Add')]]");
		public static final By measure_btn_addSector = By.xpath("//div[@id='measureSectorsWrapper']//span[text()[contains(.,'Add')]]");
		public static final By measure_sectorname = By.xpath("//div[contains(@id,'measureSectorsWrapper')]//table//td[1]/div");
		public static final By measure_dropdownbox_searchSector = By.xpath("//label[contains(text(),'Select Sector')]/..//div[contains(@class,'x-form-search-trigger')]");
		public static final By measure_uploaded_filename = By.xpath("//table[1]//a[@class='measureDataGridMeasureName']");
		public static final By measure_filterarrow = By.xpath("//input[@name='paramSearchCombo']/../..//div[contains(@id,'trigger-picker')]");
        public static final By measure_filteroptions = By.xpath("//ul[@class='x-list-plain']/li");
        public static final By measure_SearchTextBox_Value = By.xpath("//div[@id='parameterSearch-innerCt']//input[@role='textbox']");
        public static final By measure_SearchRecord = By.xpath("//div[@id='library-measure-grid-body']//table");
        public static final By measure_textbox_refnum = By.xpath("//span[contains(text(),'Measure #')]/../..//input");
        public static final By measure_sorting_lastupdated = By.xpath("//span[contains(text(),'Last updated by')]");
        public static final By measure_sorting_reference = By.xpath("//span[contains(text(),'Reference')]");
        public static final By measure_trackingfield = By.xpath("//div[@id='measureTrackingFieldsGrid-body']//table//td[2]/div");
        public static final By measure_trackingfield_incetivepartner = By.xpath("//div[text()='Incentive - Partner ($ amount)']/../..//div[@class='x-grid-row-checker']");
        public static final By measure_attributefield = By.xpath("//div[@id='measureAttributes-body']//table[1]//td[2]/div");
        public static final By measure_attributefield_remove = By.xpath("//div[@id='measureAttributes-body']//table[1]//a[contains(text(),'remove')]");
        public static final By measure_attribute_description = By.xpath("//div[text()='Descriptions']/../..//div[@class='x-grid-row-checker']");
        public static final By measure_searcattribute_addbutton = By.xpath("//div[@id='searchByAttributeValue']//a[contains(@id,'button')]");
        public static final By measure_subsectorname = By.xpath("//div[contains(@id,'measureSectorsWrapper')]//table//td[2]/div");
        public static final By measure_utilityname = By.xpath("//div[contains(@id,'measureWhereUsed')]//table//td[2]/div");
        public static final By measure_btn_editSector = By.xpath("//div[@id='measureSectorsWrapper']//a[@class='edit_icon']");
        public static final By measure_btn_editwhereused = By.xpath("//div[@id='measureWhereUsedWrapper']//a[@class='edit_icon']");

		
    }

    public interface Survey {
    	public static final By L_ZIP_CODE = By.cssSelector("#saveSurveySector-zipCode-inputEl");
		public static final By L_SEARCH = By.cssSelector("#survey-search-button");
		public static final By L_SAVE = By.cssSelector("#survey-save-button");
		public static final By L_SAVE_AGAIN = By.cssSelector("#createSurveyPopup-save-btnEl");
		public static final By L_NAME = By.cssSelector("#createSurveyPopup-tokenName-inputEl");
		public static final By L_DESCRIPTION = By.cssSelector("#createSurveyPopup-description-inputEl");
		public static final By L_CUSTOMER_TYPE = By.cssSelector("#customer_type_filter-body");
		public static final By L_SURVEY_LIST = By.xpath("//div/ul/li/a[text()[contains(.,'Survey List')]]");
		public static final By L_Survey_Pagination_Button_100 = By.xpath("//div/a[text()[.='100']]");
		public static final By L_Survey_Pagination_Button_50 = By.xpath("//div/a[text()[.='50']]");
		public static final By L_Survey_Pagination_Button_20 = By.xpath("//div/a[text()[.='20']]");
		public static final By L_Survey_Sort_Last_Updated = By.xpath("//a/span[text()[.='Last Updated']]");
		public static final By L_Survey_Sort_Last_ZipCode = By.xpath("//a/span[text()[.='Zip Code']]");
		public static final By L_Survey_Sort_Last_Name = By.xpath("//a/span[text()[.='Name']]");
		public static final By L_Survey_Sort_Customer_Number = By.xpath("//a/span[text()[.='Customer Number']]");
		public static final By L_Survey_SurveyList_Sort_CreatedOn = By.xpath("//ul/li[text()[.='Created On']]");
		public static final By L_Survey_SurveyList_Sort_Name = By.xpath("//div/a[text()[.='Name']]");
    }

    public interface Equipment {
    	// main page
    			public static final By L_ADD_NEW_EQUIPMENT = By.cssSelector("[value= '+ Add New Equipment']");
    			// public static final By L_EQUIPMENT_TYPE =
    			// By.xpath("(//*[@id='eqtype_filter_container']//ul/li)[text()[contains(.,'Boiler')]]");
    			public static final By L_SOURCE_COMBO = By.cssSelector("input[name='source']");
    			public static final By L_CATEGORY_COMBO = By.cssSelector("input[name='type']");
    			public static final By L_EQUIPMENT_NAME = By
    					.cssSelector("input[name='equipmentName']");
    			public static final By L_EQUIPMENT_DESCR = By
    					.cssSelector("input[name='description']");

    			// attributes popup
    			public static final By L_SELECT_CATEGORY = By
    					.cssSelector("input[name='categoryId']");
    			public static final By L_SELECT_ATTRIBUTE = By
    					.cssSelector("input[name='id']");
    			public static final By L_ENTER_VALUE = By
    					.cssSelector("input[name='value']");
    			public static final By L_SAVE_ATTRIBUTE = By
    					.cssSelector("a.save_attribute.actionButton");

    			public static final By L_SAVE_EQUIPMENT = By.cssSelector("a.equipment_save.actionButton");

    			public static final String L_CATEGORY_ITEM = "Size/Capacity";
    			public static final By L_SELECT_ATTRIBUTE2 = By
    					.xpath("//div[@id=library-equipment-gridContent]//*[text()[contains(.,'Select Attribute')]]"); // By.xpath("//div/table/tbody/tr[2]/td[3]/div");
    			public static final By L_ATTRIBUTE_COMBO = By
    					.cssSelector("#attrib_combo-inputEl");

    			public static final By L_SELECT_ATTRIBUTE1 = By
    					.xpath("//div/ul/li[1][text()[contains(.,'AHRI #')]]");
    			public static final String L_ATTRIBUTE_ITEM = "GrossThermsPerUnit";
    			public static final By L_VALUE = By.cssSelector("[name='value']");
    			
    			//Added by Paedru on 3/10/2016 for TestAddNewEquipment.java
    			public static final By L_Equipment_Link = By.xpath("//span[@class='link']//a[text()[.='Equipment']]");
    			public static final By L_Category_Id = By.xpath("//div[contains(@id,'ext-comp-')]//input[contains(@name,'categoryId')]");
    			public static final By L_Directly_Related = By.xpath("//div[contains(@class,'main')]/../div[contains(@id,'boundlist')]//ul//li[contains(text(),'Directly-related')]");
    			public static final By L_OtherInfo_Related = By.xpath("//div[contains(@class,'main')]/../div[contains(@id,'boundlist')]//ul//li[contains(text(),'Other Information')]");
    			public static final By L_Name = By.xpath("//div[contains(@id,'ext-comp-')]//input[contains(@name,'id')]");
    			public static final By L_AHRI = By.xpath("//div[contains(@class,'main')]/../div[contains(@id,'boundlist')]//ul//li[contains(text(),'AHRI #')]");
    			public static final By L_InstallationAndEquipCost = By.xpath("//div[contains(@class,'main')]/../div[contains(@id,'boundlist')]//ul//li[contains(text(),'Installation/EquipmentCost($)')]");
    			public static final String L_Cancel = "//div[contains(@id,'ext-comp-')]//span[contains(text(),'Cancel')]";
    			public static final By L_Save_Succeeded = By.xpath("//div[text()[.='Equipment save succeeded.']]");
    			public static final By L_Directly_Related_2 = By.xpath("//div[contains(@class,'main')]/../div[contains(@id,'boundlist')]//ul/li[contains(text(),'Directly-related')]");
    			public static final By L_OtherInfo_Related_2 = By.xpath("//div[contains(@class,'main')]/../div[contains(@id,'boundlist')][4]//ul/li[contains(text(),'Other Information')]");
    			public static final By L_AHRI2 = By.xpath("//div[contains(@class,'main')]/../div[contains(@id,'boundlist')]//ul/li[contains(text(),'AHRI #')]");
    			public static final By L_InstallationAndEquipCost2 = By.xpath("//div[contains(@class,'main')]/../div[contains(@id,'boundlist')]//ul/li[contains(text(),'Installation/EquipmentCost($)')]");
    			public static final By L_Equals = By.xpath("//div[contains(@class,'main')]/../div[contains(@id,'boundlist')]//ul/li[contains(text(),'Equals')]");
    			public static final By L_Equals2 = By.xpath("//div[contains(@class,'main')]/../div[contains(@id,'boundlist')][6]//ul/li[contains(text(),'Equals')]");
    			public static final By attribute_dropdwnbox = By.id("attr_1_field-trigger-picker");
    			public static final By attribute_dropdwnboxonpopup = By.xpath("//span[text()='Attribute']/../..//div[contains(@id,'picker')]");
    			public static final By equipmenttype_number = By.xpath("//div[@class='library_user_num library_user_num_equip']/a");
    			public static final By recordsofequipmentdetails = By.xpath("//table");
    			public static final By validationmsgondeletingallatri = By.xpath("//div[contains(@id,'messagebox')]//div[@role='textbox']");
    			public static final By removerowicon = By.id("remove_2_container");
    }

    public interface Partner {
		public static final By L_ADD_PARTNER_BTN = By
				.cssSelector("[value= '+ Add Partners']");

		/**
		 * no great way to find these elements since there is no label and no
		 * field name (execpt auto generated ids that we cannot rely on)
		 */
		public static final By L_PARTNER_ID = By
				.xpath("//div[@id='companyName_div']//input[@type='text']");
		public static final By L_ADDRESS_1 = By
				.xpath("//div[@id='addressLine1_div']//input[@type='text']");
		public static final By L_ADDRESS_2 = By
				.xpath("//div[@id='addressLine2_div']//input[@type='text']");
		public static final By L_WEBSITE = By
				.xpath("//div[@id='companyUrl_div']//input[@type='text']");
		public static final By L_CITY = By
				.xpath("//div[@id='city_div']//input[@type='text']");
		public static final By L_ZIP = By
				.xpath("//div[@id='postalCode_div']//input[@type='text']");
		public static final By L_STATE = By
				.xpath("//div[@id='state_div']//input[@type='text' and @role='combobox']");
		public static final By L_COUNTRY = By
				.xpath("//div[@id='country_div']//input[@type='text']");
		public static final By L_DESCRIPTION = By
				.xpath("//div[@id='description_div']//input[@type='text']");
		public static final By L_phoneCountryCode = By
				.xpath("//input[@type='text' and @name='phoneCountryCode']");
		public static final By L_Add_Phone_Area = By
				.xpath("//input[@type='text' and @name='phoneAreaCode']");
		public static final By L_Add_Phone_Prefix = By
				.xpath("//input[@type='text' and @name='phonePrefix']");
		public static final By L_Add_Phone_Suffix = By
				.xpath("//input[@type='text' and @name='phoneSuffix']");
		public static final By L_Add_Phone_Ext = By
				.xpath("//input[@type='text' and @name='phoneExtension']");
		public static final By L_faxCountryCode = By
				.xpath("//input[@type='text' and @name='faxCountryCode']");
		public static final By L_Add_fax_Area = By
				.xpath("//input[@type='text' and @name='faxAreaCode']");
		public static final By L_Add_fax_Prefix = By
				.xpath("//input[@type='text' and @name='faxPrefix']");
		public static final By L_Add_fax_Suffix = By
				.xpath("//input[@type='text' and @name='faxSuffix']");
		public static final By L_Add_fax_Ext = By
				.xpath("//input[@type='text' and @name='faxExtension']");
		public static final By remittanceNumber = By
				.xpath("//div[@id='remittanceNumber_div']//input[@type='text']");
		public static final By partnerNumber = By
				.xpath("//label/span[text()[contains(.,'Partner Number')]]/../..//input[@type='text']");
		public static final By STATUS = By
				.xpath("//div[@id='active_div']//input[@type='text' and @role='combobox']");
		public static final By savePartner = By.cssSelector("#savePartner");
		// Add new Company Offices
		public static final By CO_STATE = By
				.xpath("//input[@componentid='aostate']");
		// Add Note
		public static final By contactedBy = By
				.cssSelector("#contactedBy-inputEl");
		public static final By subject = By.cssSelector("#subject-inputEl");
		public static final By notes = By.cssSelector("#notes-inputEl");
		public static final By notetype = By.cssSelector("#noteTypeId-inputEl");
		public static final By saveNote = By.cssSelector("#file_upload_button");
		public static final By L_SAVE_NEW_PERSONNEL = By
				.xpath("//input[@type='button' and @value='Save']");
		public static final By contractName = By
				.xpath("//input[@name='contractName']");
		public static final By activeindicator = By
				.xpath("//input[@name='activeFlag' and @role='combobox']");
		public static final By saveContractButton = By
				.xpath("//input[@value='Save']");
		public static final By saveLicenseButton = By
				.cssSelector("#file_upload_button");
		public static final By licenseName = By
				.cssSelector("#licenseName-inputEl");
		public static final By followUpRequired = By
				.cssSelector("#followUpRequired-inputEl");
		public static final By followUpEmailAddress = By
				.cssSelector("#followUpEmailAddress-inputEl");
		public static final By followUpEmailText = By
				.cssSelector("#followUpEmailText-inputEl");
		public static final By serviceTypes = By
				.xpath("//input[@name='serviceTypes']");
		public static final By serviceTypeSpecialties = By
				.xpath("//input[@name='serviceTypeSpecialties']");
		public static final By sector = By
				.xpath("//input[@id='sector-inputEl']");
		public static final By state_comb = By
				.xpath("//input[@id='state-inputEl']");
		public static final By followUpDateString = By
				.cssSelector("#followUpDateString-inputEl");
		public static final By officeName = By
				.cssSelector("[name='officeName']");
		public static final By addressLine1 = By
				.cssSelector("[name='addressLine1']");
		public static final By addressLine2 = By
				.cssSelector("[name='addressLine2']");
		public static final By city = By.cssSelector("[name='city']");
		public static final By postalCode = By
				.cssSelector("[name='postalCode']");
		public static final By country = By.cssSelector("[name='country']");
		public static final By description = By
				.cssSelector("[name='description']");
		public static final By phoneCountryCode = By
				.cssSelector("[name='phoneCountryCode']");
		public static final By phoneAreaCode = By
				.cssSelector("[name='phoneAreaCode']");
		public static final By phonePrefix = By
				.cssSelector("[name='phonePrefix']");
		public static final By phoneSuffix = By
				.cssSelector("[name='phoneSuffix']");
		public static final By phoneExtension = By
				.cssSelector("[name='phoneExtension']");
		public static final By faxCountryCode = By
				.cssSelector("[name='faxCountryCode']");
		public static final By faxAreaCode = By
				.cssSelector("[name='faxAreaCode']");
		public static final By faxPrefix = By.cssSelector("[name='faxPrefix']");
		public static final By faxSuffix = By.cssSelector("[name='faxSuffix']");
		public static final By faxExtension = By
				.cssSelector("[name='faxExtension']");
		public static final By firstName = By.cssSelector("[name='firstName']");
		public static final By lastName = By.cssSelector("[name='lastName']");
		public static final By title1 = By.cssSelector("[name='title']");
		public static final By emailAddress = By
				.cssSelector("[name='emailAddress']");
		public static final By phCntryCode = By
				.cssSelector("[name='phCntryCode']");
		public static final By phAreaCode = By
				.cssSelector("[name='phAreaCode']");
		public static final By phPrefix = By.cssSelector("[name='phPrefix']");
		public static final By phSuffix = By.cssSelector("[name='phSuffix']");
		public static final By phExt = By.cssSelector("[name='phExt']");
		public static final By primaryContact = By
				.id("apprimaryContact-inputEl");
		public static final By comments = By.cssSelector("[name='comments']");
		public static final By partnerOfficeId = By
				.cssSelector("[name='partnerOfficeId']");

		// the add new contract popup box
		public static final By L_ADD_CONTRACT_POPUP = Locator
				.divText("Add New Contract");

		// the add new contract popup box title span
		public static final By L_ADD_CONTRACT_POPUP_TITLE_SPAN = By
				.id("addContractPopup_header_hd-textEl");

		public static final By L_SAVE_COMPANY_OFFICE = By
				.xpath("//input[@type='button' and @value='Save']");

		// Added on 09/27/2016 by Paedru Fernandofor
		// TestmanagePartnersElements.java
		public static final By L_Manage_partner_Elements_Button = By.cssSelector("[value='+ Manage Partner Elements']");
		public static final By L_Add_Service_Type_Button = By.cssSelector("[value='+ Service Type']");
		public static final By L_Active_Indicator = By.xpath("//input[@componentid='activeFlag']");
		public static final By L_Service_Type = By.xpath("//input[@componentid='serviceType']");
		public static final By L_Service_Type_Description = By.xpath("//textarea[@componentid='description']");
		public static final By L_ServiceType_Updated_Date_Sort = By.xpath("//div/span[text()[.='Updated Date']]");
		public static final By L_ServiceType_Active_Indicator_Sort = By.xpath("//div/span[text()[.='Active Indicate']]");
		public static final By L_ServiceType_Description_Sort = By.xpath("//div/span[text()[.='Description']]");
		public static final By L_ServiceType_Field_Sort = By.xpath("//div/span[text()[.='Service Type']]");

		public static final By L_Specialities = By.xpath("//a[text()[contains(.,'Specialties')]]");

		public static final By L_Add_Speciality_Link = By.cssSelector("[value='+ Specialty']");
		public static final By L_Specialities_Hyperlink = By.xpath(".//*[@id='title']/div/div/span[4]/a");
		public static final By L_Speciality_Name = By.xpath("//input[@componentid='specialtyName']");
		public static final By L_Speciality_Description = By.xpath("//textarea[@componentid='description']");
		public static final By L_Speciality_ActiveIndicator = By.xpath("//input[@componentid='activeFlag']");
		public static final By L_Speciality_Save = By.xpath("//input[@value='Save']");
		public static final By L_Speciality_Last_Updated_Sort = By.xpath("//span[text()[.='Last Updated']]");
		public static final By L_Speciality_Active_Indicator_Sort = By.xpath("//span[text()[.='Active Indicator']]");
		public static final By L_Speciality_Description_Sort = By.xpath("//span[text()[.='Description']]");
		public static final By L_Speciality_Service_Type_Sort = By.xpath("//span[text()[.='Service Type']]");

		// Added on 09/28/2016 by Paedru Fernando for TestAddNewContract.java
		public static final By L_Contract_Add_Contract_BTN = By.cssSelector("[value='+Add Contract']");
		public static final By Partner_Add_Contract_BTN = By.xpath(".//input[@value='+Add Contract']//ancestor::div[@class='top_button_right']");
		
		//public static final By L_Contract_savedLinkActive = By.xpath("//div[@id='partner_contract_content']//a[.='" + TestAddNewContract.parnerIdActive + "']");
		public static final By L_Contract_sort_Contract = By.xpath("//div/span[text()[.='Contract']]");
		public static final By L_Contract_sort_Active_Indicator = By.xpath("//div/span[text()[.='Active Indicator']]");

		public static final By L_License_Add_License_BTN = By.cssSelector("[value= '+Add License']");
		public static final By L_License_sort_Active_Indicator = By.xpath("//div/span[text()[.='Active Indicator']]");
		public static final By L_License_sort_License = By.xpath("//div/span[text()[.='License']]");

		public static final By L_Add_Service_Combination_Button = By.cssSelector("[value='+Add Service Combination']");
		public static final By L_ServiceType_Sort = By.xpath("//div/span[text()[.='Service Type']]");
		public static final By L_Speciality_Sort = By.xpath("//div/span[text()[.='Specialty']]");
		public static final By L_Sector_Sort = By.xpath("//div/span[text()[.='Sector']]");
		public static final By L_State_Sort = By.xpath("//div/span[text()[.='State']]");
		public static final By L_Last_Update_Sort = By.xpath("//div/span[text()[.='Last Update']]");
		public static final By L_By_Sort = By.xpath("//div/span[text()[.='By']]");

		// Added on 09/29/2016 by Paedru Fernando for TestAddNewPartner.java
		public static final By L_Partner_Pagination100_Sort = By.xpath("//a[text()[.='100']]");
		public static final By L_Partner_Pagination50_Sort = By.xpath("//a[text()[.='50']]");
		public static final By L_Partner_Pagination20_Sort = By.xpath("//a[text()[.='20']]");
		public static final By L_Partner_LastUpdated_Sort = By.xpath("//a/span[text()[.='Last Updated']]");
		public static final By L_Partner_Active_Sort = By.xpath("//a/span[text()[.='Active']]");
		public static final By L_Partner_City_Sort = By.xpath("//a/span[text()[.='City']]");
		public static final By L_Partner_Name_Sort = By.xpath("//a/span[text()[.='Name']]");

		public static final By L_Partner_Personnel_Add_Button = By
				.cssSelector("[value= '+Add Personnel']");
		public static final By L_Personnel_LName_Sort = By
				.xpath("//div/span[text()[.='Last Name']]");
		public static final By L_Personnel_FName_Sort = By
				.xpath("//div/span[text()[.='First Name']]");
		public static final By L_Personnel_Email_Sort = By
				.xpath("//div/span[text()[.='Email']]");
		public static final By L_Personnel_Primary_Contact_Sort = By
				.xpath("//div/span[text()[.='Primary Contact']]");
		public static final By L_Personnel_Last_Updated_Sort = By
				.xpath("//div/span[text()[.='Last Update']]");
		public static final By L_Personnel_Last_By_Sort = By
				.xpath("//div/span[text()[.='By']]");
		public static final By L_Personnel_TracksmartUser_Sort = By
				.xpath("//div/span[text()[.='TrakSmart User']]");

		public static final By L_Partner_Add_Note_BTN = By.cssSelector("[value= '+ Add Note']");
		
	/*	TestAddNewNote obj = new TestAddNewNote();
		String value = obj.desc;
		

        public static final By L_Partner_Note_Exists_Check1 = By.xpath("(//div[@id='library-note-gridContent']//div[@class='notes-detail'])[text()[contains(.,'"+ TestAddNewNote.desc1 + "')]]");
        public static final By L_Partner_Note_Exists_Check = By.xpath("(//div[@id='library-note-gridContent']//div[@class='notes-detail'])[text()[contains(.,'"+ TestAddNewNote.desc + "')]]");*/
        //	public static final By L_Partner_Note_Exists_Check = By.xpath("(//div[@id='library-note-gridContent']//div[@class='notes-detail'])[text()[contains(.,'"+ value + "')]]");
		public static final By L_Partner_Select_Future_FollwUp_Date = By
				.xpath("//div[@id='followUpDateString-trigger-picker']");
		public static final By L_Partner_Note_Type_Sort = By
				.xpath("//a/span[text()[.='Note Type']]");
		public static final By L_Partner_Contact_Date_Sort = By
				.xpath("//a/span[text()[.='Contact Date']]");
		public static final By L_Partner_Contact_By_Sort = By
				.xpath("//a/span[text()[.='Contact By']]");
		public static final By L_Partner_Program_Sort = By
				.xpath("//a/span[text()[.='Program']]");
		public static final By L_Partner_Project_Sort = By
				.xpath("//a/span[text()[.='Project']]");
		public static final By partner_validation_msg = By.xpath(".//*[@id='globalMessages']/div");
		public static final By btn_deletepartner = By.id("deletePage");
    }

    public interface Measure_PSEG {
    	public static final By L_MANAGE_TAXONOMY = Locator
				.traksmartButtonAnchorStyle("+ Manage Taxonomy");
    	
    	
    	public static final String Button_Disabled_CSS_Property = "x-btn-disabled";
		// add category test
		// public static final By L_MEASURE_CATEGORY_NAME =
		// By.xpath("//label/span[text()[contains(.,'Name:')]]//../..//input[@name='name' and @type='text']");
		public static final By L_MEASURE_CATEGORY_NAME = By
				.xpath("//*[contains(@class, 'manageTaxonomy')]//input[@name='name' and @type='text']");
		public static final By L_STATUS = By
				.xpath("//*[contains(@class, 'manageTaxonomy')]//input[@name='statusId']");
		public static final By L_DESCRIPTION = By
				.xpath("//*[contains(@class, 'manageTaxonomy')]//textarea[@name='description']");
		public static final By L_CATEGORIES = By
				.cssSelector("#categoryListComp_listcontainer");
		public static final By L_SAVE_CATEGORY = By
				.xpath("//*[contains(@class, 'manageTaxonomy')]//span[text()[.='Save']]");
		// add type test // addTaxonomyElement-1122
		public static final By L_ADD_TYPE_NAME = By
				.xpath("//*[contains(@class, 'manageTaxonomy')]//input[@name='name' and @type='text']");
		public static final By L_ADD_TYPE_DESCRIPTION = By
				.xpath("//*[contains(@class, 'manageTaxonomy')]//textarea[@name='description']");
		public static final By L_ADD_TYPE_STATUS = By
				.xpath("//*[contains(@class, 'manageTaxonomy')]//input[@name='statusId']");
		public static final By L_SAVE_TYPE = By
				.xpath("//*[contains(@class, 'manageTaxonomy')]//span[text()[.='Save']]");
		// add // addTaxonomyElement-1135 // x-form-checkbox-default
		public static final By L_ADD_SUB_TYPE_NAME = By
				.xpath("//*[contains(@class, 'manageTaxonomy')]//input[@name='name' and @type='text']");
		public static final By L_ADD_SUB_TYPE_DESCRIPTION = By
				.xpath("//*[contains(@class, 'manageTaxonomy')]//textarea[@name='description']");
		public static final By L_ADD_SUB_TYPE_CHECK = By
				.xpath("//*[contains(@class, 'manageTaxonomy')]//span[text()[.='Track Renewable Generation:']]");
		public static final By L_ADD_SUB_TYPE_STATUS = By
				.xpath("//*[contains(@class, 'manageTaxonomy')]//input[@name='statusId']");
		public static final By L_SAVE_SUB_TYPE = By
				.xpath("//*[contains(@class, 'manageTaxonomy')]//span[text()[.='Save']]");
		//
		public static final By L_ADD_TRACKING = By
				.cssSelector("#trackFieldButton-btnEl");
		public static final By L_CHECK_BOX = By
				.xpath("//div/table/tbody/tr/td/div/div[@class='x-grid-row-checker']");
		public static final By L_ATTRIBUTES = By.cssSelector("#tab-1095-btnEl");
		public static final By L_ADD_ATTRIBUTES = By
				.xpath("//div/em/button/span[text()[contains(.,'ATTRIBUTES')]]");
		public static final By L_ADD = By
				.xpath("(//div/em/button/span[text()[contains(.,'+ Add')]])[last()]");
		public static final By L_TRACK = By
				.xpath("(//div/table/tbody/tr/td/input)[Last()]");
		public static final By L_TYPE = By
				.cssSelector("#typeListComp_listcontainer");
		public static final By L_SUB_TYPE = By
				.cssSelector("#subTypeListComp_listcontainer");

    }


    public interface Programs {
    	public static final By projectsReadyForPayment_link = By.linkText("Projects Ready For Payment");
    	//public static final By Program_Breadcrum_Sub = By.xpath("//div[@class='breadcrumb_links']/span[contains(a,'"+TestAddProgramVariableCostDashboard.sub+"')]/a");
    	public static final By processed_Individual_Payments_link = By.linkText("Processed Individual Payments");
    	public static final By processed_Batch_Payments_link = By.linkText("Processed Batch Payments");
    	public static final By Apply_btn = By.xpath("//input[@type='button' and @value='Apply']");
    	public static final By openAsCollapsedExpression = By.name("expression");
    	public static final By BatchReview_RebatesProjected_FromHeader = By.xpath(".//span[text()='Rebates']//ancestor::label//following-sibling::div//span[text()='Projected:']//parent::label//following-sibling::div");
    	public static final By BatchReview_RebatesPaid_FromHeader = By.xpath(".//span[text()='Rebates']//ancestor::label//following-sibling::div//span[text()='Paid:']//parent::label//following-sibling::div");
    	public static final By BatchReview_Projected_FromChart =By.xpath("//*[name()='svg']//*[name()='g'][8]//*[name()='text' and @class='fusioncharts-subcaption']//*[name()='tspan']");
    	public static final By BatchReview_Paid_FromChart = By.xpath("//*[name()='svg']//*[name()='g'][8]//*[name()='text' and @class='fusioncharts-caption']//*[name()='tspan']");
    	public static final By L_PROGRAMS = By.xpath("//div/ul/li/a[text()[contains(.,'PROGRAMS')]]");
		public static final By L_Add_New_Program = By.xpath("//div/input[@value='+ Add New Program']");
		public static final By link_exit = By.linkText("Exit");
		public static final By L_SEARCH = By.cssSelector("[name='keyword_search']");
		public static final By L_Enroll = By.linkText("Program Enroll Setting"); // 
		public static final By L_Enroll_Settings =By.xpath("//div/ul/li/a[text()[contains(.,'Program Enroll Setting')]]");
		public static final By Program_Enroll_Setting_btn_save = By.id("button-1067-btnIconEl");
		public static final By Program_Form_Block_ConfigureNormalSection= By.xpath("//div[@id='webFormDesigner_center_regionId-body']//div[text()[.='Normal']]");
		public static final By L_NAME = By.cssSelector("#publicName-inputEl");
		public static final By L_DESCRIPTION = By.cssSelector("#publicDescription-inputEl");
		public static final By L_publicSummary = By.cssSelector("#publicSummary-inputEl");
		public static final By Btn_CustomerEligibilitySettings = By.id("varDecAddRowLink");
		public static final By Row_SelectAttribute = Locator.divText("-Select Attribute-");
		public static final By DrpDwn_CustStateProvince = By.xpath("//*[@id='eligibility_panel']//input[@name='condition_attribute_Combo']");
		public static final By DrpDwn_Operator = By.xpath("//*[@id='eligibility_panel']//input[@name='operator_combo']");
		public static final By DrpDwn_Value = By.xpath("//*[@id='eligibility_panel']//input[@name='condition_value_textfield']");
		public static final By DrpDwn_Expression = By.xpath("//*[@id='eligibility_panel']//input[@name='logic_operator_combo']");
		public static final By Btn_Update_CustomerEligibilitySettings = By.xpath("//*[@id='eligibility_panel']//span[text()[.='Update']]/../../..");
		public static final By Row_PartnerEligibility = By.xpath("//*[@id='partnerEligibilityGrid-body']//tbody/tr");
		public static final By DrpDwn_ServiceType = By.xpath("//*[@id='partner_eligibility_panel']//input[@name='serviceTypeId']");
		public static final By DrpDwn_Specility = By.xpath("//*[@id='partner_eligibility_panel']//input[@name='operator_combo']");
		public static final By DrpDwn_Sector = By.xpath("//*[@id='partner_eligibility_panel']//input[@name='sectorId']");
		public static final By DrpDwn_State = By.xpath("//*[@id='partner_eligibility_panel']//input[@name='stateId']");
		public static final By DrpDwn_Operator1 = By.xpath("//*[@id='partner_eligibility_panel']//input[@name='logic_operator_combo']");
		public static final By Btn_Update_PartnerEligibilitySettings = By.xpath("//*[@id='partner_eligibility_panel']//span[text()[.='Update']]/../../..");
		public static final By Btn_SaveProgramSettings = Locator.traksmartButtonAnchorStyle("Save");
		public static final By Msg_SuccessfulSave = Locator.divTextContains("Customer Enroll Setting save succeeded.");
		public static final By Program_BudgetAccounting_FixedCost_on_Payments_Page = By.xpath("//tr");
		// Add program
		public static final By L_ADD_PROGRAM_NAME = By.xpath("//input[@componentid='program_name_field']");
		public static final By L_ADD_PROGRAM_DESCRIPTION = By.xpath("//textarea[@componentid='program_description_field']");
		public static final By SERVICE_TYPE = By.id("program_serviceType_field_combobox-inputEl");
		public static final By TYPE = By.id("program_type_field-inputEl");
		public static final By STATUS = By.id("program_status_field-inputEl");
		public static final By SECTORS = By.id("program_sectors_field_combobox-inputEl");
		public static final By DEFAULT_SECTOR = By.id("program_defaultsector_field-inputEl");
		public static final By DATE_FROM = By.xpath("//input[@componentid='program_fromdate_field']");
		public static final By DATE_TO = By.xpath("//input[@componentid='program_todate_field']");
		public static final By Service_Point_0_Input = By.id("service_point_0_input");
		public static final By Program_Service_Territory = By.id("program_service_territory_field_combobox-inputEl");
		public static final By TRACK_RENEWABLE_GENERATION = By.xpath("//input[@componentid='program_renewable_field']");
		public static final By ADD_NEW_SERVICE_POINT = By.partialLinkText("+ Add New Service Point");
		public static final By SERVICE_TERRITORY = By.xpath("//input[@componentid='program_service_territory_field_combobox']");
		public static final By Metric1 = By.xpath("//input[@componentid='program_metric_0_field']");
		public static final String Metric1_txt = "Coincidence Demand Reduction kW Per Unit";
		public static final By ADD_NEW_GOAL = By.linkText("+ Add New Goal");
		public static final By goal = By.xpath("//input[@componentid='program_goal_0_field']");
		public static final String goal_txt = "20";
		public static final By terr = By.cssSelector("#program_svc_terr_0_input");
		public static final String terr_txt = "CA";
		public static final By sector = By.cssSelector("#program_sector_0_input");
		public static final String sector_txt = "Mining";
		public static final By startdate = By.xpath("//input[@componentid='program_goal_startdate_0_field']");
		public static final By enddate = By.xpath("//input[@componentid='program_goal_enddate_0_field']");
		public static final By Program_InternalUser = By.id("program_programmgrs_0_field_combobox-inputEl");
		public static final By Program_Manager = By.xpath("(//input[@componentid='program_programmgrs_0_field_combobox']");
		public static final String Program_Manager_Name = "Tester Automation";
		public static final By Support_Staff = By.id("program_supportstaff_0_field_combobox-inputEl");
		public static final String Support_Staff_Name = "Tester Automation";
		public static final By Project_Managers = By.id("program_projectmgrs_0_field_combobox-inputEl");
		public static final String Project_Managers_Name = "Tester Automation";
		public static final By Project_Number_Prefix = By.xpath("//input[@componentid='program_projectnumprefix_field']");
		public static final String Project_Number_Prefix_Name = "PNP";
		public static final By Allow_Project_Copy = By.xpath("//input[@componentid='program_projectCopy_field']");
		public static final By Comments_program = By.xpath("//textarea[@componentid='program_comments_field']");
		public static final By Save_btn = By.xpath("//input[@type='button' and @value='Save']");
		public static final By Assert_Program_Title = By.xpath("(//div[@class='title'])[text()[contains(.,'All Programs')]]");
		public static final By Program_MyName = By.name("type");
		public static final By AutoAssignementFormula = By.name("autoAssignmentFormula");
		public static final By Program_Manual_Assignement_List_Formula = By.name("manualAssignmentListFormula");
		public static final By Program_Save_Assignment_Formula = By.id("form_assignment_config_form_save-btnIconEl");
		public static final By program_category = By.cssSelector("#program_category_field_combobox-inputEl");
		public static final By msg_progenrollment_success = By.xpath("//div[@class='global-message-holder' and contains(text(),'Customer Enroll Setting save succeeded.')]");
		public static final String program_category_text = "Air Conditioners";		
		public static final By category = By.cssSelector("#newCategory-inputEl");
		//public static final By Add_Category = By.xpath("//div/em/button/span[text()[contains(.,'Add Category')]]");
		public static final By Add_Category = By.xpath("//span[text()[.='Add Category']]/..");
		public static final By create_accounting_code_popup_form_save = By.cssSelector("#create_accounting_code_popup_form_save-btnEl");
		public static final By Select_State = By.cssSelector("[name='stateId']");
		public static final By Add_Eligibility_Setting = By.linkText("+Add");
		public static final String Select_State_text = "CA";
		public static final By Select_Sector = By.xpath("//div/input[@name='sectorId']");
		public static final String Select_Sector_text = "Mining";
		public static final By Select_Indicator = By.cssSelector("[name='activeFlag']");
		public static final String Select_Indicator_text = "Active";
		public static final By Select_Category = By.cssSelector("[name='accountCategoryId']");
		public static final String Select_Category_text = "EABVMRK";
		public static final By Description_Budget = By.cssSelector("[name='description']");
		public static final By Add_Account_Code = By.cssSelector("[name='accountCode']");
		public static final By Add_code = By.cssSelector("#btnAddNewAccountingItem");
		public static final By Open_Budget_And_Accounting = By.linkText("Budget & Accounting");
		public static final By btn_Back = By.xpath("//input[@value='Back']");
		// public static final By Save_Cost =
		// By.xpath("(//*[@id='variable-program-cost-window'])//input[@type='button' and @value='Save']");
		// // might be other save buttons on the screen
		public static final By formsEditor = By.xpath(".//a[contains(text(),'Forms Editor')]");
		public static final By Save_Cost = By.xpath("(//div[@id='variable-program-cost-window'])//span[text()[.='Save']]"); // might be other save buttons on the screen
		public static final By Select_Accounting_Code = By.xpath("(//div/table/tbody/tr/td/table/tbody/tr/td/div)[21]");
		public static final By Add_External_Reference_Id_VC = By.cssSelector("[name='externalRef']");
		public static final By VC_Add_External_Reference_Id = By.xpath("//div[@id='variable-program-cost-window-body']//input[@name='externalRef']");
		public static final By Payee_Last_Name = By.cssSelector("[name='payeeLastName']");
		public static final By Payee_First_Name = By.cssSelector("[name='payeeFirstName']");
		public static final By Select_Interval = By.xpath("(//div/table/tbody/tr/td/table/tbody/tr/td/div)[19]");
		public static final String Select_Interval_Text = "MONTHLY";
		public static final By Select_Amount = By.xpath("(//div/table/tbody/tr/td/table/tbody/tr/td/div)[16]");
		public static final By End_Date = By.cssSelector("[name='endDate']");
		public static final By Start_Date = By.cssSelector("[name='startDate']");
		public static final By Description_FC = By.cssSelector("[name='description']");
		// Add variable cost
		public static final By ADD_COST_NAME = By.cssSelector("[name='costName']");
		public static final By Remit_Country = By.cssSelector("[name='remitToCountry']");
		public static final By Remit_County = By.cssSelector("[name='remitToCounty']");
		public static final By Remit_ZIP = By.cssSelector("[name='remitToPostalCode']");
		public static final By Remit_State = By.cssSelector("[name='remitToState']");
		public static final By Remit_City = By.cssSelector("[name='remitToCity']");
		public static final By Remit_Street_address_2 = By.cssSelector("[name='remitToAddressLine2']");
		public static final By Remit_Street_address_1 = By.cssSelector("[name='remitToAddressLine1']");
		public static final By Remit_Partner_Number = By.cssSelector("[name='remitToPartnerNumber']");
		public static final By Remit_Name = By.cssSelector("[name='remitToName']");
		public static final By Remit_Last_Name = By.cssSelector("[name='remitToLastName']");
		public static final By Remit_First_Name = By.cssSelector("[name='remitToFirstName']");
		public static final By Press_Remit_Information = By.xpath("//div/fieldset/legend/div[text()[contains(.,\"Remit Information\")]]");
		public static final By Payee_Country = By.cssSelector("[name='payeeCountry']");
		public static final By Payee_County = By.cssSelector("[name='payeeCounty']");
		public static final By Payee_ZIP = By.cssSelector("[name='payeePostalCode']");
		public static final By Payee_State = By.cssSelector("[name='payeeState']");
		public static final By Payee_City = By.cssSelector("[name='payeeCity']");
		public static final By Payee_Street_address_2 = By.cssSelector("[name='payeeAddressLine2']");
		public static final By Payee_Street_address_1 = By.cssSelector("[name='payeeAddressLine1']");
		public static final By Payee_Partner_Number = By.cssSelector("[name='payeePartnerNumber']");
		public static final By Payee_Name = By.cssSelector("[name='payeeName']");
		public static final By Select_Accounting_Code_VC = By.cssSelector("[name='accountCodeId']");
		public static final By VC_Select_Accounting_Code = By.xpath("//div[@id='variable-program-cost-window-body']//input[@name='accountCodeId']");
		public static final By Payment_TxtBox_Costname = By.xpath("//input[@name='costName']"); 
		public static final By Payment_TxtBox_Description = By.xpath("//input[@name='description']"); 
		public static final By Payment_TxtBox_Amount = By.xpath("//input[@name='amount']");
		public static final By Payment_TxtBox_ExternalRefID = By.xpath("//input[@name='externalRef']");
		public static final By Payment_dropdwn_AccountingCode = By.xpath("//input[@name='accountCodeId']");
		public static final By Payment_dropdwnarrow_AccountingCode = By.id("fixed_costs_accountCodeId-inputEl");
		//public static final By Payment_dropdwnarrow_AccountingCode = By.id("fixed_costs_accountCodeId-trigger-picker");
		public static final By Payment_btn_SaveAccountingCode = By.xpath("//input[@value='Save']");
		public static final By Payment_label_approvedcostname = By.xpath("//div[text()='Approved' or text()='Payment Status']");
		public static final By Payment_btn_Cancel = By.xpath("//input[@value='Cancel']");
		public static final By Payment_btn_Close = By.xpath("//img[contains(@class,'x-tool-close')]");
		public static final By Payment_btn_Save = By.xpath("//input[@value='Save']");
		
		// //div/table/tbody/tr/td/table/tbody/tr/td/div[@class='x-trigger-index-0
		// x-form-trigger x-form-arrow-trigger x-form-trigger-last
		// x-unselectable']
		public static final By Add_External_Reference_Id = By.cssSelector("[name='externalRef']");
		public static final By Select_Amount_VC = By.cssSelector("[name='amount']");
		public static final By VC_Amount = By.xpath("//div[@id='variable-program-cost-window-body']//input[@name='amount']");
		public static final By Transaction_Date = By.cssSelector("[name='transactionDate']");
		//public static final By VC_Transaction_Date = By.xpath(".//legend//div[text()='Cost Information']//ancestor::legend//following-sibling::div//div//input[@name='transactionDate']");
		public static final By VC_Transaction_Date = By.xpath("//div[@id='variable-program-cost-window-body']//input[@name='transactionDate']");
		public static final By Description_VC = By.xpath("//textarea[@name='description']");
		public static final By Add_Cost_Name = By.cssSelector("[name='costName']");
		public static final By Add_Variable_Cost = By.cssSelector("[value='+ Add Variable Cost']");
		public static final By BTN_Save_Variable_Cost = By.xpath("//a[contains(@class, 'variableCostSaveBtn')]");
		//public static final By Add_Partner_Eligibility_Setting = By.cssSelector("[id='varDecAddRowLink_Partner']");
		public static final By Add_Partner_Eligibility_Setting = By.id("varDecAddRowLink_Partner");
		public static final By Add_Fixed_Cost = By.cssSelector("[value='+ Add Fixed Cost']");
		public static final By Program_Measure_Tab = By.id("ameasures");
		public static final By Program_MeasureAssoc_Dropdown_Category = By.id("measure_category_field-inputEl");
		public static final By Program_MeasureAssoc_Dropdown_Type = By.id("measure_type_field-inputEl");
		public static final By Program_MeasureAssoc_Dropdown_SubType = By.id("measure_subtype_field-inputEl");
		public static final By Program_MeasureAssoc_Textbox_Name = By.id("measure_name1_field-inputEl");
		public static final By Program_MeasureAssoc_List_First_Option = By.xpath("//div[@id='measure_list']//div/table[1]/tbody/tr");
		public static final By Program_MeasureAssoc_Button_Save = By.xpath("//div[@id='name_buttons_ro']/input[1]");
		public static final By txt_confirmation = By.id("messagebox-1001-displayfield-inputEl");
		public static final By Program_MeasureAssoc_Assert =  By.xpath("//div[@id='structures']//div/table");
		public static final By Program_BudgetAccounting_Button_ManageBudgetCategories = By.xpath("//input[@value='+ Manage Budget Categories']");
		public static final By Program_BudgetAccounting_Button_ManageBudgetCategories2 = By.xpath("//div[@class='top_button_right']//input[@value='+ Manage Budget Categories']");
		public static final By Program_BudgetAccounting_Button_AddNewCategory = By.id("addCategoryRowLink");
		public static final By Program_BudgetAccounting_TextBox_CategoryName = By.xpath("//div[@id='manage_budget_category_popup']//input[@type='text']");
		public static final By Program_BudgetAccounting_Dropdown_Status = By.xpath("//input[@name='status_combo']");
		public static final By Program_BudgetAccounting_Button_Save = By.xpath("//input[@value='Save']");
		public static final By ManageAccountCodeCategory = By.id("btnManageAcctCodeCategory");
		public static final By Program_BudgetAccounting_Button_ManageAccountingCodeCate = By.xpath("//input[@value='+ Manage Accounting Code Categories']");
		public static final By Program_BudgetAccounting_ManageAccountingCodeCate_Button_DeleteCategory = By.id("deleteRowLink");
		public static final By Program_BudgetAccounting_ManageAccountingCodeCate_Button_AddNewCategory = By.id("addRowLink");		;
		public static final By Program_BudgetAccounting_ManageAccountingCodeCate_TextBox_NewCategory = By.xpath("//input[@name='accountCategory']");
		public static final By Program_BudgetAccounting_AddVariableCost_CostName = By.xpath("//div[@id='add-variable-cost-form-innerCt']//input[@name='costName']");
		public static final By Program_BudgetAccounting_AddVariableCost_Btn_Save = By.xpath("//a[contains(@class, 'variableCostSaveBtn')]");
		public static final By Program_BudgetAccounting_FixedCost_TextBox_CostName = By.xpath("//div[@id='add-cost-form-innerCt']//input[@name='costName']");
		public static final By Program_BudgetAccounting_FixedCost_TextBox_Description = By.xpath("//div[@id='add-cost-form-innerCt']//input[@name='description']");
		public static final By Program_BudgetAccounting_FixedCost_TextBox_Amount = By.xpath("//div[@id='add-cost-form-innerCt']//input[@name='amount']");
		public static final By Program_BudgetAccounting_FixedCost_TextBox_ExternalRefID = By.xpath("//div[@id='add-cost-form-innerCt']//input[@name='externalRef']");
		public static final By Program_BudgetAccounting_FixedCost_TextBox_AccountingCode = By.xpath("//div[@id='add-cost-form-innerCt']//input[@name='accountCodeId']");
		public static final By Program_Form_Block_PageTitle = By.xpath("//div[text()[.='Page Title']]/../../div[2]");
		public static final By Program_Form_Block_PageTitleName = By.xpath("//div[text()[.='Page Title'] and contains(@class, 'formAssembly_title')]");
		public static final By Program_Form_Button_Save = By.id("saveButton");		
		public static final By Program_Form_Dropdown_SelectForm = By.id("north_region_form_combo-inputEl");
		public static final By Program_Form_Block = By.xpath("//div[@id='fdp-innerCt']/div[3]");
		public static final By Program_Form_Block1 = By.xpath("//*[@id='fdp-innerCt']//div[4]");
		public static final By Program_Form_MsgBox = By.xpath("//div[contains(@id,'fdp-innerCt')]//div[4]//div[contains(@id,'box-')][6]");
		public static final By Program_Form_Container = By.xpath("//div[contains(@id,'lcontainer')]/div[16]");
		public static final By Program_Form_label_Select  = By.xpath("//div[contains(@class,'x-grid-item-container')]//table[13]//div[contains(text(),'select')]");
		public static final By Program_Form_AssemblyPopUpWindow = By.xpath("//div[contains(@id,'formAssemblyPopupWindow-body')]//textarea");
		//public static final By Program_Form_Validate_AssemblyPopUpWindow = By.xpath("//div[contains(@id,'formAssemblyPopupWindow-body')]//textarea"), "Contains(value,\"Anthony\")");
		public static final By Program_Form_AssemblyPopUpWindowBtn_Apply = By.xpath("//input[contains(@value,'Apply')]");
		public static final By Program_Form_AssemblyPopUpWindowBtn_GridCont = By.xpath("//div[@class='x-grid-item-container']/table[10]//tr/td[2]");
		public static final By Program_Form_SectionClonable = By.xpath("//input[contains(@id,'IsSectionClonable-inputEl')]");
		
		public static final By Program_Form_Block5 = By.xpath("//*[@id='fdp-innerCt']//div[5]");
		public static final By Program_Form_GridConatiner2 = By.xpath("//div[@class='x-grid-item-container']/table[1]//tr/td[2]");
		
		public static final By Program_Form_Button_Exp = By.xpath("//div[@id='fdp-innerCt']/div[3]//div[contains(@class,'x-component section_expand')]");
		public static final By Program_Form_Button_Component = By.xpath("//div[@id='fdp-innerCt']/div[3]//div[contains(@class,'x-component form_action_link')]");
		
		public static final By Program_Measure_Library = By.cssSelector("#measure_library_top_button_right");
		public static final By Program_ProcessedBatchPayments_Date = By.xpath("//div[@class='cell1']");
		
		public static final By Program_Form_Grid = By.xpath("//div[contains(@class,'x-grid-item-container')]//tbody//div");
		public static final By Program_Form_Button_AddForm = By.xpath("//span[contains(text(),'Add') and contains(@class,'x-btn-inner x-btn-inner-default-small')]/..");
		public static final By Program_Form_TextBox_IncentivesCustomer = By.xpath("//span[contains(text(),'Incentive - Customer:')]/../following-sibling::div[1]/div/div/input");
		public static final By Program_Form_TextBox_AHRI = By.xpath("//span[contains(text(),'AHRI #:')]/../following-sibling::div[1]/div/div/input");
		public static final By Program_Form_TextBox_IncentivesPartners = By.xpath("//span[contains(text(),'Incentive - Partner ($ amount):')]/../following-sibling::div[1]/div/div/input");
		public static final By Program_Form_TextBox_WaterSavings = By.xpath("//span[contains(text(),'Water Savings (Gal):')]/../following-sibling::div[1]/div/div/input");
		public static final By Program_Form_TextBox_ThermSavings = By.xpath("//span[contains(text(),'Therm Savings:')]/../following-sibling::div[1]/div/div/input");
		public static final By Program_Form_TextBox_KwInstalled = By.xpath("//span[contains(text(),'kW Installed:')]/../following-sibling::div[1]/div/div/input");
		public static final By Program_Form_TextBox_kWh_Savings = By.xpath("//span[contains(text(),'kWh Savings:')]/../following-sibling::div[1]/div/div/input");
		public static final By Program_Form_TextBox_KW_Savings = By.xpath("//span[contains(text(),'KW Savings:')]/../following-sibling::div[1]/div/div/input");
		public static final By Program_Form_TextBox_Payee_Lastname = By.xpath("//input[contains(@name,'LastName') and contains(@name,'Payees_Attributes') and not(contains(@name,'ContactLastName'))]");
		public static final By Program_Form_TextBox_Payee_Firstname = By.xpath("//input[contains(@name,'FirstName') and contains(@name,'Payees_Attributes') and not(contains(@name,'ContactFirstName'))]");
		public static final By Program_Form_ButtonSave = By.id("saveButton-btnIconEl");
		public static final By Program_Form_MsgHolder = By.xpath(".//*[@id='topMessageHolder_bottom']/div/img");
		public static final By Program_Form_TopMsgHolder = By.id("topMessageHolder_bottom");
		public static final By Program_Form_Button_RequestPayment = By.id("requestPaymentButton-btnIconEl");
		public static final By Program_Form_Button_Submit = Locator.traksmartButtonAnchorStyleOnPopupTitle("Payment Selection", "Submit");
		public static final By Program_Form_Title = By.xpath(".//*[@id='title']/div/div/span[2]/a");
		public static final By Program_Form_PayeeBlock = By.xpath("//div[@id='webFormDesigner_center_regionId-body']//div[text()='Payee']");
		public static final By msg_formsave_success = By.xpath("//div[@class='global-message-holder' and contains(text(),'Form Saved Successfully')]");
		public static final By Program_Link_Payments = By.linkText("Payments");
		public static final By Program_Grid_Payments = By.className("x-grid-row-checker");
		public static final By Program_PaymentsForApproval_Chkbx_SelectAll = By.xpath("//div[@id='payment_grid']//div[contains(@class,'x-column-header-inner-empty')]/span");
		public static final By Program_Payments_Button_Proceed = By.id("Proceed-btnIconEl");
		public static final By Program_Payments_Button_Approve = By.id("Approve-btnIconEl");
		//public static final By Program_Payments_NoOfPayments = By.xpath("//div[@id='no_of_payments']//div[text()='2']|//div[@id='no_of_payments']//span[text()='Payments :']");
		public static final By Program_Payments_NoOfPayments = By.xpath("//div[@id='no_of_payments'][//span[text()='Payments :'] and //div[text()='1']]");
		public static final By Program_Payments_ChkboxandRejectPaymentslabel = By.xpath("//div[@id='RejectPayments'][//input[@role='checkbox'] and //label[text()='Reject Payments']]");
		public static final By Program_Payments_ChkboxandBatchEditlabel = By.xpath("//div[@id='edit_batch_payment'][//input[@role='checkbox'] and //label[text()='Batch Edit']]");
		public static final By Program_Payments_SearchFiltersOptions = By.xpath("//div[@class='filter_container'][//div[@class='filter_keyword_block'] and //div[@id='keyword_input_container'] and //div[@id='payment_type_container'] and //div[@id='kwf_filter_container'] and //div[@id='therm_filter_container']]");
		public static final By Program_Payments_btn_Payment = By.xpath("//input[@value='Payment']");
		public static final By Program_Payments_btn_PaymentDetails = By.xpath("//input[@value='Payment Details']");
		public static final By Program_Payments_SearchFiltersOnProcessedIndiPayments = By.xpath("//div[@id='program_payments_right_part'][//div[@class='filter_keyword_block'] and //div[@id='payment_type_container'] and //div[@id='kwf_filter_container'] and //div[@id='therm_filter_container']]");
		public static final By Program_Payments_PaymentStatus = By.xpath("//div[@class='prog_wrapper'][//div[text()='Payment Status'] and //div[text()='Approved']]");
		public static final By Program_Payments_totalamt = By.xpath("//span[text()='Total Amount:']/../..//div[contains(text(),'$')]");
		public static final By Program_Payments_total = By.xpath("//div[contains(text(),'$') and contains(@class,'x-grid-cell-inner')]");
		public static final By Program_Payments_amount1 = By.xpath("//table[@id='NestedMeasuerList_table']//td[contains(text(),'$')]");
		public static final By Email_MapTokens= By.xpath("//a[contains(text(),' Map Tokens ')]");
		
		
		
		public static final By Program_approvedpaymentrecods = By.xpath("//table");
		//public static final By Program_Payments_Button_Group = By.id("Group-btnIconEl");
		public static final By Program_Payments_Button_Group = By.xpath("//span[text()='Group']");
		public static final By Program_Payments_PopUp_GroupCostlines = By.xpath("//div[@class='main']/../div[contains(@id,'ext-comp')]");
		public static final By Program_Payments_GrpCostlines_Measurename= By.xpath("//div[text()='Measure Name']");
		public static final By Program_Payments_GrpCostlines_ProjectName= By.xpath("//div[text()='Project Name']");
		public static final By Program_Payments_GrpCostlines_AccountingCode= By.xpath("//div[text()='Accounting Code']");
		public static final By Program_Payments_GrpCostlines_PayToName= By.xpath("//div[text()='Pay To Name']");
		public static final By Program_Payments_GrpCostlines_Measurenametoselect = By.xpath("//span[contains(text(),'Measure Name')]");
		public static final By Program_Payments_GrpCostlines_ProjectNametoselect = By.xpath("//span[contains(text(),'Project Name')]");
		public static final By Program_Payments_GrpCostlines_AccountingCodetoselect = By.xpath("//span[contains(text(),'Accounting Code')]");
		public static final By Program_Payments_GrpCostlines_PayToNametoselect = By.xpath("//span[contains(text(),'Pay To Name')]");
		public static final By Program_Payments_GrpCostlines_btn_ResetGrouping = By.xpath("//div[@class='main']/../div[contains(@id,'ext-comp')]//span[text()='Reset Grouping']");
		//public static final By Program_Payments_GrpCostlines_chkbox_setasdefault = By.xpath("//div[@class='main']/../div[4]//label[text()='Set As Default']/../input");
		//public static final By Program_Payments_GrpCostlines_chkbox_setasdefault = By.xpath("//input[contains(@componentid,'checkbox')]");
		public static final By Program_Payments_GrpCostlines_chkbox_setasdefault = By.xpath("//label[text()='Set As Default']/../input");
		//public static final By Program_Payments_GrpCostlines_btn_Group = By.xpath("//div[@class='main']/../div[4]//span[text()='Group']");
		public static final By Program_Payments_GrpCostlines_btn_Group = By.xpath("//div[contains(@class,'x-window-closable')]//span[text()='Group']");
		public static final By Program_Payments_GrpCostlines_btn_deleteaccountcode = By.xpath("//div[contains(text(),'Accounting Code')]/../..//td//img");
		public static final By Program_Payments_GrpCostlines_chkbox_setasdefault1 = By.xpath("//div[@class='main']/../div[3]//label[text()='Set As Default']/../input");
		public static final By Program_Payments_GrpCostlines_btn_Group1 = By.xpath("//div[@class='main']/../div[5]//span[text()='Group']");
		//public static final By Program_Payments_GrpCostlines_btn_ResetGroup = By.xpath("//div[@class='main']/../div[7]//span[text()='Reset Grouping']");
		public static final By Program_Payments_GrpCostlines_btn_ResetGroup = By.xpath("//div[contains(@class,'x-window-closable')]//span[text()='Reset Grouping']");
		
		public static final By Program_Payments_GrpCostlines_btn_deleteselectedparams = By.xpath("//div[@class='main']/../div[4]//td//img");
		public static final By Program_Payments_GrpCostlines_btn_deletemeasure = By.xpath("//div[text()='Measure Name']/../..//td//img");
		public static final By Program_Payments_GrpCostlines_opt_Measurename = By.xpath("//div[text()='Measure Name']");
		public static final By Program_Payments_GrpCostlines_opt_Projectname = By.xpath("//div[text()='Project Name']");
		public static final By Program_Payments_GrpCostlines_opt_Accountingcode = By.xpath("//div[text()='Accounting Code']");
		public static final By Program_Payments_GrpCostlines_opt_Paytoname = By.xpath("//div[text()='Pay To Name']");
		
		public static final By Program_Payments_Button_SelectFileFormat = Locator.traksmartButtonAnchorStyleOnPopupTitle("Select File Format", "Save");
		public static final By Program_Payments_label_individual = By.xpath("//span[text()='Individual']");
		public static final By Program_Payments_drpdwn_reasoncode = By.id("reasonCode-inputEl");
		public static final By Program_Payments_txtarea_Description = By.name("description");
		public static final By Program_Payments_btn_void = By.xpath("//span[text()='Void']");
		public static final By Program_Payments_link_individualprocessespayment = By.linkText("Processed Individual Payments");
		public static final By Program_Payments_link_processesbatchpayment = By.linkText("Processed Batch Payments");
		public static final By Program_Payments_link_voidpayment = By.linkText("   Void Payment");
		public static final By Program_Payments_chkbox_batch = By.id("selectFileFormat-Batch-inputEl");
		public static final By Program_Payments_drpdwn_fileformat = By.id("fileFormat-inputEl");
		public static final By Program_Payments_drpdwn_paymentstatus = By.id("paymentStatus-inputEl");
		public static final By Program_Payments_label_Batch = By.xpath("//div[@class='res_content_block_top']/span[text()='Batch']");
		public static final By Program_Payments_label_BatchID = By.xpath("//div[@class='res_content_block_top']/span[contains(text(),'Batch ID')]");
		public static final By Program_Payments_label_GeneratedStatus = By.xpath("//div[@class='res_content_block_top']/span[contains(text(),'Generated')]");
		public static final By Program_Payments_batchdate = By.xpath("//div[contains(@class,'cell')]");
		public static final By Program_Payments_processedIndividualPaymentsDate = By.xpath("//div[@class='table_data_inner_cl']//div[@class='cell1']/a/..");
		public static final By Program_Payments_label_ReconciledStatus = By.xpath("//div[@class='res_content_block_top']/span[contains(text(),'Reconciled')]");
		public static final By Program_Payments_chkbox_batchedit = By.id("selectFileFormat-batchEdit-inputEl");
		public static final By Program_Payments_CostLine_txtbox_PayeeFirstName = By.xpath("//div[@id='edit-batch-costline-edit-popup']//label/span[text()='Payee First Name']/../..//input");
		public static final By Program_Payments_CostLine_txtbox_PayeeLastName = By.xpath("//div[@id='edit-batch-costline-edit-popup']//label/span[text()='Payee Last Name']/../..//input");
		public static final By Program_Payments_CostLine_txtbox_PayeeAddress1 = By.xpath("//div[@id='edit-batch-costline-edit-popup']//label/span[text()='Payee Address1']/../..//input");
		public static final By Program_Payments_CostLine_txtbox_PayeeAddress2 = By.xpath("//div[@id='edit-batch-costline-edit-popup']//label/span[text()='Payee Address2']/../..//input");
		public static final By Program_Payments_CostLine_txtbox_PayeeCity = By.xpath("//div[@id='edit-batch-costline-edit-popup']//label/span[text()='Payee City']/../..//input");
		public static final By Program_Payments_CostLine_txtbox_PayeeState = By.xpath("//div[@id='edit-batch-costline-edit-popup']//label/span[text()='Payee State/Province']/../..//input");
		public static final By Program_Payments_CostLine_txtbox_PayeeZip = By.xpath("//div[@id='edit-batch-costline-edit-popup']//label/span[text()='Payee Zip/Postal Code']/../..//input");
		public static final By Program_Payments_CostLine_txtbox_PayeeCountry = By.xpath("//div[@id='edit-batch-costline-edit-popup']//label/span[text()='Payee Country']/../..//input");
		public static final By Program_Payments_CostLine_BTN_SaveEditCostLines = By.xpath("//div[@id='edit-batch-costline-edit-popup']//span[text()='Save']");
		public static final By Program_Payments_CostLine_Label_RemitToInfo = By.xpath("//div[@id='edit-batch-costline-edit-popup']//div[text()='Remit To Information']");
		public static final By Program_Payments_CostLine_txtbox_RemitToFirstName = By.xpath("//div[@id='edit-batch-costline-edit-popup']//label/span[text()='RemitTo First Name']/../..//input");
		public static final By Program_Payments_CostLine_txtbox_RemitToLastName = By.xpath("//div[@id='edit-batch-costline-edit-popup']//label/span[text()='RemitTo Last Name']/../..//input");
		public static final By Program_Payments_CostLine_txtbox_RemitToAddress1 = By.xpath("//div[@id='edit-batch-costline-edit-popup']//label/span[text()='RemitTo Address1']/../..//input");
		public static final By Program_Payments_CostLine_txtbox_RemitToAddress2 = By.xpath("//div[@id='edit-batch-costline-edit-popup']//label/span[text()='RemitTo Address2']/../..//input");
		public static final By Program_Payments_CostLine_txtbox_RemitToCity = By.xpath("//div[@id='edit-batch-costline-edit-popup']//label/span[text()='RemitTo City']/../..//input");
		public static final By Program_Payments_CostLine_txtbox_RemitToState = By.xpath("//div[@id='edit-batch-costline-edit-popup']//label/span[text()='RemitTo State']/../..//input");
		public static final By Program_Payments_CostLine_txtbox_RemitToZIP = By.xpath("//div[@id='edit-batch-costline-edit-popup']//label/span[text()='RemitTo Zip/Postal Code']/../..//input");
		
		
		public static final By Program_Link_ProgSettings = By.linkText("Program Settings");
		public static final By Program_Link_ProgRenewal = By.id("program_renewable_field-inputEl");
		public static final By Program_Button_Save = By.xpath("//div[contains(@class,'widget_button_s_c top-nav-buttons')]//input[contains(@value,'Save')][1]");
		public static final By Program_tspan = By.xpath("//*[name()='svg']//*[name()='g'][7]//*[name()='text' and @class='fusioncharts-subcaption']//*[name()='tspan']");
		//public static final By Program_Progname = By.linkText(TestMeasureSavings.proj_name);
		public static final By Program_ActualSaveInfo = By.xpath("//div[@id='summary']/div[2]/div/div/div/div[3]//div[starts-with(@class,'x-component')]");	
		public static final By Program_Form_Block_Measure = By.xpath("//div[@id='webFormDesigner_center_regionId-body']//div[text()[.='Begin Measure Group']]");
		public static final By Program_Form_Block_MeasureLocation = By.xpath("//div[@id='programElementMeasuresListBoxId-body']//table/tbody/tr");
		public static final By Program_Form_Block3 = By.xpath("//*[@id='fdp-innerCt']//div[3]");
		public static final By Program_Form_Box = By.xpath("//div[contains(@id,'fdp-innerCt')]//div[3]//div[contains(@id,'box-')][6]");
		public static final By Program_Form_PersonnelFname = By.xpath("//*[@id='inputId']/..//label[text()='Personnel First Name:']");
		public static final By Program_Form_GridContainer = By.xpath("//div[@class='x-grid-item-container']/table[2]//tr/td[2]");
		public static final By Program_Form_ContactName = By.xpath("//div[text()='Contact Name']");
		public static final By Program_Form_Operator = By.xpath("//div[text()='operator']");
		public static final By Program_Form_AssemblyPopupWindowInput = By.xpath("//div[contains(@id,'formAssemblyPopupWindow-body')]//div[contains(@id,'-inputWrap')]/input");
		public static final By Program_Form_AssemblyPopupWindowGridContainer = By.xpath("//div[@class='x-grid-item-container']//tr/td[3]");
		public static final By Program_Form_AssemblyPopupWindowGridconditionValue = By.xpath("//div[contains(@id,'formAssemblyPopupWindow-body')]//div[contains(@id,'-inputWrap')]/input[@name='conditionValue']");
		public static final By Program_Form_AccountingCode = By.xpath("//div[text()[.='AccountingCode']]/../../td[2]/div");
		public static final By Program_Form_Button_Configure = By.xpath("//div[@id='webFormDesigner_center_regionId-body']//div[text()[.='Buttons']]");
		public static final By Program_Form_Button_Expand = By.xpath("//div[@id='webFormDesigner_center_regionId-body']//div[text()[.='Buttons']]/../../div[2]");
		public static final By Program_Form_Button_FormAssembly_Save = By.xpath("//button[text()[.='Save'] and contains(@class, 'formAssembly_button_save')]/../../..");
		public static final By Program_WorkFlow_Img_Traksmart = By.xpath("//div[contains(@class, 'traksmart_img')]");
		public static final By Program_WorkFlow_TextBox_Username = By.id("username");
		public static final By Program_WorkFlow_TextBox_Password = By.id("password");
		public static final By Program_WorkFlow_btn_Logout = By.xpath("//button[text()='Logout']");
		public static final By Program_WorkFlow_Button_Submit = By.xpath("//div/input[@name='submit']");
		public static final By Program_WorkFlow_Title_EditBusinessProcess = By.xpath("//div[contains(@class,'x-window-tl')]//span[contains(text(),'Edit Business Process')]");
		public static final By Program_WorkFlow_WestArrow = By.className("x-tool-expand-west");
		public static final By Program_WorkFlow_EastArrow = By.className("x-tool-expand-east");
		public static final By Program_WorkFlow_Editor_Area = By.id("ext-comp-1005");
		public static final By Program_WorkFlow_Event_Start = By.id("extdd-20");
		public static final By Program_WorkFlow_Wapama_Right = By.cssSelector(".Wapama_Right");
		public static final By Program_WorkFlow_Btn_Wapama = By.className("Wapama_button");
		public static final By Program_WorkFlow_GridGrp = By.className("x-grid-group-body");
		public static final By Program_WorkFlow_Row_GridGrp = By.className("x-grid3-row");
		public static final By Program_WorkFlow_TeamParticipant = By.tagName("td");		
		public static final By Program_WorkFlow_Button_Save = By.xpath("//button[contains(@style, 'save.png')]");
		public static final By Program_WorkFlow_Button_Deploy = By.xpath("//button[text()[.='Deploy']]");
		public static final By Program_WorkFlow_Button_Deploy1 = By.id("ext-gen829");
		public static final By Program_WorkFlow_Button_OK = By.xpath("//div[@class='x-window-mc']//button[text()[.='OK']]");
		public static final By Program_WorkFlow_Button_OK1 = By.xpath("//button[text()[.='OK']]");
		public static final By Program_TestCreateProject_Button_Refresh = By.xpath("//input[@value='Refresh']"); 
		public static final By Program_TestCreateProject_Button_CreateNewProject = By.xpath("//input[@value = '+ Create A New Project']");
		public static final By Program_TestCreateProject_Link_ProjectName = By.xpath("//div[@id='projects']//a[text()[contains(.,'" + Locator.Programs.Project_Number_Prefix_Name + "')]]");
		public static final By Program_TestCreateProject_CheckBox_ProjectFilesPopUp =  By.xpath("//div[@id='projectfilespopup']//div[text()[.='Add File ?']]/..//input");
		public static final By Program_TestCreateProject_TextBox_Incentives_Customers = By.xpath("//label/span[text()[contains(.,'Incentive - Customer:')]]/../..//input[@type='text']");
		public static final By Program_TestCreateProject_TextBox_Payee_CompanyName = By.xpath("//div[text()[.='Payee']]/../../../../..//span[text()[contains(.,'Company Name:')]]/../..//input[@type='text']");
		public static final By Program_TestCreateProject_Label_Status = By.xpath("//label/span[text()[contains(.,'Status:')]]/../..//input[@type='text']");
		public static final By Program_TestCreateProject_Label_Status_Summary = By.xpath("//*[@id='summary']//div/b[text()[.='Completed']]");
		public static final By Program_ADDDSMTestProjectInput_Subnav_Performance = By.xpath("//div[@id='subnav']//a[text()[contains(.,'Performance')]]");
		public static final By Program_ADDDSMTestProjectInput_Button_AddProjectInput = By.xpath("//input[@value = '+ Add Project Input']");
		public static final By Program_MakePayments_ChkBox_Header = By.cssSelector(".x-column-header-checkbox div");
		public static final By Program_MakePayments_Labels_Payments = By.xpath("//span[text()[.='Payments :']]/../../div/div");
		public static final By Program_MakePayments_Labels_TotalPayments = By.xpath("//span[text()[.='Total to pay:']]/../../div/div");
		public static final By Program_MakePayments_Grid_ProcessedBatchPayments = By.xpath("(//div[@class='x-grid-item-container']/table/tbody/tr/td)");
		public static final By Program_MakePayments_Link_Download = By.xpath("//div[@class='payment_prog_wrapper']/a[text()='+Download File']");
		public static final By Program_MakePayments_Link_Reconcile = By.xpath("//div[@class='payment_prog_wrapper']/a[text()=' +Reconcile']");
		//public static final By Program_MakePayments_textbox_ReconcileFile = By.xpath("//span[text()='Reconcile File:']/../..//input[@role='textbox']");
		public static final By Program_MakePayments_textbox_ReconcileFile = By.xpath("//input[@name='fileName']");
		public static final By Program_MakePayments_btn_upload = By.xpath("//span[text()='Upload']");
		public static final By Program_MakePayments_btn_browse = By.xpath("//span[text()='Browse']");		
		public static final By Program_MakePayments_Link_Payments = Locator.anchorTextContains("Payments");
		public static final By Program_MakePayments_Link_ManagePayementsTemplates = By.linkText("Manage Payments Templates");
		public static final By Program_MakePayments_Link_AddNewPaymentFileType = By.linkText("+Add New Payment File Type");
		public static final By Program_MakePayments_Button_Save = Locator.inputWithValue("Save");
		public static final By Program_MakePayments_Button_Delete = Locator.inputWithValue("Delete");		
		public static final By Program_Forms_FormsEditor = Locator.anchorTextContains("Forms Editor");
		public static final By link_logout = By.linkText("Logout");
		public static final By Program_Pagination_Btn_Next = By.xpath("//span[contains(@class, 'x-tbar-page-next')]");
		public static final By Program_Dashboard_PerfPortfolio  = By.xpath("//div[@id='lineChartGlobalId_chart']/span/*[name()='svg']/*[name()='g'][@class='fusioncharts-legend']/*[name()='rect'][7]");
		public static final By Program_Dashboard_Coordinates = By.xpath("//div[@id='lineChartGlobalId_chart']/span/*[name()='svg']/*[name()='g'][5]");
		public static final By Program_Dashboard_Coordinates1 = By.xpath("//div[@id='lineChartGlobalId_chart']/span/*[name()='svg']/*[name()='g'][4]/*[name()='g'][3]/*[name()='g'][7]//*[name()='path']");
		public static final By Program_Dashboard_Coordinates2 = By.xpath("//span[@id='fusioncharts-tooltip-element']/span[contains(text(),'45.0')]");
		public static final By Program_ServiceProvider = By.id("service_provider_field-inputEl");
		public static final By link_progenrolset = By.linkText("Program Enroll Setting");
		public static final By txtbox_publicname = By.id("publicName-inputEl");
		public static final By txtbox_publicdesc = By.id("publicDescription-inputEl");
		public static final By txtbox_publicsumm = By.id("publicSummary-inputEl");
		public static final By txtbox_publicaddNewCategory = By.id("newCategory-inputEl");
		public static final By Button_publicaddNewCategory = By.xpath(".//span[text()='Add Category']");
		
		public static final By drpdwn_progcat = By.id("program_category_field_combobox-inputEl");
		public static final By partnerblock = By.xpath("//div[@id='fdp-innerCt']//div[text()='Partner']");
		public static final By partnera = By.xpath("//div[contains(@id,'fdp-innerCt')]//div[3]//div[contains(@id,'box-')][6]");
		public static final By partner_section_expand = By.xpath("//div[@id='fdp-innerCt']//div[text()='Partner']/../..//div[@class='x-component section_expand x-box-item x-component-default x-component-after-title']");
		public static final By partner_sectionfname = By.xpath("//*[@id='inputId']/..//label[text()='Personnel First Name:']");
		public static final By btn_save = By.id("button-1067-btnIconEl");
		public static final By programEnrollmentSave = By.xpath(".//span[text()='Save']//ancestor::a");
		public static final By beginmeasuregrp = By.xpath("//div[@id='fdp-innerCt']//div[text()='Begin Measure Group']");
		public static final By beginmeasuregrp_PUI_Form = By.xpath("//div[text()='Begin Measure Group']");
		public static final By displaycond = By.xpath("//div[text()='DisplayCondition']/../../td[contains(@class,'x-grid-cell-value')]/div");
		public static final By clonecond = By.xpath("//div[text()='Clone Condition']/../../td[contains(@class,'x-grid-cell-value')]/div");
		public static final By gridblank = By.xpath("//div[@class='x-grid-item-container']//b[text()='BLANK']/../..");
		public static final By checkbox_autoapprovepayment = By.id("program_approval_payment_field-inputEl");
		public static final By drpdwn_approvaltype = By.id("project_assignee_type_input");
		public static final By drpdwn_approvaltype_option = By.xpath("//li[text()='None']");
		
		public static final By popupmsg_autoapprovesuccess = By.xpath("//div[contains(@id,'messagebox')]/div[text()='Payment Approved successfully']");
		
		public static final By searchFilterDropdown = By.xpath("//input[@role='combobox']");
		  public static final By viewPaymentDetails = By.id("payments-details-view");
		  //public static final By paymentStatus = By.xpath("//div[@class='prog_label' and text()='Payment Status']/following-sibling::div[1]");
		  public static final By paymentStatus = By.xpath("//div[text()='Payment Line Status']//following-sibling::div");
		  public static final By paymentStatusInPaymentPage = By.xpath("//div[text()='Payment Status']//following-sibling::div");
		  public static final By applicationNumber = By.xpath("//div[text()='Application Number']//following-sibling::div");
		  public static final By payToName = By.xpath("//div[text()='Pay To Name']//following-sibling::div");
		  public static final By projectName = By.xpath("//div[text()='Project Name']//following-sibling::div");
		  public static final By totalAmount = By.xpath("//div[text()='Total Amount']//following-sibling::div");
		  
		  public static final By accountCode = By.xpath("//span[text()='Acct Code:']//following-sibling::span");
		  
		  public static final By searchComboBox1 = By.xpath("//div[@id='keyword_search_container']//div[1][@class='dynamic_inner_form_items']//div//input[@role='combobox']");
		  public static final By searchTextBox1 = By.xpath("//input[@componentid='keyword_1_field']");
		  public static final By paymentDetailsButton = By.xpath("//*[@type='button' and @value='Payment Details']");
		  public static final By searchButton = By.xpath("//*[@type='button' and @value='Search']");
		  public static final By addKeyWordButton = By.linkText("+ Add Keyword");
		  public static final By searchComboBox2 = By.xpath("//div[@id='keyword_search_container']//div[2][@class='dynamic_inner_form_items']//div//input[@role='combobox']");
		  public static final By searchTextBox2 = By.xpath("//input[@componentid='keyword_2_field']");
		  
		  public static final By searchComboBox3 = By.xpath("//div[@id='keyword_search_container']//div[3][@class='dynamic_inner_form_items']//div//input[@role='combobox']");
		  public static final By searchTextBox3 = By.xpath("//input[@componentid='keyword_3_field']");
		  
		  public static final By searchComboBox4 = By.xpath("//div[@id='keyword_search_container']//div[4][@class='dynamic_inner_form_items']//div//input[@role='combobox']");
		  public static final By searchTextBox4 = By.xpath("//input[@componentid='keyword_4_field']");

		  public static final By searchComboBox5 = By.xpath("//div[@id='keyword_search_container']//div[5][@class='dynamic_inner_form_items']//div//input[@role='combobox']");
		  public static final By searchTextBox5 = By.xpath("//input[@componentid='keyword_5_field']");
		  
		  
		  public static final By searchComboBox6 = By.xpath("//div[@id='keyword_search_container']//div[6][@class='dynamic_inner_form_items']//div//input[@role='combobox']");
		  public static final By searchTextBox6 = By.xpath("//input[@componentid='keyword_6_field']");
		  
		  public static final By searchComboBox7 = By.xpath("//div[@id='keyword_search_container']//div[2][@class='dynamic_inner_form_items']//div//input[@role='combobox']");
		  public static final By searchTextBox7 = By.xpath("//div[@id='keyword_search_container']//div[2][@class='dynamic_inner_form_items']//div//input[@role='textbox']']");
		  
		  public static final By searchComboBox8 = By.xpath("//div[@id='keyword_search_container']//div[2][@class='dynamic_inner_form_items']//div//input[@role='combobox']");
		  public static final By searchTextBox8 = By.xpath("//div[@id='keyword_search_container']//div[2][@class='dynamic_inner_form_items']//div//input[@role='textbox']']");
		  
		  public static final By searchComboBox9 = By.xpath("//div[@id='keyword_search_container']//div[2][@class='dynamic_inner_form_items']//div//input[@role='combobox']");
		  public static final By searchTextBox9 = By.xpath("//div[@id='keyword_search_container']//div[2][@class='dynamic_inner_form_items']//div//input[@role='textbox']']");
		  
		  public static final By searchComboBox10 = By.xpath("//div[@id='keyword_search_container']//div[2][@class='dynamic_inner_form_items']//div//input[@role='combobox']");
		  public static final By searchTextBox10 = By.xpath("//div[@id='keyword_search_container']//div[2][@class='dynamic_inner_form_items']//div//input[@role='textbox']']");
		  
		  public static final By cancelButton = By.xpath(".//input[@type='button' and @value='Cancel']");
		  
		  public static final By paymentButton = By.xpath(".//input[@type='button' and @value='Payment']");
		  
		  public static final By amountRangeFromTextBox = By.xpath(".//input[@type='text' and @componentid='amId']");
		  public static final By amountRangeToTextBox = By.xpath(".//input[@type='text' and @componentid='amToId']");
		  
		  public static final By kwhFilterRangeFromTextBox = By.xpath(".//input[@type='text' and @componentid='kwId']");
		  public static final By kwhFilterRangeToTextBox = By.xpath(".//input[@type='text' and @componentid='kwToID']");
		  
		  public static final By thermFilterRangeFromTextBox = By.xpath(".//input[@type='text' and @componentid='thermId']");
		  public static final By thermFilterRangeToTextBox = By.xpath(".//input[@type='text' and @componentid='thermToID']");
		  public static final By Program_Btn_SaveAS = By.id("saveAsButtonTop");
		  public static final By Program_ProgramSettings_Btn_Save = By.xpath("//input[@class='customize_save_button' and @value='Save']");
		  public static final By workFlow_btn_Action = By.xpath("//button[contains(text(),'Actions')]");
		  public static final By workFlow_menu_Clone = By.xpath("//a[(text()='Clone')]");
		  public static final By workFlow_Submenu_DeepClone = By.xpath("//a[contains(text(),'Deep Clone')]");
		  public static final By workflow_ItemsAvl_Projects = By.xpath("//div[text()='Projects']");
		  public static final By workflow_ItemsAvl_Events = By.xpath("//div[text()='Events']");
		  public static final By workflow_ItemsAvl_Tasks = By.xpath("//div[text()='Tasks']");
		  public static final By workflow_ItemsAvl_DeploymentUnits = By.xpath("//div[text()='Deployment Units']");
		  public static final By workflow_Btn_Clone = By.xpath("//button[text()='Clone']");
		  //public static final By workflow_txtbox_copyprogname = By.xpath("//div[@class='x-window x-window-plain']//input[@name='name']");
		  public static final By workflow_txtbox_copyprogname = By.xpath("//body/div[contains(@class,'x-window-maximized')]//div[@class='x-panel-bwrap']//label[text()='Name']/..//input");
		 // public static final By workflow_btn_Savecopyprogram = By.xpath("//div[@class='x-window x-window-plain']//button[text()='Save']");
		  public static final By workflow_btn_Savecopyprogram = By.xpath("//div[contains(@class,'x-window-maximized')]//button[text()='Save']");
		  public static final By workflow_btn_DeploycopyProgram = By.xpath("//div[contains(@class,'x-window-maximized')]//button[text()='Deploy']");
		  public static final By ProgramSetting_Process_ID = By.xpath("//input[contains(@id,'process_id_field')]");
		  public static final By ProgramSetting_ProgramStatus = By.xpath("//input[@id='program_status_field-inputEl']");
		  public static final By Programcopy_Btn_Save = By.xpath("//div[@class='widget_button_s_c_footer']//input[@value='Save']");
		  public static final By WorkFlow_Tab_Design = By.xpath("//div[contains(@class,'x-window-maximized')]//span[text()='Design']");
		  public static final By Program_CopyProgramName = By.xpath("//div[@class='breadcrumb_links']//a");
		  public static final By Program_CopyProgram_eastarrow = By.xpath(".//*[contains(@id,'xcollapsed') and contains(@class,'x-layout-collapsed-east')]/xhtml:div");
		  public static final By WorkFlow_Frame_Canvas = By.xpath("//div[contains(@class,'x-window-maximized')]//iframe[contains(@scrolling,'auto')]");
		  public static final By WorkFlow_SVG_Rectangle_Task = By.xpath("//*[name()='svg']//*[name()='tspan']");
		  public static final By WorkFlow_TraksmartWebID = By.xpath("//xhtml:div[text()='Traksmart Web Form ID']/../..//xhtml:div[contains(@class,'propertywindow_column_value')]");
		  //public static final By WorkFlow_TraksmartInput = By.xpath("//xhtml:input");
		  public static final By WorkFlow_TraksmartInput = By.xpath("//xhtml:input[contains(@class,'x-form-text x-form-field x-form-focus')]");
		  //public static final By Programs_Btn_CreateVersion = By.xpath("//div[@class='widget_button_s_c top-nav-buttons']/input[@value='Create Version']");
		  public static final By Programs_Btn_CreateVersion = By.xpath("//input[@value='Create Version' and @class='customize_save_button']");
		  public static final By Program_Form_btn_minimize = By.xpath("//div[text()='Program Elements']/../..//img");
		  public static final By Program_Form_Element_ApplicationStatus = By.xpath("//table//div[text()='Application Status']");
		  public static final By Program_AllowProjectCopy = By.id("program_projectCopy_field-inputEl");
		  public static final By Program_Form_Element_ApplicationPriority = By.xpath("//table//div[text()='Application Priority']");
		  public static final By Program_Form_ConfigureFormAssignmentType = By.xpath("//span[text()='Type']/../..//input");
		  public static final By Program_Form_ConfigureFormAssignmentManualAssignment = By.xpath("//span[contains(text(),'Manual Assignment')]/../..//input");
		  public static final By Program_link_programeditor = By.xpath(".//*[@id='intalioUrl']/a");
		  public static final By program_Managers = By.id("program_programmgrs_0_field_combobox-inputEl");
		  public static final By support_Staff = By.id("program_supportstaff_0_field_combobox-inputEl");
		  public static final By projectOwnerAssignmentType = By.id("project_assignee_type_input");
		  public static final By autoAssignmentField = By.id("project_auto_assignment_input");
		  public static final By manualAssignmentField = By.id("project_manual_assignment_input");
		  public static final By Activiti_login_username = By.id("username");
		  public static final By Activiti_login_password = By.id("password");
		  public static final By Activiti_Btn_login = By.id("login");
		  public static final By Activiti_popup_tour = By.xpath("//div[@class='popover tour orphan tour-activitiEditorTour tour-activitiEditorTour-0 fade top in']");
		  public static final By Activiti_startevent = By.id("StartNoneEvent");
		  public static final By Activiti_StartEvent_in_Canvassection = By.xpath("//div[@class='ORYX_Editor']//*[name()='g' and @title='BPMN-EDITOR.ITEM.START-NONE-EVENT.TITLE']");
		  public static final By Activiti_canvas_section = By.id("canvasSection");
		  public static final By Activiti_canvasarea_img_usertask = By.xpath("//div[@id='canvasSection']//div[@id='UserTask']/img");
		  public static final By Activiti_usertaskname = By.xpath(".//*[@id='propertySection']//span[contains(text(),'Name')]/../span[@class='value']");
		  public static final By Activiti_textbox_usertaskname = By.xpath(".//*[@id='propertySection']//span[contains(text(),'Name')]/../span[@class='value']/ng-include/div/input");
		  public static final By Activiti_textbox_Gateway = By.xpath("//div[@id='canvasHelpWrapper']//*[@id='ExclusiveGateway']/img");
		  //public static final By Activiti_textbox_Gateway_Condition = By.xpath("//div[@class='ORYX_Editor']//*[name()='g' and @class='svgcontainer']/*[name()='g'][1]/*[name()='rect']");
		  public static final By Activiti_textbox_Gateway_Condition = By.xpath("//div[@class='ORYX_Editor']//*[name()='g' and @stroke='none']//*[name()='g' and @title='BPMN-EDITOR.ITEM.EXCLUSIVE-GATEWAY.TITLE' ]");
		  public static final By Activiti_endtask_main = By.xpath("//div[@class='ORYX_Editor']//*[name()='g' and @title='BPMN-EDITOR.ITEM.END-NONE-EVENT.TITLE' ]/*[name()='circle']");
		  public static final By Activiti_textbox_ = By.xpath("//div[@id='canvasHelpWrapper']//*[@id='ExclusiveGateway']/img");
		  public static final By Activiti_formkey = By.xpath(".//*[@id='propertySection']//span[contains(text(),'Form key')]/../span[@class='value']");
		  public static final By Activiti_drpdwn_formkey = By.xpath(".//*[@id='propertySection']//span[contains(text(),'Form key')]/../span[@class='value']/ng-include/div/select");
		  public static final By Activiti_endtask = By.xpath("//div[@id='canvasSection']//*[@id='EndNoneEvent']/img");
		  public static final By Activiti_Set_Condition = By.xpath(".//*[name()='g' and @class='edge']/*[name()='g'][3]//*[name()='g' and @pointer-events='painted']");
		  public static final By Activiti_Set_Condition_ButtonApply = By.xpath("//button[text()='Apply']");
		  public static final By Activiti_Set_DefaultFlow = By.xpath(".//*[name()='g' and @class='edge']/*[name()='g'][3]//*[name()='g' and @pointer-events='painted']");
		  public static final By Activiti_Set_Checkbox_DefaultFlow = By.xpath("//input[@type='checkbox']");
		  public static final By Activiti_Textarea_ConditionExpression = By.xpath("//textarea");
		  public static final By Activiti_img_Save = By.xpath("//button[@title='Save the model' and .!=contains(@class,'separator')]");
		  public static final By Activiti_Set_LoopBackCondition = By.xpath(".//*[name()='g' and @class='edge']/*[name()='g'][4]//*[name()='g' and @pointer-events='painted']");
		  public static final By Activiti_Set_Condition2 = By.xpath(".//*[name()='g' and @class='children']/*[name()='g'][4]");
		  public static final By Activiti_Set_Condition1 = By.xpath(".//*[name()='g' and @class='children']/*[name()='g'][2]");
		  
		  public static final By Activiti_canvasarea_img_sequenceflow = By.xpath("//div[@id='canvasSection']//div[@id='SequenceFlow']/img");
		  public static final By Activiti_Set_LoopBackConditionwithdefault = By.xpath(".//*[name()='g' and @class='edge']/*[name()='g'][6]//*[name()='g' and @pointer-events='painted']");
		  
		  
		  
		  public static final By Aciviti_btn_Save = By.xpath("//button[text()='Save']");
		  public static final By Activiti_img_deploy = By.xpath("//button[@title='Deploy']/i");
		  public static final By Actviti_btn_designer = By.xpath("//div[@class='dropdown btn-group btn-group-sm']/button");
		  public static final By Activiti_signout = By.xpath("//div[@class='dropdown btn-group btn-group-sm open']//a[text()='Sign out']");
		  public static final By Activiti_Close = By.xpath("//button[@title='Close']");
		  public static final By Activiti_duplicatemodel = By.xpath("//button[@title='Duplicate this model']");
		  public static final By Activiti_textbox_modelname = By.id("newProcessName");
		  public static final By Activiti_btn_duplicatethemodel = By.xpath("//div[@class='modal-footer']//button[.!=contains(text(),'Cancel')]");
		  //public static final By Activiti_usertaskblock = By.xpath("//div[@class='ORYX_Editor']//*[name()='g' and @class='me']//*[name()='rect'][1]");
		  public static final By Activiti_usertaskblock = By.xpath("//div[@id='canvasHelpWrapper']//div[@class='ORYX_Editor']/*[name()='svg']//*[name()='g' and @class='me']//*[name()='text']/*[name()='tspan' and text()='Test Task']");
		  public static final By CopyProgName = By.xpath("//input[@componentid='program_name_field']");
		  public static final By lastupdatedtime = By.xpath("//div[@class='widget_content_container_div prog_row']//span[@class='pg_blk_links pg_update']");
		  public static final By listofprogramnames = By.xpath("//div[@class='widget_content_container_div prog_row']//span[@class='pg_blk_links pg_name']/a");
		  public static final By firstProjectinStatusWiseProjectSubTabs = By.xpath(".//div[@class='x-grid-item-container']//table[1]//span[@class='pg_blk_links pg_name']");
		  public static final By firstProjectTitleinStatusWiseProjectSubTabs = By.xpath(".//div[@class='x-grid-item-container']//table[1]//span[@class='pg_blk_links pg_name']//text()");
		  //public static final By Program_BudgetAccounting_Button_ManageBudgetCategories = By.xpath("//input[@value='+ Manage Budget Categories']");
		  //public static final By Program_BudgetAccounting_Button_AddNewCategory = By.id("addCategoryRowLink");
		  //public static final By Program_BudgetAccounting_TextBox_CategoryName = By.xpath("//div[@id='manage_budget_category_popup']//input[@type='text']");
		  //public static final By Program_BudgetAccounting_Dropdown_Status = By.xpath("//input[@name='status_combo']");
		  public static final By Program_BudgetAccounting_Checkbox_Active = By.xpath("//input[@role='checkbox']");
		  public static final By Program_BudgetAccounting_Button_Update = By.xpath("//span[text()='Update']");
		  //public static final By Program_BudgetAccounting_Button_Save = By.xpath("//input[@class='button primary']");
		  public static final By Program_BudgetAccounting_Button_CreateBudget = By.id("btnCreateBudgets");
		  public static final By Program_BudgetAccounting_CreateBudgetButton_AddNewBudget = By.id("addBudgetRowLink");
		  public static final By Program_BudgetAccounting_CreateBudgetDropdown_Category = By.xpath("//input[@id='budget_categories_combobox-inputEl']");
		  public static final By Program_BudgetAccounting_CreateBudgetTextbox_Name = By.xpath("//div[contains(@id,'roweditor')]//input[contains(@name,'textfield')]");
		  public static final By Program_BudgetAccounting_CreateBudgetTextbox_Amount = By.xpath("//div[contains(@id,'roweditor')]//input[contains(@name,'numberfield')]");
		  public static final By Program_BudgetAccounting_CreateBudgetDate_Effective = By.id("effectiveDateStringId-inputEl");
		  public static final By Program_BudgetAccounting_CreateBudget_ClickNextMonth = By.xpath("//div[contains(@id,'datepicker')]//div[contains(@title,'Next Month (Control+Right)')]");
		  public static final By Program_BudgetAccounting_CreateBudgetDate_Expire = By.id("expirationDateStringId-inputEl");
		  public static final By Program_BudgetAccounting_CreateBudgetButton_Delete = By.xpath("//div[text()='Delete']");
		  public static final By Program_BudgetAccounting_CreateBudgetButton_Update = By.xpath("//span[text()='Update']");
		  public static final By Program_BudgetAccounting_CreateBudgetButton_Cancel = By.xpath("//span[text()='Cancel']");
		  public static final By Program_BudgetAccounting_CreateBudgetPopUpButton_Save = By.xpath("//div[@id='create_budget_popup']//input[@value='Save']");
		  public static final By Program_BudgetAccounting_CreateBudgetPopUpButton_Close = By.xpath("//div[@id='create_budget_popup']//input[@value='Close']");
		  public static final By Program_label_kwhsavings = By.xpath("//label[text()='kWh Savings:']");
		  public static final By Program_label_kwsavings = By.xpath("//label[text()='KW Savings:']");
		  public static final By Program_label_kwreduction = By.xpath("//label[text()='kW Reduction:']");
		  public static final By Program_label_kwinstalled = By.xpath("//label[text()='kW Installed:']");
		  public static final By Program_label_incentivescustomers = By.xpath("//label[text()='Incentive - Customer:']");
		  public static final By Program_label_watersavings = By.xpath("//label[text()='Water Savings (Gal):']");
		  public static final By Program_label_thermsavings = By.xpath("//label[text()='Therm Savings:']");		 
		  public static final By Program_Link_BatchReview = By.linkText("Batch Review");
		  public static final By Program_Project_Records_On_BatchReviewTasks = By.xpath("//table");
		  public static final By Program_BatchReviewChart_Title =By.xpath("//div[contains(@id,'batchreviewcharts')]//div[contains(@id,'title') and contains(@class,'x-title-text')]");
		  public static final By Program_BatchReviewChart_Rebates_Target = By.xpath("//span[text()='Rebates']/../..//span[text()='Target:']/../..//div[@role='textbox']");
		  public static final By Program_BatchReviewChart_Rebates_Paid = By.xpath("//span[text()='Rebates']/../..//span[text()='Paid:']/../..//div[@role='textbox']");
		  public static final By Program_BatchReviewChart_Rebates_Projected = By.xpath("//span[text()='Rebates']/../..//span[text()='Projected:']/../..//div[@role='textbox']");
		  public static final By Program_BatchReviewChart_kwhsavings_Target = By.xpath("//span[text()='kWh Savings']/../..//span[text()='Target:']/../..//div[@role='textbox']");
		  public static final By Program_BatchReviewChart_kwhsavings_Paid = By.xpath("//span[text()='kWh Savings']/../..//span[text()='Saved:']/../..//div[@role='textbox']");
		  public static final By Program_BatchReviewChart_kwhsavings_Projected = By.xpath("//span[text()='kWh Savings']/../..//span[text()='Projected:']/../..//div[@role='textbox']");
		  public static final By Program_BatchReviewChart_kwsavings_Target = By.xpath("//span[text()='kW Savings']/../..//span[text()='Target:']/../..//div[@role='textbox']");
		  public static final By Program_BatchReviewChart_kwsavings_Paid = By.xpath("//span[text()='kW Savings']/../..//span[text()='Saved:']/../..//div[@role='textbox']");
		  public static final By Program_BatchReviewChart_kwsavings_Projected = By.xpath("//span[text()='kW Savings']/../..//span[text()='Projected:']/../..//div[@role='textbox']");
		  public static final By Program_BatchReviewChart_thermsavings_Target = By.xpath("//span[text()='Therm Savings']/../..//span[text()='Target:']/../..//div[@role='textbox']");
		  public static final By Program_BatchReviewChart_thermsavings_Paid = By.xpath("//span[text()='Therm Savings']/../..//span[text()='Saved:']/../..//div[@role='textbox']");
		  public static final By Program_BatchReviewChart_thermsavings_Projected = By.xpath("//span[text()='Therm Savings']/../..//span[text()='Projected:']/../..//div[@role='textbox']");
		  public static final By Program_BatchReviewChart_kwinstalled_Target = By.xpath("//span[text()='kW Installed']/../..//span[text()='Target:']/../..//div[@role='textbox']");
		  public static final By Program_BatchReviewChart_kwinstalled_Paid = By.xpath("//span[text()='kW Installed']/../..//span[text()='Installed:']/../..//div[@role='textbox']");
		  public static final By Program_BatchReviewChart_kwinstalled_Projected = By.xpath("//span[text()='kW Installed']/../..//span[text()='Projected:']/../..//div[@role='textbox']");
		  public static final By Program_BatchReviewChart_galsavings_Target = By.xpath("//span[text()='Gal Savings']/../..//span[text()='Target:']/../..//div[@role='textbox']");
		  public static final By Program_BatchReviewChart_galsavings_Paid = By.xpath("//span[text()='Gal Savings']/../..//span[text()='Saved:']/../..//div[@role='textbox']");
		  public static final By Program_BatchReviewChart_galsavings_Projected = By.xpath("//span[text()='Gal Savings']/../..//span[text()='Projected:']/../..//div[@role='textbox']");
		  public static final By Program_BatchReviewChart_Validation_Msg = By.xpath("//div[contains(@id,'messagebox')]//div[text()='No record has been selected. Please make a valid selection.']");
		  public static final By Program_variablecostonpaymentpage = By.xpath("//span[text()='Total to pay:']/../..//div[@id='total_to_pay-inputEl']");
		  public static final By Program_variablecostonpaymentpage1 = By.xpath("//div[text()='Total Amount:']/../div[@class='prog_data']");
		  public static final By Program_acccodeon_processedindividualpayment = By.xpath("//span[text()='Acct Code:']/../span[contains(@class,'payee_name_value')]");
		  public static final By Program_variablecoston_processedindividualpayment = By.xpath("//div[text()='Total Amount']/../div[@class='prog_data']");
		  public static final By Program_externalref_processedindividualpayment = By.xpath("//div[text()='External Ref']/../div[@class='prog_data']");
		  public static final By Program_validationmsg_duplicateVariablecostname = By.xpath("//div[@class='project-notes-warning']/span");
		  public static final By Program_budgetandaccounting_dropdownacccode = By.id("variable_costs_accountCodeId-inputEl");
		  public static final By Program_FixedCost_SuccessMsg = By.xpath(".//*[@id='topMessageHolder']/div");
		  public static final By Program_payToName = By.xpath("//span[text()='Pay To Name : ']/span");
		  public static final By Program_BatchReview_FilterByFormDrpdwn = By.xpath("//div[@id='formFilter-inputWrap']/following-sibling::div");
		  public static final By Program_BatchReview_FilterByInputBox = By.id("formFilter-inputEl");
		  public static final By Program_BatchReview_Form1 = By.xpath("//a[contains(text(),'Test Task1') and .!=contains(text(), 'Test Task2')]");
		  public static final By Program_BatchReview_Form2 = By.xpath("//a[contains(text(),'Test Task2') and .!=contains(text(), 'Test Task1')]");
		  public static final By Program_BatchReview_ForNone = By.xpath("//a[contains(text(),'Test Task2') or contains(text(), 'Test Task1')]");
		  public static final By Program_BatchReview_editicon = By.xpath("//a[@class='edit_icon']");
		  public static final By Program_Form_Required_Checkbox = By.xpath("//input[@role='checkbox']");
		  public static final By Program_BatchReview_SelectStatus = By.id("bulkEditStatus-inputEl");
		  public static final By Program_BatchReview_attributesvalidationsmsg = By.xpath("//div[@id='globalMessages']/div");
		  public static final By Program_BatchReview_ReadOlyAttribute_Apply = By.xpath(".//input[@value='Apply']");
		  public static final By Program_BatchReview_EditWindow_Cancel = By.xpath(".//a[@id='create_batchpaymentedittasks_popup_panel_cancel']");
		  public static final By Program_Edit_Icon = By.xpath(".//a[@class='edit_icon']");
		  public static final By Program_BatchReview_kWh_Savings_Edit = By.xpath(".//input[@role='textbox' and @name ='kWhSavings']");
		  public static final By Program_BatchReview_kWh_Installed_Edit =By.xpath(".//input[@role='textbox' and @name ='kWInstalled']");
		    public static final By Program_BatchReview_kWh_Reduction_Edit = By.xpath(".//input[@role='textbox' and @name ='kWReduction']");
		    public static final By Program_BatchReview_Water_Savings_Edit = By.xpath(".//input[@role='textbox' and @name ='WaterSavings']");
		    public static final By Program_BatchReview_Therm_Savings_Edit = By.xpath(".//input[@role='textbox' and @name ='ThermSavings']");
		    public static final By Program_batchReview_Measure_EditWindow_saveButton = By.xpath(".//span[@id='create_batchpaymentedittasks_popup_panel_save-btnIconEl']");
		    public static final By Program_batchReview_Column_MeasureName = By.xpath("//span[text()='Measure Name']/../../preceding-sibling::div");
		    public static final By Program_batchReview_Column_kWhSavings = By.xpath("//span[text()='kWh Savings']/../../preceding-sibling::div");
		    public static final By Program_batchReview_Column_kWinstalled = By.xpath("//span[text()='kW Installed']/../../preceding-sibling::div");
		    public static final By Program_batchReview_Column_incentivecust = By.xpath("//span[text()='Incentive - Customer']/../../preceding-sibling::div");
		    public static final By Program_batchReview_Column_kWreduction = By.xpath("//span[text()='kW Reduction']/../../preceding-sibling::div");
		    public static final By Program_batchReview_Column_watersavings = By.xpath("//span[text()='Water Savings (Gal)']/../../preceding-sibling::div");
		    public static final By Program_batchReview_Column_thermsavings = By.xpath("//span[text()='Therm Savings']/../../preceding-sibling::div");
		    public static final By Program_batchReview_Column_totalcost = By.xpath("//span[text()='Total Cost']/../../preceding-sibling::div");
		    public static final By Program_batchReview_Column_totalkwhsavings = By.xpath("//span[text()='Total kWh Savings']/../../preceding-sibling::div");
		    public static final By Program_batchReview_Column_totalthermsavings = By.xpath("//span[text()='Total Therm Savings']/../../preceding-sibling::div");
		    public static final By Program_batchReview_Column_totalwatersavings = By.xpath("//span[text()='Total Water Savings']/../../preceding-sibling::div");
		    public static final By Program_batchReview_Column_totalkwreduction = By.xpath("//span[text()='Total kW Reduction']/../../preceding-sibling::div");
		    public static final By Program_batchReview_PaymentValidationMessage = By.xpath("//div[@class='review_tasks_error']");
		    public static final By Program_batchReview_Column_TEST_kWh_Savings = By.xpath("//span[text()='TEST_kWh_Savings']/../../preceding-sibling::div");
		    public static final By Program_batchReview_RequestPaymentSuccessMsg = By.xpath(".//*[@id='globalMessages']/div");
		    public static final By Program_batchReview_readonlyapplicationstatus = By.xpath("//td[contains(.,'Application Status')]//input");
		    public static final By Program_batchReview_AutoApprovePayment = By.id("program_approval_payment_field-inputEl");
		    public static final By Program_batchReview_SelectDropDown = By.xpath("//input[@value='Across All Pages']");
		    public static final By Program_batchReview_SelectCheckbox = By.xpath("//div[@id='test9']//input[@role='checkbox']");
		    public static final By Program_batchReview_recordcheckbox = By.xpath("//div[contains(@class,'x-grid-row-checker')]");
		    public static final By Program_batchReview_pagination_sorting = By.xpath("//div[@id='sort-by-toolbar']//span[contains(@class,'x-tbar-page-next')]");
		    public static final By Program_batchReview_Records = By.xpath("//table");
		    public static final By Program_Form_ImportFromProperty = By.xpath("//div[text()='ImportFrom']/../..//div[text()='select']");
		    public static final By Program_Form_ImportFromProperty_Apply = By.xpath("//input[@value='Apply']");
		    public static final By Program_batchReview_Column_ahri = By.xpath("//span[text()='AHRI #']/../../preceding-sibling::div");
		    public static final By Program_batchReview_FilterStatus = By.xpath("//input[contains(@id,'applicationStatusFilter')]");
		    public static final By Program_batchReview_FilterByForm = By.xpath("//span[contains(text(),'Filter By Form')]");
		    public static final By Program_batchReview_FilterByForm_DefaultValue = By.xpath(".//*[@id='formFilter-inputEl' and contains(@placeholder,'None')]");
		    public static final By Program_batchReview_FilterByStatus = By.xpath("//span[contains(text(),'Filter By Status')]");
		    public static final By Program_batchReview_FilterByStatus_DefaultValue = By.xpath(".//*[@id='applicationStatusFilter-inputEl' and contains(@placeholder,'None')]");
		    public static final By Program_batchReview_FilterByCreationDate = By.xpath("//span[contains(text(),'Filter By Creation Date')]");
		    public static final By Program_batchReview_FilterByCreationDate_DefaultValue = By.xpath(".//*[@id='effectiveStartDate-inputEl' and contains(@placeholder,'None')]");
		    public static final By Program_batchReview_pagination50 = By.cssSelector("#page-grid-contents-50.current");
		    public static final By Program_batchReview_SelectStatus = By.xpath("//span[contains(text(),'Select Status')]");
		    public static final By Program_batchReview_SelectStatus_DefaultValue = By.xpath(".//*[@id='bulkEditStatus-inputEl' and contains(@placeholder,'None')]");
		    public static final By Program_batchReview_ProjectCheckBox = By.xpath("//div[@class='x-grid-row-checker']");
		    public static final By Program_batchReview_sortedbyactive = By.xpath("//ul[@class='sort_by_type']//a[@class='sorted active' and text()[.='Last Update Date']]");
		    public static final By createProject_CSSLocator = By.cssSelector("#measure_library_top_button_right");
		    public static final By Program_Label_SetProperty = By.xpath("//div[text()='Label']/../..//td[2]/div");
		    public static final By Program_Label_TextArea = By.id("Label-inputEl");
		    public static final By Program_batchReview_Pagination_Next = By.xpath("//div[@id='sort-by-toolbar']//span[contains(@class,'x-tbar-page-next')]");
		    //public static final By Program_Form_AppStatus_Token = By.xpath("//div[text()='Token']/../..//td[2]/div");
		    public static final By BatchReview_SelectStatus_DownArrow = By.id("bulkEditStatus-trigger-picker");
		    public static final By BatchReview_SelectStatus_Options = By.xpath("//div[contains(@id,'boundlist')]//li");
		    public static final By ProgramSettings_Notification_Options_DownArrow = By.id("project_status_0_field-trigger-picker");
		    public static final By ProgramSettingsPage_SelectStatus_Options = By.xpath(".//*[@id='project_status_0_field_boundList-listWrap']//li");
		    
		    public static final By Save_Program_Formula = By.xpath("//span[contains(@class,'x-btn-inner x-btn-inner-default-toolbar-small') and (text()='Save')]");
		    public static final By Program_Formula_Cancel_Button =By.xpath("//span[contains(@class,'x-btn-inner x-btn-inner-default-toolbar-small') and contains(text(),'Cancel')]");
		    public static final By Program_PayementApprovalDate = By.id("selectFileFormat-savingsReportedDate-inputEl");
		    public static final By Program_PayementApprovalDate_PrevMonth = By.xpath("//div[contains(@title,'Previous Month')]");
		    public static final By Program_PayementApprovalDate_date = By.xpath("//table[contains(@id,'datepicker')]//div[text()='20']");
		    public static final By ManageAccCodeCatpopup_Name = By.xpath(".//*[@id='manage_acctcode_category_popup']//span[contains(text(),'Name')]");
		    public static final By ManageAccCodeCatpopup_Description = By.xpath(".//*[@id='manage_acctcode_category_popup']//span[contains(text(),'Description')]");
		    public static final By ManageAccCodeCatpopup_Status = By.xpath(".//*[@id='manage_acctcode_category_popup']//span[contains(text(),'Status')]");
		    public static final By ManageAccCodeCatpopup_Btn_Cancel = By.xpath(".//*[@id='manage_acctcode_category_popup']//span[contains(text(),'Status')]");
		    public static final By BudgetAndAccFixedCost_btn_Delete = By.xpath("//div[@id='fixed_cost_list_grid-body']//div[contains(@class,'x-grid-cell-inner-action-col')]/img");
		   // public static final By Program_Img_SavedProgName = By.xpath("//a[text()=\""+TestAddProgramVariableCostDashboard.savedProgramName+"\"]/../../..//*[local-name() = 'svg']");
		    
		    public static final By BudgetAndAccFixedCost_validationmsg_Delete = By.xpath(".//*[@id='topMessageHolder']/div");
		    public static final By program_recordsonsearchpage = By.xpath("//table");
		    public static final By ProgramPage_ProjectsActive = By.xpath("//table//div[contains(text(),'Projects Active')]/../div[@class='prog_data']");
		    public static final By ProgramDetails_MeasureValue = By.xpath("//table//td[1]//div[@class='library_user_username']/a"); 
		    public static final By ProgramDetails_FormValue = By.xpath("//table//td[1]//div[@class='library_user_num']");
		    
		  //Add Task Order
	        public static final By L_Add_New_Project_Task_Order = By.xpath("//div/input[@value='+ Add Project Task Order']");
	        public static final By L_ADD_TASK_ORDER_DESC = By.xpath("//textarea[@componentid='ptoDescription']");
	        public static final By L_ADD_TASK_ORDER_PARTNER_NAME = By.xpath("//input[@componentid='partnerlookupfield']");
	        public static final By L_ADD_TASK_ORDER_PARTNER_LIST = By.xpath("//div[@class='search-item']");
	        
	        public static final By Budgetandaccountingdropdowncategory = By.xpath("//div[contains(@id,'roweditor')]//input[.!=contains(@class,' x-form-required-field x-form-text')]");
	        public static final By Budgetandaccountingdropdowncategorydesc = By.xpath("//div[contains(@id,'roweditor')]//input[contains(@class,' x-form-required-field x-form-text')]");
	        public static final By forecastTyperadioatmeter = By.xpath("//label[text()='At Meter']/../input");
	        public static final By forecastTyperadioatgeneration = By.xpath("//label[text()='At Meter']/../input");
	        public static final By Program_Template_Tab = By.id("atemplates");
	        public static final By L_ADD_TASK_ORDER_CREATION_DATE = By.xpath("//input[@componentid='ptoCreationDate']");
	         public static final By L_ADD_TASK_ORDER_DUE_DATE = By.xpath("//input[@componentid='ptoDueDate']");
	         public static final By L_ADD_TASK_ORDER_AMOUNT = By.xpath("//input[@componentid='ptoAmount']");
	         public static final By L_ADD_TASK_ORDER_SAVE = By.xpath("//a[@componentid='saveTaskOrderBtn']");
	         public static final By L_TASK_GENERATED_FILE = By.xpath(".//label[@id='taskOrderDownloadLink']/b/a");
	         public static final By TO_DESCRIPTION_TAG =By.xpath("//li[@id='taskorder_desc']");
	         public static final By L_ADD_VARIABLE_COST_PROJECT_NAME = By.xpath("//input[@componentid='variable_costs_projectStoreId']");
	         public static final By L_ADD_VARIABLE_COST_PROJECT_TASK_ORDER = By.xpath("//input[@componentid='variable_costs_projectTaskOrderStoreId']");
	         public static final By PROJECT_NAME=By.xpath("//b[@class='projectSummaryTitle']");
	         public static final By TASKORDER_CREATED=By.xpath("//div[contains(@class,'x-grid-cell-inner')]/b/a");
	         public static final By COST_CREATED=By.xpath("//div[contains(@class,'x-grid-cell-inner')]/b/a");
	         public static final By VARIABLE_COST_POPUP = By.xpath("//div[contains(.,'Invoice Details')]");
	         public static final By Template_Dropdown_Disabled = By.xpath(".//div[@id='docTemplateCollection-inputWrap']//input[@disabled='']");
	         //public static final By Edit_TaskOrder = By.xpath(".//div[@id='docTemplateCollection-inputWrap']//input[@disabled='']");
	         public static final By Edit_TaskOrder = By.xpath(".//div[@id='task_order_desc-targetEl']//a[contains(@class,'cancelButton')]//preceding-sibling::a");
	         public static final By Cancel_TaskOrder = By.xpath(".//span[text()='Cancel']//ancestor::a[contains(@class,'cancelButton')]");
	         public static final By Generate_TaskOrder = By.xpath(".//span[@id='generateDocBtn-btnIconEl']//ancestor::a");
	         public static final By forecastcreatedstatus = By.xpath("//td[text()='Successful']");
	         public static final By forecasteditedname = By.xpath("//span[@class='pg_blk_links pg_name']/a");
	         public static final By projectvalidationmsg = By.xpath("//div[contains(@id,'messagebox')and text()='Create Project Failed']");
	         public static final By processedindividualpayment_lastupdateddate = By.xpath("//div[contains(@class,'res_content_block5 res_content_block')]//a/..");
	         public static final By processedindividualpayment_totalamt = By.xpath("//div[text()='Total Amount']/..//span");
	         public static final By processedindividualpayment_accountingcode = By.xpath("//span[contains(@class,'pg_blk_links_l pg_payee_name_value_l')]");
	         public static final By projectsreadyforpayment_totalamount = By.xpath("//div[contains(text(),'Total Amount')]/../div[@class='prog_data']");
	         public static final By projectsreadyforpayment_noofcostlines = By.xpath("//div[contains(text(),'No Of Cost Lines')]/../div[@class='prog_data']");
	         //Email Notification
	         public static final By select_Template_Attachment = By.xpath(".//input[@id='file_upload_attr_field_combobox-inputEl']");
	         public static final By Activiti_emailTask = By.xpath(".//li[@id='MailTask']//img");
	         public static final By Activiti_canvasarea_img_sequenceFlow = By.xpath("//div[@id='canvasSection']//div[@id='SequenceFlow']/img");
	         public static final By Activiti_Mail_Task = By.xpath("//div[@class='ORYX_Editor']//*[name()='g']//*[@class='children']//*[@title='BPMN-EDITOR.ITEM.MAILTASK.TITLE']");
	         public static final By Activiti_Email_Notification_Template = By.xpath(".//*[@id='propertySection']//span[contains(text(),'Notification Template')]/../span[@class='value']");
	         public static final By Activiti_EmailNotif_drpdwnkey = By.xpath(".//*[@id='propertySection']//span[contains(text(),'Notification Template')]/../span[@class='value']/ng-include/div/select");
	         public static final By Activiti_EmailEndtask = By.xpath("//div[@id='canvasSection']//*[@id='EndNoneEvent']/img");
	         public static final By StatAndVersionNo = By.xpath("//table[2]//div[text()='2']");
	         public static final By StatAndVersionStatus = By.xpath("//table[2]//div[text()='Active']");
	         public static final By Activiti_EndEvent_in_Canvassection = By.xpath("//div[@class='ORYX_Editor']//*[name()='g' and @title='BPMN-EDITOR.ITEM.END-NONE-EVENT.TITLE']");
	         public static final By ProgramPage_AllowProjectCopy_Checkbox = By.xpath("//label[text()='Allow Project Copy']/..//input");
	         public static final By FORM_Import_From_Parent = By.xpath("//div[text()[.='ImportFromParent']]/../../td[2]/div");
	         public static final By FORM_Import_From_Parent_CheckBox = By.xpath(".//input[@role='checkbox' and @type='button']");
	         public static final By AllowProjectFormCheckBox_withchecked = By.xpath("//div[@id='program_projectCopy_field' and contains(@class,'x-form-cb-checked')]");
	         public static final By formpage_modifiableAttibute = By.xpath("//div[text()='ModifiableAttribute']/../..//div[text()='select']");
	         public static final By formpage_modifiableAttibute_checkbox = By.xpath("//input[@role='checkbox']");
	         public static final By payment_reject = By.id("RejectPayments-inputEl");
	         
    }
    
    public interface System{
    	   public static final By attributesFolder = By.xpath("//span[text()='Attributes']/../img[contains(@class,'expander')]");
    	   public static final By measureattributename = By.xpath("//span[text()='Name']/../..//input");
    	   public static final By measureattributelabel = By.xpath("//span[text()='Label']/../..//textarea");
    	   public static final By measureattributedatatype = By.xpath("//span[text()='Data Type']/../..//input");
    	   public static final By measureattributeuielement = By.xpath("//span[text()='UI Element']/../..//input");
    	   public static final By measureattributeequipmentcategory = By.xpath("//span[text()='Equipment Category:']/../..//input");
    	   public static final By taskattributeequipmentcategory = By.xpath("//span[contains(text(),'Category')]/../..//input");
    	   public static final By measureattributeequipmentpropertyconf = By.xpath("//span[text()='Property Configuration']/../..//input");
    	   public static final By assessprogcategory = By.xpath("//span[text()='Category']/../..//input");
    	   public static final By assessprogformatter = By.xpath("//span[text()='Formatter:']/../..//input");
    	   public static final By assessprogvalidator = By.xpath("//span[text()='Validator:']/../..//input");
    	   public static final By assessprogpropconf = By.xpath("//span[text()='Property Configuration']/../..//input");
    	   public static final By assesssearchdropdown = By.xpath("//span[text()='Search:']/../..//input");
    	   public static final By assesssearchtextbox = By.xpath("//div[@id='right-container-body']//input[@role='textbox']");
    	   public static final By assesssearchdwnarrow = By.xpath("//span[text()='Search:']/../..//div[@id='admin_attributes_search_combobox-trigger-picker']");
    	   public static final By JobsFolder = By.xpath("//span[text()='Jobs']/../img[contains(@class,'expander')]");
    	   //public static final By uploadfiletextfield = By.xpath("//div[contains(@class,'x-window-body-closable')]/div[1]//span[text()='File']/../..//input[@name='']");
    	   public static final By uploadfiletextfield = By.xpath("//div[contains(@class,'x-window-body-closable')]/div[1]//input[@name='FileName']");
    	   public static final By uploadfilebutton = By.xpath("//div[contains(@class,'x-window-body-closable')]/div[1]//span[text()='Upload']");
    	   public static final By SecurityFolder = By.xpath("//span[text()='Security']/../img[contains(@class,'expander')]");
    	   public static final By roleattributename = By.xpath("//span[text()='Role Name']/../..//input");

    	  }
  	
    public interface Formulas {
        public static final String Program_name_1 = "Standard Business Solutions";
        public static final String Formula_name_1 = "CO Sensor Inc Cost";
        public static final String Formula_name_2 = "CO Sensor kWh";
        public static final String Formula_name_3 = "CO Sensor Rebate";
        public static final String Formula_name_4 = "CO2 Sensor kW";
        public static final String Formula_name_5 = "AC Constant";
        public static final String Formula_name_6 = "Air Conditioner Measure Name";
        public static final String Formula_name_7 = "Chiller kW";
        public static final String Formula_name_8 = "Chiller kWh";
        public static final String Formula_name_9 = "Chiller Minimum IPLV";
        public static final String Formula_name_10 = "Chiller Rebate";
        public static final String Formula_name_11 = "Chillers - Proposed Chiller IPLV (kW/ton)";
        public static final String Formula_name_12 = "Chillers Measure Name";
        public static final String Formula_name_13 = "CO2 Sensor kW";
        public static final String Formula_name_14 = "CO2 Sensor Measure Name";
        public static final String Formula_name_15 = "CO2 Sensor Rebate";
        public static final String Formula_name_16 = "CO2 Sensors Base HSPF or COP";
        public static final String Formula_name_17 = "CO2 Sensors SEERbase";
        public static final String Formula_name_18 = "Compressed Air Inc Cost";
        public static final String Formula_name_19 = "Compressed Air kW";
        public static final String Formula_name_20 = "Compressed Air kWh";
        public static final String Formula_name_21 = "Compressed Air Measure Name";
        public static final String Formula_name_22 = "Compressed Air Qualifies";
        public static final String Formula_name_23 = "Compressed Air Rebate";
        public static final String Formula_name_24 = "Computer and Data Center Inc Cost";
        public static final String Formula_name_25 = "Computer and Data Center kW";
        public static final String Formula_name_26 = "Computer and data Center Measure Name";
        public static final String Formula_name_27 = "Computer and Data Center Rebate";
        public static final String Formula_name_28 = "Cust Info Customer Company Name";
        public static final String Formula_name_29 = "RA Customer Company Name";
        public static final String Formula_name_30 = "RA Lighting Controls Incremental Cost";
        public static final String Formula_name_31 = "RA Lighting Fixture Incremental Cost";
        public static final String Formula_name_32 = "RA Total Customer Incentive";
        public static final String Formula_name_33 = "RA Total Project Incremental Cost";
        public static final String Formula_name_34 = "RA Total Project kW Reduction";
        public static final String Formula_name_35 = "RA Total Project kWh Savings";
        public static final String Formula_name_36 = "RA Total Project Measure Cost";
        public static final String Formula_name_37 = "RA Total Project Rebate";
        public static final String Formula_name_38 = "Retro Lighting Total Project Incremental Cost";
        public static final String Formula_name_39 = "Retro Lighting Total Project kW Reduction";
        public static final String Formula_name_40 = "Retro Lighting Total Project Measure Cost";
        public static final String Formula_name_41 = "Retro Lighting Total Project Rebate";
        public static final String Formula_name_42 = "Retrofit Lighting Total Project kWh Savings";
        public static final String Formula_name_43 = "RRR Incentive Total";
        public static final String Formula_name_44 = "RRR Total Project Incremental Cost";
        public static final String Formula_name_45 = "RRR Total Project kW Reduction";
        public static final String Formula_name_46 = "RRR Total Project kWh Savings";
        public static final String Formula_name_47 = "RRR Total Project Measure Cost";
        public static final String Formula_name_48 = "RRR Total Project Rebate";
        public static final String Formula_name_49 = "RRR Total Quantity";
        public static final String Formula_name_50 = "Self Direct?";
        public static final String Formula_name_51 = "Chiller Measure Life";
        public static final String Formula_name_52 = "CO2 Inc Cost";
        public static final String Formula_name_53 = "Computer and Data Center kW";
        public static final String Formula_name_54 = "Computer and Data Center kWh";
        public static final String Formula_name_55 = "Hotel Occ Controls Inc Cost";
        public static final String Formula_name_56 = "ECM Inc Rebate";
        public static final String Formula_name_57 = "ECM kW";
        public static final String Formula_name_58 = "ECM kWh";
        public static final String Formula_name_59 = "ECM Inc Cost";
        public static final String Formula_name_60 = "Envelope Inc Cost";
        public static final String Formula_name_61 = "Envelope kW";
        public static final String Formula_name_62 = "Envelope kWh";
        public static final String Formula_name_63 = "Refrigeration Inc Cost";
        public static final String Formula_name_64 = "Refrigeration kW";
        public static final String Formula_name_65 = "Refrigeration kWh";
        public static final String Formula_name_66 = "Refrigeration kW_copy_02-28-2014T08:39:26.218";
        public static final String Formula_name_67 = "VFD Inc Cost";
        public static final String Formula_name_68 = "VFD kW";
        public static final String Formula_name_69 = "VFD kWh";
        public static final String Formula_name_70 = "Year 1 Inc Cost";
        public static final String Formula_name_71 = "Year 1 kW Reduction";
        public static final String Formula_name_72 = "Year 1 kWh";
        public static final String Formula_name_73 = "Year 1 Measure Cost";
        public static final String Formula_name_74 = "Year 1 Rebate";
        public static final String Formula_name_75 = "Year 2 Expiration Date";
        public static final String Formula_name_76 = "Year 2 Inc Cost";
        public static final String Formula_name_77 = "Year 2 kW Reduction";
        public static final String Formula_name_78 = "Year 2 kWh";
        public static final String Formula_name_79 = "Year 2 Measure Cost";
        public static final String Formula_name_80 = "Year 3 Expiration Date";
        public static final String Formula_name_81 = "Year 3 Inc Cost";
        public static final String Formula_name_82 = "Year 3 kW Reduction";
        public static final String Formula_name_83 = "Year 3 kWh";
        public static final String Formula_name_84 = "Year 3 Measure Cost";
        public static final String Formula_name_85 = "Year 3 Rebate";
        public static final String Formula_name_86 = "Heat Pump Measure Name";
        public static final String Formula_name_87 = "Hotel Occ Control Base HSPF or COP";
        public static final String Formula_name_88 = "Hotel Occ Controls Inc Cost";
        public static final String Formula_name_89 = "Hotel Occ Controls kW";
        public static final String Formula_name_90 = "Hotel Occ Controls kWh";
        public static final String Formula_name_91 = "Hotel Occ Controls Measure Name";
        public static final String Formula_name_92 = "Hotel Occ Controls Rebate";
        public static final String Formula_name_93 = "Hotel Occupancy Sensors Qualifies";
        public static final String Formula_name_94 = "HP Constant";
        public static final String Formula_name_95 = "HVAC AC Tier";
        public static final String Formula_name_96 = "HVAC HP Tier";
        public static final String Formula_name_97 = "HVAC-AC Inc Cost";
        public static final String Formula_name_98 = "HVAC-AC kW";
        public static final String Formula_name_99 = "HVAC-AC kWh/yr";
        public static final String Formula_name_100 = "HVAC-AC Rebate";
        public static final String Formula_name_101 = "HVAC-HP Inc Cost";
        public static final String Formula_name_102 = "HVAC-HP kW";
        public static final String Formula_name_103 = "HVAC-HP kWh/yr";
        public static final String Formula_name_104 = "HVAC-HP Rebate";
        public static final String Formula_name_105 = "Installed Expired";
        public static final String Formula_name_106 = "Installed PY";
        public static final String Formula_name_107 = "Lighting Controls Measure Name";
        public static final String Formula_name_108 = "LR Lighting Fixture kW Reduction";
        public static final String Formula_name_109 = "Lighting Fixtures Measure Name";
        public static final String Formula_name_110 = "LR Lighting Controls kW Reduction";
        public static final String Formula_name_111 = "";
        public static final String Formula_name_112 = "";
        public static final String Formula_name_113 = "";
        public static final String Formula_name_114 = "";
        public static final String Formula_name_115 = "";
        public static final String Formula_name_116 = "";
        public static final String Formula_name_117 = "";
        public static final String Formula_name_118 = "";
        public static final String Formula_name_119 = "";
        public static final String Formula_name_120 = "";
        public static final String Formula_name_121 = "";
        public static final String Formula_name_122 = "";
        public static final String Formula_name_123 = "";
        public static final String Formula_name_124 = "";
        public static final String Formula_name_125 = "";
        public static final String Formula_name_126 = "";
        public static final String Formula_name_127 = "";
        public static final String Formula_name_128 = "";
        public static final String Formula_name_129 = "";


        public static final By L_FORMULA_NAME = By.cssSelector("[name='formulaName']");
        public static final By L_FORMULA = By.cssSelector("[name='formulaField-inputEl']");
        public static final String FORMULA_1 = "Q*C";
        public static final String FORMULA_2 = "Q*S*K";
        public static final String FORMULA_3 = "Q*C";
        public static final String FORMULA_4 = "Q*(S*400*.15)* (PEAKred/1000/SEERbase)";
        public static final String FORMULA_5 = "if(right(val,7)==\"Unitary\", \"Unitary AC\", \"PTAC\")";
        public static final String FORMULA_6 = "\"HVAC - \"+  if(right(type,3)==\"ary\", \"Air Conditioner\", if(left(type,3)==\"Pac\",\"PTAC\",\" \")) + \n" +
                "\" - \"+if(right(type,3)==\"ary\", if(right(str(round(btuh/6000,0)/2),2)==\".5\", \n" +
                "str(round(btuh/6000,0)/2),\n" +
                "left(str(round(btuh/6000,0)/2),\n" +
                "len(str(round(btuh/6000,0)/2))-2)) \n" +
                "+ \" ton\" ,str(btuh)+\" Btu/hr\")";
        public static final String FORMULA_7 = "max(0,(3.517 * (1 / baseCOP - 1 / COP) - if(left(Type,3)==\"Wat\"&&left(Typebase,3)==\"Air\",0.109,0) )* Tons * Quantity)";
        public static final String FORMULA_8 = "(3.517*(1/baseIPLV - 1/IPLV) - if(left(Type,3)==\"Wat\"&&left(Typebase,3)==\"Air\",0.109,0))\n" +
                "*Tons*EFLH*Quantity";
        public static final String FORMULA_9 = "floor( (12 / (N * 3.412)) * 100 + .5) / 100";
        public static final String FORMULA_10 = "if(IPLV <= M,  N * Tons * ( EqR+ (M - IPLV) * EfR), 0)";
        public static final String FORMULA_11 = "floor ( (12 / (J * 3.412)) * 100 + .5) / 100";
        public static final String FORMULA_12 = "\"Chiller - \" + val + \" ton\"";
        public static final String FORMULA_13 = "Q*(S*400*.15)* (PEAKred/1000/SEERbase)";
        public static final String FORMULA_14 = "\"CO2 Sensor - \" + val + \" ton\"";
        public static final String FORMULA_15 = "if(S>=10,Q*C,0)";
        public static final String FORMULA_16 = "if(Heat==\"Electric Resistance\", 3.412,\n" +
                "if(Type==\"Unitary Heat Pump\", \n" +
                "if(ID==1,COP,COP*3.413/1.718),COP*3.413/1.718))";
        public static final String FORMULA_17 = "val";
        public static final String FORMULA_18 = "if(Qual==1,if(NC==\"Yes\", CUN, CUR) * if (U==\"None (Equip)\", Q, S*Q),0)";
        public static final String FORMULA_19 = "if(Qual==1,K * ( if(U==\"None (Equip)\", Q, S*Q) ),0)";
        public static final String FORMULA_20 = "if(Qual==1,K * (if(U==\"None (Equip)\", Q*min(A,8760), S*Q*min(A,8760))),0)";
        public static final String FORMULA_21 = "\"Compressed Air - \" + val";
        public static final String FORMULA_22 = "if(type==\"VFD Compressors\", if(val>0 && val<=75,1,0), if(type==\"Refrigerated Cycling Dryers\"||type==\"Low Pressure-drop Filters\",\n" +
                "if(val<=1000 ,1,0),1))";
        public static final String FORMULA_23 = "if(Qual==1,R * ( if(U==\"None (Equip)\", Q, S*Q) ),0)";
        public static final String FORMULA_24 = "Q*K";
        public static final String FORMULA_25 = "Q*K";
        public static final String FORMULA_26 = "val";
        public static final String FORMULA_27 = "if(NC==\"Yes\"&&val!=\"Network Desktop Computer Power Mgt Software\",0,Q*K)";
        public static final String FORMULA_28 = "val";
        public static final String FORMULA_29 = "val";
        public static final String FORMULA_30 = "val";
        public static final String FORMULA_31 = "val";
        public static final String FORMULA_32 = "val";
        public static final String FORMULA_33 = "val";
        public static final String FORMULA_34 = "val";
        public static final String FORMULA_35 = "val";
        public static final String FORMULA_36 = "val";
        public static final String FORMULA_37 = "val";
        public static final String FORMULA_38 = "val";
        public static final String FORMULA_39 = "val";
        public static final String FORMULA_40 = "val";
        public static final String FORMULA_41 = "val";
        public static final String FORMULA_42 = "val";
        public static final String FORMULA_43 = "val";
        public static final String FORMULA_44 = "val";
        public static final String FORMULA_45 = "val";
        public static final String FORMULA_46 = "val";
        public static final String FORMULA_47 = "val";
        public static final String FORMULA_48 = "val";
        public static final String FORMULA_49 = "val";
        public static final String FORMULA_50 = "val";
        public static final String FORMULA_51 = "val";
        public static final String FORMULA_52 = "Q*C";
        public static final String FORMULA_53 = "Q*K";
        public static final String FORMULA_54 = "Q*K";
        public static final String FORMULA_55 = "Q*C";
        public static final String FORMULA_56 = "val*K * S*Q";
        public static final String FORMULA_57 = "val*K * S*Q";
        public static final String FORMULA_58 = "val*K*S*Q";
        public static final String FORMULA_59 = "val*K * S*Q";
        public static final String FORMULA_60 = "if(QUAL==1,Q*K,0)";
        public static final String FORMULA_61 = "if(QUAL==1,Q*K,0)";
        public static final String FORMULA_62 = "if(QUAL==1,Q*K,0)";
        public static final String FORMULA_63 = "K * (if(U==1, Q, S*Q))";
        public static final String FORMULA_64 = "K * (if(U==1, Q, S*Q))";
        public static final String FORMULA_65 = "K * (if(U==1, Q, S*Q))";
        public static final String FORMULA_66 = "K * (if(U==1, Q, S*Q))";
        public static final String FORMULA_67 = "K * (if(U==1, Q, S*Q))";
        public static final String FORMULA_68 = "K * (if(U==1, Q, S*Q))";
        public static final String FORMULA_69 = "K * (if(U==1, Q, S*Q))";
        public static final String FORMULA_70 = "case(Phase,\"5. Installed\", Inst,\"4. Proposed\",Prop,\"3. Preliminary\",Prelim,\"\")";
        public static final String FORMULA_71 = "case(Phase,\"5. Installed\", Inst,\"4. Proposed\",Prop,\"3. Preliminary\",Prelim,\"\")";
        public static final String FORMULA_72 = "case(Phase,\"5. Installed\", Inst,\"4. Proposed\",Prop,\"3. Preliminary\",Prelim,\"\")";
        public static final String FORMULA_73 = "case(Phase,\"5. Installed\", Inst,\"4. Proposed\",Prop,\"3. Preliminary\",Prelim,\"\")";
        public static final String FORMULA_74 = "case(Phase,\"5. Installed\", Inst,\"4. Proposed\",Prop,\"3. Preliminary\",Prelim,\"\")";
        public static final String FORMULA_75 = "case(Phase,\"5. Installed\", Inst,\"4. Proposed\",Prop,\"3. Preliminary\",Prelim,\"\")";
        public static final String FORMULA_76 = "case(Phase,\"5. Installed\", Inst,\"4. Proposed\",Prop,\"3. Preliminary\",Prelim,\"\")";
        public static final String FORMULA_77 = "case(Phase,\"5. Installed\", Inst,\"4. Proposed\",Prop,\"3. Preliminary\",Prelim,\"\")";
        public static final String FORMULA_78 = "case(Phase,\"5. Installed\", Inst,\"4. Proposed\",Prop,\"3. Preliminary\",Prelim,\"\")";
        public static final String FORMULA_79 = "case(Phase,\"5. Installed\", Inst,\"4. Proposed\",Prop,\"3. Preliminary\",Prelim,\"\")";
        public static final String FORMULA_80 = "case(Phase,\"5. Installed\", Inst,\"4. Proposed\",Prop,\"3. Preliminary\",Prelim,\"\")";
        public static final String FORMULA_81 = "case(Phase,\"5. Installed\", Inst,\"4. Proposed\",Prop,\"3. Preliminary\",Prelim,\"\")";
        public static final String FORMULA_82 = "case(Phase,\"5. Installed\", Inst,\"4. Proposed\",Prop,\"3. Preliminary\",Prelim,\"\")";
        public static final String FORMULA_83 = "case(Phase,\"5. Installed\", Inst,\"4. Proposed\",Prop,\"3. Preliminary\",Prelim,\"\")";
        public static final String FORMULA_84 = "case(Phase,\"5. Installed\", Inst,\"4. Proposed\",Prop,\"3. Preliminary\",Prelim,\"\")";
        public static final String FORMULA_85 = "case(Phase,\"5. Installed\", Inst,\"4. Proposed\",Prop,\"3. Preliminary\",Prelim,\"\")";
        public static final String FORMULA_86 = "\"HVAC - \"+  if(right(type,3)==\"ary\", \"Heat Pump\", if(left(type,3)==\"Pac\",\"PTHP\",\" \")) + \n" +
                "\" - \"+if(right(type,3)==\"ary\", if(right(str(round(btuh/6000,0)/2),2)==\".5\", \n" +
                "str(round(btuh/6000,0)/2),\n" +
                "left(str(round(btuh/6000,0)/2),\n" +
                "len(str(round(btuh/6000,0)/2))-2)) \n" +
                "+ \" ton\" ,str(btuh)+\" Btu/hr\")";
        public static final String FORMULA_87 = "if((Type==\"PTAC\"||Type==\"Fan Coil Unit\") &&Heat==\"Electric Resistance\", 3.412,\n" +
                "COP)";
        public static final String FORMULA_88 = "Q*C";
        public static final String FORMULA_89 = "0";
        public static final String FORMULA_90 = "0.624*Q*(S*((12/SEERbase)*(EFLHcBase-EFLHc) +\n" +
                "if(HSPFbase>0,(12/HSPFbase)*(EFLHhBase-EFLHh),0)))";
        public static final String FORMULA_91 = "\"Hotel Occ Controls - \" + val";
        public static final String FORMULA_92 = "qual*Q*C";
        public static final String FORMULA_93 = "if(OCCHEAT>=UNOCCHEAT+6&&UNOCCCOOL>=OCCCOOL+6,1,0)";
        public static final String FORMULA_94 = "if(right(val,7)==\"Unitary\", \"Unitary HP\", \"PTHP\")";
        public static final String FORMULA_95 = "if(Type!=\"Water-Cooled Unitary\" && Type!=\"Evap-Cooled Unitary\"&& Unit!=\"PTAC\", if(SEER >= T2SEER &&\n" +
                "EER >= T2EER, 2, \n" +
                "if(SEER >= T1SEER && \n" +
                "EER >= T1EER, 1, 0)),\n" +
                "if(EER >= T2EER,2,if(EER >= T1EER,1,0)))";
        public static final String FORMULA_96 = "if(Type!=\"Water-Cooled Unitary\" && Type!=\"Evap-Cooled Unitary\"&& Unit!=\"PTHP\", if(SEER >= T2SEER &&\n" +
                "EER >= T2EER && HSPF >= T2HSPF &&\n" +
                "COP2>=T2COP2, 2, \n" +
                "if(SEER >= T1SEER && \n" +
                "EER >= T1EER && HSPF >= T1HSPF && COP2>=T1COP2, 1, 0)),\n" +
                "if(EER >= T2EER && HSPF >= T2HSPF &&\n" +
                "COP2>=T2COP2,2,if(EER >= T1EER && HSPF >= T1HSPF && COP2>=T1COP2,1,0)))";
        public static final String FORMULA_97 = "S /12000 * Q * if(Tier==1, T1, if(Tier==2, T2, 0))";
        public static final String FORMULA_98 = "if(Tier==0,0,Q * (S/1000 * (1/baseEER - 1/EER)))";
        public static final String FORMULA_99 = "if(Tier==0,0,Q * (S/1000 * (if(SEER==0,(1/baseEER - 1/EER),(1/baseSEER - 1/SEER)) * EC)))";
        public static final String FORMULA_100 = "S /12000 * Q * if(Tier==1, T1, if(Tier==2, T2, 0))";
        public static final String FORMULA_101 = "S /12000 * Q * if(Tier==1, T1, if(Tier==2, T2, 0))";
        public static final String FORMULA_102 = "if(Tier==0,0,Q * (S/1000 * (1/baseEER - 1/EER)))";
        public static final String FORMULA_103 = "if(Tier==0,0,Q * (S/1000 *(if(SEER==0,(1/baseEER - 1/EER),(1/baseSEER - 1/SEER)) * EC + \n" +
                "if(baseHSPF>0&&HSPF>0,(1/baseHSPF - 1/HSPF)*EH* \n" +
                "if(ID==1,1,1.718/3.413),0))))";
        public static final String FORMULA_104 = "S /12000 * Q * if(Tier==1, T1, if(Tier==2, T2, 0))";
        public static final String FORMULA_105 = "if(isNull(EXP)||EXP==\"\",\"\",\n" +
                "(isBeforeDate(EXP, Now(\"MM/dd/yyyy\")))&&(\n" +
                "(isNull(Review)||Review==\"\")),\"\")";
        public static final String FORMULA_106 = "if((isNull(Review)||Review==\"\"),\"\",\n" +
                "if(month(Review)>=5,mid(str(year(Review)+1),2,2), right(Review,2)))";
        public static final String FORMULA_107 = "\"Lighting Controls\" + if(val==\"Yes\", \" New Construction\", \" Retrofit\")";
        public static final String FORMULA_108 = "CF*IF*revkW";
        public static final String FORMULA_109 = "\"Lighting Fixtures\" + if(val==\"Yes\", \" New Construction\", \" Retrofit\")";
        public static final String FORMULA_110 = "CF*IF*revkW";
        public static final String FORMULA_111 = "";
        public static final String FORMULA_112 = "";
        public static final String FORMULA_113 = "";
        public static final String FORMULA_114 = "";
        public static final String FORMULA_115 = "";
        public static final String FORMULA_116 = "";
        public static final String FORMULA_117 = "";
        public static final String FORMULA_118 = "";
        public static final String FORMULA_119 = "";
        public static final String FORMULA_120 = "";


        public static final String FORMULA_1_VALUE_1 = "5";
        public static final String FORMULA_1_VALUE_2 = "6";
        public static final String FORMULA_2_VALUE_1 = "3341.74";
        public static final String FORMULA_2_VALUE_2 = "1";
        public static final String FORMULA_2_VALUE_3 = "3";
        public static final String FORMULA_3_VALUE_1 = "250";
        public static final String FORMULA_3_VALUE_2 = "1";
        public static final String FORMULA_4_VALUE_1 = "10";
        public static final String FORMULA_4_VALUE_2 = "20";
        public static final String FORMULA_4_VALUE_3 = "30";
        public static final String FORMULA_4_VALUE_4 = "40";
        public static final String FORMULA_4_VALUE_5 = "50";
        public static final String FORMULA_5_VALUE_1 = "PTAC";
        public static final String FORMULA_6_VALUE_1 = "120000";
        public static final String FORMULA_6_VALUE_2 = "ary";
        public static final String FORMULA_7_VALUE_1 = "3.9";
        public static final String FORMULA_7_VALUE_2 = "Air";
        public static final String FORMULA_7_VALUE_3 = "1";
        public static final String FORMULA_7_VALUE_4 = "3.2";
        public static final String FORMULA_7_VALUE_5 = "Water";
        public static final String FORMULA_7_VALUE_6 = "120";
        public static final String FORMULA_8_VALUE_1 = "1";
        public static final String FORMULA_8_VALUE_2 = "3.66";
        public static final String FORMULA_8_VALUE_3 = "3505";
        public static final String FORMULA_8_VALUE_4 = "Air";
        public static final String FORMULA_8_VALUE_5 = "60";
        public static final String FORMULA_8_VALUE_6 = "Wat";
        public static final String FORMULA_8_VALUE_7 = "5.832";
        public static final String FORMULA_9_VALUE_1 = "5";
        public static final String FORMULA_10_VALUE_1 = "10";
        public static final String FORMULA_10_VALUE_2 = "20";
        public static final String FORMULA_10_VALUE_3 = "30";
        public static final String FORMULA_10_VALUE_4 = "40";
        public static final String FORMULA_10_VALUE_5 = "50";
        public static final String FORMULA_10_VALUE_6 = "60";
        public static final String FORMULA_11_VALUE_1 = "5.832";
        public static final String FORMULA_12_VALUE_1 = "80";
        public static final String FORMULA_13_VALUE_1 = "10";
        public static final String FORMULA_13_VALUE_2 = "20";
        public static final String FORMULA_13_VALUE_3 = "30";
        public static final String FORMULA_13_VALUE_4 = "40";
        public static final String FORMULA_13_VALUE_5 = "50";
        public static final String FORMULA_14_VALUE_1 = "80";
        public static final String FORMULA_15_VALUE_1 = "10";
        public static final String FORMULA_15_VALUE_2 = "1";
        public static final String FORMULA_15_VALUE_3 = "120";
        public static final String FORMULA_16_VALUE_1 = "5";
        public static final String FORMULA_16_VALUE_2 = "Unitary HP";
        public static final String FORMULA_16_VALUE_3 = "8.0";
        public static final String FORMULA_16_VALUE_4 = "Electric Resistance";
        public static final String FORMULA_17_VALUE_1 = "10";
        public static final String FORMULA_18_VALUE_1 = "10";
        public static final String FORMULA_18_VALUE_2 = "1";
        public static final String FORMULA_18_VALUE_3 = "k";
        public static final String FORMULA_18_VALUE_4 = "50";
        public static final String FORMULA_18_VALUE_5 = "10";
        public static final String FORMULA_18_VALUE_6 = "Yes";
        public static final String FORMULA_18_VALUE_7 = "10";
        public static final String FORMULA_19_VALUE_1 = "10";
        public static final String FORMULA_19_VALUE_2 = "20";
        public static final String FORMULA_19_VALUE_3 = "1";
        public static final String FORMULA_19_VALUE_4 = "None";
        public static final String FORMULA_19_VALUE_5 = "30";
        public static final String FORMULA_20_VALUE_1 = "1";
        public static final String FORMULA_20_VALUE_2 = "9000";
        public static final String FORMULA_20_VALUE_3 = "1";
        public static final String FORMULA_20_VALUE_4 = "1";
        public static final String FORMULA_20_VALUE_5 = "1";
        public static final String FORMULA_20_VALUE_6 = "k";
        public static final String FORMULA_21_VALUE_1 = "80";
        public static final String FORMULA_22_VALUE_1 = "Low Pressure-drop Filters";
        public static final String FORMULA_22_VALUE_2 = "1000";
        public static final String FORMULA_23_VALUE_1 = "1";
        public static final String FORMULA_23_VALUE_2 = "10";
        public static final String FORMULA_23_VALUE_3 = "10";
        public static final String FORMULA_23_VALUE_4 = "10";
        public static final String FORMULA_23_VALUE_5 = "10";
        public static final String FORMULA_23_VALUE_6 = "k";
        public static final String FORMULA_24_VALUE_1 = "10";
        public static final String FORMULA_24_VALUE_2 = "200";
        public static final String FORMULA_25_VALUE_1 = "10";
        public static final String FORMULA_25_VALUE_2 = "200";
        public static final String FORMULA_26_VALUE_1 = "Name";
        public static final String FORMULA_27_VALUE_1 = "Network Desktop Computer Power Mgt Software";
        public static final String FORMULA_27_VALUE_2 = "2";
        public static final String FORMULA_27_VALUE_3 = "Yes";
        public static final String FORMULA_27_VALUE_4 = "4";
        public static final String FORMULA_28_VALUE_1 = "Name";
        public static final String FORMULA_29_VALUE_1 = "Name";
        public static final String FORMULA_30_VALUE_1 = "10";
        public static final String FORMULA_31_VALUE_1 = "10";
        public static final String FORMULA_32_VALUE_1 = "10";
        public static final String FORMULA_33_VALUE_1 = "10";
        public static final String FORMULA_34_VALUE_1 = "10";
        public static final String FORMULA_35_VALUE_1 = "10";
        public static final String FORMULA_36_VALUE_1 = "10";
        public static final String FORMULA_37_VALUE_1 = "10";
        public static final String FORMULA_38_VALUE_1 = "10";
        public static final String FORMULA_39_VALUE_1 = "10";
        public static final String FORMULA_40_VALUE_1 = "10";
        public static final String FORMULA_41_VALUE_1 = "10";
        public static final String FORMULA_42_VALUE_1 = "10";
        public static final String FORMULA_43_VALUE_1 = "10";
        public static final String FORMULA_44_VALUE_1 = "10";
        public static final String FORMULA_45_VALUE_1 = "10";
        public static final String FORMULA_46_VALUE_1 = "10";
        public static final String FORMULA_47_VALUE_1 = "10";
        public static final String FORMULA_48_VALUE_1 = "10";
        public static final String FORMULA_49_VALUE_1 = "10";
        public static final String FORMULA_50_VALUE_1 = "10";
        public static final String FORMULA_51_VALUE_1 = "10";
        public static final String FORMULA_52_VALUE_1 = "650";
        public static final String FORMULA_52_VALUE_2 = "2";
        public static final String FORMULA_53_VALUE_1 = "10";
        public static final String FORMULA_53_VALUE_2 = "20";
        public static final String FORMULA_54_VALUE_1 = "20";
        public static final String FORMULA_54_VALUE_2 = "10";
        public static final String FORMULA_55_VALUE_1 = "20";
        public static final String FORMULA_55_VALUE_2 = "10";
        public static final String FORMULA_56_VALUE_1 = "10";
        public static final String FORMULA_56_VALUE_2 = "20";
        public static final String FORMULA_56_VALUE_3 = "30";
        public static final String FORMULA_56_VALUE_4 = "40";
        public static final String FORMULA_57_VALUE_1 = "10";
        public static final String FORMULA_57_VALUE_2 = "20";
        public static final String FORMULA_57_VALUE_3 = "30";
        public static final String FORMULA_57_VALUE_4 = "40";
        public static final String FORMULA_58_VALUE_1 = "10";
        public static final String FORMULA_58_VALUE_2 = "20";
        public static final String FORMULA_58_VALUE_3 = "30";
        public static final String FORMULA_58_VALUE_4 = "40";
        public static final String FORMULA_59_VALUE_1 = "10";
        public static final String FORMULA_59_VALUE_2 = "20";
        public static final String FORMULA_59_VALUE_3 = "30";
        public static final String FORMULA_59_VALUE_4 = "40";
        public static final String FORMULA_60_VALUE_1 = "10";
        public static final String FORMULA_60_VALUE_2 = "20";
        public static final String FORMULA_60_VALUE_3 = "30";
        public static final String FORMULA_61_VALUE_1 = "10";
        public static final String FORMULA_61_VALUE_2 = "20";
        public static final String FORMULA_61_VALUE_3 = "30";
        public static final String FORMULA_62_VALUE_1 = "10";
        public static final String FORMULA_62_VALUE_2 = "20";
        public static final String FORMULA_62_VALUE_3 = "30";
        public static final String FORMULA_63_VALUE_1 = "k";
        public static final String FORMULA_63_VALUE_2 = "20";
        public static final String FORMULA_63_VALUE_3 = "30";
        public static final String FORMULA_63_VALUE_4 = "30";
        public static final String FORMULA_64_VALUE_1 = "k";
        public static final String FORMULA_64_VALUE_2 = "20";
        public static final String FORMULA_64_VALUE_3 = "30";
        public static final String FORMULA_64_VALUE_4 = "30";
        public static final String FORMULA_65_VALUE_1 = "k";
        public static final String FORMULA_65_VALUE_2 = "20";
        public static final String FORMULA_65_VALUE_3 = "30";
        public static final String FORMULA_65_VALUE_4 = "30";
        public static final String FORMULA_66_VALUE_1 = "k";
        public static final String FORMULA_66_VALUE_2 = "20";
        public static final String FORMULA_66_VALUE_3 = "30";
        public static final String FORMULA_66_VALUE_4 = "30";
        public static final String FORMULA_67_VALUE_1 = "k";
        public static final String FORMULA_67_VALUE_2 = "20";
        public static final String FORMULA_67_VALUE_3 = "30";
        public static final String FORMULA_67_VALUE_4 = "30";
        public static final String FORMULA_68_VALUE_1 = "k";
        public static final String FORMULA_68_VALUE_2 = "20";
        public static final String FORMULA_68_VALUE_3 = "30";
        public static final String FORMULA_68_VALUE_4 = "30";
        public static final String FORMULA_69_VALUE_1 = "k";
        public static final String FORMULA_69_VALUE_2 = "20";
        public static final String FORMULA_69_VALUE_3 = "30";
        public static final String FORMULA_69_VALUE_4 = "30";
        public static final String FORMULA_70_VALUE_1 = "k";
        public static final String FORMULA_70_VALUE_2 = "20";
        public static final String FORMULA_70_VALUE_3 = "30";
        public static final String FORMULA_70_VALUE_4 = "30";
        public static final String FORMULA_71_VALUE_1 = "k";
        public static final String FORMULA_71_VALUE_2 = "20";
        public static final String FORMULA_71_VALUE_3 = "30";
        public static final String FORMULA_71_VALUE_4 = "30";
        public static final String FORMULA_72_VALUE_1 = "k";
        public static final String FORMULA_72_VALUE_2 = "20";
        public static final String FORMULA_72_VALUE_3 = "30";
        public static final String FORMULA_72_VALUE_4 = "30";
        public static final String FORMULA_73_VALUE_1 = "k";
        public static final String FORMULA_73_VALUE_2 = "20";
        public static final String FORMULA_73_VALUE_3 = "30";
        public static final String FORMULA_73_VALUE_4 = "30";
        public static final String FORMULA_74_VALUE_1 = "k";
        public static final String FORMULA_74_VALUE_2 = "20";
        public static final String FORMULA_74_VALUE_3 = "30";
        public static final String FORMULA_74_VALUE_4 = "30";
        public static final String FORMULA_75_VALUE_1 = "k";
        public static final String FORMULA_75_VALUE_2 = "20";
        public static final String FORMULA_75_VALUE_3 = "30";
        public static final String FORMULA_75_VALUE_4 = "30";
        public static final String FORMULA_76_VALUE_1 = "k";
        public static final String FORMULA_76_VALUE_2 = "20";
        public static final String FORMULA_76_VALUE_3 = "30";
        public static final String FORMULA_76_VALUE_4 = "30";
        public static final String FORMULA_77_VALUE_1 = "k";
        public static final String FORMULA_77_VALUE_2 = "20";
        public static final String FORMULA_77_VALUE_3 = "30";
        public static final String FORMULA_77_VALUE_4 = "30";
        public static final String FORMULA_78_VALUE_1 = "k";
        public static final String FORMULA_78_VALUE_2 = "20";
        public static final String FORMULA_78_VALUE_3 = "30";
        public static final String FORMULA_78_VALUE_4 = "30";
        public static final String FORMULA_79_VALUE_1 = "k";
        public static final String FORMULA_79_VALUE_2 = "20";
        public static final String FORMULA_79_VALUE_3 = "30";
        public static final String FORMULA_79_VALUE_4 = "30";
        public static final String FORMULA_80_VALUE_1 = "k";
        public static final String FORMULA_80_VALUE_2 = "20";
        public static final String FORMULA_80_VALUE_3 = "30";
        public static final String FORMULA_80_VALUE_4 = "30";
        public static final String FORMULA_81_VALUE_1 = "k";
        public static final String FORMULA_81_VALUE_2 = "20";
        public static final String FORMULA_81_VALUE_3 = "30";
        public static final String FORMULA_81_VALUE_4 = "30";
        public static final String FORMULA_82_VALUE_1 = "k";
        public static final String FORMULA_82_VALUE_2 = "20";
        public static final String FORMULA_82_VALUE_3 = "30";
        public static final String FORMULA_82_VALUE_4 = "30";
        public static final String FORMULA_83_VALUE_1 = "k";
        public static final String FORMULA_83_VALUE_2 = "20";
        public static final String FORMULA_83_VALUE_3 = "30";
        public static final String FORMULA_83_VALUE_4 = "30";
        public static final String FORMULA_84_VALUE_1 = "k";
        public static final String FORMULA_84_VALUE_2 = "20";
        public static final String FORMULA_84_VALUE_3 = "30";
        public static final String FORMULA_84_VALUE_4 = "30";
        public static final String FORMULA_85_VALUE_1 = "k";
        public static final String FORMULA_85_VALUE_2 = "20";
        public static final String FORMULA_85_VALUE_3 = "30";
        public static final String FORMULA_85_VALUE_4 = "30";
        public static final String FORMULA_86_VALUE_1 = "ary";
        public static final String FORMULA_86_VALUE_2 = "2000";
        public static final String FORMULA_87_VALUE_1 = "FCU";
        public static final String FORMULA_87_VALUE_2 = "8.0";
        public static final String FORMULA_87_VALUE_3 = "Electric d";
        public static final String FORMULA_88_VALUE_1 = "10";
        public static final String FORMULA_88_VALUE_2 = "260";
        public static final String FORMULA_89_VALUE_1 = "10";
        public static final String FORMULA_90_VALUE_1 = "10";
        public static final String FORMULA_90_VALUE_2 = "10";
        public static final String FORMULA_90_VALUE_3 = "10";
        public static final String FORMULA_90_VALUE_4 = "10";
        public static final String FORMULA_90_VALUE_5 = "10";
        public static final String FORMULA_90_VALUE_6 = "10";
        public static final String FORMULA_90_VALUE_7 = "10";
        public static final String FORMULA_90_VALUE_8 = "10";
        public static final String FORMULA_91_VALUE_1 = "80";
        public static final String FORMULA_92_VALUE_1 = "50";
        public static final String FORMULA_92_VALUE_2 = "1";
        public static final String FORMULA_92_VALUE_3 = "2";
        public static final String FORMULA_93_VALUE_1 = "0";
        public static final String FORMULA_93_VALUE_2 = "6";
        public static final String FORMULA_93_VALUE_3 = "6";
        public static final String FORMULA_93_VALUE_4 = "0";
        public static final String FORMULA_94_VALUE_1 = "Unitary";
        public static final String FORMULA_95_VALUE_1 = "16";
        public static final String FORMULA_95_VALUE_2 = "0";
        public static final String FORMULA_95_VALUE_3 = "12";
        public static final String FORMULA_95_VALUE_4 = "16";
        public static final String FORMULA_95_VALUE_5 = "16";
        public static final String FORMULA_95_VALUE_6 = "16";
        public static final String FORMULA_95_VALUE_7 = "0";
        public static final String FORMULA_95_VALUE_8 = "15";
        public static final String FORMULA_96_VALUE_1 = "14";
        public static final String FORMULA_96_VALUE_2 = "12";
        public static final String FORMULA_96_VALUE_3 = "14";
        public static final String FORMULA_96_VALUE_4 = "0";
        public static final String FORMULA_96_VALUE_5 = "14";
        public static final String FORMULA_96_VALUE_6 = "8";
        public static final String FORMULA_96_VALUE_7 = "7";
        public static final String FORMULA_96_VALUE_8 = "Water-Cooled Unitary";
        public static final String FORMULA_96_VALUE_9 = "12";
        public static final String FORMULA_96_VALUE_10 = "12";
        public static final String FORMULA_96_VALUE_11 = "Unitary HP";
        public static final String FORMULA_96_VALUE_12 = "12";
        public static final String FORMULA_96_VALUE_13 = "12";
        public static final String FORMULA_96_VALUE_14 = "12";
        public static final String FORMULA_97_VALUE_1 = "14";
        public static final String FORMULA_97_VALUE_2 = "12";
        public static final String FORMULA_97_VALUE_3 = "14";
        public static final String FORMULA_97_VALUE_4 = "0";
        public static final String FORMULA_97_VALUE_5 = "14";
        public static final String FORMULA_98_VALUE_1 = "14";
        public static final String FORMULA_98_VALUE_2 = "12";
        public static final String FORMULA_98_VALUE_3 = "14";
        public static final String FORMULA_98_VALUE_4 = "0";
        public static final String FORMULA_98_VALUE_5 = "14";
        public static final String FORMULA_99_VALUE_1 = "14";
        public static final String FORMULA_99_VALUE_2 = "12";
        public static final String FORMULA_99_VALUE_3 = "14";
        public static final String FORMULA_99_VALUE_4 = "0";
        public static final String FORMULA_99_VALUE_5 = "14";
        public static final String FORMULA_99_VALUE_6 = "14";
        public static final String FORMULA_99_VALUE_7 = "12";
        public static final String FORMULA_99_VALUE_8 = "14";
        public static final String FORMULA_100_VALUE_1 = "14";
        public static final String FORMULA_100_VALUE_2 = "12";
        public static final String FORMULA_100_VALUE_3 = "14";
        public static final String FORMULA_100_VALUE_4 = "0";
        public static final String FORMULA_100_VALUE_5 = "14";
        public static final String FORMULA_101_VALUE_1 = "14";
        public static final String FORMULA_101_VALUE_2 = "12";
        public static final String FORMULA_101_VALUE_3 = "14";
        public static final String FORMULA_101_VALUE_4 = "0";
        public static final String FORMULA_101_VALUE_5 = "14";
        public static final String FORMULA_102_VALUE_1 = "14";
        public static final String FORMULA_102_VALUE_2 = "12";
        public static final String FORMULA_102_VALUE_3 = "14";
        public static final String FORMULA_102_VALUE_4 = "0";
        public static final String FORMULA_102_VALUE_5 = "14";
        public static final String FORMULA_103_VALUE_1 = "14";
        public static final String FORMULA_103_VALUE_2 = "12";
        public static final String FORMULA_103_VALUE_3 = "14";
        public static final String FORMULA_103_VALUE_4 = "0";
        public static final String FORMULA_103_VALUE_5 = "14";
        public static final String FORMULA_103_VALUE_6 = "8";
        public static final String FORMULA_103_VALUE_7 = "7";
        public static final String FORMULA_103_VALUE_8 = "12";
        public static final String FORMULA_103_VALUE_9 = "12";
        public static final String FORMULA_103_VALUE_10 = "12";
        public static final String FORMULA_103_VALUE_11 = "12";
        public static final String FORMULA_103_VALUE_12 = "12";
        public static final String FORMULA_104_VALUE_1 = "14";
        public static final String FORMULA_104_VALUE_2 = "12";
        public static final String FORMULA_104_VALUE_3 = "14";
        public static final String FORMULA_104_VALUE_4 = "0";
        public static final String FORMULA_104_VALUE_5 = "14";
        public static final String FORMULA_105_VALUE_1 = "2/15/2013";
        public static final String FORMULA_105_VALUE_2 = "0";
        public static final String FORMULA_106_VALUE_1 = "5/15/2014";
        public static final String FORMULA_107_VALUE_1 = "Yes";
        public static final String FORMULA_108_VALUE_1 = "2";
        public static final String FORMULA_108_VALUE_2 = "3";
        public static final String FORMULA_108_VALUE_3 = "4";
        public static final String FORMULA_109_VALUE_1 = "Yes";
        public static final String FORMULA_110_VALUE_1 = "2";
        public static final String FORMULA_110_VALUE_2 = "3";
        public static final String FORMULA_110_VALUE_3 = "4";

        public static final String FORMULA_1_RESULT = "30";
        public static final String FORMULA_2_RESULT = "10025.22";
        public static final String FORMULA_3_RESULT = "250";
        public static final String FORMULA_4_RESULT = "16";
        public static final String FORMULA_5_RESULT = "PTAC";
        public static final String FORMULA_6_RESULT = "HVAC - Air Conditioner - 10 ton";
        public static final String FORMULA_7_RESULT = "10.59211538461537";
        public static final String FORMULA_8_RESULT = "52338.79851581999";
        public static final String FORMULA_9_RESULT = "0.7";
        public static final String FORMULA_10_RESULT = "246000";
        public static final String FORMULA_11_RESULT = "0.6";
        public static final String FORMULA_12_RESULT = "Chiller - 80 ton";
        public static final String FORMULA_13_RESULT = "14.4";
        public static final String FORMULA_14_RESULT = "CO2 Sensor - 80 ton";
        public static final String FORMULA_15_RESULT = "120";
        public static final String FORMULA_16_RESULT = "3.412";
        public static final String FORMULA_17_RESULT = "10";
        public static final String FORMULA_18_RESULT = "1000";
        public static final String FORMULA_19_RESULT = "6000";
        public static final String FORMULA_20_RESULT = "8760";
        public static final String FORMULA_21_RESULT = "Compressed Air - 80";
        public static final String FORMULA_22_RESULT = "1";
        public static final String FORMULA_23_RESULT = "1000";
        public static final String FORMULA_24_RESULT = "2000";
        public static final String FORMULA_25_RESULT = "2000";
        public static final String FORMULA_26_RESULT = "Name";
        public static final String FORMULA_27_RESULT = "8";
        public static final String FORMULA_28_RESULT = "Name";
        public static final String FORMULA_29_RESULT = "Name";
        public static final String FORMULA_30_RESULT = "10";
        public static final String FORMULA_31_RESULT = "10";
        public static final String FORMULA_32_RESULT = "10";
        public static final String FORMULA_33_RESULT = "10";
        public static final String FORMULA_34_RESULT = "10";
        public static final String FORMULA_35_RESULT = "10";
        public static final String FORMULA_36_RESULT = "10";
        public static final String FORMULA_37_RESULT = "10";
        public static final String FORMULA_38_RESULT = "10";
        public static final String FORMULA_39_RESULT = "10";
        public static final String FORMULA_40_RESULT = "10";
        public static final String FORMULA_41_RESULT = "10";
        public static final String FORMULA_42_RESULT = "10";
        public static final String FORMULA_43_RESULT = "10";
        public static final String FORMULA_44_RESULT = "10";
        public static final String FORMULA_45_RESULT = "10";
        public static final String FORMULA_46_RESULT = "10";
        public static final String FORMULA_47_RESULT = "10";
        public static final String FORMULA_48_RESULT = "10";
        public static final String FORMULA_49_RESULT = "10";
        public static final String FORMULA_50_RESULT = "10";
        public static final String FORMULA_51_RESULT = "10";
        public static final String FORMULA_52_RESULT = "1300";
        public static final String FORMULA_53_RESULT = "200";
        public static final String FORMULA_54_RESULT = "200";
        public static final String FORMULA_55_RESULT = "200";
        public static final String FORMULA_56_RESULT = "240000";
        public static final String FORMULA_57_RESULT = "240000";
        public static final String FORMULA_58_RESULT = "240000";
        public static final String FORMULA_59_RESULT = "240000";
        public static final String FORMULA_60_RESULT = "200";
        public static final String FORMULA_61_RESULT = "200";
        public static final String FORMULA_62_RESULT = "200";
        public static final String FORMULA_63_RESULT = "24000";
        public static final String FORMULA_64_RESULT = "24000";
        public static final String FORMULA_65_RESULT = "24000";
        public static final String FORMULA_66_RESULT = "24000";
        public static final String FORMULA_67_RESULT = "600";
        public static final String FORMULA_68_RESULT = "600";
        public static final String FORMULA_69_RESULT = "600";
        public static final String FORMULA_70_RESULT = "600";
        public static final String FORMULA_71_RESULT = "600";
        public static final String FORMULA_72_RESULT = "600";
        public static final String FORMULA_73_RESULT = "600";
        public static final String FORMULA_74_RESULT = "600";
        public static final String FORMULA_75_RESULT = "600";
        public static final String FORMULA_76_RESULT = "600";
        public static final String FORMULA_77_RESULT = "600";
        public static final String FORMULA_78_RESULT = "600";
        public static final String FORMULA_79_RESULT = "600";
        public static final String FORMULA_80_RESULT = "600";
        public static final String FORMULA_81_RESULT = "600";
        public static final String FORMULA_82_RESULT = "600";
        public static final String FORMULA_83_RESULT = "600";
        public static final String FORMULA_84_RESULT = "600";
        public static final String FORMULA_85_RESULT = "600";
        public static final String FORMULA_86_RESULT = "600";
        public static final String FORMULA_87_RESULT = "8";
        public static final String FORMULA_88_RESULT = "2600";
        public static final String FORMULA_89_RESULT = "0";
        public static final String FORMULA_90_RESULT = "748.8000000000001";
        public static final String FORMULA_91_RESULT = "Hotel Occ Controls - 80";
        public static final String FORMULA_92_RESULT = "1";
        public static final String FORMULA_93_RESULT = "1";
        public static final String FORMULA_94_RESULT = "Unitary HP";
        public static final String FORMULA_95_RESULT = "2";
        public static final String FORMULA_96_RESULT = "0";
        public static final String FORMULA_97_RESULT = "600";
        public static final String FORMULA_98_RESULT = "600";
        public static final String FORMULA_99_RESULT = "600";
        public static final String FORMULA_100_RESULT = "600";
        public static final String FORMULA_101_RESULT = "600";
        public static final String FORMULA_102_RESULT = "600";
        public static final String FORMULA_103_RESULT = "600";
        public static final String FORMULA_104_RESULT = "600";
        public static final String FORMULA_105_RESULT = "false";
        public static final String FORMULA_106_RESULT = "15";
        public static final String FORMULA_107_RESULT = "Lighting Controls New Construction";
        public static final String FORMULA_108_RESULT = "24";
        public static final String FORMULA_109_RESULT = "Lighting Fixtures New Construction";
        public static final String FORMULA_110_RESULT = "24";


        public static final By L_TEST_VALUE_1 = By.xpath("//div/table/tbody/tr[2]/td[3]/div");
        public static final By L_TEST_VALUE_1_Field = By.cssSelector("[name='testValue']");
        public static final By L_TEST_VALUE_2 = By.xpath("//div/table/tbody/tr[3]/td[3]/div");
        public static final By L_TEST_VALUE_2_Field = By.cssSelector("[name='testValue']");
        public static final By L_TEST_VALUE_3 = By.xpath("//div/table/tbody/tr[4]/td[3]/div");
        public static final By L_TEST_VALUE_3_Field = By.cssSelector("[name='testValue']");
        public static final By L_TEST_VALUE_4 = By.xpath("//div/table/tbody/tr[5]/td[3]/div");
        public static final By L_TEST_VALUE_4_Field = By.cssSelector("[name='testValue']");
        public static final By L_TEST_VALUE_5 = By.xpath("//div/table/tbody/tr[6]/td[3]/div");
        public static final By L_TEST_VALUE_5_Field = By.cssSelector("[name='testValue']");
        public static final By L_TEST_VALUE_6 = By.xpath("//div/table/tbody/tr[7]/td[3]/div");
        public static final By L_TEST_VALUE_6_Field = By.cssSelector("[name='testValue']");
        public static final By L_TEST_VALUE_7 = By.xpath("//div/table/tbody/tr[8]/td[3]/div");
        public static final By L_TEST_VALUE_7_Field = By.cssSelector("[name='testValue']");
        public static final By L_TEST_VALUE_8 = By.xpath("//div/table/tbody/tr[9]/td[3]/div");
        public static final By L_TEST_VALUE_8_Field = By.cssSelector("[name='testValue']");
        public static final By L_TEST_VALUE_9 = By.xpath("//div/table/tbody/tr[10]/td[3]/div");
        public static final By L_TEST_VALUE_9_Field = By.cssSelector("[name='testValue']");
        public static final By L_TEST_VALUE_10 = By.xpath("//div/table/tbody/tr[11]/td[3]/div");
        public static final By L_TEST_VALUE_10_Field = By.cssSelector("[name='testValue']");
        public static final By L_TEST_VALUE_11 = By.xpath("//div/table/tbody/tr[12]/td[3]/div");
        public static final By L_TEST_VALUE_11_Field = By.cssSelector("[name='testValue']");
        public static final By L_TEST_VALUE_12 = By.xpath("//div/table/tbody/tr[13]/td[3]/div");
        public static final By L_TEST_VALUE_12_Field = By.cssSelector("[name='testValue']");
        public static final By L_TEST_VALUE_13 = By.xpath("//div/table/tbody/tr[14]/td[3]/div");
        public static final By L_TEST_VALUE_13_Field = By.cssSelector("[name='testValue']");
        public static final By L_TEST_VALUE_14 =By.xpath( "//div/table/tbody/tr[15]/td[3]/div");
        public static final By L_TEST_VALUE_14_Field = By.cssSelector("[name='testValue']");
        public static final By L_CALCULATION_LINK = By.cssSelector("[id='calculationLink']");
        public static final By L_CALCULATION_RESULT = By.cssSelector("[name='formulaResultField-inputEl']");
        public static final By ProgramDetailsFormulaTab = By.xpath(".//*[@id='aformulas']/span");
        public static final By Formula_Textarea = By.id("formulaField-inputEl");
        public static final By Formula_Save = By.xpath("//div[starts-with(@id, 'toolbar')][2]//span[text()[.='Save']]");
       
    }


    public interface Program_Bulk_Upload {
    	public static final String Link = "AK-PBU";
		public static final By ADMIN = By
				.xpath("//div/ul/li/a[text()[contains(.,'ADMIN')]]");
		public static final By L_PROGRAMS = By
				.cssSelector(".main_bg:nth-child(2)>a");
		public static final String Link_PBU_Void_Prog = "AK-PBU-Void";

    }

    public interface Planning {
    	public static final By PL_CREATE_PROG_BTN = By.xpath("//span[text()[contains(.,'+ Create Program')]]/../../.."); // go up click on a Form 'Create New Program'																					
		public static final By PL_CHOOSE_EXISTING = By.xpath("//div/input[@placeholder='Program Details.....']");
		public static final By PL_NAME_FIELD = By.xpath("//*[@name='name']");
		public static final By PL_DESCRIPTION_FIELD = By.xpath("//*[@name='description']");
		public static final By PL_TYPE_DD_FIELD = By.xpath("//*[@name='programTypeId']");
		public static final By PL_SECTORS_DD_FIELD = By.xpath("//label/span[text()[contains(.,'Sectors')]]/../..//div/input[@placeholder='Select to add']");
		public static final By PL_SERVICE_TYPE_DD_FIELD = By.xpath("//label/span[text()[contains(.,'Service Type')]]/../..//div/input[@placeholder='Select " + "to add']");
		public static final By PL_DEFAULT_SECTOR_FIELD = By.xpath("//*[@name='defaultSectorId']");
		public static final By PL_START_YEAR = By.xpath("//*[@name='planningStartYear']");
		public static final By PL_END_YEAR = By.xpath("//*[@name='planningEndYear']");
		public static final By PL_PROG_START_YEAR = By.xpath("//*[@name='programStartYear']");
		public static final By PL_PROG_END_YEAR = By.xpath("//*[@name='programEndYear']");
		//public static final By PL_SAVE_PROG_BTN = By.xpath("//span[text()[contains(.,'Save')]]/../../..");
		public static final By PL_SAVE_PROG_BTN = By.xpath("//div[contains(@id,'overviewProgramDetails')]//span[text()[contains(.,'Save')]]/../../..");
		public static final By PL_CostEffective = By.xpath("//div[text()[.='Cost Effectiveness Parameters']]/../../../..");
		public static final By PL_Btn_EditMeasure = By.xpath("//div[text()[.='Measures']]/../a");
		public static final By PL_CheckBox = By.xpath("//div[contains(@class, 'locked_grid_with_checkbox')][1]//table[1]/tbody/tr/td[1]"); 
		public static final By PL_Type = By.xpath("//div/span[text()[contains(.,'Type')]]"); 
		public static final By PL_DropPrompt = By.cssSelector("#dropPromptText");
		public static final By Cost_Effectiveness_Header = By.xpath("//div[text()[.='Cost Effectiveness Parameters']]/../../../..");
		
		public static final By PL_SHOW_20 = By.xpath("//div/a[text()[contains(.,'20')]]");
		public static final By PL_SHOW_50 = By.xpath("//div/a[text()[contains(.,'50')]]");
		public static final By PL_SHOW_100 = By.xpath("//div/a[text()[contains(.,'100')]]");
		public static final By PL_SORT_LAST_UPDATED = By.xpath("//div/a/span[text()[contains(.,'Last Updated')]]");
		public static final By PL_TYPE_DD = By.xpath("//*[@name='type_1_field-inputEl']");
		public static final By PL_KEYWORD_TXT = By.xpath("//input[@name='keyword_1_field-inputEl']");
		public static final By PL_SEARCH_BTN = By.xpath("//input[@id='searchContact' and @value='Search']");
		public static final By PL_OVERVIEW_LINK = spanText("Overview");
		public static final By PL_INPUT_LINK = spanText("Input");
		public static final By PL_BUDGETING_LINK = spanText("Budgeting");
		public static final By PL_ANALYZE_LINK = spanText("Analyze");
		// After the BTN save new program was clicked 'Overview'
		public static final By PL_EDIT_PARAMETERS_BTN = By.xpath("//div[4]/em/button/span[text()[contains(.,'Edit')]]");
		public static final By PL_SAVE_PARAM_BTN = By.xpath("//div/em/button/span[text()[contains(.,'Save')]]");
		public static final By PL_PROGRAME_NAME = By.cssSelector("#planningTitle");
		// Parameters Fields
		public static final By PL_MARGINAL_COST = By.xpath("//*[@name='marginalCostId']");
		public static final By PL_SYSTEMS_INPUT = By.xpath("//*[@name='systemInputId']");
		public static final By PL_MARGINAL_RATE = By.xpath("//*[@name='marginalRateId']");
		public static final By PL_CUSTOMERS_FORECAST = By.xpath("//*[@name='planningCustForecastLibId']");

		public static final By PL_GAS_MARGINAL_COST = By.xpath("//*[@name='gasMarginalCostId']");
		public static final By PL_GAS_SYSTEMS_INPUT = By.xpath("//*[@name='gasSystemInputId']");
		public static final By PL_GAS_MARGINAL_RATE = By.xpath("//*[@name='gasMarginalRateId']");
		public static final By PL_GAS_CUSTOMERS_FORECAST = By.xpath("//*[@name='gasCustForecastLibId']");
		// Measures
		public static final By PL_EDIT_MEASURES_BTN = By.xpath("//span[1][text()[contains(.,'+ Edit Measures')]]");
		// 'Edit Measures'
		public static final By PL_SELECT_TAXONOMY = By.xpath("//div/input[@value='Categories']");
		public static final By PL_SEARCH_ALL = spanText("Search All");
		public static final By PL_DRAG_AND_DROP_TARGET = By.cssSelector("#dropPromptText");
		public static final By PL_TYPE_COLUMN = By.xpath("//div/span[text()[contains(.,'Type')]]");
		public static final By PL_INCLUDE_SELECTED_BTN = spanText("Include Selected");
		public static final By PL_DONE_MEASURING_BTN = spanText("Done Measure Editing");
		// Input screen
		public static final By PL_ELIGIBILITY_BTH = spanText("ELIGIBILITY");
		public static final By PL_PARTICIPATION_BTH = spanText("PARTICIPATION");
		public static final By PL_COST_BTH = spanText("COST");
		// Input - Eligibility
		// //input[starts-with(@name, 'combo-') and @type='text']
		// //label/span[text()[contains(.,'Support
		// Staff')]]/../..//input[@type='text']
		public static final By PL_ELIGIBILITY_ROW = By.xpath("//div[starts-with(@componentid, 'inputMeasuresList')]/div/table/tbody/tr[1]/td[1]");
		public static final By PL_PARTICIPATION_ROW = By.xpath("//div[starts-with(@id, 'inputMeasuresList')]/../..//table[contains(@class, 'x-grid-item')]/..//tr[contains(@class, 'x-grid-row')]");
		public static final By PL_COST_ROW = By.xpath("//div[starts-with(@id, 'inputMeasuresList')]/../..//table[contains(@class, 'x-grid-item')]/..//tr[contains(@class, 'x-grid-row')]");
		public static final By PL_SECTOR_DD = By.xpath("//input[@name='eSectorId']");
		public static final By PL_FACILITY_TYPE_DD = By.xpath("//input[@name='eFacilityTypeId']");
		public static final By PL_CONSTRUCTION_TYPE_DD = By.xpath("//input[@name='eConstructionTypeId']");
		public static final By PL_EQUIPMENT_DD = By.xpath("//input[@name='eheatingEquipTypeId']");
		// Input - Participation
		public static final By PL_START_TXT = By.xpath("//*[@name='pStart']");
		public static final By PL_MAX_TXT = By.xpath("//*[@name='pMax']");
		public static final By PL_INCREMENT_TXT = By.xpath("//*[@name='pIncrement']");
		public static final By PL_UNIT_DD = By.xpath("//*[@name='pUnit']");
		public static final By PL_NO_YEARS_TXT = By.xpath("//*[@name='pNumYears']");
		public static final By PL_QTY_CUSTOMERS_TXT = By.xpath("//*[@name='pQtyPerCustomer']");
		// Input - Cost
		public static final By PL_CAP_TYPE = By.xpath("//input[@name='cCapType']");
		public static final By PL_CAP_VALUE = By.xpath("//input[@name='cCapValue']");
		public static final By PL_INSTALL_COST = By.xpath("//input[@name='attribute164']");
		public static final By PL_INCENTIVE_CUSTOMER = By.xpath("//*[@name='attribute154']");
		public static final By PL_CREDIT_AMOUNT = By.xpath("//*[@name='attribute148']");
		public static final By PL_PARTNER_AMOUNT = By.xpath("//*[@name='attribute138']");
		public static final By PL_CUSTOMER_AMMOUNT = By.xpath("//*[@name='attribute130']");
		public static final By PL_INCREMENTAL = By.xpath("//*[@name='attribute129']");
		public static final By PL_TextBox_CAP_TYPE = By.xpath("//input[@name='cCapValue']");
		
		// Budgeting Cost - Budget Cost Reference Parameters
		public static final By PL_EDIT_BUDG_BTN = spanTextContains("Edit");
		public static final By PL_REFERENCE_SET_DD = By.xpath("//*[@name='planningBudgetCostReferenceId']");
		public static final By PL_SAVE_BTN = spanText("Save");
		public static final By PL_Edit_Btn_PlanningProg = By.xpath("//div[text()[.='Budget Cost Reference Parameters']]/../a[2]");
		public static final By PL_PlanningCostrefID = By.xpath("//*[@name='planningBudgetCostReferenceId']");
		public static final By PL_BudgetCostRefPara = By.xpath("//div[text()[.='Budget Cost Reference Parameters']]/../a[3]");
		public static final By PL_DrpDwn_BudgetCategoryID = By.xpath("//*[@name='budgetCategoryId']");
		public static final By PL_DrpDwn_Methodlogy = By.xpath("//*[@name='methodology']");
		public static final By PL_TextBox_Calendar = By.xpath("//*[@name='value1']");		
		
		// Budgeting Cost Categories
		public static final By PL_ADD_BTN_CC = spanText("Add");
		public static final By PL_BUD_CAT_DD = By.xpath("//*[@name='budgetCategoryId']");
		public static final By PL_METH_DD = By.xpath("//*[@name='methodology']");
		public static final By PL_VALUE1_TXT = By.xpath("//*[@name='value1']");
		// / Analyze
		public static final By PL_RUN_TEST_BTN = By.linkText("Run Tests");
		public static final By PL_SAVE_BTN_ANALYZE = spanText("Save");
		public static final By PL_SAVE_AS_BTN_ANALYZE = By.linkText("Save As");
		public static final By PL_NAME_TXT = By.xpath("//input[@id='saveASName-inputEl']");
		// Portfolio
		public static final By PL_PORTFOLIO_LINK = spanText("Portfolio");
		public static final By PL_CREATE_PORTF_BTN = spanText("+ Create Portfolio");
		public static final By PL_NAME_PF_TXT = By.xpath("//*[@name='portfolioName']");
		public static final By PL_DESCRIPTION_TXT = By.xpath("//*[@name='description']");
		public static final By PL_SAVE_PRF_BTN = spanText("Save");		
		
		// Edit Portfolio
		public static final By PL_EDIT_PROGRAMS = spanText("+ Edit Programs");
		public static final By PL_SELECT_TYPE = By.cssSelector(".x-form-trigger");
		public static final By PL_SEARCH_ALL_BTN = spanText("Search All");
		public static final By PL_CHECK_BOX = By.cssSelector(".x-grid-row-checker");
		public static final By PL_DONE_PROGRAM_EDIT = spanText("Done Program Editing");
		public static final By PL_PORTFOLIO_ANALYZER = By.xpath("//div/em/button/span[text()[contains(.,'Portfolio Analyzer')]]");
		public static final By PL_ANALYZE_BTN = By.xpath("//div/em/button/span[text()[contains(.,'Analyze')]]");
		public static final By PL_PortFolioNameLabel = By.xpath("//div/a/span[text()[contains(.,'Portfolio Name')]]");
		public static final By PL_Link_LastUpdate = By.xpath("//div/a/span[text()[contains(.,'Last Updated')]]");
		
    }

    public interface Marketing{
    	// Tabs
    			public static final By MR_CAMPAIGNS_TAB = spanText("Campaigns");
    			public static final By MR_DASHBOARD_TAB = spanText("Dashboard");
    			public static final By MR_LEADS_TAB = spanText("Leads");
    			public static final By MR_EMAIl_TEMPLATES = spanText("Email Templates");
    			// Create Campaign
    			public static final By MR_CREATE_CAMPAIGN_BTN = spanText("Create Campaigns");
    			public static final By MR_POPUP_HEADER = divText("Create Campaign");
    			public static final By MR_NAME_TXT = By.xpath("//input[@name='campaignName']");
    			public static final By MR_STATUS_DD = By.xpath("//input[@name='statusId']");
    			public static final By MR_START_DATE = By.xpath("//input[@name='startDate']");
    			public static final By MR_END_DATE = By.xpath("//input[@name='endDate']");
    			public static final By MR_SUPPORT_STAFF = By.xpath("//label/span[text()[contains(.,'Support Staff')]]/../..//input[@type='text']");
    			public static final By MR_CAMPAIGN_MANAGER = By.xpath("//label/span[text()[contains(.,'Campaign Manager')]]/../..//input[@type='text']");
    			public static final By MR_OBJECTIVE = By.xpath("//textarea[@name='objective']");
    			public static final By MR_DESCRIPTION = By.xpath("//textarea[@name='description']");
    			public static final By MR_TYPE = By.xpath("//input[@name='campaignTypeId']");
    			public static final By MR_RELATED_PROGS = By.xpath("//label/span[text()[contains(.,'Related Programs')]]/../..//input[@type='text']");
    			public static final By MR_Drpdwn_CampaignSearch =  By.xpath("//div[contains(@class, 'campaignSearchContainer')]//input[@role='combobox']");
    			public static final By MR_TextBoxCampaignSearch = By.xpath("//div[contains(@class, 'campaignSearchContainer')]//input[@role='textbox']");
    			public static final By MR_MsgBox = By.xpath("//div[contains(@class,'main')]/../div[contains(@id,'messagebox')]");
    			public static final By MR_MsgBox_Btn_OK = By.xpath("//div[contains(@class,'main')]/../div[contains(@id,'messagebox')]//div[contains(@id,'toolbar')]//a//span[contains(text(),'OK')]");
    			
    			
    			
    			// Show All Filter
    			public static final By MR_SHOW_ADDED_BY_YOU = By.partialLinkText("Added by You");
    			public static final By MR_SHOW_COMPLETED = By.partialLinkText("Completed");
    			public static final By MR_SHOW_IN_PROGRESS = By.partialLinkText("In Progress");
    			public static final By MR_SHOW_ALL = By.partialLinkText("All");
    			// Sort By
    			public static final By LUD = By.xpath("//div/a/span[text()[contains(., 'Last Updated Date')]]");
    			public static final By ED = By.xpath("//div/a/span[text()[contains(., 'End Date')]]");
    			public static final By SD = By.xpath("//div/a/span[text()[contains(., 'Start Date')]]");
    			public static final By Type = By.xpath("//div/a/span[text()[contains(., 'Type')]]");
    			public static final By Name = By.xpath("//div/a/span[text()[contains(., 'Name')]]");
    			// pop up
    			public static final By MR_POPUP_OK_BTN = spanText("OK");
    			// campaign page
    			public static final By MR_ADD_BTN = spanText("+ Add");
    			// add 'Activity' // //*[@id=''] //*[@name='']
    			public static final By MR_ACTIVITY_HEADER = spanText("Activities");
    			public static final By MR_TITLE = By.xpath("//*[@name='activityTitle']");
    			public static final By MR_STATUS = By.xpath("//*[@name='statusId']");
    			public static final By MR_DESCRIPTION_2 = By.xpath("//*[@name='description']");
    			public static final By MR_PRIORITY = By.xpath("//*[@name='priorityId']");
    			public static final By MR_NEED_BY_DATE = By.xpath("//*[@name='needByDate']");
    			public static final By MR_PR_COMPLETE = By.xpath("//*[@name='percentComplete']");
    			public static final By MR_ASSIGNED_TO = By.xpath("//*[@name='assignedToId']");
    			public static final By MR_NOTES = By.xpath("//*[@name='notes']");
    			// add 'Emails'
    			public static final By MR_EMAIL_HEADER = spanText("Emails");
    			public static final By MR_EMAIL_NAME_TXT = By.xpath("//*[@name='templateName']");
    			public static final By MR_SUBJECT_TXT = By.xpath("//*[@name='subject']");
    			public static final By MR_BODY_AREA = By.xpath("//*[@name='body']");
    			public static final By MR_NAME_TOKEN = divText("${CAMPAIGN_NAME}");
    			public static final By MR_NUMBER_TOKEN = divText("${TM_NUMBER}");
    			public static final By MR_TEMPLATE_ARR_DD = By.xpath("//label/span[text()[contains(.,'Template Attachment')]]/../..//input[@type='text']");
    			public static final By MR_SAVE_EMAIL = spanText("Save");
    			public static final By MR_CLOSE_EMAIL = spanText("Close");
    			// Participants
    			public static final By MR_PARTICIPANTS = spanText("Participants");
    			public static final By MR_OPTIONS = spanText("Options");
    			public static final By MR_SELECT_EXISTING = By.xpath("//input[starts-with(@name, 'combo-') and @type='text']");
    			public static final By MR_IncludedMemberGrid = By.xpath("//div[contains(@class, 'includedMembersGrid')][1]//span[text()[.='Close']]/../../..");
    			public static final By MR_LabelSelectExisting = By.xpath("//label/span[text()[contains(.,'Select Existing:')]]/../..//input[@type='text']");
    			// mark as lead
    			public static final By MR_MARK_AS_LEAD = spanText("Mark as Lead");
    			public static final By MR_LEAD_QUALIFIER = By.xpath("//input[@name='leadQualifierId']");
    			public static final By MR_LEAD_STATUS = By.xpath("//input[@name='leadStatusId']");
    			public static final By MR_OWNER = By.xpath("//*[@name='leadOwnerId']");
    			//public static final By MR_CampaignBody = By.xpath("//div[@id='campaign-main-body-body']//table/tbody/tr[2]/td[1]//a");
    			public static final By MR_CampaignBody = By.xpath("//div[@id='campaign-main-body-body']//table/tbody/tr/td[1]//a");
    			public static final By MR_Lead_MsgBox_Btn_OK = By.xpath("//div[contains(@class,'main')]/../div[contains(@id,'messagebox')]/div[3]//span[contains(text(),'OK')]");
    			public static final By MR_Lead_Btn_AddNotesForLeads = By.xpath("//div[contains(@class,'widget_content_cl')]//a//span[contains(text(),'Add Notes for Leads')]");
    			// //div[@id='variable-program-cost-window'])//span[text()[.='Save']]
    			public static final By MR_SAVE_LEAD = By.xpath("//div[starts-with(@id, 'markaslead-') and @data-ref='body']//span[text()[.='Save']]");
    			// public static final By MR_SAVE_LEAD = spanText("Save"); //
    			// markaslead-1264
    			// Leads
    			public static final By MR_LEADS = spanText("Leads");
    			// Select a Lead by anchor text
    			// Add Opportunity
    			public static final By MR_ADD_OPPORTNUITY = spanText("Add Opportunity");
    			public static final By MR_PROGRAM_DD = By.xpath("//*[@name='programId']");
    			public static final By MR_STATUS_DD_OPP = By.xpath("//*[@name='statusId']");
    			public static final By MR_ASSIGNED_TO_OPP = By.xpath("//*[@name='assignedToId']");
    			public static final By MR_COMMENTS = By.xpath("//*[@name='notes']");
    			public static final By MR_MR_OPPORTNUITY_SAVE = spanText("Save");
    			public static final By MR_Row_Campaign = By.xpath("//div[@id='campaign-main-body-body']//table[1]//td[1]//a");
    			
    			// Add Notes
    			public static final By MR_ADD_NOTES_FOR_LEAD = spanText("Add Notes for Leads");
    			public static final By MR_DATE_OF_CONTACT_NOTES = By.xpath("//*[@name='contactDateStr']");
    			public static final By MR_CONTACTED_BY_NOTES = By.xpath("//*[@name='contactedBy']");
    			public static final By MR_SUBJECT_NOTES = By.xpath("//*[@name='subject']");
    			public static final By MR_NOTES_NOTES = By.xpath("//*[@name='notes']");
    			public static final By MR_F_UP_CHECK = By.xpath("//input[contains(@class, 'x-form-checkbox') and @type='button']");
    			public static final By MR_EMAIL_NOTES = By.xpath("//*[@name='followUpEmailAddress']");
    			public static final By MR_TEXT_NOTES = By.xpath("//*[@name='followUpEmailText']");
    			public static final By MR_F_UP_DATE = By.xpath("//*[@name='followUpDateStr']");
    			// Activities
    			public static final By MR_ACT_TITLE = By.xpath("//*[@name='activityTitle']");
    			public static final By MR_ACT_STATUS = By.xpath("//*[@name='statusId']");
    			public static final By MR_ACT_DESCRIPTION = By.xpath("//*[@name='description']");
    			public static final By MR_ACT_PRIORITY = By.xpath("//*[@name='description']");
    			public static final By MR_ACT_NEED_BY_DATE = By.xpath("//*[@name='needByDate']");
    			public static final By MR_ACT_PERCENT_COMPLETE = By.xpath("//*[@name='percentComplete']");
    			public static final By MR_ACT_ASSIGNED_TO = By.xpath("//*[@name='assignedToId']");
    			public static final By MR_ACT_NOTES = By.xpath("//*[@name='notes']");
    }

    public interface Assessments{
    	// Onsite Programs
    			public static final By AS_SubTabOnsiteProg = By.xpath(".//*[@id='assessments_flyout']//a[contains(text(),'Onsite Programs')]");
    			//public static final By AS_ADD_NEW_PROGRAM = By.xpath("//input[@value='+ Add New Program']");
    			public static final By AS_ADD_NEW_PROGRAM = By.cssSelector("#measure_library_top_button_right");
    			public static final By AS_PROG_NAME = By.xpath("//input[@name='auditProgramName']");
    			public static final By AS_PROG_DESCRIPTION = By.xpath("//textarea[@name='description']");
    			//public static final By AS_ASSESSMENT_TYPE = By.xpath("//input[@name='assessmentType']");
    			public static final By AS_ASSESSMENT_TYPE = By.xpath("//div[@class='form_content']//input[@name='assessmentType']");
    			public static final By AS_SERVICE_TYPE = By.xpath("//label/span[text()[contains(.,'Service Type')]]/../..//input[@type='text']");
    			//public static final By AS_PROG_STATUS = By.xpath("//input[@name='programStatus']");
    			public static final By AS_PROG_STATUS = By.xpath("//div[@class='form_content']//input[@name='programStatus']");
    			public static final By AS_NUM_PREFIX = By.xpath("//input[@name='auditProgramNumber']");
    			public static final By AS_OnsiteProg_Btn_Ok = By.xpath("//div[contains(@class,'main')]/../div[contains(@id,'messagebox')]//a[1]//span[contains(text(),'OK')]"); 
    			// Assessment Settings
    			public static final By AS_PROG_MANAGER = By.xpath("//input[@name='program_programmgrs_0_field_combobox-inputEl']");
    			public static final By AS_PROJ_MANAGER = By.xpath("//input[@name='program_projectmgrs_0_field_combobox-inputEl']");
    			public static final By AS_APP_USER = By.xpath("//input[@name='program_supportstaff_0_field_combobox-inputEl']");
    			// Configuration Parameters
    			public static final By AS_ADD_BTN = spanText("Add");
    			public static final By AS_LABEL = By.xpath("//input[@name='label']");
    			public static final By AS_VALUE = By.xpath("//input[@name='value']");
    			public static final By AS_UPDATE = By.linkText("Update");
    			// public static final By AS_UPDATE =
    			// By.xpath("//div[contains(@class,'manageScreensGrid ')]/div[3]/div[2]/div/div/a[1]/span/span");
    			public static final By AS_SAVE = By.linkText("Save");

    			// Attribute Groups
    			public static final By AS_ATTR_GROUP = spanText("Attribute Groups");

    			// Add Name
    			public static final By AS_ATTR_NAME = By.xpath("//div[starts-with(@id,'managescreen')]//input[@name='auditProgramGroupName']");

    			// Add Description
    			public static final By AS_ATTR_DESCR = By.xpath("//div[starts-with(@id,'managescreen')]//input[@name='description']");

    			public static final By AS_ATTR_ORDER = By.xpath("//div[starts-with(@id,'managescreen')]//input[@name='order']");

    			public static final By AS_ATTR_SAVE_BTN = By.xpath("//div[starts-with(@id,'managescreen')]//span[text()[.='Save']]");

    			// Click YES on Confirm pop up Manage screen
    			public static final By AS_ATTR_CON_BTN = By.xpath("/html/body/div[4]/div[3]/div/div/a[2]/span/span/span[2]");

    			// Association Types
    			public static final By AS_ASS_TYPE = spanText("Association Types");
    			public static final By AS_ASS_ADD = By.xpath("//div[@id='associationTypesPopup']//span[text()[.='Add']]");
    			public static final By AS_ASS_KEY = By.xpath("//div[@id='associationTypesPopup']//input[@name='key']");
    			public static final By AS_ASS_NAME = By.xpath("//div[@id='associationTypesPopup']//input[@name='name']");
    			public static final By AS_ASS_DESCR = By.xpath("//div[@id='associationTypesPopup']//input[@name='description']");
    			public static final By AS_ASS_SAVE = By.xpath("//div[@id='associationTypesPopup']//span[text()[.='Save']]");
    			// Attributes
    			public static final By AS_ATTR = By.linkText("Attributes");
    			public static final By AS_ATTR_HINT_TEXT = By.xpath("//*[@name='hintText']");
    			public static final By AS_ATTR_Drpdwn_SelectScreen = By.xpath("//div[@id='summary']//input[@role='combobox']");
    			public static final By AS_ATTR_Drpdwn_SpaceAttribute = By.xpath("//div[@id='summary']/div/div[1]/div[1]//input");
    			public static final By AS_ATTR_Drpdwn_SearchAttri = By.xpath("//input[@id='admin_attributes_search_combobox-inputEl']");
    			public static final By AS_ATTR_TextBox_SearchAttri = By.xpath("//input[@role='textbox']");
    			public static final By AS_ATTR_Tablerow = By.xpath("//div[@id='right-container']//table");
    			public static final By AS_ATTR_Options = By.xpath("//span[text()[.='Options:']]/../../div/div/div[2]");
    			
    			// public static final By AS_ATTR_DROP_TARGET =
    			// By.xpath("//div[starts-with(@id, 'grid')]//div[@class='x-grid-item-container']");
    			public static final By AS_ATTR_DROP_TARGET = By.xpath("//div[@id='summary']/div/div//div[@data-ref='body']/div");
    			public static final By AS_ATTR_SAVE = Locator.spanText("Save");
    			// Equipment
    			public static final By EQUIPMENT = By.linkText("Equipment");
    			public static final By AS_ADD_ASSOCIATION = spanText("+ Add Associations");
    			public static final By AS_ASS_EQU_TYPE = By.xpath("//label/span[text()[contains(.,'Equipment Type')]]/../..//input[@type='text']");
    			public static final By AS_ASS_BASELINE = By.xpath("//label/span[text()[contains(.,'Baseline')]]/../..//input[@type='text']");
    			public static final By AS_ASS_EQU_ASS_TYPE = By.xpath("//label/span[text()[contains(.,'Association Type')]]/../..//input[@type='text']");
    			public static final By AS_ASS_ITEM = divText("qa1");
    			public static final By AS_ASSOCIATION_ADD = spanText("Add");
    			// Measures here
    			public static final By MEASURES = By.linkText("Measures");
    			public static final By AS_M_CATEGORY = By.xpath("//label/span[text()[contains(.,'Category')]]/../..//input[@type='text']");
    			public static final By AS_M_TYPE = By.xpath("//label/span[text()[contains(.,'Type')]]/../..//input[@type='text']");
    			public static final By AS_M_SUB_TYPE = By.xpath("//label/span[text()[contains(.,'Sub-Type')]]/../..//input[@type='text']");
    			public static final By AS_M_SAVE = spanText("Save");
    			// Measure Configuration
    			public static final By MEASURE_CONF = By.linkText("Measure Configuration");
    			public static final By AS_MC_FORMULA = By.xpath("//label/span[text()[contains(.,'Formula')]]/../..//input[@type='text']");
    			public static final By AS_MC_SAVE = spanText("Save");
    			// Measure Identification
    			public static final By MEASURE_IDENT = By.linkText("Measure Identification");
    			public static final By ASS_MI_ADD_IDENTIFICATION = spanText("+ Add Identification");
    			public static final By ASS_MI_SELECT_BASELINE = By.xpath("//label/span[text()[contains(.,'Select Baseline')]]/../..//input[@type='text']");
    			public static final By ASS_MI_SELECT_MEASURE = By.xpath("//label/span[text()[contains(.,'Select Measure')]]/../..//input[@type='text']");
    			public static final By ASS_MI_SAVE = By.xpath("//div[@id='measueIdentificationPopup']//span[text()[.='Save']]");
    			// Formulas
    			public static final By ADD_NEW_FORMULA = By.xpath("//input[@value='Add New Formula']");
    			public static final By AS_FORM_ACTIVE_INDICATOR = By.xpath("//input[@name='formulaActiveFlag-inputEl']");
    			public static final By AS_FORMULA_NAME = By.xpath("//input[@name='formulaName']");
    			public static final By AS_FORMULA_FIELD = By.xpath("//textarea[@name='formulaField-inputEl']");
    			public static final By AS_FORMULA_RESULT = By.xpath("//textarea[@name='formulaResultField-inputEl']");
    			public static final By AS_CALCULATE_BTN = By.linkText("Calculate");
    			// Add New Token
    			public static final By AS_ADD_TOKEN = By.linkText("+ Add Token");
    			public static final By AS_TOKEN_NAME = By.xpath("//input[@name='name']");
    			public static final By AS_TOKEN_TYPE = By.xpath("//input[@name='tokenType']");
    			public static final By AS_ATTRIBUTE_NAME = By.xpath("//input[@name='attributeName']");
    			public static final By AS_EXPRESSION_NAME = By.xpath("//input[@name='expression']");
    			public static final By AS_SAVE_TOKEN = By.xpath("//div[starts-with(@id, 'toolbar')]//span[text()[.='Save']]");
    			// Add Variable
    			public static final By AS_ADD_NEW_VAR = By.linkText("+Add");
    			public static final By AS_VARIABLE_NAME = By.xpath("//*[@name='name']");
    			public static final By AS_TEST_VALUE = By.xpath("//*[@name='testValue']");
    			public static final By AS_DEFAULT_TEXT = By.xpath("//*[@name='defaultValue']");
    			public static final By AS_TOKEN = By.xpath("//*[@name='formulas_token_combo']");
    			public static final By AS_DATA_TYPE = By.xpath("//*[@name='tokenDataType']");
    			public static final By AS_SAVE_FORMULA = By.xpath("//input[@value='Save']");
    }

    public interface FormAssignment{
        public static final By TYPE = By.xpath("//input[@name='type']");
        public static final By AUTO = By.xpath("//input[@name='autoAssignmentFormula']");
        public static final By MANUAL = By.xpath("//input[@name='manualAssignmentListFormula']");
        public static final By APPLY_BTN = By.xpath("//*[@id='form_assignment_config_form_save']");
        
        
        public static final By formConfigurationsButton = By.xpath("//span[text()='Form Configurations']");
        public static final By configureFormAsignmentOption = By.xpath("//span[text()='Configure Form Assignment']");
        public static final By configureFormAsignmentType = By.xpath(" .//input[@name='type']");
        public static final By configureFormAsignmentautoAssignment = By.xpath(" .//input[@name='autoAssignmentFormula']");
        public static final By configureFormManualAssignment = By.xpath(" .//input[@name='manualAssignmentListFormula']");
        public static final By formSaveButton = By.xpath(".//input[@id='saveButton']");
        public static final By normalsection = By.xpath("//div[@id='webFormDesigner_center_regionId-body']//div[text()='Normal']");
        public static final By normalsectionexpandbutton = By.xpath("//div[@id='webFormDesigner_center_regionId-body']//div[text()='Normal']/../../div[6]");
        public static final By normalsectionapplicationfield = By.xpath("//label[text()='Application Status:']");
        public static final By normalsectionapplicationfieldallowedOption = By.xpath("//div[text()='AllowedOptions']/../..//div[text()='select']");
        public static final By normalsectionapplicationfieldallowedOptionactiveotpion = By.xpath("//div[text()='Active']");
        public static final By normalsectionapplicationfieldallowedOptionrejectotpion = By.xpath("//div[text()='Rejected']");
        public static final By normalsectionapplicationfieldallowedapply_btn = By.xpath("//input[@value='Apply']");
        public static final By Program_Form_AppStatus_Token = By.xpath("//div[text()='Token']/../..//td[2]/div");
        public static final By Program_Form_AppStatus_Token_InputBox = By.id("Token-inputEl");
    }

    public interface Dashboard {
    	// Electric
    			public static final By DB_ELECTRIC = By.xpath("//*[@value='Electric']");
    			public static final By DB_GAS = By.xpath("//*[@value='Gas']");
    			public static final By DB_RENEWABLE = By.xpath("//*[@value='Renewable']");
    			public static final By DB_WATER = By.xpath("//*[@value='Water']");
    			public static final By DB_SPEND_BY_CHART = By.xpath("//div/label[text()[contains(.,'Spend By')]]");
    			public static final By DB_SPENDBY_CAT = By.partialLinkText("Category");
    			public static final By DB_SPENDBY_SEC = By.partialLinkText("Sector");
    			public static final By DB_CLOSE_POP_UP = By.xpath("//div/img[@class='x-tool-img x-tool-close']");
    			public static final By DB_YTD_BUDGET = By.xpath("//div/label[text()[contains(.,'YTD Budget')]]");
    			public static final By DB_ELECTRIC_KWH = By.xpath("//div/label[text()[contains(.,'Cost/kWh Installed')]]");
    			public static final By DB_ENERGY_GRAPH = By.partialLinkText("Energy");
    			public static final By DB_DEMAND_GRAPH = By.partialLinkText("Demand");
    			public static final By DB_Graph_Label_Units = By.xpath("//div[@id='lineChartGlobalId_chart']/span/*[name()='svg']/*[name()='g'][3]/*[name()='g'][@class='fusioncharts-yaxis-0-gridlabels']/*[1]/*");
    			public static final By DB_Graph_Label_Legends = By.xpath("//div[@id='lineChartGlobalId_chart']/span/*[name()='svg']/*[name()='g'][@class='fusioncharts-legend']/*[5]/*[1]");
    			public static final By DB_Graph_Label_Legends1 = By.xpath("//div[@id='lineChartGlobalId_chart']/span/*[name()='svg']/*[name()='g'][@class='fusioncharts-legend']/*[9]/*[1]");
    			public static final By DB_PortfolioGraph_Label_Units = By.xpath("//div[@id='portfolioDemandId_chart']/span/*[name()='svg']/*[name()='g'][3]/*[name()='g'][@class='fusioncharts-yaxis-0-gridlabels']/*[1]/*");
    			public static final By DB_Portfolio_Graph_Label_Legends = By.xpath("//div[@id='portfolioDemandId_chart']/span/*[name()='svg']/*[name()='g'][@class='fusioncharts-legend']/*[5]/*[1]");
    			public static final By DB_Portfolio_Graph_Label_Legends1 = By.xpath("//div[@id='portfolioDemandId_chart']/span/*[name()='svg']/*[name()='g'][@class='fusioncharts-legend']/*[9]/*[1]");
    			// Gas
    			public static final By DB_GAS_THERM = By.xpath("//div/label[text()[contains(.,'Cost/Therm Installed')]]");

    			// Renewable
    			public static final By DB_RENEWABLE_THERM = By.xpath("//div/label[text()[contains(.,'Cost/kW Installed')]]");

    			// Water
    			public static final By DB_WATER_THERM = By.xpath("//div/label[text()[contains(.,'Cost/Gal Installed')]]");

    			// General
    			public static final By DB_SORT_MAX = By.partialLinkText("Max");
    			public static final By DB_SORT_1Y = By.partialLinkText("1Y");
    			public static final By DB_SORT_2Y = By.partialLinkText("2Y");
    			public static final By DB_SORT_3Y = By.partialLinkText("3Y");
    			public static final By DB_SORT_YTD = By.partialLinkText("YTD");
    			public static final By DB_SHOW_5 = By.xpath("//a[text()[contains(.,'5')] and @class='count']");
    			public static final By DB_SHOW_10 = By.xpath("//a[text()[contains(.,'10')] and @class='count']");
    			public static final By DB_SHOW_25 = By.xpath("//a[text()[contains(.,'25')] and @class='count']");
    			public static final By DB_TRANSPORTATION_TAB = By.partialLinkText("Transportation");
    			public static final By DB_ST_PUBLIC_AUTHORITY_TAB = By.partialLinkText("Street Light Public Authority");
    			public static final By DB_RESIDENTIAL_TAB = By.linkText("Residential");
    			public static final By DB_PUBLIC_AUTHORITY_TAB = By.partialLinkText("Public Authority");
    			public static final By DB_NON_RESIDENTIAL_TAB = By.linkText("Non-Residential");
    			public static final By DB_MINING_TAB = By.partialLinkText("Mining");
    			public static final By DB_Irrigation_TAB = By.partialLinkText("Irrigation");
    			public static final By DB_Industrial_TAB = By.partialLinkText("Industrial");
    			public static final By DB_Commercial_TAB = By.partialLinkText("Commercial");
    			public static final By DB_Agriculture_TAB = By.partialLinkText("Agriculture");
    			public static final By DB_ALL_TAB = By.partialLinkText("ALL");
    			public static final By Img1 = By.xpath("//span[@id='lineChartGlobalId_fusionChartID']//*[name()='svg']//*[name()='g'][9]//*[name()='circle'][10]"); 
    			public static final By act_thermsave = By.xpath("//span[@id='fusioncharts-tooltip-element']/span");
    			public static final By Button_Electricity = By.id("dashboard-elec-id");
    			public static final By Img2 = By.xpath("//span[@id='lineChartGlobalId_fusionChartID']//*[name()='svg']//*[name()='g'][9]//*[name()='circle'][10]");
    			public static final By act_kwhsave = By.xpath("//span[@id='fusioncharts-tooltip-element']/span");
    			public static final By Button_RenewID = By.id("dashboard-renew-id");
    			public static final By Img3 = By.xpath("//span[@id='lineChartGlobalId_fusionChartID']//*[name()='svg']//*[name()='g'][9]//*[name()='circle'][10]");
    			public static final By act_kwInstall = By.xpath("//span[@id='fusioncharts-tooltip-element']/span");
    			public static final By Button_WaterID = By.id("dashboard-water-id");
    			public static final By Img4 = By.xpath("//span[@id='lineChartGlobalId_fusionChartID']//*[name()='svg']//*[name()='g'][9]//*[name()='circle'][10]");
    			public static final By act_water = By.xpath("//span[@id='fusioncharts-tooltip-element']/span");


    }

    public interface CustomerAppointment {
    	public static final By CUSTOMER_APPOINTMENT_LINK = Locator
				.spanId("Appointments-btnIconEl");

		// Sort Appointments
		public static final By APPOINTMENT_SORT_BY_NAME = By
				.xpath("//div/div/span[text()[.='Name']]");
		public static final By APPOINTMENT_SORT_BY_GROUP = By
				.xpath("//div/div/span[text()[.='Group']]");
		public static final By APPOINTMENT_SORT_BY_TYPE = By
				.xpath("//div/div/span[text()[.='Type']]");
		public static final By APPOINTMENT_SORT_BY_PHONE = By
				.xpath("//div/div/span[text()[.='Phone']]");
		public static final By APPOINTMENT_SORT_BY_EMAIL = By
				.xpath("//div/div/span[text()[.='Email']]");
		public static final By APPOINTMENT_SORT_BY_ADDRESS = By
				.xpath("//div/div/span[text()[.='Address']]");
		public static final By APPOINTMENT_SORT_BY_A_TIME = By
				.xpath("//div/div/span[text()[.='Assigned Time']]");
		public static final By APPOINTMENT_SORT_BY_P_TIME = By
				.xpath("//div/div/span[text()[.='Preferred Time']]");
		public static final By APPOINTMENT_SORT_BY_ALT_TIME = By
				.xpath("//div/div/span[text()[.='Alternative Time 1']]");
		public static final By APPOINTMENT_SORT_BY_ALT_TIME_2 = By
				.xpath("//div/div/span[text()[.='Alternative Time 2']]");
		public static final By APPOINTMENT_SORT_BY_STATUS = By
				.xpath("//div/div/span[text()[.='Status']]");
		public static final By APPOINTMENT_SORT_BY_NOTES = By
				.xpath("//div/div/span[text()[.='Notes']]");

    }
    
    public interface PICustomerRegistrationAndLogin {
    	public static final By L_ACCOUNT_NUMBER = inputWithName("accountNumber");
		public static final By L_SERVICE_PROVIDER = By
				.cssSelector("[name='businessUnitId']");
		public static final By L_LAST_NAME = inputWithName("lastName");
		public static final By L_ZIP_CODE = inputWithName("zipCode");
		public static final By L_EMAIL = inputWithName("emailAddress");
		public static final By L_PASSWORD = inputWithName("encryptedPassword");
		public static final By L_CONFIRM_PASSWORD = inputWithName("password_confirm");
		public static final By L_SIGN_UP_BTN = Locator
				.traksmartButtonAnchorStyle("Sign Up");
		public static final By L_LOGIN_USERNAME_EMAIL = inputWithName("j_username");
		public static final By L_LOGIN_USER_PWD = inputWithName("j_password");
		public static final By validationmsg = By.xpath(".//*[@id='topMessageHolder']/div");
		public static final By deletbutton = By.xpath("//table[1]//span[text()='Delete']");
		public static final By deletbuttonwithoutstylenone = By.xpath("//a[.!=contains(@style,'none')]//span[text()='Delete']");
		public static final By confirmdeletmsgbox = By.xpath(".//*[contains(@id,'messagebox') and contains(@class,'x-window-closable')]");
		public static final By recordsinmyrebates = By.xpath("//div[text()='No Applications available.']");
    }

    public interface ProjectDSMInput{
    	public static final By PDI_NAME = inputWithName("dsmInputName");
		public static final By PDI_DESCRIPTION = textareaWithName("description");
		public static final By PDI_SERVICE_DRPBX = inputWithName("serviceTypeId");
		public static final String PDI_ELECTRIC = "Electric";
		public static final By PDI_YEAR_DRPBX = inputWithName("year");
		public static final String PDI_YEAR = "2014";
		public static final By PDI_OTHER_DRPBX = inputWithName("dsmSystemInputId");
		public static final By projectinputdsmtext = By.xpath("//span[@class='pg_blk_links pg_name']//a");
		public static final By projectinputdsmstatus = By.xpath(".//*[@id='library_user_info_text']/li[5]");
		public static final By projectinputdsmrecordnotfound = By.xpath(".//*[@id='project-input-gridContent-notfound']");
		public static final By programinputdsmrecordnotfound = By.xpath(".//*[@id='program-input-gridContent-notfound']");
    }
    
    public interface Projects{
    	
    	public static final By dropdown_stateprovince = By.xpath("//div[contains(@id,'payeesection')]//label/span[text()='State/Province:']/../..//input");
    	public static final By project_runtimeformfields = By.xpath("//div[contains(@id,'runtimeform')]//input[@readonly='readonly']");
    	public static final By txtbox_zipcode = By.xpath("//div[contains(@id,'payeesection')]//label/span[text()='Zip/Postal Code:']/../..//input");
    	public static final By txtbox_custometsectionfirstname = By.xpath("//div[contains(@id,'customersection')]//label/span[text()='First Name:']/../..//input");
    	public static final By txtbox_customersectionlastname = By.xpath("//div[contains(@id,'customersection')]//label/span[text()='Last Name:']/../..//input");
    	public static final By BatchReview_SubmitSelectedRequestForPayment = By.xpath(".//span[text()='Submit']");
    	public static final By BatchReview_Charts_OKButton = By.xpath(".//span[text()='Ok']");
    	public static final By btn_refresh = By.xpath("//input[contains(@value,'Refresh')]");
    	public static final By measurename = By.xpath("//div[contains(@class,'x-grid-item-container')]//tbody//div");
    	public static final By btn_add = By.xpath("//span[contains(text(),'Add') and contains(@class,'x-btn-inner x-btn-inner-default-small')]/..");
    	public static final By txtbox_incentivecust = By.xpath("//span[contains(text(),'Incentive - Customer:')]/../following-sibling::div[1]/div/div/input");
    	public static final By txtbox_cloned_incentivecust = By.xpath("//div[contains(@id,'Incentive_Customer') and contains(@id,'CLONE')]//input");
    	public static final By txtbox_kwhsavings = By.xpath("//span[contains(text(),'kWh Savings:')]/../following-sibling::div[1]/div/div/input");
    	public static final By txtbox_cloned_kwhsavings = By.xpath("//div[contains(@id,'kWhSavings') and contains(@id,'CLONE')]//input");
    	public static final By txtbox_kwinstalled = By.xpath("//span[contains(text(),'kW Installed:')]/../following-sibling::div[1]/div/div/input");
    	public static final By txtbox_cloned_kwinstalled = By.xpath("//div[contains(@id,'kWInstalled') and contains(@id,'CLONE')]//input");
    	public static final By txtbox_AHRI = By.xpath("//span[contains(text(),'AHRI #:')]/../following-sibling::div[1]/div/div/input");
    	public static final By txtbox_cloned_AHRI = By.xpath("//div[contains(@id,'AHRI') and contains(@id,'CLONE')]//input");
    	public static final By txtbox_kwreduction = By.xpath("//span[contains(text(),'kW Reduction:')]/../following-sibling::div[1]/div/div/input");
    	public static final By txtbox_cloned_kwreduction = By.xpath("//div[contains(@id,'kWReduction') and contains(@id,'CLONE')]//input");
    	public static final By txtbox_watersavings = By.xpath("//span[contains(text(),'Water Savings (Gal):')]/../following-sibling::div[1]/div/div/input");
    	public static final By txtbox_cloned_watersavings = By.xpath("//div[contains(@id,'WaterSavings') and contains(@id,'CLONE')]//input");
    	public static final By txtbox_thermsavings = By.xpath("//span[contains(text(),'Therm Savings:')]/../following-sibling::div[1]/div/div/input");
    	public static final By txtbox_cloned_thermsavings = By.xpath("//div[contains(@id,'ThermSavings') and contains(@id,'CLONE')]//input");
    	public static final By txtbox_incentivepartner = By.xpath("//span[contains(text(),'Incentive - Partner ($ amount):')]/../following-sibling::div[1]/div/div/input");
    	public static final By txtbox_cloned_incentivepartner = By.xpath("//div[contains(@id,'IncentivePartneramount') and contains(@id,'CLONE')]//input");
    	public static final By txtbox_purchaseDate = By.xpath("//span[contains(text(),'Purchase Date:')]/../following-sibling::div[1]/div/div/input");
    	public static final By txtbox_installationDate = By.xpath("//span[contains(text(),'Installation Date:')]/../following-sibling::div[1]/div/div/input");
    	public static final By txtbox_kwsaving = By.xpath("//span[contains(text(),'KW Savings:')]/../following-sibling::div[1]/div/div/input");
    	
    	//public static final By txtbox_firstname = By.xpath("//input[contains(@name,'FirstName') and contains(@name,'Payees_Attributes') and not(contains(@name,'ContactFirstName'))]");
    	//public static final By txtbox_lastname = By.xpath("//input[contains(@name,'LastName') and contains(@name,'Payees_Attributes') and not(contains(@name,'ContactLastName'))]");
    	public static final By txtbox_firstname = By.xpath("//div[contains(@id,'payeesection')]//label/span[text()='First_Name:']/../..//input");
    	public static final By txtbox_lastname = By.xpath("//div[contains(@id,'payeesection')]//label/span[text()='Last_Name:']/../..//input");
    	
    	public static final By btn_projectsave = By.id("saveButton-btnIconEl");
    	public static final By btn_projectsaveandcreate = By.xpath("//div[contains(@id,'runtimeheader')]//span[text()='Save and Create New Project']");
    	public static final By title_projectname = By.xpath(".//*[@id='title']/div/div/span[2]/a");
    	public static final By msg_projsavesucces = By.xpath(".//*[@id='topMessageHolder_bottom']/div/img");
    	public static final By actualmsg_projsavesucces = By.id("topMessageHolder_bottom");
    	public static final By btn_requestpayment = By.id("requestPaymentButton-btnIconEl");
    	public static final By project_title = By.xpath("//b[contains(text(),'PNP')]");
    	public static final By project_cancelledtab = By.xpath("//div[@class='grid_tabs']//span[contains(text(),'CANCELLED')]");
    	public static final By project_inprogresstab = By.xpath("//div[@class='grid_tabs']//span[contains(text(),'IN PROGRESS')]"); 
    	public static final By txtbox_remittofirstname = By.xpath("//input[contains(@name,'REMITTOFirstName')]");
    	public static final By txtbox_remittolastname = By.xpath("//input[contains(@name,'REMITTOLastName')]");
    	public static final By txtbox_remittoaddress1 = By.xpath("//input[contains(@name,'REMITTOBillAddrLine1')]");
    	public static final By txtbox_remittoaddress2 = By.xpath("//input[contains(@name,'REMITTOBillAddrLine2')]");
    	public static final By txtbox_remittocity = By.xpath("//input[contains(@name,'REMITTOBillCity')]");
    	public static final By txtbox_remittostate = By.xpath("//input[contains(@name,'REMITTOBillState')]");
    	public static final By txtbox_remittozip = By.xpath("//input[contains(@name,'REMITTOBillPostalCode')]");  
    	public static final By drpdown_ApplicationStatus = By.xpath("//input[@role='combobox' and contains(@componentid,'ApplicationStatus')]");
    	public static final By drpdown_ProjectStatus = By.xpath("//input[@role='combobox' and .!=contains(@name,'Projects_Attributes') and contains(@name,'Status')]");
    	public static final By projectstatus_InProgress = By.xpath("//b[text()='InProgress']");
    	public static final By projectstatus_Completed = By.xpath("//b[text()='Completed']");
    	public static final By project_SelectAssignee= By.xpath("//input[@componentid='user_assignee_combo']");
    	public static final By drpdown_ProjectPriority = By.xpath("//input[contains(@componentid,'ApplicationPriority')]");
    	public static final By btn_createSubProject = By.id("measure_library_top_button_right");
    	public static final By record_Subproject = By.id("project_subproject_list_grid");
    	public static final By title_SubProjects = By.xpath("//div[@id='project_subproject_list_grid']//td[contains(@class,'x-grid-cell-first x-unselectable')]//a");
    	public static final By checkapplicationstatus = By.xpath("//div[contains(@class,'res_content_block1')]/div[1]/div[2]");
    	public static final By checkapplicationnumber = By.xpath("//div[contains(@class,'res_content_block1')]/div[2]/div[2]");
    	public static final By checkversion = By.xpath("//div[contains(@class,'res_content_block1')]/div[4]/div[2]");
    	public static final By checktask = By.xpath("//div[contains(@class,'res_content_block1')]/div[5]/div[2]");
    	public static final By checktaskassignee = By.xpath("//div[contains(@class,'res_content_block2')]/div[4]//div[contains(@class,'grid_row')]/div[2]");
    	public static final By checkcreatedandupdated = By.xpath("//div[contains(@class,'res_content_block_top')]//span[contains(@class,'pg_blk_links pg_update')]");
    	public static final By checksubprojectlink = By.xpath("//a[contains(text(),'Subprojects ')]");
    	public static final By checkInProgressProjectStatus = By.xpath("//table//span[text()='InProgress']");
    	public static final By sorting_button_lastpage = By.xpath("//div[@id='top-pagingToolBar']//span[contains(@class,'x-tbar-page-last')]");
    	public static final By sorting_button_firstpage = By.xpath("//div[@id='top-pagingToolBar']//span[contains(@class,'x-tbar-page-first')]");
    	public static final By sorting_button_nextpage = By.xpath("//div[@id='top-pagingToolBar']//span[contains(@class,'x-tbar-page-next')]");
    	public static final By sorting_button_prevpage = By.xpath("//div[@id='top-pagingToolBar']//span[contains(@class,'x-tbar-page-prev')]");
    	public static final By dropdown_advancesearch_option = By.xpath("//input[@name='paramSearchCombo']");
    	public static final By dropdown_advancesearch_value = By.xpath("//input[@role='combobox' and .!=contains(@name,'paramSearchCombo')]");
    	public static final By project_search_records = By.xpath("//table");
    	public static final By textbox_advancesearch_value = By.xpath("//div[@id='projectParameterSearch-innerCt']//input[.!=contains(@name,'paramSearchCombo')]");
    	public static final By applicationnumber = By.xpath("//div[contains(text(),'Application Number:')]/../div[2]");
    	public static final By applicationpriority = By.xpath("//div[contains(text(),'Application Priority:')]/../div[2]");
    	public static final By btn_search = By.xpath("//div[@id='projectParameterSearch']//span[contains(@id,'button') and text()='Search']");
    	public static final By drpdown_search_options = By.xpath("//div[contains(@class,' x-boundlist-floating')][1]//li");
    	public static final By drpdown_btn_search = By.xpath("//div[@id='projectParameterSearch-innerCt']//div[contains(@class,'x-form-arrow-trigger')]");
    	public static final By filters_program = By.id("program_filter_container");
    	public static final By filters_programversion = By.id("program_version_filter_container");
    	public static final By filters_programtask = By.id("program_Task_container");
    	public static final By filter_assignedTo = By.id("program_assinee_container");
    	public static final By lbl_opentaskstatus = By.xpath("//div[text()='Open']");
    	public static final By lbl_completedtaskstatus = By.xpath("//div[text()='Completed']");
    	public static final By summaryTab = By.linkText("Summary");
        public static final By taskAssignee = By.xpath("//table[@class='x-grid-item']//tr//td[2]//a");
        public static final By taskStatus = By.xpath("//table[@class='x-grid-item']//tr//td[3]//div//div");
        public static final By collapseToolBy = By.xpath("//img[contains(@class , 'x-tool-expand-bottom')]");
        
        public static final By taskName = By.xpath("//table[@class='x-grid-item']//tr//td[1]//a");
        public static final By selectAssignee = By.xpath(".//input[@name='userValueId']");
        public static final By assignmentNotApplicable = By.xpath(".//div[text()='Assignment not applicable.']");
        
        
        public static final By saveAssigneeButton = By.xpath("//span[text()='Save']"); 
        public static final By confirmSaveAssignee = By.xpath("//span[text()='OK']");
        //public static final By CancelButton = By.xpath("//span[text()='Cancel']");
        public static final By CancelButton = By.xpath("//a[@id='task_instance_form_assignment_config_form_cancel']//span[@id='task_instance_form_assignment_config_form_cancel-btnWrap']"); 
        public static final By projectsOfSelectedProgramSearchFilter = By.xpath(".//div[@id='project_list_grid-body']//table");
        public static final By updatedByAttributeofFirstProject = By.xpath(".//div[@id='project_list_grid-body']//table[1]");
        public static final By createNewProject_Button = By.xpath("//input[@value = '+ Create A New Project']");
        public static final By programFilter = By.xpath(".//div[@id='program_filter_container']");
        public static final By programVersionFilter = By.xpath(".//div[@id='program_version_filter_container']");
        public static final By openTaskFilter = By.xpath(".//div[@id='program_Task_container']");
        public static final By programAssigneeFilter = By.xpath(".//div[@id='program_assinee_container']");
        public static final By projectSummaryTitle = By.xpath(".//div[@id='summary']//b[@class='projectSummaryTitle']");
        public static final By projectStatus = By.xpath(".//div[@id='summary']//b[@class='projectSummaryTitle']//parent::div//following-sibling::div[1]");
        public static final By applicationNumber = By.xpath(".//div[@id='summary']//b[@class='projectSummaryTitle']//parent::div//following-sibling::div[2]");
        public static final By program = By.xpath(".//div[@id='summary']//b[@class='projectSummaryTitle']//parent::div//following-sibling::div[3]");
        public static final By projectOwner = By.xpath(".//div[@id='summary']//b[@class='projectSummaryTitle']//parent::div//following-sibling::div[4]");
        public static final By assignProjectOwner = By.xpath(".//div[@id='summary']//b[@class='projectSummaryTitle']//parent::div//following-sibling::div[4]//a");
        public static final By selectProjectOwner = By.id("user_assignee_combo-inputEl");
        public static final By updatedDateandBy = By.xpath(".//div[@id='summary']//b[@class='projectSummaryTitle']//parent::div//following-sibling::div[6]");
        public static final By applicationStatus = By.xpath(".//div[@id='summary']//b[@class='projectSummaryTitle']//parent::div//following-sibling::div[7]");
        public static final By daysOpen = By.xpath(".//div[@id='summary']//b[@class='projectSummaryTitle']//parent::div//following-sibling::div[10]");
        public static final By createdDateAndCreatedBy = By.xpath(".//span[@class='pg_blk_links pg_update']//span[1]");
        public static final By UpdatedBy = By.xpath(".//span[@class='pg_blk_links pg_update']//span[2]");
        public static final By UpdatedDate_Substring = By.xpath("//span[@class='pg_blk_links pg_update']");
        public static final By Project_Status = By.xpath("//span[@class='pg_blk_links pg_name']//following-sibling::span[1]");
        public static final By Project_Title = By.xpath("//span[@class='pg_blk_links pg_name']//a");
        public static final By btn_ProgPageSearchProjects = By.id("project_search_top_button_right");
        public static final By textbox_ProjectName = By.xpath("//input[@name='projectNames']");
        public static final By drpdwn_ProjectStatus = By.xpath("//input[@name='projectSelectionStatus']");
        public static final By assert_INProgressStatus = By.xpath("//div[contains(text(),'InProgress (Active)') and .!=contains(text,'Completed') and .!=contains(text,'Cancelled')]");
        public static final By assert_CompletedStatus = By.xpath("//div[contains(text(),'Completed') and .!=contains(text,'InProgress (Active)') and .!=contains(text,'Cancelled')]");
        public static final By assert_CancelledStatus = By.xpath("//div[contains(text(),'Cancelled') and .!=contains(text,'InProgress (Active)') and .!=contains(text,'Completed')]");
        public static final By assert_INProgressStatusforallstatusfilter = By.xpath("//div[contains(text(),'InProgress (Active)')]");
        public static final By assert_CompletedStatusforallstatusfilter = By.xpath("//div[contains(text(),'Completed')]");
        public static final By assert_CancelledStatusforallstatusfilter = By.xpath("//div[contains(text(),'Cancelled')]");
        
        
        public static final By drpdwn_attributes = By.xpath("//input[@name='effectiveDateCombo']");
        public static final By textbox_effectivedate = By.xpath("//input[@name='effectiveDate']");
        public static final By btn_executeformula = By.xpath("//div[@id='form_content']//span[text()[.='Execute Formula']]");
        public static final By drpdwn_applicationstatus = By.xpath("//input[contains(@id,'ApplicationStatus')]");
        public static final By project_status = By.xpath("//b[text()='Cancelled']");
        public static final By project_status_Completed = By.xpath("//b[text()='Completed']");
        public static final By measuresection_copywithdata = By.xpath("//div[contains(@id,'measuresection')]//a[contains(@class,'copy_with_data')]");
        public static final By measuresection_copywithoutdata = By.xpath("//div[contains(@id,'measuresection')]//a[contains(@class,'copy_without_data')]");
        public static final By projectnames = By.xpath("//span[@class='pg_blk_links pg_name']/a");
        public static final By projectname = By.xpath("//table[1]//span[@class='pg_blk_links pg_name']/a");
        public static final By taskordername = By.id("taskorder_desc");
        public static final By partnername = By.id("taskorder_partner");
        public static final By personnelname = By.id("taskorder_contact");
        public static final By ordertype = By.id("taskorder_ordertype");
        public static final By creationdate = By.id("taskorder_creationDate");
        public static final By taskorderdate = By.id("taskorder_dueDate");
        public static final By taskorderamount = By.id("taskorder_amount");
        public static final By btn_UpdateClosedProjects = By.xpath("//a[contains(@class,'x-btn-over')]//span[text()='Update Closed Projects']");
        public static final By First_Sub_Projectname = By.xpath("//div[@class='x-grid-item-container']//table[1]//div[contains(@class,'x-grid-cell-inner')]//div//a");
        public static final By Parent_Project_Link_In_SubProject =  By.xpath("//div[@class='x-autocontainer-innerCt']//div[contains(text(),'Parent')]//a");
        public static final By SubProjectLink =  By.xpath("//table//td[1]//a");
        public static final By ParentProjectLink =  By.xpath("//div[contains(text(),'Parent Project')]/a");
        public static final By subProjectLink_ProjectSearchPage =  By.xpath("//table//tr[2]//span[@class='pg_blk_links pg_name']/a");
        public static final By original_measure_section =  By.xpath("//div[contains(@id,'measuregroupsection')]//div[.!=contains(@class,'cloned_section') and contains(@class,'single_column')]");
        public static final By cloned_measure_section =  By.xpath("//div[contains(@id,'measuregroupsection')]//div[contains(@class,'cloned_section') and contains(@class,'single_column')]");
        public static final By project_payment_info = By.className("projectSummaryTitle");
        public static final By Payment_status = By.xpath(".//*[@id='library_user_info_text']/li[1]");
        
  
    }

    public interface Users {
    	public static final By UserSearch = By.xpath(".//input[@id='keyword_search_textfield-inputEl']");
        public static final By UserNameFieldInUserSearchPage = By.xpath(".//div[@class='res_content_block1 program_content_block']//div[1][@class='prog_wrapper']//div[@class='prog_label']");
    	public static final By entire_list_avaliable_roles = By.xpath("//div[@id='rolesList']//div[contains(@id,'boundlist-')]//ul");
    	public static final By entire_list_selected_roles = By.xpath("//div[@id='selectedRolesList']//div[contains(@id,'boundlist-')]//ul");
    	public static final By US_ADD_NEW = inputWithValue("+ Add New User");
    	 // public static final By US_USER_ID = anchorTextWithinDivId("userName_div", "User ID");
    	  public static final By User_ID = By.xpath("//span[text()='User ID']//ancestor::label//following-sibling::div//input[@role='textbox']");
    	  public static final By User_Password = By.xpath("//span[text()='Password']//ancestor::label//following-sibling::div//input[@role='textbox']");
    	  public static final By User_Confirm_Password = By.xpath("//span[text()='Confirm Password']//ancestor::label//following-sibling::div//input[@role='textbox']");
    	  public static final By User_First_Name = By.xpath("//span[text()='First Name']//ancestor::label//following-sibling::div//input[@role='textbox']");
    	  public static final By User_Last_Name = By.xpath("//span[text()='Last Name']//ancestor::label//following-sibling::div//input[@role='textbox']");
    	  public static final By user_Email = By.xpath("//span[text()='Email']//ancestor::label//following-sibling::div//input[@role='textbox']");
    	//public static final By US_ADD_NEW = inputWithValue("+ Add New User");
		//public static final By US_USER_ID = anchorTextWithinDivId("userName_div", "User ID");

		// Added by Akshay Tupe
		public static final By tab_admin = By.xpath("//*[@id='tabAdmin']/a");
		public static final By subtab_user = By.xpath("//*[@id='admin_flyout']/div/div/a[text()='Users']");
		public static final By txtbox_search = By.id("keyword_search_textfield-inputEl");
		public static final By btn_edit = By.id("editUser");
		public static final By campaign_manager = By.xpath("//div[@id='selectedRolesList']//ul/li[text()[.= 'Campaign Manager']]");
		public static final By campaign_manager_link = By.xpath("//div[@id='rolesList']//ul/li[text()[.= 'Campaign Manager']]");
		public static final By OnSite_Program_Manager = By.xpath("//div[@id='selectedRolesList']//ul/li[text()[.= 'OnSite Program Manager']]");
		public static final By OnSite_Program_Manager_Link = By.xpath("//div[@id='rolesList']//ul/li[text()[.= 'OnSite Program Manager']]");
		public static final By Onsiteappuser = By.xpath("//div[@id='selectedRolesList']//ul/li[text()[.= 'OnSite Application User']]");
		public static final By link_Onsiteappuser = By.xpath("//div[@id='rolesList']//ul/li[text()[.= 'OnSite Application User']]");
		public static final By Onsiteprojmgr = By.xpath("//div[@id='selectedRolesList']//ul/li[text()[.= 'OnSite Project Manager']]");
		public static final By link_Onsiteprojmgr = By.xpath("//div[@id='rolesList']//ul/li[text()[.= 'OnSite Project Manager']]");
		public static final By btn_save_role = By.id("saveAssignedRoles");
		public static final By txtbox_userid = By.id("textfield-1011-inputEl");		
		public static final By txtbox_firstname = By.id("textfield-1012-inputEl");	
		public static final By txtbox_lastname = By.id("textfield-1013-inputEl");		
		public static final By txtbox_password = By.id("textfield-1026-inputEl");		
		public static final By txtbox_confirmpassword = By.id("textfield-1027-inputEl");		
		public static final By drpdwn_secretques = By.id("trksmtcombo-1014-inputEl");
		public static final By drpdwn_secretans = By.id("textfield-1016-inputEl");
		public static final By txt_email = By.id("textfield-1025-inputEl");		
		public static final By btn_save = By.id("saveUser");
		public static final By btn_ok = By.id("button-1005-btnIconEl");	
		public static final By txtbox_searchuserkeyword = By.id("keyword_search_textfield-inputEl");		
		public static final By url_user = By.xpath("//a[starts-with(@href,'/traksmart4/users/user_detail.do?userId=') and contains(@data-savedtabindex-gridview-1025,'none')]");		
		public static final By btn_useredit= By.id("editUser");	
		public static final By drpdwn_userstatus = By.id("activeFlag-inputEl");		
		public static final By btn_saveuser = By.id("saveUser");		
		public static final By link_userroles = By.linkText("User Roles");
		//public static final String list_availableroles = "boundlist-1013-listEl";
		public static final String list_availableroles = "//div[@id='rolesList']//div[contains(@id,'boundlist-')]//ul";
		public static final String opt_availablerole = "li";
		public static final By btn_saveassignrole = By.id("saveAssignedRoles");
		public static final String popup_rolesassignsuccess = "button-1005-btnIconEl";
		public static final String link_summary = "Summary";
		public static final String list_assigned_role = ".//*[@id='UserDetailSummary']/div[3]/div[2]/div[2]/ul";
		public static final String list_associatedroles = "li";
		public static final By link_Security = Locator.spanText("Security");
		public static final By link_Roles = Locator.spanText("Roles");
		public static final By Btn_Add = Locator.traksmartButtonAnchorStyle("Add");
		public static final By ActiveStatus_dropdown_btn = By.xpath("//div[@id='activeFlag-trigger-picker']");
		public static final By ActiveStatus_dropdown_list = By.xpath("//li[text()='Active']");		
		public static final By Notes_UserManagementSearchPage= By.xpath("//div[contains(text(),'Notes')]/../div[@class='prog_data']");
		public static final By UserMgmtPage_lockedAt = By.xpath("//span[contains(text(),'Locked At')]/../span[@class='value']");
		public static final By UserMgmtPage_UnlockedAt = By.xpath("//span[contains(text(),'Unlock At')]/../span[@class='value']");
		public static final By popup_validation_userduplicate = By.xpath("//div[contains(@class,'x-form-display-field') and @role='textbox']");
		public static final By UserMgmtPage_CustomerID = By.xpath("//input[@role='textbox']");
		public static final By UserMgmtPage_Btn_Search = By.xpath("//input[@value='Search']");
		public static final By User_Name = By.xpath("//span[text()='User Name']//ancestor::label//following-sibling::div//input[@role='textbox']");
		public static final By popup_successmsg_usercreation = By.xpath("//div[contains(@class,'x-form-display-field') and @role='textbox']");
		public static final By UserMgmtPage_partnername = By.xpath("//input[@role='combobox']");
		public static final By UserMgmtPage_accountexpiry = By.xpath("//div[@class='expire_details']//span[@class='value']");
		public static final By UserMgmtPage_passwordexpiry = By.xpath("//div[@class='expire_details']//span[@class='value']");
		public static final By UserMgmtPage_accountlockpolicy_attemptsallowed = By.xpath("//span[contains(text(),'Attempts Allowed')]/../span[@class='value']");
		public static final By UserMgmtPage_accountlockpolicy_lockoutdetails = By.xpath("//span[contains(text(),'Lockout Duration')]/../span[@class='value']");
		public static final By UserMgmtPage_accountexpirepolicy_expireduration = By.xpath("//span[contains(text(),'Expire Duration')]/../span[@class='value']");
		public static final By UserMgmtPage_accountexpirepolicy_expirewarning = By.xpath("//span[contains(text(),'Expire Warning')]/../span[@class='value']");
		public static final By Notes_Textarea= By.xpath("//span[text()='Notes']/../..//textarea");
		public static final By usernames = By.xpath("//span[@class='pg_blk_links pg_name']/a");
		
    }
    
 // Add by Akshay Tupe
 	public interface Suit {
 		public static final By tab_admin = By.xpath("//*[@id='tabAdmin']/a");
 		public static final By subtab_system = By.xpath("//*[@id='admin_flyout']/div/div/a[2]");
 		public static final By icon_attributes = By.xpath("//span[text()[.='Attributes']]/../img[2]");
 		public static final By search_combobox = By.id("admin_attributes_search_combobox-inputEl");
 		public static final By txt_planningattri = By.xpath("//label[text()[.='Planning Attribute:']]/../input");
 		public static final By txtbox_currpwd = By.name("currPassword");
 		public static final By txtbox_newpwd = By.id("encryptedPassword-inputEl");
 		public static final By txtbox_cnfpwd = By.name("confirmPassword");
 		//public static final By drpdwn_secques = By.xpath("//body/div[5]//li[3]");
 		//public static final By drpdwn_secques = By.xpath("//body/div[5]//ul/li");
 		public static final By drpdwn_secques = By.name("secretQuestionId");
 		public static final By txtbox_secans = By.name("secretQuesAnswer");
 		public static final By Btn_Change = Locator.traksmartButtonAnchorStyle("Change");
 	}
 	
 	public interface Admin{
        public static final By Utility = Locator.spanText("Utility");
        public static final By Utility_Add_Button = Locator.spanText("Add");
        public static final By L_ADD_Utility_NAME = By.xpath("//input[@placeholder='Utility Name']");
        public static final By L_ADD_Utility_Desc = By.xpath("//input[@placeholder='Description']");
        public static final By Utility_Create_Button = Locator.spanText("Create");
        public static final By Utility_OK_Button = Locator.spanText("OK");
        public static final By Application_Status_Attribute = Locator.spanText("Application Status");
       public static final By Application_Status_Add_button = Locator.spanText("Add");
       public static final By L_ADD_Lookup_Type_Value = By.xpath("//input[@placeholder='Lookup Type Value']");
       public static final By L_ADD_Application_Status_Description = By.xpath("//input[@placeholder='Description']");
       public static final By L_ADD_Estimated_Percent_completion = By.xpath("//input[@placeholder='Estimated Percent completion']");
       public static final By Application_Status_Create_Button = Locator.spanText("Create");
       public static final By Application_Status__OK_Button = Locator.spanText("OK");
       public static final By Status_Completed = By.xpath("//div[@id='right-container']//table[1]//div[text()='COMPLETED']");
       public static final By searchdropdown = By.id("admin_attributes_search_combobox-inputEl");
       public static final By userlink = By.xpath("//table//a");
       public static final By updateclosedprojectsroles = By.xpath(".//*[@id='UserDetailSummary']//li[contains(text(),'Update Closed Projects')]");
       public static final By selectedrolelistupdateclosedprojectrole = By.xpath(".//*[@id='UserDetailSummary']//li[contains(text(),'Update Closed Projects')]");


  }
 	
 	

 	public interface TestRoles {
 		public static final By drpdwn_role_name = By.id("admin_attributes_search_combobox-inputEl");
 		public static final By src_text_box = By.xpath("//div[@id='admin_attributes_search_combobox']/../div[2]//input");
 		public static final By drpdwn_name = By.xpath("//input[@name='name']");
 		public static final By drpdwn_entitlement = By.xpath("//input[@name='entitlement']");
 		public static final String link_system = "System";	
 			public static final String link_security = "//span[contains(text(),'Security')]";		
 			public static final String link_roles = "//span[contains(text(),'Roles')]";
 			public static final String btn_addrole = "//span[contains(text(), 'Add')]";			
 			public static final By txtbox_rolename = By.xpath("//input[contains(@name, 'name')]");			
 			public static final By txtbox_roledescription = By.xpath("//input[contains(@name, 'description')]");			
 			public static final By btn_update = By.xpath("//span[starts-with(@id,'button') and contains(text(),'Update')]");
 			public static final String roles_popup_success = "messagebox-1001";
 			public static final String role_chk = "/html/body/div[1]/div[2]/div[2]/div[2]/div[3]/div/div/div[2]/div[2]/div/div/div/div/div[3]/div/div[2]/table[1]/tbody/tr/td[1]/div";		
 			public static final String link_role_entitlement = "//span[contains(text(),'Role Entitlements')]";			
 			public static final By btn_addroleentitlement= By.xpath("//span[starts-with(@id,'button') and contains(text(),'Add')]"); 			
 			public static final By drpdwn_rolename = By.xpath("//input[@name='name' and @placeholder='Select a Role Name...']/../../div[2]");			
 			public static final By drpdwn_entitlement1 = By.xpath("//input[@name='entitlement' and @placeholder='Select an Entitlement...']/../../div[2]");
 			public static final String opt_entitlement = "//li[contains(text(),'PAYMENT_MODULE_READ_WRITE')]";
 			public static final String opt_entitlement1 = "//li[contains(text(),'GLOBAL_PAGES')]";
 			public static final String opt_entitlement2 = "//li[contains(text(),'PROGRAM_MODULE_READ_ONLY')]";			
 			public static final By btn_createroleentitlement = By.xpath("//span[starts-with(@id,'button') and contains(text(),'Create')]");
 			public static final String btn_addrne = "//span[starts-with(@id,'button-') and contains(text(),'OK')]";
 			public static final String role_entitlement_association_chk = ".//table[starts-with(@id,'gridview-')][1]/tbody[1]/tr[1]/td[1]/div[1]";		
 			public static final String link_usertype_role_association = "//span[contains(text(),'User Type Roles Association')]";
 			public static final String btn_usertyperole = "//span[starts-with(@id,'button') and contains(text(),'Add')]";
 			public static final String drpdwn_usertype = "//input[@name='userType']";
 			public static final String drpdwn_roletype = "//input[@name='name']";
 			public static final String btn_updateusertyperole = "//span[starts-with(@id,'button') and contains(text(),'Update')]";
 			public static final String usertyperole_popup_success = "messagebox-1001";
 			public static final String usertype_role_association_chk = ".//table[starts-with(@id,'gridview-')][1]/tbody[1]/tr[1]/td[2]/div[1]";
 			public static final By Btn_Update = Locator.traksmartButtonAnchorStyle("Update");
 			public static final By link_roleEntitlement  = Locator.spanText("Role Entitlements");
 			public static final By Btn_Add = Locator.traksmartButtonAnchorStyle("Add");
 			public static final By Btn_EditAdd = Locator.divText("Add/Edit");
 			public static final By Drpdown_role = By.xpath("//input[@name='name']");
 			public static final By TextBox_Entitlement = By.xpath("//input[@name='entitlement']");
 			public static final String TextBoxEntitlement = "//input[@name='entitlement']";
 			public static final By Btn_Create = Locator.traksmartButtonAnchorStyle("Create");
 			public static final By Btn_AddRoleEntitlement = Locator.traksmartButtonAnchorStyle("Add");
 			public static final By Drpdown_usertype = By.xpath("//input[@name='userType']");
 			public static final By search_combobox = By.id("admin_attributes_search_combobox-inputEl");
 			
 	}

 	public interface Partners {
 		public static final By Office_Name = By.xpath("//div/span[text()[.='Office Name']]");
 		public static final By address = By.xpath("//div/span[text()[.='Address']]");
 		public static final By city = By.xpath("//div/span[text()[.='City']]");
 		public static final By state = By.xpath("//div/span[text()[.='State']]");
 		public static final By zip_code = By.xpath("//div/span[text()[.='Zip Code']]");
 		public static final By phone_number = By.xpath("//div/span[text()[.='Phone Number']]");
 		

 	}	
 	
 	public interface Scheduling {
 		public static final By L_Create_Scheduling_Group = traksmartButtonAnchorStyle("+ Create Scheduling Group");
 		//they have same class but appear on different pages.
 		public static final By L_Create_Apt_Type = traksmartButtonAnchorStyle("+ Create Appointment Type");
 		public static final By L_Group_Name = By.cssSelector("input[name='name']");
 		public static final By L_Group_Location = By.cssSelector("input[name='location']");
 		public static final By L_Group_Address1 = By.cssSelector("input[name='address1']");
 		public static final By L_Group_Address2 = By.cssSelector("input[name='address2']");
 		public static final By L_Group_City = By.cssSelector("input[name='city']");
 		public static final By L_Group_PostalCode = By.cssSelector("input[name='postalCode']");
 		public static final By L_Group_State = By.cssSelector("input[name='state']");
 		public static final By L_Group_Country = By.cssSelector("input[name='country']");
 		//actions
 		public static final By L_SaveAndContinueButton = traksmartButtonAnchorStyle("Save & Continue");
 		public static final By L_Search_Trigger = By.cssSelector(".x-form-search-trigger");
 		public static final By L_Search_Member_Result = By.cssSelector(".x-grid-item");
 		public static final By L_Search_Member_Result_First_Result = By.cssSelector(".x-grid-item a");
 		//member details
 		public static final By L_FIRST_EVENT_BLOCK1 = By.cssSelector(".sch-event:first-child");
 		public static final By L_FIRST_MENU_ITEM1 = By.cssSelector(".x-menu-item");
 		public static final By L_Message_Box = By.cssSelector(".x-message-box");
 		public static final By L_APT_TYPE_NAME = By.cssSelector("input[name='name']");
 		public static final By L_APT_TYPE_DESC = By.cssSelector("textarea[name='description']");
 		public static final By L_FIRST_EVENT_BLOCK = By.cssSelector(".sch-event:first-child");
 		public static final By L_FIRST_MENU_ITEM = By.cssSelector(".x-menu-item");
 		public static final By L_LAST_EVENT_BLOCK = By.cssSelector(".sch-event:last-child");
 		public static final By Scheduling_DrpDwn_Customer_Number = By.xpath("//div[@id='parameter_search_container']//input[@role='combobox']");
 		public static final By Scheduling_TextBox_Customer_Number = By.xpath("//div[@id='parameter_search_container']//input[@role='textbox']");
 		public static final By Scheduling_PopUp_CreateAptReq = By.xpath("//div[text()[.='Create Appointment Request']]/../../../../..");
 		public static final By Scheduling_Searchdropdwnbtn = By.xpath("//div[contains(@id,'ts-search-parametersearchitem') and contains(@class,'x-box-target')]//div[contains(@class,'x-form-trigger x-form-trigger-default x-form-arrow-trigger x-form-arrow-trigger-default')]");
 		public static final By Scheduling_SearchList = By.xpath("//li[text()='Name']");
 		public static final By Scheduling_Searchdropdwnbtn1 = By.xpath("//div[contains(@id,'ts-search-parametersearchitem') and contains(@class,'x-box-target')]//div[contains(@class,'x-form-trigger x-form-trigger-default x-form-arrow-trigger x-form-arrow-trigger-default')]");
 		public static final By Scheduling_SearchList1 = By.xpath("//li[text()='Name']");
 	}
 	
 	public interface PublicUserInterface{
 		public static final By checkvalidationmsg = By.xpath("//div[contains(@id,'topMessageHolder_bottom')]/div[text()='Information has not been entered correctly.  Please review and fix the validation errors in specific form fields.' or text()='Information has not been entered correctly. Please review and fix the validation errors in specific form fields.']");
 		public static final By txtbox_fname1 = By.xpath("//div[starts-with(@id,'payeesection')]//tbody[contains(@role,'presentation')]/tr[16]//input");
 		public static final By txtbox_PUIfname1 = By.xpath("//div[starts-with(@id,'payeesection')]//span[text()='First_Name:']/../../..//input");
 		public static final By link_progenrolset = By.linkText("Program Enroll Setting");
 		public static final By txtbox_publicname = By.id("publicName-inputEl");
 		public static final By txtbox_publicdesc = By.id("publicDescription-inputEl");
 		public static final By txtbox_publicsumm = By.id("publicSummary-inputEl");
 		public static final By drpdwn_progcat = By.id("program_category_field_combobox-inputEl");
 		public static final By btn_save = By.id("button-1067-btnIconEl");
 		public static final By msg_progenrollment_success = By.xpath("//div[@class='global-message-holder' and contains(text(),'Customer Enroll Setting save succeeded.')]");
 		public static final By PUI_Btn_Login = By.xpath(".//*[@id='loginId2-innerCt']/input");
 		//public static final By title_cust = By.xpath("//div[contains(text(),'Customer')]");
 		public static final By btn_continue = By.id("continue_button");
 		public static final By btn_continue_PUI = By.id("continueButton-btnIconEl");
 		
 		public static final By sel_measure = By.xpath(".//*[@id='gridview-1020-record-1']/tbody/tr/td");
 		//public static final By btn_add = By.id("button-1021-btnIconEl");
 		public static final By btn_add = By.xpath("//span[text()='Add']");
 		//public static final By txtbox_incentivecust = By.id("FORM-157264503-157264613-157264799-85-1-Measure_Instances_Attributes-185-Incentive_Customer-157199507-157275000-inputEl");
 		public static final By txtbox_incentivecust = By.xpath("//span[contains(text(),'Incentive - Customer:')]/../following-sibling::div[1]/div/div/input");
 		//public static final By txtbox_ahri = By.id("FORM-157264503-157264613-157275202-6-1-Measure_Instances_Attributes-106-AHRI-157199507-157275000-inputEl");
 		public static final By txtbox_ahri = By.xpath("//span[contains(text(),'AHRI #:')]/../following-sibling::div[1]/div/div/input");
 		//public static final By txtbox_incentivepartner = By.id("FORM-157264503-157264613-157275207-87-1-Measure_Instances_Attributes-187-IncentivePartneramount-157199507-157275000-inputEl");
 		public static final By txtbox_incentivepartner = By.xpath("//span[contains(text(),'Incentive - Partner ($ amount):')]/../following-sibling::div[1]/div/div/input");
 		public static final By check_status_pg = By.xpath(".//*[@id='panel-1012-innerCt']/div[1]/b");
 		public static final By title_cust = By.xpath("//div[contains(text(),'Customer')]");
 		public static final By title_cust_PUI = By.xpath(".//div[@class='x-autocontainer-outerCt']//ul//li/span[text()='Customer']");
 		//public static final By txtbox_fname = By.id("FORM-157264503-157264608-157264749-200-4-Form_Customers_Attributes-200-FirstName-79120-inputEl");
 		public static final By txtbox_fname = By.xpath(".//div//label//span[text()='First Name:']//ancestor::label//following-sibling::div[1]//div//input");
 		public static final By txtbox_Contactfname = By.xpath(".//div//label//span[text()='Contact First Name:']//ancestor::label//following-sibling::div[1]//div//input");
 		//public static final By txtbox_sitezip = By.id("FORM-157264503-157264608-157264739-229-4-Form_Customers_Attributes-231-SitePostalCode-79120-inputEl");
 		public static final By txtbox_sitezip = By.xpath("//tbody[contains(@role,'presentation')]/tr[30]//input");
 		public static final By txtbox_Customer_ZipPostal_Code = By.xpath(".//div//label//span[contains(text(),'Customer Zip/Postal Code:')]//ancestor::label//following-sibling::div[1]//div//input");
 		public static final By txtbox_Bill_ZipPostal_Code = By.xpath(".//div//label//span[contains(text(),'Bill Zip/Postal Code:')]//ancestor::label//following-sibling::div[1]//div//input");
 		public static final By txtbox_Site_ZipPostal_Code = By.xpath(".//div//label//span[contains(text(),'Site Zip/Postal Code:')]//ancestor::label//following-sibling::div[1]//div//input");
 		public static final By btn_tabcontinue = By.id("continueButton-btnIconEl");
 		public static final By btn_tabSectionReview = By.id("reviewButton-btnIconEl");//DSMC-12908
 		
 		public static final By title_partner = By.xpath("//div[contains(text(),'Partner')]");
 		//public static final By txtbox_remit_no = By.id("FORM-157264503-157264612-157264769-248-5-Project_Partners_Attributes-238-RemittanceNumber-79123-inputEl");
 		public static final By txtbox_remit_no = By.xpath("//div[starts-with(@id,'partnersection')]//tbody[contains(@role,'presentation')]/tr[1]//input");
 		//public static final By txt_box_patner_country = By.id("FORM-157264503-157264612-157264770-246-5-Project_Partners_Attributes-223-BillCountry-79123-inputEl");
 		public static final By txt_box_patner_country = By.xpath("//div[starts-with(@id,'partnersection')]//tbody[contains(@role,'presentation')]/tr[2]//input");
 		//public static final By drpdwn_sector = By.id("FORM-157264503-157264612-157264771-2007-5-Project_Partners_Attributes-503-Sector-79123-inputEl");
 		public static final By drpdwn_sector = By.xpath("//div[starts-with(@id,'partnersection')]//tbody[contains(@role,'presentation')]/tr[3]//input");
 		public static final By txtbox_personnel_lname = By.xpath("//div[starts-with(@id,'partnersection')]//tbody[contains(@role,'presentation')]/tr[4]//input");
 		//public static final By txtbox_personnel_lname = By.id("FORM-157264503-157264612-157264772-2006-5-Project_Partners_Attributes-201-LastName-79123-inputEl");
 		public static final By txtbox_personnel_fname = By.xpath("//div[starts-with(@id,'partnersection')]//tbody[contains(@role,'presentation')]/tr[26]//input");
 		//public static final By txtbox_personnel_fname = By.id("FORM-157264503-157264612-157264794-2005-5-Project_Partners_Attributes-200-FirstName-79123-inputEl");
 		public static final By txtbox_partner_email = By.xpath("//div[starts-with(@id,'partnersection')]//tbody[contains(@role,'presentation')]/tr[11]//input");
 		public static final By logo_partner_email_search = By.xpath("//span[text()='Partner Email:']/../../..//div[contains(@class,'search_logo')]");
 		
 		public static final By title_payee = By.xpath("//div[contains(text(),'Payee')]");
 		//public static final By txtbox_fname1 = By.id("FORM-157264503-157264609-157264765-1000-13-Project_Payees_Attributes-200-FirstName-79121-inputEl");
 		public static final By txtbox_lname = By.xpath("//div[starts-with(@id,'payeesection')]//tbody[contains(@role,'presentation')]/tr[2]//input");
 		//public static final By txtbox_zipcode = By.id("FORM-157264503-157264609-157264756-1006-13-Project_Payees_Attributes-220-BillPostalCode-79121-inputEl");
 		public static final By txtbox_zipcode = By.xpath("//div[starts-with(@id,'payeesection')]//tbody[contains(@role,'presentation')]/tr[7]//input");
 		public static final By btn_submit = By.id("continueButton-btnIconEl");
 		public static final By txt_confirmation = By.id("messagebox-1001-displayfield-inputEl");
 		//public static final By check_status_pg = By.xpath(".//*[@id='panel-1012-innerCt']/div[1]/b");
 		public static final By txtbox_trackno = By.id("trakid-inputEl");
 		public static final By txtbox_sitezipcode = By.id("siteZip-inputEl");
 		//public static final By btn_search = By.id("button-1023-btnInnerEl");
 		public static final By btn_search = By.xpath(".//*[@id='button-1023-btnIconEl']");
 		
 		public static final By msg_validation = By.xpath("//div[@class='global-message-holder' and contains(text(),'Please enter the required information to search.')]");
 		public static final By msg_validation1 = By.xpath("//div[@class='global-message-holder' and contains(text(),'No Application found for tracking number and zip code combination')]");
 		//public static final By chk_trackingid = By.xpath("//span[contains(@class,'public_tracking_number')");
 		public static final By chk_trackingid = By.className("public_tracking_number");
 		
 		public static final By link_exit = By.linkText("Exit");
 		public static final By txtbox_phonenumber = By.xpath("//span[contains(text(),'Phone Number:')]/../following-sibling::div[1]/div/div/input");
 		public static final By txtbox_phonenumber_By_Index = By.xpath("//div[starts-with(@id,'partnersection')]//tbody[contains(@role,'presentation')]/tr[5]//input");
 		public static final By txtbox_altphonenumber = By.xpath("//span[contains(text(),'Alt Phone Num:')]/../following-sibling::div[1]/div/div/input");
 		public static final By txtbox_MsgBox_Btn_Ok = By.xpath("//span[contains(@class,'x-btn-inner x-btn-inner-default-toolbar-small') and contains(text(),'OK')]");
 		public static final By txtbox_txtbox_Partner = By.xpath("//div[starts-with(@id,'partner')]//tbody[contains(@role,'presentation')]/tr[27]//input");
 		public static final By btn_Back = By.id("backButton");
 		public static final By btn_TabNormalcontinue = By.xpath("//span[text()='Continue']");
 		public static final By partnersection_contactnumber  = By.xpath("//div[starts-with(@id,'partnersection')]//span[text()='Contact Name:']/../../..//input");
 		public static final By partnersection_btn_back = By.xpath("//span[text()='Back']");
 		public static final By customersection_btn_back = By.xpath("//span[text()='Back']");
 		public static final By programname_PUI = By.xpath("//div[@class='program-name']//a");
 		public static final By programimage_PUI = By.xpath("//div[contains(@class,'program-summary')]/img");
 		public static final By PUI_ContinueAsGuest = By.xpath(".//input[contains(@class,'button') and @value='Continue as Guest']");
 	   public static final By PUI_WelecomeasGuest_Header = By.xpath(".//div[@class='header-wrapper']//div[contains(@class,'top_links')]//ul//li[text()='Welcome, Guest']");
 	   
 	   public static final By PUI_HelpAssist_Header = By.xpath(".//div[@class='header-wrapper']//div[contains(@class,'top_links') and contains(@class,'second')]//li[contains(.,'Having problems with your application?')][contains(.,'Call us at 1-000-000-0000 or email dsmc@nexant.com')]");
 	   
 	   
 	   public static final By PUI_Rebates_Tab = By.xpath(".//span[contains(@class,'tab') and contains(text(),'REBATES')]");
 	   public static final By PUI_CheckStatus_Tab = By.xpath(".//span[contains(@class,'tab') and contains(text(),'CHECK STATUS')]");
 	   public static final By PUI_SaveAsDraft = By.xpath("//a[@id='saveAsDraftButton']//span[text()='Save as Draft']");
 	  
 	}

 	public interface List {
		public static final By L_Create_Scheduling_Group = traksmartButtonAnchorStyle("+ Create Scheduling Group");
		//they have same class but appear on different pages.
		public static final By L_Create_Apt_Type = traksmartButtonAnchorStyle("+ Create Appointment Type");

	}

	//group details screen.
	public interface GroupDetails {
		//fields.. -
		public static final By L_Group_Name = By.cssSelector("input[name='name']");
		public static final By L_Group_Location = By.cssSelector("input[name='location']");
		public static final By L_Group_Address1 = By.cssSelector("input[name='address1']");
		public static final By L_Group_Address2 = By.cssSelector("input[name='address2']");
		public static final By L_Group_City = By.cssSelector("input[name='city']");
		public static final By L_Group_PostalCode = By.cssSelector("input[name='postalCode']");
		public static final By L_Group_State = By.cssSelector("input[name='state']");
		public static final By L_Group_Country = By.cssSelector("input[name='country']");
		//actions
		public static final By L_SaveAndContinueButton = traksmartButtonAnchorStyle("Save & Continue");



		public static final By L_Search_Trigger = By.cssSelector(".x-form-search-trigger");
		public static final By L_Search_Member_Result = By.cssSelector(".x-grid-item");
		public static final By L_Search_Member_Result_First_Result = By.cssSelector(".x-grid-item a");
		//member details
		public static final By L_FIRST_EVENT_BLOCK = By.cssSelector(".sch-event:first-child");
		public static final By L_FIRST_MENU_ITEM = By.cssSelector(".x-menu-item");

		public static final By L_Message_Box = By.cssSelector(".x-message-box");

	}

	public interface AppointmentType {
		public static final By L_APT_TYPE_NAME = By.cssSelector("input[name='name']");
		public static final By L_APT_TYPE_DESC = By.cssSelector("textarea[name='description']");

		public static final By L_FIRST_EVENT_BLOCK = By.cssSelector(".sch-event:first-child");
		public static final By L_FIRST_MENU_ITEM = By.cssSelector(".x-menu-item");

		public static final By L_LAST_EVENT_BLOCK = By.cssSelector(".sch-event:last-child");

	}

 	
	  public static boolean isElementEnabled(WebElement element) {
	        return !(isElementDisabled(element));
	    }

	  public static boolean isElementDisabled(WebElement element) {
	        String className = element.getAttribute("class");
	        return className.matches(".+(x-.+-disabled)+");
	    }
 	
}
