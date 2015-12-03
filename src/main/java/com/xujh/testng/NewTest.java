package com.xujh.testng;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

public class NewTest {
	
	WebDriver driver = null;
	String url = "http://www.baidu.com";
	
	
  @Test(dataProvider="SearchStrProvider")
  public void f(String user, String ss) throws InterruptedException {
	  WebElement inputText = driver.findElement(By.id("kw"));
	  inputText.sendKeys(ss);
	  System.out.println("username is:"+user+"search string is:"+ss);
	  Thread.sleep(5000);
	  String testss = inputText.getAttribute("value");
	  System.out.println(ss+"::::"+testss);
	  inputText.clear();
	  Assert.assertEquals(testss, ss, "Not equal!!!");
  }
  
  @DataProvider(name="SearchStrProvider")
  public Object[][] getSearchData(ITestContext c){
	  Object[][] sd = null;
	  sd = new Object[][] {
			  {"user_a","teradata"},
			  {"user_b","SAS"}
	  };
	  return sd;
  }
  
  @BeforeClass
  public void beforeClass() {
	  System.setProperty("webdriver.ie.driver","C:\\IEDriverServer.exe");
	  driver = new InternetExplorerDriver();
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  driver.get(url);
  }

  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
