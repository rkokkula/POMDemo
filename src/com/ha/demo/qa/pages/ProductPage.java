package com.ha.demo.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductPage {
	private static WebElement element = null;
	
	public static WebElement buyButton(WebDriver driver){
		element = driver.findElement(By.cssSelector("input.wpsc_buy_button"));
		return element;
	}
	
	public static String getProductPrice(WebDriver driver){
		element = driver.findElement(By.cssSelector("span.currentprice.pricedisplay.product_price_96"));
		return element.getText();
	}


}
