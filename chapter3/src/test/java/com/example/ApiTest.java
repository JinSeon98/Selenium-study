package com.example;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class ApiTest {

	 WebDriver driver;

	    @BeforeMethod
	    public void setup() {

	        System.setProperty("webdriver.chrome.driver",
	                "./src/test/resources/drivers/chromedriver");
	        driver = new ChromeDriver();
	        driver.get("http://demo-store.seleniumacademy.com/");

	    }
	    
	    @Test
	    public void linksTest() {
	    	List<WebElement> links = driver.findElements(By.tagName("a"));
	    	System.out.println("Total Links : " + links.size());
	    	
	    	long count = links.stream().filter(item -> item.isDisplayed()).count();
	    	System.out.println("Total Link visible " + count);
	    }
	    @Test
	    public void imgAltTest() {
	    	List<WebElement> images = driver.findElements(By.tagName("img"));
	    	
	    	System.out.println("Total images : " + images.size());
	    	
	    	List<WebElement> imagesWithOutAlt = images.stream()
	    			.filter(item -> item.getAttribute("alt") == "")
	    			.collect(Collectors.toList());
	    	
	    	System.out.println("Total images without alt attribute " + imagesWithOutAlt);
	    }
	    
	    @AfterMethod
	    public void tearDown() {
	        driver.quit();
	    }
}
