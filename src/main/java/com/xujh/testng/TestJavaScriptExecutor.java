package com.xujh.testng;

import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestJavaScriptExecutor {

	Logger logger = Logger.getLogger(TestJavaScriptExecutor.class);

	WebDriver driver = new FirefoxDriver();
	String url = "http://www.baidu.com";

	@BeforeTest
	public void setUp() {
		driver.get(url);
	}

	@AfterTest
	public void tearDown() {
		
		//driver.quit();
	}

	@Test
	public void testJSExecutor() throws InterruptedException {
		Set<Cookie> cookies = driver.manage().getCookies();
		logger.info("Domain -> name -> value ->expiry -> path");

		for (Cookie c : cookies) {
			logger.info(String.format("%s -> %s -> %s -> %s -> %s",
					c.getDomain(), c.getName(), c.getValue(), c.getExpiry(),
					c.getPath()));

		}
		Cookie cookie = new Cookie("xutest","cookiecoooooooooool");
		driver.manage().addCookie(cookie);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("alert(\"Cookie.get(\"xutest\")\")");
		Thread.sleep(5000);
	}
}
