package com.qa.linkedin.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class LinkedinLoggedinPage extends BasePageWeb{

	private static Logger log = Logger.getLogger(LinkedinLoggedinPage.class);
	//create a constructor
	public LinkedinLoggedinPage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//div[contains(@class,'profile-rail-card')]")
	private WebElement profileRailCard;
	
	@FindBy(xpath="//*[@class='global-nav__me-photo ember-view']")
	private WebElement profileIcon;
	
	@FindBy(xpath="//a[@data-control-name='nav.settings_signout']")
	private WebElement signOutLink;
	
	@FindBy(xpath="//input[contains(@class,'search-global-typeahead__input')]")
	private WebElement searchEditbox;
	
	public void verifyProfileRailCardPresence() throws InterruptedException {
		log.debug("Verify the ProfileRailCard is present or not");
		Assert.assertTrue(isDisplayedElement(profileRailCard), "profileRailCard is not present");
	}
	
	public void doSignout() throws InterruptedException {
		log.debug("logout from the linkedin application");
		Assert.assertTrue(isDisplayedElement(profileIcon));
		click(profileIcon);
		Assert.assertTrue(isDisplayedElement(signOutLink));
		click(signOutLink);
		
	}
	
	public SearchResultsPage doPeopleSearch(String keyword) throws InterruptedException {
		log.debug("perform the people search for keyword:"+keyword);
		log.debug("clear the content in searchEditbox");
		clearText(searchEditbox);
		log.debug("type the content in searchEditbox");
		sendKey(searchEditbox,keyword);
		log.debug("press the enter key from keyboard");
		searchEditbox.sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		return new SearchResultsPage();
		
	}
	
}
