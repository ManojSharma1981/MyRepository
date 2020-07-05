package com.crm.qa.testcases;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.ITestResult;

import java.io.IOException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.crm.qa.base.TestBase;
//import com.crm.qa.utility.ExtentReport;
import com.crm.qa.utility.ScreenshotListner;

@Listeners(ScreenshotListner.class)
//@Listeners(ExtentReport.class)
public class LoginPageTest extends TestBase {
	
	com.crm.qa.pages.LoginPage LoginPage;
	//ExtentReport extentreport;
		//builds a new report using the html template 
				public ExtentHtmlReporter extent; 
				public ExtentReports report; 
				
				//helps to generate the logs in test report.
				public ExtentTest logger;
				public ITestResult result;

	public LoginPageTest() throws IOException {
		super();
	}
	
	
	@BeforeTest
	public void setExtent(){
		// initialize the HtmlReporter
		extent =  new ExtentHtmlReporter("C:/Users/manoj/workspace/FreeCRMTest/test-output/extentreport.html");
		//initialize ExtentReports and attach the HtmlReporter
		report = new ExtentReports();
		report.attachReporter(extent);
		
		//configuration items to change the look and feel
        extent.config().setReportName("ABC Project - Automated Test Report");
        extent.config().setTheme(Theme.DARK);
        extent.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
	}
	
	
	@BeforeMethod
	public void Setup() throws IOException{
		Initialization();
		LoginPage = new com.crm.qa.pages.LoginPage();
	}
	
	// Test Case 1 - To verify the login page title
	@Test(priority = 1)
	public void LoginPageTitleTest(){
		logger = report.createTest("Verify Login Page Title");
		String title = LoginPage.ValidateLoginPageTitle(); 
		String exptitle = "CRMPRO - CRM software for customer relationship management, sales, and support.";
		Assert.assertEquals(title, exptitle);
	}
	
	// Test Case 2 - To verify existence of CRM Logo on login page
	@Test(priority = 2)
	public void LoginPageCRMLogoTest(){
		logger = report.createTest("Verify Login Page CRM Logo");
		Assert.assertTrue(LoginPage.ValidateCRMImage());	
	}
	
	// Test Case 3 - To verify existence of SignUp link on login page
	@Test(priority=3)
	public void LoginPageSignUpTest(){
		logger = report.createTest("Verify Login Page SignUp Link");
		Assert.assertTrue(LoginPage.ValidateSignUpLink());
	}
	
	// Test Case 4 - To verify login process (moving from login page to homepage)
	@Test(priority = 4)
	public void LoginVerificationTest() throws InterruptedException, IOException
	{
		logger = report.createTest("Verify Login Process");
		Thread.sleep(5000);
		LoginPage.Login(prop.getProperty("username"),prop.getProperty("password"));
		Thread.sleep(5000);
	}
	
	@AfterMethod
	public void Teardown(){
		/*
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
        */
		driver.quit();
	}
	
	
	
	

}
