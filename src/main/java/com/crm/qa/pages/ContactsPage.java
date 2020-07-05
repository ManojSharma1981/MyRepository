package com.crm.qa.pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.crm.qa.base.TestBase;

public class ContactsPage extends TestBase{
	
	//****************** Page Factory - OR **********************************
		@FindBy(xpath="//*[@id='vContactsForm']/table/tbody/tr[1]/td/table/tbody/tr/td[1]")
		public
		WebElement contactsLbl;
		
		@FindBy(xpath="//*[@id='vContactsForm']/table/tbody/tr[9]//td[1]/input")
		WebElement chk1;
		
		@FindBy(xpath="//*[@id='vContactsForm']/table/tbody/tr[5]//td[1]/input")
		WebElement chk2;
		
		@FindBy(id="first_name")
		WebElement firstname;
		
		@FindBy(id="surname")
		WebElement lastname;
		
		@FindBy(xpath="//*[@id='contactForm']/table/tbody/tr[1]/td/input[2]")
		WebElement saveBtn;
		

		public ContactsPage() throws IOException {
			super();
			PageFactory.initElements(driver, this);
		}
		
		//****************** Methods ***********************************************
		public boolean ValidateContactsLbl(){
			return  contactsLbl.isDisplayed();
		}
		
		public void ContactSelection(){
			chk1.click();
			chk2.click();
		}
		
		public void CreateNewContact(String title, String fname, String lname) throws InterruptedException{
			Select select = new Select(driver.findElement(By.name("title")));
			select.selectByVisibleText(title);
			firstname.sendKeys(fname);
			lastname.sendKeys(lname);
			saveBtn.click();
			
		}

}
