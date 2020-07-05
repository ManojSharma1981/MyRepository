package com.crm.qa.pages;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class HomePage extends TestBase{
	

	//****************** Page Factory - OR **********************************
	@FindBy(xpath="//td[contains(text(),'Demo User')]")
	public
	WebElement usernameLabel;
	
	@FindBy(xpath="//td[contains(text(),'CRMPRO')]")
	@CacheLookup
	WebElement CRMPROLogoImg;
	
	@FindBy(xpath="//a[contains(text(),'Contacts')]")
	@CacheLookup
	WebElement contactsLink;
	
	@FindBy(xpath="//a[contains(text(),'New Contact')]")
	@CacheLookup
	WebElement newcontactLink;
	
	
	@FindBy(xpath="//a[contains(text(),'Deals')]")
	WebElement dealsLink;
	
	//Initializing the page objects
	public HomePage() throws IOException {
		super();
		PageFactory.initElements(driver, this);
	}
	
	//****************** Methods ***********************************************
	public String GetHomePageTitle(){
		return driver.getTitle();
	}
	
	public boolean ValidateUserNameLbl(){
		return  usernameLabel.isDisplayed();
	}
	
	public boolean ValidateCRMPROLogo(){
		return  CRMPROLogoImg.isDisplayed();
	}
	
	public ContactsPage ClickOnContactsLink() throws IOException{
		contactsLink.click();
		return new ContactsPage();
	}
		
	public DealsPage ClickOnDealsLink(){
		contactsLink.click();
		return new DealsPage();
	}
	
	public void ClickOnNewContactLink() throws InterruptedException{
		Actions action = new Actions(driver);
		action.moveToElement(contactsLink).build().perform();
		Thread.sleep(5000);
		newcontactLink.click();
		
		
	}
	

}
