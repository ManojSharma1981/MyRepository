package com.crm.qa.pages;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class LoginPage extends TestBase {

	

	//****************** Page Factory - OR **********************************
	@FindBy(name="username")
	WebElement usernameTxt;
	
	@FindBy(name="password")
	WebElement passwordTxt;
	
	@FindBy(xpath="//input[@type='submit']")
	WebElement loginBtn;
	
	@FindBy(xpath="//img[@src='https://classic.freecrm.com/img/logo.png']")
	WebElement crmImg;
	
	@FindBy(xpath="//a[text()='Sign Up']")
	WebElement singupLink;
	//************************************************************************
	
	//Initializing the page objects
	public LoginPage() throws IOException {
		super();
		PageFactory.initElements(driver, this);
	}
	
	
	//******************** Methods ********************************
	public String ValidateLoginPageTitle(){
		return driver.getTitle();
	}
	
	public boolean ValidateCRMImage(){
		return crmImg.isDisplayed();
	}
		
	public boolean ValidateSignUpLink(){
		return singupLink.isDisplayed();
	}
	public HomePage Login(String uname, String pwd) throws InterruptedException, IOException
	{
		usernameTxt.sendKeys(uname);
		Thread.sleep(5000);
		passwordTxt.sendKeys(pwd);
		Thread.sleep(5000);
		loginBtn.click();
		Thread.sleep(5000);
		
		return new HomePage();
		
	}

	
}
