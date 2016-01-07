package com.ha.demo.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PurchasePage {
	private static WebElement element = null;
	
	public static String productPurchaseText(WebDriver driver){
		element = driver.findElement(By.tagName("p"));
		return element.getText();
	}
	
	
	public static String productPurchasePrice(WebDriver driver){
		element = driver.findElement(By.xpath(".//table[@class='wpsc-purchase-log-transaction-results']/tbody/tr/td[4]"));
		return element.getText();
	}
	

}
