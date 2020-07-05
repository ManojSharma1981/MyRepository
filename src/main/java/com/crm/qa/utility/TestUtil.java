package com.crm.qa.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.crm.qa.base.TestBase;

public class TestUtil extends TestBase{
	
	public static String testdatasheetpath="C:\\Users\\manoj\\workspace\\FreeCRMTest\\src\\main\\java\\com\\crm\\qa\\testdata\\TestDataExcel.xlsx";
	public static Workbook book;
	public static org.apache.poi.ss.usermodel.Sheet sheet;
	
	
	
	public TestUtil() throws IOException {
		super();
	}
	
	
	//*****************************************************************************
	//To switch to a frame
	//*****************************************************************************
	public void SwitchToFrame(){
		driver.switchTo().frame("mainpanel");
	}
	
	//*****************************************************************************
	//To get data to Data Provider for New Contacts Page
	//*****************************************************************************
	public static Object[][] GetTestData(String sheetname){
		FileInputStream fs = null;
		try {
			fs = new FileInputStream(testdatasheetpath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			book = WorkbookFactory.create(fs);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		sheet = book.getSheet(sheetname);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		for(int i = 0; i < sheet.getLastRowNum(); i++){
			for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++){
				data[i][j]=sheet.getRow(i+1).getCell(j).toString();
			}
		}
		return data;	
	}
	
	//*****************************************************************************
	//To capture screenshots for the failed test cases
	//*****************************************************************************
	public void TCfailed(String methodname) 
	{
		File screenshotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        
        try 
        {
			
			FileUtils.copyFile(screenshotFile, new File("C:\\Users\\manoj\\workspace\\FreeCRMTest\\Screenshots\\"
					+"failScreen"+methodname+".jpeg"));
		} catch (IOException e) 
        {
			e.printStackTrace();
		}
        
	}
		
}
