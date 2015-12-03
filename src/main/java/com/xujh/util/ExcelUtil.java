package com.xujh.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelUtil {
	
	private static Logger logger = Logger.getLogger(ExcelUtil.class);
	
	public static Sheet getExcelContent(String fileName) {
		
		Sheet sheet = null;
		Workbook wd = null;
		try {
			FileInputStream fis = new FileInputStream(new File(fileName));
			wd = new XSSFWorkbook(fis);
			sheet = wd.getSheet("Sheet2");			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			logger.error("xujh:FileNotFound!");
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		if (wd != null) {
			try {
				wd.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
				
		return sheet;
	}
	
}
