package supportFiles;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;

public class WebEventListener extends AbstractWebDriverEventListener {

	public void beforeNavigateTo(String url, WebDriver driver) {
		System.out.println("Before navigating to: '" + url + "'");
	}

	public void afterNavigateTo(String url, WebDriver driver) {
		System.out.println("Navigated to:'" + url + "'");
	}

	public void beforeClickOn(WebElement element, WebDriver driver) {
		System.out.println("Before click on: " + element.toString()+ element.getLocation()+"Element Enabled status is "+element.isEnabled()+"Element Displayed status is "+element.isDisplayed()+"Element Selected status is "+element.isSelected());
	}

	public void afterClickOn(WebElement element, WebDriver driver) {
		System.out.println("After click on: " + element.toString()+ element.getLocation()+"Element Enabled status is "+element.isEnabled()+"Element Displayed status is "+element.isDisplayed()+"Element Selected status is "+element.isSelected());
	}

	public void onException(Throwable error, WebDriver driver) {
		System.out.println("Error occurred: " + error.toString());
	}
}