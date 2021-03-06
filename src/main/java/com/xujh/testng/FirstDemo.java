package com.xujh.testng;

import java.io.File;
import java.util.List;

import java_cup.runtime.lr_parser;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.thoughtworks.selenium.Selenium;
import com.xujh.operation.UIOperation;
//import static org.testng.AssertJUnit.*;

public class FirstDemo {
	
	private WebDriver driver;

	
	public static void openAllLinks() throws Exception {

		// System.setProperty("webdriver.firefox.bin",
		// "C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
		String s = "C:\\Users\\JX186009\\AppData\\Roaming\\Mozilla\\Firefox\\Profiles\\7ywt5cn7.default";
		FirefoxProfile ffp = new FirefoxProfile(new File(s));
		WebDriver driver = new FirefoxDriver(ffp);

		driver.get("http://www.baidu.com");
		Thread.sleep(15000);

		// starts-with, ends-with, contains
		// List links = driver.findElements(By.xpath("//a[starts-with(@href,'http')]"));
		// ^=, $=, *=
		List<WebElement> links = driver.findElements(By.cssSelector("a[href^='http']"));

		if (links != null && links.size() > 0) {

			System.out.println(links.size());

			for (int i = 0; i < links.size(); i++) {
				System.out.println(((WebElement) links.get(i)).getText()
						+ "-->isDisplayed:"
						+ ((WebElement) links.get(i)).isDisplayed()
						+ "-->isEnabled:"
						+ ((WebElement) links.get(i)).isEnabled());
				System.out.println(((WebElement) links.get(i))
						.getAttribute("href") + "\n");

				if (((WebElement) links.get(i)).isDisplayed()) {

					Actions builder = new Actions(driver);
					builder.keyDown(Keys.CONTROL)
							.click((WebElement) links.get(i))
							.keyUp(Keys.CONTROL).build().perform();
				}
			}
		}
		
		

	}
	

	public void dealInvisibleElem() throws InterruptedException {
		driver.get("http://www.baidu.com");
		// invisible����
		WebElement e = driver.findElement(By.cssSelector("*[data-cmd='add']"));
		Thread.sleep(1000);
		e.click();
	}
	
	
	public void dealInvisible1() throws InterruptedException {
		driver.get("http://www.baidu.com");
		UIOperation ui = new UIOperation(driver);
		String css1 = "*[data-cmd='add']";
		ui.jsClickOn(css1);
	}
	
	@Test
	public void dealInvisible2() throws InterruptedException {
		driver.get("http://www.baidu.com");
		String css0 = "div.s-code-blocks.s-block-nav";
		String css1 = "*[data-cmd='add']";
		Actions builder = new Actions(driver);
		// Move cursor to the Main Menu Element
		builder.moveToElement(driver.findElement(By.cssSelector(css0))).perform();
		// Giving 5 Secs for submenu to be displayed
		Thread.sleep(5000);
		// Clicking on the Hidden SubMenu
		driver.findElement(By.cssSelector(css1)).click();
	}
	
	@Test
	public void verify() {
		driver.get("http://www.baidu.com");
		UIOperation uio = new UIOperation(driver);
		Assert.assertTrue(uio.isElementPresent(By.id("weather-mod")));
		Assert.assertTrue(uio.isElementPresent(By.className("everyday-item")));
		driver.get("https://www.google.com/webhp?hl=zh-CN");
		
	}
	
	@Test
	public void softverify() {
		driver.get("http://www.baidu.com");
		UIOperation uio = new UIOperation(driver);
		SoftAssert softAssert = new SoftAssert();
		WebElement e = driver.findElement(By.className("city-wather"));
		String string = e.getAttribute("href");
		System.out.println(string);
		softAssert.assertTrue(uio.isElementPresent(By.id("weather-mod")), "first");
		softAssert.assertTrue(uio.isElementPresent(By.className("everyday-item")), "second");
		System.out.println("xxxxxxxxxxxxxxxxxxxx"+uio.isElementPresent(By.className("everyday-item")));
		driver.get("https://www.google.com/webhp?hl=zh-CN");
		softAssert.assertAll();
		
	}
	
	@BeforeTest
	public void setUp() {
		// System.setProperty("webdriver.firefox.bin",
		// "C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
		String s = "C:\\Users\\JX186009\\AppData\\Roaming\\Mozilla\\Firefox\\Profiles\\7ywt5cn7.default";
		FirefoxProfile ffp = new FirefoxProfile(new File(s));
		driver = new FirefoxDriver(ffp);
	}
	
	@AfterTest
	public void tearDown() {
		driver.close();
		
	}

}
