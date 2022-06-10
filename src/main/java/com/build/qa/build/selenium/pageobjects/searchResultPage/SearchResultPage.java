package com.build.qa.build.selenium.pageobjects.searchResultPage;

import com.build.qa.build.selenium.pageobjects.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Wait;

import java.util.List;

public class SearchResultPage extends BasePage {

    public SearchResultPage(WebDriver driver, Wait<WebDriver> wait) {
        super(driver, wait);
    }

    @FindBy(xpath = "(//p[@data-placement='1'])[1]")
    public WebElement firstElementAddToCartButton;

    @FindBy(xpath = "(//p[@data-placement='2'])[1]")
    public WebElement secondElementAddToCartButton;

    @FindBy(xpath = "//ul[@class='fg-search-results']/li[2]/div[@id='sku7289400']/div[@class='sr-content-box']/a")
    public WebElement secondElementLink;

    @FindBy(xpath = "//ul[@class='fg-search-results']/li[2]/div[@id='sku7289400']/div[@class='sr-content-box']/div[1]/a/span")
    public WebElement secondElementId;

    @FindBy(xpath = "//*[@id=\"addToCartModalForm\"]/div[5]/button[2]/span")
    public WebElement confirmAddToCartButton;

    @FindBy(xpath = "//*[@id=\"addToCartTips\"]/div/p/span/a")
    public WebElement viewCartButton;

    @FindBy(xpath = "//div[@class='word total-record']")
    public WebElement totalResults;

    @FindBy(xpath = "//li[@data-di-id='di-id-55b639a5-ac644c9']//input/..")
    public WebElement noHandleFilter;

    @FindBy(xpath = "//p[.='Number of Handles:']")
    public WebElement noHandleFilterOption;

    @FindBy(xpath = "//li[@data-di-id='di-id-55b639a5-b7564077']//input/..")
    public WebElement electronicFilter;

    @FindBy(xpath = "//div[@class='rc-fg-option']/p[.='Electronic']")
    public WebElement electronicFilterOption;

    @FindBy(xpath = "//ul[@class='fg-search-results']/li[@data-id='1']")
    public List<WebElement> allProducts;



}
