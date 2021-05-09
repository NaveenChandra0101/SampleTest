package com.selenium;

import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

import com.Resuable.Resuable;

public class SeleniumTest extends Resuable {
	
	

	
	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		 WebDriver driver;
	        
	        //Setting webdriver.chrome.driver property
	        System.setProperty("webdriver.chrome.driver","C:\\Users\\Shanmukh\\seleniumWS\\SeleniumPractice\\chromedriver.exe");
	        //Reading the property file
	        Properties prop = readPropertiesFile("C:\\Users\\Shanmukh\\seleniumWS\\SeleniumPractice\\propertyFile");
	       
	        //Instantiating driver object and launching browser
	        driver = new ChromeDriver();
	        
	        driver.get(prop.getProperty("url"));// url is launched
	        
	        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);	// wait of 20 secounds
	        
	        
	        driver.manage().window().maximize();
	        
	        driver.findElement(By.xpath("//a[@href='/login']")).click(); //click on login
	        
	        String validateMsg=driver.findElement(By.xpath("//h1[text()='Welcome, Please Sign In!']")).getText();
	        
	        Assert.assertEquals(validateMsg, "Welcome, Please Sign In!");// validate the welcome screen
	        
	        
	        driver.findElement(By.xpath("//input[@class='email']")).sendKeys(prop.getProperty("UserName"));//enter the username
	        
	        driver.findElement(By.xpath("//input[@class='password']")).sendKeys(prop.getProperty("Password"));//enter the pwd
	        
	        driver.findElement(By.xpath("//input[@value='Log in']")).click();// click on login
	        
	        String validateAccount=driver.findElement(By.xpath("(//a[@href='/customer/info'])[1]")).getText();
	        
	        Assert.assertEquals(validateAccount, "testdemowebshop@gmail.com");//validate the account
	        
	        driver.findElement(By.xpath("//span[text()='Shopping cart']")).click(); //Click on shopping Cart
	        
	        Thread.sleep(2000);
	        
	        //Check if the shopping cart as added item or not 
	        //if not skip the body in the if condition do next operations
	        if(!driver.findElement(By.xpath("//span[@class='cart-qty']")).getText().equalsIgnoreCase("(0)")){
	        
	        driver.findElement(By.xpath("//tbody/tr/td[5]/span[@class='td-title']/../input")).clear();
	        
	        driver.findElement(By.xpath("//tbody/tr/td[5]/span[@class='td-title']/../input")).sendKeys("0");
	        
	        driver.findElement(By.xpath("//input[@name='updatecart']")).click();
	        
	        }
	        
	        //click on BOOKS
	        driver.findElement(By.xpath("(//a[@href='/books'])[1]")).click();
	        
	        //Get the list of books
	        List<WebElement> listOfBook=driver.findElements(By.xpath("//h2[@class='product-title']"));
	        
	        //do the loop to see based on the book name select the book
	        for(int i=0;i<listOfBook.size()-1;i++){
	        	
	        if(listOfBook.get(i).getText().equalsIgnoreCase("Fiction")){
	        	
	        	listOfBook.get(i).click();
	        	
	        	break;
	        	
	        	}
	        }
	        
	        String pricedetails=driver.findElement(By.xpath("//span[@itemprop='price']")).getText();
	        
	        System.out.println(pricedetails);
	        
	        Thread.sleep(2000);
	        
	        driver.findElement(By.xpath("//input[@data-val-number='The field Qty must be a number.']")).click();
	        
	        driver.findElement(By.xpath("//input[@data-val-number='The field Qty must be a number.']")).clear();// Clear the Qty
	        
	        driver.findElement(By.xpath("//input[@data-val-number='The field Qty must be a number.']")).sendKeys("3");
	        
	        driver.findElement(By.xpath("(//input[@value='Add to cart'])[1]")).click();// add to the cart
	        
	        String validateShoopingCart=driver.findElement(By.xpath("//p[@class='content']")).getText();
	        
	        System.out.println(validateShoopingCart);
	        
	        Assert.assertEquals(validateShoopingCart, "The product has been added to your shopping cart");// Validate the shopping cart
	        
	        
	        driver.findElement(By.xpath("//span[text()='Shopping cart']")).click();// click the shopping cart
	        
	        
	        String validateSubTotal=driver.findElement(By.xpath("(//td[@class='cart-total-right'])[1]")).getText();
	        
	        
	        Assert.assertEquals(validateSubTotal, "72.00");
	        
	        driver.findElement(By.xpath("//input[@id='termsofservice']")).click();// check the box of terms of service
	        
	        driver.findElement(By.xpath("//button[@id='checkout']")).click();// Click checkout
	        
	        //Selecting New Address from drop Down and fill the mandatory details of the new address
	        SeleniumTest.Dropdown(driver,"New Address","billing_address_id"); //select the New address from drop down
	        
	        driver.findElement(By.xpath("//input[@name='BillingNewAddress.FirstName']")).clear();
	        Thread.sleep(1000);
	      
	        driver.findElement(By.xpath("//input[@name='BillingNewAddress.FirstName']")).sendKeys(prop.getProperty("firstName"));
	       	        
	        driver.findElement(By.xpath("//input[@name='BillingNewAddress.LastName']")).clear();
	        driver.findElement(By.xpath("//input[@name='BillingNewAddress.LastName']")).sendKeys(prop.getProperty("lastName"));
	        
	        
	        SeleniumTest.Dropdown(driver,"India","BillingNewAddress.CountryId");
	        
	        
	        driver.findElement(By.xpath("//input[@id='BillingNewAddress_City']")).sendKeys(prop.getProperty("city"));
	        
	        driver.findElement(By.xpath("//input[@id='BillingNewAddress_Address1']")).sendKeys(prop.getProperty("Address1"));
	        
	        driver.findElement(By.xpath("//input[@id='BillingNewAddress_Address2']")).sendKeys(prop.getProperty("Address2"));
	        
	        
	        driver.findElement(By.xpath("//input[@id='BillingNewAddress_ZipPostalCode']")).sendKeys(prop.getProperty("ZipCode"));
	       
	        
	        driver.findElement(By.xpath("//input[@id='BillingNewAddress_PhoneNumber']")).sendKeys(prop.getProperty("phonenumber"));
	        
	        SeleniumTest.Continue(driver, 1);
	       // driver.findElement(By.xpath("(//input[@value='Continue'])[1]")).click();
	        
	        //Select the shipping address
	        SeleniumTest.Dropdown(driver,prop.getProperty("shippingDropdown"),"shipping_address_id");	
	        
	        SeleniumTest.Continue(driver, 2);
	        
	        driver.findElement(By.xpath("(//input[@value='Next Day Air___Shipping.FixedRate'])[1]")).click();
	        
	        SeleniumTest.Continue(driver, 3);
	        
	        SeleniumTest.Continue(driver, 4);
	        
	        //COD validation
	        String ValidateCODPAy=driver.findElement(By.xpath("//p[text()='You will pay by COD']")).getText();
	        
	        Assert.assertEquals(ValidateCODPAy, "You will pay by COD");
	        
	        SeleniumTest.Continue(driver, 5);
	        
	        //Confirm order
	        driver.findElement(By.xpath("//input[@value='Confirm']")).click();
	        
	        String orderproceessedSuccessfully=driver.findElement(By.xpath("//strong[text()='Your order has been successfully processed!']")).getText();
	        
	        
	        Assert.assertEquals(orderproceessedSuccessfully, "Your order has been successfully processed!");
	        
	        //Continue after validation
	        SeleniumTest.Continue(driver, 1);
	        
	     
	        //Logout successfully
	        driver.findElement(By.xpath(" //a[@href='/logout']")).click();
	        
	        Thread.sleep(2000);
	        //Close the Current browser
	        driver.close();
	        
	        System.out.println("Execution completed successfully and browser closed successfully");
	        	
	        	
	        }
	             
	}
	
	
	       
	        
	        
	    
	      
	        
	     
	        	
	        	
	        	
	      
	        	
	        
	        
	        
	      
