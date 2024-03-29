package com.qa.pages;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.qa.base.TestBaseWithThreadLocal;
import com.qa.actions.UserActions;
import com.qa.assertions.Assertions;
import com.qa.base.TestBase;



public class LoginPage extends TestBase{
	Logger log = Logger.getLogger(LoginPage.class);
	private WebDriver driver;
	UserActions useraction;
	Assertions assertions;
	//Page Factory - OR:
	@FindBy(name="login")
	WebElement username;
	
	@FindBy(name="password")
	WebElement password;
	
	@FindBy(name="commit")
	WebElement loginBtn;
	
	@FindBy(xpath="//summary[@class='Header-link' and @aria-label='View profile and more']")
	WebElement profileBtn;
	
	@FindBy(xpath="//strong[@class='css-truncate-target']")
	WebElement user;
	
	@FindBy(xpath="//div[@class='Header-item Header-item--full flex-column flex-lg-row width-full flex-order-2 flex-lg-order-none mr-0 mr-lg-3 mt-3 mt-lg-0 Details-content--hidden']")
	WebElement banner;
	
	//Initializing the Page Objects:
	public LoginPage(WebDriver driver) throws InterruptedException, IOException{
		
		PageFactory.initElements(driver, this);
		this.driver=driver;
		useraction=new UserActions(driver);
		assertions=new Assertions(driver);
	}
	
	//Actions:
	
	public HomePage login(String un, String pwd, String verifyUser){
		driver.get("https://github.com/login");
		useraction.enter(username, un);
		useraction.enter(password, pwd);
		useraction.click(loginBtn);
		useraction.click(profileBtn);	
		Assert.assertTrue(assertions.compareText(user, verifyUser));
		
		useraction.click(profileBtn);
		return new HomePage(driver);
	}
	
}
