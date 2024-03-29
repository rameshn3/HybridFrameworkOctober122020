package com.qa.linkedin.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class SearchResultsPage extends BasePageWeb{

	private static Logger log = Logger.getLogger(SearchResultsPage.class);
	//create a constructor
	public SearchResultsPage() {
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath="//*[contains(@class,'search-results__total')]")
	private WebElement searchResultText;
	

	@FindBy(xpath="//*[@class='global-nav__primary-items']/li/a")
	private WebElement homeTab;
	
	String searchResultsPgTitle="Search | LinkedIn";

	public void validateSearchResultsTitle() {
		log.debug("waiting for the search results page title -- "+searchResultsPgTitle);
		wait.until(ExpectedConditions.titleContains(searchResultsPgTitle));
		Assert.assertTrue(driver.getPageSource().contains(driver.getTitle()));
		
	}

	public long getResultsCount() {
		validateSearchResultsTitle();
		log.debug("wait for the search results text");
		wait.until(ExpectedConditions.visibilityOf(searchResultText));
		log.debug("getting the results text from webpage");
		String txt=searchResultText.getText();
		log.debug("results text is:"+txt);
		//txt="Showing 40,938 results";
		String[] str=txt.split(" ");
		//str[]=["Showing","40,938","results"]
		//			0			1		2
		log.debug("results count in string format is-->"+str[1]);
		String finalStringcnt=str[1].replace(",", "").trim();
		//convert the String into long format
		long count=Long.parseLong(finalStringcnt);
		//convert the String into integer format
		//int cnt=Integer.parseInt(finalStringcnt);
		
		return count;
	}

	public void clickHomeTab() throws InterruptedException {
		log.debug("check whether homeTab is clickable or not ");
		clickUsingJsExecutor(homeTab);
	}
	
}
