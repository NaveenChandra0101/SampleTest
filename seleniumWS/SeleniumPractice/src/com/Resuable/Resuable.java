package com.Resuable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class Resuable {
	
	 public static Properties readPropertiesFile(String fileName) throws IOException {
	      FileInputStream fis = null;
	      Properties prop = null;
	      try {
	         fis = new FileInputStream(fileName);
	         prop = new Properties();
	         prop.load(fis);
	      } catch(FileNotFoundException fnfe) {
	         fnfe.printStackTrace();
	      } catch(IOException ioe) {
	         ioe.printStackTrace();
	      } finally {
	         fis.close();
	      }
	      return prop;
	   }
	
	 
	 public static void Dropdown(WebDriver driver,String value, String xpath){
	 WebElement mySelectElement = driver.findElement(By.name(xpath));
		Select dropdown= new Select(mySelectElement);
		//To select an option - selectByVisibleText, selectByIndex, selectByValue
		//selectByVisibleText
		dropdown.selectByVisibleText(value);
	 }
	 
	 public static void Continue(WebDriver driver,int value){
		 driver.findElement(By.xpath("(//input[@value='Continue'])["+value+"]")).click();
			 
	 }
	 
	}