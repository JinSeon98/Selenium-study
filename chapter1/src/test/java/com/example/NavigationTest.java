package com.example;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class NavigationTest {
	
	WebDriver driver;
	
	 @BeforeMethod
	  public void beforeMethod() {

	      // Chromedriver 경로 설정 
	      System.setProperty("webdriver.chrome.driver",
	              "./src/test/resources/drivers/chromedriver");

	      // 웹드라이버 세션 초기화 
	      driver = new ChromeDriver();
	
	  }
	 
	 @Test
	    public void navigateToAUrl() {

	        // 웹사이트 열기 
	        driver.get("http://demo-store.seleniumacademy.com/");

	        // 페이지 제목 확인 
	        Assert.assertEquals(driver.getTitle(), "Madison Island");
	    }
 

	 @AfterMethod
	    public void afterMethod() {

	        // 테스트 종료 및 브라우저 닫기 
	        driver.quit();
	    }

}
