package com.xujh.cases;

import java.io.IOException;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.xujh.operation.UIOperation;
import com.xujh.util.ExcelUtil;
import com.xujh.util.ObjectUtil;

public class ExecuteCases {

	WebDriver driver;
	UIOperation ui;
	Properties p;
	
	String prop = "C:\\Workspace\\testng2\\src\\main\\java\\com\\xujh\\objects\\baidu.properties";

	@BeforeTest
	public void setUp() {
		driver = new FirefoxDriver();
	}

	@Test(dataProvider = "getTestData")
	public void executeCase(String caseName, String keyWord, String objectName,
			String objectType, String value) {
		
		ui = new UIOperation(driver);
		try {
			p = new ObjectUtil().getObjectProperties(prop);
			if (caseName != null && caseName.trim().length() != 0) {
				System.out.println("New case starts here:"+caseName);
			} else {
				ui.perform(p, keyWord, objectName, objectType, value);
			}
		} catch (IOException e) {
			System.err.println("can't find prop file!");
			e.printStackTrace();
		}
	}
	
	@DataProvider(name="getTestData")
	public Object[][] dataProvider() {
		
		Object[][] datas = null;
		Sheet sheet = ExcelUtil.getExcelContent("C:\\xujh\\ssq_ods.xlsx");
		datas = new Object[4][5];
		for (int i = 1; i < 4+1; i++) {
			Row row = sheet.getRow(i);
			for (int j = 0; j < 5; j++) {
				datas[i-1][j] = row.getCell(j).toString();
			}
		}
		
		for (int i = 0; i < datas.length; i++) {
			Object[] inner = datas[i];
			for (int j = 0; j < inner.length; j++) {
				System.out.print("datas["+i+"]["+j+"]:"+inner[j]);
			}
			System.out.println();
		}
		
		return datas;
		
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
