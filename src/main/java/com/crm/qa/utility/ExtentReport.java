package com.crm.qa.utility;


import org.testng.ITestResult;


import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;



public class ExtentReport{
		
					
		//helps to generate the logs in test report.
		public ExtentTest logger;
		public ITestResult result;
		
		
		public void Reporting()
		{
		if(result.getStatus() == ITestResult.FAILURE) {
            logger.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+"  -- FAILED ", ExtentColor.RED));
            logger.fail(result.getThrowable());
        }
        else if(result.getStatus() == ITestResult.SUCCESS) {
            logger.log(Status.PASS, MarkupHelper.createLabel(result.getName()+"  -- PASSED ", ExtentColor.GREEN));
        }
        else {
            logger.log(Status.SKIP, MarkupHelper.createLabel(result.getName()+"  -- SKIPPED ", ExtentColor.ORANGE));
            logger.skip(result.getThrowable());
        } 
		
	}

}
