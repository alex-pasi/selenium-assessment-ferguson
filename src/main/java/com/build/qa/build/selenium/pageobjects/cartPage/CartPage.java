package com.build.qa.build.selenium.pageobjects.cartPage;

import com.build.qa.build.selenium.pageobjects.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Wait;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends BasePage {

    public CartPage(WebDriver driver, Wait<WebDriver> wait) {
        super(driver, wait);
    }

    @FindBy(xpath = "//*[@id=\"item-datas\"]/li/div[3]/div[2]/div/a/p")
    public WebElement productTitle;

    @FindBy(xpath = "//*[@id=\"item-datas\"]/li/div[3]/div[2]/p")
    public WebElement productId;

    @FindBy(xpath = "//div[@class='os-total table']/span[2]")
    public WebElement subtotalPrice;

    //update product price by given index
    public void updateQuantityByIndex(WebDriver driver, int index, String quantity){
        driver.findElement(By.xpath("(//input[@name='updateQuantity'])["+index+"]")).clear();
        driver.findElement(By.xpath("(//input[@name='updateQuantity'])["+index+"]")).sendKeys(quantity);
    }

    //get all product price as List<Double>
    public List<Double> getAllProductPrice(WebDriver driver){
        List<WebElement> elements = driver.findElements(By.xpath("//div[@class='total-price']/span[@class='f-bold']"));
        List<Double> result = new ArrayList<>();
        for (WebElement element : elements) {
            result.add(Double.parseDouble(element.getText().substring(1)));
        }
        return result;
    }

}
