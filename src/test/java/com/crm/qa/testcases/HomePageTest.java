package com.crm.qa.testcases;

import java.io.IOException;

import org.junit.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
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

public class HomePageTest extends TestBase{
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
	
	public HomePageTest() throws IOException {
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
	}
	
	@Test(priority=1)
	public void HomePageTitleTest(){
		logger = report.createTest("Verify Homepage Title");
		String exptitle = "CRMPRO";
		Assert.assertEquals(exptitle, homePage.GetHomePageTitle());
	}
	
	@Test(priority=2)
	public void HomePageUserLabelTest() throws InterruptedException{
		logger = report.createTest("Verify Homepage User Label");
		testUtil.SwitchToFrame();
		Thread.sleep(5000);
		Assert.assertTrue(homePage.ValidateCRMPROLogo());
	}

	@Test(priority=3)
	public void VerifyContactsLinkTest() throws IOException{
		logger = report.createTest("Verify Homepage Contact List");
		testUtil.SwitchToFrame();
		contactsPage = homePage.ClickOnContactsLink();
	}
	
	@Test(priority=4)
	public void VerifyDealsLinkTest(){
		testUtil.SwitchToFrame();
		dealsPage = homePage.ClickOnDealsLink();
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
		driver.quit();
		*/
	}
	
	
	/*@AfterTest
	public void reportFlush(){
		report.flush();
	}
		*/
}
