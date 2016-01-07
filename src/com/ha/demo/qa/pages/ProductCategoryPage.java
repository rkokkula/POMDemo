package com.ha.demo.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductCategoryPage {
	private static WebElement element = null;
	
	public static WebElement productLink(WebDriver driver){
		element = driver.findElement(By.linkText("Apple iPhone 4S 16GB SIM-Free – Black"));
		return element;
	}
	

}
