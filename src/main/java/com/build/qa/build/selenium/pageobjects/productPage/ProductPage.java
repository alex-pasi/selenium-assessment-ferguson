package com.build.qa.build.selenium.pageobjects.productPage;

import com.build.qa.build.selenium.pageobjects.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Wait;

public class ProductPage extends BasePage {

    public ProductPage(WebDriver driver, Wait<WebDriver> wait) {
        super(driver, wait);
    }

    @FindBy(xpath = "//h2[@class='product__brand']")
    public WebElement productBrand;

    @FindBy(xpath = "//span[@itemprop='productID']")
    public WebElement productId;

}
