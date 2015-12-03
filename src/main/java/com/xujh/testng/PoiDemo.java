package com.xujh.testng;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;



import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class PoiDemo {
	
	private static Logger logger = Logger.getLogger(PoiDemo.class);

	public void readExcel(String filePath, String fileName, String SheetName){
		File file = new File(filePath+"\\"+fileName);
		try {
			FileInputStream fis = new FileInputStream(file);
			Workbook wb = null;
			String fileExtensionString = fileName.substring(fileName.indexOf("."));
			if (fileExtensionString.equalsIgnoreCase(".xlsx")) {
				wb = new XSSFWorkbook(fis);				
			} else if (fileExtensionString.equalsIgnoreCase(".xls")) {
				wb = new HSSFWorkbook(fis);
			}
			
			Sheet sheet = wb.getSheet(SheetName);
			int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
			logger.info(sheet.getLastRowNum()+" - "+sheet.getFirstRowNum());
			
			for (int i = 0; i < rowCount+1; i++) {
				Row row = sheet.getRow(i);
				if (row == null) {
					logger.error("The row "+i+" is null...");
					continue;
				}
				logger.info(row.getFirstCellNum()+"\t"+row.getLastCellNum());
				
				for (int j = 0; j < row.getLastCellNum(); j++) {
					logger.info(row.getCell(j).getStringCellValue()+"|");
				}
				logger.info("\r\n");
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("File is not found!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("XSSFWorkbook exception");
		}
		
	}
	
	public static void main(String[] args) {
		
		PoiDemo objPoiDemo = new PoiDemo();
		String filePath = "C:\\xujh";
		objPoiDemo.readExcel(filePath, "ssq_ods.xlsx", "Sheet2");

	}

}
