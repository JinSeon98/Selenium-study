package com.example;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import static org.testng.Assert.assertEquals;

import java.net.URL;
import java.text.MessageFormat;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;

public class BmiCalculatorTest {
  
	WebDriver driver;

  @BeforeMethod
  public void setUp() throws Exception {
  
	  String SAUCE_USER = "";
	  String SAUCE_KEY = "";
	  
	  DesiredCapabilities caps = new DesiredCapabilities();
	  caps.setCapability("platform", "macOS Sierra - 10.12");
	  caps.setCapability("browserName", "Safari");
	  caps.setCapability("name", "BMI Calculator Test");
	  driver = new RemoteWebDriver(
			  new URL(MessageFormat.format("http://{0}:{1}@ondemand.saucelabs.com:4444/wd/hub", SAUCE_USER, SAUCE_KEY)), caps);
	  driver.get("http://cookbook.seleniumacademy.com/bmicalculator.html");
  }

  @Test
  public void testBmiCalc() {
	  WebElement height = driver.findElement(By.name("heightCMS"));
	  height.sendKeys("181");
	  
	  WebElement weight = driver.findElement(By.name("weightkg"));
	  weight.sendKeys("80");
	  
	  WebElement calculateButton = driver.findElement(By.id("Calculate"));
	  calculateButton.click();
	  
	  WebElement bmi = driver.findElement(By.name("bmi"));
	  assertEquals(bmi.getAttribute("value"), "24.4");
	  
	  WebElement bmi_category = driver.findElement(By.name("bmi_category"));
	  assertEquals(bmi_category.getAttribute("value"), "Normal");
	  
  }
  
  @AfterMethod
  public void tearDown() throws Exception {
	  driver.quit();
  }

}
