package com.ha.demo.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
	private static WebElement element = null;
	
	public static WebElement productCategoryLink(WebDriver driver){
		element = driver.findElement(By.linkText("Product Category"));
		return element;
	}
	
	public static WebElement logoutLink(WebDriver driver){
		element = driver.findElement(By.linkText("logout"));
		return element;
	}


}
