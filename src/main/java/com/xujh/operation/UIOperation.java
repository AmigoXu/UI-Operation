package com.xujh.operation;

 
import java.util.Properties;

 
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class UIOperation {
	private WebDriver driver;
	
	public UIOperation(WebDriver driver) {
		this.driver = driver;
	}
	
	public void perform(Properties p, String operation, String objectName, String objectType, String value) {
		switch (operation) {
		case "Click":
			driver.findElement(getObject(p, objectName, objectType)).click();
			break;
		case "Open":
			driver.get(p.getProperty(objectName));
			break;
		case "SetKeys":
			driver.findElement(getObject(p, objectName, objectType)).sendKeys(value);
			break; 

		default:
			break;
		}
	}

	private By getObject(Properties p, String objectName, String objectType) {
		
		switch (objectType) {
		case "id":
			return By.id(p.getProperty(objectName));
		case "name":
			return By.name(p.getProperty(objectName));
		case "css":
			return By.cssSelector(p.getProperty(objectName));
		case "xpath":
			return By.xpath(p.getProperty(objectName));
		case "link":
			return By.linkText(p.getProperty(objectName));
		case "partialLink":
			return By.partialLinkText(p.getProperty(objectName));
		case "tagName":
			return By.tagName(p.getProperty(objectName));
		default:
			break;
		}
		// TODO Auto-generated method stub
		System.err.println("Cannot parse the objectType!");
		return null;
	}
	
	public void jsClickOn(String css) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		StringBuilder sb = new StringBuilder();
		sb.append("$(\""+ css +"\").click();");
		jse.executeScript(sb.toString());
	}
	
	public boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
