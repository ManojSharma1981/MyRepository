package com.crm.qa.testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.DealsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.utility.ScreenshotListner;
import com.crm.qa.utility.TestUtil;

@Listeners(ScreenshotListner.class)

public class ContactsPageTest extends TestBase{
	HomePage homePage;
	TestUtil testUtil;
	ContactsPage contactsPage;
	DealsPage dealsPage;
	//ExtentReport extentreport;
	//builds a new report using the html template 
			public ExtentHtmlReporter extent; 
			public ExtentReports report; 
			
			//helps to generate the logs in test report.
			public ExtentTest logger;
			public ITestResult result;
			
			
	
	public ContactsPageTest() throws IOException {
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
	public void Setup() throws IOException, InterruptedException{
		Initialization();
		LoginPage Loginpage = new com.crm.qa.pages.LoginPage();
		testUtil = new com.crm.qa.utility.TestUtil();
		contactsPage = new ContactsPage();
		dealsPage = new DealsPage();
		homePage = Loginpage.Login(prop.getProperty("username"), prop.getProperty("password"));	
		testUtil.SwitchToFrame();
		//homePage.ClickOnContactsLink();
	}
	
	@Test(priority=1,enabled=true)
	public void VerifyContactsLabel() throws IOException{
		logger = report.createTest("Verify Contacts Label");
		homePage.ClickOnContactsLink();
		Assert.assertTrue(contactsPage.ValidateContactsLbl());
	}
	
	@Test(priority=2,enabled=true)
	public void SelectContactsTest() throws IOException{
		logger = report.createTest("Verify Contacts Label");
		homePage.ClickOnContactsLink();
		contactsPage.ContactSelection();
	}
	
	@Test(priority=3, dataProvider = "TestData", enabled=true)
	public void CreateNewContactTest(String title, String ftname, String ltname) throws InterruptedException{
		logger = report.createTest("Verify Contact Create");
		homePage.ClickOnNewContactLink();
		//contactsPage.CreateNewContact("Mr.", "ABC", "Sharma");
		contactsPage.CreateNewContact(title, ftname, ltname);
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
	
	/*@AfterTest
	public void reportFlush(){
		report.flush();
	} */
	
	@DataProvider
	public Object[][] TestData(){
		Object data[][]=TestUtil.GetTestData("contacts");
		return data;
		
	}
	
}
