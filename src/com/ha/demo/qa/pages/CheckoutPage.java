package com.ha.demo.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class CheckoutPage {
	private static WebElement element = null;
	
	public static WebElement continueButton(WebDriver driver){
		element = driver.findElement(By.linkText("Continue"));
		return element;
	}
	
	public static WebElement purchaseButton(WebDriver driver){
		element = driver.findElement(By.cssSelector("input.make_purchase.wpsc_buy_button"));
		return element;
	}
	
	public static WebElement updateButton(WebDriver driver){
		element = driver.findElement(By.xpath("//input[@name='submit'and @value='Update']"));
		return element;
	}
	
	public static void selectOptionValue(WebDriver driver){
		
		Select dropdown = new Select(driver.findElement(By.id("current_country")));
		dropdown.selectByValue("US");
	}

	public static WebElement calculateButton(WebDriver driver){
		element = driver.findElement(By.name("wpsc_submit_zipcode"));
		return element;
	}
	
	public static String getShippingPice(WebDriver driver){
		element = driver.findElement(By.xpath("//td[@class='wpsc_product_price.wpsc_product_price_0']/td[1]/span/span[@class='pricedisplay']"));
		return element.getText();
	}
	
	public static String getUpdatedTotal(WebDriver driver){
		element = driver.findElement(By.xpath("//span[@class='pricedisplay'][1]"));
		return element.getText();
	}
	
	public static WebElement updateQuantity(WebDriver driver){
		element = driver.findElement(By.name("quantity"));
		return element;
	}
	
	

}
