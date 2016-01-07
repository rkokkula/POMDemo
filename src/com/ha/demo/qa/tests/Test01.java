package com.ha.demo.qa.tests;

import java.text.Format;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import java.util.Set;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.ha.demo.qa.pages.CheckoutPage;
import com.ha.demo.qa.pages.HomePage;
import com.ha.demo.qa.pages.ProductCategoryPage;
import com.ha.demo.qa.pages.ProductPage;
import com.ha.demo.qa.pages.PurchasePage;

// this test shows the product purchase, update quantity of product updates totals, handling pop up windows and js interactions or actions to scroll down

public class Test01 {

	private static WebDriver driver = null;
	 
	public static void main(String[] args) {
		
		
		driver = new FirefoxDriver();		 
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);	 
        driver.get("http://www.store.demoqa.com");
        WebElement menu = HomePage.productCategoryLink(driver);
        // Initiate the mouse actions
        Actions builder = new Actions(driver);
        builder.moveToElement(menu).build().perform();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement  menuOption = driver.findElement(By.linkText("iPhones"));
        menuOption.click();
        
        ProductCategoryPage.productLink(driver).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        String productPrice = ProductPage.getProductPrice(driver);
        System.out.println(productPrice);
        ProductPage.buyButton(driver).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        
        //Handling Pop Ups for Checkout
        Set<String> handles = driver.getWindowHandles();
        Iterator<String> iterator = handles.iterator();
        String childWindowHandler = null;
        while(iterator.hasNext()){
        	childWindowHandler = iterator.next();
        }   
        driver.switchTo().window(childWindowHandler);
        driver.findElement(By.xpath("//a[@class='go_to_checkout']")).click();
        
        //Update quantity
        CheckoutPage.updateQuantity(driver).clear();
        CheckoutPage.updateQuantity(driver).sendKeys("2");
        CheckoutPage.updateButton(driver).click();
        
        String t = productPrice.replaceAll("[^\\d.]","");
        float amount = Float.parseFloat(t);
        float Total = amount*2;
     
        String expectedTotal = String.format("%.2f", Total);
        // Compare the expected and updated totals
        String updatedTotalValue = CheckoutPage.getUpdatedTotal(driver);
        String updatedTotal = updatedTotalValue.replaceAll("[^\\d.]","");
        Assert.assertEquals("Totals are updated", expectedTotal, updatedTotal);
        
        CheckoutPage.continueButton(driver).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        CheckoutPage.selectOptionValue(driver);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        CheckoutPage.calculateButton(driver).click();
        // Fill the Shipping and Billing Details
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.findElement(By.id("wpsc_checkout_form_9")).sendKeys("test@test.com");
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("window.scrollBy(0,250)", "");
        
        // As of now I have written here but we can write these elements as part of checkout page
        driver.findElement(By.name("collected_data[2]")).sendKeys("Testfirstname");
        driver.findElement(By.name("collected_data[3]")).sendKeys("Testlastname");
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.findElement(By.name("collected_data[4]")).sendKeys("Testaddress");
        driver.findElement(By.name("collected_data[5]")).sendKeys("Austin");
        driver.findElement(By.name("collected_data[6]")).sendKeys("Texas");
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        Select countrydropdown = new Select(driver.findElement(By.name("collected_data[7][0]")));
		countrydropdown.selectByValue("US");
        driver.findElement(By.name("collected_data[18]")).sendKeys("1234541234");
        driver.findElement(By.name("shippingSameBilling")).click();
        CheckoutPage.purchaseButton(driver).click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        String purchaseOrderText = PurchasePage.productPurchaseText(driver);
        Assert.assertEquals("Purchase Order Complete", "Thank you, your purchase is pending. You will be sent an email once the order clears.", purchaseOrderText);
        driver.quit(); 
    
       
	}

}
