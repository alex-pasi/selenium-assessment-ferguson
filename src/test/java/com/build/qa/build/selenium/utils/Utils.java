package com.build.qa.build.selenium.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

public class Utils {

    public static void clickElement(WebElement element, Wait<WebDriver> wait){
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }



}
