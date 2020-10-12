package com.qa.linkedin.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class LinkedinHomePage extends BasePageWeb{

	private static Logger log = Logger.getLogger(LinkedinHomePage.class);
	//create a constructor
	public LinkedinHomePage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="a[class='nav__logo-link']")
	private WebElement linkedinLogo;
	
	@FindBy(linkText="Sign in")
	private WebElement SigninLink;
	
	@FindBy(xpath="//h1[@class='header__content__heading ']")
	private WebElement welcomeBackHeaderTxt;
	
	@FindBy(id="username")
	private WebElement emailEditbox;
	
	@FindBy(name="session_password")
	private WebElement passwordEditbox;
	
	@FindBy(xpath="//button[@type='submit' and @aria-label='Sign in']")
	private WebElement SigninBtn;
	
	String homepgTitle="LinkedIn: Log In or Sign Up";
	String loginPgTitle="LinkedIn Login, Sign in | LinkedIn";
	public void verifyLinkedinHomePageTitle() {
		log.debug("Verifying the Linkedin home page title:"+homepgTitle);
		wait.until(ExpectedConditions.titleIs(homepgTitle));
		Assert.assertEquals(driver.getTitle(), homepgTitle);
	}
	
	public void verifyLinkedinLogoPresence() throws InterruptedException {
		log.debug("Verify the linkedin logo is present or not");
		Assert.assertTrue(isDisplayedElement(linkedinLogo), "linkedinLogo is not present");
	}
	
	public void clickSignInLink() throws InterruptedException {
		click(SigninLink);
	}
	
	
	public void verifyLinkedinLoginPageTitle() {
		log.debug("Verifying the Linkedin login page title:"+loginPgTitle);
		wait.until(ExpectedConditions.titleIs(loginPgTitle));
		Assert.assertEquals(driver.getTitle(), loginPgTitle);
	}
	
	public void verifyWelcomeBackheadingPresence() throws InterruptedException {
		log.debug("Verify the welcomeBack heading is present or not");
		Assert.assertTrue(isDisplayedElement(welcomeBackHeaderTxt), "welcomeBackHeaderTxt is not present");
	}
	
	public void clickSignInButton() throws InterruptedException {
		click(SigninBtn);
	}
	
	public LinkedinLoggedinPage doLogin(String uname,String pwd) throws InterruptedException {
		log.debug("clear the content in emailEditbox");
		clearText(emailEditbox);
		log.debug("type the content in emailEditbox:"+uname);
		sendKey(emailEditbox,uname);
		log.debug("clear the content in passwordEditbox");
		clearText(passwordEditbox);
		log.debug("type the content in passwordEditbox:"+pwd);
		sendKey(passwordEditbox,pwd);
		clickSignInButton();
		
		return new LinkedinLoggedinPage();
		
	}
	
}
