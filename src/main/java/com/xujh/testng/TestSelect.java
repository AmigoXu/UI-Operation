package com.xujh.testng;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.*;

public class TestSelect {
	
	@Test
	public void testSelect() {
		WebDriver driver = new FirefoxDriver();
		String url = "http://www.w3school.com.cn/tiy/t.asp?f=html_select";
		driver.get(url);
		driver.switchTo().frame("i");
		Select select = new Select(driver.findElement(By.tagName("select")));
		List<WebElement> opts = select.getOptions();
		for (WebElement element : opts) {
			
			System.err.println(element.getAttribute("value"));
			System.err.println(element.getText());
		}
		select.selectByIndex(1);
		System.err.println(select.getFirstSelectedOption().getText());
		assertTrue(select.getFirstSelectedOption().getText().equals("Saab"));
		driver.quit();
	}
	
	
	
}
