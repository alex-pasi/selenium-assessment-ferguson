package com.build.qa.build.selenium.pageobjects.homepage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

import com.build.qa.build.selenium.pageobjects.BasePage;

public class HomePage extends BasePage {
	
	private By homePageWrapper;
	
	public HomePage(WebDriver driver, Wait<WebDriver> wait) {
		super(driver, wait);
		homePageWrapper = By.cssSelector("#wrapper.homepage");
	}
	
	public boolean onHomePage() {
		return wait.until(ExpectedConditions.presenceOfElementLocated(homePageWrapper)) != null;
	}

	@FindBy(xpath = "//div[@id='react-type-ahead-normal']//input[@name='search']")
	public WebElement searchBar;

	@FindBy(xpath = "//fieldset[@class='show-dropdown']//a[@class='fg-icon-search']")
	public WebElement searchButton;

	@FindBy(xpath = "//*[@id=\"wrapper\"]/main/div/div[4]/ul/li[1]/div/a")
	public WebElement bathroomSinkCategory;

}
